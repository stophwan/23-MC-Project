package com.example.mc_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val BTN_NUM = 3
class FragmentAdapter(fragment: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragment, lifecycle){
        override fun getItemCount() : Int = BTN_NUM
        override fun createFragment(position:Int) : Fragment {
            when (position) {
                0 -> return FriendFragment()
                1 -> return MapPage()
                2 -> return Mypage()
            }
        return MapPage()
    }
}