package Exia2019.Tron.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Exia2019.Tron.model.LightSquare;
import Exia2019.Tron.model.LightSquareType;
import Exia2019.Tron.model.Model;

public class View extends JFrame implements KeyListener, Observer {
	
	public JPanel panel;
	
	private long startTime = System.currentTimeMillis();
	
	
	public boolean player1turn;
	
	public boolean player1pushed;
	
	public boolean player2pushed;
	
	
	
	
	private Model model;
	
	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}
	
	
	
	private UserOrder player1Order;
	
	private UserOrder player2Order;
	
	
	public UserOrder getPlayer2Order() {
		return player2Order;
	}


	public void setPlayer2Order(UserOrder player2Order) {
		this.player2Order = player2Order;
	}


	public UserOrder getPlayer1Order() {
		return player1Order;
	}


	public void setPlayer1Order(UserOrder player1Order) {
		this.player1Order = player1Order;
	}
	
	
	
	
	public View(final String title, Model model) {
		
		
		
		super(title);
		
		this.model = model;
		this.player1turn = true;
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        
        
        //Set background to Black
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        this.setContentPane(panel);
        
        this.addKeyListener(this);
		
        this.refreshGrid();
        
        this.setVisible(true);
        System.out.println("visible");
        
        
        
        
     }
	
	
	
	
	public void refreshGrid() {
		
		panel.removeAll();
		
		
		int X = 0;
		
		int Y = 0;
			
		for(int i = 0 ; i < 2400 ; i++) {
				
				switch(this.getModel().theGrid.get(i).getType()) {
				
				case VOID :
					this.addSquare(Color.black, X, Y);
					break;
					
				case WALL1 :
					this.addSquare(Color.cyan, X, Y);
					break;
					
				case WALL2 :
					this.addSquare(Color.red, X, Y);
					break;
					
				case WALL3 :
					this.addSquare(Color.LIGHT_GRAY, X, Y);
					
				case PLAYER1 :
					this.addSquare(Color.blue, X, Y);
					break;
					
				case PLAYER2 :
					this.addSquare(Color.magenta, X, Y);
					break;

				default:
					this.addSquare(Color.black, X, Y);
					break;
				
			}
				
			X++;
			
			if ( X == 60 ) {
				Y++;
				X = 0;
			}
			
		}
		
		this.setTitle("Tron EXIA - Time : "+ ((System.currentTimeMillis() - startTime)/1000) + " seconds");

		panel.repaint();
		
	}
	
	
	public void refreshGrid(LightSquare[] table) {
		
		
		
		
		
		for ( int i = 0 ; i < 2 ; i++) {
			
					System.out.println(table[i].getType().toString());
		
					
					switch(table[i].getType()) {
					
					case VOID :
						this.addSquare(Color.black, table[i].getX(), table[i].getY());
						
						break;
						
					case WALL1 :
						this.addSquare(Color.cyan, table[i].getX(), table[i].getY());
						
						break;
						
					case WALL2 :
						this.addSquare(Color.red, table[i].getX(), table[i].getY());
						
						break;
						
					case WALL3 :
						this.addSquare(Color.LIGHT_GRAY, table[i].getX(), table[i].getY());
						
					case PLAYER1 :
						this.addSquare(Color.blue, table[i].getX(), table[i].getY());
						
						break;
						
					case PLAYER2 :
						this.addSquare(Color.magenta, table[i].getX(), table[i].getY());
						
						break;

					default:
						this.addSquare(Color.black, table[i].getX(), table[i].getY());
						break;
					
				}
			
		}
		
		this.setTitle("Tron EXIA - Time : "+ ((System.currentTimeMillis() - startTime)/1000) + " seconds");
		
		
		
		panel.repaint();
		
		
	}
	
	
	
	public void addSquare(final Color color, int x, int y) {
		
		//panel.removeAll();
		
		final int squareX = 10 * x;
		final int squareY = 10 * y;
		
		
		/*panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.fillRect(squareX, squareY, 10, 10);
				g.setColor(color);
			}
		};*/
		
		JPanel square = new JPanel();
        square.setBackground( color );//New.   
        square.setBounds(squareX, squareY, 10, 10);//New.
        panel.add( square );//New.
		
        
		
	}
	
	

	
	


	public void keyTyped(KeyEvent e) {
		// Nothing to see here
		
	}


	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		if(this.player1turn == true && this.player1pushed == false) {
		
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				this.setPlayer1Order(UserOrder.P1LEFT);
				this.player1pushed = true;
				//System.out.println("P1 LEFT");
			

			
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				this.setPlayer1Order(UserOrder.P1RIGHT);
				this.player1pushed = true;
				//System.out.println("P1 RIGHT");
			} else {
				this.setPlayer1Order(UserOrder.NOTHING);
				//System.out.println("P1 ...");
			}
			
			this.player1turn = false;
			
		}
		
		
		if(this.player1turn == false && this.player2pushed == false) {
		
			if (e.getKeyCode() == KeyEvent.VK_K) {
				this.setPlayer2Order(UserOrder.P2LEFT);
				this.player2pushed = true;
				//System.out.println("P2 LEFT");
			

			
			} else if (e.getKeyCode() == KeyEvent.VK_M) {
				this.setPlayer2Order(UserOrder.P2RIGHT);
				this.player2pushed = true;
				//System.out.println("P2 RIGHT");
			} else {
				this.setPlayer2Order(UserOrder.NOTHING);
				//System.out.println("P2 ...");
			}
			
			this.player1turn = true;
		}
		

		
	}


	public void keyReleased(KeyEvent e) {
		
		
			
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				
				this.setPlayer1Order(UserOrder.NOTHING);
				this.player1pushed = false;
				
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				
				this.setPlayer1Order(UserOrder.NOTHING);
				this.player1pushed = false;
				
			}
			
		
			if (e.getKeyCode() == KeyEvent.VK_K) {
				
				this.setPlayer2Order(UserOrder.NOTHING);
				this.player2pushed = false;
			
			} else if (e.getKeyCode() == KeyEvent.VK_M) {
				
				this.setPlayer2Order(UserOrder.NOTHING);
				this.player2pushed = false;
				
			}
	
		
		
	}


	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		System.out.println("La vue vient d'être notifiée");
		
		Model mod = (Model) arg;
		this.setModel(mod);
		this.refreshGrid();
		
		
		
	}








}
