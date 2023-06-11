package com.example.mc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.mc_project.adapter.FriendAdapter
import com.example.mc_project.databinding.ActivityTabBinding
import com.example.mc_project.databinding.FriendpageBinding
import com.example.mc_project.databinding.MypageBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import com.example.mc_project.kakao.KakaoSearch
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabBinding
    val kakaoSearch = KakaoSearch();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout = binding.tabLayout
        val viewPage = binding.viewPager

        var db = FoodieDataBase.getInstance(applicationContext)

        var userArr = mutableListOf(
            User(authId = "a", password = "a", name = "정지환", tasteCount = 3, friendCount = 2),
            User(authId = "a", password = "a", name = "박하나", tasteCount = 3, friendCount = 2),
            User(authId = "a", password = "a", name = "신지영", tasteCount = 3, friendCount = 1),
        )

        var followArr = mutableListOf(
            Follow(followingId = 1, followerId = 2),
            Follow(followingId = 1, followerId = 3),
            Follow(followingId = 2, followerId = 1),
            Follow(followingId = 2, followerId = 3),
            Follow(followingId = 3, followerId = 2),
        )

        var tastePlaceArr = mutableListOf(
            TastePlace(userId = 1, type = "식당", longitude = 127.0588583663039, latitude = 37.5109095881113,
            name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(userId = 1, type = "식당", longitude = 127.07693931122469, latitude = 37.50433469932041,
                name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(userId = 1, type = "식당", longitude = 127.04690276073, latitude = 37.5035719753543,
                name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(userId = 1, type = "식당", longitude = 127.041413345131, latitude = 37.5162457553437,
                name = "삼겹베네 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천")

        )

        for(user in userArr) {
            CoroutineScope(Dispatchers.IO).launch {
                db!!.userDao().insert(user)
            }
        }

        for(follow in followArr) {
            CoroutineScope(Dispatchers.IO).launch {
                db!!.followDao().insert(follow)
            }
        }

        for(tablePlace in tastePlaceArr) {
            CoroutineScope(Dispatchers.IO).launch {
                db!!.tastePlaceDao().insert(tablePlace)
            }
        }

        val viewPager2: ViewPager2 = binding.viewPager.apply {

}

        // 검색 키워드에 이 코드 호출 해주시면 됩니다.
        kakaoSearch.searchPlaceByKeyword("맥도날드", "127.06283102249932", "37.514322572335935", 10000)

        //val tabIcons = listOf(R.drawable.friend, R.drawable.map, R.drawable.mypage)
        viewPage.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.friend)
                }
                1 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.map)
                }
                2 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.mypage)
                }
            }
            //tab.icon = ContextCompat.getDrawable(this, tavIcons[position])
        }.attach()
    }
}
