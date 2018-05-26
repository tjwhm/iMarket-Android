package com.twtstudio.tjwhm.imarket.record

import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.RecordBean
import retrofit2.Call
import retrofit2.http.GET

interface RecordApi {
    @GET("record/list")
    fun getRecordsList(): Call<BaseBean<List<RecordBean>>>
}