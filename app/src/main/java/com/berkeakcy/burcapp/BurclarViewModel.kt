package com.berkeakcy.burcapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BurclarViewModel(val repository: BurclarRepository):ViewModel() {

    private val _burcList = MutableLiveData<UIState<ArrayList<Burclar>>>()
    val burcList : LiveData<UIState<ArrayList<Burclar>>> get() = _burcList

    init {
        getBurclar()
    }

    fun getBurclar(){
        _burcList.value = UIState.Loading
        repository.getBurclar { _burcList.value = it }
    }
}