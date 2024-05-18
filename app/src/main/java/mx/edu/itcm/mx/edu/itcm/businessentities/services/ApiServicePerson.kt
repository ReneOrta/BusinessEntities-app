package mx.edu.itcm.mx.edu.itcm.businessentities.services

import mx.edu.itcm.mx.edu.itcm.businessentities.datasets.Person
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofitPerson=Retrofit.Builder().baseUrl("http://192.168.1.95:8081/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val personService= retrofitPerson.create(ApiServicePerson::class.java)

interface ApiServicePerson{
    @POST("persons")
    suspend fun postPerson(@Body person: Person?): Call<Person?>?

    @GET("persons/fname")
    suspend fun getPersonByFirstName(@Query("personFsNm") fname : String): Person

    @GET("persons/lname")
    suspend fun getPersonByLastName(@Query("personLstNm") lname : String): Person

    suspend fun getPersonByTyper (@Query("personType") personType : String): Person

}