package com.example.kidhabit.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.kidhabit.data.Badge;
import com.example.kidhabit.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class RewardsViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;
    private MutableLiveData<Integer> mStars = new MutableLiveData<>();
    private MutableLiveData<List<Badge>> mBadges = new MutableLiveData<>();

    public RewardsViewModel(Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
        mStars.setValue(mUserRepository.getStars());

        List<Badge> badges = new ArrayList<>();
        badges.add(new Badge(1, "10 Stars!", 10, mUserRepository.isBadgeUnlocked(1)));
        badges.add(new Badge(2, "20 Stars!", 20, mUserRepository.isBadgeUnlocked(2)));
        badges.add(new Badge(3, "50 Stars!", 50, mUserRepository.isBadgeUnlocked(3)));
        mBadges.setValue(badges);
    }

    public LiveData<Integer> getStars() {
        return mStars;
    }

    public LiveData<List<Badge>> getBadges() {
        return mBadges;
    }

    public void addStar() {
        int currentStars = mStars.getValue() != null ? mStars.getValue() : 0;
        mStars.setValue(currentStars + 1);
        mUserRepository.saveStars(mStars.getValue());
        checkBadges();
    }

    private void checkBadges() {
        List<Badge> currentBadges = mBadges.getValue();
        if (currentBadges != null) {
            for (Badge badge : currentBadges) {
                if (!badge.isUnlocked() && mStars.getValue() >= badge.getStarRequirement()) {
                    badge.setUnlocked(true);
                    mUserRepository.saveBadgeUnlocked(badge.getId());
                }
            }
            mBadges.setValue(currentBadges);
        }
    }
}
