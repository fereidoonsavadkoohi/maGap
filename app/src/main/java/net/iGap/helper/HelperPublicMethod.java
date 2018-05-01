/*
* This is the source code of iGap for Android
* It is licensed under GNU AGPL v3.0
* You should have received a copy of the license in this archive (see LICENSE).
* Copyright © 2017 , iGap - www.iGap.net
* iGap Messenger | Free, Fast and Secure instant messaging application
* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
* All rights reserved.
*/

package net.iGap.helper;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import net.iGap.G;
import net.iGap.activities.ActivityMain;
import net.iGap.interfaces.OnChatGetRoom;
import net.iGap.interfaces.OnUserInfoResponse;
import net.iGap.proto.ProtoGlobal;
import net.iGap.realm.RealmRegisteredInfo;
import net.iGap.realm.RealmRoom;
import net.iGap.realm.RealmRoomFields;
import net.iGap.request.RequestChatGetRoom;
import net.iGap.request.RequestUserInfo;

import io.realm.Realm;


public class HelperPublicMethod {

    public static void goToChatRoom(final long peerId, final OnComplete onComplete, final OnError onError) {

        final Realm realm = Realm.getDefaultInstance();
        final RealmRoom realmRoom = realm.where(RealmRoom.class).equalTo(RealmRoomFields.CHAT_ROOM.PEER_ID, peerId).findFirst();

        if (realmRoom != null) {

            if (onComplete != null) {
                onComplete.complete();
            }

            goToRoom(realmRoom.getId(), -1);
        } else {
            G.onChatGetRoom = new OnChatGetRoom() {
                @Override
                public void onChatGetRoom(final ProtoGlobal.Room room) {

                    if (onError != null) {
                        onError.error();
                    }

                    getUserInfo(peerId, room.getId(), onComplete, onError);

                    G.onChatGetRoom = null;
                }

                @Override
                public void onChatGetRoomTimeOut() {

                    if (onError != null) {
                        onError.error();
                    }
                }

                @Override
                public void onChatGetRoomError(int majorCode, int minorCode) {

                    if (onError != null) {
                        onError.error();
                    }
                }
            };

            new RequestChatGetRoom().chatGetRoom(peerId);
        }
        realm.close();
    }

    private static void getUserInfo(final long peerId, final long roomId, final OnComplete onComplete, final OnError onError) {

        G.onUserInfoResponse = new OnUserInfoResponse() {
            @Override
            public void onUserInfo(final ProtoGlobal.RegisteredUser user, String identity) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        if (user.getId() == peerId) {
                            Realm realm = Realm.getDefaultInstance();

                            realm.executeTransactionAsync(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    RealmRegisteredInfo.putOrUpdate(realm, user);
                                }
                            }, new Realm.Transaction.OnSuccess() {
                                @Override
                                public void onSuccess() {
                                    try {

                                        if (onComplete != null) {
                                            onComplete.complete();
                                        }

                                        goToRoom(roomId, peerId);

                                        G.onUserInfoResponse = null;

                                    } catch (IllegalStateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            realm.close();
                        }
                    }
                });
            }

            @Override
            public void onUserInfoTimeOut() {

                if (onError != null) {
                    onError.error();
                }
            }

            @Override
            public void onUserInfoError(int majorCode, int minorCode) {

                if (onError != null) {
                    onError.error();
                }
            }
        };

        new RequestUserInfo().userInfo(peerId);
    }

    //**************************************************************************************************************************************

    private static void goToRoom(long roomid, long peerId) {

        Intent intent = new Intent(G.context, ActivityMain.class);
        intent.putExtra(ActivityMain.openChat, roomid);
        if (peerId >= 0) {
            intent.putExtra("PeerID", peerId);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        G.context.startActivity(intent);
    }

    public interface OnComplete {
        void complete();
    }

    public interface OnError {
        void error();
    }

    //**************************************************************************************************************************************
}
