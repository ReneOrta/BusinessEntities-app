package mx.edu.itcm.mx.edu.itcm.businessentities.views
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

/*
// Función para menú desplegable de BusinessEntities
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BEMenu(activity: ComponentActivity){
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
    val selections=listOf("Home", "Persons", "Stores", "Vendors")
    val subItems = listOf("Registration", "Search", "Modifications")
    var selectedSection by remember { mutableStateOf<String?>(null) }

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
            NavigationHost(navController = navController, innerPadding = innerPadding,activity)
        }
    }
    }*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BEMenu(activity: ComponentActivity) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val sections = listOf("Home", "Persons", "Stores", "Vendors")
    val subItems = listOf("Registration", "Search", "Modifications")
    var selectedSection by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    sections.forEach { section ->
                        TextButton(
                            onClick = {
                                if (section == "Home") {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate(Destination.HomeScreeen.route)
                                    }
                                }
                                else {
                                    selectedSection = if (selectedSection == section) null else section
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = section)
                        }

                        if (selectedSection == section && section != "Home") {
                            subItems.forEach { subItem ->
                                TextButton(
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        when (section) {
                                            "Persons" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.PersonScreen.route)
                                                "Search" -> navController.navigate("persons_consultas")
                                                "Modifications" -> navController.navigate("persons_modificaciones")
                                            }
                                            "Stores" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.StoreScreen.route)
                                                "Search" -> navController.navigate("stores_consultas")
                                                "Modifications" -> navController.navigate("stores_modificaciones")
                                            }
                                            "Vendors" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.VendorScreen.route)
                                                "Search" -> navController.navigate("vendors_consultas")
                                                "Modifications" -> navController.navigate("vendors_modificaciones")
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp)
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
                        title = { Text("Business Entities") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationHost(navController = navController, innerPadding = innerPadding, activity = activity)
                    }
                }
            )
        }
    )
}*/

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
                                                "Registration" -> navController.navigate(Destination.PersonRegistrationScreen.route)
                                                "Search" -> navController.navigate("persons_consultas")
                                                "Modifications" -> navController.navigate("persons_modificaciones")
                                            }
                                            "Stores" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.StoreRegistrationScreen.route)
                                                "Search" -> navController.navigate(Destination.StoreSearchScreen.route)
                                                "Modifications" -> navController.navigate("stores_modificaciones")
                                            }
                                            "Vendors" -> when (subItem) {
                                                "Registration" -> navController.navigate(Destination.VendorRegistrationScreen.route)
                                                "Search" -> navController.navigate("vendors_consultas")
                                                "Modifications" -> navController.navigate("vendors_modificaciones")
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
