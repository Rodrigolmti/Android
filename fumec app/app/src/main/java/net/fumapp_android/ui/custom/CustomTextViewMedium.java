package net.fumapp_android.ui.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class CustomTextViewMedium extends TextView {

    public CustomTextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Medium.ttf"));
    }
}
