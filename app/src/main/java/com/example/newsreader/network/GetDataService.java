package com.example.newsreader.network;


import com.example.newsreader.model.ArticleResponse;
import com.example.newsreader.model.NewsSourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("sources")
    Call<NewsSourcesResponse> getNewsSources(@Query("language") String language,
                                                       @Query("country") String country,
                                                       @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ArticleResponse> getArticles(@Query("sources") String id,
                                                @Query("apiKey") String apiKey);
}