package Exia2019.Tron;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Exia2019.Tron.model.LightSquare;
import Exia2019.Tron.model.LightSquareType;
import Exia2019.Tron.model.Model;
import Exia2019.Tron.view.View;
import Exia2019.Tron.controller.Controller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	
    	Model model = new Model(10, 20, 50, 20);
    	
    	//model.movePlayer1Forward();
    	
    	//model.movePlayer2Forward();
    	
    	//System.out.println("GAME !!!!!");
    	
    	
    	View view = new View("Test", model);
    	

    	
    	model.setObserser(view);
    	
    	
    	//Model model1 = new Model(10, 20, 50, 20);
    	
    	//view.setModel(model1);
    	
    	//view.refreshGrid();
    	
  
    	Controller controller = new Controller(view, model);
    	
    	controller.play();

    }
}
