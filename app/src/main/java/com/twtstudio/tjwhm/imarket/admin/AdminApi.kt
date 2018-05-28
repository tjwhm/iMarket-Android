package com.twtstudio.tjwhm.imarket.admin

import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.FoodBean
import retrofit2.Call
import retrofit2.http.GET

interface AdminApi {
    @GET("goods/clothes/all")
    fun getAllClothes(): Call<BaseBean<List<ClothesBean>>>

    @GET("goods/food/all")
    fun getAllFood(): Call<BaseBean<List<FoodBean>>>
}