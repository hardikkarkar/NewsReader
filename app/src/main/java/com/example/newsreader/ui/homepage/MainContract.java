package com.example.newsreader.ui.homepage;

import com.example.newsreader.base.BasePresenter;
import com.example.newsreader.base.BaseView;
import com.example.newsreader.model.Source;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {

        /**
         * <p>Success response of news sources</p>
         * */
        void response(List<Source> body);
    }

    interface Presenter extends BasePresenter{
        /**
         * <p>This method used to initialize presenter</p>
         * @param view
         */
        void initPresenter(MainContract.View view);

        /**
         * <p>Used to get list of news sources</p>
         * */
        void getSources();
    }
}
