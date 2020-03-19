package com.cafrecode.clamnumpad.interfaces

import com.cafrecode.clamnumpad.enums.KeypadButtonEnum

/**
 * Created by frederick on 12/29/16.
 */
interface KeypadButtonClickedListener {
    /**
     * Receive the click of a button, just after a [android.view.View.OnClickListener] has fired.
     * Called before [.onRippleAnimationEnd].
     * @param keyboardButtonEnum The organized enum of the clicked button
     */
    fun onKeypadClick(keyboardButtonEnum: KeypadButtonEnum?)

    /**
     * Receive the end of a [com.andexert.library.RippleView] animation using a
     * [com.andexert.library.RippleAnimationListener] to determine the end.
     * Called after [.onKeyboardClick].
     */
    fun onRippleAnimationEnd()
}