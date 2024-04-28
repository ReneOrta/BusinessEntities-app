package mx.edu.itcm.mx.edu.itcm.businessentities.services


import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofitStore=Retrofit.Builder().baseUrl("")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val storeService= retrofitStore.create(ApiServiceStore::class.java)

interface ApiServiceStore{
    @POST("person")
    suspend fun postPerson(@Body sotore: Store?): Call<Store?>?
}