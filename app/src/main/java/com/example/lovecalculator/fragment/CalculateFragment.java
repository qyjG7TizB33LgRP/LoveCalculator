package com.example.lovecalculator.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lovecalculator.App;
import com.example.lovecalculator.R;
import com.example.lovecalculator.base.BaseFragment;
import com.example.lovecalculator.databinding.FragmentCalculateBinding;
import com.example.lovecalculator.madel.LoveModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculateFragment extends BaseFragment<FragmentCalculateBinding> {

    private NavController controller;

    @Override
    public FragmentCalculateBinding getBinding() {
        return FragmentCalculateBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavHostFragment nav = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        controller = nav.getNavController();
        initClickers();
    }

    private void initClickers() {
        binding.calculateBtn.setOnClickListener(v -> {
            binding.loading.setVisibility(View.VISIBLE);
            binding.someText.setVisibility(View.VISIBLE);
            String firstName = binding.femaleName.getText().toString();
            String secondName = binding.maleName.getText().toString();
            App.loveApi.getLove(firstName, secondName, "love-calculator.p.rapidapi.com",
                    "54ab1e22e0mshc41c4fceeb7ada0p17ff10jsn9567c572af51")
                    .enqueue(new Callback<LoveModel>() {
                        @Override
                        public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Log.e("ololo", "onResponse: " + response.errorBody());
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("result", response.body());
                                controller.navigate(R.id.resultFragment, bundle);
                            } else {
                                try {
                                    Log.e("---", response.errorBody().string() +"");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                binding.loading.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoveModel> call, Throwable t) {
                            Log.e("ololo", t.getMessage());
                            binding.loading.setVisibility(View.GONE);
                        }
                    });

        });
    }
}