package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mc_project.databinding.ActivityMainBinding

class LoginPage : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tele.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            로그인 버튼 클릭 시 메인 액티비티로 이동 (카카오로그인 구현 전 임시)
        }
    }
}
