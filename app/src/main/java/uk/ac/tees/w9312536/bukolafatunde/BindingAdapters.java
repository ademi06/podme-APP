package uk.ac.tees.w9312536.bukolafatunde;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * The binding adapter that makes the framework calls required to set the corresponding properties.
 * Reference: @see "https://github.com/googlesamples/android-architecture-components/tree/master/BasicSample"
 */
public class BindingAdapters {

    /**
     * This method will make the View visible when the value of the boolean show is true and hide
     * the View when the value of the boolean show is false.
     */
    @BindingAdapter("visibleGone")
    public static void showHide(View view, Boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}


