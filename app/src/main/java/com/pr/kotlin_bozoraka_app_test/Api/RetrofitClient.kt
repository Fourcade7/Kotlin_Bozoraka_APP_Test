package com.pr.kotlin_bozoraka_app_test.Api

import com.pr.kotlin_bozoraka_app_test.Request.Login
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getApi():Api{
        val retrofit=Retrofit.Builder()
            .baseUrl("https://valixon.bexatobot.uz/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api= retrofit.create(Api::class.java)
        return api
    }

}