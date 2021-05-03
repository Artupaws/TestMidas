package com.putra.test.network

import com.putra.test.model.ResponseGetData
import retrofit2.http.GET

interface Endpoint {

    //Get Photo
    @GET("photos")
    fun getDataApi():retrofit2.Call<List<ResponseGetData>>
}