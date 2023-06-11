package com.example.mc_project

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.mc_project.databinding.FriendAddBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow

class AddFriendActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val binding = FriendAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)
        var userId: String
        binding.add.setOnClickListener {
            userId = binding.edit.text.toString()
            val user = db!!.userDao().getUserByAuthId(userId)
            db.followDao().insert(Follow(followingId = 1, followerId = user.id))
        }
    }
}