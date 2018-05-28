package com.twtstudio.tjwhm.imarket.ext

import com.twtstudio.tjwhm.imarket.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

fun random(min: Int, max: Int): Int {
    val rand = Random()
    return rand.nextInt((max - min) + 1) + min
}

fun getDebugClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        // Log信息拦截器
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY//这里可以选择拦截级别

        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor)
    }

    return builder.build()
}