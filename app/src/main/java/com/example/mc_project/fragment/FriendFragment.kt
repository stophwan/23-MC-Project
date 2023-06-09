package com.example.mc_project.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.AddFriendActivity
import com.example.mc_project.adapter.FriendAdapter
import com.example.mc_project.databinding.FriendpageBinding
import com.example.mc_project.db.FoodieDataBase
import kotlinx.coroutines.*
import kotlin.streams.toList

class FriendFragment: Fragment() {
    private lateinit var binding : FriendpageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FriendpageBinding.inflate(inflater, container, false)
        val db = FoodieDataBase.getInstance(requireContext())
        var adapter = FriendAdapter(mutableListOf())
        GlobalScope.launch(Dispatchers.IO) {
            val user = db!!.userDao().getUser(1)
            val followersByUser = db!!.followDao().getFollowerList(1)
            val followerIds = followersByUser.stream().map{f-> f.followerId}.toList()
            val followers = db!!.userDao().getFollowers(followerIds)
            withContext(Dispatchers.Main){
                adapter.setFriendList(followers)
                binding.myName.text = user.name
                binding.friendCount.text = user.tasteCount.toString()
                binding.reFreind.adapter = adapter
            }
        }

        binding.plus.setOnClickListener {
            val intent = Intent(requireContext(), AddFriendActivity::class.java)
            startActivity(intent)
        }

        adapter.setItemClickListener(object:FriendAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                GlobalScope.launch(Dispatchers.IO) {
                    db!!.followDao().deleteFollow(position)
                    withContext(Dispatchers.Main) {
                        Log.d("DELETE", position.toString())
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        binding.reFreind.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}