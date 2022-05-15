package com.example.task.ui.fragment.boardFragment;


import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.task.databinding.FragmentBoardFramentBinding;
import com.example.task.ui.fragment.base.BaseFragment;


public class BoardFragment extends BaseFragment<FragmentBoardFramentBinding> {
    private ViewPager2 viewPager2;
    AdapterForViewPager adapter = new AdapterForViewPager();
    @Override
    protected FragmentBoardFramentBinding getBinding() {
        return FragmentBoardFramentBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setupUI() {
    }

    @Override
    protected void setupObservers() {
        initAdapter();
    }
    private void initAdapter() {
        binding.boardVp.setAdapter(adapter);
    }

}