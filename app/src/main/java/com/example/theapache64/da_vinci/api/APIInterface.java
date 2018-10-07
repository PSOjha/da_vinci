package com.example.theapache64.da_vinci.api;

import com.example.theapache64.da_vinci.api.responses.BaseAPIResponse;
import com.example.theapache64.da_vinci.api.responses.GetShapesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    /**
     * @return GetShapesResponse
     */

    @GET("get_shapes")
    Call<BaseAPIResponse<GetShapesResponse>> getShapes();
}
