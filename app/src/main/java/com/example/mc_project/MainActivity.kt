package com.example.mc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.mc_project.databinding.ActivityTabBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout = binding.tabLayout
        val viewPage = binding.viewPager

        viewPage.adapter = AdapterFragment(supportFragmentManager, lifecycle)
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