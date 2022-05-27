package com.example.lovecalculator.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovecalculator.R;
import com.example.lovecalculator.base.BaseFragment;
import com.example.lovecalculator.databinding.FragmentResultBinding;
import com.example.lovecalculator.madel.LoveModel;

public class ResultFragment extends BaseFragment<FragmentResultBinding> {

    private NavController controller;

    @Override
    public FragmentResultBinding getBinding() {
        return FragmentResultBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoveModel loveModel = (LoveModel) getArguments().getSerializable("result");
        binding.tvResult.setText(loveModel.getPercentage());
        binding.tvYourName.setText(loveModel.getSecondName());
        binding.tvMeName.setText(loveModel.getFirstName());
        binding.textResult.setText(loveModel.getResult());
        NavHostFragment nav =(NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        controller = nav.getNavController();
        initListener();
    }

    private void initListener() {
        binding.btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigateUp();
            }
        });
    }
}