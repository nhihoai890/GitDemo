package com.example.students.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment(); // Trang chủ
            case 1:
                return new ProfileFragment(); // Thông tin cá nhân
            case 2:
                return new GradeFragment(); // Điểm số
            default:
                return new HomeFragment(); // Mặc định
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
