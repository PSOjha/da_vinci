package com.theah64.da_vinci.ui.activities.base;


import com.theah64.da_vinci.api.responses.BaseAPIResponse;

public interface BaseNetworkView<R> {
    void onLoaded(BaseAPIResponse<R> response);

    void onLoadFailed(String reason);

    void onNetworkError(String reason);
}
