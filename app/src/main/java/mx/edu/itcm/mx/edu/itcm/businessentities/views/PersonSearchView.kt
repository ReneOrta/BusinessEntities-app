package mx.edu.itcm.mx.edu.itcm.businessentities.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(person) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            //Showing the name of the
            Text(
                text = person.businessentityid.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            //Showing the name of the store
            Text(
                text = person.personType,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = person.firstName+" "+person.lastName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PersonSearchView(){
    val businessEntitiesViewModel:BusinessEntitiesViewModel= viewModel()
    var query by remember{businessEntitiesViewModel.personQuery}
    val options: List<String> = listOf(
        "Person type",
        "First name",
        "Last name"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Row {

            personFilterDropDown( options, businessEntitiesViewModel)

            TextField(value = query ,
                onValueChange ={query=it}
            )

            IconButton(onClick = {
                if (businessEntitiesViewModel.personFilterOption.value == "Person type"){

                }else if (businessEntitiesViewModel.personFilterOption.value=="First name"){

                }else if (businessEntitiesViewModel.personFilterOption.value=="Last name"){

                }
            }) {
             Icon( Icons.Filled.Search , contentDescription =" Icono de busqueda" )
            }
        }
    }
}

@Composable
fun personFilterDropDown(options:List<String>,businessEntitiesViewModel: BusinessEntitiesViewModel){
    var isExpanded by remember { mutableStateOf(false) }
    var optionSelected by remember { businessEntitiesViewModel.personFilterOption }
    var optionText by remember { mutableStateOf("Filter options") }

    Box{
        Button(onClick = { isExpanded = true }) {
            Text(text = optionText)
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = null )
        }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 2.dp)) {
            for (option in options) {
                DropdownMenuItem(
                    text = { Text(option.toString()) },
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