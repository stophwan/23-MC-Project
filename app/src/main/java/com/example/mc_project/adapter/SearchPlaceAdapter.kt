package com.example.mc_project.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_project.EvalPage
import com.example.mc_project.databinding.SearchListBinding
import com.example.mc_project.SearchList
import com.example.mc_project.SearchPlace
import com.example.mc_project.databinding.SearchBinding
import com.example.mc_project.dto.PlaceData
import com.example.mc_project.dto.SearchPlaceResultDto

class SearchPlaceAdapter(private var dataSet: MutableList<PlaceData>): RecyclerView.Adapter<SearchPlaceAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: SearchListBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun setList(newList: MutableList<PlaceData>) {
        this.dataSet = newList
        notifyDataSetChanged()
        Log.d("setList",dataSet.toString())
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("count", dataSet.size.toString())
        val binding = (holder as SearchPlaceAdapter.MyViewHolder).binding
        binding.restname.text = dataSet[position].place_name
        binding.location.text = dataSet[position].road_address_name


        binding.submitBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, EvalPage::class.java)

            intent.putExtra("place_name", dataSet[position].place_name)
            intent.putExtra("distance", dataSet[position].distance)
            intent.putExtra("place_url", dataSet[position].place_url)
            intent.putExtra("category_name", dataSet[position].category_name)
            intent.putExtra("road_address_name", dataSet[position].road_address_name)
            intent.putExtra("id", dataSet[position].id)
            intent.putExtra("phone", dataSet[position].phone)
            intent.putExtra("x", dataSet[position].x)
            intent.putExtra("y", dataSet[position].y)
            intent.putExtra("category_group_code", dataSet[position].category_group_code)
            intent.putExtra("category_group_name", dataSet[position].category_group_name)
            Log.d("intent", intent.putExtra("place_name", dataSet[position].place_name).toString())

            holder.itemView.context.startActivity(intent)
        }
    }
}