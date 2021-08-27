package com.utico.fooddelivery.util

import android.content.Context
import android.widget.Toast
import java.util.regex.Pattern

fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
