package mx.edu.itcm.mx.edu.itcm.businessentities.views
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination.*
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.NavBar
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.NavBarBody
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.NavigationHost
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.NavigationItem


// Función para menú desplegable de BusinessEntities
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BEMenu(){
    val items= listOf(
        NavigationItem(
            "Home",
            HomeScreeen.route,
            Icons.Filled.Home,
            Icons.Outlined.Home
        )
        ,NavigationItem(
                "Persons",
                PersonScreen.route,
                Icons.Filled.Person,
                Icons.Outlined.Person)
        , NavigationItem(
            "Stores",
            Destination.StoreScreen.route,
            Icons.Filled.ShoppingCart,
            Icons.Outlined.ShoppingCart)
        ,NavigationItem(
            "Vendors",
            Destination.VendorScreen.route,
            Icons.Filled.BusinessCenter,
            Icons.Outlined.BusinessCenter)
    )
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle =
        if (currentRoute != null){
            items[items.indexOfFirst {
                it.route == currentRoute
            }].title
        }else{
            items[0].title
        }
    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,drawerContent = {
            ModalDrawerSheet(

            ) {
                NavBar()
                Spacer(modifier = Modifier.height(8.dp))
                NavBarBody(items = items, currentRoute =currentRoute) { currentNavigationItem ->
                    if(currentNavigationItem.route == "share"){
                        Toast.makeText(context,"Share Clicked", Toast.LENGTH_LONG).show()
                    }else{
                        navController.navigate(currentNavigationItem.route){
                            navController.graph.startDestinationRoute?.let { startDestinationRoute ->
                                popUpTo(startDestinationRoute) {
                                    saveState = true
                                }
                            }
                            // Configure navigation to avoid multiple instances of the same destination
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }, drawerState = drawerState) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text ="Business Entities")
                }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "menu"
                        )
                    }
                })
            }
        ) {innerPadding->
            NavigationHost(navController = navController, innerPadding = innerPadding)
        }
    }
    }

@Preview(showBackground = true)
@Composable
fun Default2Preview(){
    BEMenu()
}