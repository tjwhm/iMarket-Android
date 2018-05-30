package com.twtstudio.tjwhm.imarket.export

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.twtstudio.tjwhm.imarket.R
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import es.dmoral.toasty.Toasty
import org.angmarch.views.NiceSpinner
import java.util.*

class ExportActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    lateinit var toolbar: Toolbar
    lateinit var nsType: NiceSpinner
    lateinit var llDateFrom: LinearLayout
    lateinit var tvDateFrom: TextView
    lateinit var llDateTo: LinearLayout
    lateinit var tvDateTo: TextView
    lateinit var btnExport: Button
    private var dateTempString = ""
    private var target = ""
    private var exportPresenter = ExportPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_export)

        initToolbar()
        nsType = findViewById(R.id.ns_export_type)
        llDateFrom = findViewById(R.id.ll_export_date_from)
        llDateTo = findViewById(R.id.ll_export_date_to)
        tvDateFrom = findViewById(R.id.tv_export_date_from)
        tvDateTo = findViewById(R.id.tv_export_date_to)
        btnExport = findViewById(R.id.btn_export_export)

        nsType.attachDataSource(mutableListOf("all", "sell", "purchase"))
        llDateFrom.setOnClickListener { launchDatePicker("from") }
        llDateTo.setOnClickListener { launchDatePicker("to") }

        btnExport.setOnClickListener {
            if (tvDateFrom.text == null || tvDateFrom.text.isEmpty() ||
                    tvDateTo.text == null || tvDateTo.text.isEmpty()) {
                Toasty.error(this@ExportActivity, "fill all!", Toast.LENGTH_SHORT).show()
            } else {
                exportPresenter.exportRecords(nsType.text.toString(), tvDateFrom.text.toString(), tvDateTo.text.toString())
            }
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Export Records"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        toolbar.elevation = 8.0F
    }

    fun onSuccess() {
        Toasty.success(this@ExportActivity, "success!", Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val text = "$dateTempString$hourOfDay:$minute:00"
        when (target) {
            "from" -> tvDateFrom.text = text
            "to" -> tvDateTo.text = text
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateTempString = "$year-${monthOfYear + 1}-$dayOfMonth "
        val now = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog.newInstance(this@ExportActivity,
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
        this.target = target
        val now = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(this@ExportActivity, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
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
