package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.adapter.SearchPlaceAdapter
import com.example.mc_project.databinding.SearchBinding
import com.example.mc_project.databinding.SearchListBinding
import com.example.mc_project.dto.PlaceData
import com.example.mc_project.dto.SearchPlaceResultDto
import com.example.mc_project.kakao.KakaoSearch
import kotlinx.coroutines.*

class SearchPlace: Activity() {
    private lateinit var binding: SearchBinding
    private var keyword = ""
    private var longitude = "126.922819"
    private var latitude = "37.580545"
    private var radius: Int = 10000
    private val kakaoSearch = KakaoSearch()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = SearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var adapter = SearchPlaceAdapter(mutableListOf())
        fun search() {
            keyword = binding.searchEditText.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                val placeDataList = withContext(Dispatchers.IO) {
                    kakaoSearch.searchPlaceByKeyword(keyword, longitude, latitude, radius)
                }
                adapter.setList(placeDataList.toMutableList())
            }
        }
        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                search()
                return@setOnKeyListener true
            }
            false
        }

        binding.searchBtn.setOnClickListener {
            search()
        }

        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter


        binding.back.setOnClickListener {
            val intent = Intent(this@SearchPlace, MainActivity::class.java)
            startActivity(intent)
        }
    }
}