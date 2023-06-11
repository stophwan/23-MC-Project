package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.adapter.SearchPlaceAdapter
import com.example.mc_project.databinding.SearchBinding
import com.example.mc_project.databinding.SearchListBinding
import com.example.mc_project.dto.PlaceData
import com.example.mc_project.dto.SearchPlaceResultDto
import com.example.mc_project.kakao.KakaoSearch

class SearchPlace: Activity() {
    private lateinit var binding: SearchBinding
    private var keyword = ""
    private var longitude = ""
    private var latitude = ""
    private var radius: Int = 0
    private val searchItems = arrayListOf<PlaceData>()
    private val kakaoSearch = KakaoSearch()
    private var adapter = SearchPlaceAdapter(searchItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = SearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
/**
        val dataSet = ArrayList<PlaceData>()

        val placeData1 = PlaceData(
            place_name = "Place 1",
            distance = "100 meters",
            place_url = "https://example.com/place1",
            category_name = "Category 1",
            road_address_name = "Address 1",
            id = "1",
            phone = "123-456-7890",
            x = "12.345",
            y = "67.890",
            category_group_code = "Group Code 1",
            category_group_name = "Group Name 1"
        )

        val placeData2 = PlaceData(
            place_name = "Place 2",
            distance = "200 meters",
            place_url = "https://example.com/place2",
            category_name = "Category 2",
            road_address_name = "Address 2",
            id = "2",
            phone = "987-654-3210",
            x = "98.765",
            y = "43.210",
            category_group_code = "Group Code 2",
            category_group_name = "Group Name 2"
        )
        **/
        /**
         * place_name=하나은행 본점, distance=, place_url=http://place.map.kakao.com/8124674,
         * category_name=금융,보험 > 금융서비스 > 은행 > 하나은행, road_address_name=서울 중구 을지로 35,
         * id=8124674, phone=1599-1111, x=126.981866951611, y=37.566491371702,
         * category_group_code=BK9, category_group_name=은행
         **/
/**
        dataSet.add(placeData1)
        dataSet.add(placeData2)
        adapter.setList(dataSet)
        searchItems.addAll(dataSet)
        **/

        binding.searchBtn.setOnClickListener {
            keyword = binding.searchEditText.text.toString()
            kakaoSearch.searchPlaceByKeyword(keyword,longitude, latitude, radius)
        }

        binding.back.setOnClickListener {
            val intent = Intent(this@SearchPlace, MapPage::class.java)
            startActivity(intent)
        }
    }

    fun addItems(searchResult: SearchPlaceResultDto?) {  // keyword로 검색한 내용 list에 저장
        //searchItems.clear()
        if (!searchResult?.documents.isNullOrEmpty()) {
            for (document in searchResult!!.documents) {
                Log.d("nolist", searchItems.toString())
                val item = PlaceData(document.place_name, document.distance, document.place_url,document.category_name,
                                    document.road_address_name,document.id, document.phone,
                                document.x, document.y, document.category_group_code,document.category_group_name)
                searchItems.add(item)
            }
            Log.d("addLIST",searchItems.toString())
            adapter.setList(searchItems)
        }
    }
}