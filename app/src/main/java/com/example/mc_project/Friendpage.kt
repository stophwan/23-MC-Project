package com.example.mc_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.mc_project.databinding.FriendpageBinding

class Friendpage: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View {
        return FriendpageBinding.inflate(inflater, container, false).root
    }
}