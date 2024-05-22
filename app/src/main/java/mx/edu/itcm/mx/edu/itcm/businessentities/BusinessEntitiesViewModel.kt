package mx.edu.itcm.mx.edu.itcm.businessentities

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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
    private val _storeState=mutableStateOf(StoreState())
    var storeState: State<StoreState> = _storeState
    private val _vendorState= mutableStateOf(VendorState())
    var vendorState:State<VendorState> = _vendorState
    val businessEntitiesView= BusinessEntities()

    val personView= Person()
    val storeView= Store()
    val vendorView= Vendor()


    //Person variables
    var personType=mutableStateOf("")//Person Type
    var personFrsName=mutableStateOf("")//Person First Name
    var personLstName=mutableStateOf("")//Person Last Name
    var personFilterOption= mutableStateOf("")
    var personQuery= mutableStateOf("")

    //Store Variables
    var storeName=mutableStateOf("")//Store Name

    //Vendor Variables
    var vendorAccNum=mutableStateOf("")//Vendor Acount Number
    var vendorName=mutableStateOf("")//Vendor Company Name
    var vendorFilterOption= mutableStateOf("")
    var vendorQuery= mutableStateOf("")

    init {
        fetchPersons()
        println("fetching persons")
        fetchStores()
        println("fetching stores")
        fetchVendors()
        println("fetching vendors")
    }

    private fun fetchPersons() {
        viewModelScope.launch{
            try{
                val response= personService.getPersons()
                _personState.value=_personState.value.copy(
                    person=response,
                    loading=false,
                    error=null
                )
                println("persons fetched")
            }catch (e:Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Persons ${e.message}"
                )
                println("persons fetch failed: ${e.message}")
            }
        }
    }

    private fun fetchStores(){
        viewModelScope.launch{
            try{
                val response= storeService.getStores()
                _storeState.value=_storeState.value.copy(
                    store=response,
                    loading=false,
                    error=null
                )
                println("stores fetched")
            }catch (e:Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }
    }

    private fun fetchVendors(){
        viewModelScope.launch{
            try{
                val response= vendorService.getVendor()
                _vendorState.value=_vendorState.value.copy(
                    vendor=response,
                    loading=false,
                    error=null
                )
                println("vendors fetched")
            }catch (e:Exception){
                _vendorState.value=_vendorState.value.copy(
                    loading = false,
                    error="Error fetching Vendors ${e.message}"
                )
                println("vendors fetch failed: ${e.message}")
            }
        }
    }


    data class PersonState(
            val loading: Boolean=false,
            val person: List<Person> = emptyList(),
            val error: String? = null
            )

    data class StoreState(
        val loading: Boolean=false,
        val store: List<Store> = emptyList(),
        val error: String? = null
    )

    data class VendorState(
        val loading: Boolean=false,
        val vendor:List<Vendor> = emptyList(),
        val error: String? = null
    )


    //Function for Person Registration
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun registratePerson() {
        personView.personType=pTypeFormat(personType.value)
        personView.firstName=personFrsName.value
        personView.lastName=personLstName.value
        try{
            println("Trying person registration: ${personView.toString()}")
            personService.postPerson(personView)
        }catch(e: Exception){
            println("Person registration failed: ${e.message}")
        }
    }

    //Function for Store Registration
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun registrateStore() {
        storeView.name=storeName.value
        try{
            println("Trying store registration: ${storeView.toString()}")
            storeService.postStore(storeView)
        }catch(e: Exception){
            println("Store registration failed: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun  consultStore(){
        if(storeState.value != null){
            try{
                val response= storeService.getStores()
                _storeState.value=_storeState.value.copy(
                    store=response,
                    loading=false,
                    error=null
                )
                println("stores fetched")
            }catch (e:Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun consultStoreName() {

    }

    //Function for Vendor Registration
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun registrateVendor() {
        vendorView.accountNumber=vendorAccNum.value
        vendorView.name=vendorName.value
        try{
            println("Trying vendor registration: ${vendorView.toString()}")
            vendorService.postVendor(vendorView)
        }catch(e: Exception){
            println("Vendor registration failed: ${e.message}")
        }
    }


    fun pTypeFormat(personType:String):String{
        var pType:String=""
        if (personType=="Store Contact"){
            pType="SC"
        }else if (personType=="Individual Customer"){
            pType="IN"
        }else if(personType=="Sales Person"){
            pType="SP"
        }else if(personType=="Employe"){
            pType="EM"
        }else if (personType=="Vendor Contact"){
            pType="VC"
        }else if (personType=="GeneralContact"){
            pType="GC"
        }
        return pType
    }

}