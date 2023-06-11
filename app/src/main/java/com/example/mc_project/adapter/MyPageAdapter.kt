package com.example.mc_project.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.R
import com.example.mc_project.databinding.MyListBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.UserWithTastePlace

class MyPageAdapter(private var dataSet: MutableList<TastePlace>): RecyclerView.Adapter<MyPageAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: MyListBinding) :RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.myprofile.setImageDrawable(ContextCompat.getDrawable(binding.root.context,
            R.drawable.markericon
        ))
        binding.review.text = dataSet[position].content
        //binding.location.text = dataSet[position]
        binding.star.text = dataSet[position].rate.toString()

    }
    fun setTastePlaceList (tasteList: MutableList<TastePlace>) { this.dataSet = tasteList }
    fun getTastePlace(pos:Int) : TastePlace { return dataSet[pos] }
}