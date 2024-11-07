package com.example.qlks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.qlks.databinding.ActivityDangNhapBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityDangNhapBinding
class DangNhap : AppCompatActivity() {
    private lateinit var data : DatabaseReference
    private lateinit var ds : ArrayList<NguoiDung>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangNhapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = FirebaseDatabase.getInstance().getReference("NguoiDung")
        ds = arrayListOf<NguoiDung>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (i in dataSnapshot.children) {
                    val j = i.getValue(NguoiDung::class.java)
                    ds.add(j!!)
                }


                binding.btnHoanThanh.setOnClickListener {
                    if (KiemTra(ds) == true) {
                        binding.edtTenDangNhap.text = null
                        binding.edtMatKhau.text = null
                        binding.txtThongbao.text = "Đăng nhập thành công! Chào admin"
                        val intent = Intent(this@DangNhap, TrangChu::class.java)
                        startActivity(intent)
//                        Toast.makeText(this@DangNhap,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
                    } else {
                        binding.txtThongbao.text = "Sai ten đăng nhập hoặc mật khẩu!"
//                        Toast.makeText(this@DangNhap,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        data.addValueEventListener(postListener)
//        HoanThanh()

    }

//    private fun HoanThanh() {
//
//        binding.btnHoanThanh.setOnClickListener {
////            val tenDangNhap = binding.edtTenDangNhap.text.toString()
////            val matKhau = MaHoa().md5(binding.edtMatKhau.text.toString())
////
//
//
//
//        }
//    }
    fun KiemTra(ds:ArrayList<NguoiDung>):Boolean{
        val tenDangNhap = binding.edtTenDangNhap.text.toString()
        val matKhau = MaHoa().md5(binding.edtMatKhau.text.toString())
        for (i in 0..ds.size-1){
            if (ds[i].tenDangNhap==tenDangNhap && ds[i].matKhau==matKhau){

    //            Toast.makeText(this@DangNhap,"Đăng nhập thành công_ Chào ${ds[i].hoTen}",Toast.LENGTH_SHORT).show()
    //            val intent = Intent(this@DangNhap, DanhSachPhong::class.java)
    //            startActivity(intent)
                return true
            }
        }
        return false
        }


}