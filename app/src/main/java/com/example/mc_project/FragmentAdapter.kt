package com.example.mc_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val BTN_NUM = 3
class FragmentAdapter(private val fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle){
        override fun getItemCount() : Int = BTN_NUM
        override fun createFragment(position:Int) : Fragment {
            fragmentManager.beginTransaction()
            val freindFragement = FriendFragment()
            val mapPage = MapPage()
            val myPage = MyPage()
            when (position) {
                0 -> return FriendFragment()
                1 -> return MapPage()
                2 -> return MyPage()
            }
            return MapPage()
        }
}