package com.twtstudio.tjwhm.imarket

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.twtstudio.tjwhm.imarket.customer.CustomerActivity
import android.widget.Button
import com.twtstudio.tjwhm.imarket.record.RecordActivity


class IdentityActivity : AppCompatActivity() {

    lateinit var btnCustomer: Button
    lateinit var btnAdmin: Button
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
