package com.example.qlks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.qlks.adapter.ViewPager2Adapter
import com.example.qlks.databinding.ActivityTrangChuBinding
import com.example.qlks.db.CSDL
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator


private lateinit var binding: ActivityTrangChuBinding
class TrangChu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrangChuBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar!!.hide()
        val adapter = ViewPager2Adapter(supportFragmentManager,lifecycle)
        binding.viewPage2TrangChu.adapter = adapter
        TabLayoutMediator(binding.tabTrangChu, binding.viewPage2TrangChu){ tab, pos->
            when(pos){
                0->{tab.text="Sơ đồ phòng"}
                1->{tab.text="Khách hàng"}
                2->{tab.text="Dịch vụ"}
                3->{tab.text="Hóa đơn"}
                4->{tab.text="Tài Khoản"}
            }
        }.attach()
//        val btnLoaiPhong = findViewById<Button>(R.id.btnLoaiPhong)
//        btnLoaiPhong.setOnClickListener {
//            Toast.makeText(this@TrangChu,"Ok",Toast.LENGTH_SHORT).show()
//        }
//        btnLoaiPhong()
//        CSDL().kh()


    }




//    private fun btnLoaiPhong() {
//
////
////        btnLoaiPhong.setOnClickListener {
////            val loaiPhong = com.example.qlks.fragment.LoaiPhong()
////            supportFragmentManager.beginTransaction().apply {
////                replace(R.id.showsodophong, loaiPhong )
////                commit()
////            }
////        }
////
////    }


}