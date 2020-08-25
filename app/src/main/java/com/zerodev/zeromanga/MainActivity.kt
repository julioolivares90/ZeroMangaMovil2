package com.zerodev.zeromanga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout

import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

   private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.busquedaFragment,R.id.favoritosFragment))
        setupActionBarWithNavController(nav_host_fragment.findNavController(),appBarConfiguration)

        //findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
       bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())

        nav_host_fragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.mainFragment, R.id.busquedaFragment,R.id.favoritosFragment -> {bottomNavigationView.visibility = View.VISIBLE}
                else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    override fun onSupportNavigateUp(): Boolean {

        return  nav_host_fragment.findNavController().navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}