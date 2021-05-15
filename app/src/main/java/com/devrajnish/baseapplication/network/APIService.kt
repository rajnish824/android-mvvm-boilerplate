package com.devrajnish.baseapplication.network

import com.devrajnish.baseapplication.model.ExampleResponse
import retrofit2.http.GET

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

interface APIService {
    @GET("/example/")
    suspend fun getExample(): ExampleResponse
}
