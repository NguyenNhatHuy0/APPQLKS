package com.example.qlks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.R
import com.example.qlks.model.LoaiPhong


class LoaiPhongAdapter(var ds: ArrayList<LoaiPhong>) : RecyclerView.Adapter<LoaiPhongAdapter.LoaiPhongViewHolder>(){
    inner class LoaiPhongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoaiPhongAdapter.LoaiPhongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loaiphong,parent,false)
        return LoaiPhongViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoaiPhongAdapter.LoaiPhongViewHolder, position: Int) {
        holder.itemView.apply {
            val txtLoaiPhong = findViewById<TextView>(R.id.txtLoaiPhong)
            val txtGiaTien = findViewById<TextView>(R.id.txtDonGia)


            txtLoaiPhong.text =ds[position].TenLoaiPhong
            txtGiaTien.text = ds[position].DonGia.toString().plus("đ")


        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}