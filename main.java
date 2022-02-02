import java.util.Scanner;
import java.util.Random;
import java.lang.*;
public class main {
	
	//DISPLAY RULES
	public static void subMenu1(Scanner sc) {
		System.out.println("---------------Rules Of Play-------------------");
		System.out.println("The Rules to Battleship are simple: Place your ships down, "
				+ "then try and destroy the enemies ships before they destroy yours.");
		System.out.println("You have 5 ships: Each you will place down by entering the "
				+ "coordinates for start then end(both inclusive)");
		System.out.println("Then you will try and destroy the enemy ships, while they yours. "
				+ "The game will exit when the game is finished, but you can exit anytime with X. "
				+ "\n Press any letter to return to Menu.");
		//Continue to Menu
		try {
			sc.nextLine();
			return;
		}catch(Exception e) {
			return;
		}
	}
	
	//CHANGE SCREEN SETTINGS
	public static boolean subMenu2(Scanner sc) {
		//Display options
		try {
			System.out.println("-----------------SCREEN DIFFICULTY---------------");
			System.out.println("1.) Hidden(Default)       2.) Enigma(Enemy Revealed)");
			System.out.println("3.) return to menu");
			System.out.print("Enter a Option:");
			int ans = Integer.parseInt(sc.nextLine());
			// If two, change, otherwise keep true
			if(ans == 2) {
				return false;
			}
			else {
				return true;
			}
		}catch(Exception e) {
			System.out.println("Error! Please Enter a valid number");
			return true;
		}
			
	}
	
	//CHANGE AI SETTINGS
	public static boolean subMenu3(Scanner sc) {
		try {
			//Display
			System.out.println("-----------------AI DIFFICULTY---------------");
			System.out.println("1.) Easy(Default)       2.) Advanced(Work in Progress)");
			System.out.println("3.) return to menu");
			System.out.print("Enter a Option:");
			//If 2 return false, otherwise return True
			int ans = Integer.parseInt(sc.nextLine());
			if(ans == 2) {
				return false;
			}
			else {
				return true;
			}
		}catch(Exception e) {
			System.out.println("Error! Please Enter a valid number");
			return true;
		}
		
	}
	
	//DISPLAY MENU
	public static int menu(Scanner sc) {
		System.out.println("\n====================================================");
		System.out.println("---------------------WELCOME TO---------------------");
		System.out.println("---------------------BATTLESHIP---------------------");
		System.out.println("====================================================\n");
		System.out.println("                       MENU                          ");
		System.out.println("    1.) RULES OF PLAY            3.) AI DIFFICULTY");
		System.out.println("    2.) SCREEN DIFFICULTY        4.) START\n");
		System.out.print("Enter a Option:");
		try {
			int option = Integer.parseInt(sc.nextLine());
			return option;
		}catch(Exception e) {
			return 5;
		}
		
		
	}
	
	//CHANGE LETTERS TO NUMBERS
	public static int alpToNum(String x) {
		try {
			int numX = -1; 
			if(x.toUpperCase().equals("A")) {
				numX = 0;
			}
			else if (x.toUpperCase().equals("B")) {
				numX = 1;
			}
			else if (x.toUpperCase().equals("C")) {
				numX = 2;
			}
			else if (x.toUpperCase().equals("D")) {
				numX = 3;
			}
			else if (x.toUpperCase().equals("E")) {
				numX = 4;
			}
			else if (x.toUpperCase().equals("F")) {
				numX = 5;
			}
			else if (x.toUpperCase().equals("G")) {
				numX = 6;
			}
			else if (x.toUpperCase().equals("H")) {
				numX = 7;
			}
			else if (x.toUpperCase().equals("I")) {
				numX = 8;
			}
			else if (x.toUpperCase().equals("J")) {
				numX = 9;
			}
			return numX;
		}catch(Exception e) {
			System.out.println("Please Enter a position in the grid. Firstly a letter, next a number for the coordinate");
			System.out.print(e);
			return -1;}
	}
		
	//GET COORDINATES FROM PLAYER
	public static int[] getCord(String position) {
		try {
			//Start in error position
			int posA[] = {-1, -1};
			
			//End Program if X or x
			if (position.equalsIgnoreCase("X")) {
				System.exit(0);
			}
			
			//split string then turn first letter and second number to integers
			String[] start = position.split("");
			int xPs = alpToNum(start[0]);
			int yPs = Integer.parseInt(start[1]) - 1;
			
			//if three, add number to 
			if(start.length > 2) {
				yPs = 10 + Integer.parseInt(start[2])- 1;
			} 
			
			//ERROR CASES#####
			//if more then 3, return Error 
			if(start.length > 3) {
				System.out.println("Error! Please Enter valid coordinate");
				return posA;
			}
			//if out of bounds: return Error
			if(yPs > 9 || yPs < 0 || xPs > 9 || xPs < 0) {
				System.out.println("Error! Please enter a position valid in the map: First letter then number!");
				return posA;
			} 
			//###############
			//If cases pass, then create int[] with numbers
			posA[0] = xPs;
			posA[1] = yPs;
			
			return posA;
			
			
		}catch(Exception e) {
			System.out.println("Error! Please Enter a position in the grid. example: A7");
			int error[] = {-1, -1};
			return error;
		}
	}
	
	//GET POSITION FROM PLAYER
	public static int[] getPos(String ship, String sOrE, int size) {
		try {
			
			//create variables
			Scanner sc = new Scanner(System.in);
			boolean correct = true;
			int errorCode[] = {-1, -1};
			int cord[] = {-1, -1};
	
			do {
				
				//ask for ship positions
				System.out.println("\nPlease Enter a "+ sOrE + " position for " + ship + "("+ size+") : ");
				String position = sc.nextLine();
				
				//get coordinate
				cord = getCord(position);
				
				//if error present: rerun section
				if (cord[0] == errorCode[0] && cord[1] == errorCode[1]) {
					correct = false;
				}else {correct = true;}
				
			}while(correct != true);
			
			//If all correct: return cord
			return cord;
			
		}catch(Exception e) {
			//If exception: print error, then run again, recursively to a result
			System.out.println("Error! Please Enter a position in the grid. example: A7");
			
			return null;
		}
		
	}
	
	//GET SHIPS FROM PLAYER
	public static void ship(String shNm, board myBoard, ship Ship) {
		boolean ok = true;
		do {
			
			int sPos[] = getPos(shNm, "starting", Ship.getHitPnt());
			int ePos[] = getPos(shNm, "ending", Ship.getHitPnt());
			
			if (sPos == null || ePos == null) {
				sPos = getPos(shNm, "starting", Ship.getHitPnt());
				ePos = getPos(shNm, "ending", Ship.getHitPnt());
			}
			//put ship in map
			ok = myBoard.putShipOnBoard(Ship, sPos[0], sPos[1], ePos[0], ePos[1], false, false);
		}while(!ok);
	}
	
	//GET SHIPS FROM AI
	public static void shipAI(Random rand, ship Ship, board aiBoard, boolean Screen) {
		
		boolean ok = true;
		do {
			//Get coordinates
			int sXCor = rand.nextInt(10);
			int sYCor = rand.nextInt(10);
			int eXCor, eYCor;
			do {
				//get booleans
				boolean XorY = rand.nextBoolean();
				boolean PorN = rand.nextBoolean();
				
				//if true, go on X: else subtract
				if (XorY) {
					eYCor = sYCor;
					//if true add hp: else subtract
					if(PorN) {
						eXCor = sXCor + Ship.getHitPnt() - 1;
					}else {eXCor = sXCor - Ship.getHitPnt() - 1;}
					
				}else {
					eXCor = sXCor;
					if(PorN) {
						eYCor = sYCor + Ship.getHitPnt() - 1;
						
					}else {eYCor = sYCor - Ship.getHitPnt() - 1;}
				}
				//check if out of bounds
				if(eXCor < 0 || eXCor > 9 || eYCor < 0 || eYCor > 9) {
					ok = false;
				}
				else {
					ok = true;
				}
			}while(ok == false);//Do til able to be placed on map
			
			//check if able to be placed
			ok = aiBoard.putShipOnBoard(Ship, sXCor, sYCor, eXCor, eYCor, true ,Screen);
		}while(ok == false);
	}
	
	//GET FIRE FROM PLAYER
	public static void fire(board aiBoard, int num) {
		boolean go = true;
		do {
			
			//get coordinates
			int cord[] = getPos("Turn", "Attack", num);
			
			//fire if successful
			go = aiBoard.fireCord(cord[0], cord[1]);
		}while (go == false);
	}
	
	//GET FIRE FROM AI(EASY)
	public static void fireAIEasy(Random rand, board myBoard) {
		boolean go[] = {true, true, true};
		do {
			
			int xCor = rand.nextInt(10);
			int yCor = rand.nextInt(10);
			
			//fire if successful
			go = myBoard.fireCordAI(xCor, yCor);
		}while (go[0] == false);
	}
	
	//GET FIRE FROM AI(ADVANCED)
	public static int[] fireAI(int x, int y, Random rand, board myBoard, boolean hitFlag) {
		//variables
		boolean go[] = {true, false, true};
		int stHit[] = {-1, -1};
		do {
			
			//If no flag sprung: fire at random-----------------------------------
			if(hitFlag == false) {
				do {
					//get random values 0 -9
					int cordX = rand.nextInt(10);
					int cordY = rand.nextInt(10);
					
					go = myBoard.fireCordAI(cordX, cordY);
					
					//if hit, change cords to coordinates
					if (go[1] == true && hitFlag == false) {
						stHit[0] = cordX;
						stHit[1] = cordY;
					}
				}while (!go[0]);
				return stHit;
				
			}
			//If Flag Sprung: check surrounding pieces----------------------------------
			else {
				
				//If Ship destroyed: send reset code
				if(go[2] == false) {
					stHit[0] = -2;
					stHit[1] = -2;
					return stHit;
				}
				
				//otherwise: check ship next position
				//Check position next: 
				//     if hit- send that cord || if already shot- go to next
				if(x+ 1 < 10) {
					go = myBoard.fireCordAI(x + 1, y);
					
					//if ship there: keep going- otherwise go back to start
					if (go[1] == true) {
						stHit[0] = x + 1;
						stHit[1] = y;
					}
					else {
						stHit[0] = -3;
						stHit[1] = -3;
						return stHit;
					}
				}
				
				//if last one was fail: check this one
				else if(go[0] == false) {
					if(y + 1 < 10) {
						go = myBoard.fireCordAI(x, y + 1);
						if (go[1] == true) {
							stHit[0] = x;
							stHit[1] = y + 1;
						}
						else {
							stHit[0] = -3;
							stHit[1] = -3;
							return stHit;
						}
					}
					
				}
				
				else if(go[0] == false) {
					if(x - 1 > -1) {
						go = myBoard.fireCordAI(x - 1, y);
						if (go[1] == true) {
							stHit[0] = x - 1;
							stHit[1] = y;
						}else {
							stHit[0] = -3;
							stHit[1] = -3;
							return stHit;
						}
					}
					
				}
				else if(go[0] == false) {
					if(y - 1 >= 0) {
						go = myBoard.fireCordAI(x, y - 1);
						if (go[1] == true) {
							stHit[0] = x;
							stHit[1] = y - 1;
						}else if(go[1] == false && go[0] == true){
							stHit[0] = -3;
							stHit[1] = -3;
							return stHit;
						}
					}
					
				}
				
				//If All four sides already shot: Send re do code
				else if(go[0] == false) {
					stHit[0] = -3;
					stHit[1] = -3;
					return stHit;
				}
			}
			
			
			
		}while (go[0] == false);
		
		return stHit;
	}
	
	//MAIN
	public static void main(String[] args) {
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		//start board==================================
		board myBoard = new board();
		board aiBoard = new board();
		myBoard.setBoard();
		aiBoard.setBoard();
		
		boolean Screen = true, AI = true, leaveI = false;
		
//WELCOME MENU===============================================
		do {
			int option = menu(sc);
			if(option == 1) {
				subMenu1(sc);//rules
			}
			else if(option == 2) {
				Screen = subMenu2(sc);//screen
			}
			else if(option == 3) {
				Screen = subMenu3(sc);//AI
			}
			else if(option == 4) {
				leaveI = true;// Start
			}
			else {
				System.out.println("Error! Please Enter a number");
			}
		}while(leaveI == false);

		
		
//PLACE SHIPS======================================
		boolean ok = true;
		myBoard.printBoard();
		ship("Carrier", myBoard, myBoard.MyCarrier);
		myBoard.printBoard();
		ship("Battleship", myBoard, myBoard.MyBat);
		myBoard.printBoard();
		ship("Cruiser", myBoard, myBoard.MyCru);
		myBoard.printBoard();
		ship("Submarine", myBoard, myBoard.MySub);
		myBoard.printBoard();
		ship("Destroyer", myBoard, myBoard.MyDst);
		myBoard.printBoard();
		
		
//AI BOARD============================================================================
		
		shipAI(rand, aiBoard.MyCarrier, aiBoard, Screen);
		shipAI(rand, aiBoard.MyBat, aiBoard, Screen);
		shipAI(rand, aiBoard.MyCru, aiBoard, Screen);
		shipAI(rand, aiBoard.MySub, aiBoard, Screen);
		shipAI(rand, aiBoard.MyDst, aiBoard, Screen);
		System.out.println("Enemy Board created");
		
		
//GAME=================================================================================
		boolean done = false;
		int turn = 0;
		int pX = -1, stX = -1, stY = -1, pY = -1;
		int cr[];
		boolean flag = false;
		while(done != true) {
			
			//display board
			System.out.println("---- ENEMY BOARD ----");
			aiBoard.printBoard();
			fire(aiBoard, turn);
			
			//if AI out of ships, you win
			if(aiBoard.amount <= 0) {
				System.out.print("Congratulations! You won the Game!");
				done = true;
				break;
			}
			
			//Sleep for 1 second
			System.out.println("Enemy turn ...");
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println("...");
			}
//AI TURN============================================================
			if(AI) {
				fireAIEasy(rand, myBoard);
			}
			
			//ADVANCED AI
			if(!AI) {
				cr =fireAI(pX, pY, rand, myBoard, flag);
				
				//if new Hit: And flag is not hit
				if(cr[0] > -1 && cr[1] > -1 && flag == false) {
					//System.out.println("----nEW" + cr[0] + cr[1]);
					flag = true;
					pX = cr[0];
					pY = cr[1];
					stX = cr[0];
					stY = cr[1];
				}
				//-3 REDO TO START
				else if (cr[0] == -3 && cr[1] == -3) {
					//System.out.println("----REDO WITH START" + stX+ stY);
					pX = stX;
					pY = stY;
				}
				//-2 REMOVE FLAG
				else if(cr[0] == -2 && cr[1] == -2) {
					flag = false;
					pX = -1;
					stX = -1;
					pY = -1;
					stY = -1;
					
				}
				else {
					//System.out.println("----next pos"+cr[0]+ cr[1] + " "+ pX + pY + "S: " + stX + stY);
					pX = cr[0];
					pY = cr[1];
				}
			}
			
			
			//Sleep for 1 second
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println("...");
			}
			
			
			System.out.println("---- PLAYER BOARD ----");
			myBoard.printBoard();
			
			//if You are out of ships, AI win
			if(myBoard.amount <= 0) {
				System.out.print("Congratulations! You Lost the Game!");
				done = true;
			}
			
			turn++;
		}//end while
		
		System.out.println("Goodbye! Hope you had fun!");
		
	}//main
	

}//main class
