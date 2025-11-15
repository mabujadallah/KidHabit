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

import com.example.kidhabit.R;
import com.example.kidhabit.databinding.FragmentOnboardingBinding;

public class OnboardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;
    private OnboardingViewModel onboardingViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onboardingViewModel = new ViewModelProvider(this).get(OnboardingViewModel.class);

        binding.saveButton.setOnClickListener(v -> {
            String name = binding.nameEditText.getText().toString();
            if (!name.isEmpty()) {
                onboardingViewModel.saveName(name);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_onboardingFragment_to_habitChecklistFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
