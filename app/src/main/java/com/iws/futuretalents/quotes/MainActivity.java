package com.iws.futuretalents.quotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.iws.futuretalents.quotes.network.RandomQuoteClient;
import com.iws.futuretalents.quotes.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.iws.futuretalents.quotes.network.ServiceGenerator.API_BASE_URL;

public class MainActivity extends AppCompatActivity {

	QuoteListFragment listFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


//		listFrag = new QuoteListFragment();
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.activity_main, listFrag)
//					.commit();
//		}
		ServiceGenerator newRequest = new ServiceGenerator();
		Toast.makeText(this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		RandomQuoteClient quoteClient = retrofit.create(RandomQuoteClient.class);

		Call<Quote> call = quoteClient.getQuote();

		Quote getQuote = null;
		try {
			getQuote = call.execute().body();
			Log.v(TAG, "getQuote: " + getQuote.getQuote());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
