package mx.edu.itcm.mx.edu.itcm.businessentities.services


import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Store
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofitStore=Retrofit.Builder().baseUrl("http://172.16.188.41")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val storeService= retrofitStore.create(ApiServiceStore::class.java)

interface ApiServiceStore{
    @POST("stores")
    suspend fun postStore(@Body sotore: Store?): Call<Store?>?
}