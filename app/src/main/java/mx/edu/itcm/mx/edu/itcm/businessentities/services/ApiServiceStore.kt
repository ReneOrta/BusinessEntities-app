package mx.edu.itcm.mx.edu.itcm.businessentities.services
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Vendor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofitStore=Retrofit.Builder().baseUrl("http://192.168.1.68:8081/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val storeService= retrofitStore.create(ApiServiceStore::class.java)

interface ApiServiceStore{
    @POST("stores")
    suspend fun postStore(@Body store: Store?): Call<Store?>?

    @GET("stores")
    suspend fun  getStores():List<Store>

    @GET("stores/name")
    suspend fun getStoreName (@Query("storeName") name : String): List<Store>
}