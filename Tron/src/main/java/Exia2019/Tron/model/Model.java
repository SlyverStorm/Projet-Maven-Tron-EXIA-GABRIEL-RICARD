package Exia2019.Tron.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	
	public ArrayList<LightSquare> theGrid = new ArrayList<LightSquare>();
	Observer observer;
	
	private int player1X;
	
	public int getPlayer1X() {
		return player1X;
	}

	public void setPlayer1X(int player1x) {
		player1X = player1x;
	}
	
	
	
	private int player1Y;
	
	public int getPlayer1Y() {
		return player1Y;
	}

	public void setPlayer1Y(int player1y) {
		player1Y = player1y;
	}
	
	
	
	private int player2X;
	
	public int getPlayer2X() {
		return player2X;
	}

	public void setPlayer2X(int player2x) {
		player2X = player2x;
	}
	
	
	
	private int player2Y;
	
	public int getPlayer2Y() {
		return player2Y;
	}

	public void setPlayer2Y(int player2y) {
		player2Y = player2y;
	}
	
	
	
	private Facing p1facing = Facing.RIGHT;
	
	public Facing getP1facing() {
		return p1facing;
	}

	public void setP1facing(Facing p1facing) {
		this.p1facing = p1facing;
	}
	
	
	
	private Facing p2facing = Facing.LEFT;
	
	public Facing getP2facing() {
		return p2facing;
	}

	public void setP2facing(Facing p2facing) {
		this.p2facing = p2facing;
	}
	
	
	
	public boolean p1isAlive;
	
	public boolean p2isAlive;
	
	
	
	
	
	public void setObserser(Observer observer) {
		this.addObserver(observer);
	}
	
	
	
	
	public Model(final int P1X, final int P1Y, final int P2X, final int P2Y) {
		
		
		this.p1isAlive = true;
		this.p2isAlive = true;
		
		this.setP1facing(Facing.RIGHT);
		this.setP2facing(Facing.LEFT);
		
		this.setPlayer1X(P1X); //10
		this.setPlayer1Y(P1Y); //20
		
		this.setPlayer2X(P2X); //50
		this.setPlayer2Y(P2Y); //20
		
		int X = 0;
		int Y = 0; 
		
		for(int i = 0 ; i < 2400 ; i++) {
				
				this.theGrid.add(null);
				LightSquare backGroundSquare = new LightSquare(X,Y);
				this.theGrid.set(i, backGroundSquare);
				//System.out.println("["+i+"] created");
				X++;
				
				if(X == 60) {
					Y++;
					X = 0;
				}
			
		}
		
		for (int i = 0 ; i < 60 ; i++) {
			
			LightSquare outWall = new LightSquare(LightSquareType.WALL3, 0, 0);
			this.theGrid.set(i, outWall);
			
			
		}
		
		for (int i = 2100 ; i < 2400 ; i++) {
			
			LightSquare outWall = new LightSquare(LightSquareType.WALL3, 0, 0);
			this.theGrid.set(i, outWall);
			
			
		}
		
		for (int i = 60 ; i < 2400 ; i = i + 60) {
			
			LightSquare outWall = new LightSquare(LightSquareType.WALL3, 0, 0);
			this.theGrid.set(i, outWall);
			
		}
		
		for (int i = 59 ; i < 2400 ; i = i + 60) {
			
			LightSquare outWall = new LightSquare(LightSquareType.WALL3, 0, 0);
			this.theGrid.set(i, outWall);
			
		}
		
		LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, 10, 20);
		this.theGrid.set(((this.getPlayer1Y()*60)+this.getPlayer1X()), p1);
		
		LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, 50, 39);
		this.theGrid.set(((this.getPlayer2Y()*60)+this.getPlayer2X()), p2);
		
		
		
		
	}
	
	
	public void movePlayer1ToTheLeft() {
		
		if( this.getP1facing() == Facing.UP ) {
			
			if( this.theGrid.get(((this.getPlayer1Y() * 60) + (this.getPlayer1X() - 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() - 1, this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X() - 1)), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1X(this.getPlayer1X() - 1);
				this.setP1facing(Facing.LEFT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
				
				
				
			} else {
				this.p1isAlive = false;
			}
		
			
		} else if ( this.getP1facing() == Facing.RIGHT ) {
			
			if( this.theGrid.get((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() - 1);
				this.theGrid.set((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X())), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1Y(this.getPlayer1Y() - 1);
				this.setP1facing(Facing.UP);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}
			
			
		} else if ( this.getP1facing() == Facing.DOWN ) {
			
			if( this.theGrid.get((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() + 1, this.getPlayer1Y());
				this.theGrid.set((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1)), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1X(this.getPlayer1X() + 1);
				this.setP1facing(Facing.RIGHT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}
			
			
		} else if ( this.getP1facing() == Facing.LEFT ) {
			
			if( this.theGrid.get((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() + 1);
				this.theGrid.set((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X())), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1Y(this.getPlayer1Y() + 1);
				this.setP1facing(Facing.DOWN);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}


		}
		
	}
	
	

	
	
	
	
	public void movePlayer1ToTheRight() {
		
		if( this.getP1facing() == Facing.UP ) {
			
			if( this.theGrid.get((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() + 1, this.getPlayer1Y());
				this.theGrid.set((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1)), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1X(this.getPlayer1X() + 1);
				this.setP1facing(Facing.RIGHT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}
		
			
		} else if ( this.getP1facing() == Facing.RIGHT ) {
			
			if( this.theGrid.get((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() + 1);
				this.theGrid.set((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X())), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1Y(this.getPlayer1Y() + 1);
				this.setP1facing(Facing.DOWN);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}
			
			
		} else if ( this.getP1facing() == Facing.DOWN ) {
			
			if( this.theGrid.get((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() - 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() - 1, this.getPlayer1Y());
				this.theGrid.set((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() - 1)), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1X(this.getPlayer1X() - 1);
				this.setP1facing(Facing.LEFT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}
			
			
		} else if ( this.getP1facing() == Facing.LEFT ) {
			
			if( this.theGrid.get((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() - 1);
				this.theGrid.set((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X())), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
				
				this.setPlayer1Y(this.getPlayer1Y() - 1);
				this.setP1facing(Facing.UP);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p1isAlive = false;
			}


		}
		
	}
	
	
	
	
	
	
	
	public void movePlayer2ToTheLeft() {
		
		if( this.getP2facing() == Facing.UP ) {
			
			if( this.theGrid.get(((this.getPlayer2Y() * 60) + (this.getPlayer2X() - 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() - 1, this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X() - 1)), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2X(this.getPlayer2X() - 1);
				this.setP2facing(Facing.LEFT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
		
			
		} else if ( this.getP2facing() == Facing.RIGHT ) {
			
			if( this.theGrid.get((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() - 1);
				this.theGrid.set((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X())), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2Y(this.getPlayer2Y() - 1);
				this.setP2facing(Facing.UP);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
			
			
		} else if ( this.getP2facing() == Facing.DOWN ) {
			
			if( this.theGrid.get((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() + 1, this.getPlayer2Y());
				this.theGrid.set((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1)), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2X(this.getPlayer2X() + 1);
				this.setP2facing(Facing.RIGHT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
			
			
		} else if ( this.getP2facing() == Facing.LEFT ) {
			
			if( this.theGrid.get((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() + 1);
				this.theGrid.set((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X())), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2Y(this.getPlayer2Y() + 1);
				this.setP2facing(Facing.DOWN);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}


		}
		
	}
	
	
	
	public void movePlayer2ToTheRight() {
		
		if( this.getP2facing() == Facing.UP ) {
			
			if( this.theGrid.get((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() + 1, this.getPlayer2Y());
				this.theGrid.set((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1)), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2X(this.getPlayer2X() + 1);
				this.setP2facing(Facing.RIGHT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
		
			
		} else if ( this.getP2facing() == Facing.RIGHT ) {
			
			if( this.theGrid.get((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() + 1);
				this.theGrid.set((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X())), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2Y(this.getPlayer2Y() + 1);
				this.setP2facing(Facing.DOWN);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
			
			
		} else if ( this.getP2facing() == Facing.DOWN ) {
			
			if( this.theGrid.get((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() - 1))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() - 1, this.getPlayer2Y());
				this.theGrid.set((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() - 1)), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2X(this.getPlayer2X() - 1);
				this.setP2facing(Facing.LEFT);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}
			
			
		} else if ( this.getP2facing() == Facing.LEFT ) {
			
			if( this.theGrid.get((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
				
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() - 1);
				this.theGrid.set((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X())), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
				
				this.setPlayer2Y(this.getPlayer2Y() - 1);
				this.setP2facing(Facing.UP);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);
				
			} else {
				this.p2isAlive = false;
			}


		}
		
		
		
	}
	
	
	
	
	public void movePlayer1Forward() {
		
		if( this.getP1facing() == Facing.UP ) {
			
			if( this.theGrid.get((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
			
				LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() - 1);
				this.theGrid.set((((this.getPlayer1Y() - 1) * 60) + (this.getPlayer1X())), p1);
				
				LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
				this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
			
				this.setPlayer1Y(this.getPlayer1Y() - 1);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p1;
				table[1] = w1;
				
				this.setChanged();
				this.notifyObservers(this);

			
			} else {
				this.p1isAlive = false;
		}
		
		
	} else if ( this.getP1facing() == Facing.RIGHT ) {
		
		if( this.theGrid.get((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1))).getType() == LightSquareType.VOID ) {
			
			LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() + 1, this.getPlayer1Y());
			this.theGrid.set((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() + 1)), p1);
			
			LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
			this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
			
			this.setPlayer1X(this.getPlayer1X() + 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p1;
			table[1] = w1;
			
			this.setChanged();
			this.notifyObservers(this);
			
			

			
		} else {
			this.p1isAlive = false;
		}


	} else if ( this.getP1facing() == Facing.DOWN ) {
		
		if( this.theGrid.get((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X()))).getType() == LightSquareType.VOID ) {
			
			LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X(), this.getPlayer1Y() + 1);
			this.theGrid.set((((this.getPlayer1Y() + 1) * 60) + (this.getPlayer1X())), p1);
			
			LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
			this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
			
			this.setPlayer1Y(this.getPlayer1Y() + 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p1;
			table[1] = w1;
			
			this.setChanged();
			this.notifyObservers(this);

			
		} else {
			this.p1isAlive = false;
		}
		
		
	} else if ( this.getP1facing() == Facing.LEFT ) {
		
		if ( this.theGrid.get((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() - 1))).getType() == LightSquareType.VOID ) {
			
			LightSquare p1 = new LightSquare(LightSquareType.PLAYER1, this.getPlayer1X() - 1, this.getPlayer1Y());
			this.theGrid.set((((this.getPlayer1Y()) * 60) + (this.getPlayer1X() - 1)), p1);
			
			LightSquare w1 = new LightSquare(LightSquareType.WALL1, this.getPlayer1X(), this.getPlayer1Y());
			this.theGrid.set(((this.getPlayer1Y() * 60) + (this.getPlayer1X())), w1);
			
			this.setPlayer1X(this.getPlayer1X() - 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p1;
			table[1] = w1;
			
			this.setChanged();
			this.notifyObservers(this);

			
		} else {
			this.p1isAlive = false;
		}


	}
		
		
		
	}


	
	
	public void movePlayer2Forward() {
		
		if( this.getP2facing() == Facing.UP ) {
			
			if( this.theGrid.get((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
			
				LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() - 1);
				this.theGrid.set((((this.getPlayer2Y() - 1) * 60) + (this.getPlayer2X())), p2);
				
				LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
				this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
			
				this.setPlayer2Y(this.getPlayer2Y() - 1);
				
				LightSquare[] table = new LightSquare[2];
				table[0] = p2;
				table[1] = w2;
				
				this.setChanged();
				this.notifyObservers(this);

			
			} else {
				this.p2isAlive = false;
		}
		
		
	} else if ( this.getP2facing() == Facing.RIGHT ) {
		
		if( this.theGrid.get((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1))).getType() == LightSquareType.VOID ) {
			
			LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() + 1, this.getPlayer2Y());
			this.theGrid.set((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() + 1)), p2);
			
			LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
			this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
			
			this.setPlayer2X(this.getPlayer2X() + 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p2;
			table[1] = w2;
			
			this.setChanged();
			this.notifyObservers(this);

			
		} else {
			this.p2isAlive = false;
		}


	} else if ( this.getP2facing() == Facing.DOWN ) {
		
		if( this.theGrid.get((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X()))).getType() == LightSquareType.VOID ) {
			
			LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X(), this.getPlayer2Y() + 1);
			this.theGrid.set((((this.getPlayer2Y() + 1) * 60) + (this.getPlayer2X())), p2);
			
			LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
			this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
			
			this.setPlayer2Y(this.getPlayer2Y() + 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p2;
			table[1] = w2;
			
			this.setChanged();
			this.notifyObservers(this);

			
		} else {
			this.p2isAlive = false;
		}
		
		
	} else if ( this.getP2facing() == Facing.LEFT ) {
		
		if ( this.theGrid.get((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() - 1))).getType() == LightSquareType.VOID ) {
			
			LightSquare p2 = new LightSquare(LightSquareType.PLAYER2, this.getPlayer2X() - 1, this.getPlayer2Y());
			this.theGrid.set((((this.getPlayer2Y()) * 60) + (this.getPlayer2X() - 1)), p2);
			
			LightSquare w2 = new LightSquare(LightSquareType.WALL2, this.getPlayer2X(), this.getPlayer2Y());
			this.theGrid.set(((this.getPlayer2Y() * 60) + (this.getPlayer2X())), w2);
			
			this.setPlayer2X(this.getPlayer2X() - 1);
			
			LightSquare[] table = new LightSquare[2];
			table[0] = p2;
			table[1] = w2;
			
			this.setChanged();
			this.notifyObservers(this);

			
		} else {
			this.p2isAlive = false;
		}


	}
		
		
		
	}












}
