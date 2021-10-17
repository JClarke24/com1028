package com1028_project_jc01663;

import java.util.List;

public class FeaturingArtist extends Artist {
	
	private List<Song> features = null;
	
	public FeaturingArtist(String name, List<Song> songs, List<Album> albums, List<Song> features) {
		super(name, songs, albums);
		if(features == null) {
			throw new NullPointerException("Featuring song list is null");
		}
		this.features = features;
	}
	
	/*
	 * Adds a song to the artist featured songs.
	 */
	public void addFeaturedSong(Song song) {
		if(song == null) {
			throw new NullPointerException("Song is null");
		}
		this.features.add(song);
	}
	
}
