///*
//* This is the source code of iGap for Android
//* It is licensed under GNU AGPL v3.0
//* You should have received a copy of the license in this archive (see LICENSE).
//* Copyright © 2017 , iGap - www.iGap.net
//* iGap Messenger | Free, Fast and Secure instant messaging application
//* The idea of the RooyeKhat Media Company - www.RooyeKhat.co
//* All rights reserved.
//*/
//
//package net.iGap.module;
//
//import android.os.Build;
//import java.util.Comparator;
//import net.iGap.adapter.items.RoomItem;
//
//public enum SortRooms implements Comparator<RoomItem> {
//    ASC {
//        @Override public int compare(RoomItem o1, RoomItem o2) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                return Long.compare(o1.getInfo().getUpdatedTime(), o2.getInfo().getUpdatedTime());
//            } else {
//                return Long.valueOf(o1.getInfo().getUpdatedTime()).compareTo(Long.valueOf(o2.getInfo().getUpdatedTime()));
//            }
//        }
//    }, DESC {
//        @Override public int compare(RoomItem o1, RoomItem o2) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                return Long.compare(o2.getInfo().getUpdatedTime(), o1.getInfo().getUpdatedTime());
//            } else {
//                return Long.valueOf(o2.getInfo().getUpdatedTime()).compareTo(Long.valueOf(o1.getInfo().getUpdatedTime()));
//            }
//        }
//    }
//}
