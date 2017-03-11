package net.fumapp_android.ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import net.fumapp_android.R;

/**
 * Created by rodrigolmti on 18/02/17.
 */

public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Drawable getBackground() {
        return ContextCompat.getDrawable(getContext(), R.drawable.custom_item_border);
    }
}
