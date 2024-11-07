package com.example.qlks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.NguoiDung
import com.example.qlks.R
import com.example.qlks.R.layout
import com.example.qlks.ThongTin

class DsAdapter(private val ds:ArrayList<NguoiDung>):RecyclerView.Adapter<DsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val txtTenPhong = findViewById<TextView>(R.id.txtTenPhong)
            txtTenPhong.text = ds[position].hoTen
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}