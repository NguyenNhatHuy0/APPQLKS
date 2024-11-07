package com.example.qlks.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.NguoiDung
import com.example.qlks.R
import com.example.qlks.R.id.rvKH
import com.example.qlks.ThongTinKhachHang
import com.example.qlks.TrangChu
import com.example.qlks.adapter.DsKHAdapter
import com.example.qlks.adapter.InterPhongAdapter
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KhachHang.newInstance] factory method to
 * create an instance of this fragment.
 */
class KhachHang : Fragment(R.layout.fragment_khach_hang)
 {
    private lateinit var data : DatabaseReference
    private lateinit var ds : ArrayList<com.example.qlks.model.KhachHang>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.fragment_khach_hang,container,false)

        val rv = view1.findViewById<RecyclerView>(R.id.rvKH)
        data = FirebaseDatabase.getInstance().getReference("KhachHang")
        ds = arrayListOf<com.example.qlks.model.KhachHang>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                ds.clear()
                // Get Post object and use the values to update the UI
                if (dataSnapshot.exists()){
                    for (i in dataSnapshot.children) {
                        val j = i.getValue(com.example.qlks.model.KhachHang::class.java)
                        ds.add(j!!)
                    }
                    val adapter = DsKHAdapter(ds,object : InterPhongAdapter{
                        override fun ClickKH(pos: Int) {
                            val intent = Intent(view1.context, ThongTinKhachHang::class.java)
                            val bundle = Bundle()
                            bundle.putInt("pos",pos)
                            bundle.putString("id",ds[pos].Id)
                            bundle.putString("ten",ds[pos].TenKhachHang)
                            bundle.putString("sdt",ds[pos].SDT)
                            bundle.putString("gioitinh",ds[pos].GioiTinh)
                            bundle.putString("diachi",ds[pos].DiaChi)
                            bundle.putString("cccd",ds[pos].CCCD)



                            intent.putExtras(bundle)
                            startActivity(intent)
//                        Toast.makeText(view1.context,"Bạn chọn phòng ${ds[pos].tenKH}",Toast.LENGTH_SHORT).show()
                        }

                        override fun ClickPhong(pos: Int) {
                            TODO("Not yet implemented")
                        }
                    })
                    rv.adapter = adapter
                    rv.layoutManager = LinearLayoutManager(
                        view1.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )


                }
                }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        data.addValueEventListener(postListener)
        return view1
    }
}