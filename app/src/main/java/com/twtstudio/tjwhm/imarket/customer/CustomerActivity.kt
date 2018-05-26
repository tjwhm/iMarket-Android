package com.twtstudio.tjwhm.imarket.customer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Window
import com.twtstudio.tjwhm.imarket.R


class CustomerActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var tlCustomer: TabLayout
    lateinit var vpCustomer: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_customer)
        initToolbar()
        tlCustomer = findViewById(R.id.customer_tab)
        vpCustomer = findViewById(R.id.customer_pager)
        val pagerAdapter = CustomerPagerAdapter(supportFragmentManager)
        pagerAdapter.add(Pair(CustomerFragment.newInstance("clothes"),"Clothes"))
        pagerAdapter.add(Pair(CustomerFragment.newInstance("food"),"Food"))
        vpCustomer.adapter = pagerAdapter
        tlCustomer.setupWithViewPager(vpCustomer)
        tlCustomer.tabGravity = TabLayout.GRAVITY_FILL
//        var customerPagerAdapter = CustomerPagerAdapter(supportFragmentManager)
//                .apply { add() }

    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Available Products"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        toolbar.elevation = 0.0F
    }
}
