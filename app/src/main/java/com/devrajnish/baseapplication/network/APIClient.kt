package com.devrajnish.baseapplication.network

import com.devrajnish.baseapplication.utils.AppConst.BASE_URL
import com.devrajnish.baseapplication.utils.AppConst.HEADER
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

private fun basicOkHttpClient() = OkHttpClient.Builder().apply {
    addInterceptor(Interceptor { chain ->
        val builder = chain.request().newBuilder()
            .addHeader(
                "example", HEADER
            )
        return@Interceptor chain.proceed((builder.build()))
    })
}.build()

fun createExampleService(): APIService {
    val baseUrl = BASE_URL
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).client(basicOkHttpClient())
        .baseUrl(baseUrl)
        .build()
    return retrofit.create(APIService::class.java)
}