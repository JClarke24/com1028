package com1028_project_jc01663_test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com1028_project_jc01663.Album;
import com1028_project_jc01663.Artist;
import com1028_project_jc01663.FeaturingArtist;
import com1028_project_jc01663.Song;

public class SongTest {

	@Test
	public void testConstructor() {
		List<Song> songs = new ArrayList<Song>();
		List<Album> albums = new ArrayList<Album>();
		List<FeaturingArtist> featurings = new ArrayList<FeaturingArtist>();
		Artist artist = new Artist("Name", songs, albums);
		Album album = new Album("Album Name", songs, artist);
		Song song = new Song("Song Title", "Song Audio File Path", artist, featurings, album);
		
		System.out.println(song.toString());
	}

}
