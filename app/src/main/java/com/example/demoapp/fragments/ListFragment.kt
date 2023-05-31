package com.example.demoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.MainActivity
import com.example.demoapp.R
import com.example.demoapp.adapters.LocationsListRV
import com.example.demoapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding:FragmentListBinding
    private lateinit var adapter:LocationsListRV

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        adapter = LocationsListRV(){
            MainActivity.latlongList.remove(MainActivity.latlongList[it])
        }
        binding.apply {
            adapter.updateLocationList(MainActivity.latlongList)
            locationList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            locationList.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {
        const val TAG = "LIST_SCREEN"
    }
}