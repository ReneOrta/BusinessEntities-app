package mx.edu.itcm.mx.edu.itcm.businessentities

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BusinessEntitiesViewModel : ViewModel() {
    enum class ValoresRegistro {
        Indx, BE, Per, Stre, Vend;
    }

    val businessEntitiesView= BusinessEntities()
    val personView= Person()
    val storeView=Store()
    val vendorView=Vendor()

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

    //Business Entitites Registration Functions//

    //Function for Person Registration
    suspend fun registratePerson() {
        personView.p_type=personType.value
        personView.frs_name=personFrsName.value
        personView.lst_name=personLstName.value
        try{
            println("Trying person registration: ${personView.toString()}")
            //businessEntitiesService.postPerson(personView)
        }catch(e: Exception){
            println("Person fetched failed: ${e.message}")
        }
    }

    //Function for Store Registration
    suspend fun registrateStore() {
        storeView.name=storeName.value
        try{
            println("Trying store registration: ${storeView.toString()}")
        }catch(e: Exception){
            println("Store fetched failed: ${e.message}")
        }
    }

    //Function for Vendor Registration
    suspend fun registrateVendor() {
        //vendorView.acc_number=vendorAccNum.value
        vendorView.name=vendorName.value
        try{
            println("Trying vendor registration: ${vendorView.toString()}")
        }catch(e: Exception){
            println("Vendor fetched failed: ${e.message}")
        }
    }

}