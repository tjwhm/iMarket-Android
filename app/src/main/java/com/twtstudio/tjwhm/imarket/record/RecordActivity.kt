package com.twtstudio.tjwhm.imarket.record

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Window
import android.widget.Toast
import com.rengwuxian.materialedittext.MaterialEditText
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.R
import com.twtstudio.tjwhm.imarket.RecordBean

class RecordActivity : AppCompatActivity() {

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
        val dialog = layoutInflater.inflate(R.layout.dialog_filter, findViewById(R.id.ll_filter_dialog))
        val builder = AlertDialog.Builder(this)
        var metName: MaterialEditText = dialog.findViewById(R.id.met_name)
        metName.setOnClickListener { launchDialog() }
        builder.setView(dialog)
        builder.show()
    }

}
