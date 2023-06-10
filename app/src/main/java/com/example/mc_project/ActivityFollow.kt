package com.example.mc_project
/**
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.adapter.FriendAdapter
import com.example.mc_project.databinding.FriendpageBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.User
import kotlinx.coroutines.*

class ActivityFollow: AppCompatActivity()  {
    lateinit var binding: FriendpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FriendpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = FoodieDataBase.getInstance(applicationContext)

        var userArr = mutableListOf(
            User(authId = "a", password = "a", name = "정지환", tasteCount = 3),
            User(authId = "a", password = "a", name = "박하나", tasteCount = 3),
            User(authId = "a", password = "a", name = "신지영", tasteCount = 3),
        )

        // 친구 목록 RecyclerView 부분 입니다.
        val Fadapter = FriendAdapter(mutableListOf())

        CoroutineScope(Dispatchers.Main).launch {
            val getList = CoroutineScope(Dispatchers.IO).async {
                //db!!.userDao().getFollowers()// 확인용으로 임의로 넣었습니다.
            }.await()
            withContext(Dispatchers.Main) {
                Fadapter.friendList(getList as MutableList<User>)
                binding.reFreind.adapter = Fadapter
            }
        }
        binding.reFreind.layoutManager = LinearLayoutManager(this)
        binding.reFreind.adapter = Fadapter
        binding.reFreind.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
}
        **/