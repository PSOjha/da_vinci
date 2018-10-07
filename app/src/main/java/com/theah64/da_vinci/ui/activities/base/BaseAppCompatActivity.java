package com.theah64.da_vinci.ui.activities.base;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theah64.da_vinci.utils.SingletonToast;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * <b>Features</b> :
 * ButterKnife auto binder
 */
public class BaseAppCompatActivity
        extends AppCompatActivity {


    /**
     * To show toast with short length
     *
     * @param message Message to be displayed
     */
    protected void showToast(@StringRes int message) {
        this.showToast(getString(message));
    }

    /**
     * To show toast with short length
     *
     * @param message Message to be displayed
     */
    protected void showToast(final String message) {
        SingletonToast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * To get ViewGroup set using setContentView
     *
     * @return ViewGroup
     */
    public ViewGroup getContentView() {
        ViewGroup content = findViewById(android.R.id.content);
        return (ViewGroup) content.getChildAt(0);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    protected Serializable getSerializableExtra(String key, Serializable defaultSerializable) {
        final Serializable serializable = getIntent().getSerializableExtra(key);
        if (serializable == null) {
            return defaultSerializable;
        }
        return serializable;
    }

}
