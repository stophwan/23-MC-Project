package com.example.mc_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.databinding.FriendpageBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import kotlinx.coroutines.*

class ActivityFollow: AppCompatActivity()  {
    lateinit var binding: FriendpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FriendpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = FoodieDataBase.getInstance(applicationContext)

        var followArr = mutableListOf(
            Follow(followingId = 1, followerId = 2),
            Follow(followingId = 2, followerId = 3),
            Follow(followingId = 2, followerId = 1),
            Follow(followingId = 2, followerId = 3),
            Follow(followingId = 3, followerId = 2),
        )

        // 친구 목록 RecyclerView 부분 입니다.
        val Fadapter = AdapterFollow(mutableListOf())

        CoroutineScope(Dispatchers.Main).launch {
            val getList = CoroutineScope(Dispatchers.IO).async {
                db!!.followDao().getFollowerList(1)  // 확인용으로 임의로 넣었습니다.
            }.await()
            withContext(Dispatchers.Main) {
                Fadapter.setList(getList as MutableList<Follow>)
                binding.reFreind.adapter = Fadapter
            }
        }
        binding.reFreind.layoutManager = LinearLayoutManager(this)
        binding.reFreind.adapter = Fadapter
        binding.reFreind.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
}