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

private val retrofitVendor=Retrofit.Builder().baseUrl("http://192.168.1.68:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val vendorService= retrofitVendor.create(ApiServiceVendor::class.java)

interface ApiServiceVendor{
    @POST("vendors")
    suspend fun postVendor(@Body vendor: Vendor?): Call<Vendor?>?

    @GET("vendors")
    suspend fun  getVendor():List<Vendor>

    @GET("vendors/vendor-account-number")
    suspend fun getVendorAccount (@Query("accountNumber") accountNumber : String): Vendor

    @GET("vendors/vendor-name")
    suspend fun getVendorName (@Query("name") name : String): List<Vendor>
}