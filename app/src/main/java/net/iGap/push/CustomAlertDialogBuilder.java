package net.iGap.push;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import net.iGap.G;
import net.iGap.R;

public class CustomAlertDialogBuilder extends AlertDialog.Builder {

    private final Context mContext;
    private TextView mTitle, mMessage;
    LinearLayout titleLinearLayout;
    private ImageView mIcon, thumb;


    public CustomAlertDialogBuilder(Context context, int titleColor) {
        super(context);
        mContext = context;
        View customTitle = View.inflate(mContext, R.layout.alert_dialog_title, null);
        mTitle = (TextView) customTitle.findViewById(R.id.alertTitle);
        mTitle.setTextColor(Color.WHITE);
        mTitle.setText(R.string.app_name);
        titleLinearLayout = (LinearLayout) customTitle.findViewById(R.id.title_template);
        titleLinearLayout.setBackgroundColor(G.context.getResources().getColor(R.color.colorPrimaryDark));
        if (titleColor != 0)
            titleLinearLayout.setBackgroundColor(G.context.getResources().getColor(titleColor));

        mIcon = (ImageView) customTitle.findViewById(R.id.icon);

        mIcon.setImageResource(R.drawable.ic_info);
        setCustomTitle(customTitle);

        View customMessage = View.inflate(mContext, R.layout.alert_dialog_message, null);
        thumb = (ImageView) customMessage.findViewById(R.id.alert_thumb);
        mMessage = (TextView) customMessage.findViewById(R.id.alert_dialog_message);
        setView(customMessage);
//        mTitle.setTypeface(getContext() Utils.Fonts.Roya);
//        mMessage.setTypeface(getTypeface(Utils.Fonts.CurrentFont()));
    }

    Picasso p;

    public void setThumb(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            thumb.setVisibility(View.VISIBLE);
            p = new Picasso.Builder(mContext).build();
            p.load(imgUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(thumb);
        }
        //                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))

//        thumb.setImageResource(R.mipmap.ic_launcher);
    }

    public void setThumb(int sourceId, int color) {
        try {
            thumb.setVisibility(View.VISIBLE);
            thumb.setImageResource(sourceId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                thumb.setColorFilter(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomAlertDialogBuilder setTitle(int textResId) {
        mTitle.setText(textResId);
        changeType(mTitle.getText().toString());
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setTitle(CharSequence text) {
        mTitle.setText(text);
        changeType(mTitle.getText().toString());
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setMessage(int textResId) {

        mMessage.setText(textResId);
//        changeType(mMessage.getText().toString());
        return this;
    }

    private void changeType(String s) {

    }

    @Override
    public CustomAlertDialogBuilder setMessage(CharSequence text) {
        mMessage.setText(text);
        changeType(mMessage.getText().toString());
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }


}