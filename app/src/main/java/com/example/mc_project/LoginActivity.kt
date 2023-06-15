package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mc_project.databinding.ActivityMainBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import kotlinx.coroutines.*

class LoginActivity : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)

        var userArr = mutableListOf(
            User(id = 1, authId = "mobile", password = "a", name = "정지환", tasteCount = 2, friendCount = 1),
            User(id = 2, authId = "program", password = "a", name = "박하나", tasteCount = 3, friendCount = 3),
        )


        var tastePlaceArr = mutableListOf(
            TastePlace(id=1, userId = 1, type = "식당", longitude = 126.92555227861433, latitude = 37.580495976590896,
                name = "순이네 고릴라 떡볶이 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=2, userId = 1, type = "식당", latitude = 37.579909, longitude= 126.924733,
                name = "맥도날드 명지대점 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=3, userId = 2, type = "식당", latitude = 37.580556, longitude = 126.922833,
                name = "모래내 곱창 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=4,userId = 2, type = "식당", latitude = 37.580545, longitude =126.9251566,
                name = "김밥천국 명지대점 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=5,userId = 2, type = "식당", latitude = 37.576510, longitude = 126.919716,
                name = "나의 행복한 돈까스 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천")
        )

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            for (user in userArr) {
                withContext(Dispatchers.IO) {
                    db!!.userDao().insert(user)
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
