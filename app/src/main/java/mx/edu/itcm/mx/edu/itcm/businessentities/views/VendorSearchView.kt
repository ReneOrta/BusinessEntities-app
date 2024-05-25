package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Vendor

@Composable
fun VendorList(
    vendors: List<Vendor>,
    onItemClick: (Vendor) -> Unit
) {
    LazyColumn {
        items(vendors) { vendor ->
            VendorListItem(vendor = vendor, onItemClick = onItemClick)
        }
    }
}

@Composable
fun VendorListItem(
    vendor: Vendor,
    onItemClick: (Vendor) -> Unit
) {
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(vendor) }
            //.padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Column{
            //Showing the id
            Text(
                text = "BusinessEntityID:"+vendor.businessEntityID.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            //Showing the vendor Account Number
            Text(
                text = "Account Number:"+vendor.accountNumber,
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            //Showing the vendor company's name
            Text(
                text ="Company's name:"+vendor.name,
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun VendorSearchView(innerPadding: PaddingValues, activity: ComponentActivity) {
    val vendorViewModel: BusinessEntitiesViewModel = viewModel()
    val vendorState by vendorViewModel.vendorState
    var query by remember { vendorViewModel.vendorQuery }
    var filterOption by remember { vendorViewModel.vendorFilterOption }
    val options: List<String> = listOf(
        "Account number",
        "Company's name"
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        when {
            !vendorState.loading && vendorState.error == null -> {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    vendorFilterDropDown(
                        options = options,
                        businessEntitiesViewModel = vendorViewModel,
                        modifier = Modifier.weight(1f)
                    )
                    TextField(
                        value = query,
                        onValueChange = { query = it
                            if (query.isEmpty()) {
                                vendorViewModel.fetchVendors()
                            }
                                        },
                        label = { Text("Search") },
                        modifier = Modifier.weight(2f)
                    )
                    IconButton(
                        onClick = {
                            when (filterOption) {
                                "Account number" -> {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        vendorViewModel.consultVendorAccount()
                                    }
                                }
                                "Company's name" -> {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        vendorViewModel.consultVendorCompanyName()
                                    }
                                }
                            }
                        },
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Icon(Icons.Filled.Search, contentDescription = "Icono de búsqueda")
                    }
                }
                VendorList(
                    vendors = vendorState.vendor,
                    onItemClick = { selectedVendor ->
                        Toast.makeText(activity, "Selected: ${selectedVendor.accountNumber}", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            vendorState.error != null -> {
                Text("Fallo de comunicación con el servicio, intente más tarde")
                val toast = Toast.makeText(
                    activity,
                    "ERROR OCCURRED: ${vendorState.error}",
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

@Composable
fun vendorFilterDropDown(options: List<String>, businessEntitiesViewModel: BusinessEntitiesViewModel, modifier: Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var optionSelected by remember { businessEntitiesViewModel.vendorFilterOption }
    var optionText by remember { mutableStateOf("Filter options") }

    Box {
        Button(onClick = { isExpanded = true }) {
            Text(text = optionText)
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }, modifier = Modifier.fillMaxWidth()) {
            for (option in options) {
                DropdownMenuItem(
                    text = {
                        Text(
                            option.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        isExpanded = false
                        optionSelected = option.toString()
                        optionText = option.toString()
                    }
                )
            }
            println("${options.size} types")
            Log.i("INFO_DEBUG", "${options.size} types")
        }
    }
}