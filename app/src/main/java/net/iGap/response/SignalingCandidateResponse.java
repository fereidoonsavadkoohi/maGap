/*
* This is the source code of iGap for Android
* It is licensed under GNU AGPL v3.0
* You should have received a copy of the license in this archive (see LICENSE).
* Copyright © 2017 , iGap - www.iGap.net
* iGap Messenger | Free, Fast and Secure instant messaging application
* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
* All rights reserved.
*/

package net.iGap.response;

import net.iGap.G;
import net.iGap.proto.ProtoSignalingCandidate;

public class SignalingCandidateResponse extends MessageHandler {

    public int actionId;
    public Object message;
    public String identity;

    public SignalingCandidateResponse(int actionId, Object protoClass, String identity) {
        super(actionId, protoClass, identity);

        this.message = protoClass;
        this.actionId = actionId;
        this.identity = identity;
    }

    @Override
    public void handler() {
        super.handler();

        ProtoSignalingCandidate.SignalingCandidateResponse.Builder builder = (ProtoSignalingCandidate.SignalingCandidateResponse.Builder) message;

        if (builder.getResponse().getId().isEmpty()) {
            if (G.iSignalingCandidate != null) {
                G.iSignalingCandidate.onCandidate(builder.getPeerSdpMId(), builder.getPeerSdpMLineIndex(), builder.getPeerCandidate());
            }
        }
    }

    @Override
    public void timeOut() {
        super.timeOut();
    }

    @Override
    public void error() {
        super.error();
    }
}


