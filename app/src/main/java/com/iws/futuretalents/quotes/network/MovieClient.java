package com.iws.futuretalents.quotes.network;

import com.iws.futuretalents.quotes.Quote;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieClient {
	public static final String API_BASE_URL =
			"http://www.omdbapi.com/";

	@POST("?y=&plot=full&r=json")
	Call<Quote.Movie> getMovie(@Query("t") String title);

	public static final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
}
