package com.pr.kotlin_bozoraka_app_test.Api

import com.pr.kotlin_bozoraka_app_test.Request.Login
import com.pr.kotlin_bozoraka_app_test.Response.LoginResponse
import com.pr.kotlin_bozoraka_app_test.Response.ProductsResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers("Content-Type: application/json")
    @POST("authenticate")
    fun authenticate(@Body login: Login):Call<LoginResponse>


    @GET("products")
    fun getProducts(@Header("Authorization") token:String):Call<List<ProductsResponse>>


    @GET("products/{id}")
    fun getProduct(
        @Header("Authorization") token:String,
        @Path("id") id:Int):Call<ProductsResponse>
}