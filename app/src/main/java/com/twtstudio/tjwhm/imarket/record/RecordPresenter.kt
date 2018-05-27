package com.twtstudio.tjwhm.imarket.record

import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.BuildConfig
import com.twtstudio.tjwhm.imarket.RecordBean
import com.twtstudio.tjwhm.imarket.customer.BASE_URL
import es.dmoral.toasty.Toasty
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecordPresenter(val recordActivity: RecordActivity) {

    fun getRecordsList() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val request = retrofit.create(RecordApi::class.java)
        val call = request.getRecordsList()
        call.enqueue(object : Callback<BaseBean<List<RecordBean>>> {
            override fun onFailure(call: Call<BaseBean<List<RecordBean>>>?, t: Throwable?) {
                Toasty.error(recordActivity, "internet error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<List<RecordBean>>>?, response: Response<BaseBean<List<RecordBean>>>?) {
                if (response?.body() != null) {
                    recordActivity.setRecordsData(response.body()!!)
                }
            }
        })
    }

    fun getEligibleRecords(map: Map<String, String>) {


        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY//这里可以选择拦截级别

            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor)
        }

        val client = builder.build()


        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
        val request = retrofit.create(RecordApi::class.java)
        val call = request.getEligibleRecords(map)
        call.enqueue(object : Callback<BaseBean<List<RecordBean>>> {
            override fun onFailure(call: Call<BaseBean<List<RecordBean>>>?, t: Throwable?) {
                Toasty.error(recordActivity, "internet error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseBean<List<RecordBean>>>?, response: Response<BaseBean<List<RecordBean>>>?) {
                if (response?.body() != null) {
                    recordActivity.setRecordsData(response.body()!!)
                } else {
                    Toasty.error(recordActivity, "no such records!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
