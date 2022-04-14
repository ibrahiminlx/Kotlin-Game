package com.example.oyun

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class skorAdapter(val skorListesi: List<skorlar>) :
    RecyclerView.Adapter<SkorlarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkorlarViewHolder {
        return SkorlarViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return skorListesi.size
    }

    override fun onBindViewHolder(holder: SkorlarViewHolder, position: Int) {
        holder.bind(skorListesi[position])

    }
}