package com.twtstudio.tjwhm.imarket.detail

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.customer.BASE_URL
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPresenter(val detailActivity: DetailActivity) {
    fun getClothesDetailInfo(sid: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request = retrofit.create(DetailApi::class.java)
        val call = request.getClothesDetailInfo(sid)
        call.enqueue(object : Callback<BaseBean<ClothesBean>> {
            override fun onResponse(call: Call<BaseBean<ClothesBean>>?, response: Response<BaseBean<ClothesBean>>?) {
                if (response?.body() != null) {
                    detailActivity.setDetailInfo(response.body()!!)
                }
            }

            override fun onFailure(call: Call<BaseBean<ClothesBean>>?, t: Throwable?) {
                Toast.makeText(detailActivity, "Internet Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun buyClothes(sid: String, change: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request = retrofit.create(DetailApi::class.java)
        val call = request.buyClothes(sid, "-$change")
        call.enqueue(object : Callback<BaseBean<Boolean>> {
            override fun onFailure(call: Call<BaseBean<Boolean>>?, t: Throwable?) {
                Toasty.error(detailActivity, "error", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<BaseBean<Boolean>>?, response: Response<BaseBean<Boolean>>?) {
                if (response?.body()?.data == true) {
                    Toasty.success(detailActivity, "success!", Toast.LENGTH_SHORT).show()
                    getClothesDetailInfo(sid)
                }
            }
        })
    }
}