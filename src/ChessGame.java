
import java.util.ArrayList;
class ChessGame {
	private String lastsetlocmv = null;
	private int gameID = -1;
	private boolean completed = false;
	private int moveindex = -1;
	//private String[] LAST_UNDONE_MOVE = null;
	//private String[] LAST_REDONE_MOVE = null;
	private ArrayList<String[]> OFFICIAL_MOVES = null;
	private String[] UNOFFICIAL_MOVE = null;
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
	
	public int getGameID()
	{
		return this.gameID;
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
	
	public static void colorsForMovesAlternate(String[][] myoffmvs, boolean val)
	{
		String[] clrs = ChessPiece.getSideColorsForMoves(myoffmvs);
		String[] tps = ChessPiece.getSideTypesForMoves(myoffmvs);
		if (clrs == null || clrs.length != myoffmvs.length)
		{
			throw new IllegalStateException("myoffmvs must be the same size as the colors!");
		}
		else
		{
			String[] mvtps = ChessPiece.getMoveCommandTypes();
			for (int n = 0; n < myoffmvs.length; n++)
			{
				if (clrs[n].equals("WHITE") || clrs[n].equals("BLACK"))
				{
					if (n + 1 < myoffmvs.length)
					{
						System.out.println("tps[" + n + "] = " + tps[n]);
						
						boolean fismvcmd = ChessPiece.itemIsOnGivenList(tps[n], mvtps);
						if (fismvcmd);
						else continue;
						
						boolean needother = false;
						if (clrs[n + 1].equals(ChessPiece.getOppositeColor(clrs[n])))
						{
							if (fismvcmd && ChessPiece.itemIsOnGivenList(tps[n + 1], mvtps))
							{
								//do nothing
							}
							else needother = true;
						}
						else
						{
							System.out.println("tps[" + (n + 1) + "] = " + tps[n + 1]);
							boolean nxtismvcmd = ChessPiece.itemIsOnGivenList(tps[n + 1], mvtps);
							if (fismvcmd && nxtismvcmd)
							{
								throw new IllegalStateException("COLORS DO NOT ALTERNATE AND MUST!");
							}
							else needother = true;
						}
						
						if (needother)
						{
							int cmpi = -1;
							for (int x = n + 1; x < myoffmvs.length; x++)
							{
								if (tps[x].equals("HINTS"));
								else
								{
									cmpi = x;
									break;
								}
							}
							if (cmpi < 0 || myoffmvs.length - 1 < cmpi) break;
							//else;//do nothing proceed below
							if (clrs[cmpi].equals(ChessPiece.getOppositeColor(clrs[n])));
							else throw new IllegalStateException("COLORS DO NOT ALTERNATE AND MUST!");
						}
						//else;//do nothing
					}
					//else;//do nothing
				}
				else throw new IllegalStateException("COLOR (" + clrs[n] + ") IS INVALID!");
			}
		}
	}
	public static void colorsForMovesAlternate(ArrayList<String[]> mvslist)
	{
		if (mvslist == null || mvslist.size() < 1) return;
		else
		{
			String[][] mymvslist = new String[mvslist.size()][];
			for (int x = 0; x < mvslist.size(); x++) mymvslist[x] = mvslist.get(x);
			colorsForMovesAlternate(mymvslist, false);
		}
	}
	public void colorsForMovesAlternate()
	{
		colorsForMovesAlternate(OFFICIAL_MOVES);
	}
	
	public static String[][] convertArrayListStrArrToStringArr(ArrayList<String[]> mvslist)
	{
		if (mvslist == null || mvslist.size() < 1) return null;
		else
		{
			String[][] mymvslist = new String[mvslist.size()][];
			for (int x = 0; x < mvslist.size(); x++) mymvslist[x] = mvslist.get(x);
			return mymvslist;
		}
	}
	
	public static String getSideTurn(String[][] myoffmvs, boolean val)
	{
		//get last side to make an actual move
		//if no side -> return white
		//if a side has return the opposite color side
		String[] clrs = ChessPiece.getSideColorsForMoves(myoffmvs);
		String[] tps = ChessPiece.getSideTypesForMoves(myoffmvs);
		if (clrs == null && tps == null) return "WHITE";
		else if (clrs.length != myoffmvs.length)
		{
			throw new IllegalStateException("myoffmvs must be the same size as the colors!");
		}
		else
		{
			String[] mvtps = {"MOVE", "CASTLEING", "PAWNING", "PROMOTION"};
			int mvi = -1;
			for (int n = 0; n < myoffmvs.length; n++)
			{
				if (ChessPiece.itemIsOnGivenList(tps[n], mvtps)) mvi = n;
				//else;//do nothing
			}
			
			if (mvi < 0 || myoffmvs.length - 1 < mvi) return "WHITE";
			else return ChessPiece.getOppositeColor(clrs[mvi]);
		}
	}
	public static String getSideTurn(ArrayList<String[]> mvslist)
	{
		return getSideTurn(convertArrayListStrArrToStringArr(mvslist), false);
	}
	public String getSideTurn()
	{
		return getSideTurn(OFFICIAL_MOVES);
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
			//right here validate it
			colorsForMovesAlternate(myoffmvs, false);
			
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
			//colorsForMovesAlternate(this.OFFICIAL_MOVES);
		}
	}
	
	public void setUnofficialMove(String[] mymvcmd)
	{
		if (mymvcmd == null || mymvcmd.length < 1) UNOFFICIAL_MOVE = null;
		else
		{
			if (UNOFFICIAL_MOVE == null || UNOFFICIAL_MOVE.length < 1)
			{
				UNOFFICIAL_MOVE = new String[mymvcmd.length];
				for (int n = 0; n < mymvcmd.length; n++) UNOFFICIAL_MOVE[n] = "" + mymvcmd[n];
			}
			else throw new IllegalStateException("YOU NEED TO MAKE THE MOVE OFFICIAL FIRST!");
		}
	}
	
	public void addOfficialMove(String[] mymvcmd)
	{
		if (mymvcmd == null || mymvcmd.length < 1)
		{
			throw new IllegalStateException("cannot add an empty or null move to the official move list!");
		}
		else
		{
			String[] mycp = new String[mymvcmd.length];
			for (int n = 0; n < mymvcmd.length; n++) mycp[n] = "" + mymvcmd[n];
			
			if (OFFICIAL_MOVES == null) OFFICIAL_MOVES = new ArrayList<String[]>();
			//else;//do nothing
			
			OFFICIAL_MOVES.add(mycp);
			colorsForMovesAlternate();
		}
	}
	
	public void makeUnofficialMoveOfficial()
	{
		addOfficialMove(UNOFFICIAL_MOVE);
		setUnofficialMove(null);
	}
	
	public void makeLastOfficialMoveUnofficial()
	{
		if (UNOFFICIAL_MOVE == null)
		{
			if (OFFICIAL_MOVES == null) throw new IllegalStateException("THERE MUST BE AT LEAST ONE OFFICIAL MOVE!");
			else if (0 < OFFICIAL_MOVES.size()) setUnofficialMove(OFFICIAL_MOVES.get(OFFICIAL_MOVES.size() - 1));
			else throw new IllegalStateException("THERE MUST BE AT LEAST ONE OFFICIAL MOVE!");
		}
		else throw new IllegalStateException("THE UNOFFICIAL MOVE MUST BE EMPTY!");
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
			ChessPiece.makeLocalShortHandMove(this.OFFICIAL_MOVES.get(this.moveindex), this.getGameID(), false,
				ChessPiece.WHITE_MOVES_DOWN_RANKS, true);
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
			ChessPiece.WHITE_MOVES_DOWN_RANKS, true);
			
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
