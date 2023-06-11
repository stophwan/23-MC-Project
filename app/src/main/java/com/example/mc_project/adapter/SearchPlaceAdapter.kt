package com.example.mc_project.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.EvalPage
import com.example.mc_project.databinding.SearchListBinding
import com.example.mc_project.SearchList
import com.example.mc_project.SearchPlace
import com.example.mc_project.databinding.SearchBinding
import com.example.mc_project.dto.PlaceData
import com.example.mc_project.dto.SearchPlaceResultDto

class SearchPlaceAdapter(private var dataSet: ArrayList<PlaceData>): RecyclerView.Adapter<SearchPlaceAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: SearchListBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return MyViewHolder(SearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return MyViewHolder(binding)
    }
/**
    fun setList(newList: ArrayList<PlaceData>)
    {
        this.dataSet = newList
    }
    **/

    fun setList(newList: ArrayList<PlaceData>) {
    /*
        dataSet.clear()
        dataSet.addAll(newList)

        notifyDataSetChanged()
     */
        this.dataSet = newList
        notifyDataSetChanged()
        Log.d("setList",dataSet.toString())
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("count", dataSet.size.toString())
        val binding = (holder as SearchPlaceAdapter.MyViewHolder).binding
        //val binding = holder.binding
        binding.restname.text = dataSet[position].place_name
        binding.location.text = dataSet[position].road_address_name


        binding.submitBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, EvalPage::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }
}