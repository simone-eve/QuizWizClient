package com.example.quizwiz3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class IconAdapter(
    private val icons: List<Int>,
    private val onIconClick: (Int) -> Unit // Lambda instead of interface
) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_item, parent, false)
        return IconViewHolder(view)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.iconImageView.setImageResource(icons[position])
        holder.itemView.setOnClickListener {
            onIconClick(icons[position]) // Use the lambda to handle clicks
        }
    }

    override fun getItemCount(): Int {
        return icons.size
    }
}

