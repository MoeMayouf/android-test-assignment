package com.example.shacklehotelbuddy

sealed class AppScreen(val route: String) {
    object HotelSearchScreen : AppScreen("hotel_search")
    object PropertyListScreen : AppScreen("property_list")

}
