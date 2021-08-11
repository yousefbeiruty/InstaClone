package com.it.yousefl.instaclone.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.it.yousefl.instaclone.R
import com.it.yousefl.instaclone.databinding.ActivityMainBinding



const val TAG="MainActivity"
class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val storiesViewModel by viewModels<HomeViewModel>()

    private val adapter=StoriesRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNav()
        binding.rcStories.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.rcStories.adapter=adapter
        storiesViewModel.fetchTags()
    }

    private fun setUpNav() {
        val navView: BottomNavigationView =binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_top, R.id.navigation_hot))
        //  setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        storiesViewModel.tagGalleries.observe(this){
        adapter.submitList(it)
        }
    }


}