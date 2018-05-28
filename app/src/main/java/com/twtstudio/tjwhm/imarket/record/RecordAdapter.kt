package com.twtstudio.tjwhm.imarket.record

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.RecordBean

class RecordAdapter(var recordsData: BaseBean<List<RecordBean>>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_record, parent, false))
    }

    override fun getItemCount(): Int {
        return recordsData.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecordViewHolder) {
            if (recordsData.data[position].type == "sell") {
                holder.cvRecord.setCardBackgroundColor(context?.resources!!.getColor(R.color.item_record_green))
                holder.tvType.text = "SOLD"
            } else {
                holder.cvRecord.setCardBackgroundColor(context?.resources!!.getColor(R.color.item_record_red))
                holder.tvType.text = "IN"
            }
            holder.apply {
                tvId.text = "id:${recordsData.data[position].sid}"
                tvTime.text = recordsData.data[position].time
                tvPriceTimesNum.text = "$${recordsData.data[position].price} * ${recordsData.data[position].num}"
                if (recordsData.data[position].value.toString().length > 7) {
                    tvValue.text = "$${recordsData.data[position].value.toString().substring(0, 5)}"
                } else {
                    tvValue.text = "$${recordsData.data[position].value}"
                }
            }
        }
    }

    inner class RecordViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var view = item
        var cvRecord: CardView = item.findViewById(R.id.cv_record)
        var tvId: TextView = item.findViewById(R.id.tv_record_id)
        var tvTime: TextView = item.findViewById(R.id.tv_record_time)
        var tvPriceTimesNum: TextView = item.findViewById(R.id.tv_record_price_times_num)
        var tvValue: TextView = item.findViewById(R.id.tv_record_value)
        var tvType: TextView = item.findViewById(R.id.tv_record_type)
        var llRecord: LinearLayout = item.findViewById(R.id.ll_record)
    }
}