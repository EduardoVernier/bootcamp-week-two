package com.iws.futuretalents.quotes.network;

import com.google.gson.GsonBuilder;
import com.iws.futuretalents.quotes.Quote;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RandomQuoteClient {
	public static final String API_BASE_URL =
			"https://andruxnet-random-famous-quotes.p.mashape.com/";

	@Headers({
			"X-Mashape-Key: v1m0dxHZARmshQpbhPfjSaWlo25vp1P15ljjsn7cjG4tF04Mc6",
			"Content-Type: application/x-www-form-urlencoded",
			"Accept: application/json"
	})

	@POST("?cat=movies")
	Call<Quote> getQuote();

	public static final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(
					new GsonBuilder()
							.excludeFieldsWithoutExposeAnnotation()
							.create()
			))
			.build();
}
