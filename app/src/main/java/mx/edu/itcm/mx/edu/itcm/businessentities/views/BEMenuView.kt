package mx.edu.itcm.mx.edu.itcm.businessentities.views
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.NavigationHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BEMenu(activity: ComponentActivity) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val sections = listOf("Home", "Persons", "Stores", "Vendors")
    val subItems = listOf("Registration", "Search", "Modifications")
    var selectedSection by remember { mutableStateOf<String?>(null) }

    val sectionIcons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Person,
        Icons.Filled.ShoppingCart,
        Icons.Filled.BusinessCenter
    )

    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    sections.forEachIndexed { index, section ->
                        TextButton(
                            onClick = {
                                if (section == "Home") {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate(Destination.HomeScreeen.route)
                                    }
                                } else {
                                    selectedSection = if (selectedSection == section) null else section
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = sectionIcons[index],
                                    contentDescription = section,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(text = section)
                            }
                        }

                        if (selectedSection == section && section != "Home") {
                            subItems.forEach { subItem ->
                                TextButton(
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        when (section) {
                                            "Persons" -> when (subItem) {
                                                "Registration" ->navController.navigate(Destination.PersonRegistrationScreen.route)
                                                "Search" -> navController.navigate(Destination.PersonSearchScreen.route)
                                                "Modifications" -> navController.navigate(Destination.PersonUpdateScreen.route)
                                            }
                                            "Stores" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.StoreRegistrationScreen.route)
                                                "Search" -> navController.navigate(Destination.StoreSearchScreen.route)
                                                "Modifications" -> navController.navigate(Destination.StoreUpdateScreen.route)
                                            }
                                            "Vendors" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.VendorRegistrationScreen.route)
                                                "Search" -> navController.navigate(Destination.VendorSearchScreen.route)
                                                "Modifications" -> navController.navigate(Destination.VendorUpdateScreen.route)
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        //.padding(start = 32.dp) // Padding increased for sub-items
                                ) {
                                    Text(text = subItem)
                                }
                            }
                        }
                    }
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Text(
                                    text = "Adventure Works",
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        navigationIcon = {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = null)
                                }
                            }
                        },
                        modifier = Modifier.height(25.dp) // Adjust the height of the TopAppBar
                    )
                }
                ,
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationHost(navController = navController, innerPadding = innerPadding, activity = activity)
                    }
                }
            )
        }
    )
}

@Composable
fun BusinessEntityID(viewModel: BusinessEntitiesViewModel){
    var id by remember {viewModel.businesEntityID}
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value =id ,
        onValueChange ={id=it},
        label = { Text("Business Entity ID") })
}

fun isValidID(id:String):Boolean{
    if( id != "" && id.toIntOrNull() != null)
        return true
    else
        return false
}