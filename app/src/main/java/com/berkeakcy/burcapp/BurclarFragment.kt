package com.berkeakcy.burcapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.berkeakcy.burcapp.databinding.FragmentBurclarBinding

class BurclarFragment : Fragment() {
    private lateinit var binding:FragmentBurclarBinding
    private lateinit var adapter : BurclarAdapter
    private lateinit var burclarViewModel: BurclarViewModel
    var burcList = ArrayList<Burclar>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurclarBinding.inflate(inflater)
        burclarViewModel = ViewModelProvider(this,BurclarViewModelFactory(BurclarRepositoryImp())).get(BurclarViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        burclarViewModel.burcList.observe(viewLifecycleOwner){state ->
            burcList.clear()
            when(state){
                is UIState.Loading ->  Log.e("BurclarFragment","Loading")
                is UIState.Failure ->  Log.e("BurclarFragment","error")
                is UIState.Success -> {
                    state.data.forEach{
                        burcList.add(it)
                        binding.rvBurc.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        binding.rvBurc.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        adapter = BurclarAdapter(requireContext(),burcList)
        binding.rvBurc.adapter = adapter


        binding.swipeLayoutOne.setOnRefreshListener {
            burclarViewModel.getBurclar()
            burclarViewModel.burcList.observe(viewLifecycleOwner){state ->
                burcList.clear()
                when(state){
                    is UIState.Loading ->  Log.e("BurclarFragment","Loading")
                    is UIState.Failure ->  Log.e("BurclarFragment","error")
                    is UIState.Success -> {
                        state.data.forEach{
                            burcList.add(it)
                            binding.rvBurc.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
            binding.swipeLayoutOne.isRefreshing = false
        }
    }
}