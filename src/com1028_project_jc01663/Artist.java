package com1028_project_jc01663;

import java.util.List;

public class Artist {
	
	private String name = null;
	private List<Song> songs = null;
	private List<Album> albums = null;
	
	public Artist(String name, List<Song> songs, List<Album> albums) {
		super();
		if(name == null) {
			throw new NullPointerException("Null artist name");
		}
		this.name = name;
		if(songs == null) {
			throw new NullPointerException("Song list is null");
		}
		this.songs = songs;
		if(albums == null) {
			throw new NullPointerException("Album list is null");
		}
		this.albums = albums;
	}
	
	/*
	 * Adds a song to the artist songs.
	 */
	public void addSong(Song song) {
		if(song == null) {
			throw new NullPointerException("Song is null");
		}
		this.songs.add(song);
	}
	
	/*
	 * Adds an album to the artist albums.
	 */
	public void addAlbum(Album album) {
		if(album == null) {
			throw new NullPointerException("Album is null");
		}
		this.albums.add(album);
	}

	public String getName() {
		return this.name;
	}


}
