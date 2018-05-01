/*
* This is the source code of iGap for Android
* It is licensed under GNU AGPL v3.0
* You should have received a copy of the license in this archive (see LICENSE).
* Copyright © 2017 , iGap - www.iGap.net
* iGap Messenger | Free, Fast and Secure instant messaging application
* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
* All rights reserved.
*/

package net.iGap.request;

import net.iGap.proto.ProtoGroupGetMemberList;

public class RequestGroupGetMemberList {

    public void getMemberList(long roomId, int offset, int limit, ProtoGroupGetMemberList.GroupGetMemberList.FilterRole role) {

        ProtoGroupGetMemberList.GroupGetMemberList.Builder builder = ProtoGroupGetMemberList.GroupGetMemberList.newBuilder();
        builder.setRoomId(roomId);
        builder.setFilterRole(role);
        builder.setPagination(new RequestPagination().pagination(offset, limit));

        RequestWrapper requestWrapper = new RequestWrapper(317, builder, roomId + "");
        try {
            RequestQueue.sendRequest(requestWrapper);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
