package com.example.mc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mc_project.databinding.ActivityMainBinding
import com.example.mc_project.databinding.FriendpageBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.friendbtn.setOnClickListener { supportFragmentManager.beginTransaction()

        }
    }
}