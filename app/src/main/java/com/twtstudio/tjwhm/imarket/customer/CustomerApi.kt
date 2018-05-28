package com.twtstudio.tjwhm.imarket.customer

import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.FoodBean
import retrofit2.Call
import retrofit2.http.GET

//const val IP = "192.168.1.104" // TP-LINK_4AC75E
//const val IP = "192.168.1.61" // TWT-Studio-5G
const val IP = "172.23.63.125" // tjuwlan
const val BASE_URL = "http://$IP:8080/api/"

interface CustomerApi {
    @GET("goods/clothes/list")
    fun getClothesList(): Call<BaseBean<List<ClothesBean>>>

    @GET("goods/food/list")
    fun getFoodList(): Call<BaseBean<List<FoodBean>>>
}