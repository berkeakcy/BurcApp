package com.berkeakcy.burcapp

import com.berkeakcy.burcapp.Burclar.Companion.toBurclar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


class BurclarRepositoryImp:BurclarRepository {
    private lateinit var database: FirebaseFirestore
    private lateinit var storage : FirebaseStorage
    override fun getBurclar(result: (UIState<ArrayList<Burclar>>) -> Unit){
        val data = arrayListOf<Burclar>()
        database = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        database.collection("Burc").orderBy("burc_ad").get().addOnSuccessListener {
            for (document in it){
                val burc = document.toBurclar()
                data.add(burc!!)
            }
            result.invoke(
                UIState.Success(data)
            )
        }.addOnFailureListener{
            result.invoke(
                UIState.Failure(it.localizedMessage)
            )
        }
    }

    override fun getBurcGunlukIcerik(burc:Burclar,result: (UIState<String>) -> Unit) {
        database = FirebaseFirestore.getInstance()
        val document = database.collection("Burc").whereEqualTo("burc_ad",burc.burc_ad).get().addOnSuccessListener {
            for (doc in it){
                doc.getString("burc_gunluk")?.let {
                    result.invoke(UIState.Success(it))
                }
            }
        }.addOnFailureListener{
            result.invoke(UIState.Failure(it.localizedMessage))
        }
    }

    override fun getBurcHaftalikIcerik(burc:Burclar,result: (UIState<String>) -> Unit) {
        database = FirebaseFirestore.getInstance()
        val document = database.collection("Burc").whereEqualTo("burc_ad",burc.burc_ad).get().addOnSuccessListener {
            for (doc in it){
                doc.getString("burc_haftalik")?.let {
                    result.invoke(UIState.Success(it))
                }
            }
        }.addOnFailureListener{
            result.invoke(UIState.Failure(it.localizedMessage))
        }
    }
    override fun getBurcAylikIcerik(burc:Burclar,result: (UIState<String>) -> Unit) {
        database = FirebaseFirestore.getInstance()
        val document = database.collection("Burc").whereEqualTo("burc_ad",burc.burc_ad).get().addOnSuccessListener {
            for (doc in it){
                doc.getString("burc_aylik")?.let {
                    result.invoke(UIState.Success(it))
                }
            }
        }.addOnFailureListener{
            result.invoke(UIState.Failure(it.localizedMessage))
        }
    }
    override fun getBurcYillikIcerik(burc:Burclar,result: (UIState<String>) -> Unit) {
        database = FirebaseFirestore.getInstance()
        val document = database.collection("Burc").whereEqualTo("burc_ad",burc.burc_ad).get().addOnSuccessListener {
            for (doc in it){
                doc.getString("burc_yillik")?.let {
                    result.invoke(UIState.Success(it))
                }
            }
        }.addOnFailureListener{
            result.invoke(UIState.Failure(it.localizedMessage))
        }
    }
    override fun getBurcGenelozelliklerIcerik(burc:Burclar,result: (UIState<String>) -> Unit) {
        database = FirebaseFirestore.getInstance()
        val document = database.collection("Burc").whereEqualTo("burc_ad",burc.burc_ad).get().addOnSuccessListener {
            for (doc in it){
                doc.getString("burc_genelozellik")?.let {
                    result.invoke(UIState.Success(it.replace("_b","\n")))
                }
            }
        }.addOnFailureListener{
            result.invoke(UIState.Failure(it.localizedMessage))
        }
    }
}