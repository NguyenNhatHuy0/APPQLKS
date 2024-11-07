package com.example.qlks.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.qlks.fragment.*

class ViewPager2Adapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(
    fragmentManager,lifecycle
) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
return when(position){
    0->{SoDoPhong()}
    1->{KhachHang()}
    2->{DichVu()}
    3->{HoaDon()}
    else->{TaiKhoan()}
}
    }
}