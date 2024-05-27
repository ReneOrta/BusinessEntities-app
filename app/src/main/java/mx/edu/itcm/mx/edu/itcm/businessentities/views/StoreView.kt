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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import kotlinx.coroutines.launch


@Composable
fun StoreBaseView(viewModel: BusinessEntitiesViewModel){
    var name by remember {viewModel.storeName}
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = name,
        onValueChange = { name=it },
        label = { Text("Store Name") }
    )
}

@Composable
fun StoreRegistrationView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel: BusinessEntitiesViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
       Text(
           text = "Store Register",
           color = MaterialTheme.colorScheme.primary,
           style = MaterialTheme.typography.titleLarge,
           fontSize =30.sp,
           fontWeight = FontWeight.Bold
       )
       StoreBaseView(viewModel = viewModel)

            //  Button to add a new Store to the database
            Button(onClick = {
                if (!isValidStoreName(viewModel.storeName.value)){
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
                        viewModel.registrateStore()
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
                Text(
                    text = "Add Store",
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }


@Composable
fun StoreUpdateView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel: BusinessEntitiesViewModel = viewModel()
    var id by remember {viewModel.businesEntityID}
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Store Data Update",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontSize =30.sp,
            fontWeight = FontWeight.Bold
        )
        BusinessEntityID(viewModel = viewModel)//This functions is located in the BEMenuView.kt archive
        StoreBaseView(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))

            //  Button to add a new Store to the database
            Button(onClick = {
                if (!isValidID(id)) {
                    //Creating a Toast to give a warning to the user about the ID format
                    val idWarning=Toast.makeText(
                        activity,
                        "The BusinessEntityID is required and must be an integer",
                        Toast.LENGTH_LONG
                    )
                    //Showing the warning to the user
                    idWarning.show()
                }
                else if (!isValidStoreName(viewModel.storeName.value)){
                    //Creating a Toast to give a warning to the user about the name format
                    val nameWarning = Toast.makeText(
                        activity,
                        "The name of the store is required and its length must be less than 50 characters",
                        Toast.LENGTH_LONG
                    )
                    //Showing the warning toast to the user
                    nameWarning.show()
                }
                else {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.updateStore()
                    }
                    //Creating a Toast to let know the user that the store was succesfully registed
                    val confirmation = Toast.makeText(
                        activity,
                        "The store was successfully updated",
                        Toast.LENGTH_LONG
                    )
                    //Showing the register confirmation Toast
                    confirmation.show()
                }
            }) {
                Text(
                    text = "Update Store",
                    fontWeight = FontWeight.Bold
                )
            }
        }
}




fun isValidStoreName(name:String):Boolean{
    if (name != "" && name != null && name.length<=50)
        return true
    else
        return false
}