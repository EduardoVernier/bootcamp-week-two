package com.iws.futuretalents.quotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullPosterActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_poster);

		String url = getIntent().getStringExtra("url");

		ImageView poster = (ImageView) findViewById(R.id.full_poster);
		Glide.with(this)
				.load(url)
				.into(poster);
	}
}
