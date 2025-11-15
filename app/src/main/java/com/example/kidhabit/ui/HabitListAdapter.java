package com.example.kidhabit.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidhabit.data.Habit;
import com.example.kidhabit.databinding.HabitItemBinding;

import java.util.List;

public class HabitListAdapter extends RecyclerView.Adapter<HabitListAdapter.HabitViewHolder> {

    public interface OnHabitToggleListener {
        void onHabitToggle(Habit habit);
    }

    private List<Habit> mHabits;
    private final OnHabitToggleListener mListener;

    public HabitListAdapter(List<Habit> habits, OnHabitToggleListener listener) {
        mHabits = habits;
        mListener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HabitItemBinding binding = HabitItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HabitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit current = mHabits.get(position);
        holder.habitNameTextView.setText(current.getName());
        holder.habitCheckBox.setChecked(current.isCompleted());
        holder.habitCheckBox.setOnClickListener(v -> mListener.onHabitToggle(current));
    }

    @Override
    public int getItemCount() {
        return mHabits != null ? mHabits.size() : 0;
    }

    public void setHabits(List<Habit> habits) {
        mHabits = habits;
        notifyDataSetChanged();
    }

    class HabitViewHolder extends RecyclerView.ViewHolder {
        private final TextView habitNameTextView;
        private final CheckBox habitCheckBox;

        private HabitViewHolder(HabitItemBinding binding) {
            super(binding.getRoot());
            habitNameTextView = binding.habitNameTextView;
            habitCheckBox = binding.habitCheckbox;
        }
    }
}
