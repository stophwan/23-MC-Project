package com.example.mc_project.kakao

import android.util.Log
import android.widget.Toast
import com.example.mc_project.SearchList
import com.example.mc_project.SearchPlace
import com.example.mc_project.adapter.SearchPlaceAdapter
import com.example.mc_project.databinding.SearchBinding
import com.example.mc_project.databinding.SearchListBinding
import com.example.mc_project.dto.PlaceData
import com.example.mc_project.dto.SearchPlaceResultDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class KakaoSearch {

    private val BASE_URL = "https://dapi.kakao.com"
    private val API_KEY = "KakaoAK 012c39386cb2cb4309c1c04b2eb9d63f"

    fun searchPlaceByKeyword(keyword: String, longitude: String, latitude: String, radius: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoSearchApi::class.java)
        val call = api.getSearchKeyword(API_KEY, keyword, longitude, latitude, radius)

        call.enqueue(object: Callback<SearchPlaceResultDto> {
            override fun onResponse(
                call: Call<SearchPlaceResultDto>,
                response: Response<SearchPlaceResultDto>
            ) {
                Log.d("Test", "Raw: ${response.raw()}")
                Log.d("Test", "Body: ${response.body()}")
                SearchPlace().addItems(response.body())
            }
            override fun onFailure(call: Call<SearchPlaceResultDto>, t: Throwable) {
                Log.w("MainActivity", "통신 실패: ${t.message}")
            }
        })
    }
}
