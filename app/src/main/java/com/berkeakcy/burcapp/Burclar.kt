package com.berkeakcy.burcapp

import com.google.firebase.firestore.DocumentSnapshot


data class Burclar(
    var burc_ad : String,
    var burc_img : String,
    var burc_tarih : String,
    var burc_gunluk : String,
    var burc_haftalik : String,
    var burc_aylik : String,
    var burc_yillik : String,
    var burc_genelozellik : String
):java.io.Serializable{
    companion object{
        fun DocumentSnapshot.toBurclar():Burclar?{
            val burc_ad = get("burc_ad") as String
            val burc_img = get("burc_img") as String
            val burc_tarih = get("burc_tarih") as String
            val burc_gunluk = get("burc_gunluk") as String
            val burc_haftalik = get("burc_haftalik") as String
            val burc_aylik = get("burc_aylik") as String
            val burc_yillik = get("burc_yillik") as String
            val burc_genelozellik = get("burc_genelozellik") as String
            return Burclar(burc_ad,burc_img,burc_tarih,burc_gunluk,burc_haftalik,burc_aylik,burc_yillik,burc_genelozellik)
        }
    }
}