package com.mobile.lapdv.trainninglession12.screen.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.lapdv.trainninglession12.R;
import com.mobile.lapdv.trainninglession12.screen.callback.OnLoadMoreListener;
import com.mobile.lapdv.trainninglession12.screen.home.adapter.StudentAdapter;
import com.mobile.lapdv.trainninglession12.screen.home.model.Student;
import com.mobile.lapdv.trainninglession12.screen.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;


/**
 * Created by lap on 10/04/2018.
 */

public class StudentFragment extends Fragment {

    private TextView tvPagePosition, tvState;
    static String position;
    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private ArrayList<Student> mStudentArrayList;

    public static StudentFragment newInstance(int page, String title) {
        StudentFragment studentFragment = new StudentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), page);
        bundle.putString(String.class.getName(), title);
        studentFragment.setArguments(bundle);
        position = title;
        return studentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chirldren, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        tvPagePosition = view.findViewById(R.id.tv_page_position);
        tvState = view.findViewById(R.id.tv_state);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mStudentArrayList = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            Student student = new Student();
            student.name = "lapdv" + i;
            student.address = "lapdv" + i + "@gmail.com";
            mStudentArrayList.add(student);
        }
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStudentAdapter = new StudentAdapter(mStudentArrayList, mRecyclerView);
        mRecyclerView.setAdapter(mStudentAdapter);
        mStudentAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mStudentArrayList.add(null);
                mStudentAdapter.notifyItemInserted(mStudentArrayList.size() - 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStudentArrayList.remove(mStudentArrayList.size() - 1);
                        mStudentAdapter.notifyItemRemoved(mStudentArrayList.size());
                        int index = mStudentArrayList.size();
                        int end = index + 5;
                        for (int i = index; i < end; i++) {
                            Student student = new Student();
                            student.name = ("Name " + i);
                            student.address = ("lapdv" + i + "@gmail.com");
                            mStudentArrayList.add(student);
                        }
                        mStudentAdapter.notifyDataSetChanged();
                        mStudentAdapter.setLoaded();
                    }
                }, 2000);
            }
        });
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
    }
}