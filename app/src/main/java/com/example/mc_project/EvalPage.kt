package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mc_project.databinding.EvalBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.dto.PlaceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EvalPage:Activity(){
    lateinit var binding: EvalBinding
    private lateinit var selectedPlaceData: PlaceData

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = EvalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)
        val category_name = intent.getStringExtra("category_name")
        val x = intent.getStringExtra("x")
        val y = intent.getStringExtra("y")
        val place_name = intent.getStringExtra("place_name")

        binding.selectRest.setText(intent.getStringExtra("place_name"))
        binding.selectLocation.setText(intent.getStringExtra("road_address_name"))

        binding.submt.setOnClickListener {
            val score = binding.score.text.toString()
            val review = binding.review.text.toString()
/**
            val tastePlace = TastePlace(
                userId = 1,  // 사용자 ID
                type = category_name.toString(),
                longitude = 0.0,
                latitude = 0.0,
                name = place_name.toString(),
                rate = score.toDouble(),
                content = review
            )
            db?.tastePlaceDao()?.insert(tastePlace)
**/
            CoroutineScope(Dispatchers.IO).launch {
                val tastePlace = TastePlace(
                    userId = 1,  // 사용자 ID
                    type = category_name.toString(),
                    longitude = x!!.toDouble(),
                    latitude = y!!.toDouble(),
                    name = place_name.toString(),
                    rate = score.toDouble(),
                    content = review
                )
                db?.tastePlaceDao()?.insert(tastePlace)
            }

            val intent = Intent(this, MainActivity::class.java)  // 맛집 평가 후 메인 화면으로 이동
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            val intent = Intent(this, SearchPlace::class.java)  // 맛집 평가에서 검책 창으로 이동
            startActivity(intent)
        }
    }
}