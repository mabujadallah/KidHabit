package com.example.kidhabit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kidhabit.databinding.FragmentParentDashboardBinding;

public class ParentDashboardFragment extends Fragment {

    private FragmentParentDashboardBinding binding;
    private RewardsViewModel rewardsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentParentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rewardsViewModel = new ViewModelProvider(requireActivity()).get(RewardsViewModel.class);

        rewardsViewModel.getStars().observe(getViewLifecycleOwner(), stars -> {
            binding.totalStarsTextView.setText("Total Stars: " + stars);
        });

        rewardsViewModel.getBadges().observe(getViewLifecycleOwner(), badges -> {
            long unlockedCount = badges.stream().filter(b -> b.isUnlocked()).count();
            binding.badgesEarnedTextView.setText("Badges Earned: " + unlockedCount);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
