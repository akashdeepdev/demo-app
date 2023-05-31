package com.example.demoapp.adapters

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.ListItemCardBinding
import com.example.demoapp.models.LocationItem

class LocationsListRV(private val onClickListener:(i:Int)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemArray = ArrayList<LocationItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        return  LocationItemViewHolder(DataBindingUtil.inflate(infalter,R.layout.list_item_card,parent,false))
    }

    override fun getItemCount(): Int {
        return itemArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val lViewHolder = holder as LocationItemViewHolder
       val bind = lViewHolder.bind(itemArray[position])
        bind.btnDelete.setOnClickListener(){
            onClickListener(position)
        }
    }

    fun clearList(){
        itemArray.clear()
    }

    fun updateLocationList(l:ArrayList<LocationItem>){
        clearList()
        itemArray.addAll(l)
    }

    inner class LocationItemViewHolder(private val binding: ListItemCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:LocationItem):ListItemCardBinding{
            binding.lat.text = item.lat.toString()
            binding.lon.text = item.lon.toString()
            return binding
        }
    }
}