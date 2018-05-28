package com.twtstudio.tjwhm.imarket.add

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.customer.BASE_URL
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddPresenter(val activity: AddActivity) {

    fun addClothes(name: String, brand: String, color: String, size: String,
                   gender: String, price: Double, num: Int, price1: Double) {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val request = retrofit.create(AddApi::class.java)

        val call = request.addClothes(name, brand, color, size, gender, price, num, price1)

        call.enqueue(object : Callback<BaseBean<Boolean>> {
            override fun onFailure(call: Call<BaseBean<Boolean>>?, t: Throwable?) {
                Toasty.error(activity, "internet error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<Boolean>>?, response: Response<BaseBean<Boolean>>?) {
                activity.onSuccess()
            }
        })
    }

    fun addFood(name: String, brand: String, shelf_life: String, origin: String,
                price: Double, num: Int, price1: Double) {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val request = retrofit.create(AddApi::class.java)

        val call = request.addFood(name, brand, shelf_life, origin, price, num, price1)

        call.enqueue(object : Callback<BaseBean<Boolean>> {
            override fun onFailure(call: Call<BaseBean<Boolean>>?, t: Throwable?) {
                Toasty.error(activity, "internet error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<Boolean>>?, response: Response<BaseBean<Boolean>>?) {
                activity.onSuccess()
            }
        })
    }
}