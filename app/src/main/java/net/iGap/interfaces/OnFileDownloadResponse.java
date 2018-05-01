/*
* This is the source code of iGap for Android
* It is licensed under GNU AGPL v3.0
* You should have received a copy of the license in this archive (see LICENSE).
* Copyright © 2017 , iGap - www.iGap.net
* iGap Messenger | Free, Fast and Secure instant messaging application
* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
* All rights reserved.
*/

package net.iGap.interfaces;

import net.iGap.proto.ProtoFileDownload;

public interface OnFileDownloadResponse {
    void onFileDownload(String token, long offset, ProtoFileDownload.FileDownload.Selector selector, int progress);

    void onError(int majorCode, int minorCode, String token, ProtoFileDownload.FileDownload.Selector selector);
}
