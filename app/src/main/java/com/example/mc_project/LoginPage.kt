package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mc_project.databinding.ActivityMainBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import kotlinx.coroutines.*

class LoginPage : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)

        var userArr = mutableListOf(
            User(id = 1, authId = "a", password = "a", name = "정지환", tasteCount = 3, friendCount = 2),
            User(id = 2, authId = "ab", password = "a", name = "박하나", tasteCount = 3, friendCount = 2),
            User(id = 3, authId = "abc", password = "a", name = "신지영", tasteCount = 3, friendCount = 1),
            User(id = 4, authId = "abcd", password = "a", name = "김세빈", tasteCount = 3, friendCount = 1),
            User(id = 5, authId = "abcde", password = "a", name = "유원준", tasteCount = 3, friendCount = 1),
        )

        var followArr = mutableListOf(
            Follow(id = 1, followingId = 1, followerId = 2),
            Follow(id = 2, followingId = 1, followerId = 3),
            Follow(id = 3, followingId = 2, followerId = 1),
            Follow(id = 4, followingId = 2, followerId = 3),
            Follow(id = 5, followingId = 3, followerId = 2),
        )

        var tastePlaceArr = mutableListOf(
            TastePlace(id=1, userId = 1, type = "식당", longitude = 127.0588583663039, latitude = 37.5109095881113,
                name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=2,userId = 1, type = "식당", longitude = 127.07693931122469, latitude = 37.50433469932041,
                name = "맥도날드 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=3, userId = 1, type = "식당", longitude = 127.04690276073, latitude = 37.5035719753543,
                name = "유짱집밥 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=4,userId = 1, type = "식당", longitude = 127.041413345131, latitude = 37.5162457553437,
                name = "왕가식당", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=5,userId = 2, type = "식당", longitude = 127.0588583663039, latitude = 37.5109095881113,
                name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=6,userId = 2, type = "식당", longitude = 127.07693931122469, latitude = 37.50433469932041,
                name = "맥도날드 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),

        )

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            for (user in userArr) {
                withContext(Dispatchers.IO) {
                    db!!.userDao().insert(user)
                }
            }

            for (follow in followArr) {
                withContext(Dispatchers.IO) {
                    db!!.followDao().insert(follow)
                }
            }

            for (tablePlace in tastePlaceArr) {
                withContext(Dispatchers.IO) {
                    db!!.tastePlaceDao().insert(tablePlace)
                }
            }
        }

        binding.login.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            로그인 버튼 클릭 시 메인 액티비티로 이동 (카카오로그인 구현 전 임시)
        }
    }
}
