package com.twtstudio.tjwhm.imarket.admin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.detail.DetailActivity
import com.twtstudio.tjwhm.imarket.ext.random

class AdminClothesAdapter(var productsData: BaseBean<List<ClothesBean>>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdminClothesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_admin_product, parent, false))
    }

    override fun getItemCount(): Int {
        return productsData.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = productsData.data[position]
        if (holder is AdminClothesViewHolder) {
            holder.tvTitle.text = data.name
            holder.tvPrice.text = "$${data.price}"
            holder.tvStock.text = "${data.in_stock} in store"
            if (data.in_stock < 6) {
                holder.cvClothes.setCardBackgroundColor(context?.resources!!.getColor(R.color.no_sale_yellow))
                println("zzzzzz$position")
            } else {
                holder.cvClothes.setCardBackgroundColor(context?.resources!!.getColor(R.color.mdtp_white))
            }
            if (data.on_sale) {
                holder.tvStatus.text = "on sale"
            } else {
                holder.tvStatus.text = "not on sale"
                holder.cvClothes.setCardBackgroundColor(context?.resources!!.getColor(R.color.item_record_red))
            }
            val params = holder.cvClothes.layoutParams
            params.height = random(230, 270) * 3
            holder.cvClothes.layoutParams = params
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("sid", data.sid.toString())
            intent.putExtra("isAdmin", true)
            holder.itemView.setOnClickListener { context?.startActivity(intent) }
        }
    }

    inner class AdminClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
        var tvTitle: TextView = itemView.findViewById(R.id.tv_admin_title)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_admin_price)
        var tvStock: TextView = itemView.findViewById(R.id.tv_admin_stock)
        var tvStatus: TextView = itemView.findViewById(R.id.tv_admin_status)
        var cvClothes: CardView = itemView.findViewById(R.id.cv_admin_clothes)
    }
}