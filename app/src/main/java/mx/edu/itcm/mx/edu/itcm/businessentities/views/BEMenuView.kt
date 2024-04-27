package mx.edu.itcm.mx.edu.itcm.businessentities.views


import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Función para menú desplegable de BusinessEntities
@Preview
@Composable
fun BEMenu(){
    //variable para controlar el despliegue del menú
    var expanded by remember{ mutableStateOf(false) }
    //Lista de opciones a desplegar en el menú
    val options= listOf(
        "Persons",
        "Stores",
        "Vendors"
    )
    
    IconButton(onClick = {expanded=!expanded}) {

            Icon(Icons.Filled.Menu, contentDescription ="Menú")

    }

    DropdownMenu(expanded =true,
        onDismissRequest = {expanded=false}) {

        options.forEach{option->
            DropdownMenuItem(text = { Text(text = option)}, onClick = { /*TODO*/ })
        }

        }
    }