package com.example.mc_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.R
import com.example.mc_project.databinding.MyListBinding
import com.example.mc_project.db.table.TastePlace

class MypageAdapter(private var dataSet: MutableList<TastePlace>): RecyclerView.Adapter<MypageAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: MyListBinding) :RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    fun tasteList (tasteList: MutableList<TastePlace>) { this.dataSet = tasteList }
    fun getTaste(pos:Int) : TastePlace { return dataSet[pos] }
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.myprofile.setImageDrawable(ContextCompat.getDrawable(binding.root.context,
            R.drawable.ic_launcher_background
        ))
        binding.restname.text = dataSet[position].content
        //binding.location.text = dataSet[position]
        binding.star.text = dataSet[position].rate.toString()
        binding.user.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

    }

}