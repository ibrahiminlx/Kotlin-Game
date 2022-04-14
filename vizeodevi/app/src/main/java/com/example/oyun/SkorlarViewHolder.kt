package com.example.oyun

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class SkorlarViewHolder(container: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate
            (
            R.layout.activity_detay,
            container,
            false
        )
    ) {
    val crdView: CardView = itemView.findViewById(R.id.cardview1)
    val oyunModuPhoto: ImageView = itemView.findViewById(R.id.kolayfoto)
    val detaySkor: TextView = itemView.findViewById(R.id.detayscore)
    val detayHamle: TextView = itemView.findViewById(R.id.detayhamle)
    val detayZaman: TextView = itemView.findViewById(R.id.detaysure)


    fun bind(skor1: skorlar) {

        oyunModuPhoto.setImageResource(skor1.oyunModuPhoto)
        detaySkor.text = skor1.detaySkor.toString()
        detayHamle.text = skor1.detayHamle.toString()
        detayZaman.text = skor1.detayZaman.toString()
    }
}