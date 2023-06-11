package com.example.mc_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.adapter.MyPageAdapter
import com.example.mc_project.databinding.MypageBinding
import com.example.mc_project.db.FoodieDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPage : Fragment() {
    private lateinit var binding: MypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MypageBinding.inflate(inflater, container, false)
        val db = FoodieDataBase.getInstance(requireContext())
        var adapter = MyPageAdapter(mutableListOf())

        GlobalScope.launch(Dispatchers.IO) {
            val userPlaces = db!!.userDao().getUserWithTastePlaceByUser(1)
            var tastePlace = userPlaces.tastePlaces
            Log.d("Table", tastePlace.size.toString())
            withContext(Dispatchers.Main){
                adapter.setTastePlaceList(tastePlace.toMutableList())
                binding.reMylist.adapter = adapter
            }
        }

        binding.reMylist.layoutManager = LinearLayoutManager(requireContext())
        binding.reMylist.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        return binding.root
    }

}
