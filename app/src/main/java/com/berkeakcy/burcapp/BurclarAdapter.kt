package com.berkeakcy.burcapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BurclarAdapter(private val mContext:Context, private var burcList:ArrayList<Burclar>)
    :RecyclerView.Adapter<BurclarAdapter.BurclarCardTasarim>(){
        inner class BurclarCardTasarim(tasarim: View) : RecyclerView.ViewHolder(tasarim){
            var burc_card : CardView
            var burc_img : ImageView
            var burc_ad : TextView
            var burc_tarih : TextView
            init {
                burc_card = tasarim.findViewById(R.id.burc_cardView)
                burc_img = tasarim.findViewById(R.id.burc_img)
                burc_ad = tasarim.findViewById(R.id.burc_ad_text)
                burc_tarih = tasarim.findViewById(R.id.burc_tarih_text)
            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurclarCardTasarim {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.burc_card_tasarim,parent,false)
        return BurclarCardTasarim(tasarim)
    }

    override fun onBindViewHolder(holder: BurclarCardTasarim, position: Int) {
        val burc = burcList.get(position)
        holder.burc_ad.text = burcList[position].burc_ad
        holder.burc_tarih.text = burcList[position].burc_tarih
        Picasso.get().load(burcList[position].burc_img).into(holder.burc_img)
        holder.burc_card.setOnClickListener(){
            it.findNavController().navigate(BurclarFragmentDirections.actionBurclarFragmentToBurcIcerikFragment(burc))
        }
    }
    override fun getItemCount(): Int {
        return burcList.size
    }
}