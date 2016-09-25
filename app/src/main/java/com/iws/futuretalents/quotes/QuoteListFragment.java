package com.iws.futuretalents.quotes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iws.futuretalents.quotes.network.RandomQuoteClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteListFragment extends Fragment
		implements QuoteAdapter.OnListFragmentInteractionListener {

	private List<Quote> quoteList;
	private QuoteAdapter adapter;
	private RandomQuoteClient service;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public QuoteListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		quoteList = new ArrayList<Quote>();
		adapter = new QuoteAdapter(quoteList, this, getContext());

		fetchQuote(5);
	}

	private void fetchQuote(int n) {
		service = RandomQuoteClient.retrofit.create(RandomQuoteClient.class);
		for (int i = 0; i < n; i++) {
			Call<Quote> call = service.getQuote();
			call.enqueue(new QuoteCallback());
		}
	}

	class QuoteCallback implements Callback<Quote> {

		@Override
		public void onResponse(Call<Quote> call, Response<Quote> response) {
				Quote newQuote = response.body();
				newQuote.includeMovieData(quoteList, adapter);
		}

		@Override
		public void onFailure(Call<Quote> call, Throwable t) {
				t.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_quote_list, container, false);

		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
			recyclerView.setAdapter(adapter);
		}

		return view;
	}

	public void onListFragmentInteraction(Quote item) {

	}
}
