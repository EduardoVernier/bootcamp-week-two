package com.iws.futuretalents.quotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Quote {

	@SerializedName("quote")
	@Expose
	private String quote;

	@SerializedName("author")
	@Expose
	private String movie;

	@SerializedName("category")
	@Expose
	private String category;

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return movie;
	}

	public void setAuthor(String movie) {
		this.movie = movie;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
