package mx.edu.itcm.mx.edu.itcm.businessentities.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel

@Composable
fun VendorView(innerPadding: PaddingValues){
    val businessEntitiesViewModel: BusinessEntitiesViewModel = viewModel()
    var accNumber by remember { businessEntitiesViewModel.vendorAccNum}
    var name by remember {businessEntitiesViewModel.vendorName}
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    Text(text = "Vendor")
    Spacer(modifier = Modifier.height(16.dp))
        TextField(
        value = accNumber,
        onValueChange = { accNumber=it },
        label = { Text("Account Number") })

    Spacer(modifier = Modifier.height(16.dp))
        TextField(
        value = name,
        onValueChange = { name=it },
        label = { Text("Company Name") })

    Spacer(modifier = Modifier.height(16.dp))
        Row{
            //Button to add a new vendor to the database
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    businessEntitiesViewModel.registrateVendor()
                }
            }) {
                Text(text = "Add Vendor") }
            //Button to consult a vendor
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    businessEntitiesViewModel.consultBE()
                }
            }) {
                Text(text = "Consult Vendor")
            }
        }
    }

}