package com.example.mc_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mc_project.adapter.TestMypageAdapter
import com.example.mc_project.databinding.MypageBinding

class Mypage : Fragment() {
    private var _binding: MypageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MypageBinding.inflate(inflater, container, false)
        val view = binding.root

        val dataset = arrayOf("하나", "둘", "셋")
        val adapter = TestMypageAdapter(dataset)

        binding.reMylist.layoutManager = LinearLayoutManager(requireContext())
        binding.reMylist.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
