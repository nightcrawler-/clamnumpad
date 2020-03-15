package com.cafrecode.clamnumpad.interfaces;

import com.cafrecode.clamnumpad.enums.KeypadButtonEnum;

/**
 * Created by frederick on 12/29/16.
 */
public interface KeypadButtonClickedListener {


    /**
     * Receive the click of a button, just after a {@link android.view.View.OnClickListener} has fired.
     * Called before {@link #onRippleAnimationEnd()}.
     * @param keyboardButtonEnum The organized enum of the clicked button
     */
    void onKeypadClick(KeypadButtonEnum keyboardButtonEnum);

    /**
     * Receive the end of a {@link com.andexert.library.RippleView} animation using a
     * {@link com.andexert.library.RippleAnimationListener} to determine the end.
     * Called after {@link #onKeyboardClick(com.github.orangegangsters.lollipin.lib.enums.KeyboardButtonEnum)}.
     */
    void onRippleAnimationEnd();
}
