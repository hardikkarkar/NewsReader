package com.example.newsreader.ui.homepage;

import com.example.newsreader.model.NewsSourcesResponse;
import com.example.newsreader.network.GetDataService;
import com.example.newsreader.network.RetrofitClientInstance;
import com.example.newsreader.utilities.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void initPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getSources() {
        view.showProgress(true);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<NewsSourcesResponse> call = service.getNewsSources(Constant.LANGUAGE,Constant.COUNTRY,Constant.API_KEY);
        call.enqueue(new Callback<NewsSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsSourcesResponse> call, Response<NewsSourcesResponse> response) {
                view.showProgress(false);
                if(response.code()==200) {
                    if (response.body() != null) {
                        view.response(response.body().getSources());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsSourcesResponse> call, Throwable t) {
                view.showProgress(false);
            }
        });
    }


}
