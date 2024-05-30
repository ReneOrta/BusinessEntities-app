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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.BusinessEntitiesViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person


@Composable
fun PersonList(
    persons: List<Person>,
    onItemClick: (Person) -> Unit
) {
    LazyColumn {
        items(persons) { person ->
            PersonListItem(person = person, onItemClick = onItemClick)
        }
    }
}

@Composable
fun PersonListItem(
    person: Person,
    onItemClick: (Person) -> Unit
) {
    val viewModel:BusinessEntitiesViewModel= viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(person) }
            .padding(5.dp)
            .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        Column(Modifier.padding(15.dp)) {
            //Showing the name of the
            Text(
                text = person.businessEntityID.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            //Showing the name of the store
            Text(
                text = viewModel.inversePTypeFormat(person.personType),
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = person.firstName+" "+person.lastName,
                style = MaterialTheme.typography.bodyLarge,
                color= MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PersonSearchView(innerPadding: PaddingValues, activity: ComponentActivity) {
    val businessEntitiesViewModel: BusinessEntitiesViewModel = viewModel()
    var query by remember { businessEntitiesViewModel.personQuery }
    val options = listOf("Person type", "First name", "Last name")
    val personViewModel: BusinessEntitiesViewModel = viewModel()
    val personState by personViewModel.personState


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            personState.loading && personState.error == null -> {
                Row(Modifier.padding(4.dp)) {
                    personFilterDropDown(options = options, businessEntitiesViewModel = personViewModel)
                    TextField(
                        value = query,
                        onValueChange = { query = it },
                        label = { Text("Search") }
                    )
                    IconButton(
                        onClick = {
                            when (businessEntitiesViewModel.personFilterOption.value) {
                                "Person type" ->{
                                    CoroutineScope(Dispatchers.IO).launch {
                                        businessEntitiesViewModel.consultPersonType()
                                    }
                                }
                                "First name" -> {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        businessEntitiesViewModel.consultPersonFirstName()
                                    }
                                }
                                "Last name" -> {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        businessEntitiesViewModel.consultPersonLastName()
                                    }
                                }
                            }

                        }
                    ) {
                        Icon(Icons.Filled.Search, "Search Icon")
                    }
                }
                PersonList(
                    persons = personState.person,
                    onItemClick = { selectedPerson ->
                        Toast.makeText(activity, "Selected: ${selectedPerson.firstName} ${selectedPerson.lastName}", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            personState.error != null -> {
                Text("Fallo de comunicación con el servicio, intente más tarde")
                Toast.makeText(activity, "ERROR OCCURRED: ${personState.error}", Toast.LENGTH_LONG).show()
            }
            else -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun personFilterDropDown(options: List<String>, businessEntitiesViewModel: BusinessEntitiesViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    var optionSelected by remember { businessEntitiesViewModel.personFilterOption }
    var optionText by remember { mutableStateOf("Filter options") }

    Box {
        Button(onClick = { isExpanded = true }) {
            Text(text = optionText)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth().padding(all = 2.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        isExpanded = false
                        optionSelected = option
                        optionText = option
                    }
                )
            }
        }
    }
}