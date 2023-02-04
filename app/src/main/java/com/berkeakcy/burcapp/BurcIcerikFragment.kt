package com.berkeakcy.burcapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.berkeakcy.burcapp.databinding.FragmentBurcIcerikBinding
import java.util.*


class BurcIcerikFragment : Fragment(){
    private lateinit var binding: FragmentBurcIcerikBinding
    private val args:BurcIcerikFragmentArgs by navArgs()
    private lateinit var burcIcerikViewModel: BurcIcerikViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurcIcerikBinding.inflate(inflater)

        val toolbarTitle = (requireActivity() as MainActivity).toolbarControl().toString()
        burcIcerikViewModel = ViewModelProvider(this,BurcIcerikViewModelFactory(toolbarTitle,args.burc,BurclarRepositoryImp())).get(BurcIcerikViewModel::class.java)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.burcImageView.setImageResource(
            requireContext().resources.getIdentifier(
                args.burc.burc_img,
                "drawable",
                requireContext().packageName
            )
        )
        binding.burcAdText.text = args.burc.burc_ad
        binding.burcTarihText.text = args.burc.burc_tarih
        burcIcerikViewModel.icerik.observe(viewLifecycleOwner) {
            binding.burcIcerikText.text = it.replace("_b","\n")
        }
        burcIcerikViewModel.date.observe(viewLifecycleOwner) {
            binding.gecerliTarihText.text = it
        }


        binding.swipeLayout.setOnRefreshListener{

            burcIcerikViewModel.icerikUpdate()
            burcIcerikViewModel.icerikUpdate.observe(viewLifecycleOwner){state ->
                when(state){
                    is UIState.Loading -> Log.e("BurcIcerikFragment","Loading")
                    is UIState.Failure -> Log.e("BurcIcerikFragment","Failure")
                    is UIState.Success -> {
                        binding.burcIcerikText.text = state.data.replace("_b","\n")
                    }
                }
            }

            binding.swipeLayout.isRefreshing = false
        }
    }
}