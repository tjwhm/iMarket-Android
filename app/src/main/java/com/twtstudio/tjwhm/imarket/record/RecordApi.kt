package com.twtstudio.tjwhm.imarket.record

import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.RecordBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecordApi {
    @GET("record/list")
    fun getRecordsList(): Call<BaseBean<List<RecordBean>>>

    @GET("record/")
    fun getEligibleRecords(@QueryMap map: Map<String, String>): Call<BaseBean<List<RecordBean>>>
}