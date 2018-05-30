package com.twtstudio.tjwhm.imarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.twtstudio.tjwhm.imarket.admin.AdminActivity
import com.twtstudio.tjwhm.imarket.customer.CustomerActivity
import com.twtstudio.tjwhm.imarket.record.RecordActivity


class IdentityActivity : AppCompatActivity() {

    lateinit var btnCustomer: Button
    lateinit var btnAdmin: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity)
        supportActionBar?.hide()
        btnCustomer = findViewById(R.id.btn_customer)
        btnAdmin = findViewById(R.id.btn_admin)
        if (!intent.getBooleanExtra("isAdmin", false)) {
            btnCustomer.setOnClickListener({ startActivity(Intent().apply { setClass(this@IdentityActivity, CustomerActivity::class.java) }) })
            btnAdmin.setOnClickListener({
                val intent = Intent(this@IdentityActivity, IdentityActivity::class.java)
                intent.putExtra("isAdmin", true)
                startActivity(intent)


            })
        } else {
            btnCustomer.text = "Products"
            btnCustomer.setOnClickListener {
                val intent = Intent(this@IdentityActivity, AdminActivity::class.java)
                startActivity(intent)
            }
            btnAdmin.apply {
                text = "Records"
                setOnClickListener({
                    val intent = Intent(this@IdentityActivity, RecordActivity::class.java)
                    startActivity(intent)
                })
            }

        }
    }
}
