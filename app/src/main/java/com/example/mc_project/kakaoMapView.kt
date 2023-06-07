package com.example.mc_project

import android.R
import android.location.LocationManager
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mc_project.databinding.ActivityMapBinding


class kakaoMapView :AppCompatActivity() {  // 지도 위치 마커 관련 코드 입니다.
    lateinit var binding: ActivityMapBinding
    private val MapKey = "854a3cb7f6c6e19da914dd436b1b7627"
    private lateinit var mapView: MapView
    private lateinit var lm: LocationManager  //위치 관련 작업 수행

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMapBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView = MapView(this)

        val mapViewContainer = binding.mapView as ViewGroup  // 공식 문서 자바 코드 수정
        mapViewContainer.addView(binding.mapView)

        // 중심점 변경
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(7, true);

        // 중심점 변경 + 줌 레벨 변경
        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);

        // 줌 인
        mapView.zoomIn(true);

        // 줌 아웃
        mapView.zoomOut(true);
/*
        val customMarker = MapPOIItem()
        customMarker.setItemName("Custom Marker")
        customMarker.setTag(1)
        customMarker.setMapPoint(MARKER_POINT)
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage) // 마커타입을 커스텀 마커로 지정.

        customMarker.setCustomImageResourceId(R.drawable.alert_dark_frame) // 마커 이미지.

        customMarker.setCustomImageAutoscale(false)
        // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.

        customMarker.setCustomImageAnchor(0.5f, 1.0f)
        // 마커 이미지 중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.

        mapView.addPOIItem(customMarker)
 */

    }
    fun MapPOIItem()  {

    }

}