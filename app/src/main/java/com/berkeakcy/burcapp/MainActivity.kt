package com.berkeakcy.burcapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.berkeakcy.burcapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawer)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun toolbarControl(): CharSequence? {
        val toolbarTitle = binding.toolbar.title
        return toolbarTitle
    }


}

