package mx.edu.itcm.mx.edu.itcm.businessentities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import mx.edu.itcm.mx.edu.itcm.businessentities.ui.theme.BussinesEntitiesTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import mx.edu.itcm.mx.edu.itcm.businessentities.views.BEMenu


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BussinesEntitiesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BEMenu(activity = this)
                }
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BussinesEntitiesTheme {
        BEMenu(activity = this)
    }
}*/


