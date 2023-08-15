
public class Engine {

	Fields fields[][]=new Fields[9][8];
	Position goals[] = new Position[6];
	public Engine()
	{
		init();
	}
	public void init()
	{
		for(int i = 0; i < fields.length; i++) {
			for(int j = 0; j < fields[0].length; j++)
				fields[i][j] = Fields.empty;
		}
		
		for(int i = 2; i < 7; i++)
			fields[0][i] = Fields.wall;
		
		for(int i = 0; i < 3; i++)
			fields[1][i] = Fields.wall;
		
		for(int i = 2; i < fields.length; i++)
			fields[i][0] = Fields.wall;
		
		for(int i = 0; i < fields[0].length; i++)
			fields[8][i] = Fields.wall;
		
		for(int i = 1; i < 6; i++)
			fields[i][6] = Fields.wall;
		
		fields[7][7] = Fields.wall;
		fields[6][7] = Fields.wall;
		fields[5][7] = Fields.wall;
		fields[3][1] = Fields.wall;
		fields[3][2] = Fields.wall;
		fields[4][2] = Fields.wall;
		fields[5][2] = Fields.wall;
		fields[4][3] = Fields.wall;
		fields[2][2] = Fields.man;
		
		fields[2][1] = Fields.goal;
		goals[0] = new Position(2,1);
		fields[3][5] = Fields.goal;
		goals[1] = new Position(3,5);
		fields[4][1] = Fields.goal;
		goals[2] = new Position(4,1);
		fields[5][4] = Fields.goal;
		goals[3] = new Position(5,4);
		fields[7][4] = Fields.goal;
		goals[4] = new Position(7,4);
		fields[6][6] = Fields.goal;
		goals[5] = new Position(6,6);
		
		fields[2][4] = Fields.box;
		fields[3][4] = Fields.box;
		fields[4][4] = Fields.box;
		fields[6][4] = Fields.box;
		fields[6][1] = Fields.box;
		fields[6][5] = Fields.box;
	}
	private boolean isThisBox(int i, int j) {
		if(fields[i][j] == Fields.box)
			return true;
		return false;
	}
	
	private Position getManPosition() {
		Position kor = new Position();
		for(int i = 0; i < fields.length; i++)
			for(int j = 0; j < fields[0].length; j++)
				if(fields[i][j] == Fields.man) {
					kor.setI(i);
					kor.setJ(j);
				}
		return kor;
	}
	
	private boolean isThisGoal(int i, int j) {
		for(int k = 0; k < goals.length; k++) {
			if(i == goals[k].getI() && j == goals[k].getJ())
				return true;
		}
		
		return false;
	}
	
	public boolean endGame() {
		
		for(int k = 0; k < goals.length; k++) {
			int i = goals[k].getI();
			int j = goals[k].getJ();
			if(fields[i][j] != Fields.boxongoal)
				return false;
		}
		
		return true;
	}
	
	public void moveMan(Direction direction) {
		Position pos = getManPosition();
		int i = pos.getI();
		int j = pos.getJ();
		if(direction == Direction.up) {
			if(fields[i-1][j] != Fields.wall && fields[i-1][j] != Fields.boxongoal) {
				if(isThisBox(i-1, j)) {
					if(moveBox(i-2, j)) {
						if(fields[i-2][j] == Fields.goal)
							fields[i-2][j] = Fields.boxongoal;
						else
							fields[i-2][j] = Fields.box;
						fields[i-1][j] = Fields.man;
						if(isThisGoal(i, j))
							fields[i][j] = Fields.goal;
						else
							fields[i][j] = Fields.empty;
					}
				}
				else {
					fields[i-1][j] = Fields.man;
					if(isThisGoal(i, j))
						fields[i][j] = Fields.goal;
					else
						fields[i][j] = Fields.empty;
				}	
			}
		}
		if(direction == Direction.down) {
			if(fields[i+1][j] != Fields.wall && fields[i+1][j] != Fields.boxongoal) {
				if(isThisBox(i+1, j)) {
					if(moveBox(i+2, j)) {
						if(fields[i+2][j] == Fields.goal)
							fields[i+2][j] = Fields.boxongoal;
						else
							fields[i+2][j] = Fields.box;
						fields[i+1][j] = Fields.man;
						if(isThisGoal(i, j))
							fields[i][j] = Fields.goal;
						else
							fields[i][j] = Fields.empty;
					}
				}
				else {
					fields[i+1][j] = Fields.man;
					if(isThisGoal(i, j))
						fields[i][j] = Fields.goal;
					else
						fields[i][j] = Fields.empty;
					}
				}
			}
		if(direction == Direction.left) {
			if(fields[i][j-1] != Fields.wall && fields[i][j-1] != Fields.boxongoal) {
				if(isThisBox(i, j-1)) {
					if(moveBox(i, j-2)) {
						if(fields[i][j-2] == Fields.goal)
							fields[i][j-2] = Fields.boxongoal;
						else
							fields[i][j-2] = Fields.box;
						fields[i][j-1] = Fields.man;
						if(isThisGoal(i, j))
							fields[i][j] = Fields.goal;
						else
							fields[i][j] = Fields.empty;
					}
				}
				else {
					fields[i][j-1] = Fields.man;
					if(isThisGoal(i, j))
						fields[i][j] = Fields.goal;
					else
						fields[i][j] = Fields.empty;
				}
			}
		}
		if(direction == Direction.right) {
			if(fields[i][j+1] != Fields.wall && fields[i][j+1] != Fields.boxongoal) {
				if(isThisBox(i, j+1)) {
					if(moveBox(i, j+2)) {
						if(fields[i][j+2] == Fields.goal)
							fields[i][j+2] = Fields.boxongoal;
						else
							fields[i][j+2] = Fields.box;
						fields[i][j+1] = Fields.man;
						if(isThisGoal(i, j))
							fields[i][j] = Fields.goal;
						else
							fields[i][j] = Fields.empty;
					}
				}
				else {
					fields[i][j+1] = Fields.man;
					if(isThisGoal(i, j))
						fields[i][j] = Fields.goal;
					else
						fields[i][j] = Fields.empty;
				}
					
			}
		}
	}
	
	public boolean moveBox(int i, int j) {
		
		if(fields[i][j] == Fields.wall)
			return false;
		if(fields[i][j] == Fields.box)
			return false;
		if(fields[i][j] == Fields.boxongoal)
			return false;
		
		return true;
	}
	public String Picture(int i,int j)
	{
		String str="pictures/";
		Fields field=fields[i][j];
		switch (field) {
		case man:
			str+="man.PNG";
			break;
		case wall:
			str+="wall.PNG";
			break;
		case empty:
			str+="empty.PNG";		
			break;
		case boxongoal:
			str+="boxongoal.PNG";
			break;
		case box:
			str+="box.PNG";
			break;
		case goal:
			str+="goal.PNG";
			break;
		default:
			break;
		}
		return str;
	}
	
}
