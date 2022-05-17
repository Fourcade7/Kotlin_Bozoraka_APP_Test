package com.pr.kotlin_bozoraka_app_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.pr.kotlin_bozoraka_app_test.Adapters.ProductsAdapter
import com.pr.kotlin_bozoraka_app_test.Api.RetrofitClient
import com.pr.kotlin_bozoraka_app_test.Response.ProductsResponse
import com.pr.kotlin_bozoraka_app_test.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    var arrayList=ArrayList<ProductsResponse>()
    lateinit var binding:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        getProducts()
        binding.progeressbar2.visibility=View.VISIBLE



    }
    fun getProducts(){
        val call=RetrofitClient.getApi().getProducts("Token ${RetrofitClient.token}")
        call.enqueue(object : Callback<List<ProductsResponse>> {
            override fun onResponse(
                call: Call<List<ProductsResponse>>,
                response: Response<List<ProductsResponse>>
            ) {
               if (response.isSuccessful){
                   binding.progeressbar2.visibility=View.INVISIBLE

                   arrayList= response.body() as ArrayList<ProductsResponse>
                   val productsAdapter=ProductsAdapter(this@MainActivity2,arrayList)
                   binding.recyclerview1.apply {
                       layoutManager=GridLayoutManager(this@MainActivity2,2)
                       adapter=productsAdapter
                   }

               }else{
                   binding.progeressbar2.visibility=View.INVISIBLE
                   Toast.makeText(this@MainActivity2,"Error", Toast.LENGTH_SHORT).show()

               }
            }

            override fun onFailure(call: Call<List<ProductsResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity2,"Error $t", Toast.LENGTH_SHORT).show()

            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1->startActivity(Intent(this@MainActivity2,MainActivity3::class.java))

        }
        return super.onOptionsItemSelected(item)
    }
}