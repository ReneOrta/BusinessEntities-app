package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel

@Composable
fun VendorBaseView(viewModel: BusinessEntitiesViewModel ){
    var accNumber by remember { viewModel.vendorAccNum}
    var name by remember {viewModel.vendorName}
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = accNumber,
        onValueChange = { accNumber=it },
        label = { Text("Account Number") },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = name,
        onValueChange = { name=it },
        label = { Text("Company Name") })
}

@Composable
fun VendorRegistrationView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel: BusinessEntitiesViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    Text(
        text = "Vendor Register",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
        fontSize =30.sp,
        fontWeight = FontWeight.Bold
        )
        VendorBaseView(viewModel = viewModel)

        Spacer(modifier = Modifier.height(16.dp))


            //Button to add a new vendor to the database
            Button(onClick = {
                if (!isValidVendorName(viewModel.vendorName.value)){
                    //Creating a Toast to give a warning to the user about the company's name format requirements
                    val nameWarning = Toast.makeText(
                        activity,
                        "The company name is required and its length must be less than 50 characters",
                        Toast.LENGTH_LONG
                    )
                    //Showing the warning toast to the user
                    nameWarning.show()
                }else if (!isValidAccNumber(viewModel.vendorAccNum.value)){
                    //Creating a Toast to give a warning to the user about the account number format requirements
                    val accWarning = Toast.makeText(
                        activity,
                        "The account number is required, its length must be 15 characters,\n" +
                                "and all letters entered must be uppercase.",
                        Toast.LENGTH_LONG
                    )
                    //Showing the warning toast to the user
                    accWarning.show()
                }else{
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.registrateVendor()
                }
                    //Creating a Toast to let know the user that the new vendor was succesfully registed
                    val confirmation = Toast.makeText(
                        activity,
                        "The vendor was successfully registered",
                        Toast.LENGTH_LONG
                    )
                    //Showing the register confirmation Toast
                    confirmation.show()
                }
            }) {
                Text(text = "Add Vendor") }

    }
}


@Composable
fun VendorUpdateView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel: BusinessEntitiesViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vendor Data Update",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontSize =30.sp,
            fontWeight = FontWeight.Bold
        )
        BusinessEntityID(viewModel = viewModel)//This functions is located in the BEMenuView.kt archive

        VendorBaseView(viewModel = viewModel)

        //Button to update the data of a Vendor
        Button(onClick = {

            if (!isValidID(viewModel.businesEntityID.value)){
                //Creating a Toast to give a warning to the user about the ID format
                val idWarning=Toast.makeText(
                    activity,
                    "The BusinessEntityID is required and must be an integer",
                    Toast.LENGTH_LONG
                )
                //Showing the warning to the user
                idWarning.show()
            }else if (!isValidVendorName(viewModel.vendorName.value)){
                //Creating a Toast to give a warning to the user about the company's name format requirements
                val nameWarning = Toast.makeText(
                    activity,
                    "The company name is required and its length must be less than 50 characters",
                    Toast.LENGTH_LONG
                )
                //Showing the warning toast to the user
                nameWarning.show()
            }else if (!isValidAccNumber(viewModel.vendorAccNum.value)){
                //Creating a Toast to give a warning to the user about the account number format requirements
                val accWarning = Toast.makeText(
                    activity,
                    "The account number is required and its length must be of 15 characters",
                    Toast.LENGTH_LONG
                )
                //Showing the warning toast to the user
                accWarning.show()
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updateVendor()
                }
                //Creating a Toast to let know the user that the new vendor was succesfully registed
                val confirmation = Toast.makeText(
                    activity,
                    "The data of the vendor was successfully updated",
                    Toast.LENGTH_LONG
                )
                //Showing the register confirmation Toast
                confirmation.show()
            }
        }) {
            Text(text = "Update Vendor") }
    }
}

fun isValidVendorName(name:String):Boolean{
    if (name != "" && name != null && name.length<=50)
        return true
    else
        return false
}

fun isValidAccNumber(accNumber: String): Boolean {
    if (accNumber.isNotEmpty() && accNumber.length <= 15 && accNumber.all { !it.isLetter() || it.isUpperCase() }) {
        return true
    }else{
        return false
    }

}