package com.iws.futuretalents.quotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Quote quote = (Quote) Parcels.unwrap(getIntent().getParcelableExtra("quote"));

		ImageView poster = (ImageView) findViewById(R.id.poster);
		TextView quoteText = (TextView) findViewById(R.id.quote);
		TextView title = (TextView) findViewById(R.id.title);
		TextView plot = (TextView) findViewById(R.id.plot);
		TextView director = (TextView) findViewById(R.id.director);
		TextView writer= (TextView) findViewById(R.id.writer);
		TextView cast = (TextView) findViewById(R.id.cast);

		Glide.with(this)
				.load(quote.movieData.getPoster())
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(poster);

		quoteText.setText("\"" + quote.getQuote() + "\"");
		title.setText(quote.getMovieTitle()+" (" + quote.movieData.year + ")");
		plot.setText(quote.movieData.plot);
		director.setText(quote.movieData.director);
		writer.setText(quote.movieData.writer);
		cast.setText(quote.movieData.actors);
		Log.v("", quote.movieData.plot);
	}
}
