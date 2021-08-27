package com.utico.fooddelivery.model

data class NotificationList(val data:List<UserNotification>)
data class UserNotification(val id:Int?,val email:String?,val avatar:String?)
