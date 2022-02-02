
public class piece {
	int xPos;
	int yPos;
	boolean ship;
	boolean hit;
	String image; //O = unknown, H = hit ship, X shot missed, S = your ship
	ship shipType;
	
	//get piece with ship and hit
	public piece(int x, int y, boolean shipI, boolean hitI) {
		xPos = x;
		yPos = y;
		ship = shipI;
		hit = hitI;
	}
	
	//create piece with just x and y
	public piece(int x, int y) {
		xPos = x;
		yPos = y;
		ship = false;
		hit = false;
		image = "O";
	}
	
	//Getters===========================================
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public String getImage() {
		return image;
	}
	
	//get array of integer x and y
	public int[] getPosition() {
		int[] position = {xPos, yPos};
		return position;
		
	}
	public boolean hasShip() {
		return ship;
	}
	
	public ship getShipType() {
		return shipType;
	}
	public boolean hasHit() {
		return hit;
	}
	//===================================================
	//SETTERS ==========================================
	public void setXPos(int x){
		xPos = x;
	}
	
	public void setYPos(int y) {
		yPos = y;
	}
	
	public void setImage(String img) {
		image = img;
	}
	public void setShip(boolean shipI) {
		ship = shipI;
	}
	public void setShipType(ship shipI) {
		shipType = shipI;
	}
	
	public void setHit(boolean hitI) {
		hit = hitI;
	}
	

}
