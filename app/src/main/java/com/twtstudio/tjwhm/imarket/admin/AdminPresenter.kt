package com.twtstudio.tjwhm.imarket.admin

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.BaseFragment
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.FoodBean
import com.twtstudio.tjwhm.imarket.customer.BASE_URL
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminPresenter(val fragment: BaseFragment) {

    fun getClothesList() {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val request = retrofit.create(AdminApi::class.java)
        val call = request.getAllClothes()
        if (fragment is AdminClothesFragment)
            call.enqueue(object : Callback<BaseBean<List<ClothesBean>>> {
                override fun onFailure(call: Call<BaseBean<List<ClothesBean>>>?, t: Throwable?) {
                    Toasty.error(fragment.context!!, "internet error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<BaseBean<List<ClothesBean>>>?, response: Response<BaseBean<List<ClothesBean>>>?) {
                    if (response?.body() != null) {
                        fragment.setClothesData(response.body()!!)
                    }
                }
            })
    }

    fun getFoodList() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val request = retrofit.create(AdminApi::class.java)
        val call = request.getAllFood()
        if (fragment is AdminFoodFragment)
            call.enqueue(object : Callback<BaseBean<List<FoodBean>>> {
                override fun onFailure(call: Call<BaseBean<List<FoodBean>>>?, t: Throwable?) {
                    Toasty.error(fragment.context!!, "internet error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<BaseBean<List<FoodBean>>>?, response: Response<BaseBean<List<FoodBean>>>?) {
                    if (response?.body() != null) {
                        fragment.setFoodData(response.body()!!)
                    }
                }
            })
    }
}