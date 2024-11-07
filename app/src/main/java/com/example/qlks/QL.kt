package com.example.qlks

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.adapter.DsAdapter
import com.google.firebase.database.*

class QL {
    private lateinit var dbRef: DatabaseReference
    private lateinit var ds:ArrayList<NguoiDung>

//fun Test():ArrayList<NguoiDung>{
//    val ds = arrayListOf<NguoiDung>()
//    val a= NguoiDung("","Diệp","diep","1234")
//    ds.add(a)
//    return ds
//}
   fun DSND(): ArrayList<NguoiDung>{


       val ds = arrayListOf<NguoiDung>()
       dbRef = FirebaseDatabase.getInstance().getReference("NguoiDung")
       dbRef.addValueEventListener(object : ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
//                snapshot chụp ảnh table
               ds.clear()
               if (snapshot.exists()){
                   for (i in snapshot.children){
                       val j = i.getValue(NguoiDung::class.java)
                       ds.add(j!!)

                   }


               }
           }

           override fun onCancelled(error: DatabaseError) {
               TODO("Not yet implemented")
           }
       })
       return ds



   }
}