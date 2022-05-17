package com.pr.kotlin_bozoraka_app_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.pr.kotlin_bozoraka_app_test.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var image=intent.getStringExtra("image")
        var name=intent.getStringExtra("name")
        var description=intent.getStringExtra("description")
        var price=intent.getDoubleExtra("price",0.0)
        binding.textview4.setText("$name\n$description\n\nNarxi :$price")
        Glide.with(this).load(image).into(binding.imageview4)


    }
}