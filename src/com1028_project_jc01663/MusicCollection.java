package com1028_project_jc01663;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicCollection {

	/*
	 * Static fields as we only want a single instance of these.
	 */
	private static List<Song> songs = null;
	private static Song current = null;
	private static MusicCollection instance = null;
	private static MusicPlayer player = null;
	private static MusicDBConnector dbConnector = null;

	/*
	 * Private constructor because of singleton class design.
	 */
	private MusicCollection() {
	}

	/*
	 * Adds a song to the collection after doing a few steps:
	 * - Creates empty temporary variables so that objects can be created without getting null pointer exception.
	 * - Creates an album if the parameter passed isn't null.
	 * - Creates and fills up the list of featuring if the parameter passed isn't empty.
	 * - If the collection isn't empty, checks if the song is already added. 
	 * - Also checks if the artist, album or featuring artist already exist in the collection.
	 * - Create the song object and complete the empty lists.
	 * 
	 * Returns true if the song is successfully added. False if the song already exists.
	 */
	public boolean addSong(String title, String audioFilePath, String artistName, List<String> featurings,
			String albumName) {
		if (title == null || audioFilePath == null || artistName == null) {
			throw new NullPointerException("Song title, audio file path or artist name are invalid");
		}

		List<Song> tempSongList = new ArrayList<Song>();
		List<Album> tempAlbumList = new ArrayList<Album>();

		Artist artist = new Artist(artistName, tempSongList, tempAlbumList);

		Album album = null;
		if (albumName != null) {
			album = new Album(albumName, tempSongList, artist);
		}

		List<FeaturingArtist> featuringArtists = new ArrayList<FeaturingArtist>();
		if (featurings != null && !featurings.isEmpty()) {
			for (String featuring : featurings) {
				featuringArtists.add(new FeaturingArtist(featuring, tempSongList, tempAlbumList, tempSongList));
			}
		}

		if (!songs.isEmpty()) {
			for (Song s : songs) {
				if (s.getTitle().contentEquals(title)) {
					return false;
				}
				if (s.getArtist().getName() == artistName) {
					artist = s.getArtist();
				}
				if (s.getAlbum().getName() == albumName) {
					album = s.getAlbum();
				}
				for (FeaturingArtist f : s.getFeaturings()) {
					for (FeaturingArtist feat : featuringArtists) {
						if (f.getName() == feat.getName()) {
							feat = f;
						}
					}
				}
			}
		}

		Song song = new Song(title, audioFilePath, artist, featuringArtists, album);

		song.getArtist().addSong(song);
		song.getArtist().addAlbum(album);

		if (song.getAlbum() != null) {
			song.getAlbum().addSong(song);
		}

		if (!song.getFeaturings().isEmpty()) {
			for (FeaturingArtist featuringArtist : song.getFeaturings()) {
				featuringArtist.addFeaturedSong(song);
			}
		}

		songs.add(song);

		return true;

	}

	/*
	 * Removes a song if it exists in the collection.
	 */
	public boolean removeSong(String songTitle) {
		for (Song s : songs) {
			if (s.getTitle().contentEquals(songTitle)) {
				songs.remove(s);
				return true;
			}
		}
		return false;
	}

	/*
	 * return a string with all songs that have toString that contains or are contained by the keyword.
	 */
	public String searchKeyword(String keyword) {
		StringBuffer str = new StringBuffer();

		for (Song s : songs) {
			if (s.toString().contains(keyword) || keyword.contains(s.toString())) {
				str.append(s.toString() + "\n");
			}
		}
		return str.toString();
	}

	/*
	 * Sorts the collection depending on the Order chosen.
	 * Also updates the current song.
	 */
	public String sortCollection(Order order) {
		StringBuffer str = new StringBuffer();

		if (!songs.isEmpty()) {
			if (order.equals(Order.ALPHABETICAL)) {
				current = songs.get(0);
			} else if (order.equals(Order.REVERSE)) {
				Collections.sort(songs);
				Collections.reverse(songs);
				current = songs.get(0);
			} else if (order.equals(Order.SHUFFLE)) {
				Collections.shuffle(songs);
				current = songs.get(0);
			}

			for (Song s : songs) {
				str.append(s.toString() + "\n");
			}
		}
		return str.toString();
	}

	/*
	 * Uses the MusicDBConnector to store each song in the collection.
	 */
	public boolean storeCollection() {
		if (!songs.isEmpty()) {
			dbConnector = new MusicDBConnector();
			for (Song song : songs) {
				dbConnector.storeSong(song);
			}
			dbConnector.closeConnection();
		}
		return true;
	}

	/*
	 * Starts playing the song if no song is playing. Stops the song if a song is playing.
	 */
	public boolean pausePlay() {
		if (!songs.isEmpty()) {
			if (!player.isPlaying()) {
				if (current == null) {
					current = songs.get(0);
				}
				player.play(current.getAudioFilePath());
			} else {
				player.pause();
			}
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Stops the currently playing song and updates the current song to the next one in the collection.
	 * If the current song is the last in the collection, the next one will be the first in the collection.
	 */
	public boolean skipForward() {
		if (!songs.isEmpty()) {
			if (player.isPlaying()) {
				player.pause();
			}
			if (songs.size() > songs.indexOf(current) + 1) {
				current = songs.get(songs.indexOf(current) + 1);
			} else {
				current = songs.get(0);
			}
			player.play(current.getAudioFilePath());
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Similar to skipForward method.
	 */
	public boolean skipBackward() {
		if (!songs.isEmpty()) {
			if (player.isPlaying()) {
				player.pause();
			}
			if (songs.indexOf(current) != 0) {
				current = songs.get(songs.indexOf(current) - 1);
			} else {
				current = songs.get(songs.size() - 1);
			}
			player.play(current.getAudioFilePath());
			return true;
		} else {
			return false;
		}
	}

	public String getCurrentToString() {
		return current.toString();
	}

	/*
	 * Returns the singleton instance of the class.
	 * If it isn't created yet, creates it as well as some of the fields.
	 */
	public static MusicCollection getInstance() {
		if (instance == null) {
			instance = new MusicCollection();
			songs = new ArrayList<Song>();
			player = new MusicPlayer();
			dbConnector = new MusicDBConnector();
			dbConnector.getSongs();
		}
		return instance;
	}

}
