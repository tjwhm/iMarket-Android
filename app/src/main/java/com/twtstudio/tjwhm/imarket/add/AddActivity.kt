package com.twtstudio.tjwhm.imarket.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.widget.*
import com.rengwuxian.materialedittext.MaterialEditText
import com.twtstudio.tjwhm.imarket.R
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import es.dmoral.toasty.Toasty
import org.angmarch.views.NiceSpinner
import java.util.*

class AddActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    lateinit var toolbar: Toolbar
    lateinit var nsType: NiceSpinner

    val addPresenter = AddPresenter(this)
    private var dateTempString: String = ""
    // Clothes
    lateinit var llClothes: LinearLayout
    lateinit var metClothesName: MaterialEditText
    lateinit var metClothesBrand: MaterialEditText
    lateinit var metColor: MaterialEditText
    lateinit var nsSize: NiceSpinner
    lateinit var nsGender: NiceSpinner
    lateinit var metClothesPrice: MaterialEditText
    lateinit var metClothesPrice1: MaterialEditText
    lateinit var metClothesNum: MaterialEditText

    // Food
    lateinit var llFood: LinearLayout
    lateinit var metFoodName: MaterialEditText
    lateinit var metFoodBrand: MaterialEditText
    lateinit var llShelfLife: LinearLayout
    lateinit var tvShelfLife: TextView
    lateinit var metOrigin: MaterialEditText
    lateinit var metFoodPrice: MaterialEditText
    lateinit var metFoodPrice1: MaterialEditText
    lateinit var metFoodNum: MaterialEditText

    lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_add)
        initToolbar()
        initViews()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "New Product"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorAccent))
        toolbar.elevation = 8.0F
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        tvShelfLife.text = "$dateTempString$hourOfDay:$minute:00"
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateTempString = "$year-${monthOfYear + 1}-$dayOfMonth "
        val now = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog.newInstance(this@AddActivity,
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

    private fun initViews() {
        nsType = findViewById(R.id.ns_add_type)
        nsType.attachDataSource(mutableListOf("type", "clothes", "food"))

        llClothes = findViewById(R.id.ll_add_clothes)
        metClothesName = findViewById(R.id.met_add_clothes_name)
        metClothesBrand = findViewById(R.id.met_add_clothes_brand)
        metColor = findViewById(R.id.met_add_color)
        nsSize = findViewById(R.id.ns_add_size)
        nsGender = findViewById(R.id.ns_add_gender)
        metClothesPrice = findViewById(R.id.met_add_clothes_price)
        metClothesPrice1 = findViewById(R.id.met_add_clothes_price1)
        metClothesNum = findViewById(R.id.met_add_clothes_num)

        llFood = findViewById(R.id.ll_add_food)
        metFoodName = findViewById(R.id.met_add_food_name)
        metFoodBrand = findViewById(R.id.met_add_food_brand)
        llShelfLife = findViewById(R.id.ll_add_shelf_life)
        tvShelfLife = findViewById(R.id.tv_add_shelf_life)
        metOrigin = findViewById(R.id.met_add_origin)
        metFoodPrice = findViewById(R.id.met_add_food_price)
        metFoodPrice1 = findViewById(R.id.met_add_food_price1)
        metFoodNum = findViewById(R.id.met_add_food_num)

        btnConfirm = findViewById(R.id.btn_add_confirm)

        llClothes.visibility = View.GONE
        llFood.visibility = View.GONE
        btnConfirm.visibility = View.GONE

        nsType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        llClothes.visibility = View.GONE
                        llFood.visibility = View.GONE
                        btnConfirm.visibility = View.GONE
                    }
                    1 -> {
                        llClothes.visibility = View.VISIBLE
                        llFood.visibility = View.GONE
                        btnConfirm.visibility = View.VISIBLE
                    }
                    2 -> {
                        llClothes.visibility = View.GONE
                        llFood.visibility = View.VISIBLE
                        btnConfirm.visibility = View.VISIBLE
                    }
                }
            }
        })
        nsSize.attachDataSource(mutableListOf("Medium", "Small", "Large"))
        nsGender.attachDataSource(mutableListOf("male", "female"))

        llShelfLife.setOnClickListener { launchDatePicker() }

        btnConfirm.setOnClickListener {
            if (nsType.text.toString() == "clothes") {
                if (metClothesName.text == null || metClothesName.text.isEmpty() ||
                        metClothesBrand.text == null || metClothesBrand.text.isEmpty() ||
                        metColor.text == null || metColor.text.isEmpty() ||
                        metClothesPrice.text == null || metClothesPrice.text.isEmpty() ||
                        metClothesPrice1.text == null || metClothesPrice1.text.isEmpty()) {
                    Toasty.error(this@AddActivity, "fill all!", Toast.LENGTH_SHORT).show()
                } else {
                    val name = metClothesName.text.toString()
                    val brand = metClothesBrand.text.toString()
                    val color = metColor.text.toString()
                    val size = nsSize.text.toString()
                    val gender = nsGender.text.toString()
                    val price = metClothesPrice.text.toString().toDouble()
                    val num = metClothesNum.text.toString().toInt()
                    val price1 = metClothesPrice1.text.toString().toDouble()
                    addPresenter.addClothes(name, brand, color, size, gender, price, num, price1)
                }
            } else if (nsType.text.toString() == "food") {
                if (metFoodName.text == null || metFoodName.text.isEmpty() ||
                        metFoodBrand.text == null || metFoodBrand.text.isEmpty() ||
                        tvShelfLife.text == null || tvShelfLife.text.isEmpty() ||
                        metOrigin.text == null || metOrigin.text.isEmpty() ||
                        metFoodPrice.text == null || metFoodPrice.text.isEmpty() ||
                        metFoodPrice1.text == null || metFoodPrice1.text.isEmpty() ||
                        metFoodNum.text == null || metFoodNum.text.isEmpty()) {
                    Toasty.error(this@AddActivity, "fill all!", Toast.LENGTH_SHORT).show()
                } else {
                    val name = metFoodName.text.toString()
                    val brand = metFoodBrand.text.toString()
                    val shelf_life = tvShelfLife.text.toString()
                    val origin = metOrigin.text.toString()
                    val price = metFoodPrice.text.toString().toDouble()
                    val num = metFoodNum.text.toString().toInt()
                    val price1 = metFoodPrice1.text.toString().toDouble()
                    addPresenter.addFood(name, brand, shelf_life, origin, price, num, price1)
                }
            }
        }
    }

    private fun launchDatePicker() {
        val now = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(this@AddActivity, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show(fragmentManager, "date")
        datePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)
        datePickerDialog.setCancelColor(resources.getColor(R.color.colorAccent))
        datePickerDialog.setCancelText("Cancel")
        datePickerDialog.setOkColor(resources.getColor(R.color.colorAccent))
        datePickerDialog.setOkText("Ok")
        datePickerDialog.vibrate(false)
    }

    fun onSuccess() {
        Toasty.success(this@AddActivity, "success!", Toast.LENGTH_SHORT).show()
        finish()
    }
}

