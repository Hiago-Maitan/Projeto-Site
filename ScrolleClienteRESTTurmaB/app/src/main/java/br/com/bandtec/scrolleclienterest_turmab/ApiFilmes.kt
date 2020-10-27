package br.com.bandtec.scrolleclienterest_turmab

import retrofit2.Call
import retrofit2.http.*

interface ApiFilmes {

    @GET("/filmes")
    fun getFilmes() : Call<List<Filme>>

    @GET("/filmes/{id}")
    fun getFilme(@Path("id") id:Int) : Call<Filme>

    @DELETE("/filmes/{id}")
    fun deleteFilme(@Path("id") id:Int) : Call<Void>

    @POST("/filmes")
    fun postFilme(@Body novoFilme: Filme) : Call<Void>

}