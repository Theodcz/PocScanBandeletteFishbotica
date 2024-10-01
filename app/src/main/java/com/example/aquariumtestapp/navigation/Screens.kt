package com.example.aquariumtestapp.navigation

sealed class Screens (val screen: String ) {

     object HomeScreen : Screens("home")
     object HistoryScreen : Screens("history")
     object CameraScreen : Screens("camera") // à voir si c'est ok pour lancer l'activité ...
     object ShopScreen : Screens("shop")
     object AccountScreen : Screens("parameter")
}