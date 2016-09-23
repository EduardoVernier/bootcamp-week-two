package com.iws.futuretalents.quotes.network;

import com.iws.futuretalents.quotes.Quote;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RandomQuoteClient {
	@Headers({
			"X-Mashape-Key: v1m0dxHZARmshQpbhPfjSaWlo25vp1P15ljjsn7cjG4tF04Mc6",
			"Content-Type: application/x-www-form-urlencoded",
			"Accept: application/json"
	})

	@POST("?cat=movies")
	Call<Quote> getQuote();
}
