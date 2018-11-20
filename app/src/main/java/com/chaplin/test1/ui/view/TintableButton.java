package com.chaplin.test1.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.chaplin.test1.R;

public class TintableButton extends AppCompatImageView {

    private static final int DEFAULT_COLOR_DISABLED = Color.argb(0xff, 0x5d, 0x5a, 0x69);
    private static final int DEFAULT_ALPHA_COLOR = Color.parseColor("#44000000");

    protected int mTintColor = Color.TRANSPARENT;
    private ColorStateList mTintColorList;

    public TintableButton(Context context) {
        super(context);
        initLayout(context, null, 0);
    }

    public TintableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs, 0);
    }

    public TintableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs, 0);
    }

    public TintableButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs, defStyleRes);
    }

    private void initLayout(Context context, AttributeSet attrs, int defStyle) {
        int color = Color.TRANSPARENT;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TintableButton, defStyle, 0);
            color = a.getColor(R.styleable.TintableButton_tintColor, color);
            a.recycle();
        }
        mTintColor = color;
        setupColorStateList(mTintColor, false);
        setColorFilter(color);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        setColorFilter(mTintColor);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mTintColorList != null && mTintColorList.isStateful()) {
            int color = mTintColorList.getColorForState(getDrawableState(), mTintColor);
            setColorFilter(color);
        }
    }

    public int getCurrentColor() {
        return mTintColor;
    }

    public void setButtonTintColor(int color) {
        setButtonTintColor(color, false);
    }

    public void setButtonTintColor(int color, boolean disabledAsPressed) {
        mTintColor = color;
        setupColorStateList(mTintColor, disabledAsPressed);
        setColorFilter(color);
    }

    private void setupColorStateList(int color, boolean disabledAsPressed) {
        int alphaColor = color != 0 ?
                Color.argb(0x99, Color.red(color), Color.green(color), Color.blue(color)) :
                DEFAULT_ALPHA_COLOR;
        mTintColorList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{}
                },
                new int[]{
                        disabledAsPressed ? alphaColor : DEFAULT_COLOR_DISABLED,
                        alphaColor,
                        color
                }
        );
    }
}
