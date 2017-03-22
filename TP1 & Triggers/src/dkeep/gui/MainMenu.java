package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel{

	private JFrame frame;
	private JButton btnNewGame, btnExit, btnSettings;
	private Game game;
	private OptionsMenu options;
	BufferedImage menuBackground = ImageLoader.loadImage("/MenuBackground.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/*
		 * Initialization of all of the frame's components
		 */

		frame = new JFrame("Main Menu");     
		frame.setContentPane(this);
		frame.setSize(1200, 700);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(75, 10);
		
		repaint();
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(43, 485, 112, 29);
		btnNewGame.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnExit.setBounds(43, 587, 112, 29);
		add(btnExit);
		
		btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSettings.setBounds(43, 536, 112, 29);
		add(btnSettings);
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.setVisible(false);
				game = new Game("Prison Escape", 700, 500);
				game.setVisible(true);
				game.init();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
		
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				
				options = new OptionsMenu("Settings", 300, 500);
				options.setVisible(true);
				options.init();
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewGame);
		
	}
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g); // Clears board
		g.drawImage(menuBackground, 0, 0, 1200, 700, null);
	}
}