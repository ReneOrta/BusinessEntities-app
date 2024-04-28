package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination.*
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.VendorView

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(navController =navController,
        startDestination =Destination.PersonScreen.route) {
        composable(Destination.PersonScreen.route){
            PersonView(innerPadding = innerPadding)
        }
        composable(Destination.StoreScreen.route){
            StoreView(innerPadding = innerPadding)
        }
        composable(Destination.VendorScreen.route){
            VendorView(innerPadding = innerPadding)
        }
    }
}