package com.twtstudio.tjwhm.imarket.add

import com.twtstudio.tjwhm.imarket.BaseBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AddApi {
    @POST("goods/clothes/new")
    @FormUrlEncoded
    fun addClothes(@Field("name") name: String,
                   @Field("brand") brand: String,
                   @Field("color") color: String,
                   @Field("size") size: String,
                   @Field("suitable_crowd") gender: String,
                   @Field("price") price: Double,
                   @Field("in_stock") num: Int,
                   @Field("price1") price1: Double): Call<BaseBean<Boolean>>

    @POST("goods/food/new")
    @FormUrlEncoded
    fun addFood(@Field("name") name: String,
                @Field("brand") brand: String,
                @Field("shelf_life") shelf_life: String,
                @Field("origin") origin: String,
                @Field("price") price: Double,
                @Field("in_stock") num: Int,
                @Field("price1") price1: Double): Call<BaseBean<Boolean>>
}