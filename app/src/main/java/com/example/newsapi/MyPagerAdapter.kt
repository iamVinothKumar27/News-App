package com.example.newsapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapi.Fragments.BusinessFragment
import com.example.newsapi.Fragments.EntertainmentFragment
import com.example.newsapi.Fragments.HomeFragment
import com.example.newsapi.Fragments.SportsFragment
import com.example.newsapi.Fragments.TechFragment

class MyPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
       return when(position){
           0-> HomeFragment()
           1-> SportsFragment()
           2-> BusinessFragment()
           3 ->TechFragment()
           4 ->EntertainmentFragment()
           else -> HomeFragment()
       }
    }
}