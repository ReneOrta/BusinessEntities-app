package mx.edu.itcm.mx.edu.itcm.businessentities.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun VendorView(){
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