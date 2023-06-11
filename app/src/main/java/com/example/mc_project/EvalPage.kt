package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mc_project.databinding.EvalBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.dto.SearchPlaceResultDto

class EvalPage:Activity() {
    lateinit var binding: EvalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = EvalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)

        binding.submt.setOnClickListener {
            val score = binding.score.text.toString()
            val review = binding.review.text.toString()

            val intent = Intent(this, MainActivity::class.java)  // 맛집 평가 후 메인 화면으로 이동
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            val intent = Intent(this, SearchPlace::class.java)  // 맛집 평가에서 검책 창으로 이동
            startActivity(intent)
        }
    }
}