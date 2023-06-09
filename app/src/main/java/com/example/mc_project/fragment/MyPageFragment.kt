package com.example.mc_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.adapter.MyPageAdapter
import com.example.mc_project.databinding.MypageBinding
import com.example.mc_project.db.FoodieDataBase
import kotlinx.coroutines.*

class MyPageFragment : Fragment() {
    private lateinit var binding: MypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MypageBinding.inflate(inflater, container, false)
        val db = FoodieDataBase.getInstance(requireContext())
        var adapter = MyPageAdapter(mutableListOf())

        CoroutineScope(Dispatchers.IO).launch {
            val userPlaces = CoroutineScope(Dispatchers.IO).async {
                db!!.userDao().getUserWithTastePlaceByUser(1)
            }.await()
            withContext(Dispatchers.Main){
                adapter.setTastePlaceList(userPlaces.tastePlaces.toMutableList())
                binding.listCount.text = userPlaces.user.tasteCount.toString()
                binding.name.text = userPlaces.user.name
                binding.reMylist.adapter = adapter
            }
        }

        adapter.setItemClickListener(object: MyPageAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                GlobalScope.launch(Dispatchers.IO) {
                    db!!.tastePlaceDao().deletePlace(adapter.getTastePlaceList()[position].id)
                    withContext(Dispatchers.Main) {
                        adapter.getTastePlaceList().removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        binding.reMylist.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}
