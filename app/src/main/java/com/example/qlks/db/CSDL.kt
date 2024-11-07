package com.example.qlks.db

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.qlks.NguoiDung
import com.example.qlks.TrangChu

import com.example.qlks.model.KhachHang
import com.google.firebase.database.*

class CSDL {
    lateinit var data : DatabaseReference
    lateinit var ds : ArrayList<KhachHang>
    fun kh(){
        data = FirebaseDatabase.getInstance().getReference("KhachHang")
        ds = arrayListOf<KhachHang>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (i in dataSnapshot.children) {
                    val j = i.getValue(KhachHang::class.java)
                    ds.add(j!!)
                }
                Toast.makeText(null,"Đăng nhập thành công${ds.size}",Toast.LENGTH_SHORT).show()



//                binding.btnHoanThanh.setOnClickListener {
//                    if (KiemTra(ds) == true) {
//                        binding.txtThongbao.text = "Đăng nhập thành công!"
//                        val intent = Intent(this@DangNhap, TrangChu::class.java)
//                        startActivity(intent)
////                        Toast.makeText(this@DangNhap,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
//                    } else {
//                        binding.txtThongbao.text = "Sai ten đăng nhập hoặc mật khẩu!"
////                        Toast.makeText(this@DangNhap,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        data.addValueEventListener(postListener)

    }


}