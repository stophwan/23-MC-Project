package com.example.mc_project.fragment
import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.NETWORK_STATS_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mc_project.R
import com.example.mc_project.SearchPlaceActivity
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

class MapFragment: Fragment() {
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
            val intent = Intent(requireActivity(), SearchPlaceActivity::class.java)
            requireActivity().startActivity(intent)
        }
        var uLatitude = 37.580545
        var uLongitude = 126.922819


        mapView = MapView(activity)
        binding.mapView.addView(mapView)
        //mapView.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))  // 커스텀 말풍선 등록
        mapView.setPOIItemEventListener(eventListener)

        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        val locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(checkLocationPermission()){
            var userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            Log.d("s", userLocation!!.latitude.toString() + " " + userLocation!!.longitude.toString())
            uLatitude = userLocation!!.latitude
            uLongitude = userLocation!!.longitude
        }

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude), true)
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
    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun permissionCheck() {
        val preference = requireActivity().getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 권한 거절 (다시 한 번 물어봄)
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { dialog, which ->
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
                } else {
                    // 다시 묻지 않음 클릭 (앱 정보 화면으로 이동)
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${requireContext().packageName}"))
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        } else {
        }
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

        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {

        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {

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

        }
    }

}