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

import net.iGap.module.FileUploadStructure;
import net.iGap.proto.ProtoFileUploadOption;

public class RequestFileUploadOption {

    public void fileUploadOption(FileUploadStructure fileUploadStructure, String identity) {

        ProtoFileUploadOption.FileUploadOption.Builder fileUploadOption = ProtoFileUploadOption.FileUploadOption.newBuilder();
        fileUploadOption.setSize(fileUploadStructure.fileSize);

        try {
            RequestWrapper requestWrapper = new RequestWrapper(700, fileUploadOption, identity);

            RequestQueue.sendRequest(requestWrapper);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
