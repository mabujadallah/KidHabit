package com.example.kidhabit.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidhabit.data.Badge;
import com.example.kidhabit.databinding.BadgeItemBinding;

import java.util.List;

public class BadgeListAdapter extends RecyclerView.Adapter<BadgeListAdapter.BadgeViewHolder> {

    private List<Badge> mBadges;

    public BadgeListAdapter(List<Badge> badges) {
        mBadges = badges;
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BadgeItemBinding binding = BadgeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BadgeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Badge current = mBadges.get(position);
        holder.badgeNameTextView.setText(current.getName());
        holder.badgeStatusTextView.setText(current.isUnlocked() ? "Unlocked" : "Locked");
    }

    @Override
    public int getItemCount() {
        return mBadges != null ? mBadges.size() : 0;
    }

    public void setBadges(List<Badge> badges) {
        mBadges = badges;
        notifyDataSetChanged();
    }

    class BadgeViewHolder extends RecyclerView.ViewHolder {
        private final TextView badgeNameTextView;
        private final TextView badgeStatusTextView;

        private BadgeViewHolder(BadgeItemBinding binding) {
            super(binding.getRoot());
            badgeNameTextView = binding.badgeNameTextView;
            badgeStatusTextView = binding.badgeStatusTextView;
        }
    }
}
