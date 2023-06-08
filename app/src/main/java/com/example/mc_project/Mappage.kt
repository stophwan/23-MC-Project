package com.example.mc_project

import net.daum.mf.map.api.MapView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mc_project.databinding.ActivityMapBinding

class Mappage: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ActivityMapBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val mapView =  MapView(context)
        binding.mapView.addView(mapView)
        return binding.root
    }
}