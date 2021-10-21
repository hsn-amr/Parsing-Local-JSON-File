package com.example.parsinglocaljsonfile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*

class RV(val images: ArrayList<String>, val c: Context): RecyclerView.Adapter<RV.ItemViewHolder>() {
    class ItemViewHolder(item: View): RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val image = images[position]

        holder.itemView.apply {
            Glide.with(c)
                .load(image)
                .into(imageView)
        }
    }

    override fun getItemCount() = images.size
}