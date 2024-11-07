package com.example.qlks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.qlks.databinding.ActivityMainBinding
import com.example.qlks.model.Phong
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private  lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Phong().LayDaTa()

//            intent = Intent(this, TrangChu::class.java)
//            startActivity(intent)

        dangnhap()
        dangky()
//        ban1()
//        binding.btnBan2.setOnClickListener {
//            val database = Firebase.database
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")
//        }
//        binding.btnDS.setOnClickListener {
//            intent = Intent(this, DanhSachPhong::class.java)
//            startActivity(intent)
//        }
//        intent = Intent(this, DangKy::class.java)
//        startActivity(intent)
    }

    private fun dangky() {
        binding.btnDangKy.setOnClickListener {
            intent = Intent(this, DangKy::class.java)
            startActivity(intent)
        }
    }

    private fun dangnhap() {
        binding.btnDangNhap.setOnClickListener {
            intent = Intent(this, DangNhap::class.java)
            startActivity(intent)
        }
    }
//    fun ban1(){
//        binding.btnBan1.setOnClickListener {
//            intent = Intent(this, Ban1::class.java)
//            startActivity(intent)
//        }
//    }
}