package net.iGap.push;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import net.iGap.R;


public class PushDialogActivity extends Activity {
    private static final String TAG = "finalsoftUDA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog();
    }

    // ==============================================================================

    @Override
    protected void onPause() {
        super.onPause();
    }

    private boolean showDialog() {
        try {
            if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("title")) {
                final CustomAlertDialogBuilder cab = new CustomAlertDialogBuilder(this, R.color.red);
                cab.setTitle(getIntent().getExtras().getString("title", ""));
                cab.setMessage(getIntent().getExtras().getString("content"));
                cab.setThumb(getIntent().getExtras().getString("img_link", ""));
                boolean cancel = getIntent().getExtras().containsKey("btn_cancel_text");
                String ok = getIntent().getExtras().getString("btn_ok_text", "خوب");
                final String url = getIntent().getExtras().getString("link", "");
                final String packageName = getIntent().getExtras().getString("package", "");
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cab.setPositiveButton(ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (!url.isEmpty()) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (!packageName.isEmpty())
                                intent.setPackage(packageName);
                            startActivity(intent);
                        }
                        PushDialogActivity.this.finish();
                    }
                });
                if (cancel) {
                    cab.setNegativeButton(getIntent().getExtras().getString("btn_cancel_text"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PushDialogActivity.this.finish();
                        }
                    });
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    cab.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            PushDialogActivity.this.finish();
                        }
                    });
                }
                cab.setCancelable(false);
//                cab.setCanceledOnTouchOutside(false);
                cab.show();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
