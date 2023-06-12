package com.example.mc_project
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.contains
import androidx.fragment.app.Fragment
import com.example.mc_project.databinding.ActivityMapBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

import kotlin.streams.toList

class MapPage: Fragment() {
    private val ACCESS_FINE_LOCATION = 1000
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private val eventListener = MarkerEventListener()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMapBinding.inflate(inflater, container, false)
        binding.searchBtn.setOnClickListener {
            // 버튼 클릭 시 AppCompatActivity 페이지로 이동하는 코드 추가
            val intent = Intent(requireActivity(), SearchPlace::class.java)
            requireActivity().startActivity(intent)
        }
        mapView = MapView(context)
        binding.mapView.addView(mapView)
        //mapView.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))  // 커스텀 말풍선 등록
        mapView.setPOIItemEventListener(eventListener)
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        /**
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val latitude = userNowLocation?.latitude
        val longitude = userNowLocation?.longitude
        **/
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.580545, 126.922819), true)
        mapView.setZoomLevel(2, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)

        val db = FoodieDataBase.getInstance(requireContext())
        GlobalScope.launch(Dispatchers.IO) {
            val user = db!!.userDao().getUserWithTastePlaceByUser(1)
            val followersByUser = db!!.followDao().getFollowerList(1)
            val followerIds = followersByUser.stream().map{f-> f.followerId}.toList()
            followerIds.stream().forEach{ id ->
                val place = db!!.userDao().getUserWithTastePlaceByUser(id)
                place.tastePlaces.stream().forEach { place ->
                    Log.d("test",place.id.toString() + " " + place.name)
                    val customMarker = createMarker(place)
                    mapView.addPOIItem(customMarker)
                }
            }
            user.tastePlaces.stream().forEach { place ->
                Log.d("my",place.id.toString() + " " + place.name)
                val customMarker = createMarker(place)
                mapView.addPOIItem(customMarker)
            }
        }

        return binding.root
        }


    private fun createMarker(place: TastePlace) : MapPOIItem {
        val customMarker = MapPOIItem()
        customMarker.apply {
            itemName = place.name
            tag = place.id
            mapPoint = MapPoint.mapPointWithGeoCoord(place.latitude, place.longitude)
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.marker
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            customSelectedImageResourceId = R.drawable.marker
            isCustomImageAutoscale = false
            setCustomImageAnchor(0.5f, 1.0f)
        }
        return customMarker
    }

    class MarkerEventListener: MapView.POIItemEventListener {
        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {
            // 마커 클릭 시
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
            // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {
            // 말풍선 클릭 시
            val context = mapView!!.context
            val builder = AlertDialog.Builder(context)
            val id = poiItem!!.tag
            val db = FoodieDataBase.getInstance(context)

            GlobalScope.launch(Dispatchers.IO) {
                val tastePlace = db!!.tastePlaceDao().getTastePlace(id)
                val user = db!!.userDao().getUser(tastePlace.userId)
                withContext(Dispatchers.Main){
                    builder.setTitle("${poiItem?.itemName}")
                    builder.setMessage("${user.name}님이 등록했어요\n"+ "평점: ${tastePlace.rate}\n"+ "평가: ${tastePlace.content}")
                    builder.show()
                }
            }
        }

        override fun onDraggablePOIItemMoved(mapView: MapView?, poiItem: MapPOIItem?, mapPoint: MapPoint?) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }

}