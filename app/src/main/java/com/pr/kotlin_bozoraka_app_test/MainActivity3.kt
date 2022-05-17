package com.pr.kotlin_bozoraka_app_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.pr.kotlin_bozoraka_app_test.Api.RetrofitClient
import com.pr.kotlin_bozoraka_app_test.Response.ProductsResponse
import com.pr.kotlin_bozoraka_app_test.databinding.ActivityMain3Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var binding:ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            if (!binding.edittext3.text.isEmpty()){
                getproduct()
            }

        }


    }

    fun getproduct(){
        binding.progeressbar3.visibility= View.VISIBLE
        val call= RetrofitClient.getApi().getProduct("Token ${RetrofitClient.token}",binding.edittext3.text.toString().toInt())
        call.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful){
                    binding.cardview1.visibility= View.VISIBLE
                    binding.progeressbar3.visibility= View.GONE
                    val productsResponse=response.body()
                    binding.textview3.setText("${productsResponse!!.name}\n${productsResponse!!.description}\n\nNarxi :${productsResponse!!.price}")
                    Glide.with(this@MainActivity3).load(productsResponse.photoUrl).into(binding.imageview3)
                }else{
                    binding.progeressbar3.visibility= View.GONE
                    binding.cardview1.visibility= View.INVISIBLE
                    Toast.makeText(this@MainActivity3,"Error", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity3,"Error $t", Toast.LENGTH_SHORT).show()

            }
        })
    }
}