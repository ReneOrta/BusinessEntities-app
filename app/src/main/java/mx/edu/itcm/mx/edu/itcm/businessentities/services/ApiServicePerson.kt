package mx.edu.itcm.mx.edu.itcm.businessentities.services

import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofitPerson=Retrofit.Builder().baseUrl("")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val personService= retrofitPerson.create(ApiServicePerson::class.java)

interface ApiServicePerson{
    @POST("person")
    suspend fun postPerson(@Body person: Person?): Call<Person?>?
}