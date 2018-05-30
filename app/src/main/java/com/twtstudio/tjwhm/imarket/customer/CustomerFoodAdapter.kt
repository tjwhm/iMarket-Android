package com.twtstudio.tjwhm.imarket.customer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.FoodBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.detail.DetailActivity
import com.twtstudio.tjwhm.imarket.ext.random

class CustomerFoodAdapter(var productsData: BaseBean<List<FoodBean>>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.item_customer_product, parent, false))
    }

    override fun getItemCount(): Int {
        return productsData.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = productsData.data[position]
        if (holder is FoodViewHolder) {
            holder.tvTitle.text = data.name
            holder.tvPrice.text = "$" + data.price.toString()
            holder.tvStock.text = data.in_stock.toString() + " in store"
            val params = holder.cvClothes.layoutParams
            params.height = random(230, 270) * 3
            holder.cvClothes.layoutParams = params
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("sid", data.sid.toString())
            intent.putExtra("isAdmin", false)
            holder.itemView.setOnClickListener {
                context?.startActivity(intent)
            }
        }
    }


    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
        var tvTitle: TextView = itemView.findViewById(R.id.tv_clothes_title)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_clothes_price)
        var tvStock: TextView = itemView.findViewById(R.id.tv_clothes_stock)
        var cvClothes: CardView = itemView.findViewById(R.id.cv_clothes)
        var ivBuy: ImageView = itemView.findViewById(R.id.iv_clothes_buy)
    }

}