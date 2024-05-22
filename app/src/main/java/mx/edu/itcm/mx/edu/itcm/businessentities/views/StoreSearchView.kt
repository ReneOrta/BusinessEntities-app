package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store

@Composable
fun StoreList(
    stores: List<Store>,
    onItemClick: (Store) -> Unit
) {
    LazyColumn {
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(store) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            //Showing the name of the
            Text(
                text = store.businessEntityID.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            //Showing the name of the store
            Text(
                text = store.name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StoreSearchView(activity: ComponentActivity) {
    val storeViewModel: BusinessEntitiesViewModel= viewModel()
    val storeState by storeViewModel.storeState
    val searchText = remember { mutableStateOf("") }
    val filteredStores = storeState.store.filter{
        it.name.contains(searchText.value, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row {
            OutlinedTextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search,"Icono de busqueda" )
            }
        }

        StoreList(
            stores =
            if(searchText.value.toString()==""){
                storeState.store
            }else{
                filteredStores
            },
            onItemClick = { selectedStore ->
                Toast.makeText(activity, "Selected: ${selectedStore.name}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
