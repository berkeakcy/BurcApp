package com.berkeakcy.burcapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.berkeakcy.burcapp.databinding.FragmentBurcIcerikBinding
import com.google.android.material.navigation.NavigationView


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
        binding.text.text = args.burc.burc_ad
    }
}