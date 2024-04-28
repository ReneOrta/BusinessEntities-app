package mx.edu.itcm.mx.edu.itcm.businessentities.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun StoreView(innerPadding: PaddingValues){
    Column(
        modifier = Modifier.padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = "Registrar Tienda")
       Spacer(modifier = Modifier.height(16.dp))
       TextField(
           value = remember { TextFieldValue() },
           onValueChange = { /* Actualizar el valor de Nombre de la tienda */ },
           label = { Text("Store Name") }
       )
       Spacer(modifier = Modifier.height(16.dp))
       Button(onClick = { /*TODO*/ }) {
           Text(text = "Add Store")
       }
    }
}