package com.twtstudio.tjwhm.imarket.detail

import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import retrofit2.Call
import retrofit2.http.*

interface DetailApi {
    @GET("goods/clothes/{sid}")
    fun getClothesDetailInfo(@Path("sid") sid: String): Call<BaseBean<ClothesBean>>

    @PUT("goods/clothes/{sid}")
    @FormUrlEncoded
    fun buyClothes(@Path("sid") sid: String, @Field("change") change: String): Call<BaseBean<Boolean>>
}