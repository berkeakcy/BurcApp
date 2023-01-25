package com.berkeakcy.burcapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.berkeakcy.burcapp.databinding.FragmentBurcIcerikBinding
import java.util.*


class BurcIcerikFragment : Fragment(){
    private lateinit var binding: FragmentBurcIcerikBinding
    private val args:BurcIcerikFragmentArgs by navArgs()
    private lateinit var date: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurcIcerikBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        binding.burcImageView.setImageResource(requireContext().resources.getIdentifier(args.burc.burc_img,"drawable",requireContext().packageName))
        binding.burcAdText.text = args.burc.burc_ad
        binding.burcTarihText.text = args.burc.burc_tarih
        val toolbarTitle = (requireActivity() as MainActivity).toolbarControl().toString()

        when(toolbarTitle){
            "Günlük Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_gunluk
                date = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
                binding.gecerliTarihText.text = date
            }
            "Haftalık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_haftalik
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY)
                val start = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
                val end = calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + monthConvert(calendar.get(Calendar.MONTH))
                binding.gecerliTarihText.text = "$start - $end"
            }
            "Aylık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_aylik
                date = monthConvert(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR).toString()
                binding.gecerliTarihText.text = date
            }
            "Yıllık Burçlar" -> {
                binding.burcIcerikText.text = args.burc.burc_yillik
                date = calendar.get(Calendar.YEAR).toString()
                binding.gecerliTarihText.text = date
            }
            "Genel Özellikler" -> {
                binding.burcIcerikText.text = args.burc.burc_genelozellik
                binding.gecerliTarihText.text = args.burc.burc_ad + " Burcunun Genel Özellikleri"
            }
        }
    }

    fun monthConvert(month:Int):String{
        when(month){
            0 -> return "Ocak"
            1 -> return "Şubat"
            2 -> return "Mart"
            3 -> return "Nisan"
            4 -> return "Mayıs"
            5 -> return "Haziran"
            6 -> return "Temmuz"
            7 -> return "Ağustos"
            8 -> return "Eylül"
            9 -> return "Ekim"
            10 -> return "Kasım"
            11 -> return "Aralık"
            else -> return "error convert"
        }
    }
}