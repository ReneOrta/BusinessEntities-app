package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination.*
import mx.edu.itcm.mx.edu.itcm.businessentities.views.HomeView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.VendorView

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    activity: ComponentActivity
){
    NavHost(navController =navController,
        startDestination =Destination.HomeScreeen.route) {
        composable(Destination.PersonScreen.route){
            PersonView(innerPadding = innerPadding)
        }
        composable(Destination.StoreScreen.route){
            StoreView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.VendorScreen.route){
            VendorView(innerPadding = innerPadding)
        }
        composable(Destination.HomeScreeen.route){
            HomeView(innerPadding = innerPadding)
        }
    }
}