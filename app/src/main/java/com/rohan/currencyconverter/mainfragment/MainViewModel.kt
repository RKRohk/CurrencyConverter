package com.rohan.currencyconverter.mainfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rohan.currencyconverter.network.Currency
import com.rohan.currencyconverter.network.CurrencyApi
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    var t1 = "Currency Converter "
    var curr1 = MutableLiveData<String>("INR")
    var curr2 = MutableLiveData<String>("INR")

    var result : LiveData<Currency> = liveData (Dispatchers.IO){
        val res = CurrencyApi.retrofitService.getCurrency()
        Log.i("MainViewModel",res.toString())
        emit(res)
    }




    var input = MutableLiveData<Double>(0.0)

    init {
        result.observeForever(Observer {
            Log.i("MainViewModel",it.toString())
        })
    }

    override fun onCleared() {
        super.onCleared()
    }

}