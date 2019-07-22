package com.star_zero.navigation_keep_fragment_sample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.star_zero.navigation_keep_fragment_sample.NotificationsFragment.Companion.NOTIFICATION_KEY
import com.star_zero.navigation_keep_fragment_sample.databinding.ActivityMainBinding
import com.star_zero.navigation_keep_fragment_sample.navigation.KeepStateNavigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        // setup custom navigator
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator

        // set navigation graph
        navController.setGraph(R.navigation.nav_graph)

        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.setOnNavigationItemReselectedListener { startSelectedFragment(it) }
        binding.bottomNav.setOnNavigationItemSelectedListener { startSelectedFragment(it) }
    }

    private fun startSelectedFragment(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_home -> {
                navController.navigate(item.itemId)
                return true
            }
            R.id.navigation_dashboard -> {
                navController.navigate(item.itemId)
                return true
            }
            R.id.navigation_notifications -> {
                val bundle = Bundle().apply {
                    putString(NOTIFICATION_KEY, "bigbacbkboom is here")
                }
                navController.navigate(item.itemId, bundle)
                return true
            }
        }

        return false
    }
}
