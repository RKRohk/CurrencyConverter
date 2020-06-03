package com.rohan.currencyconverter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("heading")
fun TextView.addHeading(heading:String){
    text = heading
}