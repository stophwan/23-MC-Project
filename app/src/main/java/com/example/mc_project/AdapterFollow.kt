package com.example.mc_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.databinding.FriendListBinding
import com.example.mc_project.db.table.Follow

class AdapterFollow (private var dataSet: MutableList<Follow>): RecyclerView.Adapter<AdapterFollow.FollowViewHolder>() {
    class FollowViewHolder(val binding: FriendListBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        return FollowViewHolder(FriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    fun setList (newList: MutableList<Follow>) { this.dataSet = newList }
    fun getFriend(pos:Int) : Follow { return dataSet[pos] }
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val binding = (holder as FollowViewHolder).binding
        binding.profile.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_launcher_background))
        binding.friendName.text = dataSet[position].id.toString()
        //binding.foodCount.text = dataSet[position]
        /*
        binding.friend.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

         */

    }
}