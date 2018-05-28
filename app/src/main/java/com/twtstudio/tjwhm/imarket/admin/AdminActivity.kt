package com.twtstudio.tjwhm.imarket.admin

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Window
import com.twtstudio.tjwhm.imarket.R

class AdminActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var tlAdmin: TabLayout
    lateinit var vpAdmin: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_admin)
        initToolbar()
        tlAdmin = findViewById(R.id.tl_admin)
        vpAdmin = findViewById(R.id.vp_admin)
        val pagerAdapter = AdminPagerAdapter(supportFragmentManager)

        pagerAdapter.add(Pair(AdminClothesFragment.newInstance("clothes"), "Clothes"))
        pagerAdapter.add(Pair(AdminFoodFragment.newInstance("food"), "Food"))
        vpAdmin.adapter = pagerAdapter
        tlAdmin.setupWithViewPager(vpAdmin)
        tlAdmin.tabGravity = TabLayout.GRAVITY_FILL
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "All Products"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        toolbar.elevation = 0.0F
    }
}
