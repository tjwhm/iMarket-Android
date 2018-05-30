package com.twtstudio.tjwhm.imarket.export

import com.twtstudio.tjwhm.imarket.BaseBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExportApi {
    @GET("record/export")
    fun exportRecords(@Query("type") type: String,
                      @Query("from") from: String,
                      @Query("to") to: String): Call<BaseBean<Boolean>>
}