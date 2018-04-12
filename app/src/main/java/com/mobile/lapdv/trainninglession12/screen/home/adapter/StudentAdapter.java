package com.mobile.lapdv.trainninglession12.screen.home.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.lapdv.trainninglession12.R;
import com.mobile.lapdv.trainninglession12.screen.callback.OnLoadMoreListener;
import com.mobile.lapdv.trainninglession12.screen.home.model.Student;

import java.util.ArrayList;

/**
 * Created by lap on 12/04/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<Student> mStudents;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public StudentAdapter(ArrayList<Student> mStudents, RecyclerView recyclerView) {
        this.mStudents = mStudents;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!isLoading) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new StudentHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chirldren, parent, false));
            case VIEW_TYPE_LOADING:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof StudentHolder) {
            if (mStudents != null) {
                ((StudentHolder) holder).tvName.setText(!TextUtils.isEmpty(mStudents.get(position).name)
                        ? mStudents.get(position).name : "");
                ((StudentHolder) holder).tvAddress.setText(!TextUtils.isEmpty(mStudents.get(position).address)
                        ? mStudents.get(position).address : "");
            }
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
            ((LoadingViewHolder) holder).progressBar.getIndeterminateDrawable()
                    .setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }

    }

    @Override
    public int getItemCount() {
        return mStudents != null && mStudents.size() != 0 ? mStudents.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position == mStudents.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class StudentHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAddress;

        public StudentHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
