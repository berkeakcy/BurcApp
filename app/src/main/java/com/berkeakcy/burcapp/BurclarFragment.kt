package com.berkeakcy.burcapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.berkeakcy.burcapp.databinding.FragmentBurclarBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class BurclarFragment : Fragment() {
    private lateinit var binding:FragmentBurclarBinding
    private lateinit var database : FirebaseFirestore
    private lateinit var adapter : BurclarAdapter

    var burcList = ArrayList<Burclar>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseFirestore.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurclarBinding.inflate(inflater)
        getVeriler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getVeriler()
        binding.rvBurc.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        adapter = BurclarAdapter(requireContext(),burcList)
        binding.rvBurc.adapter = adapter


    }

    @SuppressLint("NotifyDataSetChanged")
    fun getVeriler(){
        database.collection("Burc").addSnapshotListener{ snapshot, exception -> //whereEqualTo filtre öğren
            if(exception != null){
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if(snapshot != null){
                    if(!snapshot.isEmpty){
                        val veriler = snapshot.documents

                        burcList.clear()

                        for(i in veriler){
                            val burc_ad = i.get("burc_ad") as String
                            val burc_img = i.get("burc_img") as String
                            val burc_tarih = i.get("burc_tarih") as String
                            val burc_gunluk = i.get("burc_gunluk") as String
                            val burc_haftalik = i.get("burc_haftalik") as String
                            val burc_aylik = i.get("burc_aylik") as String
                            val burc_yillik = i.get("burc_yillik") as String
                            val burc_genelozellik = i.get("burc_genelozellik") as String

                            burcList.add(Burclar(burc_ad,burc_img,burc_tarih,burc_gunluk,burc_haftalik,burc_aylik,burc_yillik,burc_genelozellik))
                        }
                        binding.rvBurc.adapter?.notifyDataSetChanged()//yeni veri geldi kendini yenile
                    }
                }
            }
        }
    }


}