package com.example.qlks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ThongTinBan : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_tin_ban)
        dbRef = FirebaseDatabase.getInstance().getReference("Phong")

        val btnOk= findViewById<Button>(R.id.btnOk)

        btnOk.setOnClickListener {
            Ok()
        }
    }

    private fun Ok() {
        val edtTen = findViewById<EditText>(R.id.edtName).text.toString()
        val edtLop = findViewById<EditText>(R.id.edtLop).text.toString()
//        Toast.makeText(this,edtTen,Toast.LENGTH_SHORT).show()
//        Đẩy dữ liệu lên FireBase
        val ttid = dbRef.push().key!!
        val thongtin = ThongTin(ttid,edtTen,edtLop)
        dbRef.child(ttid).setValue(thongtin).addOnCompleteListener {
            Toast.makeText(this, "Thêm thành công",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {err->
            Toast.makeText(this, "Thêm lỗi ${err.message}", Toast.LENGTH_SHORT).show()
        }
   }
}