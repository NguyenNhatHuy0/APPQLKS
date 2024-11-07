package com.example.qlks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.qlks.databinding.ActivityThongTinKhachHangBinding
import com.example.qlks.model.KhachHang
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityThongTinKhachHangBinding
class ThongTinKhachHang : AppCompatActivity()
 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinKhachHangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sukienbtn()


    }

    private fun sukienbtn() {
        binding.btnPhieu.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("ten", binding.edtTen.text.toString())
            bundle.putString("idPhieu",intent.getStringExtra("id").toString())
            val intent = Intent(this,PhieuThuePhong::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
        binding.btnQuayLai.setOnClickListener {
            onBackPressed()
        }
//        cập nhật thông tin khách hàng
        binding.btnCapNhat.setOnClickListener {
//            CapNhatThongTin(
//                intent.getStringExtra("id").toString(),
//                binding.edtTen.toString(),
//                binding.edtSDT.toString(),
//                binding.edtDiaChi.toString(),
//                binding.edtGioiTinh.toString(),
//                intent.getStringExtra("cccd").toString(),
//                intent.getStringExtra("den").toString(),
//                intent.getStringExtra("tra").toString()
//
//            )
            Update(
                intent.getStringExtra("id").toString(),
                binding.edtTen.text.toString(),
                binding.edtSDT.text.toString(),
                binding.edtDiaChi.text.toString(),
                binding.edtGioiTinh.text.toString()
            )
        }

//        Xuất thông tin
        XemThongTin()
//        Xóa khách hàng
        binding.btnXoa.setOnClickListener {
            Xoa(
                intent.getStringExtra("id").toString()
            )
        }

    }

    private fun Update(id:String,ten: String,sdt:String,diachi: String,gioitinh: String) {
        val data = FirebaseDatabase.getInstance().getReference("KhachHang").child(id)
        data.child("tenKhachHang").setValue(ten)
        data.child("gioiTinh").setValue(gioitinh)
        data.child("diaChi").setValue(diachi)
        data.child("sdt").setValue(sdt)

     Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_SHORT).show()
        onBackPressed()
//      val intent = Intent(this,TrangChu::class.java)
//      startActivity(intent)




    }


    private fun XemThongTin() {
        binding.edtTen.setText(intent.getStringExtra("ten"))
        binding.edtSDT.setText(intent.getStringExtra("sdt"))
        binding.edtDiaChi.setText(intent.getStringExtra("diachi"))
        binding.edtGioiTinh.setText(intent.getStringExtra("gioitinh"))
    }

    private fun Xoa(id: String) {
       val data = FirebaseDatabase.getInstance().getReference("KhachHang").child(id)
        val i = data.removeValue()
        i.addOnSuccessListener {
//         val intent = Intent(this,TrangChu::class.java)
//         startActivity(intent)
            Toast.makeText(this,"Xóa thành công",Toast.LENGTH_LONG).show()
            onBackPressed()
        }.addOnFailureListener {err->
            Toast.makeText(this,"Lỗi ${err.message}",Toast.LENGTH_LONG).show()
        }


    }
}