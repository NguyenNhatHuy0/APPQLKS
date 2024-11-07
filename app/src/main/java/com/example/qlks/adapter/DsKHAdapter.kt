package com.example.qlks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.R
import com.example.qlks.model.KhachHang

class DsKHAdapter (private val ds:ArrayList<KhachHang>,val ClickKH : InterPhongAdapter): RecyclerView.Adapter<DsKHAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemkh,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val txtTen= findViewById<TextView>(R.id.txtHoTen)
            val txtSDT = findViewById<TextView>(R.id.txtSDT)
            val txtGioiTinh = findViewById<TextView>(R.id.txtGioiTinh)
            val img = findViewById<ImageView>(R.id.imageView10)
            img.setImageResource(R.drawable.user)
            txtTen.text = ds[position].TenKhachHang.toString()
            txtSDT.text=ds[position].SDT.toString()
            txtGioiTinh.text = ds[position].GioiTinh.toString()
        }
        holder.itemView.setOnClickListener {
            ClickKH.ClickKH(position)
        }

    }

    override fun getItemCount(): Int {
        return ds.size
    }
}