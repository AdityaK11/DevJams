package com.example.devjams.url;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devjams.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleActivityFragment extends Fragment {

    public ArticleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_activity, container, false);
    }
}