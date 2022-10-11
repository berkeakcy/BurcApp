package com.berkeakcy.burcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.berkeakcy.burcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.navController)

        //binding.toolbar.title = "Burçlar"
        navHostFragment.navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            binding.toolbar.title = when (destination.id) {
                R.id.burclarFragmentGunluk -> "Günlük Burçlar"
                R.id.burclarFragmentHaftalik -> "Haftalik Burçlar"
                R.id.burclarFragmentAylik -> "Aylık Burçlar"
                R.id.burclarFragmentYillik -> "Yıllık Burçlar"
                else -> "asd"
            }

        }

        val toggle = ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }


}