package com.example.aquasense.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aquasense.Fragments.HomeFragment
import com.example.aquasense.Fragments.IssuesFragment
import com.example.aquasense.Fragments.SettingsFragment
import com.example.aquasense.Fragments.SolutionsFragment

class FragmentBodyAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {

            return when(position) {
                0-> HomeFragment()
                1-> IssuesFragment()
                2-> SolutionsFragment()
                3-> SettingsFragment()

                else -> HomeFragment()
            }
        }
    }