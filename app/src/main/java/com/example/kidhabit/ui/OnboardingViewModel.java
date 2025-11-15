package com.example.kidhabit.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.kidhabit.data.UserRepository;

public class OnboardingViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;

    public OnboardingViewModel(Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
    }

    public void saveName(String name) {
        mUserRepository.saveName(name);
    }
}
