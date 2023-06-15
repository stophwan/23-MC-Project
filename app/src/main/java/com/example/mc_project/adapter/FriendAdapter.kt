package com.example.mc_project.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.R
import com.example.mc_project.databinding.FriendListBinding
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.User

class FriendAdapter(private var dataSet: List<User>): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    class FriendViewHolder(val binding: FriendListBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(FriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    private lateinit var itemClickListener: OnItemClickListener
    interface  OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun setFriendList (friendList: List<User>) { this.dataSet = friendList }
    fun getFriendList (): List<User> {return dataSet}
    fun getFriend(pos:Int) : User { return dataSet[pos] }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val binding = (holder as FriendViewHolder).binding
        binding.profile.setImageDrawable(ContextCompat.getDrawable(binding.root.context,
            R.drawable.main_img
        ))
        binding.friendName.text = dataSet[position].name
        binding.foodCount.text = dataSet[position].friendCount.toString()
        binding.delete.setOnClickListener {
            itemClickListener.onClick(it, dataSet[position].id)
        }
    }

}
