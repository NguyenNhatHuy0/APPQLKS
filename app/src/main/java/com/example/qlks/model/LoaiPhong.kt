package com.example.qlks.model

import com.google.firebase.database.DatabaseReference

class LoaiPhong(
    var Id:String?=null,
    var TenLoaiPhong:String?=null,
    var DonGia:Int?=null
)
 {
     private lateinit var data : DatabaseReference
     private lateinit var ds : ArrayList<LoaiPhong>
     fun LayDaTa(){
         ds= arrayListOf<LoaiPhong>()

     }

    fun LayId():String?{
        return Id
    }
    fun LayDonGia(): Int? {
        return DonGia
    }
    fun LayTenLoaiPhong(): String? {
        return TenLoaiPhong
    }
}