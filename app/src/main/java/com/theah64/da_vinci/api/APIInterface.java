package com.theah64.da_vinci.api;

import com.theah64.da_vinci.api.responses.BaseAPIResponse;
import com.theah64.da_vinci.api.responses.GetShapesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    /**
     * @return GetShapesResponse
     */

    @GET("get_shapes")
    Call<BaseAPIResponse<GetShapesResponse>> getShapes();
}
