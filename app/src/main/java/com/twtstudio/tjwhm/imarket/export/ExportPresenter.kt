package com.twtstudio.tjwhm.imarket.export

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.customer.BASE_URL
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExportPresenter(val exportActivity: ExportActivity) {

    fun exportRecords(type: String, from: String, to: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request = retrofit.create(ExportApi::class.java)
        val call = request.exportRecords(type, from, to)
        call.enqueue(object : Callback<BaseBean<Boolean>> {
            override fun onFailure(call: Call<BaseBean<Boolean>>?, t: Throwable?) {
                Toasty.error(exportActivity, "internet error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<Boolean>>?, response: Response<BaseBean<Boolean>>?) {
                if (response?.body()?.data == true) {
                    exportActivity.onSuccess()
                }
            }
        })
    }
}