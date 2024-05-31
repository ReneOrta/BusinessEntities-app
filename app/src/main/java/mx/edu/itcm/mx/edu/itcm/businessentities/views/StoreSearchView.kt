package mx.edu.itcm.mx.edu.itcm.businessentities.views
import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import mx.edu.itcm.mx.edu.itcm.businessentities.navigation.Destination
import mx.edu.itcm.mx.edu.itcm.bussinesentities.R

@Composable
fun StoreList(
    stores: List<Store>,
    onItemClick: (Store) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(stores) { store ->
            StoreListItem(store = store, onItemClick = onItemClick)
        }
    }
}

@Composable
fun StoreListItem(
    store: Store,
    onItemClick: (Store) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(store) }
            .padding(5.dp)
            .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Column {
            //Showing the ID of the store
            Text(
                text ="BusinessEntityID:"+store.businessEntityID.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            //Showing the name of the store
            Text(
                text ="Name:"+store.name,
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun StoreSearchView(innerPadding: PaddingValues, activity: ComponentActivity) {
    val storeViewModel: BusinessEntitiesViewModel = viewModel()
    val storeState by storeViewModel.storeState
    val searchText = remember { mutableStateOf("") }
    var query by remember {storeViewModel.storeQuery}
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        when {
            !storeState.loading && storeState.error == null -> {
                Row(Modifier.padding(4.dp)) {
                    TextField(
                        value = query,
                        onValueChange = {
                            query = it
                            if (query.isEmpty()) {
                                storeViewModel.fetchStores()
                            } else {
                                storeViewModel.fetchEntityDebounced()
                            }
                        },
                        label = { Text("Search") }
                    )
                    IconButton(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                storeViewModel.consultStoreName()
                            }
                        }) {
                        Icon(Icons.Filled.Search, "Icono de búsqueda")
                    }
                }
                StoreList(
                    stores = storeState.store,
                    onItemClick = { selectedStore ->
                        Toast.makeText(activity, "Selected: ${selectedStore.name}", Toast.LENGTH_SHORT).show()
                        /*storeViewModel.businesEntityID.value = selectedStore.businessEntityID.toString()
                        storeViewModel.storeName.value = selectedStore.name
                        navController.navigate(Destination.StoreUpdateScreen.route)*/

                    }
                )
            }
            storeState.error != null -> {
                Text("Fallo de comunicación con el servicio, intente más tarde")
                val toast = Toast.makeText(
                    activity,
                    "ERROR OCCURRED: ${storeState.error}",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
