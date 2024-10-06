package com.example.aquariumtestapp.navigation

sealed class Screens (val screen: String ) {

     object HomeScreen : Screens("home")
     object HistoryScreen : Screens("history")
     object ShopScreen : Screens("shop")
     object AccountScreen : Screens("parameter")
}