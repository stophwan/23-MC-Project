package com.example.mc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.mc_project.databinding.ActivityTabBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.User
import com.example.mc_project.kakao.KakaoSearch
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            User("a", "a", "정지환", 3),
            User("a", "a", "박하나", 5),
            User("a", "a", "신지영", 5),
        )
        var followArr = mutableListOf(
            Follow(1,2)
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

        // 검색 키워드에 이 코드 호출 해주시면 됩니다.
        kakaoSearch.searchPlaceByKeyword("맥도날드", "127.06283102249932", "37.514322572335935", 10000)

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
        }.attach()
    }
}
