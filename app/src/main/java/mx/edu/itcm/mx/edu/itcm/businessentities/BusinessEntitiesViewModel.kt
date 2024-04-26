package mx.edu.itcm.mx.edu.itcm.businessentities

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BusinessEntitiesViewModel : ViewModel() {

    val businessEntitiesView= BusinessEntities()
    var contactName = mutableStateOf("")


    suspend fun consultarBE() {

    }

    suspend fun registrarBE() {

    }

}