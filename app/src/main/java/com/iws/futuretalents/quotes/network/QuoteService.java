package com.iws.futuretalents.quotes.network;


import com.iws.futuretalents.quotes.Quote;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteService extends Observable {

	private static QuoteService instance;
	private RandomQuoteClient service;
	private List<Quote> quoteList;

	private QuoteService() {
	}

	public static QuoteService getInstance() {

		if (instance == null) {
			instance = new QuoteService();
		}
		return instance;
	}

	public void init(List<Quote> quoteList) {

		this.quoteList = quoteList;
		service = RandomQuoteClient.retrofit.create(RandomQuoteClient.class);

		fetchQuote(5);
	}

	public void fetchQuote(int n) {

		// Enqueue n calls (API doesn't provide a mechanism to provide multiple at a time)
		for (int i = 0; i < n; i++) {
			Call<Quote> call = service.getQuote();
			call.enqueue(new QuoteCallback());
		}
	}

	public void addQuote(Quote quote) {
		quoteList.add(quote);
		// Notify adapter of new Quote
		setChanged();
		notifyObservers();
	}

	private class QuoteCallback implements Callback<Quote> {

		@Override
		public void onResponse(Call<Quote> call, Response<Quote> response) {

			Quote newQuote = response.body();
			includeMovieData(newQuote);
		}

		@Override
		public void onFailure(Call<Quote> call, Throwable t) {
			t.printStackTrace();
		}
	}

	/*
	Since I don't know how to 'join' asynchronous callbacks and I'd like to avoid active waiting
	on the main/UI thread, new Quote object is added to the quoteList reference and the adapter is
	notified on the OnResponse callback
	*/
	private void includeMovieData(final Quote newQuote) {

		MovieClient service = MovieClient.retrofit.create(MovieClient.class);
		Call<Quote.Movie> call = service.getMovie(newQuote.getMovieTitle().replace(' ', '+'));

		call.enqueue(new Callback<Quote.Movie>() {

			@Override
			public void onResponse(Call<Quote.Movie> call, Response<Quote.Movie> response) {

				// Retrieve response object
				Quote.Movie movieData = response.body();

				if (validMovieData(movieData, newQuote.getQuote())) {
					// Add it to quote list inside onResponse to avoid callback/threads synchronization
					newQuote.movieData = movieData;
					addQuote(newQuote);
				}
				else {
					// Invalidate quote if can't get poster url or if it's a repeated quote
					fetchQuote(1);
				}
			}

			@Override
			public void onFailure(Call<Quote.Movie> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}

	private boolean validMovieData(Quote.Movie movieData, String newQuote) {

		for (Quote q : quoteList) {
			if (q.getQuote().equals(newQuote)) {
				// Check for repetition
				return false;
			}
		}

		// Check for invalid poster urls
		return movieData.getPoster() != null && !movieData.getPoster().equals("N/A");
	}
}
