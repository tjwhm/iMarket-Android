package com.twtstudio.tjwhm.imarket.record

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.rengwuxian.materialedittext.MaterialEditText
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.RecordBean
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import org.angmarch.views.NiceSpinner
import java.util.*

class RecordActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private lateinit var tvDateFrom: TextView
    private lateinit var tvDateTo: TextView
    private lateinit var tvShelfLifeFrom: TextView
    private lateinit var tvShelfLifeTo: TextView
    private var dateTarget = "date target"
    private var dateTempString = ""

    companion object {
        val DATE_FROM = "date from"
        val DATE_TO = "date to"
        val SHELF_LIFE_FROM = "shelf life from"
        val SHELF_LIFE_TO = "shelf life to"
    }

    lateinit var toolbar: Toolbar
    lateinit var rvRecord: RecyclerView
    lateinit var adapter: RecordAdapter
    lateinit var fabRecord: FloatingActionButton
    private val presenter: RecordPresenter = RecordPresenter(this)
    var baseBean: BaseBean<List<RecordBean>> = BaseBean(-1, "", mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_record)
        initToolbar()
        rvRecord = findViewById(R.id.rv_record)
        fabRecord = findViewById(R.id.fab_record)
        val layoutManager = LinearLayoutManager(this)
        rvRecord.layoutManager = layoutManager
        adapter = RecordAdapter(baseBean, this)
        rvRecord.adapter = adapter
        presenter.getRecordsList()
        fabRecord.setOnClickListener { launchDialog() }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Records"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
    }

    fun setRecordsData(baseBean: BaseBean<List<RecordBean>>) {
        this.baseBean.error_code = baseBean.error_code
        this.baseBean.message = baseBean.message
        this.baseBean.data = baseBean.data
        adapter.notifyDataSetChanged()

    }

    private fun launchDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_filter, findViewById(R.id.ll_filter_dialog))
        val builder = AlertDialog.Builder(this)
        val dialog = builder.setView(dialogView).create()
        val metName: MaterialEditText = dialogView.findViewById(R.id.met_name)
        val metBrand: MaterialEditText = dialogView.findViewById(R.id.met_brand)
        val metPriceFrom: MaterialEditText = dialogView.findViewById(R.id.met_price_from)
        val metPriceTo: MaterialEditText = dialogView.findViewById(R.id.met_price_to)
        val llDateFrom: LinearLayout = dialogView.findViewById(R.id.ll_date_from)
        val llDateTo: LinearLayout = dialogView.findViewById(R.id.ll_date_to)
        tvDateFrom = dialogView.findViewById(R.id.tv_filter_date_from)
        tvDateTo = dialogView.findViewById(R.id.tv_filter_date_to)
        val nsType: NiceSpinner = dialogView.findViewById(R.id.ns_filter_type)
        val llClothes: LinearLayout = dialogView.findViewById(R.id.ll_filter_clothes)
        val llFood: LinearLayout = dialogView.findViewById(R.id.ll_filter_food)
        val metColor: MaterialEditText = dialogView.findViewById(R.id.met_filter_color)
        val nsSize: NiceSpinner = dialogView.findViewById(R.id.ns_filter_size)
        val nsGender: NiceSpinner = dialogView.findViewById(R.id.ns_filter_gender)
        val metOrigin: MaterialEditText = dialogView.findViewById(R.id.met_filter_origin)
        val llShelfLifeFrom: LinearLayout = dialogView.findViewById(R.id.ll_shelf_life_from)
        val llShelfLifeTo: LinearLayout = dialogView.findViewById(R.id.ll_shelf_life_to)
        tvShelfLifeFrom = dialogView.findViewById(R.id.tv_filter_shelf_life_from)
        tvShelfLifeTo = dialogView.findViewById(R.id.tv_filter_shelf_life_to)
        val btnCancel: Button = dialogView.findViewById(R.id.btn_filter_cancel)
        val btnOk: Button = dialogView.findViewById(R.id.btn_filter_ok)

        llClothes.visibility = View.GONE
        llFood.visibility = View.GONE
        val typeSpinnerDataList = mutableListOf("all", "clothes", "food")
        val sizeSpinnerDataList = mutableListOf("Medium", "Small", "Large")
        val genderSpinnerDataList = mutableListOf("male", "female")
        nsType.apply {
            attachDataSource(typeSpinnerDataList)
            setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            llClothes.visibility = View.GONE
                            llFood.visibility = View.GONE
                        }
                        1 -> {
                            llClothes.visibility = View.VISIBLE
                            llFood.visibility = View.GONE
                        }
                        2 -> {
                            llClothes.visibility = View.GONE
                            llFood.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
        nsSize.attachDataSource(sizeSpinnerDataList)
        nsGender.attachDataSource(genderSpinnerDataList)
        llDateFrom.setOnClickListener { launchDatePicker(DATE_FROM) }
        llDateTo.setOnClickListener { launchDatePicker(DATE_TO) }
        llShelfLifeFrom.setOnClickListener { launchDatePicker(SHELF_LIFE_FROM) }
        llShelfLifeTo.setOnClickListener { launchDatePicker(SHELF_LIFE_TO) }

        btnCancel.setOnClickListener { dialog.dismiss() }
        var map = mutableMapOf<String, String>()
        btnOk.setOnClickListener {
            map["after"] = tvDateFrom.text.toString()
            map["before"] = tvDateTo.text.toString()
            map["type"] = nsType.selectedIndex.toString()
            map["name"] = metName.text.toString()
            map["brand"] = metBrand.text.toString()
            map["color"] = metColor.text.toString()
            map["size"] = nsSize.text.toString()
            map["suitable_crowd"] = nsGender.text.toString()

            Log.d("zzzzz", "${map["type"]}  ${map["size"]}  ${map["size"]}")

            if (metPriceFrom.text != null && metPriceFrom.text.isNotEmpty()) {
                map["low"] = metPriceFrom.text.toString()
            } else {
                map["low"] = 0.toString()
            }
            if (metPriceTo.text != null && metPriceTo.text.isNotEmpty()) {
                map["high"] = metPriceTo.text.toString()
            } else {
                map["high"] = 999999.toString()
            }
            map["shelf_life_from"] = tvShelfLifeFrom.text.toString()
            map["shelf_life_to"] = tvShelfLifeTo.text.toString()
            map["origin"] = metOrigin.text.toString()
            presenter.getEligibleRecords(map)
            dialog.dismiss()
        }
//        builder.show()
        dialog.show()
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val text = "$dateTempString$hourOfDay:$minute:00"
        when (dateTarget) {
            DATE_FROM -> tvDateFrom.text = text
            DATE_TO -> tvDateTo.text = text
            SHELF_LIFE_FROM -> tvShelfLifeFrom.text = text
            SHELF_LIFE_TO -> tvShelfLifeTo.text = text
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateTempString = "$year-${monthOfYear + 1}-$dayOfMonth "
        val now = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog.newInstance(this@RecordActivity,
                now.get(Calendar.HOUR), now.get(Calendar.MINUTE), now.get(Calendar.SECOND), true
        )
        timePickerDialog.show(fragmentManager, "time")
        timePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)
        timePickerDialog.setCancelColor(resources.getColor(R.color.colorAccent))
        timePickerDialog.setCancelText("Cancel")
        timePickerDialog.setOkColor(resources.getColor(R.color.colorAccent))
        timePickerDialog.setOkText("Ok")
        timePickerDialog.vibrate(false)

    }

    private fun launchDatePicker(target: String) {
        dateTarget = target
        val now = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(this@RecordActivity, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show(fragmentManager, "date")
        datePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)
        datePickerDialog.setCancelColor(resources.getColor(R.color.colorAccent))
        datePickerDialog.setCancelText("Cancel")
        datePickerDialog.setOkColor(resources.getColor(R.color.colorAccent))
        datePickerDialog.setOkText("Ok")
        datePickerDialog.vibrate(false)

    }

}
