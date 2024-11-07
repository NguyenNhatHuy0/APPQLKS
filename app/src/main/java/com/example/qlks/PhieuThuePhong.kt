package com.example.qlks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qlks.databinding.ActivityPhieuThuePhongBinding
import com.example.qlks.model.KhachHang
import com.example.qlks.model.PhieuThue
import com.google.firebase.database.*

private lateinit var binding: ActivityPhieuThuePhongBinding
class PhieuThuePhong : AppCompatActivity() {
    private lateinit var data:DatabaseReference
    var idP : String?=null
    var ten : String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhieuThuePhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle!=null){
            val idphieu = bundle.getString("idPhieu")
            val tenKH = bundle.getString("ten")
//            Toast.makeText(this,"Id là ${idphieu}",Toast.LENGTH_SHORT).show()
            idP = idphieu
            ten =tenKH
        }
        sukien()

    }

    private fun sukien() {
        Home()
        ThanhToan()
    }
    fun Home(){
        binding.btnHome.setOnClickListener {
            val intent= Intent(this, TrangChu::class.java)
            startActivity(intent)
        }

    }


    private fun ThanhToan() {

//            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
            data = FirebaseDatabase.getInstance().getReference("PhieuThue")
            var ds = arrayListOf<PhieuThue>()
            val post = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for (i in snapshot.children){
                            val j = i.getValue(PhieuThue::class.java)
                            if (j!!.Id==idP){
                                ds.add(j!!)
                            }
                        }
                        binding.txtDen.setText(ds[0].Den.toString())
                        binding.txtTra.text = ds[0].Tra.toString()
                        binding.txtTrangThai.text = ds[0].TrangThai.toString()
                        binding.txtTien.text = ds[0].TongTien.plus("VNĐ")
                        binding.txtPhongSo.text=ds[0].IdPhong.toString()
                        binding.txtTenKhachHang.text = ten

                        binding.btnTraPhong.setOnClickListener {
                            CapNhatTinhTrang(ds[0].IdPhong.toString())
                            CapNhatTinhTrangTT(idP!!)
                        }
//                        Toast.makeText(this@PhieuThuePhong,"Số phần tử ${ds.size}",Toast.LENGTH_SHORT).show()


                    }
//
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            data.addValueEventListener(post)



    }
    fun CapNhatTinhTrang(Id:String){
       val data1 = FirebaseDatabase.getInstance().getReference("Phong").child(Id)
        data1.child("TinhTrang").setValue("Còn phòng")

    }
    fun CapNhatTinhTrangTT(Id:String){
        val data1 = FirebaseDatabase.getInstance().getReference("PhieuThue").child(Id)
        data1.child("trangThai").setValue("Đã thanh toán")
    }



}