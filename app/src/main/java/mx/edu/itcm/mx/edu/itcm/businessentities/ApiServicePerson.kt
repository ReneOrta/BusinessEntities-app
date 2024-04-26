package mx.edu.itcm.mx.edu.itcm.businessentities

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofitPerson=Retrofit.Builder().baseUrl("")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val personService= retrofitPerson.create(ApiServicePerson::class.java)

interface ApiServicePerson{
    @POST("person")
    suspend fun postPerson(@Body person: Person?): Call<Person?>?
}