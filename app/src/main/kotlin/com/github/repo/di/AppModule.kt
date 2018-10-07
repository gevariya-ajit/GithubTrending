package com.github.repo.di

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

object AppModule {

    fun getModule() = module(definition = {

        // Network dependencies
        single(definition = { createOkHttpClient(androidApplication()) })
        single(definition = { createRetrofit(get()) })

    })

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    private fun createOkHttpClient(context: Context): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheFile = File(context.cacheDir, "NetworkCache")
        val cache = Cache(cacheFile, (10 * 1000 * 1000).toLong()) // 10 MB

        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }
}

