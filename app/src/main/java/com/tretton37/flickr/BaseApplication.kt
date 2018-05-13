package com.tretton37.flickr

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BaseApplication : Application() {

    val gson by lazy {
        GsonBuilder().create()
    }

    private val okHttpClient by lazy {
        createOkHttpClient()
    }

    lateinit var restApi: RestApi
    override fun onCreate() {
        super.onCreate()

        val retrofitBuilder = provideRetrofitBuilder(okHttpClient, gson)
        restApi = createRestAdapter(retrofitBuilder)
    }

    private fun createRestAdapter(retrofitBuilder: Retrofit.Builder): RestApi {
        return retrofitBuilder.baseUrl("https://api.flickr.com/services/")
                .build()
                .create<RestApi>(RestApi::class.java)
    }


    private fun createOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("retrofit", message)
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }


    private fun provideRetrofitBuilder(okHttpClient: OkHttpClient,
                                       gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
    }

    companion object {
        const val Key: String = "8d1fb3b3614a0f4873db568a9c3a2106"
    }
}