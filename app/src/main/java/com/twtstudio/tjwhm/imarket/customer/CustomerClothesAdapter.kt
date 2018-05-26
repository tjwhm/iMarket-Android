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
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.detail.DetailActivity
import com.twtstudio.tjwhm.imarket.ext.random

class CustomerClothesAdapter(var productsData: BaseBean<List<ClothesBean>>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ClothesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_clothes, parent, false))
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ClothesViewHolder) {
            holder.tvTitle.text = productsData.data[position].name
            holder.tvPrice.text = "$" + productsData.data[position].price.toString()
            holder.tvStock.text = productsData.data[position].in_stock.toString() + " in store"
            val params = holder.cvClothes.layoutParams
            params.height = random(230, 270) * 3
            holder.cvClothes.layoutParams = params
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("sid", productsData.data[position].sid.toString())
            holder.itemView.setOnClickListener { context?.startActivity(intent) }
        }
    }


    override fun getItemCount(): Int {
        return productsData.data.size
    }

    inner class ClothesViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var view = item
        var tvTitle: TextView = item.findViewById(R.id.tv_clothes_title)
        var tvPrice: TextView = item.findViewById(R.id.tv_clothes_price)
        var tvStock: TextView = item.findViewById(R.id.tv_clothes_stock)
        var cvClothes: CardView = item.findViewById(R.id.cv_clothes)
        var ivBuy: ImageView = item.findViewById(R.id.iv_clothes_buy)
    }

}