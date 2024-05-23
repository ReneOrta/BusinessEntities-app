package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination.*
import mx.edu.itcm.mx.edu.itcm.businessentities.views.HomeView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonSearchView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreSearchView
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
        composable(Destination.PersonRegistrationScreen.route){
            PersonView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.PersonSearchScreen.route){
            PersonSearchView()
        }
        composable(Destination.StoreRegistrationScreen.route){
            StoreView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.StoreSearchScreen.route){
            StoreSearchView(innerPadding = innerPadding,activity = activity)
        }
        composable(Destination.VendorRegistrationScreen.route){
            VendorView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.HomeScreeen.route){
            HomeView(innerPadding = innerPadding)
        }
    }
}