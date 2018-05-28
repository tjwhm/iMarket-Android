package com.twtstudio.tjwhm.imarket.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.FoodBean
import com.twtstudio.tjwhm.imarket.R
import es.dmoral.toasty.Toasty

class DetailActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var toolbar: Toolbar
    lateinit var tvBrand: TextView
    lateinit var tvPrice: TextView
    lateinit var tvName: TextView
    lateinit var tvSize: TextView
    lateinit var tvColor: TextView
    lateinit var tvGender: TextView
    lateinit var tvStock: TextView
    lateinit var ivDec: ImageView
    lateinit var ivInc: ImageView
    lateinit var tvNum: TextView
    lateinit var btnBuy: Button
    lateinit var sid: String
    private val detailPresenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_detail)
        initToolbar()
        tvBrand = findViewById(R.id.tv_detail_brand)
        tvPrice = findViewById(R.id.tv_detail_name)
        tvName = findViewById(R.id.tv_detail_name)
        tvSize = findViewById(R.id.tv_detail_size)
        tvColor = findViewById(R.id.tv_detail_color)
        tvGender = findViewById(R.id.tv_detail_gender)
        tvStock = findViewById(R.id.tv_detail_stock)
        ivDec = findViewById(R.id.iv_detail_dec)
        ivInc = findViewById(R.id.iv_detail_inc)
        tvNum = findViewById(R.id.tv_detail_num)
        btnBuy = findViewById(R.id.btn_detail_buy)


        sid = intent.getStringExtra("sid")
        if (sid.toInt() < 1000) {
            detailPresenter.getClothesDetailInfo(sid)
        } else {
            detailPresenter.getFoodDetailInfo(sid)
        }

        ivInc.setOnClickListener(this)
        ivDec.setOnClickListener(this)
        btnBuy.setOnClickListener(this)
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Product Detail"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        toolbar.elevation = 0.0F
    }

    override fun onClick(v: View?) {
        if (v == ivDec) {
            if (tvNum.text.toString().toInt() == 1) {
                Toasty.error(this@DetailActivity, "can not less than 1", Toast.LENGTH_SHORT).show()
            } else {
                tvNum.text = (tvNum.text.toString().toInt() - 1).toString()
            }
        } else if (v == ivInc) {
            tvNum.text = (tvNum.text.toString().toInt() + 1).toString()
        } else if (v == btnBuy) {
            detailPresenter.buyClothes(sid, tvNum.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    fun setClothesDetailInfo(baseBean: BaseBean<ClothesBean>) {
        tvBrand.text = baseBean.data.brand
        tvPrice.text = "$${baseBean.data.price}"
        tvName.text = baseBean.data.name
        tvSize.text = "Size: ${baseBean.data.size}"
        tvColor.text = "Color: ${baseBean.data.color}"
        tvGender.text = "Gender: ${baseBean.data.suitable_crowd}"
        tvStock.text = "${baseBean.data.in_stock} in store"
    }

    @SuppressLint("SetTextI18n")
    fun setFoodDetailInfo(baseBean: BaseBean<FoodBean>) {
        tvBrand.text = baseBean.data.brand
        tvPrice.text = "$${baseBean.data.price}"
        tvName.text = baseBean.data.name
        tvSize.text = "Origin: ${baseBean.data.origin}"
        tvColor.text = "Shelf Life: ${baseBean.data.shelf_life}"
        tvGender.text = ""
        tvStock.text = "${baseBean.data.in_stock} in store"
    }
}
