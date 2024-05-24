
import java.util.ArrayList;
class ChessGame {
	private String lastsetlocmv = null;
	private int gameID = -1;
	private boolean completed = false;
	private int moveindex = -1;
	//private String[] LAST_UNDONE_MOVE = null;
	//private String[] LAST_REDONE_MOVE = null;
	private ArrayList<String[]> OFFICIAL_MOVES = null;
	public static ArrayList<ChessGame> all = new ArrayList<ChessGame>();
	
	public static String[][] convertStringArrayToMultidim(String[] sarr)
	{
		String[][] mymvs = new String[1][];
		mymvs[0] = sarr;
		return mymvs;
	}
	
	public ChessGame(int gid, String[][] offmvs, boolean isdone)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		int numitems = ChessPiece.getNumItemsInList(all);
		if (numitems < 1);
		else
		{
			for (int x = 0; x < all.size(); x++)
			{
				if (all.get(x).getGameID() == gid)
				{
					throw new IllegalStateException("there must be only one game with that ID!");
				}
				//else;//do nothing
			}
		}
		this.gameID = gid;
		this.completed = isdone;
		this.setOfficialMoves(offmvs);
		all.add(this);
	}
	public ChessGame(int gid)
	{
		this(gid, null, false);
	}
	
	
	public int getGameID()
	{
		return this.gameID;
	}
	
	public static ChessGame getGame(int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else
		{
			int numitems = ChessPiece.getNumItemsInList(all);
			if (numitems < 1);
			else
			{
				for (int x = 0; x < numitems; x++)
				{
					if (all.get(x).getGameID() == gid) return all.get(x);
				}
			}
			//return null;
			throw new IllegalStateException("GAME with ID (" + gid + ") not found!");
		}
	}
	
	public boolean isCompleted()
	{
		return this.completed;
	}
	
	public void setLastSetLocMove(String mvstr)
	{
		if (mvstr == null) this.lastsetlocmv = null;
		else this.lastsetlocmv = "" + mvstr;
	}
	public String getLastSetLocMove()
	{
		return this.lastsetlocmv;
	}
	
	public void clearOfficalMoves()
	{
		int numofmvs = ChessPiece.getNumItemsInList(this.OFFICIAL_MOVES);
		if (numofmvs < 1);
		else
		{
			for (int x = 0; x < numofmvs; x++)
			{
				if (this.OFFICIAL_MOVES.get(x) == null);
				else if (this.OFFICIAL_MOVES.get(x).length < 1) this.OFFICIAL_MOVES.set(x, null);
				else
				{
					for (int r = 0; r < this.OFFICIAL_MOVES.get(x).length; r++)
					{
						this.OFFICIAL_MOVES.get(x)[r] = null;
					}
				}
			}
			this.OFFICIAL_MOVES.clear();
		}
	}
	
	public void setOfficialMoves(String[][] myoffmvs)
	{
		int numofmvs = ChessPiece.getNumItemsInList(this.OFFICIAL_MOVES);
		if (myoffmvs == null)
		{
			if (this.OFFICIAL_MOVES == null);
			else if (numofmvs < 1) this.OFFICIAL_MOVES = null;
			else
			{
				this.clearOfficalMoves();
				this.OFFICIAL_MOVES = null;
			}
		}
		else if (myoffmvs.length < 1)
		{
			if (this.OFFICIAL_MOVES == null) this.OFFICIAL_MOVES = new ArrayList<String[]>();
			else if (numofmvs < 1);
			else this.clearOfficalMoves();
		}
		else
		{
			if (this.OFFICIAL_MOVES == null) this.OFFICIAL_MOVES = new ArrayList<String[]>();
			else if (numofmvs < 1);
			else this.clearOfficalMoves();
			for (int x = 0; x < myoffmvs.length; x++)
			{
				if (myoffmvs[x] == null) this.OFFICIAL_MOVES.add(null);
				else
				{
					String[] mynwstrarr = new String[myoffmvs[x].length];
					for (int r = 0; r < myoffmvs[x].length; r++)
					{
						mynwstrarr[r] = "" + myoffmvs[x][r];
					}
					this.OFFICIAL_MOVES.add(mynwstrarr);
				}
			}
		}
	}
	
	public void stepForward()
	{
		//TO GO FORWARDS: (MIGHT BE SMART TO START STEP_INDEX AT -1)
		//-INCREASE THE STEP COUNTER/INDEX
		//-GET THE CURRENT MOVE (SET IT AS THE UNOFFICIAL MOVE)
		//-MAKE IT (IT IS ALREADY OFFICIAL, SO DO NOT ADD TO LIST OF OFFICIAL MOVES)
		
		//if (this.isCompleted());
		//else throw new IllegalStateException("GAME MUST BE COMPLETED IN ORDER TO STEP THROUGH IT!");
		
		System.out.println("OLD moveindex = " + this.moveindex);
		
		this.moveindex++;
		
		System.out.println("NEW moveindex = " + this.moveindex);
		
		//get the move from list of official moves
		int numofmvs = ChessPiece.getNumItemsInList(this.OFFICIAL_MOVES);
		if (numofmvs < 1)
		{
			throw new IllegalStateException("CANNOT MOVE FORWARD, NO MOVES PROVIDED!");
		}
		else if (this.moveindex < numofmvs)
		{
			ChessPiece.makeLocalShortHandMove(this.OFFICIAL_MOVES.get(this.moveindex), this.getGameID());
		}
		else throw new IllegalStateException("CANNOT MOVE FORWARD, NO MORE MOVES PROVIDED!");
	}
	
	public void stepBackward()
	{
		//TO GO BACKWARDS:
		//UNDO THE CURRENT MOVE (PUT ON UNOFFICIAL MOVE, AND THEN UNDO THE UNOFFICIAL MOVE)
		//-COUNTER HAS TO DECREASE,
		//-CLEAR UNOFFICIAL_MOVE.
		//-THEN GET THE PREVIOUS MOVE
		
		if (this.isCompleted());
		else throw new IllegalStateException("GAME MUST BE COMPLETED IN ORDER TO STEP THROUGH IT!");
		
		if (this.moveindex < 0) throw new IllegalStateException("CANNOT STEP BACKWARD!");
		//else;//do nothing
		
		//get the current move
		//undo it
		//then get the previous move
		//moveindex--;
		//get it
		//then make it
		
		ChessPiece.makeLocalShortHandMove(
			ChessPiece.genUndoMoveToShortHandCommand(this.OFFICIAL_MOVES.get(this.moveindex)), this.getGameID(), true,
			ChessPiece.WHITE_MOVES_DOWN_RANKS);
			
		System.out.println("OLD moveindex = " + this.moveindex);
		
		this.moveindex--;
		
		System.out.println("NEW moveindex = " + this.moveindex);
	}
	
	public void makeAllGivenOfficialMoves()
	{
		int numofmvs = ChessPiece.getNumItemsInList(this.OFFICIAL_MOVES);
		if (numofmvs < 1);
		else for (int x = 0; x < numofmvs; x++) this.stepForward();
	}
}
