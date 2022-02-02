


public class board {
	piece[][] pieces = new piece[10][10];
	
	//create ships for board
	carrier MyCarrier = new carrier();
	battleship MyBat = new battleship();
	cruiser MyCru = new cruiser();
	submarine MySub = new submarine();
	destroyer MyDst = new destroyer();
	int amount = 5;
	
//SETS UP BOARD=============================================================================================
	public void setBoard(){
		
		//create a 10x10 of pieces
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				pieces[x][y] = new piece(x, y);
			}
		}
	}
	
	//Check if ship can go in viable coordinates
	public boolean checkIfShipWorks(int stX, int stY, int enX, int enY, int shHp, boolean AI) {
		
		String erShip = "ERROR! Ship in the way!\n";
		String erLgthX = "Error! You entered " + (Math.abs(stX - enX)+1) + " slots while the ship is "+ shHp+" spaces!\n";
		String erLgthY = "Error! You entered " + (Math.abs(stY - enY)+1) + " slots while the ship is "+ shHp+" spaces!\n";
		//If AI, dont show messages
		if (AI == true) {
			erShip = "";
			erLgthX = "";
			erLgthY = "";
		}
		//If change is on Y axis: X is equal---------------------------------------
		if (stX == enX) {
			
			if (Math.abs(stY - enY) != shHp - 1) {
				System.out.print(erLgthY);
				return false;
			}
			//create increment or decrement
			 int inc = 1;
			 if (enY < stY) {
				 inc = -1;
			 }
			 
			 //continue until start = finish
			 while (stY != enY) {
				 
				 //if pieces position has ship = FALSE
				 if (pieces[stX][stY].hasShip()) {
					 System.out.print(erShip);
					 return false;
				 }
				 stY += inc;
			 }
			 
			 //Check if endpiece is good
			 if(pieces[stX][stY].hasShip()) {
				 System.out.print(erShip);
				 return false;
			 }
			 return true; 
		}
		//If change is on X axis: Y axis equal-------------------------------------------
		else if (stY == enY) {
			
			if (Math.abs(stX - enX) != shHp - 1) {
				System.out.print(erLgthX);
				return false;
			}
			//create increment or decrement
			 int inc = 1;
			 if (enX < stX) {
				 inc = -1;
			 }
			 
			 //continue start = finish
			 while (stX != enX) {
				 
				 //if pieces position has ship = FALSE
				 if (pieces[stX][stY].hasShip()) {
					 System.out.print(erShip);
					 return false;
				 }
				 stX += inc;
			 }
			 
			//check if endpiece has ship
			 if (pieces[stX][stY].hasShip()) {
				 System.out.print(erShip);
				 return false;
			 }
			 return true;
		}
		//SHIPS on diagonal-----------------------------------------------------------
		else {
			System.out.print("Error! ships cannot be diagonal.");
			return false;
		}
	}

	
	//PUT SHIP ON BOARD=========================================================================================
	public boolean putShipOnBoard(ship Ship, int stX, int stY, int enX, int enY, boolean AI, boolean Screen) {
		
		//Change display type if AI to not show Ship locations
		String Nm = "S";
		
		if(Screen == true) {
			Nm = "O";
		}
		
		//Check if coordinates works
		if (checkIfShipWorks(stX, stY, enX, enY, Ship.hitPnt, AI) == true) {
			
			//If change is on Y axis------------------------------------------
			if (stX == enX) {
				
				//create increment or decrement
				 int inc = 1;
				 if (enY < stY) {
					 inc = -1;
				 }
				 
				 //continue until start = finish
				 while (stY != enY) {
					 
					 pieces[stX][stY].setImage(Nm);
					 pieces[stX][stY].setShip(true);
					 pieces[stX][stY].setShipType(Ship);
					 stY += inc;
				 }
				 //put piece for endpiece
				 pieces[stX][stY].setImage(Nm);
				 pieces[stX][stY].setShip(true);
				 pieces[stX][stY].setShipType(Ship);
				  
			}
			//If change is on X axis------------------------------------------------
			else if (stY == enY) {
				
				//create increment or decrement
				 int inc = 1;
				 if (enX < stX) {
					 inc = -1;
				 }
				 
				 //continue start = finish
				 while (stX != enX) {
					 
					 pieces[stX][stY].setImage(Nm);
					 pieces[stX][stY].setShip(true);
					 pieces[stX][stY].setShipType(Ship);
					 stX += inc;
				 }
				 
				 //once more for endpiece
				 pieces[stX][stY].setImage(Nm);
				 pieces[stX][stY].setShip(true);
				 pieces[stX][stY].setShipType(Ship);
			}
			return true;
		}
		return false;
	}

	
//FIRE + CHANGE PIECE===========================================================================================
	public boolean fireCord(int x, int y) {
		boolean go = true;
		
		//if co is already hit, give error
		if (pieces[x][y].hasHit()) {
			System.out.println("Error! Coordinate has already been fired on!");
			go = false;
		}
		else {
			//if ship on piece, change image and decrease hit point for ship
			if (pieces[x][y].hasShip()) {
				pieces[x][y].setImage("H");
				System.out.println("Hit!");
				pieces[x][y].getShipType().decHitPnt();
				
				//if hit points = 0, then sunk ship, and have one less ships left
				if(pieces[x][y].getShipType().getHitPnt() == 0) {
					System.out.println("You sunk their "+ pieces[x][y].getShipType().getName()+ "!");
					amount--;
					System.out.println("They Only have "+ amount +" ships left!");
				}
				
			}
			else{
				pieces[x][y].setImage("X");
				System.out.println("Miss!");
			}
			//either way, set piece to hit
			pieces[x][y].setHit(true);
		}
		return go;
	}
	
	//FIRE FOR AI
	public boolean[] fireCordAI(int x, int y) {
		boolean go[] = {true, false, true};
		
		//if co is already hit, give error
		if (pieces[x][y].hasHit()) {
			go[0] = false;
		}
		else {
			//if ship on piece, change image and decrease hit point for ship
			if (pieces[x][y].hasShip()) {
				pieces[x][y].setImage("H");
				System.out.println("Enemy Hit!");
				pieces[x][y].getShipType().decHitPnt();
				go[1] = true;
				
				//if hit points = 0, then sunk ship, and have one less ships left
				if(pieces[x][y].getShipType().getHitPnt() == 0) {
					System.out.println("They sunk your "+ pieces[x][y].getShipType().getName()+ "!");
					amount--;
					go[2] = false;
					System.out.println("They Only have "+ amount +" ships left!");
				}
				
			}
			else{
				pieces[x][y].setImage("X");
				System.out.println("Enemy Missed!");
			}
			//either way, set piece to hit
			pieces[x][y].setHit(true);
		}
		return go;
	}	

	//SHOWS THE BOARD========================================================================================
	public void printBoard() {
		//create a 10x10 of pieces
		System.out.print("================================");
		System.out.print("\nX  1  2  3  4  5  6  7  8  9  10");
		for (int x = 0; x < 10; x++) {
			System.out.print("\n" + numToLet(x));
			for (int y = 0; y < 10; y++) {
				System.out.print(" " + pieces[x][y].getImage() + " ");
			}
			
		}
		System.out.println("\n");
	}//end printBoard
	
	//turn number to letter 0 = A, 9 = J
	
	//CHANGE NUMBERS TO LETTERS====================================================================
	public String numToLet(int n) {
		String al;
		if(n == 0) {
			al = "A|";
		}
		else if(n == 1) {
			al = "B|";
		}
		else if(n == 2) {
			al = "C|";
		}
		else if(n == 3) {
			al = "D|";
		}
		else if(n == 4) {
			al = "E|";
		}
		else if(n == 5) {
			al = "F|";
		}
		else if(n == 6) {
			al = "G|";
		}
		else if(n == 7) {
			al = "H|";
		}
		else if(n == 8) {
			al = "I|";
		}
		else{
			al = "J|";
		}
		return al;
	}
}
