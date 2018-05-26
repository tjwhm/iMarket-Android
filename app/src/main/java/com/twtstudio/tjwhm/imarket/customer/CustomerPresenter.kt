package com.twtstudio.tjwhm.imarket.customer

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomerPresenter(val customerFragment: CustomerFragment) {
    fun getClothesList() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request = retrofit.create(CustomerApi::class.java)
        val call = request.getClothesList()
        call.enqueue(object : Callback<BaseBean<List<ClothesBean>>> {
            override fun onFailure(call: Call<BaseBean<List<ClothesBean>>>?, t: Throwable?) {
                Toast.makeText(customerFragment.context, "zzz", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<List<ClothesBean>>>?, response: Response<BaseBean<List<ClothesBean>>>?) {
                Toast.makeText(customerFragment.context, "666", Toast.LENGTH_SHORT).show()
                if (response?.body() != null) {
                    customerFragment.setClothesData(response.body()!!)
                }
            }
        })
    }
}
