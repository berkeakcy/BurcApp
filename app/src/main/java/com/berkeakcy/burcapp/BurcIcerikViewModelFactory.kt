package com.berkeakcy.burcapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BurcIcerikViewModelFactory(
    private val toolbarTitle:String,
    private val burc:Burclar,
    private val repository: BurclarRepository
):ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BurcIcerikViewModel::class.java!!)) {
            BurcIcerikViewModel(toolbarTitle,burc,this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}