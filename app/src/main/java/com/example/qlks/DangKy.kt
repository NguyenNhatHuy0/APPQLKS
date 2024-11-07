package com.example.qlks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qlks.databinding.ActivityDangKyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.math.BigInteger
import java.security.MessageDigest
import java.util.zip.Inflater

private lateinit var binding: ActivityDangKyBinding
class DangKy : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDangKyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbRef = FirebaseDatabase.getInstance().getReference("NguoiDung")

        HoanThanh()

    }
//    fun md5(input:String): String {
//        val md = MessageDigest.getInstance("MD5")
//        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
//    }

    private fun HoanThanh() {
        binding.btnHoanThanhDk.setOnClickListener {
             val hoTen = binding.edtHoTen.text.toString()
            val tenDangNhap = binding.edtTenDangNhap.text.toString()
            val matKhau = binding.edtMatKhau.text.toString()
            val id = dbRef.push().key!!
            val nguoiDung = NguoiDung(id,hoTen,tenDangNhap,MaHoa().md5(matKhau))

            dbRef.child(id).setValue(nguoiDung).addOnCompleteListener {
                Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DangNhap::class.java)
                startActivity(intent)
            }.addOnFailureListener {err->
                Toast.makeText(this, "Không thành công ${err.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}