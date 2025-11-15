package com.example.kidhabit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kidhabit.R;
import com.example.kidhabit.data.Habit;
import com.example.kidhabit.databinding.FragmentHabitChecklistBinding;

import java.util.ArrayList;

public class HabitChecklistFragment extends Fragment implements HabitListAdapter.OnHabitToggleListener {

    private FragmentHabitChecklistBinding binding;
    private HabitChecklistViewModel habitChecklistViewModel;
    private RewardsViewModel rewardsViewModel;
    private HabitListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHabitChecklistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        habitChecklistViewModel = new ViewModelProvider(this).get(HabitChecklistViewModel.class);
        rewardsViewModel = new ViewModelProvider(requireActivity()).get(RewardsViewModel.class);

        setupRecyclerView();

        habitChecklistViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
            adapter.setHabits(habits);
        });

        binding.rewardsButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_habitChecklistFragment_to_rewardsFragment);
        });

        binding.parentDashboardButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_habitChecklistFragment_to_parentDashboardFragment);
        });
    }

    private void setupRecyclerView() {
        adapter = new HabitListAdapter(new ArrayList<>(), this);
        binding.habitRecyclerView.setAdapter(adapter);
        binding.habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onHabitToggle(Habit habit) {
        habitChecklistViewModel.toggleHabit(habit, rewardsViewModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
