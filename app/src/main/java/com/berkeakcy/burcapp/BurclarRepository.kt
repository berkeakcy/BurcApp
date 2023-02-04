package com.berkeakcy.burcapp


interface BurclarRepository {
    fun getBurclar(result: (UIState<ArrayList<Burclar>>) -> Unit)
    fun getBurcGunlukIcerik(burc:Burclar,result : (UIState<String>) -> Unit)
    fun getBurcHaftalikIcerik(burc:Burclar,result : (UIState<String>) -> Unit)
    fun getBurcAylikIcerik(burc:Burclar,result : (UIState<String>) -> Unit)
    fun getBurcYillikIcerik(burc:Burclar,result : (UIState<String>) -> Unit)
    fun getBurcGenelozelliklerIcerik(burc:Burclar,result : (UIState<String>) -> Unit)
}