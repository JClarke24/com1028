package com1028_project_jc01663_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com1028_project_jc01663.MusicCollection;
import com1028_project_jc01663.Order;

public class MusicCollectionTest {

	@Test
	public void testConstructor() {
		MusicCollection.getInstance();
	}
	
	@Test
	public void testAddSong() {
		List<String> featurings = new ArrayList<String>();
		assertTrue(MusicCollection.getInstance().addSong("songTitle", "audioFilePath", "artistName", featurings, "albumName"));
	}
	
	@Test
	public void testSearchKeyword() {
		List<String> featurings = new ArrayList<String>();
		MusicCollection.getInstance().addSong("title", "audioFilePath", "artistName", featurings, "albumName");
		assertEquals("title - artistName (feat.[]), (albumName)\n", MusicCollection.getInstance().searchKeyword("title"));
	}
	
	@Test
	public void testSortCollection() {
		List<String> featurings = new ArrayList<String>();
		MusicCollection.getInstance().addSong("firstTitle", "audioFilePath", "artistName", featurings, "albumName");
		MusicCollection.getInstance().addSong("secondTitle", "secondAudioFilePath", "secondArtistName", featurings, "secondAlbumName");
		assertEquals("secondTitle - secondArtistName (feat.[]), (secondAlbumName)\n"+"firstTitle - artistName (feat.[]), (albumName)\n", MusicCollection.getInstance().sortCollection(Order.REVERSE));
	}
	
	@Test
	public void testRemoveSong() {
		assertTrue(MusicCollection.getInstance().removeSong("title"));
	}
	
	@Test
	public void testStoreCollection() {
		assertTrue(MusicCollection.getInstance().storeCollection());
	}

}
