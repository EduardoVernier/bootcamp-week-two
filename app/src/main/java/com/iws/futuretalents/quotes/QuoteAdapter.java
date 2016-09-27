package com.iws.futuretalents.quotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iws.futuretalents.quotes.network.QuoteService;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Quote} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

	private final OnListFragmentInteractionListener listener;
	private final Context context;
	private List<Quote> quoteList;
	private QuoteService quoteService;

	public QuoteAdapter(OnListFragmentInteractionListener listener, Context context) {

		this.listener = listener;
		this.context = context;
		quoteList = new ArrayList<Quote>();
		quoteService = new QuoteService(quoteList, this);
		quoteService.fetchQuote(5);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_quote, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {

		holder.item = quoteList.get(position);
		holder.quoteTextView.setText("\"" + quoteList.get(position).getQuote() + "\"");
		holder.titleTextView.setText("- " + quoteList.get(position).movieData.getTitle());

		Glide.with(context)
				.load(holder.item.movieData.getPoster())
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(holder.posterImageView);

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

		// Fetch more quotes when reaching end of list
		if (position > getItemCount() - 2) {
			quoteService.fetchQuote(3);
		}
	}

	@Override
	public int getItemCount() {
		return quoteList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public final View view;
		public final ImageView posterImageView;
		public final TextView quoteTextView;
		public final TextView titleTextView;
		public Quote item;

		public ViewHolder(View view) {
			super(view);
			this.view = view;
			quoteTextView = (TextView) view.findViewById(R.id.list_quote);
			titleTextView = (TextView) view.findViewById(R.id.list_title);
			posterImageView = (ImageView) view.findViewById(R.id.list_poster);
		}
	}

	interface OnListFragmentInteractionListener {
		void onListFragmentInteraction(Quote item);
	}

}
