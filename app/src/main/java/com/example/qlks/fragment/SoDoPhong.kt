package com.example.qlks.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlks.DatPhong
import com.example.qlks.R
import com.example.qlks.TrangChu
import com.example.qlks.adapter.InterPhongAdapter
import com.example.qlks.adapter.LoaiPhongAdapter
import com.example.qlks.adapter.PhongAdapter
import com.example.qlks.databinding.FragmentSoDoPhongBinding
import com.example.qlks.model.Phong
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SoDoPhong.newInstance] factory method to
 * create an instance of this fragment.
 */

class SoDoPhong : Fragment(R.layout.fragment_so_do_phong)

{
private lateinit var data:DatabaseReference
 private lateinit  var dsLoaiPhong : ArrayList<com.example.qlks.model.LoaiPhong>
 private lateinit var dsPhong : ArrayList<Phong>
//private lateinit var dsLoaiPhong:ArrayList<com.example.qlks.model.LoaiPhong>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_so_do_phong,container,false)

        val btnSoDoPhong = view.findViewById<Button>(R.id.btnSoDoPhong)
        val btnLoaiPhong= view.findViewById<Button>(R.id.btnLoaiPhong)

         sodo(view)

        btnSoDoPhong.setOnClickListener {
            sodo(view)
        }
        btnLoaiPhong.setOnClickListener {
            sodoLP(view)
        }
//


        return view
    }
    fun sodoLP(view: View){
        data = FirebaseDatabase.getInstance().getReference("LoaiPhong")
        dsLoaiPhong = arrayListOf<com.example.qlks.model.LoaiPhong>()
        val post = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dsLoaiPhong.clear()
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val j = i.getValue(com.example.qlks.model.LoaiPhong::class.java)
                        dsLoaiPhong.add(j!!)
                    }
                    val adapter = LoaiPhongAdapter(dsLoaiPhong)
                    val rvView = view.findViewById<RecyclerView>(R.id.rvPhong)
                    rvView.adapter = adapter
                    rvView.layoutManager = GridLayoutManager(
                        null,1, GridLayoutManager.VERTICAL,
                        false
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        data.addValueEventListener(post)

//            val ds= dsLoaiPhong()

    }



    fun sodo(view: View){
        val rvView = view.findViewById<RecyclerView>(R.id.rvPhong)
        data = FirebaseDatabase.getInstance().getReference("Phong")
        dsPhong = arrayListOf<Phong>()
        val post = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dsPhong.clear()
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val j = i.getValue(Phong::class.java)
                        dsPhong.add(j!!)
                    }

                    val adapter = PhongAdapter(dsPhong, object :InterPhongAdapter{
                        override fun ClickPhong(pos: Int) {
                            if (dsPhong[pos].TinhTrang=="Hết phòng"){
                                Toast.makeText(view.context,"Vui lòng chọn phòng khác!!!",Toast.LENGTH_SHORT).show()
                            }else{
                                val intent = Intent(view.context,DatPhong::class.java)
                                val bundle = Bundle()
                                bundle.putInt("pos",pos)
                                intent.putExtras(bundle)
                                startActivity(intent)
                            }

//
                        }

                        override fun ClickKH(pos: Int) {
                            TODO("Not yet implemented")
                        }
                    })

                    rvView.adapter = adapter
                    rvView.layoutManager = GridLayoutManager(
                        null,3, GridLayoutManager.VERTICAL,
                        false
                    )

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        data.addValueEventListener(post)

    }


}