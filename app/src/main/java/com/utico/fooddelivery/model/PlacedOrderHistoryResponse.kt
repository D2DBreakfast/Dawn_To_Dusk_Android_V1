package com.utico.fooddelivery.model

data class PlacedOrderHistoryResponse(
    val PlacedOrdersList: List<PlacedOrders>,
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val totalOrders: Int
)
data class PlacedOrders(
    val categoryType:String,
    val deliveryStatus: String,
    val landMark: String,
    val orderDate: String,
    val endDate:String,
    val orderDetails: List<OrderDetail>,
    val orderId: String,
    val orderStatus: String,
    val paymentMethod: String,
    val paymentPaid: String,
    val paymentStatus: String,
    val sectorId: String,
    val userId: String,
    val villa: String,
    val title:String

)
data class OrderDetail(
    val cartId: String,
    val itemFoodType: Boolean,
    val itemId: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemBaseQuantity: String,
    val itemSubCategoryName: String
)