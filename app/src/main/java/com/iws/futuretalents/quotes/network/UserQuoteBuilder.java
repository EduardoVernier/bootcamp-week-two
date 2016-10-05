package com.iws.futuretalents.quotes.network;

import com.iws.futuretalents.quotes.Quote;

public class UserQuoteBuilder {

	public final String quote;
	public final String movie;
	public String year;
	public String plot;
	public String director;
	public String writer;
	public String actors;

	public UserQuoteBuilder(String quote, String movie) {
		this.quote = quote;
		this.movie = movie;
	}

	public UserQuoteBuilder year(String year) {
		this.year = year;
		return this;
	}

	public UserQuoteBuilder plot(String plot) {
		this.plot = plot;
		return this;
	}

	public UserQuoteBuilder director(String director) {
		this.director = director;
		return this;
	}

	public UserQuoteBuilder writer(String writer) {
		this.writer = writer;
		return this;
	}

	public UserQuoteBuilder actors(String actors) {
		this.actors = actors;
		return this;
	}

	public Quote build() {
		return new Quote(this);
	}


}
