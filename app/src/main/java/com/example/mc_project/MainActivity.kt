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
    //val kakaoSearch = KakaoSearch();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout = binding.tabLayout
        val viewPage = binding.viewPager

        // 검색 키워드에 이 코드 호출 해주시면 됩니다.

        //kakaoSearch.searchPlaceByKeyword("맥도날드", "127.06283102249932", "37.514322572335935", 10000)

        //val tabIcons = listOf(R.drawable.friend, R.drawable.map, R.drawable.mypage)
        viewPage.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        viewPage.setCurrentItem(1, false)
        viewPage.setUserInputEnabled(false);
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
