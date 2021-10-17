package com1028_project_jc01663;

import java.util.List;

public class Album {
	
	private String name = null;
	private List<Song> songs = null;
	private Artist artist = null;
	
	public Album(String name, List<Song> songs, Artist artist) {
		super();
		if(name == null) {
			throw new NullPointerException("Album name is null");
		}
		this.name = name;
		if(songs == null) {
			throw new NullPointerException("Song list is null");
		}
		this.songs = songs;
		if(artist == null) {
			throw new NullPointerException("Artist is null");
		}
		this.artist = artist;
	}
	
	/*
	 * Adds a song to the album.
	 */
	public void addSong(Song song) {
		if(song == null) {
			throw new NullPointerException("Song is null");
		}
		this.songs.add(song);
	}

	public String getName() {
		return this.name;
	}


	
	

}
