package com.example.qlks.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.qlks.DangNhap
import com.example.qlks.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaiKhoan.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaiKhoan : Fragment(R.layout.fragment_tai_khoan){
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tai_khoan,container,false)
        val btnDangXuat = view.findViewById<Button>(R.id.btnDangXuat)
        btnDangXuat.setOnClickListener {
//            onDestroy()
           val intent = Intent(view.context,DangNhap::class.java)
            startActivity(intent)
            Toast.makeText(view.context,"Đăng xuất",Toast.LENGTH_SHORT).show()
        }

        return view
    }
}