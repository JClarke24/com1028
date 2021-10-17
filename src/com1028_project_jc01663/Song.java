package com1028_project_jc01663;

import java.util.List;

public class Song implements Comparable<Song> {
	
	private String title = null;
	private String audioFilePath = null;
	private Artist artist = null;
	private List<FeaturingArtist> featurings = null;
	private Album album = null;
	
	/*
	 * Constructor with all the information on a song.
	 */
	public Song(String title, String audioFilePath, Artist artist, List<FeaturingArtist> featurings, Album album) {
		super();
		if(title == null) {
			throw new NullPointerException("Song title is null");
		}
		this.title = title;
		if(audioFilePath == null) {
			throw new NullPointerException("Song audio file path is null");
		}
		this.audioFilePath = audioFilePath;
		if(artist == null) {
			throw new NullPointerException("Artist is null");
		}
		this.artist = artist;
		
		//These fields are not validated as they don't need to have a value.
		this.featurings = featurings;
		this.album = album;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getAudioFilePath() {
		return this.audioFilePath;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public List<FeaturingArtist> getFeaturings() {
		return this.featurings;
	}

	public Album getAlbum() {
		return this.album;
	}
	
	@Override
	public String toString() {
		return this.title + " - " + this.artist.getName() + " (feat." + this.featurings.toString() + "), (" + this.album.getName() + ")";
	}

	@Override
	public int compareTo(Song song) {
		return this.getTitle().compareTo(song.getTitle());
	}

}
