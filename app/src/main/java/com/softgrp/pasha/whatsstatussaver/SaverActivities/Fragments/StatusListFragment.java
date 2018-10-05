package com.softgrp.pasha.whatsstatussaver.SaverActivities.Fragments;

import HelperDir.DpToPx;
import HelperDir.SimpleCalculations;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.softgrp.pasha.whatsstatussaver.R;
import com.softgrp.pasha.whatsstatussaver.SaverActivities.Adapter.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmanuel on 27/09/2017.
 */

public class StatusListFragment extends Fragment implements CustomArrayAdapter.AdapterFragmentInteraction{
    private OnFragmentAttachedListener fragmentAttachedListener;
    private Context context;

    public static StatusListFragment newInstance(List<String> saverDataModelList, List<String> filePath) {
        Bundle args = new Bundle();
        args.putStringArrayList("arrayListName", (ArrayList<String>) saverDataModelList);
        args.putStringArrayList("paths", (ArrayList<String>) filePath);
        StatusListFragment fragment = new StatusListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        Log.i("Attached", "true");
        try {
            fragmentAttachedListener = (OnFragmentAttachedListener) context;
            Log.i("Intanciated: ", "True");

        }catch (ClassCastException e){
            e.fillInStackTrace();
            throw new ClassCastException("M" +
                    "just implemant this interface");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> saverDataModels = getArguments().getStringArrayList("arrayListName");
        List<String> paths = getArguments().getStringArrayList("paths");
        GridView gridView = view.findViewById(R.id.grid_view);
        gridView.setHorizontalSpacing(GridView.STRETCH_SPACING);
        gridView.setNumColumns(getNoOfColumn());
        CustomArrayAdapter adapter = new CustomArrayAdapter(view.getContext(), R.layout.status_item_view,
                saverDataModels, paths, this);
        gridView.setAdapter(adapter);
    }

    private int getNoOfColumn() {
        //get the width of the phone and convert to px
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthDisplay = displayMetrics.widthPixels;
        int pxWidth = DpToPx.PxValue(context, widthDisplay);
        float pixWidth = context.getResources().getDimension(R.dimen.width);
        int pixPiWidht = DpToPx.PxValue(context, pixWidth);
        return SimpleCalculations.getNumOfColumn(pixPiWidht, pxWidth);
    }

    @Override
    public void downLoadClicked(String path) {
        fragmentAttachedListener.downLoadClicked(path);
    }

    public interface OnFragmentAttachedListener{
        void downLoadClicked(String path);
    }
}
