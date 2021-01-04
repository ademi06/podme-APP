package uk.ac.tees.w9312536.bukolafatunde.ui.add;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import uk.ac.tees.w9312536.bukolafatunde.R;


/**
 * Creates a dialog which is the same as ListPreference where the user can choose a country.
 * Reference: @see "https://stackoverflow.com/questions/33976579/how-to-call-listpreference-dialog-only"
 */
public class CountryPreferenceDialog extends DialogFragment implements DialogInterface.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mValue;
    private SharedPreferences mPrefs;
    private int mPrefIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEntries = getResources().getStringArray(R.array.pref_country_options);
        mEntryValues = getResources().getStringArray(R.array.pref_country_values);
        mValue = mPrefs.getString(
                getString(R.string.pref_country_key),
                getString(R.string.pref_country_default));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.title_country_dialog));
        builder.setPositiveButton(null, null);
        mPrefIndex = getValueIndex();
        builder.setSingleChoiceItems(mEntries, mPrefIndex, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mPrefIndex != which) {
            mPrefIndex = which;
            mValue = mEntryValues[mPrefIndex].toString();
            mPrefs.edit().putString(getString(R.string.pref_country_key), mValue).apply();
        }
        dialog.dismiss();
    }

    private int getValueIndex() {
        return findIndexOfValue(mValue);
    }

    private int findIndexOfValue(String value) {
        int defaultValue = -1;
        if (value != null && mEntryValues != null) {
            for (int i = mEntryValues.length - 1; i >= 0; i--) {
                if (mEntryValues[i].equals(value)) {
                    return i;
                }
            }
        }
        return defaultValue;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_country_key))) {
            mPrefs.edit().putString(key, mValue).apply();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Register the OnSharedPreferenceChange listener
        mPrefs.registerOnSharedPreferenceChangeListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        // Unregister the OnSharedPreferenceChange listener
        mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}

