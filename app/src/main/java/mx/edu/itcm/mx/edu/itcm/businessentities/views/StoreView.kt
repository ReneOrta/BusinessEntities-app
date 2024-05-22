package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import kotlinx.coroutines.launch

@Composable
fun StoreView(innerPadding: PaddingValues, activity: ComponentActivity){
    val businessEntitiesViewModel: BusinessEntitiesViewModel = viewModel()
    var name by remember {businessEntitiesViewModel.storeName}
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = "Store registration")
       Spacer(modifier = Modifier.height(16.dp))
       TextField(
           value = name,
           onValueChange = { name=it },
           label = { Text("Store Name") }
       )
       Spacer(modifier = Modifier.height(16.dp))
        Row {
            //  Button to add a new Store to the database
            Button(onClick = {
                if (!isValidStoreName(name)){
                    //Creating a Toast to give a warning to the user
                    val warning = Toast.makeText(
                        activity,
                        "The name of the store is required and its length must be less than 50 characters",
                        Toast.LENGTH_LONG
                    )
                    //Showing the warning toast to the user
                    warning.show()
                }else{
                    CoroutineScope(Dispatchers.IO).launch {
                        businessEntitiesViewModel.registrateStore()
                    }
                    //Creating a Toast to let know the user that the store was succesfully registed
                    val confirmation = Toast.makeText(
                        activity,
                        "The store was successfully registered",
                        Toast.LENGTH_LONG
                    )
                    //Showing the register confirmation Toast
                    confirmation.show()
                }
            }) {
                Text(text = "Add Store")
            }
            //Button to consult a Store
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                  //  businessEntitiesViewModel.consultBE()
                }
            }) {
                Text(text = "Consult Store")
            }
        }

    }
}


private fun isValidStoreName(name:String):Boolean{
    if (name != "" && name != null && name.length<=50)
        return true
    else
        return false
}