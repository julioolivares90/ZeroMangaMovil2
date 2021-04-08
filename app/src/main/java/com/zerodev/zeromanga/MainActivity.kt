package com.zerodev.zeromanga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zerodev.zeromanga.ui.tmo.descripcion.DescripcionViewModel


class MainActivity : AppCompatActivity() {

   private lateinit var appBarConfiguration : AppBarConfiguration
   private lateinit var navController: NavController

   lateinit var descripcionViewModel: DescripcionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(findViewById(R.id.toolbar))

        setTheme(R.style.AppTheme)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.busquedaFragment,R.id.favoritosFragment))


        val navView : BottomNavigationView = findViewById(R.id.nav_view)

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        //solucion no se encuentra un navhostcontroller
        /*
        *
        * val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        * */
            //findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController,appBarConfiguration)

        //findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        navView.setupWithNavController(navController)


        Navigation.findNavController(this,R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.mainFragment, R.id.busquedaFragment,R.id.favoritosFragment -> {navView.visibility = View.VISIBLE}
                else -> {
                    navView.visibility = View.GONE
                }
            }
        }
       /*
        navView.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.mainFragment, R.id.busquedaFragment,R.id.favoritosFragment -> {navView.visibility = View.VISIBLE}
                else -> {
                    navView.visibility = View.GONE
                }
            }
        }
        */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    override fun onSupportNavigateUp(): Boolean {

        return  navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}