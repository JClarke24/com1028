package com1028_project_jc01663;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	
	private FileInputStream fileInputStream = null;
	private BufferedInputStream bufferedInputStream = null;
	private Player player = null;
	private boolean isPlaying = false;
	
	/*
	 * Method that plays an audio file by starting a new thread so that the user can still use the interface.
	 */
	public void play(String audioFilePath) {
		if(audioFilePath == null) {
			throw new NullPointerException("Audio file path is null");
		}
		try {
			this.fileInputStream = new FileInputStream(audioFilePath);
			this.bufferedInputStream = new BufferedInputStream(fileInputStream);
			this.player = new Player(bufferedInputStream);
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}
		
		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		this.isPlaying = true;
	}
	
	/*
	 * Method that stops the song.
	 */
	public void pause() {
		this.player.close();
		this.isPlaying = false;
	}
	
	/*
	 * Returns true if a song is playing.
	 */
	public boolean isPlaying() {
		return this.isPlaying;
	}
}
