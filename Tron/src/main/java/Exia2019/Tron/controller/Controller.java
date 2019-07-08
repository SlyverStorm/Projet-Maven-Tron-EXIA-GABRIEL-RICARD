package Exia2019.Tron.controller;

import javax.swing.JFrame;

import Exia2019.Tron.model.Model;
import Exia2019.Tron.view.UserOrder;
import Exia2019.Tron.view.View;
import Exia2019.Tron.view.WinScreen;

public class Controller {
	
	private View view;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	

	private Model model;
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	
	private int speed = 10;
	
	
	
	public void play() throws InterruptedException {
		
		while( this.getModel().p1isAlive && this.getModel().p2isAlive) {
			
			Thread.sleep(speed);
		
			if ( this.getOrderFromP1() == UserOrder.P1RIGHT ) {
				
				this.getModel().movePlayer1ToTheRight();
				
			} else if ( this.getOrderFromP1() == UserOrder.P1LEFT ) {
				
				this.getModel().movePlayer1ToTheLeft();
			
			} else if ( this.getOrderFromP1() == UserOrder.NOTHING ) {
				
				this.getModel().movePlayer1Forward();
			
			} else {
				
				this.getModel().movePlayer1Forward();
				
			}
			
			
		
			if ( this.getOrderFromP2() == UserOrder.P2RIGHT ) {
				
				this.getModel().movePlayer2ToTheRight();
				
			} else if ( this.getOrderFromP2() == UserOrder.P2LEFT ) {
				
				this.getModel().movePlayer2ToTheLeft();
				
			} else if ( this.getOrderFromP2() == UserOrder.NOTHING ) {
				
				this.getModel().movePlayer2Forward();
				
			} else {
				
				this.getModel().movePlayer2Forward();
				
			}
			
		}
		
		System.out.println("////////////////////////////////////////////////");
		System.out.println("");
		System.out.println("GAME OVER !!!!!!");
		
		
		if ( this.getModel().p1isAlive == true && this.getModel().p2isAlive == false) {
			
			System.out.println("Blue WIN !");
			JFrame endFrame = new JFrame();
			endFrame.setTitle("Tron EXIA - Jeu Fini");
			
			WinScreen  win = new WinScreen("! BLUE WIN !");
			
			endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    endFrame.setSize(600, 400);
		    endFrame.setResizable(false);
			
			endFrame.setContentPane(win);
			
			endFrame.setVisible(true);
			
			
		}	
		
		if ( this.getModel().p1isAlive == false && this.getModel().p2isAlive == true) {
			
			System.out.println("Red WIN !");
			
			JFrame endFrame = new JFrame();
			endFrame.setTitle("Tron EXIA - Jeu Fini");
			
			WinScreen  win = new WinScreen("! RED WIN !");
			
			endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    endFrame.setSize(600, 400);
		    endFrame.setResizable(false);
			
			endFrame.setContentPane(win);
			
			endFrame.setVisible(true);
			
		}	
		
		if ( this.getModel().p1isAlive == false && this.getModel().p2isAlive == false) {
			
			System.out.println("DRAW !");
			
			JFrame endFrame = new JFrame();
			endFrame.setTitle("Tron EXIA - Jeu Fini");
			
			WinScreen  win = new WinScreen("! DRAW !");
			
			endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    endFrame.setSize(600, 400);
		    endFrame.setResizable(false);
			
			endFrame.setContentPane(win);
			
			endFrame.setVisible(true);
			
		}	
		
		System.out.println("");
		System.out.println("////////////////////////////////////////////////");
		
		
	}
	
	public UserOrder getOrderFromP1() {
		return this.getView().getPlayer1Order();
	}
	
	public UserOrder getOrderFromP2() {
		return this.getView().getPlayer2Order();
	}
	
	
	
	
	
	
	public Controller(View sView, Model sModel) {
		
		this.setModel(sModel);
		
		this.setView(sView);
		
		
	}
	

}
