package net.iGap.helper;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import net.iGap.G;

public class Setting {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "Stors";

    @SuppressLint("CommitPrefEdits")
    private static void setupSetting() {
        if (pref == null) {
            pref = G.context.getSharedPreferences(PREF_NAME, 0);
            editor = pref.edit();
        }
    }


    public static void setCurrentTabIndex(int index) {
        setupSetting();
        editor.putInt("CurrentTabIndex", index);
        editor.commit();
    }

    public static int getCurrentTabIndex() {
        setupSetting();
        return pref.getInt("CurrentTabIndex", HelperCalander.isPersianUnicode ? 4 : 0);
    }
}
