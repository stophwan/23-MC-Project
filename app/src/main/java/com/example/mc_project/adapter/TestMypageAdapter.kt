package com.example.mc_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.R
import com.example.mc_project.databinding.MyListBinding

class TestMypageAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<TestMypageAdapter.MyViewHolder>() {
    class MyViewHolder(val binding : MyListBinding) : RecyclerView.ViewHolder(binding.root){

    }
    override fun getItemCount() = dataSet.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as TestMypageAdapter.MyViewHolder).binding
        binding.myprofile.setImageDrawable(
            ContextCompat.getDrawable(binding.root.context,
            R.drawable.ic_launcher_background
        ))
        binding.restname.text = dataSet[position]
    }
}