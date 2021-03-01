package com.mingrisoft.androidnetwork;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.InnerHolder> {
    @NonNull
    @Override
    public ResultAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.InnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
