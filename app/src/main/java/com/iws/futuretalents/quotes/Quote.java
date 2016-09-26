package com.iws.futuretalents.quotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

// All members must be public in order for the Parceler lib to work :/
// TODO: Clean getters and setters
@Parcel
public class Quote {

	@SerializedName("quote")
	@Expose
	public String quote;
	@SerializedName("author")
	@Expose
	public String movieTitle;
	public Movie movieData;

	public String getQuote() {
		return quote;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	@Parcel
	public static class Movie {

		@SerializedName("Title")
		@Expose
		public String title;
		@SerializedName("Year")
		@Expose
		public String year;
		@SerializedName("Director")
		@Expose
		public String director;
		@SerializedName("Writer")
		@Expose
		public String writer;
		@SerializedName("Actors")
		@Expose
		public String actors;
		@SerializedName("Plot")
		@Expose
		public String plot;
		@SerializedName("Poster")
		@Expose
		public String poster;
		@SerializedName("imdbRating")
		@Expose
		public String imdbRating;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public String getActors() {
			return actors;
		}

		public void setActors(String actors) {
			this.actors = actors;
		}

		public String getPlot() {
			return plot;
		}

		public void setPlot(String plot) {
			this.plot = plot;
		}

		public String getPoster() {
			return poster;
		}

		public void setPoster(String poster) {
			this.poster = poster;
		}

		public String getImdbRating() {
			return imdbRating;
		}

		public void setImdbRating(String imdbRating) {
			this.imdbRating = imdbRating;
		}
	}
}
