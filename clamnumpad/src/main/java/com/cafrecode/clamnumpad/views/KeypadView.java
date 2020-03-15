package com.cafrecode.clamnumpad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cafrecode.clamnumpad.R;
import com.cafrecode.clamnumpad.enums.KeypadButtonEnum;
import com.cafrecode.clamnumpad.interfaces.KeypadButtonClickedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 12/29/16.
 */
public class KeypadView extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private KeypadButtonClickedListener mKeypadButtonClickedListener;

    private List<KeypadButtonView> mButtons;

    public KeypadView(Context context) {
        this(context, null);
    }

    public KeypadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeypadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        initializeView(attrs, defStyleAttr);
    }

    private void initializeView(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null && !isInEditMode()) {
            final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.PinCodeView,
                    defStyleAttr, 0);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            KeypadView view = (KeypadView) inflater.inflate(R.layout.keypad, this);

            initKeyboardButtons(view);
        }
    }

    /**
     * Init the keyboard buttons (onClickListener)
     */
    private void initKeyboardButtons(KeypadView view) {
        mButtons = new ArrayList<>();
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_0));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_1));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_2));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_3));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_4));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_5));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_6));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_7));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_8));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_9));
        mButtons.add((KeypadButtonView) view.findViewById(R.id.pin_code_button_clear));

        for(View button : mButtons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(mKeypadButtonClickedListener == null) {
            return;
        }

        int id = v.getId();
        if(id == R.id.pin_code_button_0) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_0);
        } else if(id == R.id.pin_code_button_1) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_1);
        } else if(id == R.id.pin_code_button_2) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_2);
        } else if(id == R.id.pin_code_button_3) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_3);
        } else if(id == R.id.pin_code_button_4) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_4);
        } else if(id == R.id.pin_code_button_5) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_5);
        } else if(id == R.id.pin_code_button_6) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_6);
        } else if(id == R.id.pin_code_button_7) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_7);
        } else if(id == R.id.pin_code_button_8) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_8);
        } else if(id == R.id.pin_code_button_9) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_9);
        } else if(id == R.id.pin_code_button_clear) {
            mKeypadButtonClickedListener.onKeypadClick(KeypadButtonEnum.BUTTON_CLEAR);
        }
    }


    public void setKeypadButtonClickedListener(KeypadButtonClickedListener keypadButtonClickedListener) {
        this.mKeypadButtonClickedListener = keypadButtonClickedListener;
        for(KeypadButtonView button : mButtons) {
            button.setOnRippleAnimationEndListener(mKeypadButtonClickedListener);
        }
    }
}
