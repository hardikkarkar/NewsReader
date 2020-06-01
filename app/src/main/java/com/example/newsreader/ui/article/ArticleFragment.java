package com.example.newsreader.ui.article;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsreader.R;
import com.example.newsreader.adapter.ArticleListAdapter;
import com.example.newsreader.model.Article;
import com.example.newsreader.ui.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment implements ArticleContract.View,SwipeRefreshLayout.OnRefreshListener, ArticleListAdapter.ClickListner {

    private String sourceId;
    private List<Article> articleList = new ArrayList<>();
    private ArticleListAdapter articleListAdapter;
    private SwipeRefreshLayout swipeRefresh;
    private ArticlePresenter presenter;

    public ArticleFragment(String sourceId) {
        // Required empty public constructor
        this.sourceId = sourceId;
        presenter = new ArticlePresenter();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);;
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        presenter.initPresenter(this);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new SpacesItemDecoration(3,10,true));
        articleListAdapter = new ArticleListAdapter(articleList,getActivity(),this);
        recyclerView.setAdapter(articleListAdapter);
        swipeRefresh.setOnRefreshListener(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getArticles(sourceId);
    }

    @Override
    public void onRefresh() {
        presenter.getArticles(sourceId);
    }

    @Override
    public void response(List<Article> list) {
        articleList.clear();
        articleList.addAll(list);
        articleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean isShow) {
        swipeRefresh.setRefreshing(isShow);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemClick(int position, Article data) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", data.getUrl());
        bundle.putString("title", data.getTitle());
        intent.putExtra("url_data", bundle);
        startActivity(intent);
    }
}
