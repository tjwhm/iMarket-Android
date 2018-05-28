package com.twtstudio.tjwhm.imarket

data class BaseBean<T>(var error_code: Int,
                       var message: String,
                       var data: T)

interface DataClass
interface BaseFragment
data class ClothesBean(var sid: Int,
                       var name: String,
                       var brand: String,
                       var color: String,
                       var size: String,
                       var suitable_crowd: String,
                       var price: Double,
                       var in_stock: Int,
                       var on_sale: Boolean) : DataClass

data class FoodBean(var sid: Int,
                    var name: String,
                    var brand: String,
                    var shelf_life: String,
                    var origin: String,
                    var price: Double,
                    var in_stock: Int,
                    var price1: Double,
                    var on_sale: Boolean
) : DataClass

data class RecordBean(var time: String,
                      var sid: Int,
                      var num: Int,
                      var price: Double,
                      var value: Double,
                      var type: String
)
