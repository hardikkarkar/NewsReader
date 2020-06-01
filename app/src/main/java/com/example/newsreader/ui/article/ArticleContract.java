package com.example.newsreader.ui.article;

import com.example.newsreader.base.BasePresenter;
import com.example.newsreader.base.BaseView;
import com.example.newsreader.model.Article;

import java.util.List;

public interface ArticleContract {

    interface View extends BaseView {

        /**
         * <p>When successful response fetch</p>
         * @param body
         * */
        void response(List<Article> body);
    }

    interface Presenter extends BasePresenter {

        /**
         * <p>This method used to initialize presenter</p>
         * @param view
         */
        void initPresenter(ArticleContract.View view);

        /**
         * <p>Used to get list of Article by albumId</p>
         * @param albumId
         * */
        void getArticles(String albumId);
    }
}
