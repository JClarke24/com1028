package com1028_project_jc01663_test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com1028_project_jc01663.Album;
import com1028_project_jc01663.Artist;
import com1028_project_jc01663.Song;

public class ArtistTest {

	@Test
	public void testConstructor() {
		List<Song> songs = new ArrayList<Song>();
		List<Album> albums = new ArrayList<Album>();
		Artist artist = new Artist("Name", songs, albums);
	}

}
