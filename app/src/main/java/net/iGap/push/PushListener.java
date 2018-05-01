package net.iGap.push;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class PushListener extends PusheListenerService {
    private static final String TAG = "finalsoftPL" ;
    @Override
    public void onMessageReceived(JSONObject message, JSONObject content) {
        Log.i(TAG, "onMessageReceived Custom json Message: " + message.toString());
        try {
            if (message.length() == 0) return;
            switch (message.getString("type")) {
                case "link": {
                    try {
                        String url = message.getString("link");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (message.has("package") && !message.getString("package").isEmpty())
                            intent.setPackage(message.getString("package"));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                }
                case "dialog": {

                    try {
                        Intent intent = new Intent(this, PushDialogActivity.class);
                        intent.putExtra("title", message.getString("title"));
                        intent.putExtra("content", message.getString("content"));

                        if (message.has("link"))
                            intent.putExtra("link", message.getString("link"));

                        if (message.has("package_name"))
                            intent.putExtra("package", message.getString("package_name"));

                        if (message.has("img_link"))
                            intent.putExtra("img_link", message.getString("img_link"));

                        if (message.has("btn_ok_text"))
                            intent.putExtra("btn_ok_text", message.getString("btn_ok_text"));

                        if (message.has("btn_cancel_text"))
                            intent.putExtra("btn_cancel_text", message.getString("btn_cancel_text"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        } catch (Exception e) {
            Log.e("finalsoft", "onMessageReceived Custom json Exception Message: " + e.getMessage());
        }
    }
}
