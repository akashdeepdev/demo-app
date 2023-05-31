package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.demoapp.adapters.FragmentsVPAdapter
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.fragments.ListFragment
import com.example.demoapp.fragments.LoginFragment
import com.example.demoapp.fragments.MapsFragment
import com.example.demoapp.models.LocationItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.util.Objects

class MainActivity : AppCompatActivity() {

    companion object {
        val latlongList = ArrayList<LocationItem>()
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var vpAdapter : FragmentsVPAdapter
    private var fragmentsList : ArrayList<Fragment>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        vpAdapter = FragmentsVPAdapter(this)
        if(fragmentsList == null){
            fragmentsList = arrayListOf(MapsFragment(),ListFragment(),LoginFragment());
        }
        binding.apply {
            vpAdapter.updateFragmentsList(fragmentsList!!)
            vpFragments.adapter = vpAdapter
            bottomAppBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
                when(it.itemId){
                    R.id.mapFragment->{
                        vpFragments.currentItem = 0
                        return@OnItemSelectedListener  true
                    }
                    R.id.listFragment->{
                        vpFragments.currentItem = 1
                        return@OnItemSelectedListener  true
                    }
                    R.id.loginFragment->{
                        vpFragments.currentItem = 2
                        return@OnItemSelectedListener  true
                    }
                }
                return@OnItemSelectedListener false
            })
        }
        handleFragmentnav()
    }

    override fun onBackPressed() {
        if(binding.vpFragments.currentItem<=0){
            finish()
        }else{
            handleFragmentnav()
        }
    }

    private fun handleFragmentnav() {
        binding.apply {
            vpFragments.currentItem = vpFragments.currentItem-1
            vpFragments.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position){
                        0->{
                            bottomAppBar.selectedItemId = R.id.mapFragment

                        }
                        1->{
                            bottomAppBar.selectedItemId = R.id.listFragment

                        }
                        2->{
                            bottomAppBar.selectedItemId = R.id.loginFragment

                        }
                    }
                }


            })

        }

    }
}