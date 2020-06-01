package com.example.newsreader.ui.article;

import com.example.newsreader.model.ArticleResponse;
import com.example.newsreader.network.GetDataService;
import com.example.newsreader.network.RetrofitClientInstance;
import com.example.newsreader.utilities.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlePresenter implements ArticleContract.Presenter{

    private ArticleContract.View view;

    @Override
    public void initPresenter(ArticleContract.View view){
        this.view = view;
    }

    @Override
    public void getArticles(String id) {
        view.showProgress(true);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArticleResponse> call = service.getArticles(id, Constant.API_KEY);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                view.showProgress(false);
                if(response.code()==200) {
                    if (response.body() != null) {
                        //Gson gson = new Gson();
                        //ArticleResponse response1 = gson.fromJson(response.toString(),ArticleResponse.class);
                        view.response(response.body().getArticles());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                view.showProgress(false);
            }
        });
    }
}
