package mx.edu.itcm.mx.edu.itcm.businessentities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.BusinessEntities
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Vendor
import mx.edu.itcm.mx.edu.itcm.businessentities.services.personService
import mx.edu.itcm.mx.edu.itcm.businessentities.services.storeService
import mx.edu.itcm.mx.edu.itcm.businessentities.services.vendorService

class BusinessEntitiesViewModel : ViewModel() {

    private val _personState= mutableStateOf(PersonState())
    var personState: State<PersonState> = _personState
    val businessEntitiesView= BusinessEntities()

    val personView= Person()
    val storeView= Store()
    val vendorView= Vendor()


    //Person variables
    var personType=mutableStateOf("")//Person Type
    var personFrsName=mutableStateOf("")//Person First Name
    var personLstName=mutableStateOf("")//Person Last Name

    //Store Variables
    var storeName=mutableStateOf("")//Store Name

    //Vendor Variables
    var vendorAccNum=mutableStateOf("")//Vendor Acount Number
    var vendorName=mutableStateOf("")//Vendor Company Name

    suspend fun consultBE() {

    }

    data class PersonState(
            val loading: Boolean=false,
            val person:Person
            ? = null,
            val error: String? = null
            )

    //Business Entitites Registration Functions//

    //Function for Person Registration
    suspend fun registratePerson() {
        personView.p_type=personType.value
        personView.frs_name=personFrsName.value
        personView.lst_name=personLstName.value
        try{
            println("Trying person registration: ${personView.toString()}")
            personService.postPerson(personView)
        }catch(e: Exception){
            println("Person registration failed: ${e.message}")
        }
    }

    //Function for Store Registration
    suspend fun registrateStore() {
        storeView.name=storeName.value
        try{
            println("Trying store registration: ${storeView.toString()}")
            storeService.postStore(storeView)
        }catch(e: Exception){
            println("Store registration failed: ${e.message}")
        }
    }

    //Function for Vendor Registration
    suspend fun registrateVendor() {
        vendorView.acc_number=vendorAccNum.value
        vendorView.name=vendorName.value
        try{
            println("Trying vendor registration: ${vendorView.toString()}")
            vendorService.postVendor(vendorView)
        }catch(e: Exception){
            println("Vendor registration failed: ${e.message}")
        }
    }

}