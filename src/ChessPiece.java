
import java.util.ArrayList;
class ChessPiece {
	private static String[] validTypes = {"PAWN", "CASTLE", "KNIGHT", "BISHOP", "QUEEN", "KING", "ROOK"};
	private static String[] validColors = {"WHITE", "BLACK"};
	public static final int ROWCOLMIN = 0;
	public static final int ROWCOLMAX = 7;
	public static ArrayList<ChessPiece> cps = new ArrayList<ChessPiece>();
	//only one copy so will cause a problem with multiple games
	private String type = "";
	private String color = "";
	private int row = -1;
	private int col = -1;
	private int gameID = -1;
	private int movecount = 0;
	private boolean isfirstmove = true;
	
	public ChessPiece(String tp, String clr, int r, int c, int gid, int initmvcnt, boolean addit)
	{
		if (tp == null || clr == null) throw new IllegalStateException("the given type and color must not be null!");
		setRow(r);
		setCol(c);
		setType(tp.toUpperCase());
		setColor(clr.toUpperCase());
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else this.gameID = gid;
		if (addit) cps.add(this);
		if (initmvcnt < 1);
		else
		{
			this.movecount = initmvcnt;
			isfirstmove = false;
		}
	}
	public ChessPiece(String tp, String clr, int r, int c, int gid, boolean addit)
	{
		this(tp, clr, r, c, gid, 0, addit);
	}
	public ChessPiece(String tp, String clr, int r, int c, int gid)
	{
		this(tp, clr, r, c, gid, 0, true);
	}
	public ChessPiece(String tp, String clr, int[] loc, int gid, boolean addit)
	{
		this(tp, clr, loc[0], loc[1], gid, 0, addit);
	}
	public ChessPiece(String tp, String clr, int[] loc, int gid)
	{
		this(tp, clr, loc[0], loc[1], gid, 0, true);
	}
	public ChessPiece(String tp, String clr, int[] loc, int gid, int initmvcnt, boolean addit)
	{
		this(tp, clr, loc[0], loc[1], gid, initmvcnt, addit);
	}
	public ChessPiece(String tp, String clr, int r, int c, int gid, int initmvcnt)
	{
		this(tp, clr, r, c, gid, initmvcnt, true);
	}
	
	
	public int getGameID()
	{
		return 0 + this.gameID;
	}
	
	//GET ALL PIECES OF A GAME
	
	public static ArrayList<ChessPiece> getAllPiecesWithGameID(int val)
	{
		if (val < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else
		{
			ArrayList<ChessPiece> mycps = null;
			if (getNumItemsInList(cps) < 1) return null;
			//else;//do nothing
			for (int x = 0; x < cps.size(); x++)
			{
				if (cps.get(x).getGameID() == val)
				{
					if (mycps == null) mycps = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					mycps.add(cps.get(x));
				}
				//else;//do nothing
			}
			return mycps;
		}
	}
	public ArrayList<ChessPiece> getAllPiecesWithGameID()
	{
		return getAllPiecesWithGameID(getGameID());
	}
	
	
	public static String[] getValidTypesOrColors(boolean useclrs)
	{
		String[] marr = null;
		if (useclrs) marr = validColors;
		else marr = validTypes;
		final int maxitems = marr.length;
		String[] nwarr = new String[maxitems];
		for (int i = 0; i < maxitems; i++) nwarr[i] = "" + marr[i];
		return nwarr;
	}
	public static String[] getValidTypes()
	{
		return getValidTypesOrColors(false);
	}
	public static String[] getValidColors()
	{
		return getValidTypesOrColors(true);
	}
	
	public static int getSetUpColForType(String val, boolean uselft)
	{
		if (val == null) throw new IllegalStateException("null not allowed for the type of chess piece!");
		
		if (val.equals("CASTLE") || val.equals("ROOK"))
		{
			if (uselft) return 0;
			else return 7;
		}
		else if (val.equals("KNIGHT"))
		{
			if (uselft) return 1;
			else return 6;
		}
		else if (val.equals("BISHOP"))
		{
			if (uselft) return 2;
			else return 5;
		}
		else if (val.equals("QUEEN")) return 3;
		else if (val.equals("KING")) return 4;
		else throw new IllegalStateException("PAWNS ARE FOUND ON ALL COLS!");
	}
	
	public static boolean isGenderKnownForPiece(String tp)
	{
		if (itemIsOnGivenList(tp, validTypes));
		else throw new IllegalStateException("tp (" + tp + ") is not a valid chess piece type!");
		
		if (tp.equals("PAWN")) return false;//pawns are either
		else return true;
	}
	public boolean isGenderKnownForPiece()
	{
		return isGenderKnownForPiece(getType());
	}
	//false for female, true for male, ILLEGALSTATE for PAWNS
	public static boolean getGenderForPiece(String tp)
	{
		if (isGenderKnownForPiece(tp))
		{
			//return the answer
			if (tp.equals("QUEEN")) return false;
			else return true;
		}
		else throw new IllegalStateException("PAWNS ARE CAPABLE OF BOTH GENDERS SO GENDER IS UNKNOWN!");
	}
	public boolean getGenderForPiece()
	{
		return getGenderForPiece(getType());
	}
	public String convertGenderValueToString()
	{
		if (isGenderKnownForPiece())
		{
			if (getGenderForPiece()) return "MALE";
			else return "FEMALE";
		}
		else return "UNKNOWN";
	}
	
	public static boolean isvalidrorc(int val)
	{
		if (val < ChessPiece.ROWCOLMIN || ChessPiece.ROWCOLMAX < val) return false;
		else return true;
	}
	private void setRowOrCol(int val, boolean usecol)
	{
		if (isvalidrorc(val))
		{
			if (usecol) this.col = val;
			else this.row = val;
		}
		else throw new IllegalStateException("the value (" + val + ") for the row or column is invalid!");
	}
	private void setCol(int val)
	{
		setRowOrCol(val, true);
	}
	private void setRow(int val)
	{
		setRowOrCol(val, false);
	}
	
	public static String getLocString(int rval, int cval)
	{
		return "(row: " + rval + ", col: " + cval + ")";
	}
	public String getLocString()
	{
		return getLocString(getRow(), getCol());
	}
	
	public int getRowOrCol(boolean usecol)
	{
		if (usecol) return this.col;
		else return this.row;
	}
	public int getRow()
	{
		return getRowOrCol(false);
	}
	public int getCol()
	{
		return getRowOrCol(true);
	}
	public void setLoc(int rval, int cval)
	{
		if (canMoveToLoc(rval, cval))
		{
			setRow(rval);
			setCol(cval);
		}
		else throw new IllegalStateException("cannot move to new location " + getLocString(rval, cval) + "!");
	}
	
	public static void printOneDIntArray(int[] arr)
	{
		if (arr == null) System.out.println("arr = null!");
		else if (arr.length < 1) System.out.println("arr is empty!");
		else
		{
			for (int i = 0; i < arr.length; i++)
			{
				System.out.println("arr[" + i + "] = " + arr[i]);
			}
		}
		System.out.println();
	}
	
	//METHODS FOR GETTING NUM ITEMS IN LIST
	
	public static int getNumItemsInList(ArrayList mylist)
	{
		if (mylist == null) return 0;
		else return mylist.size();
	}
	public static int getNumItemsInList(Object[] arr)
	{
		if (arr == null) return 0;
		else return arr.length;
	}
	public static int getNumItemsInList(int[] arr)
	{
		if (arr == null) return 0;
		else return arr.length;
	}
	public static int getNumItemsInList(double[] arr)
	{
		if (arr == null) return 0;
		else return arr.length;
	}
	public static int getNumItemsInList(float[] arr)
	{
		if (arr == null) return 0;
		else return arr.length;
	}
	public static int getNumItemsInList(long[] arr)
	{
		if (arr == null) return 0;
		else return arr.length;
	}
	
	//CONVERT LOCS METHODS
	
	public static int[] convertStringLocToRowCol(String mlocstr)
	{
		if (mlocstr == null) throw new IllegalStateException("the locstring must not be null!");
		else
		{
			if (mlocstr.length() == 2);
			else throw new IllegalStateException("the locstring must be length 2!");
		}
		
		String abet = "ABCDEFGH";
		boolean fndltr = false;
		int ltri = -1;
		for (int i = 0; i < abet.length(); i++)
		{
			if (mlocstr.charAt(0) == abet.charAt(i))
			{
				ltri = i;
				fndltr = true;
				break;
			}
			//else;//do nothing
		}
		if (fndltr);
		else
		{
			throw new IllegalStateException("the locstr is in the wrong format! A letter must be first! " +
				"If a letter is actually first, then it is illegal! If it is legal, then it is not capitalized!");
		}
		
		String dgts = "0123456789";
		boolean fnddgt = false;
		for (int i = 1; i < dgts.length() - 1; i++)
		{
			if (dgts.charAt(i) == mlocstr.charAt(1))
			{
				fnddgt = true;
				break;
			}
			//else;//do nothing
		}
		if (fnddgt);
		else
		{
			throw new IllegalStateException("the locstr is in the wrong format! A digit must be last! " +
				"If a digit is actually last, then it is illegal!");
		}
		
		int[] myloc = new int[2];
		myloc[1] = Integer.parseInt("" + dgts.charAt(ltri));//letter is column
		myloc[0] = Integer.parseInt("" + mlocstr.charAt(1)) - 1;//number is row
		if (isvalidrorc(myloc[0]) && isvalidrorc(myloc[1]));
		else throw new IllegalStateException("CONVERSION ERROR! FINAL R AND C ARE NOT VALID!");
		return myloc;
	}
	public static String convertRowColToStringLoc(int rval, int cval)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("R OR C MUST BE VALID!");
		
		String abet = "ABCDEFGH";
		return "" + abet.charAt(cval) + "" + (rval + 1);
	}
	public static String convertRowColToStringLoc(int[] mloc)
	{
		if (mloc == null || mloc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return convertRowColToStringLoc(mloc[0], mloc[1]);
	}
	
	
	//GET PIECE AT AND IS LOCATION EMPTY METHODS
	
	public static ChessPiece getPieceAt(int rval, int cval, ArrayList<ChessPiece> mpclist)
	{
		if (mpclist == null || mpclist.size() < 1);
		else
		{
			for (int x = 0; x < mpclist.size(); x++)
			{
				if (mpclist.get(x).getRow() == rval && mpclist.get(x).getCol() == cval) return mpclist.get(x);
			}
		}
		//System.out.println("NO ITEMS FOUND AT: " + getLocString(rval, cval) + "!");
		return null;
	}
	public static ChessPiece getPieceAt(int[] loc, ArrayList<ChessPiece> mpclist)
	{
		if (loc == null || loc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return getPieceAt(loc[0], loc[1], mpclist);
	}
	public static ChessPiece getPieceAt(int rval, int cval, int gid)
	{
		return getPieceAt(rval, cval, getAllPiecesWithGameID(gid));
	}
	public static ChessPiece getPieceAt(int[] mloc, int gid)
	{
		if (mloc == null || mloc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return getPieceAt(mloc[0], mloc[1], gid);
	}
	public ChessPiece getPieceAt(int rval, int cval)
	{
		return getPieceAt(rval, cval, getAllPiecesWithGameID());
	}
	public ChessPiece getPieceAt(int[] mloc)
	{
		if (mloc == null || mloc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return getPieceAt(mloc[0], mloc[1]);
	}
	
	public static boolean isLocationEmpty(int rval, int cval, ArrayList<ChessPiece> mpclist)
	{
		ChessPiece cp = getPieceAt(rval, cval, mpclist);
		return (cp == null);
	}
	public static boolean isLocationEmpty(int rval, int cval, int gid)
	{
		ChessPiece cp = getPieceAt(rval, cval, gid);
		return (cp == null);
	}
	//prioritize addpcs list above board list
	public static boolean isLocationEmpty(int rval, int cval, int gid, ArrayList<ChessPiece> addpcs)
	{
		if (isLocationEmpty(rval, cval, addpcs)) return isLocationEmpty(rval, cval, gid);
		else return false;
	}
	public static boolean isLocationEmpty(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocationEmpty(loc[0], loc[1], gid);
	}
	public boolean isLocationEmpty(int rval, int cval)
	{
		return isLocationEmpty(rval, cval, getGameID());
	}
	public boolean isLocationEmpty(int[] loc)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocationEmpty(loc[0], loc[1], getGameID());
	}
	
	
	private void incrementMoveCount()
	{
		this.movecount = this.movecount + 1;
	}
	private void decrementMoveCount()
	{
		if (0 < this.movecount) this.movecount = this.movecount - 1;
	}
	
	
	public static boolean itemIsOnGivenList(String val, String[] arr)
	{
		if (arr == null || arr.length < 1) return false;
		for (int i = 0; i < arr.length; i++)
		{
			if (val == null)
			{
				if (arr[i] == null) return true;
				//else;//do nothing
			}
			else
			{
				if (arr[i] == null);
				else
				{
					if (val.equals(arr[i])) return true;
					//else;//do nothing
				}
			}
		}
		return false;
	}
	
	private void setTypeOrColor(String val, boolean useclr)
	{
		String[] marr = null;
		if (useclr) marr = validColors;
		else marr = validTypes;
		if (itemIsOnGivenList(val, marr))
		{
			if (useclr) this.color = "" + val;
			else
			{
				if (val.equals("ROOK")) this.type = "CASTLE";
				else this.type = "" + val;
			}
		}
		else
		{
			String mitemstr = "";
			if (useclr) mitemstr = "COLOR";
			else mitemstr = "TYPE";
			throw new IllegalStateException("ILLEGAL " + mitemstr + " (" + val + ") FOUND AND USED HERE!");
		}
	}
	private void setType(String val)
	{
		setTypeOrColor(val, false);
	}
	private void setColor(String val)
	{
		setTypeOrColor(val, true);
	}
	public String getTypeOrColor(boolean useclr)
	{
		if (useclr) return "" + this.color;
		else return "" + this.type;
	}
	public String getType()
	{
		return getTypeOrColor(false);
	}
	public String getColor()
	{
		return getTypeOrColor(true);
	}
	
	public static String getOppositeColor(String clrval)
	{
		if (clrval == null) throw new IllegalStateException("THE COLOR MUST NOT BE NULL!");
		else if (clrval.equals("WHITE")) return "BLACK";
		else if (clrval.equals("BLACK")) return "WHITE";
		else throw new IllegalStateException("INVALID COLOR (" + clrval + ") FOUND AND USED HERE!");
	}
	
	
	//FILTER METHODS
	
	public static ArrayList<ChessPiece> filterListByColor(ArrayList<ChessPiece> mylist, String clrval)
	{
		if (itemIsOnGivenList(clrval, validColors));
		else throw new IllegalStateException("ILLEGAL COLOR (" + clrval + ") FOUND AND USED HERE!");
		
		ArrayList<ChessPiece> myretlist = null;
		if (mylist == null || mylist.size() < 1) return null;
		else
		{
			for (int x = 0; x < mylist.size(); x++)
			{
				if (mylist.get(x).getColor().equals(clrval))
				{
					if (myretlist == null) myretlist = new ArrayList<ChessPiece>();
					//else;//do nothing
					myretlist.add(mylist.get(x));
				}
			}
			return myretlist;
		}
	}
	
	public static ArrayList<ChessPiece> filterListByType(ArrayList<ChessPiece> mylist, String typeval)
	{
		if (itemIsOnGivenList(typeval, validTypes));
		else throw new IllegalStateException("ILLEGAL TYPE (" + typeval + ") FOUND AND USED HERE!");
		
		ArrayList<ChessPiece> myretlist = null;
		if (mylist == null || mylist.size() < 1) return null;
		else
		{
			for (int x = 0; x < mylist.size(); x++)
			{
				if (mylist.get(x).getType().equals(typeval))
				{
					if (myretlist == null) myretlist = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					myretlist.add(mylist.get(x));
				}
			}
			return myretlist;
		}
	}
	
	public static ArrayList<ChessPiece> filterListByColorAndType(String typeval, String clrval,
		ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(filterListByType(allpcs, typeval), clrval);
	}
	public static ArrayList<ChessPiece> filterListByColorAndType(String typeval, String clrval, int gid)
	{
		return filterListByColorAndType(typeval, clrval, getAllPiecesWithGameID(gid));
	}
	
	
	public static ArrayList<ChessPiece> getCurrentSidePieces(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(allpcs, clrval);
	}
	public static ArrayList<ChessPiece> getCurrentSidePieces(String clrval, int gid)
	{
		return getCurrentSidePieces(clrval, getAllPiecesWithGameID(gid));
	}
	public static ArrayList<ChessPiece> getOpposingSidePieces(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return getCurrentSidePieces(getOppositeColor(clrval), allpcs);
	}
	public static ArrayList<ChessPiece> getOpposingSidePieces(String clrval, int gid)
	{
		return getOpposingSidePieces(clrval, getAllPiecesWithGameID(gid));
	}
	public ArrayList<ChessPiece> getMySidePieces()
	{
		return getCurrentSidePieces(getColor(), getGameID());
	}
	
	
	//GET ALL OF A CERTAIN TYPE
	
	public static ArrayList<ChessPiece> getAllOfType(String typeval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByType(allpcs, typeval);
	}
	public static ArrayList<ChessPiece> getAllOfType(String typeval, int gid)
	{
		return getAllOfType(typeval, getAllPiecesWithGameID(gid));
	}
	
	
	public static ArrayList<ChessPiece> getAllKings(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("KING", allpcs);
	}
	public static ArrayList<ChessPiece> getAllKings(int gid)
	{
		return getAllKings(getAllPiecesWithGameID(gid));//return getAllOfType("KING", gid);
	}
	public static ArrayList<ChessPiece> getAllCastles(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("CASTLE", allpcs);
	}
	public static ArrayList<ChessPiece> getAllCastles(int gid)
	{
		return getAllCastles(getAllPiecesWithGameID(gid));//return getAllOfType("CASTLE", gid);
	}
	public static ArrayList<ChessPiece> getAllRooks(ArrayList<ChessPiece> allpcs)
	{
		return getAllCastles(allpcs);
	}
	public static ArrayList<ChessPiece> getAllRooks(int gid)
	{
		return getAllCastles(gid);
	}
	public static ArrayList<ChessPiece> getAllQueens(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("QUEEN", allpcs);
	}
	public static ArrayList<ChessPiece> getAllQueens(int gid)
	{
		return getAllQueens(getAllPiecesWithGameID(gid));//return getAllOfType("QUEEN", gid);
	}
	public static ArrayList<ChessPiece> getAllKnights(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("KNIGHT", allpcs);
	}
	public static ArrayList<ChessPiece> getAllKnights(int gid)
	{
		return getAllKnights(getAllPiecesWithGameID(gid));//return getAllOfType("KNIGHT", gid);
	}
	public static ArrayList<ChessPiece> getAllBishops(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("BISHOP", allpcs);
	}
	public static ArrayList<ChessPiece> getAllBishops(int gid)
	{
		return getAllBishops(getAllPiecesWithGameID(gid));//return getAllOfType("BISHOP", gid);
	}
	public static ArrayList<ChessPiece> getAllPawns(ArrayList<ChessPiece> allpcs)
	{
		return getAllOfType("PAWN", allpcs);
	}
	public static ArrayList<ChessPiece> getAllPawns(int gid)
	{
		return getAllPawns(getAllPiecesWithGameID(gid));//return getAllOfType("PAWN", gid);
	}
	
	
	public static ArrayList<ChessPiece> getAllKnightsOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllKnights(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllKnightsOfColor(String clrval, int gid)
	{
		return getAllKnightsOfColor(clrval, getAllKnights(gid));
	}
	public static ArrayList<ChessPiece> getAllKingsOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllKings(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllKingsOfColor(String clrval, int gid)
	{
		return getAllKingsOfColor(clrval, getAllKings(gid));
	}
	public static ArrayList<ChessPiece> getAllCastlesOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllCastles(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllCastlesOfColor(String clrval, int gid)
	{
		return getAllCastlesOfColor(clrval, getAllCastles(gid));
	}
	public static ArrayList<ChessPiece> getAllRooksOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return getAllCastlesOfColor(clrval, allpcs);
	}
	public static ArrayList<ChessPiece> getAllRooksOfColor(String clrval, int gid)
	{
		return getAllCastlesOfColor(clrval, gid);
	}
	public static ArrayList<ChessPiece> getAllQueensOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllQueens(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllQueensOfColor(String clrval, int gid)
	{
		return getAllQueensOfColor(clrval, getAllQueens(gid));
	}
	public static ArrayList<ChessPiece> getAllBishopsOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllBishops(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllBishopsOfColor(String clrval, int gid)
	{
		return getAllBishopsOfColor(clrval, getAllBishops(gid));
	}
	public static ArrayList<ChessPiece> getAllPawnsOfColor(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return filterListByColor(getAllPawns(allpcs), clrval);
	}
	public static ArrayList<ChessPiece> getAllPawnsOfColor(String clrval, int gid)
	{
		return getAllPawnsOfColor(clrval, getAllPawns(gid));
	}
	
	public static ChessPiece getCurrentSideKing(String clrval, ArrayList<ChessPiece> allpcs)
	{
		if (itemIsOnGivenList(clrval, validColors));
		else throw new IllegalStateException("ILLEGAL COLOR (" + clrval + ") FOUND AND USED HERE!");
		
		ArrayList<ChessPiece> mysidepieces = getCurrentSidePieces(clrval, allpcs);
		if (mysidepieces == null || mysidepieces.size() < 1) return null;
		else
		{
			for (int x = 0; x < mysidepieces.size(); x++)
			{
				if (mysidepieces.get(x).getType().equals("KING")) return mysidepieces.get(x);
			}
			return null;
		}
	}
	public static ChessPiece getCurrentSideKing(String clrval, int gid)
	{
		return getCurrentSideKing(clrval, getAllPiecesWithGameID(gid));
	}
	public static ChessPiece getOppositeSideKing(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return getCurrentSideKing(getOppositeColor(clrval), allpcs);
	}
	public static ChessPiece getOppositeSideKing(String clrval, int gid)
	{
		return getOppositeSideKing(clrval, getAllPiecesWithGameID(gid));
	}
	public ChessPiece getMySideKing()
	{
		if (getType().equals("KING")) return this;
		else return getCurrentSideKing(getColor(), getGameID());
	}
	
	
	//THIS CANNOT TELL IF THE SET UP IS ILLEGAL OR NOT OR RATHER IT CANNOT TELL IF IT IS POSSIBLE OR NOT
	//IT CAN TELL IF THERE ARE AN ILLEGAL NUMBER OF PIECES ON THE BOARD
	public static boolean isBoardValid(ArrayList<ChessPiece> allpcs)
	{
		//each side must have at most 16 pieces total one of which must be a king
		//there are only 8 pawns so at most 8 pawns plus one of each
		//the most we can have of any one piece excluding kings and pawns is 9
		//at most 1 king, 8 pawns, 9 of the others per side.
		//if we have 9 of one we will have no pawns.
		
		//the # of pawns on the board will be minus one for every one more of another type.
		
		ArrayList<ChessPiece> wkgs = filterListByColorAndType("KING", "WHITE", allpcs);
		ArrayList<ChessPiece> bkgs = filterListByColorAndType("KING", "BLACK", allpcs);
		
		if (wkgs == null) return false;
		else if (1 < wkgs.size()) return false;
		if (bkgs == null) return false;
		else if (1 < bkgs.size()) return false;
		
		ArrayList<ChessPiece> wkts = filterListByColorAndType("KNIGHT", "WHITE", allpcs);
		ArrayList<ChessPiece> bkts = filterListByColorAndType("KNIGHT", "BLACK", allpcs);
		
		if (wkts == null);
		else if (9 < wkts.size()) return false;
		if (bkts == null);
		else if (9 < bkts.size()) return false;
		
		ArrayList<ChessPiece> wcls = filterListByColorAndType("CASTLE", "WHITE", allpcs);
		ArrayList<ChessPiece> bcls = filterListByColorAndType("CASTLE", "BLACK", allpcs);
		
		if (wcls == null);
		else if (9 < wcls.size()) return false;
		if (bcls == null);
		else if (9 < bcls.size()) return false;
		
		ArrayList<ChessPiece> wqns = filterListByColorAndType("QUEEN", "WHITE", allpcs);
		ArrayList<ChessPiece> bqns = filterListByColorAndType("QUEEN", "BLACK", allpcs);
		
		if (wqns == null);
		else if (9 < wqns.size()) return false;
		if (bqns == null);
		else if (9 < bqns.size()) return false;
		
		ArrayList<ChessPiece> wbps = filterListByColorAndType("BISHOP", "WHITE", allpcs);
		ArrayList<ChessPiece> bbps = filterListByColorAndType("BISHOP", "BLACK", allpcs);
		
		if (wbps == null);
		else if (9 < wbps.size()) return false;
		if (bbps == null);
		else if (9 < bbps.size()) return false;
		
		ArrayList<ChessPiece> wpns = filterListByColorAndType("PAWN", "WHITE", allpcs);
		ArrayList<ChessPiece> bpns = filterListByColorAndType("PAWN", "BLACK", allpcs);
		
		if (wpns == null);
		else if (8 < wpns.size()) return false;
		if (bpns == null);
		else if (8 < bpns.size()) return false;
		
		int numwpns = getNumItemsInList(wpns);
		int numbpns = getNumItemsInList(bpns);
		
		int numwcls = getNumItemsInList(wcls);
		int numbcls = getNumItemsInList(bcls);
		
		int numwbps = getNumItemsInList(wbps);
		int numbbps = getNumItemsInList(bbps);
		
		int numwqns = getNumItemsInList(wqns);
		int numbqns = getNumItemsInList(bqns);
		
		int numwkts = getNumItemsInList(wkts);
		int numbkts = getNumItemsInList(bkts);
		
		int[] mywitemdiffs = {numwcls - 2, numwbps - 2, numwqns - 1, numwkts - 2};
		int[] mybitemdiffs = {numbcls - 2, numbbps - 2, numbqns - 1, numbkts - 2};
		int twdiff = 0;
		for (int x = 0; x < mywitemdiffs.length; x++)
		{
			if (mywitemdiffs[x] < 1);
			else twdiff += mywitemdiffs[x];
		}
		int tbdiff = 0;
		for (int x = 0; x < mybitemdiffs.length; x++)
		{
			if (mybitemdiffs[x] < 1);
			else tbdiff += mybitemdiffs[x];
		}
		//System.out.println("numwpns = " + numwpns);
		//System.out.println("numbpns = " + numbpns);
		//System.out.println("numwcls = " + numwcls);
		//System.out.println("numbcls = " + numbcls);
		//System.out.println("numwbps = " + numwbps);
		//System.out.println("numbbps = " + numbbps);
		//System.out.println("numwqns = " + numwqns);
		//System.out.println("numbqns = " + numbqns);
		//System.out.println("numwkts = " + numwkts);
		//System.out.println("numbkts = " + numbkts);
		//System.out.println();
		//System.out.println("twdiff = " + twdiff);
		//System.out.println("tbdiff = " + tbdiff);
		//System.out.println("twdiff + numwpns = " + (twdiff + numwpns));
		//System.out.println("tbdiff + numbpns = " + (tbdiff + numbpns));
		
		//total converted pawns + num pawns must be less than or equal to 8
		boolean errwfnd = false;
		boolean errbfnd = false;
		if (8 < numwpns + twdiff) errwfnd = true;
		if (8 < numbpns + tbdiff) errbfnd = true;
		if (16 < numwpns + numwcls + numwbps + numwqns + numwkts + 1) errwfnd = true;
		if (16 < numbpns + numbcls + numbbps + numbqns + numbkts + 1) errbfnd = true;
		if (errwfnd) throw new IllegalStateException("ILLEGAL NUMBER OF WHITE PIECES FOUND ON THE BOARD!");
		if (errbfnd) throw new IllegalStateException("ILLEGAL NUMBER OF BLACK PIECES FOUND ON THE BOARD!");
		return true;
	}
	public static boolean isBoardValid(int gid)
	{
		return isBoardValid(getAllPiecesWithGameID(gid));
	}
	
	
	
	//NEED TO KNOW WHERE CERTAIN PIECES CAN MOVE TO...
	//NEED TO KNOW WHOSE TURN IT IS AND
	//NEED TO PREVENT THE OTHER SIDE FROM MOVING UNTIL WE TELL THEM IT IS THEIR TURN
	//NEED TO KNOW IF THE GAME ENDS IN AUTO STALEMATE OR CHECKMATE
	//NEEDS A WAY TO SURRENDER
	//NEEDS A WAY TO UNDO OR REDO A MOVE
	//NEED A WAY TO STEP THROUGH A FINISHED GAME
	//
	//BEFORE WE ADVANCE TO THE OTHER SIDE'S TURN:
	//MAKE THE UNOFFICIAL MOVE OFFICIAL
	//IF THEY ARE IN CHECK AND THE UNOFFICIAL MOVE DID NOT MOVE THEM OUT OF CHECK ASK IF THEY WANT TO SURRENDER OR UNDO?
	//IF THEY CHOOSE SURRENDER: CHECKMATE!
	//IF THEY CHOOSE UNDO: NEED TO LET THEM MOVE THEN RE-ENTER THIS METHOD.
	//WE NEED TO CHECK TO SEE IF THE CURRENT SIDE KING IS STILL IN CHECK() IF SO, END GAME IMMEDIATELY CHECKMATE!
	//CHECK TO SEE IF THE GAME ENDS IN AN AUTO-STALEMATE
	//WE NEED TO CHECK TO SEE IF THERE ARE PAWNS FOR THAT SIDE THAT HAVE MADE IT TO THE OTHER SIDE AND
	//-NEED PROMOTED AND TO PROMOTE THEM
	//
	
	
	
	//THE OVERALL GOAL OF THE NEXT SET OF METHODS IS TO DETERMINE WHAT PIECES CAN ATTACK A LOCATION
	//
	//NEED TO KNOW WHAT LOCATIONS CAN BE ATTACKED BY THE OPPOSITE SIDE. (DONE)
	//NEED TO KNOW IF EXCLUDING CERTAIN PIECIES EFFECT IT. (DONE)
	//NEED TO KNOW IF ADDING CERTAIN PIECES AT CERTAIN SPOTS EFFECT IT. (DONE)
	//
	//WE NEED TO KNOW HOW ADDING A PIECE AT CERTAIN SPOTS AND IGNORING OTHERS AT CERTAIN SPOTS EFFECT THIS?
	//WE DO NOT WANT THE NEW PIECE(S) TO BE ON THE BOARD!!!
	//WE ALSO DO NOT WANT IT TO BE ADDED TO THE LIST OF OFFICIAL CHESS PIECES!!!
	//WE HAVE A LIST OF PIECES ON THE BOARD
	//WE CAN TELL THE ALGORITHM TO IGNORE SOME OF THEM WHEN SEARCHING FOR GUARDING LOCATIONS
	//WE WANT TO BE ABLE TO TELL THAT SAME ALGORITHM THAT SOME NEW PIECES ARE AT A CERTAIN LOCATION
	//WE WANT TO KEEP THE RETURN TYPE THE SAME!
	//TELL THE CONSTRUCTOR NOT TO ADD IT SO IT WILL THINK IT IS NOT ON THE BOARD
	
	
	//NOTE: THE IS LOCATION GUARDED METHODS ARE NOT AS ACCURATE AS THE GET PIECES GUARDING LOCATION METHODS
	
	private static int[][] getAllPossibleKnightMoveToLocs(int rval, int cval)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		
		int[][] pklocs = new int[8][2];
		pklocs[0][0] = rval - 2;
		pklocs[0][1] = cval - 1;
		
		pklocs[1][0] = rval - 2;
		pklocs[1][1] = cval + 1;
		
		pklocs[2][0] = rval + 2;
		pklocs[2][1] = cval - 1;
		
		pklocs[3][0] = rval + 2;
		pklocs[3][1] = cval + 1;
		
		pklocs[4][0] = rval - 1;
		pklocs[4][1] = cval - 2;
		
		pklocs[5][0] = rval - 1;
		pklocs[5][1] = cval + 2;
		
		pklocs[6][0] = rval + 1;
		pklocs[6][1] = cval - 2;
		
		pklocs[7][0] = rval + 1;
		pklocs[7][1] = cval + 2;
		
		return pklocs;
	}
	private static int[][] getAllPossibleKnightMoveToLocs(int[] loc)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getAllPossibleKnightMoveToLocs(loc[0], loc[1]);
	}
	
	
	//IF addpcs IS NULL OR EMPTY, THEN THE LIST USED IS THE LIST OF ALL PIECES.
	public static boolean isPieceAtLocationOnAListOfTypes(int rval, int cval, int gid, String[] mtypes,
		ArrayList<ChessPiece> addpcs)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		//System.out.println("INSIDE OF IS PIECE AT LOCATION ON A LIST OF TYPES WITH LOCATION: " +
		//	getLocString(rval, cval));
		//System.out.println("gid = " + gid);
		//System.out.println("addpcs = " + addpcs);
		//System.out.print("mtypes = [");
		//for (int x = 0; x < mtypes.length; x++)
		//{
		//	System.out.print('"' + mtypes[x] + '"');
		//	if (x + 1 < mtypes.length) System.out.print(", ");
		//}
		//System.out.println("]");
		
		if (getNumItemsInList(addpcs) < 1);//no items on the add pieces list
		else
		{
			//System.out.println("INSIDE ELSE STATEMENT!");
			for (int x = 0; x < addpcs.size(); x++)
			{
				//System.out.println("x = " + x);
				//System.out.println("addpcs.get(" + x + ") = " + addpcs.get(x));
				//System.out.println("row = " + addpcs.get(x).getRow());
				//System.out.println("col = " + addpcs.get(x).getCol());
				if (addpcs.get(x).getRow() == rval && addpcs.get(x).getCol() == cval)
				{
					if (itemIsOnGivenList(addpcs.get(x).getType(), mtypes)) return true;
					else return false;
				}
				//else;//do nothing
			}
		}
		//System.out.println("LOCATION IS NOT ON ADD PIECES LIST!");
		ChessPiece cp = getPieceAt(rval, cval, gid);
		//System.out.println(cp);
		if (cp == null);//do nothing location is empty
		else
		{
			if (itemIsOnGivenList(cp.getType(), mtypes))
			{
				//System.out.println("FOUND IT!");
				return true;
			}
			//else;//do nothing
		}
		//System.out.println("DID NOT FIND IT!");
		return false;
		
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int[] loc, int gid, String[] mtypes, ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isPieceAtLocationOnAListOfTypes(loc[0], loc[1], gid, mtypes, addpcs);
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int rval, int cval, int gid, String[] mtypes)
	{
		return isPieceAtLocationOnAListOfTypes(rval, cval, gid, mtypes, null);
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int[] loc, int gid, String[] mtypes)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isPieceAtLocationOnAListOfTypes(loc[0], loc[1], gid, mtypes);
	}
	
	
	//this checks the diagnals for a Bishop a Pawn or a Queen the first one it finds starting at rval cval it will return true
	//that means if you call this on a Bishop, Pawn, or Queen it will return true immediately
	//it will not be conclusive as to if it is protected by one.
	public static boolean isSameDiagnalLocationGuarded(int rval, int cval, int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//diagnals only no pawning
		//up 1 right 1
		//up 1 left 1
		//down 1 right 1
		//down 1 left 1
		//0, 0 is at top left of board
		//7, 7 is at bottom right of board
		//0, 7 is at top right of board
		//7, 0 is at bottom left of board
		//r and c will always increment towards bottom right
		//r and c will always decrement towards top left
		String[] myvtps = {"BISHOP", "PAWN", "QUEEN", "KING"};
		for (int x = 0; x < 4; x++)
		{
			int r = rval;
			int c = cval;
			while (isvalidrorc(r) && isvalidrorc(c))
			{
				if (isPieceAtLocationOnAListOfTypes(r, c, gid, myvtps)) return true;
				else
				{
					if (isLocationEmpty(r, c, gid));
					else
					{
						if (r == rval && c == cval);
						else break;
					}
				}
				
				//increment the variables
				if (x == 0)
				{
					//go towards bottom right
					r++;
					c++;
				}
				else if (x == 1)
				{
					//go towards top left
					r--;
					c--;
				}
				else if (x == 2)
				{
					//go towards top right
					r--;
					c++;
				}
				else if (x == 3)
				{
					//go towards bottom left
					r++;
					c--;
				}
				else throw new IllegalStateException("ILLEGAL VALUE FOUND AND USED HERE FOR INDEX X!");
			}//end of while loop
		}//end of x for loop
		
		return false;
	}
	public static boolean isSameDiagnalLocationGuarded(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isSameDiagnalLocationGuarded(loc[0], loc[1], gid);
	}
	
	//this checks the rows or columns for a CASTLE, ROOK, QUEEN, OR KING and returns true on the first one found
	//this will return true immediately if called on one of the above.
	public static boolean isSameRowOrSameColLocationGuarded(int rval, int cval, int gid)
	{
		//row or col is the same
		//assume if we run into a piece other than a castle or a queen
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//move along the row starting at cval
		//move along the col starting at row
		//go up
		//go down
		//go left to right
		String[] myvtps = {"CASTLE", "ROOK", "QUEEN", "KING"};
		for (int r = rval; r < 8; r++)
		{
			if (isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps)) return true;
			else
			{
				if (isLocationEmpty(r, cval, gid));
				else
				{
					if (r == rval);
					else break;
				}
			}
		}
		for (int r = rval; ((r == 0 || 0 < r) && r < 8); r--)
		{
			if (isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps)) return true;
			else
			{
				if (isLocationEmpty(r, cval, gid));
				else
				{
					if (r == rval);
					else break;
				}
			}
		}
		for (int c = cval; c < 8; c++)
		{
			if (isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps)) return true;
			else
			{
				if (isLocationEmpty(rval, c, gid));
				else
				{
					if (c == cval);
					else break;
				}
			}
		}
		for (int c = cval; ((c == 0 || 0 < c) && c < 8); c--)
		{
			if (isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps)) return true;
			else
			{
				if (isLocationEmpty(rval, c, gid));
				else
				{
					if (c == cval);
					else break;
				}
			}
		}
		return false;
	}
	public static boolean isSameRowOrSameColLocationGuarded(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isSameRowOrSameColLocationGuarded(loc[0], loc[1], gid);
	}
	
	public static boolean isLocationGuardedByAKnight(int rval, int cval, int gid)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		int[][] pklocs = getAllPossibleKnightMoveToLocs(rval, cval);
		
		String[] mvtps = {"KNIGHT"};
		for (int x = 0; x < 8; x++)
		{
			if (isvalidrorc(pklocs[x][0]) && isvalidrorc(pklocs[x][1]))
			{
				if (isPieceAtLocationOnAListOfTypes(pklocs[x][0], pklocs[x][1], gid, mvtps)) return true;
			}
			//else;//do nothing
		}
		return false;
	}
	public static boolean isLocationGuardedByAKnight(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocationGuardedByAKnight(loc[0], loc[1], gid);
	}
	//this piece will not be a KNIGHT, but it checks for the others
	public static boolean isLocationGuardedByAnythingOtherThanAKnight(int rval, int cval, int gid)
	{
		return (isSameRowOrSameColLocationGuarded(rval, cval, gid) || isSameDiagnalLocationGuarded(rval, cval, gid));
	}
	public static boolean isLocationGuardedByAnythingOtherThanAKnight(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocationGuardedByAnythingOtherThanAKnight(loc[0], loc[1], gid);
	}
	//this hints as to a possibility of the location being directly attacked by something unless you call it on a piece
	public static boolean isLocationGuarded(int rval, int cval, int gid)
	{
		return (isLocationGuardedByAnythingOtherThanAKnight(rval, cval, gid) || isLocationGuardedByAKnight(rval, cval, gid));
	}
	public static boolean isLocationGuarded(int[] loc, int gid)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocationGuarded(loc[0], loc[1], gid);
	}
	
	
	
	//IS A LOC ON A LIST OF LOCS
	
	public static boolean isLocOnListOfLocs(int rval, int cval, int[][] loclist)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		
		if (loclist == null || loclist.length < 1) return false;
		else if (loclist[0] == null || loclist[0].length != 2) return false;
		else
		{
			for (int x = 0; x < loclist.length; x++)
			{
				if (loclist[x][0] == rval && loclist[x][1] == cval) return true;
			}
			return false;
		}
	}
	public static boolean isLocOnListOfLocs(int[] loc, int[][] loclist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return isLocOnListOfLocs(loc[0], loc[1], loclist);
	}
	
	
	//DETECTS PIECES DIRECTLY ABLE TO ATTACK OR MOVE TO A LOCATION
	
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int rval, int cval, int gid,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		int[][] pklocs = getAllPossibleKnightMoveToLocs(rval, cval);
		
		String[] mvtps = {"KNIGHT"};
		ArrayList<ChessPiece> gpcs = null;
		if (isvalidrorc(rval) && isvalidrorc(cval))
		{
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, cval, mvtps, ignorelist, addpcs);
			boolean loconiglist = logonigvtpdtalist[0];
			boolean pcatloconiglist = logonigvtpdtalist[1];
			boolean isvpctpeoniglist = logonigvtpdtalist[2];
			
			boolean exitif = false;
			if (loconiglist)
			{
				if (pcatloconiglist);
				else exitif = true;
			}
			//else;//do nothing
			
			if (exitif);
			else
			{
				if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
					(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, cval, gid, mvtps, addpcs)))
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					gpcs.add(getPieceAt(rval, cval, addpcs));
				}
				//else;//do nothing
			}
		}
		//else;//do nothing
		
		for (int x = 0; x < 8; x++)
		{
			if (isvalidrorc(pklocs[x][0]) && isvalidrorc(pklocs[x][1]))
			{
				boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(pklocs[x][0], pklocs[x][1], mvtps,
					ignorelist, addpcs);
				boolean loconiglist = logonigvtpdtalist[0];
				boolean pcatloconiglist = logonigvtpdtalist[1];
				boolean isvpctpeoniglist = logonigvtpdtalist[2];
				if (loconiglist && !pcatloconiglist) continue;
				//else;//do nothing safe to proceed
				
				if (loconiglist)
				{
					if (pcatloconiglist);
					else
					{
						throw new IllegalStateException("WE ARE AT AN IGNORE LIST SPOT, BUT THERE IS NO PIECE THERE, " +
							"SO SHOULD NOT HAVE MADE IT HERE!");
					}
				}
				//else;//do nothing
				
				if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
					(!loconiglist && isPieceAtLocationOnAListOfTypes(pklocs[x][0], pklocs[x][1], gid, mvtps, addpcs)))
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					gpcs.add(getPieceAt(pklocs[x][0], pklocs[x][1], addpcs));
				}
				//else;//do nothing
			}
			//else;//do nothing
		}
		return gpcs;
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int loc[], int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocationByAKnight(loc[0], loc[1], gid, ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int rval, int cval, int gid,
		int[][] ignorelist)
	{
		return getPiecesGuardingLocationByAKnight(rval, cval, gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int loc[], int gid, int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocationByAKnight(loc[0], loc[1], gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int rval, int cval, int gid)
	{
		return getPiecesGuardingLocationByAKnight(rval, cval, gid, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int[] loc, int gid)
	{
		return getPiecesGuardingLocationByAKnight(loc, gid, null);
	}
	
	
	private static boolean getCanAddPieceToGList(ChessPiece cp, String[] myvtps, int srval, int scval,
		boolean initaddit, boolean usecdiff)
	{
		boolean addit = initaddit; 
		if (cp == null) return false;
		else
		{
			//the piece is on our list of types, but it may not be able to attack the location
			//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
			if (isPieceAtLocationOnAListOfTypes(cp.getRow(), cp.getCol(), cp.getGameID(), myvtps))
			{
				//compute the distance between rval and cval to r and c
				//just use the magnitude of the cols
				if (usecdiff)
				{
					int cdiff = 0;
					if (cp.getCol() - scval < 0) cdiff = scval - cp.getCol();
					else cdiff = cp.getCol() - scval;
					if (1 < cdiff) addit = false;
				}
				else
				{
					int rdiff = 0;
					if (cp.getRow() - srval < 0) rdiff = srval - cp.getRow();
					else rdiff = cp.getRow() - srval;
					if (1 < rdiff) addit = false;
				}
				
				
				if (addit)
				{
					//System.out.println("DIFF NOT TOO BIG!");
					if (cp.getType().equals("PAWN"))
					{
						if (cp.getRow() == srval && cp.getCol() == scval);
						else
						{
							//we want to know if the pawn can actually move in that direction
							if ((cp.getColor().equals("WHITE") && cp.getRow() - 1 == srval) ||
								(cp.getColor().equals("BLACK") && cp.getRow() + 1 == srval))
							{
								//addit so do nothing
							}
							else
							{
								addit = false;
								//System.out.println("PAWN NOT MOVING IN THE CORRECT DIRECTION!");
							}
						}
					}
					//else;//do nothing
				}
				else
				{
					//System.out.println("DIFF TOO BIG!");
					return false;
				}
			}
			else return true;
			return addit;
		}
	}
	private static boolean getCanAddPieceToGList(ChessPiece cp, String[] myvtps, int[] sloc,
		boolean initaddit, boolean usecdiff)
	{
		if (sloc == null || sloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the current chess piece location!");
		}
		else return getCanAddPieceToGList(cp, myvtps, sloc[0], sloc[1], initaddit, usecdiff);
	}
	private static boolean getCanAddPieceToGList(int rval, int cval, String[] myvtps, int srval, int scval,
		boolean initaddit, boolean usecdiff, int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else return getCanAddPieceToGList(getPieceAt(rval, cval, gid), myvtps, srval, scval, initaddit, usecdiff);
	}
	private static boolean getCanAddPieceToGList(int[] nloc, String[] myvtps, int[] sloc,
		boolean initaddit, boolean usecdiff, int gid)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		//else;//do nothing
		if (sloc == null || sloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the current chess piece location!");
		}
		//else;//do nothing
		return getCanAddPieceToGList(nloc[0], nloc[1], myvtps, sloc[0], sloc[1], initaddit, usecdiff, gid);
	}
	
	
	public static boolean[] getLocOnIgnoreListAndValidTypeData(int rval, int cval, String[] myvtps,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		boolean loconiglist = false;
		boolean pcatloconiglist = false;
		boolean isvpctpeoniglist = false;
		if (isLocOnListOfLocs(rval, cval, ignorelist))
		{
			//is there a piece on the add list that matches the loc?
			loconiglist = true;
			ChessPiece cp = getPieceAt(rval, cval, addpcs);
			if (cp == null);
			else
			{
				pcatloconiglist = true;
				isvpctpeoniglist = itemIsOnGivenList(cp.getType(), myvtps);
			}
		}
		//else;//do nothing safe to proceed
		
		boolean[] rvals = {loconiglist, pcatloconiglist, isvpctpeoniglist};
		return rvals;
	}
	
	
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int rval, int cval, int gid,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//diagnals only no pawning
		//up 1 right 1
		//up 1 left 1
		//down 1 right 1
		//down 1 left 1
		//0, 0 is at top left of board
		//7, 7 is at bottom right of board
		//0, 7 is at top right of board
		//7, 0 is at bottom left of board
		//r and c will always increment towards bottom right
		//r and c will always decrement towards top left
		String[] myvtps = {"BISHOP", "PAWN", "QUEEN", "KING"};
		ArrayList<ChessPiece> gpcs = null;
		
		for (int x = 0; x < 4; x++)
		{
			//System.out.println("x = " + x);
			
			int r = rval;
			int c = cval;
			while (isvalidrorc(r) && isvalidrorc(c))
			{
				//System.out.println("r = " + r);
				//System.out.println("c = " + c);
				boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, c, myvtps, ignorelist, addpcs);
				boolean loconiglist = logonigvtpdtalist[0];
				boolean pcatloconiglist = logonigvtpdtalist[1];
				boolean isvpctpeoniglist = logonigvtpdtalist[2];
				//System.out.println("loconiglist = " + loconiglist);
				//System.out.println("pcatloconiglist = " + pcatloconiglist);
				//System.out.println("isvpctpeoniglist = " + isvpctpeoniglist);
				boolean inconly = (loconiglist && !pcatloconiglist);
				//System.out.println("inconly = " + inconly);
				
				//GIVEN: NO MATTER WHAT THERE WILL BE AT LEAST 2 CHESS PIECES ON THE BOARD
				//GIVEN: NORMAL BEHAVIOR IS TO FIND PIECES ON THE BOARD, THAT ARE OF A CERTAIN TYPE, AND
				//SEE IF IT CAN MOVE TO SAID LOC
				//GIVEN: SEARCH PATTERN IS SEARCH PATTERN IS BISHOP/CASTLE/KNIGHT...
				//AND HAS NO EFFECT OTHER THAN IT DETERMINES THE TYPES OF PIECES WE ARE GENERALLY LOOKING FOR
				//GIVEN: A LOCATION, A LIST OF LOCATIONS TO IGNORE, A LIST OF NEW PIECES AT LOCATIONS
				//
				//RULE: PRIORITIZE ADD LIST OVER BOARD (USE PIECES ON ADD LIST INSTEAD OF ON BOARD).
				//RULE: PRIORITIZE ADD LIST OVER IGNORE LIST
				//(USE PIECES ON ADD LIST INSTEAD OF SKIPPING LOCATION IF THE SAME).
				//
				//IDEA: IS IF WE WANT TO SIMULATE MOVING, ADD A PIECE TO THE IGNORE LIST THEN PUT IT ON THE ADD LIST
				//ULTIMATE GOAL: SEE HOW MOVING TO A CERTAIN LOCATION EFFECTS ATTACKING LOCATIONS
				//
				//If there are pieces on BOTH THE BOARD (ALL_CHESSPIECES LIST, THE NEW PIECES WILL NOT BE ON THIS LIST)
				//AND THE ADD LIST, WHAT LIST DO WE CHECK? SHOULD WE CHECK BOTH?
				//
				//BOARD:
				//4,3 PAWN
				//4,4 PAWN
				//7,4 KING
				//0,4 KING
				//... OTHERS
				//
				//IGNORE LIST:
				//4,3 PAWN
				//
				//ADD LIST:
				//4,3 QUEEN
				//
				//IF THE LOCATION IS ON BOTH THE IGNORE LIST AND ON THE ADD LIST
				//CANNNOT OUTRIGHT SKIP THAT LOCATION, BUT MUST PRIORITIZE ADD LIST OVER BOARD IN THAT CASE
				
				//IF NOT AT A SPOT ON THE IGNORE LIST AND THE ADD LIST DOES NOT HAVE A PIECE THERE,
				//THEN WE USE THE BOARD LIST.
				
				
				//if no pieces on the addlist, then behaves normal
				//(looks for pieces on the board that are of certain types and if it finds it,
				//checks to see if it can move in the required way)
				//
				//if there are pieces on the addlist, then it should prioritize the addlist
				//look at that list instead of the board list first.
				//
				//IF IT IS NOT AT A LOCATION ON THE IGNORE LIST, THEN ?
				//
				//
				
				if (inconly);
				else
				{
					if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
						(!loconiglist && isPieceAtLocationOnAListOfTypes(r, c, gid, myvtps, addpcs)))
					{
						boolean addit = true;
						if (c == cval && r == rval)
						{
							if (x == 0) addit = true;
							else addit = false;
						}
						else addit = true;
						
						//the piece is on our list of types, but it may not be able to attack the location
						//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
						String[] rstps = {"PAWN", "KING"};
						ChessPiece cp = null;
						if (getNumItemsInList(addpcs) < 1);
						else cp = getPieceAt(r, c, addpcs);
						//System.out.println("INIT cp = " + cp);
						if (cp == null) cp = getPieceAt(r, c, getAllPiecesWithGameID(gid));
						//System.out.println("FINAL cp = " + cp);
						addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
						
						if (addit)
						{
							if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
							//else;//do nothing
							
							gpcs.add(cp);
						}
						//else;//do nothing
					}
					else
					{
						boolean locntempty = true;
						if (loconiglist);//the location is not empty
						else
						{
							if (isLocationEmpty(r, c, gid, addpcs)) locntempty = false;
							//else;//do nothing
						}
						if (locntempty)
						{
							if (r == rval && c == cval);
							else break;
						}
						//else;//do nothing
					}
				}
				
				
				//increment the variables
				//System.out.println("x = " + x);
				if (x == 0)
				{
					//go towards bottom right
					//System.out.println("TOWARDS BOTTOM RIGHT!");
					r++;
					c++;
				}
				else if (x == 1)
				{
					//go towards top left
					//System.out.println("TOWARDS TOP LEFT!");
					r--;
					c--;
				}
				else if (x == 2)
				{
					//go towards top right
					//System.out.println("TOWARDS TOP RIGHT!");
					r--;
					c++;
				}
				else if (x == 3)
				{
					//go towards bottom left
					//System.out.println("TOWARDS BOTTOM LEFT!");
					r++;
					c--;
				}
				else throw new IllegalStateException("ILLEGAL VALUE FOUND AND USED HERE FOR INDEX X!");
			}//end of while loop
		}//end of x for loop
		
		return gpcs;
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int rval, int cval, int gid,
		int[][] ignorelist)
	{
		return getPiecesGuardingLocationOnSameDiagnal(rval, cval, gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int[] loc, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocationOnSameDiagnal(loc[0], loc[1], gid, ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int[] loc, int gid, int[][] ignorelist)
	{
		return getPiecesGuardingLocationOnSameDiagnal(loc, gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int rval, int cval, int gid)
	{
		return getPiecesGuardingLocationOnSameDiagnal(rval, cval, gid, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameDiagnal(int[] loc, int gid)
	{
		return getPiecesGuardingLocationOnSameDiagnal(loc, gid, null);
	}
	
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int rval, int cval, int gid,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		//System.out.println("INSIDE GET PIECES GUARDING LOCATION ON SAME ROW OR COL() WITH LOCATION: " +
		//	getLocString(rval, cval));
		//System.out.println("gid = " + gid);
		//System.out.println("addpcs = " + addpcs);
		//if (ignorelist == null) System.out.println("ignorelist = null!");
		//else
		//{
		//	if (ignorelist.length < 1) System.out.println("ignorelist is empty!");
		//	else
		//	{
		//		for (int x = 0; x < ignorelist.length; x++)
		//		{
		//			for (int c = 0; c < ignorelist[x].length; c++)
		//			{
		//				System.out.println("ignorelist[" + x + "][" + c + "] = " + ignorelist[x][c]);
		//			}
		//		}
		//	}
		//}
		
		
		//move along the row starting at cval
		//move along the col starting at row
		//go up
		//go down
		//go left to right
		String[] myvtps = {"CASTLE", "ROOK", "QUEEN", "KING"};
		ArrayList<ChessPiece> gpcs = null;
		for (int r = rval; r < 8; r++)
		{
			//System.out.println("INC r = " + r);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, cval, myvtps, ignorelist, addpcs);
			boolean loconiglist = logonigvtpdtalist[0];
			boolean pcatloconiglist = logonigvtpdtalist[1];
			boolean isvpctpeoniglist = logonigvtpdtalist[2];
			//System.out.println("loconiglist = " + loconiglist);
			//System.out.println("pcatloconiglist = " + pcatloconiglist);
			//System.out.println("isvpctpeoniglist = " + isvpctpeoniglist);
			if (loconiglist && !pcatloconiglist) continue;
			//else;//do nothing safe to proceed
			
			if (loconiglist)
			{
				if (pcatloconiglist);
				else
				{
					throw new IllegalStateException("WE ARE AT AN IGNORE LIST SPOT, BUT THERE IS NO PIECE THERE, " +
						"SO SHOULD NOT HAVE MADE IT HERE!");
				}
			}
			//else;//do nothing
			
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				
				//the piece is on our list of types, but it may not be able to attack the location
				//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
				ChessPiece cp = null;
				if (getNumItemsInList(addpcs) < 1);
				else cp = getPieceAt(r, cval, addpcs);
				//System.out.println("INIT cp = " + cp);
				if (cp == null) cp = getPieceAt(r, cval, getAllPiecesWithGameID(gid));
				//System.out.println("FINAL cp = " + cp);
				boolean addit = true;
				String[] rstps = {"KING"};
				addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, false);
				
				if (addit)
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					gpcs.add(cp);
				}
				//else;//do nothing
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				boolean locntempty = true;
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(r, cval, gid, addpcs)) locntempty = false;
					//else;//do nothing
				}
				//System.out.println("locntempty = " + locntempty);
				if (locntempty)
				{
					if (r == rval);
					else break;
				}
				//else;//do nothing
			}
		}
		for (int r = rval; ((r == 0 || 0 < r) && r < 8); r--)
		{
			//System.out.println("DEC r = " + r);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, cval, myvtps, ignorelist, addpcs);
			boolean loconiglist = logonigvtpdtalist[0];
			boolean pcatloconiglist = logonigvtpdtalist[1];
			boolean isvpctpeoniglist = logonigvtpdtalist[2];
			//System.out.println("loconiglist = " + loconiglist);
			//System.out.println("pcatloconiglist = " + pcatloconiglist);
			//System.out.println("isvpctpeoniglist = " + isvpctpeoniglist);
			if (loconiglist && !pcatloconiglist) continue;
			//else;//do nothing safe to proceed
			
			if (loconiglist)
			{
				if (pcatloconiglist);
				else
				{
					throw new IllegalStateException("WE ARE AT AN IGNORE LIST SPOT, BUT THERE IS NO PIECE THERE, " +
						"SO SHOULD NOT HAVE MADE IT HERE!");
				}
			}
			//else;//do nothing
			
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (r == rval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = null;
					if (getNumItemsInList(addpcs) < 1);
					else cp = getPieceAt(r, cval, addpcs);
					//System.out.println("INIT cp = " + cp);
					if (cp == null) cp = getPieceAt(r, cval, getAllPiecesWithGameID(gid));
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, false);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
					}
					//else;//do nothing
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				boolean locntempty = true;
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(r, cval, gid, addpcs)) locntempty = false;
					//else;//do nothing
				}
				//System.out.println("locntempty = " + locntempty);
				if (locntempty)
				{
					if (r == rval);
					else break;
				}
				//else;//do nothing
			}
		}
		for (int c = cval; c < 8; c++)
		{
			//System.out.println("INC c = " + c);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, c, myvtps, ignorelist, addpcs);
			boolean loconiglist = logonigvtpdtalist[0];
			boolean pcatloconiglist = logonigvtpdtalist[1];
			boolean isvpctpeoniglist = logonigvtpdtalist[2];
			//System.out.println("loconiglist = " + loconiglist);
			//System.out.println("pcatloconiglist = " + pcatloconiglist);
			//System.out.println("isvpctpeoniglist = " + isvpctpeoniglist);
			if (loconiglist && !pcatloconiglist) continue;
			//else;//do nothing safe to proceed
			
			if (loconiglist)
			{
				if (pcatloconiglist);
				else
				{
					throw new IllegalStateException("WE ARE AT AN IGNORE LIST SPOT, BUT THERE IS NO PIECE THERE, " +
						"SO SHOULD NOT HAVE MADE IT HERE!");
				}
			}
			//else;//do nothing
			
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (c == cval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = null;
					if (getNumItemsInList(addpcs) < 1);
					else cp = getPieceAt(rval, c, addpcs);
					//System.out.println("INIT cp = " + cp);
					if (cp == null) cp = getPieceAt(rval, c, getAllPiecesWithGameID(gid));
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
					}
					//else;//do nothing
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				boolean locntempty = true;
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(rval, c, gid, addpcs)) locntempty = false;
					//else;//do nothing
				}
				//System.out.println("locntempty = " + locntempty);
				if (locntempty)
				{
					if (c == cval);
					else break;
				}
				//else;//do nothing
			}
		}
		for (int c = cval; ((c == 0 || 0 < c) && c < 8); c--)
		{
			//System.out.println("DEC c = " + c);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, c, myvtps, ignorelist, addpcs);
			boolean loconiglist = logonigvtpdtalist[0];
			boolean pcatloconiglist = logonigvtpdtalist[1];
			boolean isvpctpeoniglist = logonigvtpdtalist[2];
			//System.out.println("loconiglist = " + loconiglist);
			//System.out.println("pcatloconiglist = " + pcatloconiglist);
			//System.out.println("isvpctpeoniglist = " + isvpctpeoniglist);
			if (loconiglist && !pcatloconiglist) continue;
			//else;//do nothing safe to proceed
			
			if (loconiglist)
			{
				if (pcatloconiglist);
				else
				{
					throw new IllegalStateException("WE ARE AT AN IGNORE LIST SPOT, BUT THERE IS NO PIECE THERE, " +
						"SO SHOULD NOT HAVE MADE IT HERE!");
				}
			}
			//else;//do nothing
			
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (c == cval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = null;
					if (getNumItemsInList(addpcs) < 1);
					else cp = getPieceAt(rval, c, addpcs);
					//System.out.println("INIT cp = " + cp);
					if (cp == null) cp = getPieceAt(rval, c, getAllPiecesWithGameID(gid));
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
					}
					//else;//do nothing
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				boolean locntempty = true;
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(rval, c, gid, addpcs)) locntempty = false;
					//else;//do nothing
				}
				//System.out.println("locntempty = " + locntempty);
				if (locntempty)
				{
					if (c == cval);
					else break;
				}
				//else;//do nothing
			}
			//System.out.println("OUTSIDE OF IF-ELSE STATEMENT");
		}
		//System.out.println("OUTSIDE OF FINAL FOR LOOP STATEMENT");
		return gpcs;
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int[] loc, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocationOnSameRowOrCol(loc[0], loc[1], gid, ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int[] loc, int gid, int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocationOnSameRowOrCol(loc[0], loc[1], gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int rval, int cval, int gid,
		int[][] ignorelist)
	{
		return getPiecesGuardingLocationOnSameRowOrCol(rval, cval, gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int rval, int cval, int gid)
	{
		return getPiecesGuardingLocationOnSameRowOrCol(rval, cval, gid, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocationOnSameRowOrCol(int[] loc, int gid)
	{
		return getPiecesGuardingLocationOnSameRowOrCol(loc, gid, null);
	}
	
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int rval, int cval, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		//System.out.println("INSIDE GET PIECES GUARDING LOCATION: " + getLocString(rval, cval));
		//System.out.println("gid = " + gid);
		//System.out.println("addpcs = " + addpcs);
		//if (ignorelist == null) System.out.println("ignorelist = null!");
		//else
		//{
		//	if (ignorelist.length < 1) System.out.println("ignorelist is empty!");
		//	else
		//	{
		//		for (int x = 0; x < ignorelist.length; x++)
		//		{
		//			for (int c = 0; c < ignorelist[x].length; c++)
		//			{
		//				System.out.println("ignorelist[" + x + "][" + c + "] = " + ignorelist[x][c]);
		//			}
		//		}
		//	}
		//}
		ArrayList<ChessPiece> rclocs = getPiecesGuardingLocationOnSameRowOrCol(rval, cval, gid, ignorelist, addpcs);
		//System.out.println("rclocs = " + rclocs);
		ArrayList<ChessPiece> dlocs = getPiecesGuardingLocationOnSameDiagnal(rval, cval, gid, ignorelist, addpcs);
		//System.out.println("dlocs = " + dlocs);
		ArrayList<ChessPiece> klocs = getPiecesGuardingLocationByAKnight(rval, cval, gid, ignorelist, addpcs);
		//System.out.println("THE LOC: " + getLocString(rval, cval));
		//System.out.println("rclocs = " + rclocs);
		//System.out.println("dlocs = " + dlocs);
		//System.out.println("klocs = " + klocs);
		
		ArrayList<ChessPiece> alocs = null;
		if (0 < getNumItemsInList(rclocs))
		{
			alocs = new ArrayList<ChessPiece>();
			for (int x = 0; x < rclocs.size(); x++) alocs.add(rclocs.get(x));
		}
		//else;//do nothing
		if (0 < getNumItemsInList(dlocs))
		{
			if (alocs == null) alocs = new ArrayList<ChessPiece>();
			//else;//do nothing
			
			for (int x = 0; x < dlocs.size(); x++)
			{
				boolean addit = true;
				for (int r = 0; r < alocs.size(); r++)
				{
					if (dlocs.get(x).getRow() == alocs.get(r).getRow() &&
						dlocs.get(x).getCol() == alocs.get(r).getCol())
					{
						addit = false;
						break;
					}
				}
				if (addit) alocs.add(dlocs.get(x));
			}
		}
		//else;//do nothing
		if (0 < getNumItemsInList(klocs))
		{
			if (alocs == null) alocs = new ArrayList<ChessPiece>();
			//else;//do nothing
			
			for (int x = 0; x < klocs.size(); x++)
			{
				boolean addit = true;
				for (int r = 0; r < alocs.size(); r++)
				{
					if (klocs.get(x).getRow() == alocs.get(r).getRow() &&
						klocs.get(x).getCol() == alocs.get(r).getCol())
					{
						addit = false;
						break;
					}
				}
				if (addit) alocs.add(klocs.get(x));
			}
		}
		//else;//do nothing
		return alocs;
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int[] loc, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocation(loc[0], loc[1], gid, ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int rval, int cval, int gid, int[][] ignorelist)
	{
		return getPiecesGuardingLocation(rval, cval, gid, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int rval, int cval, int gid)
	{
		return getPiecesGuardingLocation(rval, cval, gid, null);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int[] loc, int gid, int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getPiecesGuardingLocation(loc[0], loc[1], gid, ignorelist);
	}
	public static ArrayList<ChessPiece> getPiecesGuardingLocation(int[] loc, int gid)
	{
		return getPiecesGuardingLocation(loc, gid, null);
	}
	
	
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int rval, int cval, int gid, String clrval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (clrval == null) return null;
		else return filterListByColor(getPiecesGuardingLocation(rval, cval, gid, ignorelist, addpcs), clrval);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int[] loc, int gid, String clrval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getSidePiecesGuardingLocation(loc[0], loc[1], gid, clrval, ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int rval, int cval, int gid, String clrval,
		int[][] ignorelist)
	{
		return getSidePiecesGuardingLocation(rval, cval, gid, clrval, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int[] loc, int gid, String clrval, int[][] ignorelist)
	{
		return getSidePiecesGuardingLocation(loc, gid, clrval, ignorelist, null);	
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocationNoList(int rval, int cval, int gid, String clrval)
	{
		return getSidePiecesGuardingLocation(rval, cval, gid, clrval, null);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocationNoList(int[] loc, int gid, String clrval)
	{
		return getSidePiecesGuardingLocation(loc, gid, clrval, null);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int rval, int cval, int gid, int[][] ignorelist)
	{
		ChessPiece cp = getPieceAt(rval, cval, gid);
		if (cp == null) return null;
		else return getSidePiecesGuardingLocation(rval, cval, gid, cp.getColor(), ignorelist);
	}
	public static ArrayList<ChessPiece> getSidePiecesGuardingLocation(int[] loc, int gid, int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getSidePiecesGuardingLocation(loc[0], loc[1], gid, ignorelist);
	}
	
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocation(int rval, int cval, int gid, String clrval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return getSidePiecesGuardingLocation(rval, cval, gid, getOppositeColor(clrval), ignorelist, addpcs);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocation(int rval, int cval, int gid, String clrval,
		int[][] ignorelist)
	{
		return getEnemyPiecesGuardingLocation(rval, cval, gid, clrval, ignorelist, null);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocationNoList(int rval, int cval, int gid, String clrval)
	{
		return getEnemyPiecesGuardingLocation(rval, cval, gid, clrval, null);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocation(int[] loc, int gid, String clrval,
		int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getEnemyPiecesGuardingLocation(loc[0], loc[1], gid, clrval, ignorelist);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocationNoList(int[] loc, int gid, String clrval)
	{
		return getEnemyPiecesGuardingLocation(loc, gid, clrval, null);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocation(int rval, int cval, int gid, int[][] ignorelist)
	{
		ChessPiece cp = getPieceAt(rval, cval, gid);
		if (cp == null) return null;
		else return getSidePiecesGuardingLocation(rval, cval, gid, getOppositeColor(cp.getColor()), ignorelist);
	}
	public static ArrayList<ChessPiece> getEnemyPiecesGuardingLocation(int[] loc, int gid, int[][] ignorelist)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else return getEnemyPiecesGuardingLocation(loc[0], loc[1], gid, ignorelist);
	}
	
	
	//METHODS TO GENERATE THE NEW BOARD LIST FROM A LIST OF CHANGES TO THE OLD BOARD
	
	public static ArrayList<ChessPiece> combineBoardAndIgnoreLists(int[][] ignorelist, ArrayList<ChessPiece> boardlist)
	{
		if (boardlist == null || boardlist.size() < 1) return boardlist;
		if (ignorelist == null || ignorelist.length < 1) return boardlist;
		//both not empty
		ArrayList<ChessPiece> retlist = null;
		for (int x = 0; x < boardlist.size(); x++)
		{
			boolean addit = true;
			for (int r = 0; r < ignorelist.length; r++)
			{
				if (boardlist.get(x).getRow() == ignorelist[r][0] && boardlist.get(x).getCol() == ignorelist[r][1])
				{
					addit = false;
					break;
				}
				//else;//do nothing
			}
			if (addit)
			{
				if (retlist == null) retlist = new ArrayList<ChessPiece>();
				//else;//do nothing
				retlist.add(boardlist.get(x));
			}
			//else;//do nothing
		}
		return retlist;
	}
	public static ArrayList<ChessPiece> combineBoardAndIgnoreLists(int[][] ignorelist, int gid)
	{
		return combineBoardAndIgnoreLists(ignorelist, getAllPiecesWithGameID(gid));
	}
	
	public static ArrayList<ChessPiece> combineBoardAddAndIgnoreLists(int[][] ignorelist, ArrayList<ChessPiece> addpcs,
		ArrayList<ChessPiece> boardlist)
	{
		//we prioritize the: addlist > ignorelist > boardlist
		//initially start with the add list
		//then if on the add list and ignore list, ignore what is already accounted for, keep what needs to be kept
		//then determine what we can keep on the last one and that is it...
		//then return result.
		ArrayList<ChessPiece> retlist = null;
		if (getNumItemsInList(addpcs) < 1);
		else
		{
			retlist = new ArrayList<ChessPiece>();
			for (int x = 0; x < addpcs.size(); x++) retlist.add(addpcs.get(x));
		}
		//System.out.println("NEW retlist = " + retlist);
		
		if (getNumItemsInList(addpcs) < 1) retlist = combineBoardAndIgnoreLists(ignorelist, boardlist);
		else
		{
			//System.out.println("RETLIST IS NOT EMPTY!");
			//generate the new ignore list
			//then get the result and add all of that to the retlist
			if (ignorelist == null || ignorelist.length < 1)
			{
				//System.out.println("IGNORELIST IS EMPTY OR NULL!");
				//need to combine board and add list here
				for (int x = 0; x < boardlist.size(); x++)
				{
					boolean addit = true;
					if (retlist == null) retlist = new ArrayList<ChessPiece>();
					//else;//do nothing
					for (int r = 0; r < retlist.size(); r++)
					{
						if (boardlist.get(x).getRow() == retlist.get(r).getRow() &&
							boardlist.get(x).getCol() == retlist.get(r).getCol())
						{
							addit = false;
							break;
						}
						//else;//do nothing
					}
					if (addit) retlist.add(boardlist.get(x));
				}
			}
			else
			{
				//System.out.println("IGNORELIST IS NOT EMPTY!");
				boolean[] keeploc = new boolean[ignorelist.length];
				int numrm = 0;
				for (int x = 0; x < ignorelist.length; x++)
				{
					//this gets the ignorelist loc
					//now get the other loc to compare it to
					keeploc[x] = true;
					for (int i = 0; i < retlist.size(); i++)
					{
						if (retlist.get(i).getRow() == ignorelist[x][0] && retlist.get(i).getCol() == ignorelist[x][1])
						{
							//do not keep this on the ignore list
							keeploc[x] = false;
							numrm++;
							break;
						}
						//else;//do nothing
					}
				}
				//System.out.println("numrm = " + numrm);
				
				ArrayList<ChessPiece> bdiglist = null;
				if (numrm < 0) throw new IllegalStateException("numrm must be at least zero!");
				else if (numrm < 1) bdiglist = combineBoardAndIgnoreLists(ignorelist, boardlist);
				else
				{
					int[][] nwiglist = new int[ignorelist.length - numrm][2];
					int nwigli = 0;
					for (int x = 0; x < ignorelist.length; x++)
					{
						if (nwigli < nwiglist.length);
						else break;
						
						if (keeploc[x])
						{
							nwiglist[nwigli][0] = ignorelist[x][0];
							nwiglist[nwigli][1] = ignorelist[x][1];
							nwigli++;
						}
						//else;//do nothing
					}
					bdiglist = combineBoardAndIgnoreLists(nwiglist, boardlist);
				}
				for (int x = 0; x < bdiglist.size(); x++) retlist.add(bdiglist.get(x));
			}
		}
		//System.out.println("FINAL retlist = " + retlist);
		return retlist;
	}
	public static ArrayList<ChessPiece> combineBoardAddAndIgnoreLists(int[][] ignorelist, ArrayList<ChessPiece> addpcs,
		int gid)
	{
		return combineBoardAddAndIgnoreLists(ignorelist, addpcs, getAllPiecesWithGameID(gid));
	}
	
	//merges the two lists
	public static int[][] combineIgnoreLists(int[][] ilista, int[][] ilistb)
	{
		if (ilista == null || ilista.length < 1) return ilistb;
		else if (ilistb == null || ilistb.length < 1) return ilista;
		else
		{
			int[][] midreslist = new int[ilista.length + ilistb.length][2];
			for (int x = 0; x < midreslist.length; x++)
			{
				midreslist[x][0] = -1;
				midreslist[x][1] = -1;
			}
			int midreslisti = 0;
			for (int x = 0; x < ilista.length; x++)
			{
				midreslist[midreslisti][0] = ilista[x][0];
				midreslist[midreslisti][1] = ilista[x][1];
				midreslisti++;
			}
			for (int x = 0; x < ilistb.length; x++)
			{
				if (isLocOnListOfLocs(ilistb[x], midreslist));//do nothing do not add it
				else
				{
					midreslist[midreslisti][0] = ilistb[x][0];
					midreslist[midreslisti][1] = ilistb[x][1];
					midreslisti++;
				}
			}
			int[][] reslist = new int[midreslisti][2];
			for (int x = 0; x < midreslisti; x++)
			{
				reslist[x][0] = midreslist[x][0];
				reslist[x][1] = midreslist[x][1];
			}
			return reslist;
		}
	}
	
	//CHECK METHODS
	
	
	//can I be directly attacked by the opposing side?
	public boolean inCheck(int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		//can I be directly attacked by the opposing side?
		ArrayList<ChessPiece> epcs = getEnemyPiecesGuardingLocation(getRow(), getCol(), getGameID(), getColor(),
			ignorelist, addpcs);
		if (getNumItemsInList(epcs) < 1) return false;
		else return true;
	}
	public boolean inCheck()
	{
		return inCheck(null, null);
	}
	
	public boolean isMySideInCheck(int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		//get my king
		//then ask can I be directly attacked by the opposing side?
		//if yes you are in check
		return getMySideKing().inCheck(ignorelist, addpcs);
	}
	public boolean isMySideInCheck()
	{
		return isMySideInCheck(null, null);
	}
	
	
	//NOT DONE YET....
	
	public static boolean canAddThisMoveToLoc(int sr, int sc, int nr, int nc, String myclr, String mytpval,
		int[][] oignorelist, ArrayList<ChessPiece> oaddpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		ChessPiece cp = getPieceAt(nr, nc, combineBoardAddAndIgnoreLists(oignorelist, oaddpcs, gid));
		boolean addit = true;
		if (cp == null);
		else
		{
			if (cp.getColor().equals(myclr)) addit = false;
			//else;//do nothing
		}
		if (addit)
		{
			//need to know if ignoring piece at sr and sc and putting a castle/queen piece at this location
			//puts my king in check
			//if it puts my king in check -> do not add it
			//else add it
			
			int[][] ilista = new int[1][2];
			ilista[0][0] = sr;
			ilista[0][1] = sc;
			int[][] ignorelist = combineIgnoreLists(ilista, oignorelist);
			
			ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
			addpcs.add(new ChessPiece(mytpval, myclr, nr, nc, gid, false));
			if (getNumItemsInList(oaddpcs) < 1);
			else
			{
				for (int x = 0; x < oaddpcs.size(); x++)
				{
					boolean addpctoit = true;
					for (int c = 0; c < addpcs.size(); c++)
					{
						if (oaddpcs.get(x).getRow() == addpcs.get(c).getRow() &&
							oaddpcs.get(x).getCol() == addpcs.get(c).getCol())
						{
							addpctoit = false;
							break;
						}
						//else;//do nothing
					}
					if (addpctoit) addpcs.add(oaddpcs.get(x));
					//else;//do nothing
				}
			}
			ChessPiece mkg = getCurrentSideKing(myclr, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			if (mkg.inCheck(ignorelist, addpcs)) addit = false;
			//else;//do nothing
		}
		//else;//do nothing
		return addit;
	}
	
	public static int[][] getBishopCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		int[][] keeplist = new int[16][2];
		for (int x = 0; x < keeplist.length; x++)
		{
			keeplist[x][0] = -1;
			keeplist[x][1] = -1;
		}
		keeplist[0][0] = sr;
		keeplist[0][1] = sc;
		int kli = 1;
		
		for (int x = 0; x < 4; x++)
		{
			//System.out.println("x = " + x);
			
			int r = sr;
			int c = sc;
			while (isvalidrorc(r) && isvalidrorc(c))
			{
				if (canAddThisMoveToLoc(sr, sc, r, c, myclr, "BISHOP", ignorelist, addpcs, gid))
				{
					keeplist[kli][0] = r;
					keeplist[kli][1] = c;
					kli++;
				}
				//else;//do nothing
				ChessPiece cp = getPieceAt(r, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
				if (cp == null);
				else break;
				
				//increment the variables
				//System.out.println("x = " + x);
				if (x == 0)
				{
					//go towards bottom right
					//System.out.println("TOWARDS BOTTOM RIGHT!");
					r++;
					c++;
				}
				else if (x == 1)
				{
					//go towards top left
					//System.out.println("TOWARDS TOP LEFT!");
					r--;
					c--;
				}
				else if (x == 2)
				{
					//go towards top right
					//System.out.println("TOWARDS TOP RIGHT!");
					r--;
					c++;
				}
				else if (x == 3)
				{
					//go towards bottom left
					//System.out.println("TOWARDS BOTTOM LEFT!");
					r++;
					c--;
				}
				else throw new IllegalStateException("ILLEGAL VALUE FOUND AND USED HERE FOR INDEX X!");
			}//end of while loop
		}//end of x for loop
		//copy keeplist to rlist
		int[][] rlist = new int[kli][2];
		for (int x = 0; x < kli; x++)
		{
			rlist[x][0] = keeplist[x][0];
			rlist[x][1] = keeplist[x][1];
		}
		return rlist;
	}
	//NOTE: this does not take into account castling
	public static int[][] getCastleCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//we do not include sr and sc only (WE ASSUME THERE IS A PIECE THERE THAT IS A CASTLE OR A QUEEN)
		//moving on rows or columns checking to see if the location is empty according to all pieces list
		//if the location is empty, add it to keep list
		//if the location is not empty:
		//-check to see if we can kill it:
		//--if we can kill it, add it;
		//--if not, do not add it;
		//-but done
		//we must make sure that the location lets us not be in check or results in our side not in check
		//
		//column stays the same
		//at most there will be 8 locations in any row or column so 8 different positions
		//the castle therefore will have at most 16 possible moves (if allowed to stay at its current position)
		int[][] keeplist = new int[16][2];
		for (int x = 0; x < keeplist.length; x++)
		{
			keeplist[x][0] = -1;
			keeplist[x][1] = -1;
		}
		keeplist[0][0] = sr;
		keeplist[0][1] = sc;
		int kli = 1;
		for (int r = sr; r < 8; r++)
		{
			if (r == sr) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, r, sc, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				keeplist[kli][0] = r;
				keeplist[kli][1] = sc;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(r, sc, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			if (cp == null);
			else break;
		}
		for (int r = sr; (0 < r || r == 0 && r < 8); r--)
		{
			if (r == sr) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, r, sc, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				keeplist[kli][0] = r;
				keeplist[kli][1] = sc;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(r, sc, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			if (cp == null);
			else break;
		}
		//row stays the same
		for (int c = sc; c < 8; c++)
		{
			if (c == sc) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, sr, c, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				keeplist[kli][0] = sr;
				keeplist[kli][1] = c;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(sr, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			if (cp == null);
			else break;
		}
		for (int c = sc; (0 < c || c == 0 && c < 8); c--)
		{
			if (c == sc) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, sr, c, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				keeplist[kli][0] = sr;
				keeplist[kli][1] = c;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(sr, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			if (cp == null);
			else break;
		}
		//copy keeplist to rlist
		int[][] rlist = new int[kli][2];
		for (int x = 0; x < kli; x++)
		{
			rlist[x][0] = keeplist[x][0];
			rlist[x][1] = keeplist[x][1];
		}
		return rlist;
	}
	public static int[][] getQueenCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//combines the two above
		int[][] bmlocs = getBishopCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
		int[][] cmlocs = getCastleCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
		if (bmlocs == null || bmlocs.length < 1) return cmlocs;
		else if (cmlocs == null || cmlocs.length < 1) return bmlocs;
		else
		{
			//both are non null;
			int[][] reslocs = new int[bmlocs.length + cmlocs.length][2];
			int resi = 0;
			for (int r = 0; r < bmlocs.length; r++)
			{
				reslocs[resi] = bmlocs[r];
				resi++;
			}
			for (int r = 0; r < cmlocs.length; r++)
			{
				if (isLocOnListOfLocs(cmlocs[r], reslocs));
				else
				{
					reslocs[resi] = cmlocs[r];
					resi++;
				}
			}
			int[][] myretlist = new int[resi][2];
			for (int x = 0; x < resi; x++)
			{
				myretlist[x][0] = reslocs[x][0];
				myretlist[x][1] = reslocs[x][1];
			}
			return myretlist;
		}
	}
	//NOTE: this does not take into account pawning
	public static int[][] getPawnCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//can only move forward one or two spaces on the first turn otherwise forward one only
		//exception is attacking or pawning
		
		//
		throw new IllegalStateException("NOT DONE YET WITH PAWN CAN MOVE TO LOCS 5-3-2024 12:24 AM MST");
	}
	public static int[][] getKnightCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		int[][] pktlocs = getAllPossibleKnightMoveToLocs(sr, sc);
		//if (pktlocs == null) System.out.println("pktlocs = null");
    	//else if (pktlocs.length < 1) System.out.println("pktlocs is empty!");
    	//else
    	//{
    	//	System.out.println("pktlocs.length = " + pktlocs.length);
	    //	for (int x = 0; x < pktlocs.length; x++)
	    //	{
	    //		System.out.println(getLocString(pktlocs[x][0], pktlocs[x][1]));
	    //		//System.out.println(getLocString(pktlocs[x][0], pktlocs[x][1]) + ": " +
	    //		//	convertRowColToStringLoc(pktlocs[x]));
	    //	}
    	//}
    	//System.out.println("STARTING LOCATION: " + getLocString(sr, sc) + ": " + convertRowColToStringLoc(sr, sc));
    	
    	if (pktlocs == null || pktlocs.length < 1)
    	{
    		int[][] rlist = new int[1][2];
    		rlist[0][0] = sr;
    		rlist[0][1] = sc;
    		return rlist;
    	}
		else
		{
			boolean[] isvloc = new boolean[pktlocs.length];
			int numv = 0;
			for (int x = 0; x < pktlocs.length; x++)
	    	{
	    		isvloc[x] = (isvalidrorc(pktlocs[x][0]) && isvalidrorc(pktlocs[x][1]));
	    		if (isvloc[x])
	    		{
	    			//the loc is valid, but now see if moving there moves our king to check or
	    			//see if we can even move there in the first place
	    			if (canAddThisMoveToLoc(sr, sc, pktlocs[x][0], pktlocs[x][1], myclr, "KNIGHT", ignorelist, addpcs, gid))
					{
						isvloc[x] = true;
					}
					else isvloc[x] = false;
		    	}
		    	//else;//do nothing
	    		if (isvloc[x]) numv++;
	    	}
	    	int[][] vpktlocs = new int[numv + 1][2];
	    	vpktlocs[0][0] = sr;
	    	vpktlocs[0][1] = sc;
	    	int vpki = 1;
	    	for (int x = 0; x < pktlocs.length; x++)
	    	{
	    		if (isvloc[x])
	    		{
	    			vpktlocs[vpki][0] = pktlocs[x][0];
	    			vpktlocs[vpki][1] = pktlocs[x][1];
	    			//System.out.println("VALID LOC " + getLocString(vpktlocs[vpki][0], vpktlocs[vpki][1]) + ": " +
	    			//	convertRowColToStringLoc(vpktlocs[vpki]));
	    			vpki++;
	    		}
	    		//else;//do nothing
	    	}
	    	return vpktlocs;
		}
	}
	//NOTE: this does not take into account castling
	public static int[][] getKingCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//can move one square in any direction
		//exception is castling
		
		//rdiff and cdiff must be at most 1 at minimum 0 zero
		int[][] keeplist = new int[9][2];
		int kli = 0;
		for (int x = 0; x < keeplist.length; x++)
		{
			keeplist[x][0] = -1;
			keeplist[x][1] = -1;
		}
		for (int r = sr - 1; ((0 < r || r == 0) && r < 8) && r < sr + 2; r++)
		{
			for (int c = sc - 1; ((0 < c || c == 0) && c < 8) && c < sc + 2; c++)
			{
				if ((r == sr && c == sc) || canAddThisMoveToLoc(sr, sc, r, c, myclr, "KING", ignorelist, addpcs, gid))
				{
					keeplist[kli][0] = r;
					keeplist[kli][1] = sc;
					kli++;
				}
				//else;//do nothing
				//different than the others because all locations are one away from the spot so just loop through them
			}
		}
		//copy keeplist to rlist
		int[][] rlist = new int[kli][2];
		for (int x = 0; x < kli; x++)
		{
			rlist[x][0] = keeplist[x][0];
			rlist[x][1] = keeplist[x][1];
		}
		return rlist;
	}
	public static int[][] getPieceCanMoveToLocs(int sr, int sc, String myclr, String mytpval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (mytpval == null) throw new IllegalStateException("mytpval must not be null!");
		else
		{
			if (mytpval.equals("BISHOP")) return getBishopCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else if (mytpval.equals("CASTLE") || mytpval.equals("ROOK"))
			{
				return getCastleCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			}
			else if (mytpval.equals("QUEEN")) return getQueenCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else if (mytpval.equals("PAWN")) return getPawnCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else if (mytpval.equals("KING")) return getKingCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else if (mytpval.equals("KNIGHT")) return getKnightCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else throw new IllegalStateException("illegal value found and used here for mytpval (" + mytpval + ")!");
		}
	}
	
	
	//NOT DONE YET...
	public boolean canMoveTo(int rval, int cval)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else return false;
		//use current location, piece type, if side is in check or not, and opposing piece locations
		//to determine where I can move or if I can move at all.
		
		//boolean mysideincheck = isMySideInCheck();
		
		if (getType().equals("BISHOP"))
		{
			//on diagnals only
		}
		else if (getType().equals("CASTLE") || getType().equals("ROOK"))
		{
			//on same row or col only
			//can castle if the other pieces between the castle and the king are not there and if not in check
			//and if neither castle nor king have moved
		}
		else if (getType().equals("QUEEN"))
		{
			//diagnals and on same row or same col
		}
		else if (getType().equals("KNIGHT"))
		{
			//has at most 8 possible moves
			//up or down 3 right or left 1
			//up or down 1 right or left 3
			//--*-*--
			//-*---*-
			//---x---
			//-*---*-
			//--*-*--
		}
		else if (getType().equals("PAWN"))
		{
			//can only move forward or diagnal one space to attack
			//if it is the first move, can move forward two spaces
			//in passing or EN PASSANT is a form of attack
			//you can only pawn a pawn that has made its first move
		}
		else if (getType().equals("KING"))
		{
			//1 in any direction provided move does not put king in check
			//if in check and king cannot move without being put into check, see if another piece can block it
			//if the king cannot get out of check -> checkmate other side wins.
			//if the king cannot move, but must move -> stalemate tie.
		}
		
		return true;
	}
	public boolean canMoveToLoc(int rval, int cval)
	{
		return canMoveTo(rval, cval);
	}
	public boolean canMoveToLoc(int[] nloc)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		else return canMoveToLoc(nloc[0], nloc[1]);
	}
	
	
	//SERVER METHODS
	
	public static String genMoveToCommand(String clr, String tp, int crval, int ccval, int nrval, int ncval)
	{
		String cmd = "" + clr + " " + tp + " at: " + getLocString(crval, ccval) + " to: " + getLocString(nrval, ncval);
		System.out.println("cmd = " + cmd);
		return cmd;
	}
	public static String genMoveToCommand(String clr, String tp, int[] cloc, int[] nloc)
	{
		if (cloc == null || cloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the current chess piece location!");
		}
		//else;//do nothing
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		//else;//do nothing
		
		return genMoveToCommand(clr, tp, cloc[0], cloc[1], nloc[0], nloc[1]);
	}
	public static String genMoveToCommand(ChessPiece cp, int nrval, int ncval)
	{
		if (cp == null)
		{
			throw new IllegalStateException("You need to provide the current chess piece location and the new location!");
		}
		else return genMoveToCommand(cp.getColor(), cp.getType(), cp.getRow(), cp.getCol(), nrval, ncval);
	}
	public static String genMoveToCommand(ChessPiece cp, int[] nloc)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		//else;//do nothing
		
		if (cp == null)
		{
			throw new IllegalStateException("You need to provide the current chess piece location and the new location!");
		}
		else return genMoveToCommand(cp.getColor(), cp.getType(), cp.getRow(), cp.getCol(), nloc[0], nloc[1]);
	}
	public String genMoveToCommand(int[] nloc)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		else return genMoveToCommand(this, nloc);
	}
	public String genMoveToCommand(int nrval, int ncval)
	{
		return genMoveToCommand(this, nrval, ncval);
	}
	
	
	//PAWN SPECIAL METHODS
	
	public boolean canPawnBePromoted()
	{
		if (getType().equals("PAWN"))
		{
			if ((getRow() == 0 && getColor().equals("WHITE")) ||
				(getRow() == 7 && getColor().equals("BLACK")))
			{
				return true;
			}
			//else System.out.println("THIS PAWN HAS NOT REACHED THE CORRECT ROW FOR ITS COLOR!");
		}
		//else System.out.println("THIS PIECE MUST BE A PAWN!");
		return false;
	}
	public static boolean canPawnForSideBePromoted(String clrval, ArrayList<ChessPiece> allpcs)
	{
		ArrayList<ChessPiece> pwnsclr = getAllPawnsOfColor(clrval, allpcs);
		if (getNumItemsInList(pwnsclr) < 1);
		else
		{
			for (int x = 0; x < pwnsclr.size(); x++)
			{
				if (pwnsclr.get(x).canPawnBePromoted()) return true;
			}
		}
		return false;
	}
	public static boolean canPawnForSideBePromoted(String clrval, int gid)
	{
		return canPawnForSideBePromoted(clrval, getAllPiecesWithGameID(gid));
	}
	
	public void promotePawnTo(String nwtype)
	{
		if (canPawnBePromoted())
		{
			if (nwtype.equals("PAWN") || nwtype.equals("KING"))
			{
				throw new IllegalStateException("CANNOT PROMOTE A PAWN TO A PAWN OR A KING!");
			}
			else setType(nwtype);
		}
		else throw new IllegalStateException("CANNOT PROMOTE THE PAWN!");
	}
	
	
	//NOT NECESSARILY DONE YET...
	//NEED A WAY TO DETECT IF THIS IS THE IMMEDIATE NEXT MOVE
	public boolean canPawnLeftOrRight(boolean useleft, ArrayList<ChessPiece> allpcs)
	{
		//if no pawns for one side -> false
		ArrayList<ChessPiece> wpns = getAllPawnsOfColor("WHITE", allpcs);
		ArrayList<ChessPiece> bpns = getAllPawnsOfColor("BLACK", allpcs);
		if (getNumItemsInList(wpns) < 1 || getNumItemsInList(bpns) < 1)
		{
			System.out.println("ONE SIDE HAS NO PAWNS! THERE MUST BE AT LEAST ONE PAWN ON EACH SIDE NEAR " +
				"EACH OTHER TO BE ABLE TO PAWN!");
			return false;
		}
		//else;//do nothing
		
		if (getType().equals("PAWN"))
		{
			//THIS IS WHEN AN OPPOSING PAWN CAPTURES A PAWN THAT MADE ITS FIRST MOVE
			//row 0: ----- ---- --- ---
			//row 1: --p-- -p-- -p- -p-
			//row 2: --*-- xnx- -n- x-x
			//row 3: -xnx- ---- x-x -n-
			//        YES   NO   NO  NO IS PAWNING?
			//where n is next location of the pawn that made its first move
			//where * is next location of the pawns at x on the left part of the board
			//where p is the starting location of the pawn that will make its first move
			//p on the right is also where the pawning pawn will end up so there is a * there too
			
			//the this ChessPiece refers to the pawn at position x
			//Pawn may not be at position x
			
			if (((getRow() == 3) && getColor().equals("WHITE")) ||
				((getRow() == 4) && getColor().equals("BLACK")))
			{
				//we are on the row so it might be an option
			}
			else
			{
				//System.out.println("OUR SIDE PIECE IS NOT ON THE APPROPRIATE ROW TO BE ABLE TO PAWN!");
				return false;
			}
			
			int lc = -1;
			if (useleft) lc = getCol() - 1;
			else lc = getCol() + 1;
			if (isvalidrorc(lc));
			else
			{
				//System.out.println("THE LOCATION " + getLocString(getRow(), lc) + " HAS AN INVALID COLUMN!");
				return false;
			}
			
			ChessPiece ep = getPieceAt(getRow(), lc, allpcs);
			if (ep == null)
			{
				System.out.println("THE LOCATION " + getLocString(getRow(), lc) + " IS EMPTY!");
				return false;
			}
			else
			{
				//System.out.println(ep);
				//System.out.println(ep.movecount);
				if (ep.getType().equals("PAWN"));
				else
				{
					System.out.println("THIS IS NOT A PAWN!");
					return false;
				}
				if (ep.getColor().equals(getColor()))
				{
					System.out.println("THIS IS YOUR PAWN!");
					return false;
				}
				//else;//do nothing this is an enemy pawn
				if (ep.movecount == 1);
				else
				{
					System.out.println("THIS IS NOT THE FIRST MOVE OF THE ENEMY PAWN!");
					return false;
				}
				
				//need to know if killing the pawn puts our king in check
				//if it does -> return false;
				//if it does not -> return true;
				//if our king is in check does killing the pawn get us out of check
				//if it does -> return true;
				//if it does not -> return false;
				
				//if we pawn left, and are white then getRow() is 3 and new row is 2, new col is getCol() - 1
				//if we pawn left, and are black then getRow() is 4 and new row is 5, new col is getCol() - 1
				//if we pawn right, and are white then getRow() is 3 and new row is 2, new col is getCol() + 1
				//if we pawn right, and are black then getRow() is 4 and new row is 5, new col is getCol() + 1
				//we ignore the enemy pawn entirely, we also ignore us, we add us at our new location
				//then we ask if this puts our king into check
				//we need to know our kings location
				ChessPiece mkg = getCurrentSideKing(getColor(), getGameID());
				
				int[][] ignorelist = new int[2][2];
				ignorelist[0][0] = getRow();
				ignorelist[0][1] = getCol();
				ignorelist[1][0] = getRow();
				ignorelist[1][1] = lc;
				
				int nr = -1;
				if (getColor().equals("WHITE")) nr = 2;
				else if (getColor().equals("BLACK")) nr = 5;
				else throw new IllegalStateException("PIECE FOUND WITH AN ILLEGAL COLOR FOUND AND USED HERE!");
				int nc = -1;
				if (useleft) nc = getCol() - 1;
				else nc = getCol() + 1;
				
				ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
				addpcs.add(new ChessPiece("PAWN", getColor(), nr, nc, mkg.getGameID(), false));
				if (mkg.inCheck(ignorelist, addpcs)) return false;
				else return true;
			}
		}
		else
		{
			System.out.println("ONLY PAWNS CAN PAWN!");
			return false;
		}
	}
	public boolean canPawnLeftOrRight(boolean useleft)
	{
		return canPawnLeftOrRight(useleft, getAllPiecesWithGameID(getGameID()));
	}
	public boolean canPawnLeft(ArrayList<ChessPiece> allpcs)
	{
		return canPawnLeftOrRight(true, allpcs);
	}
	public boolean canPawnLeft()
	{
		return canPawnLeft(getAllPiecesWithGameID(getGameID()));
	}
	public boolean canPawnRight(ArrayList<ChessPiece> allpcs)
	{
		return canPawnLeftOrRight(false, allpcs);
	}
	public boolean canPawnRight()
	{
		return canPawnRight(getAllPiecesWithGameID(getGameID()));
	}
	public boolean canPawn(ArrayList<ChessPiece> allpcs)
	{
		return (canPawnLeft(allpcs) || canPawnRight(allpcs));
	}
	public boolean canPawn()
	{
		return canPawn(getAllPiecesWithGameID(getGameID()));
	}
	public static boolean canSidePawn(String clrval, ArrayList<ChessPiece> allpcs)
	{
		//get all the pawns for our color
		//then call the canPawnLeft() or canPawnRight()
		//if true, then return true
		//if none are true, or no pawns return false
		
		ArrayList<ChessPiece> pns = getAllPawnsOfColor(clrval, allpcs);
		//System.out.println("pns = " + pns);
		if (getNumItemsInList(pns) < 1)
		{
			System.out.println("THIS SIDE HAS NO PAWNS! THERE MUST BE AT LEAST ONE PAWN ON EACH SIDE NEAR " +
				"EACH OTHER TO BE ABLE TO PAWN!");
			return false;
		}
		else
		{
			for (int x = 0; x < pns.size(); x++)
			{
				if (pns.get(x).canPawn(allpcs)) return true;
			}
			
			System.out.println("NO PAWN CAN PAWN ON THIS SIDE!");
			return false;
		}
	}
	public static boolean canSidePawn(String clrval, int gid)
	{
		return canSidePawn(clrval, getAllPiecesWithGameID(gid));
	}
	
	//NEED A METHOD TO PAWN A PAWN...
	
	//CASTLING METHODS
	
	public static boolean canSideCastleLeftOrRight(boolean useleft, String clrval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (itemIsOnGivenList(clrval, validColors));
		else throw new IllegalStateException("ILLEGAL COLOR (" + clrval + ") FOUND AND USED HERE!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		
		ChessPiece mkg = getCurrentSideKing(clrval, allpcs);
		if (mkg.inCheck(ignorelist, addpcs))
		{
			System.out.println("YOU CANNOT CASTLE OUT OF CHECK!");
			return false;
		}
		else
		{
			//if the move puts the king into check, no!
			//the squares must be empty
			//but the king cannot cross over a square that is attacked.
			//is first move for castle and king
			
			if (mkg.isfirstmove);
			else
			{
				System.out.println("THIS MUST BE THE FIRST MOVE FOR THE KING!");
				return false;
			}
			if (mkg.getCol() == 4 && ((mkg.getColor().equals("WHITE") && mkg.getRow() == 7) ||
				(mkg.getColor().equals("BLACK") && mkg.getRow() == 0)))
			{
				//the king is at its starting location
			}
			else
			{
				System.out.println("THE KING MUST BE AT ITS STARTING LOCATION!");
				return false;
			}
			
			int mccol = -1;
			if (useleft) mccol = 0;
			else mccol = 7;
			int mcrw = -1;
			if (mkg.getColor().equals("WHITE")) mcrw = 7;
			else mcrw = 0;
			ChessPiece mc = getPieceAt(mcrw, mccol, allpcs);
			if (mc == null) return false;
			else
			{
				if (mc.getType().equals("CASTLE") || mc.getType().equals("ROOK"))
				{
					if (mc.isfirstmove);
					else
					{
						System.out.println("THIS MUST BE THE FIRST MOVE FOR THE CASTLE!");
						return false;
					}
				}
				else
				{
					System.out.println("THERE MUST BE A CASTLE AT ITS STARTING LOCATION!");
					return false;
				}
			}
			
			//verify that the squares between the castle and the king are empty
			int sccol = -1;
			int cmx = -1;
			//king on col 4
			if (useleft)
			{
				//castle on col 0
				sccol = mccol;
				cmx = 4;
			}
			else
			{
				//castle on col 7
				sccol = 4;
				cmx = mccol;
			}
			for (int c = sccol + 1; c < cmx; c++)
			{
				if (getPieceAt(mcrw, c, allpcs) == null);
				else
				{
					System.out.println("THE SQUARES ARE NOT EMPTY!");
					return false;
				}
			}
			
			
			//need to know if there are any enemy pieces attacking the locations
			for (int c = sccol + 1; c < cmx; c++)
			{
				ArrayList<ChessPiece> epcs = getEnemyPiecesGuardingLocation(mcrw, c, mkg.getGameID(), mkg.getColor(),
					ignorelist, addpcs);
				if (getNumItemsInList(epcs) < 1);
				else
				{
					System.out.println("THERE IS AT LEAST ONE ENEMY PIECE ABLE TO ATTACK ONE OF THESE LOCATIONS DIRECTLY!");
					return false;
				}
			}
			
			//the king goes over two in either direction
			//and the castle goes next to the king, but on the other side
			
			//for left castle:
			//king ends at: (7, 2)
			//castle ends at (7, 2 + 1)
			//
			//for right castle:
			//king ends at: (7, 6)
			//castle ends at: (7, 6 - 1)
			
			return true;
		}
	}
	public static boolean canSideCastleLeftOrRight(boolean useleft, String clrval, int gid)
	{
		return canSideCastleLeftOrRight(useleft, clrval, null, null, gid);
	}
	public static boolean canSideCastleLeft(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return canSideCastleLeftOrRight(true, clrval, ignorelist, addpcs, gid);
	}
	public static boolean canSideCastleLeft(String clrval, int gid)
	{
		return canSideCastleLeftOrRight(true, clrval, gid);
	}
	public static boolean canSideCastleRight(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return canSideCastleLeftOrRight(false, clrval, ignorelist, addpcs, gid);
	}
	public static boolean canSideCastleRight(String clrval, int gid)
	{
		return canSideCastleLeftOrRight(false, clrval, gid);
	}
	public static boolean canSideCastle(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return (canSideCastleLeft(clrval, ignorelist, addpcs, gid) || canSideCastleRight(clrval, ignorelist, addpcs, gid));
	}
	public static boolean canSideCastle(String clrval, int gid)
	{
		return (canSideCastleLeft(clrval, gid) || canSideCastleRight(clrval, gid));
	}
	public boolean canCastleLeftOrRight(boolean useleft)
	{
		if (getType().equals("CASTLE") || getType().equals("ROOK") || getType().equals("KING"));
		else
		{
			System.out.println("YOU MUST BE A CASTLE OR A KING TO CASTLE!");
			return false;
		}
		
		return canSideCastleLeftOrRight(useleft, getColor(), getGameID());
	}
	public boolean canCastleLeft()
	{
		return canCastleLeftOrRight(true);
	}
	public boolean canCastleRight()
	{
		return canCastleLeftOrRight(false);
	}
	public boolean canCastle()
	{
		return (canCastleLeft() || canCastleRight());
	}
	
	//NEED A METHOD TO CASTLE THE CASTLE AND THE KING IN QUESTION...
	
	public static int[] getLeftOrRightCastleSideNewCastleOrKingLoc(boolean useleft, boolean usekg, String clrval, int gid)
	{
		int[] myretarr = {-1, -1};
		if (canSideCastleLeftOrRight(useleft, clrval, gid))
		{
			int cdiff = 0;
			int kdiff = 0;
			if (useleft)
			{
				cdiff = 1;
				kdiff = -2;
			}
			else
			{
				cdiff = -1;
				kdiff = 2;
			}
			myretarr[0] = getCurrentSideKing(clrval, gid).getRow();
			myretarr[1] = 4 + kdiff + cdiff;
		}
		//else;//do nothing
		return myretarr;
	}
	public static int[] getRightCastleSideNewKingLoc(String clrval, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, true, clrval, gid);
	}
	public static int[] getRightCastleSideNewCastleLoc(String clrval, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, false, clrval, gid);
	}
	public static int[] getLeftCastleSideNewKingLoc(String clrval, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, true, clrval, gid);
	}
	public static int[] getLeftCastleSideNewCastleLoc(String clrval, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, false, clrval, gid);
	}
	//non-static version convenience methods
	public int[] getLeftOrRightCastleNewCastleOrKingLoc(boolean useleft, boolean usekg)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, usekg, getColor(), getGameID());
	}
	public int[] getRightCastleNewKingLoc()
	{
		return getRightCastleSideNewKingLoc(getColor(), getGameID());
	}
	public int[] getRightCastleNewCastleLoc()
	{
		return getRightCastleSideNewCastleLoc(getColor(), getGameID());
	}
	public int[] getLeftCastleNewKingLoc()
	{
		return getLeftCastleSideNewKingLoc(getColor(), getGameID());
	}
	public int[] getLeftCastleNewCastleLoc()
	{
		return getLeftCastleSideNewCastleLoc(getColor(), getGameID());
	}
	
	
	//GENERIC TO STRING METHOD FOR THE PIECE
	
	public String toString()
	{
		return "<ChessPiece of Type: " + getType() + " and Color: " + getColor() +
			" at: " + getLocString(getRow(), getCol()) + " of Gender: " + convertGenderValueToString() +
			" on Game ID: " + getGameID() + ">";
	}
}
