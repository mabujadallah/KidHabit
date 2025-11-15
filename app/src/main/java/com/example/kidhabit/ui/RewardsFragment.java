package com.example.kidhabit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kidhabit.databinding.FragmentRewardsBinding;

import java.util.ArrayList;

public class RewardsFragment extends Fragment {

    private FragmentRewardsBinding binding;
    private RewardsViewModel rewardsViewModel;
    private BadgeListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRewardsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rewardsViewModel = new ViewModelProvider(requireActivity()).get(RewardsViewModel.class);

        setupRecyclerView();

        rewardsViewModel.getStars().observe(getViewLifecycleOwner(), stars -> {
            binding.starsTextView.setText("Total Stars: " + stars);
        });

        rewardsViewModel.getBadges().observe(getViewLifecycleOwner(), badges -> {
            adapter.setBadges(badges);
        });
    }

    private void setupRecyclerView() {
        adapter = new BadgeListAdapter(new ArrayList<>());
        binding.badgeRecyclerView.setAdapter(adapter);
        binding.badgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
