package com.example.qlks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.R
import com.example.qlks.model.Phong

class PhongAdapter(var ds: ArrayList<Phong>, val ClickPhong : InterPhongAdapter) : RecyclerView.Adapter<PhongAdapter.PhongViewHolder>(){
    inner class PhongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phong,parent,false)
        return PhongViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhongViewHolder, position: Int) {
        holder.itemView.apply {
            val txttenPhong = findViewById<TextView>(R.id.txtLoaiPhong)
            val txtTinhTrang = findViewById<TextView>(R.id.txtTinhTrang)

            txttenPhong.text=ds[position].TenPhong
            txtTinhTrang.text=ds[position].TinhTrang

            holder.itemView.setOnClickListener {
                ClickPhong.ClickPhong(position)
            }


        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}