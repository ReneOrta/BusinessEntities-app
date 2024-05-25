package mx.edu.itcm.mx.edu.itcm.businessentities

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.BusinessEntities
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Vendor
import mx.edu.itcm.mx.edu.itcm.businessentities.services.personService
import mx.edu.itcm.mx.edu.itcm.businessentities.services.storeService
import mx.edu.itcm.mx.edu.itcm.businessentities.services.vendorService

class BusinessEntitiesViewModel : ViewModel() {

    val businessEntitiesView= BusinessEntities()
    private var debounceJob: Job? = null

    //Person variables
    val personView= Person()
    private val _personState= mutableStateOf(PersonState())
    var personState: State<PersonState> = _personState
    var personType=mutableStateOf("")//Person Type
    var personFrsName=mutableStateOf("")//Person First Name
    var personLstName=mutableStateOf("")//Person Last Name
    var personFilterOption= mutableStateOf("")
    var personQuery= mutableStateOf("")

    //Store Variables
    val storeView= Store()
    private val _storeState=mutableStateOf(StoreState())
    var storeState: State<StoreState> = _storeState
    var storeName=mutableStateOf("")//Store Name
    var storeQuery= mutableStateOf("")

    //Vendor Variables
    val vendorView= Vendor()
    private val _vendorState= mutableStateOf(VendorState())
    var vendorState:State<VendorState> = _vendorState
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

     fun fetchStores(){
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
                _storeState.value=_storeState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }
    }

     fun fetchVendors(){
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
    //Function for person's consult using the Person Type as a filter
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun consultPersonType(){
        if(personQuery.value!= ""){
            try {
                val response= personService.getPersonByType(personQuery.value)
                _personState.value=_personState.value.copy(
                    person=response,
                    loading=false,
                    error=null
                )
            }catch (e: Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }
    }
    //Function for person's consult using the Person FirstName as a filter
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun consultPersonFirstName(){
        if(personQuery.value!= ""){
            try {
                val response= personService.getPersonByFirstName(personQuery.value)
                _personState.value=_personState.value.copy(
                    person=response,
                    loading=false,
                    error=null
                )
            }catch (e: Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }
    }
    //Function for person's consult using the Person LastName as a filter
    suspend fun consultPersonLastName(){
        if(personQuery.value!= ""){
            try {
                val response= personService.getPersonByLastName(personQuery.value)
                _personState.value=_personState.value.copy(
                    person=response,
                    loading=false,
                    error=null
                )
            }catch (e: Exception){
                _personState.value=_personState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
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
    //Function for consult the a Store using the store's name as a filter
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun consultStoreName() {
        if(storeQuery.value!= ""){
            try{
                val response= storeService.getStoreName(storeQuery.value)
                _storeState.value=_storeState.value.copy(
                    store=response,
                    loading=false,
                    error=null
                )
                println("Stores fetched: ${response.toString()}")
            }catch (e: Exception){
                _storeState.value=_storeState.value.copy(
                    loading = false,
                    error="Error fetching Stores ${e.message}"
                )
                println("stores fetch failed: ${e.message}")
            }
        }
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
    //Function for vendor consult using the account number as a filter
    suspend fun consultVendorAccount() {
        if (vendorQuery.value != "") {
            try {
                val response = vendorService.getVendorAccount(vendorQuery.value)
                _vendorState.value = _vendorState.value.copy(
                    vendor = listOf(response),
                    loading = false,
                    error = null
                )
                println("vendors fetched")
            } catch (e: Exception) {
                _vendorState.value = _vendorState.value.copy(
                    loading = false,
                    error = "Error fetching Vendors ${e.message}"
                )
                println("vendors fetch failed: ${e.message}")
            }
        }
    }

    //Function for vendor consult using the Company's Name as a filter
    suspend fun  consultVendorCompanyName(){
        if(vendorQuery.value!= ""){
            try {
                val response = vendorService.getVendorName(vendorQuery.value)
                _vendorState.value = _vendorState.value.copy(
                    vendor = response,
                    loading = false,
                    error = null
                )
                println("vendors fetched")
            } catch (e: Exception) {
                _vendorState.value = _vendorState.value.copy(
                    loading = false,
                    error = "Error fetching Vendors ${e.message}"
                )
                println("vendors fetch failed: ${e.message}")
            }
        }
    }


    fun fetchEntityDebounced() {
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            delay(300) // 300ms debounce time
            fetchStores()
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

    fun inversePTypeFormat(personType:String):String{
        var pType:String=""
        if (personType=="SCStore Contact"){
            pType="Store Contact"
        }else if (personType=="IN"){
            pType="Individual Customer"
        }else if(personType=="SP"){
            pType="Sales Person"
        }else if(personType=="EM"){
            pType="Employe"
        }else if (personType=="VC"){
            pType="Vendor Contact"
        }else if (personType=="GC"){
            pType="General Contact"
        }
        return pType
    }


}
