package com.it.yousefl.libinstaclone

import com.it.yousefl.libinstaclone.apis.InstaCloneApi
import com.it.yousefl.libinstaclone.convertes.EnumConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object InstaClient {

    const val API_KEY = "fcba3b4d68422dc"//TODO: Ideally should  be in app not lib

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Client-ID $API_KEY")
                    .build()
                it.proceed(request)
            }.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .baseUrl("https://api.imgur.com/3/")
            .build()
    }
    val api: InstaCloneApi by lazy { retrofit.create(InstaCloneApi::class.java) }
}