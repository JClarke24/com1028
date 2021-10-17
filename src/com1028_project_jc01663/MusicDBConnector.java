package com1028_project_jc01663;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicDBConnector {

	private Connection connect;
	private Statement statement;

	/*
	 * Creates a MusicDBConnector object and opens a connection to the database.
	 */
	public MusicDBConnector() {
		super();

		this.connect = null;
		this.statement = null;
		this.openConnection();
	}

	/*
	 * Opens a connection to the database located in the project.
	 */
	public void openConnection() {
		
		try {
			if (this.connect == null || this.connect.isClosed()) {
				this.connect = DriverManager.getConnection("jdbc:hsqldb:file:db_data/myDBfilestore;ifexists=true;shutdown=true", "SA", "");
			}
			if (this.statement == null || this.statement.isClosed()) {
				this.statement = this.connect.createStatement();
			}

		} catch (SQLException e) {
			System.out.println("ERRROR - Failed to create a connection to the database");
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * Closes the connection to the database.
	 */
	public void closeConnection() {
		try {

			if (this.statement != null) {
				this.statement.close();
			}
			if (this.connect != null) {
				this.connect.close();
			}
		} catch (Exception e) {
			System.out.print("ERROR - Failed to close the connection to the database");
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * Adds a record to each table in the database for information on a song
	 */
	public void storeSong(Song song) {
		if(song == null) {
			throw new NullPointerException("Invalid song");
		}
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement("INSERT INTO Artist (ArtistName) VALUES (?)");
			preparedStatement.setString(1, song.getArtist().getName());
			preparedStatement.execute();
			
			preparedStatement = this.connect.prepareStatement("INSERT INTO FeaturingArtist (ArtistName) VALUES (?)");
			for(FeaturingArtist feat : song.getFeaturings()) {
				preparedStatement.setString(1, feat.getName());
				preparedStatement.execute();
			}
			
			preparedStatement = this.connect.prepareStatement("INSERT INTO Album (AlbumName, Artist) VALUES (?,?)");
			preparedStatement.setString(1, song.getAlbum().getName());
			preparedStatement.setString(2, song.getArtist().getName());
			preparedStatement.execute();
			
			preparedStatement = this.connect.prepareStatement("INSERT INTO Song (Title, AudioFilePath, Artist, Album) VALUES (?,?,?,?)");
			preparedStatement.setString(1, song.getTitle());
			preparedStatement.setString(2, song.getAudioFilePath());
			preparedStatement.setString(3, song.getArtist().getName());
			preparedStatement.setString(4, song.getAlbum().getName());
			preparedStatement.execute();
			
			preparedStatement = this.connect.prepareStatement("INSERT INTO Features (Song, FeaturingArtist) VALUES (?,?)");
			for(FeaturingArtist feat : song.getFeaturings()) {
				preparedStatement.setString(1, song.getTitle());
				preparedStatement.setString(2, feat.getName());
				preparedStatement.execute();
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException happened while writing a song- abort programmme");
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * Retrieves records from the database and adds them directly to the collection using the addSong method.
	 * Closes the connection to the database when finished.
	 */
	public void getSongs() {
		try {
			String query = "SELECT * FROM Song";

			ResultSet results = this.statement.executeQuery(query);

			while (results.next()) {

				String title = results.getString("Title");
				String audioFilePath = results.getString("AudioFilePath");
				String artistName = results.getString("Artist");
				String albumName = results.getString("Album");
				
				List<String> featurings = new ArrayList<String>();
				ResultSet features = this.statement.executeQuery("Select * FROM Features");
				while (features.next()) {
					if(features.getString("Song") == title) {
						featurings.add(features.getString("FeaturingArtist"));
					}
				}
				
				MusicCollection.getInstance().addSong(title, audioFilePath, artistName, featurings, albumName);
			}
			
			closeConnection();

		} catch (SQLException e) {
			System.out.println("SQLException happened while retrieving records- abort programmme");
			throw new RuntimeException(e);
		}
	}

}
