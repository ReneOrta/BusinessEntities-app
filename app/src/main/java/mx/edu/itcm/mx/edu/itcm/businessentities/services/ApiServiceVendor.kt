package mx.edu.itcm.mx.edu.itcm.businessentities.services

import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Vendor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofitVendor=Retrofit.Builder().baseUrl("http://192.168.1.95:8081/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val vendorService= retrofitVendor.create(ApiServiceVendor::class.java)

interface ApiServiceVendor{
    @POST("vendors")
    suspend fun postVendor(@Body Vendor: Vendor?): Call<Vendor?>?
}