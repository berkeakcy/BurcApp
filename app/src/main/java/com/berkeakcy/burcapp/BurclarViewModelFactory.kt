package com.berkeakcy.burcapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BurclarViewModelFactory (
    private val repository: BurclarRepositoryImp
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BurclarViewModel::class.java!!)) {
            BurclarViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}