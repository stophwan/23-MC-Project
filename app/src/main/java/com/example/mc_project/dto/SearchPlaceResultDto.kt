package com.example.mc_project.dto

data class SearchPlaceResultDto (
    var meta: MetaData,
    var documents: List<PlaceData>
)

data class MetaData(
    var same_name: RegionInfo,
    var total_count: Int,
    var pageable_count: Int,
    var is_end: Boolean
)

data class RegionInfo(
    var region: List<String>,
    var keyword: String,
    var selected_region: String
)

data class PlaceData(
    var place_name: String,
    var distance: String,
    var place_url: String,
    var category_name: String,
    var road_address_name: String,
    var id: String,
    var phone: String,
    var x: String,
    var y: String,
    var category_group_code: String,
    var category_group_name: String,
)