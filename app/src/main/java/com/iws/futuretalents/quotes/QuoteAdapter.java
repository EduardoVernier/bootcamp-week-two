package com.iws.futuretalents.quotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
* {@link RecyclerView.Adapter} that can display a {@link Quote} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

	private final List<Quote> adapterQuoteList;
	private final OnListFragmentInteractionListener listener;

	public QuoteAdapter(List<Quote> items, OnListFragmentInteractionListener listener) {

		this.adapterQuoteList = items;
		this.listener = listener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_quote, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {

		holder.item = adapterQuoteList.get(position);
		holder.quoteTextView.setText(adapterQuoteList.get(position).getQuote());
		holder.movieTextView.setText(adapterQuoteList.get(position).getAuthor());

		holder.view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != listener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					listener.onListFragmentInteraction(holder.item);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return adapterQuoteList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public final View view;
		public final TextView quoteTextView;
		public final TextView movieTextView;
		public Quote item;

		public ViewHolder(View view) {
			super(view);
			this.view = view;
			quoteTextView = (TextView) view.findViewById(R.id.quote_text);
			movieTextView = (TextView) view.findViewById(R.id.movie_name);
		}
	}

	public interface OnListFragmentInteractionListener {
		void onListFragmentInteraction(Quote item);
	}

}
