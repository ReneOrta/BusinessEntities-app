package mx.edu.itcm.mx.edu.itcm.businessentities.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Preview
@Composable
fun PersonView(){
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
