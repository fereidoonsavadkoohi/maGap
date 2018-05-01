package net.iGap.module;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MyPhoneStateListener extends PhoneStateListener {

    public static int lastPhoneState = TelephonyManager.CALL_STATE_IDLE;
    public static boolean isBlutoothOn = false;

    public void onCallStateChanged(int state, String incomingNumber) {

        if (lastPhoneState == state || !MusicPlayer.isMusicPlyerEnable) {
            return;
        } else {

            lastPhoneState = state;

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    pauseSoundIfPlay();
                    break;
                case TelephonyManager.CALL_STATE_IDLE:

                    if (MusicPlayer.pauseSoundFromCall) {
                        MusicPlayer.pauseSoundFromCall = false;
                        MusicPlayer.playSound();

                        //if (isBlutoothOn) {
                        //    isBlutoothOn = false;
                        //
                        //    AudioManager am = (AudioManager) G.context.getSystemService(Context.AUDIO_SERVICE);
                        //    am.setBluetoothScoOn(true);
                        //}
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    pauseSoundIfPlay();
                    break;
            }
        }
    }

    private void pauseSoundIfPlay() {

        if (MusicPlayer.mp != null && MusicPlayer.mp.isPlaying()) {

            MusicPlayer.pauseSound();
            MusicPlayer.pauseSoundFromCall = true;

            //AudioManager am = (AudioManager) G.context.getSystemService(Context.AUDIO_SERVICE);

            //if (am.isBluetoothScoOn()) {
            //    isBlutoothOn = true;
            //    am.setBluetoothScoOn(false);
            //
            //    try {
            //        am.stopBluetoothSco();
            //    } catch (Exception e) {
            //        HelperLog.setErrorLog("myPhoneStateListener    " + e.toString());
            //    }
            //}
        }
    }
}