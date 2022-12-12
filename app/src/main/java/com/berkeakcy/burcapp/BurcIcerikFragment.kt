package com.berkeakcy.burcapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.berkeakcy.burcapp.databinding.FragmentBurcIcerikBinding


class BurcIcerikFragment : Fragment(){
    private lateinit var binding: FragmentBurcIcerikBinding
    private val args:BurcIcerikFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurcIcerikBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.burcImageView.setImageResource(requireContext().resources.getIdentifier(args.burc.burc_img,"drawable",requireContext().packageName))
        binding.burcAdText.text = args.burc.burc_ad
        binding.burcTarihText.text = args.burc.burc_tarih
        val toolbarTitle = (requireActivity() as MainActivity).toolbarControl().toString()
        if(toolbarTitle == "Aylık Burçlar"){
            //binding.text.text = args.burc.burc_gunluk
        }
        else{
            //binding.text.text = args.burc.burc_ad
        }

        when(toolbarTitle){
            "Günlük Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_gunluk
            }
            "Haftalık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_haftalik
            }
            "Aylık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_aylik
            }
            "Yıllık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_yillik
            }
            "Genel Özellikler" -> {
                binding.burcIcerikText.text = args.burc.burc_genelozellik
            }
        }
    }
}