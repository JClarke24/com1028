package com1028_project_jc01663;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class MusicCollectionGUI {

	private JFrame frame;
	JPanel mainPanel = new JPanel();
	JPanel listeningPanel = new JPanel();
	JPanel organizingPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MusicCollectionGUI window = new MusicCollectionGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MusicCollectionGUI() {
		initialize();
		MusicCollection.getInstance();
	}

	/**
	 * Initialise the contents of the frame.
	 * If the user clicks on either of the two buttons, this interface is set to invisible and the chosen one is created.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mainPanel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JButton listeningButton = new JButton("Listen to your music");
		listeningButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListeningPanel();
				mainPanel.setVisible(false);
				listeningPanel.setVisible(true);
			}
		});
		listeningButton.setBounds(44, 114, 154, 50);
		mainPanel.add(listeningButton);

		JButton organizingButton = new JButton("Organize your music");
		organizingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrganizingPanel();
				mainPanel.setVisible(false);
				organizingPanel.setVisible(true);
			}
		});
		organizingButton.setBounds(224, 114, 154, 50);
		mainPanel.add(organizingButton);

		JLabel questionLabel = new JLabel("What would you like to do?");
		questionLabel.setBounds(130, 63, 154, 16);
		mainPanel.add(questionLabel);

		JLabel welcomeLabel = new JLabel("Welcome to your Music!");
		welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setFont(new Font("Impact", Font.PLAIN, 18));
		welcomeLabel.setBounds(120, 33, 179, 16);
		mainPanel.add(welcomeLabel);
	}

	/**
	 * Used to display the "Listening" interface of the program.
	 */
	private void ListeningPanel() {

		listeningPanel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(listeningPanel);
		listeningPanel.setLayout(null);

		JButton backToMenuButton = new JButton("Back to Menu");
		backToMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listeningPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		backToMenuButton.setBounds(10, 13, 109, 25);
		listeningPanel.add(backToMenuButton);

		JTextField songToStringField = new JTextField();
		songToStringField.setBounds(39, 71, 345, 30);
		listeningPanel.add(songToStringField);
		songToStringField.setText("Title - Artist (feat.[]), (Album)");
		songToStringField.setColumns(10);

		JProgressBar songTimeBar = new JProgressBar();
		songTimeBar.setBounds(39, 101, 345, 14);
		listeningPanel.add(songTimeBar);

		JButton skipBackwardButton = new JButton("Skip backward");
		skipBackwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MusicCollection.getInstance().skipBackward()) {
					songToStringField.setText(MusicCollection.getInstance().getCurrentToString());
				} else {
					songToStringField.setText("Collections is empty");
				}
			}
		});
		skipBackwardButton.setBounds(39, 114, 115, 30);
		listeningPanel.add(skipBackwardButton);

		JButton pausePlayButton = new JButton();
		pausePlayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (MusicCollection.getInstance().pausePlay()) {
					songToStringField.setText(MusicCollection.getInstance().getCurrentToString());
				} else {
					songToStringField.setText("Collections is empty");
				}
			}
		});
		pausePlayButton.setText("Pause/Play");
		pausePlayButton.setBounds(154, 114, 115, 30);
		listeningPanel.add(pausePlayButton);

		JButton skipForwardButton = new JButton("Skip forward");
		skipForwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MusicCollection.getInstance().skipForward()) {
					songToStringField.setText(MusicCollection.getInstance().getCurrentToString());
				} else {
					songToStringField.setText("Collections is empty");
				}
			}
		});
		skipForwardButton.setBounds(269, 114, 115, 30);
		listeningPanel.add(skipForwardButton);

		JButton shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusicCollection.getInstance().sortCollection(Order.SHUFFLE);
				MusicCollection.getInstance().pausePlay();
				MusicCollection.getInstance().pausePlay();
				songToStringField.setText(MusicCollection.getInstance().getCurrentToString());
			}
		});
		shuffleButton.setBounds(291, 180, 95, 25);
		listeningPanel.add(shuffleButton);
	}

	/**
	 * Used to display the "Organizing" interface of the program.
	 */
	private void OrganizingPanel() {

		organizingPanel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(organizingPanel);
		organizingPanel.setLayout(null);

		JButton backToMenuButton2 = new JButton("Back to Menu");
		backToMenuButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				organizingPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		backToMenuButton2.setBounds(5, 6, 108, 25);
		organizingPanel.add(backToMenuButton2);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(246, 13, 186, 237);
		organizingPanel.add(scrollPane);

		JLabel addSongLabel = new JLabel("Add a Song:");
		addSongLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addSongLabel.setBounds(15, 36, 85, 16);
		organizingPanel.add(addSongLabel);

		JTextField songTitleField = new JTextField();
		songTitleField.setText("Enter song title");
		songTitleField.setColumns(10);
		songTitleField.setBounds(10, 54, 116, 22);
		organizingPanel.add(songTitleField);

		JTextField audioFilePathField = new JTextField();
		audioFilePathField.setText("Enter audio file path");
		audioFilePathField.setColumns(10);
		audioFilePathField.setBounds(10, 77, 116, 22);
		organizingPanel.add(audioFilePathField);

		JTextField artistNameField = new JTextField();
		artistNameField.setText("Enter artist name");
		artistNameField.setColumns(10);
		artistNameField.setBounds(10, 99, 116, 22);
		organizingPanel.add(artistNameField);

		JTextField featuringsField = new JTextField();
		featuringsField.setText("Enter featurings (separated by commas)");
		featuringsField.setColumns(10);
		featuringsField.setBounds(10, 121, 116, 22);
		organizingPanel.add(featuringsField);

		JTextField albumNameField = new JTextField();
		albumNameField.setText("Enter album name");
		albumNameField.setColumns(10);
		albumNameField.setBounds(10, 143, 116, 22);
		organizingPanel.add(albumNameField);

		JButton addSongButton = new JButton("Add song");
		addSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = songTitleField.getText();
				String audioFilePath = audioFilePathField.getText();
				String artistName = artistNameField.getText();

				if (title.length() < 1 || audioFilePath.length() < 1 || artistName.length() < 1) {
					textArea.setText("Song title, audio file path or artist name is incomplete");
				} else {
					StringTokenizer featuringsTokens = null;
					List<String> featurings = new ArrayList<String>();
					if (featuringsField.getText() != null) {
						featuringsTokens = new StringTokenizer(featuringsField.getText(), ",");
						while (featuringsTokens.hasMoreTokens()) {
							featurings.add(featuringsTokens.nextToken());
						}
					}
					String albumName = albumNameField.getText();

					if (MusicCollection.getInstance().addSong(title, audioFilePath, artistName, featurings,
							albumName)) {
						textArea.setText("Song succesfully added!");
					} else {
						textArea.setText("Song is already in collection.");
					}
				}
			}
		});
		addSongButton.setBounds(10, 165, 116, 20);
		organizingPanel.add(addSongButton);

		JLabel removeSongLabel = new JLabel("Remove a Song:");
		removeSongLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		removeSongLabel.setBounds(10, 185, 116, 16);
		organizingPanel.add(removeSongLabel);

		JTextField songTitleField2 = new JTextField();
		songTitleField2.setText("Enter song title");
		songTitleField2.setColumns(10);
		songTitleField2.setBounds(10, 205, 116, 22);
		organizingPanel.add(songTitleField2);

		JButton removeSongButton = new JButton("Remove song");
		removeSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String songTitle = songTitleField2.getText();
				if (MusicCollection.getInstance().removeSong(songTitle)) {
					textArea.setText("Song succesfully removed!");
				} else {
					textArea.setText("Song could not be found");
				}
			}
		});
		removeSongButton.setBounds(10, 230, 116, 20);
		organizingPanel.add(removeSongButton);

		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchLabel.setBounds(140, 40, 62, 16);
		organizingPanel.add(searchLabel);

		JTextField searchField = new JTextField();
		searchField.setText("Enter keyword");
		searchField.setColumns(10);
		searchField.setBounds(133, 61, 101, 22);
		organizingPanel.add(searchField);

		JButton searchButton = new JButton("Show result");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyword = searchField.getText();
				String result = MusicCollection.getInstance().searchKeyword(keyword);
				if (result != null && result.length() > 0) {
					textArea.setText(result);
				} else {
					textArea.setText("Not matches found");
				}
			}
		});
		searchButton.setBounds(133, 85, 101, 20);
		organizingPanel.add(searchButton);

		JLabel sortLabel = new JLabel("Sort:");
		sortLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortLabel.setBounds(138, 108, 56, 16);
		organizingPanel.add(sortLabel);

		JComboBox sortDropDown = new JComboBox();
		sortDropDown.setModel(new DefaultComboBoxModel(new String[] { "Alphabetical", "Reverse", "Shuffle" }));
		sortDropDown.setBounds(133, 128, 101, 22);
		organizingPanel.add(sortDropDown);

		JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				if (sortDropDown.getSelectedItem().toString() == "Alphabetical") {
					result = MusicCollection.getInstance().sortCollection(Order.ALPHABETICAL);
				} else if (sortDropDown.getSelectedItem().toString() == "Reverse") {
					result = MusicCollection.getInstance().sortCollection(Order.REVERSE);
				} else if (sortDropDown.getSelectedItem().toString() == "Shuffle") {
					result = MusicCollection.getInstance().sortCollection(Order.SHUFFLE);
				}

				if (result == null) {
					textArea.setText("Collection could not be sorted");
				} else if (result.length() < 1) {
					textArea.setText("Collection is empty");
				} else {
					textArea.setText(result);
				}
			}
		});
		sortButton.setBounds(133, 150, 97, 20);
		organizingPanel.add(sortButton);

		JLabel saveLabel = new JLabel("Store/Save:");
		saveLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		saveLabel.setBounds(133, 180, 85, 16);
		organizingPanel.add(saveLabel);

		JButton saveButton = new JButton("Store/Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MusicCollection.getInstance().storeCollection()) {
					textArea.setText("Collection is saved");
				} else {
					textArea.setText("Collection could not be saved");
				}
			}
		});
		saveButton.setBounds(134, 200, 100, 25);
		organizingPanel.add(saveButton);

	}
}
