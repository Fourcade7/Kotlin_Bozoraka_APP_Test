package com.pr.kotlin_bozoraka_app_test.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pr.kotlin_bozoraka_app_test.R
import com.pr.kotlin_bozoraka_app_test.Response.ProductsResponse
import com.pr.kotlin_bozoraka_app_test.databinding.RecyclerviewItemBinding

class ProductsAdapter(
    val context:Context,
    val arraylist:ArrayList<ProductsResponse>
): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.binding.apply {
            textview1.setText(arraylist.get(position).name)
            Glide.with(context).load(arraylist.get(position).photoUrl).into(imageview1)

        }

    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    class ProductViewHolder(itemview:View): RecyclerView.ViewHolder(itemview){
        val binding=RecyclerviewItemBinding.bind(itemView)
    }
}