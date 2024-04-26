package mx.edu.itcm.mx.edu.itcm.businessentities

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofitVendor=Retrofit.Builder().baseUrl("")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val vendorService= retrofitVendor.create(ApiServiceVendor::class.java)

interface ApiServiceVendor{
    @POST("vendor")
    suspend fun postPerson(@Body Vendor: Vendor?): Call<Vendor?>?
}