package com.example.newsreader.ui.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;
import com.example.newsreader.R;
import com.example.newsreader.adapter.SourcesPagerAdapter;
import com.example.newsreader.model.Source;
import com.example.newsreader.ui.article.ArticleFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    TabLayout tabLayout;
    ViewPager viewPager;
    SourcesPagerAdapter sourcesPagerAdapter;
    private View progressBar;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        presenter = new MainPresenter();
        presenter.initPresenter(this);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        progressBar = findViewById(R.id.progressBar);
        sourcesPagerAdapter = new SourcesPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sourcesPagerAdapter);
        presenter.getSources();
    }

    /**
     * <p>Setup viewpager after successful response</p>
     * */
    private void setupViewPager(ViewPager viewPager, List<Source> sourceList) {
        for(int i=0 ; i<sourceList.size(); i++) {
            sourcesPagerAdapter.addFragment(new ArticleFragment(sourceList.get(i).getId()), sourceList.get(i).getName(),i);
        }
        sourcesPagerAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showProgress(boolean isShow) {
        progressBar.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void response(List<Source> body) {
        setupViewPager(viewPager,body);
    }
}
