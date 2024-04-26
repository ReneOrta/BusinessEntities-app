package mx.edu.itcm.mx.edu.itcm.businessentities

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

enum class ValoresRegistro {
    Indx, BE, Per, Stre, Vend;
}

@Composable
fun BEScreen(modifier: Modifier = Modifier) {
    var valoresRegistro by remember { mutableStateOf(ValoresRegistro.Indx) }
    //val businessEntitiesViewModel:BusinessEntitiesViewModel = viewModel()
    //var showOptions by remember { mutableStateOf(false) }
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Entidades de Negocios", style =MaterialTheme.typography.headlineLarge )
        Spacer(modifier = Modifier.height(16.dp))

        when (valoresRegistro) {
            ValoresRegistro.Indx -> {
                // Mostrar el botón principal "Registrar"
                Button(
                    onClick = { valoresRegistro = ValoresRegistro.BE},
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Registrar")
                }
            }
            ValoresRegistro.BE -> {
                // Mostrar opciones para elegir el tipo de entidad a registrar
                Text(text = "Selecciona el tipo de entidad a registrar")
                //Se escoje persona
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { valoresRegistro = ValoresRegistro.Per },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Registrar Persona")
                }
                //Se escoje Tienda
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { valoresRegistro = ValoresRegistro.Stre },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Registrar Tienda")
                }
                //Se escoje Proveedor
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { valoresRegistro = ValoresRegistro.Vend },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Registrar Proveedor")
                }
            }
            //Campos de registro para agregar una persona
            ValoresRegistro.Per -> {
                Text(text = "Registrar Persona")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor de PersonType */ },
                    label = { Text("Tipo de persona") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor de FirstName */ },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor de LastName */ },
                    label = { Text("Apellido") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Agregar persona")
                }
            }
            //Campos de registro para agregar una tienda
            ValoresRegistro.Stre -> {
                Text(text = "Registrar Tienda")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor de Nombre de la tienda */ },
                    label = { Text("Nombre de la tienda") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Agregar tienda")
                }
            }
            // Campos de registro para agregar un proveedor
            ValoresRegistro.Vend -> {
                Text(text = "Registrar Proveedor")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor de Número de cuenta */ },
                    label = { Text("Número de cuenta") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = remember { TextFieldValue() },
                    onValueChange = { /* Actualizar el valor del nombre de la compañía */ },
                    label = { Text("Nombre de la compañía") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Agregar proveedor")
                }
            }
        }

    }
}
