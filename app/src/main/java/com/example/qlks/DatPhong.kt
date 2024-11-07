package com.example.qlks

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.qlks.databinding.ActivityDatPhongBinding
import com.example.qlks.model.KhachHang
import com.example.qlks.model.PhieuThue
import com.example.qlks.model.Phong
import com.google.firebase.database.*
import java.util.Calendar

private lateinit var binding:ActivityDatPhongBinding
class DatPhong : AppCompatActivity() {
    private lateinit var data : DatabaseReference
    var arr : ArrayList<Phong>?= null
    var vitri : Int?=null
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bundle = intent.extras
       if (bundle!=null){
            vitri = bundle.getInt("pos")
       }

        dulieu()


        SuKien()



    }
    private fun LayTenPhong(a:String){
        supportActionBar!!.title = "Thông tin đặt phòng số ${a}"
    }


    private fun dulieu(){
       data = FirebaseDatabase.getInstance().getReference("Phong")
        var huhu = arrayListOf<Phong>()
        val post = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val j = i.getValue(Phong::class.java)
                        huhu.add(j!!)
                    }
//                    Xử lý với dữ liêu
                    binding.txtTongTien.text = huhu[vitri!!].DonGia.plus(" VND/Day")
                    LayTenPhong(huhu[vitri!!].TenPhong.toString())
                    ClickDatPhong(huhu[vitri!!].Id.toString(),huhu[vitri!!].DonGia.toString())

//                    Stop
                }

            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        data.addValueEventListener(post)
    }

    private fun SuKien(){
        ClickNgayDen()
        ClickNgayTra()
        ClickHuy()





    }
    fun CapNhatTinhTrang(Id:String){
        data = FirebaseDatabase.getInstance().getReference("Phong").child(Id)
        data.child("TinhTrang").setValue("Hết phòng")

    }
    fun TaoPhieuThue(id:String,idPhong: String, IdKH:String,den:String,tra:String,tien:String){
        data= FirebaseDatabase.getInstance().getReference("PhieuThue")
        val phieuthue = PhieuThue(id,IdKH,"",idPhong,den,tra,tien,"Chưa thanh toán")
        data.child(id).setValue(phieuthue).addOnCompleteListener {
//            Toast.makeText(this, "Đặt Phòng Thành Công", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {err->
            Toast.makeText(this, "Thất bại ${err.message}", Toast.LENGTH_SHORT).show()
        }

    }


    private fun ClickDatPhong(Id:String,DonGia:String) {

        binding.btnDatPhong.setOnClickListener {
            CapNhatTinhTrang(Id)
            dbRef = FirebaseDatabase.getInstance().getReference("KhachHang")
            val ten = binding.edtTenKH.text.toString()
            var gioiTinh : String = ""
            if (binding.radNam.isChecked){
                gioiTinh = "Nam"
            }else{
                gioiTinh = "Nũ"
            }
            val sdt = binding.edtSDT.text.toString()
            val cccd = binding.edtCccd.text.toString()
            val diaChi = binding.edtDiaChi.text.toString()
            val den = binding.edtNgayDen.text.toString()
            val tra = binding.edtNgayTra.text.toString()
            val tongtien = "10000000đ"

            val id = dbRef.push().key!!
            val khachhang= KhachHang(id,ten,gioiTinh,sdt,cccd,diaChi)
            dbRef.child(id).setValue(khachhang).addOnCompleteListener {
                Toast.makeText(this, "Đặt Phòng Thành Công", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,TrangChu::class.java)
                startActivity(intent)
            }.addOnFailureListener {err->
                Toast.makeText(this, "Thất bại ${err.message}", Toast.LENGTH_SHORT).show()
            }
            TaoPhieuThue(id,Id,id,den,tra,DonGia)
        }



    }


    private fun ClickHuy() {
        binding.btnHuy.setOnClickListener {
            onStop()
            val intent = Intent(this, TrangChu::class.java)
            startActivity(intent)
        }

    }

    private fun ClickNgayTra() {
        binding.imgNgayTra.setOnClickListener{
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.edtNgayTra.setText("${dayOfMonth}/${month+1}/${year}")
            },2023,5,17).show()
        }
    }

    private fun ClickNgayDen() {
        val today = Calendar.getInstance()
        binding.imgNgayDen.setOnClickListener{
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.edtNgayDen.setText("${dayOfMonth}/${month+1}/${year}")
            },2023,1,1).show()
        }

    }
}