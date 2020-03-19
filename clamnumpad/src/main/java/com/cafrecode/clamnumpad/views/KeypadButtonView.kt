package com.cafrecode.clamnumpad.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.andexert.library.RippleView
import com.andexert.library.RippleView.OnRippleCompleteListener
import com.cafrecode.clamnumpad.R
import com.cafrecode.clamnumpad.interfaces.KeypadButtonClickedListener

/**
 * Created by frederick on 12/29/16.
 */
class KeypadButtonView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(mContext, attrs, defStyleAttr), OnRippleCompleteListener {
    private var mKeyboardButtonClickedListener: KeypadButtonClickedListener? =
        null
    private lateinit var mRippleView: RippleView
    private fun initializeView(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs != null && !isInEditMode) {
            val attributes = mContext.theme.obtainStyledAttributes(
                attrs, R.styleable.KeypadButtonView,
                defStyleAttr, 0
            )
            val text =
                attributes.getString(R.styleable.KeypadButtonView_lp_keyboard_button_text)
            val image =
                attributes.getDrawable(R.styleable.KeypadButtonView_lp_keyboard_button_image)
            val rippleEnabled = attributes.getBoolean(
                R.styleable.KeypadButtonView_lp_keyboard_button_ripple_enabled,
                true
            )
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(
                R.layout.keypad_button,
                this
            ) as KeypadButtonView
            if (text != null) {
                val textView = view.findViewById<TextView>(R.id.keyboard_button_textview)
                if (textView != null) {
                    textView.text = text
                }
            }
            if (image != null) {
                val imageView =
                    view.findViewById<ImageView>(R.id.keyboard_button_imageview)
                if (imageView != null) {
                    imageView.setImageDrawable(image)
                    imageView.visibility = View.VISIBLE
                }
            }
            mRippleView = view.findViewById(R.id.pin_code_keyboard_button_ripple)
            mRippleView.setOnRippleCompleteListener(this)
            if (mRippleView != null) {
                if (!rippleEnabled) {
                    mRippleView!!.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun setOnRippleAnimationEndListener(keypadButtonClickedListener: KeypadButtonClickedListener?) {
        mKeyboardButtonClickedListener = keypadButtonClickedListener
    }
    //    @Override
//    public void onRippleAnimationEnd() {
//        if (mKeyboardButtonClickedListener != null) {
//            mKeyboardButtonClickedListener.onRippleAnimationEnd();
//        }
//    }
    /**
     * Retain touches for [com.andexert.library.RippleView].
     * Otherwise views above will not have the event.
     */
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        onTouchEvent(event)
        return false
    }

    override fun onComplete(rippleView: RippleView) {
        if (mKeyboardButtonClickedListener != null) {
            mKeyboardButtonClickedListener!!.onRippleAnimationEnd()
        }
    }

    init {
        initializeView(attrs, defStyleAttr)
    }
}