package com.example.theapache64.da_vinci.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.theapache64.da_vinci.api.APIInterface;
import com.example.theapache64.da_vinci.api.RetrofitManager;
import com.example.theapache64.da_vinci.api.responses.BaseAPIResponse;
import com.example.theapache64.da_vinci.utils.SingletonToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseNetworkActivity<R> extends BaseProgressManActivity implements BaseNetworkView<R> {

    private static final String TAG = BaseNetworkActivity.class.getSimpleName();
    private Call<BaseAPIResponse<R>> call;

    protected void load() {
        load("");
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (!isAutoLoadDisabled()) {
            load();
        }
    }

    private boolean isAutoLoadDisabled() {
        return false;
    }

    protected void load(String message) {
        showLoading(message);
        final APIInterface apiInterface = RetrofitManager.getInstance().getApiInterface();
        call = getAPICall(apiInterface);
        call.enqueue(new Callback<BaseAPIResponse<R>>() {
            @Override
            public void onResponse(Call<BaseAPIResponse<R>> call, Response<BaseAPIResponse<R>> response) {
                if (!call.isCanceled()) {
                    BaseAPIResponse<R> resp = response.body();
                    if (resp != null) {
                        if (resp.isError()) {
                            onLoadFailed(resp.getMessage());
                        } else {
                            onLoaded(resp);
                        }
                    } else {
                        onLoadFailed("Server error");
                    }
                } else {
                    Log.d(TAG, "Cancelled network call succeeded");
                }
            }

            @Override
            public void onFailure(Call<BaseAPIResponse<R>> call, Throwable t) {
                if (!call.isCanceled()) {
                    onNetworkError(t.getLocalizedMessage());
                } else {
                    Log.d(TAG, "Cancelled network call failed");
                }
            }
        });
    }

    protected abstract Call<BaseAPIResponse<R>> getAPICall(APIInterface apiInterface);

    protected abstract void onResponseLoaded(R response);

    @Override
    public void onLoaded(BaseAPIResponse<R> response) {
        hideLoading();
        onResponseLoaded(response.getData());
    }


    @Override
    public void onLoadFailed(String reason) {
        hideLoading();
        SingletonToast.makeText(this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
    }

    @Override
    public void onNetworkError(String reason) {
        onLoadFailed(String.format("Network error : %s", reason));
    }
}
