package com.example.qlks.model

import android.widget.Toast
import com.google.firebase.database.*

class Phong(
    var Id : String?=null,
    var TenPhong : String?=null,
    var MaLoaiPhong : String?=null,
    var GhiChu:String?=null,
    var TinhTrang:String?=null,
    var DonGia:String?=null
) {
    private lateinit var data : DatabaseReference
    private lateinit var ds : ArrayList<Phong>
    fun LayDaTa(){
        data = FirebaseDatabase.getInstance().getReference("Phong")
        ds = arrayListOf<Phong>()

        val post = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val j = i.getValue(Phong::class.java)
                        ds.add(j!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

    }

    fun LayTenPhong(ds:ArrayList<Phong>,vitri:Int):String?{
               return ds[vitri].TenPhong
    }
}