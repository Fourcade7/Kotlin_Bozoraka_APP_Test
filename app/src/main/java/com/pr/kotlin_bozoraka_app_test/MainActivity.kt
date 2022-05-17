package com.pr.kotlin_bozoraka_app_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pr.kotlin_bozoraka_app_test.Api.RetrofitClient
import com.pr.kotlin_bozoraka_app_test.Request.Login
import com.pr.kotlin_bozoraka_app_test.Response.LoginResponse
import com.pr.kotlin_bozoraka_app_test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            binding.button1.isEnabled=false
            binding.progressbar1.visibility= View.VISIBLE
            loginrequest()
        }
    }


    fun loginrequest(){
        val login=Login(binding.edittext1.text.toString(),binding.edittext2.text.toString())
        val call=RetrofitClient.getApi().authenticate(login)
        call.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    binding.progressbar1.visibility= View.INVISIBLE
                    binding.button1.isEnabled=true
                    val loginResponse=response.body()
                    RetrofitClient.token=loginResponse!!.token
                    startActivity(Intent(this@MainActivity,MainActivity2::class.java))


                    Toast.makeText(this@MainActivity,"Succes",Toast.LENGTH_SHORT).show()
                }else{
                    binding.button1.isEnabled=true
                    binding.progressbar1.visibility= View.INVISIBLE
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()


                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


}