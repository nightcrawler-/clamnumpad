package com.cafrecode.clamnumpad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.cafrecode.clamnumpad.R;
import com.cafrecode.clamnumpad.interfaces.KeypadButtonClickedListener;

/**
 * Created by frederick on 12/29/16.
 */
public class KeypadButtonView extends RelativeLayout implements RippleView.OnRippleCompleteListener {

    private KeypadButtonClickedListener mKeyboardButtonClickedListener;

    private Context mContext;
    private RippleView mRippleView;

    public KeypadButtonView(Context context) {
        this(context, null);
    }

    public KeypadButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeypadButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        initializeView(attrs, defStyleAttr);
    }

    private void initializeView(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null && !isInEditMode()) {
            final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.KeypadButtonView,
                    defStyleAttr, 0);
            String text = attributes.getString(R.styleable.KeypadButtonView_lp_keyboard_button_text);
            Drawable image = attributes.getDrawable(R.styleable.KeypadButtonView_lp_keyboard_button_image);
            boolean rippleEnabled = attributes.getBoolean(R.styleable.KeypadButtonView_lp_keyboard_button_ripple_enabled, true);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            KeypadButtonView view = (KeypadButtonView) inflater.inflate(R.layout.keypad_button, this);

            if (text != null) {
                TextView textView = view.findViewById(R.id.keyboard_button_textview);
                if (textView != null) {
                    textView.setText(text);
                }
            }
            if (image != null) {
                ImageView imageView = view.findViewById(R.id.keyboard_button_imageview);
                if (imageView != null) {
                    imageView.setImageDrawable(image);
                    imageView.setVisibility(View.VISIBLE);
                }
            }

            mRippleView = view.findViewById(R.id.pin_code_keyboard_button_ripple);
            mRippleView.setOnRippleCompleteListener(this);
            if (mRippleView != null) {
                if (!rippleEnabled) {
                    mRippleView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    public void setOnRippleAnimationEndListener(KeypadButtonClickedListener keypadButtonClickedListener) {
        mKeyboardButtonClickedListener = keypadButtonClickedListener;
    }

//    @Override
//    public void onRippleAnimationEnd() {
//        if (mKeyboardButtonClickedListener != null) {
//            mKeyboardButtonClickedListener.onRippleAnimationEnd();
//        }
//    }

    /**
     * Retain touches for {@link com.andexert.library.RippleView}.
     * Otherwise views above will not have the event.
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        onTouchEvent(event);
        return false;
    }


    @Override
    public void onComplete(RippleView rippleView) {
        if (mKeyboardButtonClickedListener != null) {
            mKeyboardButtonClickedListener.onRippleAnimationEnd();
        }
    }
}
