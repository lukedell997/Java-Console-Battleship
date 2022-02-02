
abstract class ship {
	String name;
	piece[] position;
	int hitPnt;
	
	//GETTERS========================
	public String getName() {
		return name;
	}
	public piece[] getPos() {
		return position;
	}
	public int getHitPnt() {
		return hitPnt;
	}
	//==============================
	//SETTERS=======================
	public void setName(String nm) {
		name = nm;
	}
	public void setPos(piece[] pos ) {
		position = pos;
	}
	public void setHitPnt(int hp) {
		hitPnt = hp;
	}
	public void decHitPnt() {
		hitPnt = hitPnt - 1;
	}
	//==============================
}
