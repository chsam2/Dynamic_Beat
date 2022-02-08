import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class DynamicBeat extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("/images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/backButtonEntered.png"));
	
	private JButton startButton = new JButton("START");
	private JButton quitButton = new JButton("QUIT");
	private JButton exitButton = new JButton("x");
	
	private JButton rightbutton = new JButton("->");
	private JButton leftbutton = new JButton("<-");
	private JButton easyButton = new JButton("Easy");
	private JButton hardButton = new JButton("Hard");
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private Image background;
		
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	Music introMusicwav = new Music("introMusicwav.wav", true);
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	
	public static Game game;
	
	public DynamicBeat() {
		trackList.add(new Track("RiotStartImage.jpg", "RiotStartImage.jpg", 
				"GameImage.jpg", "TinyRiot.wav", "TinyRiot.wav","Tiny Riot - Sam Ryder"));
		trackList.add(new Track("EnemyStartImage.jpg", "EnemyStartImage.jpg", 
				"GameImage.jpg", "Enemy.wav", "Enemy.wav", "Enemy - Imagin Dragon"));
		trackList.add(new Track("CircleStartImage.jpg", "CircleStartImage.jpg", 
				"GameImage.jpg", "Circle.wav", "Circle.wav", "Circle - Post Malone"));
		
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusicwav.start();
		
		exitButton.setBounds(1240,0,40,40);
		exitButton.setBackground(Color.GRAY);
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40,200,400,100);
		startButton.setBackground(Color.GRAY);
		//startButton.setBorderPainted(false);
		//startButton.setContentAreaFilled(false);
		//startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Game start event
				enterMain();
			}
		});
		add(startButton);
		

		quitButton.setBounds(40,330,400,100);
		quitButton.setBackground(Color.GRAY);
		quitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		leftbutton.setVisible(false);
		leftbutton.setBounds(140,310,60,60);
		leftbutton.setBackground(Color.WHITE);
		leftbutton.setFont(new Font("고딕", Font.BOLD,20));
		leftbutton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				leftbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//왼쪽버튼 이벤트
				selectLeft();
			}
		});
		add(leftbutton);
		
		rightbutton.setVisible(false);
		rightbutton.setBounds(1140 ,310,60,60);
		rightbutton.setBackground(Color.WHITE);
		rightbutton.setFont(new Font("고딕", Font.BOLD,20));
		rightbutton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				rightbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//오른쪽버튼 이벤트
				selectRight();
			}
		});
		add(rightbutton);

		background = new ImageIcon(Main.class.getResource("/images/introBackground.jpg")).getImage();
		
		easyButton.setVisible(false);
		easyButton.setBounds(375 ,580,250,67);
		easyButton.setBackground(Color.GRAY);
		easyButton.setForeground(Color.BLUE);
		easyButton.setFont(new Font("고딕", Font.BOLD,30));
		easyButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//난이도 쉬움버튼
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655 ,580,250,67);
		hardButton.setBackground(Color.GRAY);
		hardButton.setForeground(Color.RED);
		hardButton.setFont(new Font("고딕", Font.BOLD,30));
		hardButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//hard button
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20,50,64,64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//back button go to Main
				backMain();
				
			}
		});
		add(backButton);
		
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics2D g){
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) {
			g.drawImage(selectedImage, 320, 85, null);
		}
		if(isGameScreen) {
			game.screenDraw(g);
		}
		
		paintComponents(g);
		try {
			Thread.sleep(5);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("/images/"+trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("/images/"+trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		}
		else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	public void selectRight() {
		if(nowSelected ==  trackList.size()-1) {
			nowSelected = 0;
		}
		else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen=false;
		leftbutton.setVisible(false);
		rightbutton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("/images/"+trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	}
	public void backMain() {
		isMainScreen = true;
		leftbutton.setVisible(true);
		rightbutton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("/images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}
	public void enterMain() {
		selectTrack(0);
		introMusicwav.close();
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftbutton.setVisible(true);
		rightbutton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("/images/mainBackground.jpg")).getImage();
		isMainScreen = true;
	}
	
}	
