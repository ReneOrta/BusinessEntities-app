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
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonUpdateView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.PersonView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreRegistrationView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreSearchView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreRegistrationView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.StoreUpdateView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.VendorSearchView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.VendorRegistrationView
import mx.edu.itcm.mx.edu.itcm.businessentities.views.VendorUpdateView

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
            PersonSearchView(innerPadding = innerPadding,activity = activity)
        }
        composable(Destination.PersonUpdateScreen.route){
            PersonUpdateView(innerPadding = innerPadding, activity =activity)
        }
        composable(Destination.StoreRegistrationScreen.route){
            StoreRegistrationView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.StoreSearchScreen.route){
            StoreSearchView(innerPadding = innerPadding,activity = activity)
        }
        composable(Destination.StoreUpdateScreen.route){
            StoreUpdateView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.VendorRegistrationScreen.route){
            VendorRegistrationView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.VendorSearchScreen.route){
            VendorSearchView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.VendorUpdateScreen.route){
            VendorUpdateView(innerPadding = innerPadding, activity = activity)
        }
        composable(Destination.HomeScreeen.route){
            HomeView(innerPadding = innerPadding)
        }
    }
}