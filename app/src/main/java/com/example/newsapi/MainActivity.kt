package com.example.newsapi

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsapi.Fragments.BusinessFragment
import com.example.newsapi.Fragments.EntertainmentFragment
import com.example.newsapi.Fragments.HomeFragment
import com.example.newsapi.Fragments.SportsFragment
import com.example.newsapi.Fragments.TechFragment
import com.example.newsapi.model.NewsData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.newsapi.viewmodel.MainViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var bottombar: BottomNavigationView
    private lateinit var viewPagerAdapter: MyPagerAdapter
    private lateinit var pager :ViewPager
    private lateinit var viewModel: MainViewmodel

    companion object {

      val APIKEY = "024b7f4277954796b2e090ccc7ef79e0"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel  = ViewModelProvider(this).get(MainViewmodel::class.java)

        bottombar = findViewById(R.id.bottom_bar)
        pager = findViewById(R.id.mainPager)
        bottombar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                  R.id.home->{
                   pager.currentItem =0
                }
                R.id.tech->{
                    pager.currentItem =3
                }
                R.id.sports->{
                    pager.currentItem =1
                }
                R.id.business->{
                    pager.currentItem =2
                }
                R.id.entetainment->{
                    pager.currentItem =4
                }
                else->{

                }
            }
            true
        }

        viewPagerAdapter= MyPagerAdapter(supportFragmentManager)
        pager.adapter = viewPagerAdapter
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0->bottombar.selectedItemId = R.id.home
                    1 -> bottombar.selectedItemId = R.id.sports
                    2 -> bottombar.selectedItemId = R.id.business
                    3 -> bottombar.selectedItemId = R.id.tech
                    4 -> bottombar.selectedItemId = R.id.entetainment
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })



    }

}