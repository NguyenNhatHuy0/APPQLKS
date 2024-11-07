package com.example.qlks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.adapter.DsAdapter
import com.google.firebase.database.*

class DanhSachPhong : AppCompatActivity() {

    private lateinit var ds:ArrayList<NguoiDung>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_phong)

        val rvDs = findViewById<RecyclerView>(R.id.rvDs)
        rvDs.layoutManager = LinearLayoutManager(this)
        rvDs.setHasFixedSize(true)
         ds = arrayListOf<NguoiDung>()

   LayThongTin()

    }


     fun LayThongTin() {

         val rvDs = findViewById<RecyclerView>(R.id.rvDs)
         val txtLoadingdata = findViewById<TextView>(R.id.txtLoading)
         rvDs.visibility = View.GONE
         txtLoadingdata.visibility = View.VISIBLE
         dbRef = FirebaseDatabase.getInstance().getReference("NguoiDung")
         dbRef.addValueEventListener(object : ValueEventListener {
             override fun onDataChange(snapshot: DataSnapshot) {
//                snapshot chụp ảnh table
//                 ds.clear()
                 if (snapshot.exists()) {
                     for (i in snapshot.children) {
                         val j = i.getValue(NguoiDung::class.java)
                         ds.add(j!!)


                     }
                     val adapter = DsAdapter(ds)
                     rvDs.adapter = adapter
                     rvDs.visibility = View.VISIBLE
                     txtLoadingdata.visibility = View.GONE
                     Toast.makeText(this@DanhSachPhong,"Phần tử số 3 là: ${ds[2].hoTen}",Toast.LENGTH_SHORT).show()
                 }
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }
         })



     }
}