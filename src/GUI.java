import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GUI extends JFrame implements KeyEventDispatcher {

	Engine engine=new Engine();
	JLabel map[][] = new JLabel[9][8];
	JPanel centralPanel = new JPanel();
	JButton buttonNewGame = new JButton("New Game");
	public GUI()
	{
		super("Sokoban");
		setBounds(500,100,0,0);
		setSouthPanel();
		setCentralPanel();
		refreshGraphics();
		setResizable(false);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setSouthPanel() {
		add(buttonNewGame, BorderLayout.SOUTH);
		buttonNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.init();
				refreshGraphics();
			}
		});
	}
	private void setCentralPanel() {
		centralPanel.setLayout(new GridLayout(map.length, map[0].length, 0, 0));
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				MyLabel mojePolje = new MyLabel(i, j);
				mojePolje.setPreferredSize(new Dimension(55,55));
				map[i][j] = mojePolje;
				centralPanel.add(map[i][j]);
			}
		}
		
		add(centralPanel, BorderLayout.CENTER);
	}
	private void refreshGraphics() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j].removeAll();
				map[i][j].revalidate();
				map[i][j].repaint();
				
				Border b = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
				
				map[i][j].setIcon(new ImageIcon(engine.Picture(i, j)));
				map[i][j].setBorder(b);
			}
		}
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				engine.moveMan(Direction.up);
				refreshGraphics();
				if(engine.endGame()) {
					engine.init();
					int answer = JOptionPane.showConfirmDialog(null, "You have successfully stacked the boxes! You want to play again?", "END!", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						engine.init();
						refreshGraphics();
					}
					else
						System.exit(0);
				}
				break;
			case KeyEvent.VK_DOWN:
				engine.moveMan(Direction.down);
				refreshGraphics();
				if(engine.endGame()) {
					engine.init();
					int answer = JOptionPane.showConfirmDialog(null, "You have successfully stacked the boxes! You want to play again?", "END!", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						engine.init();
						refreshGraphics();
					}
					else
						System.exit(0);
				}
				break;
			case KeyEvent.VK_LEFT:
				engine.moveMan(Direction.left);
				refreshGraphics();
				if(engine.endGame()) {
					engine.init();
					int answer = JOptionPane.showConfirmDialog(null, "You have successfully stacked the boxes! You want to play again?", "END!", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						engine.init();
						refreshGraphics();
					}
					else
						System.exit(0);
				}
				break;
			case KeyEvent.VK_RIGHT:
				engine.moveMan(Direction.right);
				refreshGraphics();
				if(engine.endGame()) {
					engine.init();
					int answer = JOptionPane.showConfirmDialog(null, "You have successfully stacked the boxes! You want to play again?", "END!", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						engine.init();
						refreshGraphics();
					}
					else
						System.exit(0);
				}
				break;
			default:
				if(engine.endGame()) {
					engine.init();
					int answer = JOptionPane.showConfirmDialog(null, "You have successfully stacked the boxes! You want to play again?", "END!", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						engine.init();
						refreshGraphics();
					}
					else
						System.exit(0);
				}
				break;
			}
		}
		
		return false;
	}
}
