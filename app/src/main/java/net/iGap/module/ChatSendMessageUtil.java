/*
* This is the source code of iGap for Android
* It is licensed under GNU AGPL v3.0
* You should have received a copy of the license in this archive (see LICENSE).
* Copyright © 2017 , iGap - www.iGap.net
* iGap Messenger | Free, Fast and Secure instant messaging application
* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
* All rights reserved.
*/

package net.iGap.module;

import android.os.Handler;
import android.os.Looper;

import net.iGap.G;
import net.iGap.interfaces.OnChatSendMessageResponse;
import net.iGap.proto.ProtoGlobal;
import net.iGap.realm.RealmRoomMessage;
import net.iGap.realm.RealmRoomMessageFields;
import net.iGap.request.RequestChannelSendMessage;
import net.iGap.request.RequestChatSendMessage;
import net.iGap.request.RequestGroupSendMessage;

import io.realm.Realm;

/**
 * util for chat send messages
 * useful for having callback from different activities
 */
public class ChatSendMessageUtil implements OnChatSendMessageResponse {
    private RequestChatSendMessage requestChatSendMessage;
    private RequestGroupSendMessage requestGroupSendMessage;
    private RequestChannelSendMessage requestChannelSendMessage;

    private ProtoGlobal.Room.Type roomType;
    private OnChatSendMessageResponse onChatSendMessageResponseChat;
    private OnChatSendMessageResponse onChatSendMessageResponseRoom;
    private OnChatSendMessageResponse onChatSendMessageResponseFragmentMainRoom;

    public ChatSendMessageUtil newBuilder(ProtoGlobal.Room.Type roomType, ProtoGlobal.RoomMessageType messageType, long roomId) {
        this.roomType = roomType;

        switch (roomType) {
            case CHAT:
                requestChatSendMessage = new RequestChatSendMessage().newBuilder(messageType, roomId);
                break;
            case GROUP:
                requestGroupSendMessage = new RequestGroupSendMessage().newBuilder(messageType, roomId);
                break;
            case CHANNEL:
                requestChannelSendMessage = new RequestChannelSendMessage().newBuilder(messageType, roomId);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil message(String value) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.message(value);
                break;
            case GROUP:
                requestGroupSendMessage.message(value);
                break;
            case CHANNEL:
                requestChannelSendMessage.message(value);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil attachment(String value) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.attachment(value);
                break;
            case GROUP:
                requestGroupSendMessage.attachment(value);
                break;
            case CHANNEL:
                requestChannelSendMessage.attachment(value);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil build(ProtoGlobal.Room.Type roomType, long roomId, RealmRoomMessage message) {
        ChatSendMessageUtil builder = newBuilder(roomType, message.getMessageType(), roomId);
        if (message.getMessage() != null && !message.getMessage().isEmpty()) {
            builder.message(message.getMessage());
        }
        if (message.getAttachment() != null && message.getAttachment().getToken() != null && !message.getAttachment().getToken().isEmpty()) {
            builder.attachment(message.getAttachment().getToken());
        }
        if (message.getRoomMessageContact() != null) {
            builder.contact(message.getRoomMessageContact().getFirstName(), message.getRoomMessageContact().getLastName(), message.getRoomMessageContact().getPhones().get(0).getString());
        }
        if (message.getLocation() != null) {
            builder.location(message.getLocation().getLocationLat(), message.getLocation().getLocationLong());
        }

        if (message.getForwardMessage() != null) {
            builder.forwardMessage(message.getForwardMessage().getRoomId(), message.getForwardMessage().getMessageId());
        }
        if (message.getReplyTo() != null) {
            builder.replyMessage(message.getReplyTo().getMessageId());
        }

        builder.sendMessage(Long.toString(message.getMessageId()));
        return this;
    }

    public ChatSendMessageUtil buildForward(ProtoGlobal.Room.Type roomType, long roomId, RealmRoomMessage message, long forwardRoomId, long forwardMessageId) {
        ChatSendMessageUtil builder = newBuilder(roomType, message.getMessageType(), roomId);
        if (message.getMessage() != null && !message.getMessage().isEmpty()) {
            builder.message(message.getMessage());
        }
        if (message.getAttachment() != null && message.getAttachment().getToken() != null && !message.getAttachment().getToken().isEmpty()) {
            builder.attachment(message.getAttachment().getToken());
        }
        if (message.getRoomMessageContact() != null) {
            builder.contact(message.getRoomMessageContact().getFirstName(), message.getRoomMessageContact().getLastName(), message.getRoomMessageContact().getPhones().get(0).getString());
        }
        if (message.getLocation() != null) {
            builder.location(message.getLocation().getLocationLat(), message.getLocation().getLocationLong());
        }

        if (message.getForwardMessage() != null) {
            builder.forwardMessage(forwardRoomId, forwardMessageId);
        }
        if (message.getReplyTo() != null) {
            builder.replyMessage(message.getReplyTo().getMessageId());
        }

        builder.sendMessage(Long.toString(message.getMessageId()));
        return this;
    }

    public ChatSendMessageUtil contact(ProtoGlobal.RoomMessageContact value) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.contact(value);
                break;
            case GROUP:
                requestGroupSendMessage.contact(value);
                break;
            case CHANNEL:
                requestChannelSendMessage.contact(value);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil contact(String firstName, String lastName, String phoneNumber) {
        ProtoGlobal.RoomMessageContact.Builder value = ProtoGlobal.RoomMessageContact.newBuilder();
        value.setFirstName(firstName);
        value.setLastName(lastName);
        //value.addEmail();
        //value.setNickname();
        value.addPhone(phoneNumber);

        ProtoGlobal.RoomMessageContact built = value.build();

        switch (roomType) {
            case CHAT:
                requestChatSendMessage.contact(built);
                break;
            case GROUP:
                requestGroupSendMessage.contact(built);
                break;
            case CHANNEL:
                requestChannelSendMessage.contact(built);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil location(ProtoGlobal.RoomMessageLocation value) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.location(value);
                break;
            case GROUP:
                requestGroupSendMessage.location(value);
                break;
            case CHANNEL:
                requestChannelSendMessage.location(value);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil replyMessage(long messageId) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.replyMessage(messageId);
                break;
            case GROUP:
                requestGroupSendMessage.replyMessage(messageId);
                break;
            case CHANNEL:
                requestChannelSendMessage.replyMessage(messageId);
                break;
        }
        return this;
    }

    public ChatSendMessageUtil location(double lat, double lon) {
        ProtoGlobal.RoomMessageLocation.Builder location = ProtoGlobal.RoomMessageLocation.newBuilder();
        location.setLat(lat);
        location.setLon(lon);

        switch (roomType) {
            case CHAT:
                requestChatSendMessage.location(location.build());
                break;
            case GROUP:
                requestGroupSendMessage.location(location.build());
                break;
            case CHANNEL:
                requestChannelSendMessage.location(location.build());
                break;
        }
        return this;
    }

    public ChatSendMessageUtil forwardMessage(long roomId, long messageId) {

        ProtoGlobal.RoomMessageForwardFrom.Builder forward = ProtoGlobal.RoomMessageForwardFrom.newBuilder();
        forward.setRoomId(roomId);
        forward.setMessageId(messageId);

        switch (roomType) {
            case CHAT:
                requestChatSendMessage.forwardMessage(forward.build());
                break;
            case GROUP:
                requestGroupSendMessage.forwardMessage(forward.build());
                break;
            case CHANNEL:
                requestChannelSendMessage.forwardMessage(forward.build());
                break;
        }

        return this;
    }

    public void setOnChatSendMessageResponseChatPage(OnChatSendMessageResponse response) {
        this.onChatSendMessageResponseChat = response;
    }

    public void setOnChatSendMessageResponseRoomList(OnChatSendMessageResponse response) {
        this.onChatSendMessageResponseRoom = response;
    }

    public void setOnChatSendMessageResponseFragmentMainRoomList(OnChatSendMessageResponse response) {
        this.onChatSendMessageResponseFragmentMainRoom = response;
    }

    public void sendMessage(String fakeMessageIdAsIdentity) {
        switch (roomType) {
            case CHAT:
                requestChatSendMessage.sendMessage(fakeMessageIdAsIdentity);
                break;
            case GROUP:
                requestGroupSendMessage.sendMessage(fakeMessageIdAsIdentity);
                break;
            case CHANNEL:
                requestChannelSendMessage.sendMessage(fakeMessageIdAsIdentity);
                break;
        }

        if (!G.userLogin) {
            makeFailed(Long.parseLong(fakeMessageIdAsIdentity));
        }
    }

    /**
     * change message status from sending to failed
     *
     * @param fakeMessageId messageId that create when created this message
     */
    private void makeFailed(final long fakeMessageId) {
        // message failed
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                final Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmRoomMessage.setStatusFailedInChat(realm, fakeMessageId);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        final RealmRoomMessage message = realm.where(RealmRoomMessage.class).equalTo(RealmRoomMessageFields.MESSAGE_ID, fakeMessageId).findFirst();
                        if (message != null && message.getStatus().equals(ProtoGlobal.RoomMessageStatus.FAILED.toString())) {
                            G.chatSendMessageUtil.onMessageFailed(message.getRoomId(), message);
                        }

                        realm.close();
                    }
                });
            }
        });
    }

    @Override
    public void onMessageUpdate(long roomId, long messageId, ProtoGlobal.RoomMessageStatus status, String identity, ProtoGlobal.RoomMessage roomMessage) {
        if (onChatSendMessageResponseChat != null) {
            onChatSendMessageResponseChat.onMessageUpdate(roomId, messageId, status, identity, roomMessage);
        }

        if (onChatSendMessageResponseRoom != null) {
            onChatSendMessageResponseRoom.onMessageUpdate(roomId, messageId, status, identity, roomMessage);
        }

        if (onChatSendMessageResponseFragmentMainRoom != null) {
            onChatSendMessageResponseFragmentMainRoom.onMessageUpdate(roomId, messageId, status, identity, roomMessage);
        }
    }

    @Override
    public void onMessageReceive(long roomId, String message, ProtoGlobal.RoomMessageType messageType, ProtoGlobal.RoomMessage roomMessage, ProtoGlobal.Room.Type roomType) {
        if (onChatSendMessageResponseChat != null) {
            onChatSendMessageResponseChat.onMessageReceive(roomId, message, messageType, roomMessage, roomType);
        }

        if (onChatSendMessageResponseRoom != null) {
            onChatSendMessageResponseRoom.onMessageReceive(roomId, message, messageType, roomMessage, roomType);
        }

        if (onChatSendMessageResponseFragmentMainRoom != null) {
            onChatSendMessageResponseFragmentMainRoom.onMessageReceive(roomId, message, messageType, roomMessage, roomType);
        }
    }

    @Override
    public void onMessageFailed(long roomId, RealmRoomMessage roomMessage) {
        if (onChatSendMessageResponseChat != null) {
            onChatSendMessageResponseChat.onMessageFailed(roomId, roomMessage);
        }

        if (onChatSendMessageResponseRoom != null) {
            onChatSendMessageResponseRoom.onMessageFailed(roomId, roomMessage);
        }

        if (onChatSendMessageResponseFragmentMainRoom != null) {
            onChatSendMessageResponseFragmentMainRoom.onMessageFailed(roomId, roomMessage);
        }
    }
}
