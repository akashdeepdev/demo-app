package com.example.demoapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentsVPAdapter(private val fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun updateFragmentsList(fl:ArrayList<Fragment>){
        fragments.clear()
        fragments.addAll(fl)
    }
}