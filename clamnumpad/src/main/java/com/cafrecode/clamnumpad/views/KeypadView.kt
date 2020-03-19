package com.cafrecode.clamnumpad.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.cafrecode.clamnumpad.R
import com.cafrecode.clamnumpad.enums.KeypadButtonEnum
import com.cafrecode.clamnumpad.interfaces.KeypadButtonClickedListener
import java.util.*

/**
 * Created by frederick on 12/29/16.
 */
class KeypadView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr), View.OnClickListener {
    private var mKeypadButtonClickedListener: KeypadButtonClickedListener? =
        null
    private lateinit var mButtons: MutableList<KeypadButtonView>

    private fun initializeView(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs != null && !isInEditMode) {
            val attributes = mContext.theme.obtainStyledAttributes(
                attrs, R.styleable.PinCodeView,
                defStyleAttr, 0
            )
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view =
                inflater.inflate(R.layout.keypad, this) as KeypadView
            initKeyboardButtons(view)
        }
    }

    /**
     * Init the keyboard buttons (onClickListener)
     */
    private fun initKeyboardButtons(view: KeypadView) {
        mButtons = ArrayList()
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_0) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_1) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_2) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_3) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_4) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_5) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_6) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_7) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_8) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_9) as KeypadButtonView)
        mButtons.add(view.findViewById<View>(R.id.pin_code_button_clear) as KeypadButtonView)
        for (button in mButtons) {
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        if (mKeypadButtonClickedListener == null) {
            return
        }
        val id = v.id
        if (id == R.id.pin_code_button_0) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_0)
        } else if (id == R.id.pin_code_button_1) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_1)
        } else if (id == R.id.pin_code_button_2) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_2)
        } else if (id == R.id.pin_code_button_3) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_3)
        } else if (id == R.id.pin_code_button_4) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_4)
        } else if (id == R.id.pin_code_button_5) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_5)
        } else if (id == R.id.pin_code_button_6) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_6)
        } else if (id == R.id.pin_code_button_7) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_7)
        } else if (id == R.id.pin_code_button_8) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_8)
        } else if (id == R.id.pin_code_button_9) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_9)
        } else if (id == R.id.pin_code_button_clear) {
            mKeypadButtonClickedListener!!.onKeypadClick(KeypadButtonEnum.BUTTON_CLEAR)
        }
    }

    fun setKeypadButtonClickedListener(keypadButtonClickedListener: KeypadButtonClickedListener?) {
        mKeypadButtonClickedListener = keypadButtonClickedListener
        for (button in mButtons!!) {
            button.setOnRippleAnimationEndListener(mKeypadButtonClickedListener)
        }
    }

    init {
        initializeView(attrs, defStyleAttr)
    }
}