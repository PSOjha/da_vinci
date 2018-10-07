package com.theah64.da_vinci.ui.activities.base;


import com.theah64.da_vinci.utils.ProgressMan;

public class BaseProgressManActivity extends BaseAppCompatActivity {


    private ProgressMan progressMan;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        progressMan = new ProgressMan(this);
        progressMan.inflate();

    }

    protected void showLoading(final String message) {
        progressMan.showLoading(message);
    }

    protected void hideLoading() {
        progressMan.hideLoading();
    }
}
