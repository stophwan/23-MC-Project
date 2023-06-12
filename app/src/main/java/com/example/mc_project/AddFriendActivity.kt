package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mc_project.databinding.ActivityTabBinding
import com.example.mc_project.databinding.FriendAddBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFriendActivity : AppCompatActivity(){
    lateinit var binding: FriendAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FriendAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)
        var userId: String
        binding.add.setOnClickListener {
            userId = binding.edit.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val user = db!!.userDao().getUserByAuthId(userId)
                db.followDao().insert(Follow(followingId = 1, followerId = user.id))
                
                //Toast.makeText(applicationContext,"친구 추가가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}