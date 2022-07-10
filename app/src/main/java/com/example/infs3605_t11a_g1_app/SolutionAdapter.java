package com.example.infs3605_t11a_g1_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SolutionAdapter extends RecyclerView.Adapter<SolutionAdapter.ViewHolder> {
    private List<Solution> mSolutions;
    private RecyclerViewClickListener mListener;

    public SolutionAdapter(List<Solution> solutions, RecyclerViewClickListener listener) {
        mSolutions = solutions;
        mListener = listener;
    }

    @NonNull
    @Override
    public SolutionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.solution_list_view, parent, false);
        return new ViewHolder(v, mListener);
    }

    // Shows the Holiday destination on screen that has been filtered
    @Override
    public void onBindViewHolder(@NonNull SolutionAdapter.ViewHolder holder, int position) {
        Solution solution = mSolutions.get(position);

        holder.mTitle.setText(solution.getTitle());
        holder.mChallenge.setText(solution.getChallenge());
        holder.mRating.setText(solution.getRating());
        holder.mImage.setImageResource(solution.getImage());
        holder.itemView.setTag(solution.getCode());
    }

    @Override
    public int getItemCount() {
        return mSolutions.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, String holidaySymbol);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle, mChallenge, mRating;
        private ImageView mImage;
        private RecyclerViewClickListener mListener;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mTitle = view.findViewById(R.id.tv_title);
            mChallenge = view.findViewById(R.id.tv_challenge);
            mRating = view.findViewById(R.id.tv_rating);
            mImage = view.findViewById(R.id.iv_solutionImage);
            mListener = listener;
            view.setOnClickListener(this);
        }

        // Shows the correct destination based on the click
        @Override
        public void onClick (View view) {
            mListener.onClick(view, (String) view.getTag());
        }
    }
}