package com.utico.fooddelivery.model

data class MenuResponse(
    val code: Int,
    val `data`: List<DataX>,
    val message: String,
    val status: Boolean
)

data class DataX(
    val __v: Int,
    val _id: String,
    val categoryname: String,
    val desc: String,
    val foodname: String,
    val foodquantity: Int,
    val id: String,
    val price: Int,
    val subcategoryname: String,
    val vegnonveg: Boolean
)