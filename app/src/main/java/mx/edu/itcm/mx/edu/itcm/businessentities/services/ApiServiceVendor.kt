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
import retrofit2.http.PUT
import retrofit2.http.Query

private val retrofitVendor=Retrofit.Builder().baseUrl("http://192.168.1.95:8081/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val vendorService= retrofitVendor.create(ApiServiceVendor::class.java)

interface ApiServiceVendor{
    @POST("vendors")
    suspend fun postVendor(@Body vendor: Vendor?): Call<Vendor?>?

    @GET("vendors")
    suspend fun  getVendor():List<Vendor>

    @GET("vendors/vendor_account_number")
    suspend fun getVendorAccount (@Query("vendorAccountNumber") vendorAccountNumber : String): Vendor

    @GET("vendors/vendor_name")
    suspend fun getVendorName (@Query("vendorName") vendorName : String): List<Vendor>

    @PUT("vendors")
    suspend fun putVendors(@Body newVendor: Vendor?): Call<Vendor?>?
}