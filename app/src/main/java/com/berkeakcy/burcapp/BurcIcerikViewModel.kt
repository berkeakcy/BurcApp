package com.berkeakcy.burcapp

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.N)
class BurcIcerikViewModel(val toolbarTitle:String, val burc: Burclar,val repository: BurclarRepository):ViewModel() {

    private val _icerik = MutableLiveData<String>()
    val icerik : LiveData<String> get() = _icerik

    private val _icerikUpdate = MutableLiveData<UIState<String>>()
    val icerikUpdate : LiveData<UIState<String>> get() = _icerikUpdate

    private val _date = MutableLiveData<String>()
    val date : LiveData<String> get() = _date

    init {
        content()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun content(){
        val calendar = Calendar.getInstance()
        when(toolbarTitle){
            "Günlük Burçlar" -> {
                _icerik.value = burc.burc_gunluk
                _date.value = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
            }
            "Haftalık Burçlar" -> {
                _icerik.value = burc.burc_haftalik
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                val start = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
                val end = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
                _date.value = "$start - $end"
            }
            "Aylık Burçlar" -> {
                _icerik.value = burc.burc_aylik
                _date.value = monthConvert(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR).toString()
            }
            "Yıllık Burçlar" -> {
                _icerik.value = burc.burc_yillik
                _date.value = calendar.get(Calendar.YEAR).toString()
            }
            "Genel Özellikler" -> {
                _icerik.value = burc.burc_genelozellik.replace("_b","\n")
                _date.value = burc.burc_ad + " Burcunun Genel Özellikleri"
            }
        }
    }

    fun icerikUpdate(){
        when(toolbarTitle){
            "Günlük Burçlar" -> {
                repository.getBurcGunlukIcerik(burc){_icerikUpdate.value = it}
            }
            "Haftalık Burçlar" -> {
                repository.getBurcHaftalikIcerik(burc){_icerikUpdate.value = it}
            }
            "Aylık Burçlar" -> {
                repository.getBurcAylikIcerik(burc){_icerikUpdate.value = it}
            }
            "Yıllık Burçlar" -> {
                repository.getBurcYillikIcerik(burc){_icerikUpdate.value = it}
            }
            "Genel Özellikler" -> {
                repository.getBurcGenelozelliklerIcerik(burc){_icerikUpdate.value = it}
            }
        }
    }

    fun monthConvert(month:Int):String{
        return when(month){
            0 -> "Ocak"
            1 -> "Şubat"
            2 -> "Mart"
            3 -> "Nisan"
            4 -> "Mayıs"
            5 -> "Haziran"
            6 -> "Temmuz"
            7 -> "Ağustos"
            8 -> "Eylül"
            9 -> "Ekim"
            10 -> "Kasım"
            11 -> "Aralık"
            else -> "error convert"
        }
    }
}