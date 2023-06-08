package com.example.mc_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mc_project.adapter.FriendAdapter
import com.example.mc_project.databinding.FriendListBinding
import com.example.mc_project.databinding.FriendpageBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendFragment: Fragment() {
    private var _binding : FriendListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View {

        var adapter = FriendAdapter(mutableListOf())
        return FriendpageBinding.inflate(inflater, container, false).root
    }
}