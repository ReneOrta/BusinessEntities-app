package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import kotlinx.coroutines.launch

@Composable
fun PersonBaseView(viewModel: BusinessEntitiesViewModel){
    var frsName by remember {viewModel.personFrsName}
    var lstName by remember {viewModel.personLstName}
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value =frsName,
        onValueChange = { frsName=it },
        label = { Text("First Name") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = lstName,
        onValueChange = { lstName=it },
        label = { Text("Last Name") }
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun PersonView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel:BusinessEntitiesViewModel= viewModel()

    val types: List<String> = listOf(
        "Store Contact",//Store contact
        "Individual Customer",//Individual customer
        "Sales Person",//Sales person
        "Employe",//Employe
        "Vendor Contact",//Vendor contact
        "General Contact"//General contact
    )
Column(
    modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
        .background(Color.White),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = "Person Register",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
        fontSize =30.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(16.dp))
    createPersonTypeDropDown(types,viewModel)
    PersonBaseView(viewModel = viewModel)

        //Button to add a new person to the database
        Button(onClick ={
            if (!isValidPersonType(viewModel.personType.value)){
                //Creating a Toast to give a warning to the user about the Person Type format
                val pTypeWarning=Toast.makeText(
                    activity,
                    "You need to choose the Type of the person",
                    Toast.LENGTH_LONG
                )
                //Showing the warning to the user
                pTypeWarning.show()
            }
            else if (!isValidPersonName(viewModel.personFrsName.value)){
            //Creating a Toast to give a warning to the user about the person's first name format requirements
            val frsNameWarning = Toast.makeText(
                activity,
                "The Person's first name is required and its length must be less than 50 characters",
                Toast.LENGTH_LONG
            )
            //Showing the warning toast to the user
            frsNameWarning.show()
        }else if (!isValidPersonName(viewModel.personLstName.value)){
            //Creating a Toast to give a warning to the user about the person's last name format requirements
            val lstNameWarning = Toast.makeText(
                activity,
                "The Person's Last name is required and its length must be less than 50 characters",
                Toast.LENGTH_LONG
            )
            //Showing the warning toast to the user
            lstNameWarning.show()
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.registratePerson()
            }
            //Creating a Toast to let know the user that the new person was succesfully registed
            val confirmation = Toast.makeText(
                activity,
                "The person was successfully registered",
                Toast.LENGTH_LONG
            )
            //Showing the register confirmation Toast
            confirmation.show()
        }
    }) {
            Text(text = "Add Person")
        }
}
}

@Composable
fun PersonUpdateView(innerPadding: PaddingValues, activity: ComponentActivity){
    val viewModel:BusinessEntitiesViewModel= viewModel()

    val types: List<String> = listOf(
        "Store Contact",//Store contact
        "Individual Customer",//Individual customer
        "Sales Person",//Sales person
        "Employe",//Employe
        "Vendor Contact",//Vendor contact
        "General Contact"//General contact
    )
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Person Data Update",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontSize =30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        BusinessEntityID(viewModel = viewModel)//This functions is located in the BEMenuView.kt archive
        createPersonTypeDropDown(types,viewModel)
        PersonBaseView(viewModel = viewModel)

        //Button to add a new person to the database
        Button(onClick ={
            if (!isValidPersonType(viewModel.personType.value)){
                //Creating a Toast to give a warning to the user about the Person Type format
                val pTypeWarning=Toast.makeText(
                    activity,
                    "You need to choose the Type of the person",
                    Toast.LENGTH_LONG
                )
                //Showing the warning to the user
                pTypeWarning.show()
            }
            else if (!isValidID(viewModel.businesEntityID.value)){
                //Creating a Toast to give a warning to the user about the ID format
                val idWarning=Toast.makeText(
                    activity,
                    "The BusinessEntityID is required and must be an integer",
                    Toast.LENGTH_LONG
                )
                //Showing the warning to the user
                idWarning.show()
            } else if (!isValidPersonName(viewModel.personFrsName.value)){
                //Creating a Toast to give a warning to the user about the person's first name format requirements
                val frsNameWarning = Toast.makeText(
                    activity,
                    "The Person's first name is required and its length must be less than 50 characters",
                    Toast.LENGTH_LONG
                )
                //Showing the warning toast to the user
                frsNameWarning.show()
            }else if (!isValidPersonName(viewModel.personLstName.value)){
                //Creating a Toast to give a warning to the user about the person's last name format requirements
                val lstNameWarning = Toast.makeText(
                    activity,
                    "The Person's Last name is required and its length must be less than 50 characters",
                    Toast.LENGTH_LONG
                )
                //Showing the warning toast to the user
                lstNameWarning.show()
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updatePerson()
                }
                //Creating a Toast to let know the user that the new person was succesfully registed
                val confirmation = Toast.makeText(
                    activity,
                    "The person was successfully updated",
                    Toast.LENGTH_LONG
                )
                //Showing the register confirmation Toast
                confirmation.show()
            }
        }) {
            Text(text = "Update Person")
        }

    }
}

@Composable
fun createPersonTypeDropDown(types:List<String>,businessEntitiesViewModel: BusinessEntitiesViewModel){
var isExpanded by remember { mutableStateOf(false) }
var typeSelected by remember { businessEntitiesViewModel.personType }
var typesText by remember { mutableStateOf("Person types") }

Box{
    Button(onClick = { isExpanded = true }) {
        Text(text = typesText)
        Icon(Icons.Default.ArrowDropDown,
            contentDescription = null )
    }
    DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }, modifier = Modifier
        .fillMaxWidth()
        .padding(all = 2.dp)) {
        for (type in types) {
            DropdownMenuItem(
                text = { Text(type.toString()) },
                onClick = {
                    isExpanded = false
                    typeSelected = type.toString()
                    typesText = type.toString()
                }
            )
        }
        println("${types.size} types")
        Log.i("INFO_DEBUG", "${types.size} types")
    }
}

}

 fun isValidPersonType(pType:String):Boolean{
    if(pType != "" && pType != null &&pType!= "Person types")
        return true
    else
        return false
}

 fun isValidPersonName(name:String):Boolean{
    if (name != "" && name != null && name.length<=50)
        return true
    else
        return false
}
