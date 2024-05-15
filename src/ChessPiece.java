
import java.util.ArrayList;
class ChessPiece {
	private static String[] validTypes = {"PAWN", "CASTLE", "KNIGHT", "BISHOP", "QUEEN", "KING", "ROOK"};
	private static String[] validColors = {"WHITE", "BLACK"};
	public static String[][] clrs = getSquareColors();
	public static final int ROWCOLMIN = 0;
	public static final int ROWCOLMAX = 7;
	public static final boolean WHITE_MOVES_DOWN_RANKS = true;
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
	
	
	//SOME LOCATION METHODS
	
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
	public static String getLocString(int[] loc)
	{
		if (loc == null) return null;
		else if (loc.length != 2) throw new IllegalStateException("illegal loc found and used here!");
		else return getLocString(loc[0], loc[1]);
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
	public int[] getLoc()
	{
		int[] loc = new int[2];
		loc[0] = getRow();
		loc[1] = getCol();
		return loc;
	}
	public void setLoc(int rval, int cval)
	{
		boolean terr = false;
		if (isvalidrorc(rval) && isvalidrorc(cval))
		{
			//if (usecastling || canMoveToLoc(rval, cval))
			//{
				setRow(rval);
				setCol(cval);
			//}
			//else terr = true;
		}
		else terr = true;
		if (terr) throw new IllegalStateException("cannot move to new location " + getLocString(rval, cval) + "!");
	}
	public void setLoc(int[] loc)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else setLoc(loc[0], loc[1]);
	}
	
	public static void printSquareColors()
	{
		String myabt = "ABCDEFGH";
		for (int r = 0; r < 8; r++)
		{
			if (r == 0)
			{
				for (int c = 0; c < 8; c++) System.out.print("  " + myabt.charAt(c) + "   ");
				System.out.println();
			}
			for (int c = 0; c < 8; c++) System.out.print(clrs[r][c] + " ");
			System.out.println((r + 1));
		}
	}
	public static String[][] getSquareColors()
	{
		//D1 is BLACK; D8 is WHITE; H8 is WHITE
		clrs = new String[8][8];
		for (int r = 0; r < 8; r++)
		{
			String sclr = null;
			if (r % 2 == 0) sclr = "WHITE";
			else sclr = "BLACK";
			for (int c = 0; c < 8; c++)
			{
				if (c%2 == 0) clrs[r][c] = "" + sclr;
				else clrs[r][c] = getOppositeColor(sclr);
				//System.out.println("clrs[" + r + "][" + c + "] = " + clrs[r][c]);
			}
		}
		
		//printSquareColors();
		return clrs;
	}
	public static String getColorOfLoc(int rval, int cval)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be at least 0 and less than 8!");
		
		return "" + clrs[rval][cval];
	}
	public static String getColorOfLoc(ChessPiece cp)
	{
		if (cp == null) throw new IllegalStateException("cp is not allowed to be null!");
		else return getColorOfLoc(cp.getRow(), cp.getCol());
	}
	
	//CONVERT LOCS METHODS
	
	public static String convertWhiteDownRanksLocToWhiteUpRanksLocString(String dstr)
	{
		locStringIsInCorrectFormat(dstr);
		
		//column stays the same
		return "" + dstr.charAt(0) + (8 - Integer.parseInt("" + dstr.charAt(1)) + 1);
	}
	public static String convertWhiteUpRanksLocToWhiteDownRanksLocString(String ustr)
	{
		locStringIsInCorrectFormat(ustr);
		
		//column stays the same
		return "" + ustr.charAt(0) + (Integer.parseInt("" + ustr.charAt(1)) + 8 - 1);
	}
	public static String convertWhiteDownOrUpRanksLocToOther(String mstr, boolean iswhitedown)
	{
		if (iswhitedown) return convertWhiteDownRanksLocToWhiteUpRanksLocString(mstr);
		else return convertWhiteUpRanksLocToWhiteDownRanksLocString(mstr);
	}
	
	public static boolean locStringIsInCorrectFormat(String mlocstr)
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
		//allow only 1 through 8 inclusive
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
		return true;
	}
	//iswhitedown is true by default
	public static int[] convertStringLocToRowCol(String mlocstr, boolean iswhitedown)
	{
		locStringIsInCorrectFormat(mlocstr);
		if (iswhitedown == WHITE_MOVES_DOWN_RANKS);
		else return convertStringLocToRowCol(convertWhiteDownOrUpRanksLocToOther(mlocstr, iswhitedown), !iswhitedown);	
		
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
		int[] myloc = new int[2];
		myloc[1] = Integer.parseInt("" + dgts.charAt(ltri));//letter is column
		if (WHITE_MOVES_DOWN_RANKS) myloc[0] = Integer.parseInt("" + mlocstr.charAt(1)) - 1;//number is row
		else myloc[0] = 8 - Integer.parseInt("" + mlocstr.charAt(1));//number is row
		
		if (isvalidrorc(myloc[0]) && isvalidrorc(myloc[1]));
		else throw new IllegalStateException("CONVERSION ERROR! FINAL R AND C ARE NOT VALID!");
		return myloc;
	}
	public static int[] convertStringLocToRowCol(String mlocstr)
	{
		return convertStringLocToRowCol(mlocstr, true);
	}
	//retwhitedown is WHITE_MOVES_DOWN_RANKS by default
	public static String convertRowColToStringLoc(int rval, int cval, boolean retwhitedown)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("R OR C MUST BE VALID!");
		
		String abet = "ABCDEFGH";
		if (WHITE_MOVES_DOWN_RANKS) return "" + abet.charAt(cval) + "" + (rval + 1);
		else return "" + abet.charAt(cval) + "" + (8 - rval);
	}
	public static String convertRowColToStringLoc(int rval, int cval)
	{
		return convertRowColToStringLoc(rval, cval, WHITE_MOVES_DOWN_RANKS);
	}
	public static String convertRowColToStringLoc(int[] mloc, boolean retwhitedown)
	{
		if (mloc == null || mloc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return convertRowColToStringLoc(mloc[0], mloc[1], retwhitedown);
	}
	public static String convertRowColToStringLoc(int[] mloc)
	{
		return convertRowColToStringLoc(mloc, WHITE_MOVES_DOWN_RANKS);
	}
	
	//if not valid, it just prints it out and does not convert it
	public static String getLocStringAndConvertIt(int rval, int cval)
	{
		String lstr = getLocString(rval, cval);
		if (isvalidrorc(rval) && isvalidrorc(cval)) return "" + lstr + " " + convertRowColToStringLoc(rval, cval);
		else return lstr;
	}
	public static String getLocStringAndConvertIt(int[] mloc)
	{
		if (mloc == null || mloc.length != 2) throw new IllegalStateException("the loc array must have two integers on it!");
		else return getLocStringAndConvertIt(mloc[0], mloc[1]);
	}
	
	public static int[][] getLocsFromPieceList(ArrayList<ChessPiece> allpcs)
	{
		int mxitems = getNumItemsInList(allpcs); 
		if (mxitems < 1) return null;
		else
		{
			int[][] locs = new int[mxitems][2];
			for (int x = 0; x < mxitems; x++)
			{
				locs[x][0] = allpcs.get(x).getRow();
				locs[x][1] = allpcs.get(x).getCol();
			}
			return locs;
		}
	}
	
	public static void printLocsArray(int[][] locs, String arrnm)
    {
    	if (arrnm == null || arrnm.length() < 1) printLocsArray(locs, "locs");
    	if (locs == null) System.out.println("" + arrnm + " = null");
    	else if (locs.length < 1) System.out.println("" + arrnm + " is empty!");
    	else
    	{
    		System.out.println("" + arrnm + ".length = " + locs.length);
	    	for (int x = 0; x < locs.length; x++)
	    	{
	    		System.out.println(getLocStringAndConvertIt(locs[x][0], locs[x][1]));
	    	}
    	}
    }
    public static void printLocsArray(int[][] locs)
    {
    	printLocsArray(locs, "locs");
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
	
	public static int[][] transpose(int[][] myarr)
	{
		if (myarr == null) return null;
		else if (myarr.length < 1) return new int[0][myarr.length];
		else
		{
			//System.out.println("OLD ARRAY:");
			//for (int r = 0; r < myarr.length; r++)
			//{
			//	for (int c = 0; c < myarr[0].length; c++)
			//	{
			//		System.out.println("myarr[" + r + "][" + c + "] = " + myarr[r][c]);
			//	}
			//}
			
			//System.out.println("OLD DIMENSIONS: myarr.length = " + myarr.length);
			//System.out.println("myarr[0].length = " + myarr[0].length);
			
			int[][] resarr = new int[myarr[0].length][myarr.length];
			//System.out.println("NEW DIMENTIONS: resarr.length = " + resarr.length);
			//System.out.println("resarr[0].length = " + resarr[0].length);
			
			for (int r = 0; r < myarr.length; r++)
			{
				for (int c = 0; c < myarr[0].length; c++) resarr[c][r] = myarr[r][c];
			}
			
			//System.out.println("NEW ARRAY:");
			//for (int r = 0; r < resarr.length; r++)
			//{
			//	for (int c = 0; c < resarr[0].length; c++)
			//	{
			//		System.out.println("resarr[" + r + "][" + c + "] = " + resarr[r][c]);
			//	}
			//}
			//throw new IllegalStateException("NEED TO CHECK IF THIS WORKS!");
			return resarr;
		}
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
				//boolean[] keeploc = new boolean[ignorelist.length];
				//int numrm = 0;
				//for (int x = 0; x < ignorelist.length; x++)
				//{
					//this gets the ignorelist loc
					//now get the other loc to compare it to
				//	keeploc[x] = true;
				//	for (int i = 0; i < retlist.size(); i++)
				//	{
				//		if (retlist.get(i).getRow() == ignorelist[x][0] && retlist.get(i).getCol() == ignorelist[x][1])
				//		{
				//			//do not keep this on the ignore list
				//			//System.out.println("REMOVING THIS LOCATION FROM THE IGNORE LIST!");
				//			//System.out.println("ignorelist[" + x + "][0] = " + ignorelist[x][0]);
				//			//System.out.println("ignorelist[" + x + "][1] = " + ignorelist[x][1]);
				//			keeploc[x] = false;
				//			numrm++;
				//			break;
				//		}
				//		//else;//do nothing
				//	}
				//}
				//System.out.println("numrm = " + numrm);
				
				ArrayList<ChessPiece> bdiglist = null;
				//if (numrm < 0) throw new IllegalStateException("numrm must be at least zero!");
				//else if (numrm < 1) bdiglist = combineBoardAndIgnoreLists(ignorelist, boardlist);
				//else
				//{
					//int[][] nwiglist = new int[ignorelist.length - numrm][2];
					//int nwigli = 0;
					//for (int x = 0; x < ignorelist.length; x++)
					//{
					//	if (nwigli < nwiglist.length);
					//	else break;
					//	
					//	if (keeploc[x])
					//	{
					//		nwiglist[nwigli][0] = ignorelist[x][0];
					//		nwiglist[nwigli][1] = ignorelist[x][1];
					//		nwigli++;
					//	}
					//	//else;//do nothing
					//}
					bdiglist = combineBoardAndIgnoreLists(ignorelist, boardlist);//nwiglist
				//}
				if (getNumItemsInList(bdiglist) < 1);
				else for (int x = 0; x < bdiglist.size(); x++) retlist.add(bdiglist.get(x));
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
	
	//WILL ONLY USE ONE PIECE FOR A LOCATION IF THEY ARE DIFFERENT TYPES ONLY THE ONE ON LISTA WILL BE USED
	public static ArrayList<ChessPiece> combineTwoPieceLists(ArrayList<ChessPiece> lista, ArrayList<ChessPiece> listb)
	{
		if (getNumItemsInList(lista) < 1) return listb;
		else if (getNumItemsInList(listb) < 1) return lista;
		else
		{
			ArrayList<ChessPiece> alocs = new ArrayList<ChessPiece>();
			for (int x = 0; x < lista.size(); x++)
			{
				boolean addit = false;
				for (int p = 0; p < alocs.size(); p++)
				{
					if ((alocs.get(p).getRow() == lista.get(x).getRow()) &&
						(alocs.get(p).getCol() == lista.get(x).getCol()))
					{
						addit = false;
						break;
					}
					//else;//do nothing
				}
				if (addit) alocs.add(lista.get(x));
				//else;//do nothing
			}
			for (int x = 0; x < listb.size(); x++)
			{
				boolean addit = false;
				for (int p = 0; p < alocs.size(); p++)
				{
					if ((alocs.get(p).getRow() == listb.get(x).getRow()) &&
						(alocs.get(p).getCol() == listb.get(x).getCol()))
					{
						addit = false;
						break;
					}
					//else;//do nothing
				}
				if (addit) alocs.add(listb.get(x));
				//else;//do nothing
			}
			return alocs;
		}
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
	public static boolean isLocationEmpty(int rval, int cval, int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isLocationEmpty(rval, cval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid))) return true;
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
	public int getMoveCount()
	{
		return this.movecount;
	}
	public void setMoveCount(int val)
	{
		if (val < 0) throw new IllegalStateException("illegal value found and used for the move count!");
		this.movecount = val;
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
	public static String getLongHandType(String tpval)
	{
		if (tpval == null || tpval.length() != 2) throw new IllegalStateException("NULL OR EMPTY TYPE NOT ALLOWED HERE!");
		else if (tpval.equals("KG")) return "KING";
		else if (tpval.equals("KT")) return "KNIGHT";
		else if (tpval.equals("CE")) return "CASTLE";
		else if (tpval.equals("QN")) return "QUEEN";
		else if (tpval.equals("BP")) return "BISHOP";
		else if (tpval.equals("PN")) return "PAWN";
		else throw new IllegalStateException("ILLEGAL SHORT HAND TYPE FOUND!");
	}
	public static String getShortHandType(String tpval)
	{
		if (tpval == null || tpval.length() < 1) throw new IllegalStateException("NULL OR EMPTY TYPE NOT ALLOWED HERE!");
		else if (itemIsOnGivenList(tpval, validTypes))
		{
			if (tpval.equals("ROOK")) return "CE";
			else return "" + tpval.charAt(0) + tpval.charAt(tpval.length() - 1);
		}
		else throw new IllegalStateException("INVALID TYPE (" + tpval + ") FOUND HERE!");
	}
	public String getShortHandType()
	{
		return getShortHandType(getType());
	}
	public static String getShortHandColor(String clrval)
	{
		if (clrval == null || clrval.length() < 1) throw new IllegalStateException("NULL OR EMPTY COLOR NOT ALLOWED HERE!");
		else if (itemIsOnGivenList(clrval, validColors)) return "" + clrval.charAt(0);
		else throw new IllegalStateException("INVALID COLOR (" + clrval + ") FOUND HERE!");
	}
	public String getShortHandColor()
	{
		return getShortHandColor(getColor());
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
	public static ArrayList<ChessPiece> getCurrentSidePieces(String clrval, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		return getCurrentSidePieces(clrval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
	}
	public static ArrayList<ChessPiece> getCurrentSidePieces(String clrval, int gid)
	{
		return getCurrentSidePieces(clrval, getAllPiecesWithGameID(gid));
	}
	public static ArrayList<ChessPiece> getOpposingSidePieces(String clrval, ArrayList<ChessPiece> allpcs)
	{
		return getCurrentSidePieces(getOppositeColor(clrval), allpcs);
	}
	public static ArrayList<ChessPiece> getOpposingSidePieces(String clrval, int gid, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs)
	{
		return getOpposingSidePieces(clrval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
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
	
	
	//IS BOARD VALID METHODS
	
	public static int getCountForPieceTypeForASide(int[] pccnts, String tpval)
	{
		if (tpval == null || tpval.length() < 1) throw new IllegalStateException("illegal type found and used here!");
		//else;//do nothing
		if (pccnts == null || pccnts.length == 0) return 0;
		else if (pccnts.length != 6) throw new IllegalStateException("illegal counts found and used here!");
		//else;//do nothing
		
		String[] mytps = {"KING", "QUEEN", "CASTLE", "BISHOP", "KNIGHT", "PAWN"};//ROOK
		int tpi = -1;
		for (int i = 0; i < mytps.length; i++)
		{
			boolean fndit = false;
			if ((mytps[i].equals("CASTLE") && tpval.equals("ROOK")) || (mytps[i].equals(tpval))) fndit = true;
			//else;//do nothing
			
			if (fndit)
			{
				tpi = i;
				break;
			}
			//else;//do nothing
		}
		if (tpi < 0 || mytps.length - 1 < tpi) throw new IllegalStateException("illegal type found and used here!");
		//else;//do nothing
		if (pccnts[tpi] < 0 || 10 < pccnts[tpi]) throw new IllegalStateException("illegal count found and used here!");
		else return pccnts[tpi];
	}
	
	public static int[] getCountsForEachPieceTypeForASide(String[] pcstpcs)
	{
		//king, queen, castle (rook), bishop, knight, pawn
		int[] pccnts = new int[6];
		String[] mytps = {"KING", "QUEEN", "CASTLE", "BISHOP", "KNIGHT", "PAWN"};//ROOK
		int[] maxallowed = {1, 9, 10, 10, 10, 8};
		int[] startamt = {1, 1, 2, 2, 2, 8};
		for (int ci = 0; ci < 6; ci++)
		{
			pccnts[ci] = 0;
			for (int x = 0; x < pcstpcs.length; x++)
			{
				if (ci == 2 && (pcstpcs[x].equals("ROOK") || pcstpcs[x].equals("CASTLE")))
				{
					//count it
					pccnts[ci] = pccnts[ci] + 1;
				}
				else if (pcstpcs[x].equals(mytps[ci]))
				{
					//count it
					pccnts[ci] = pccnts[ci] + 1;
				}
				//else;//do nothing
			}
			//make sure the board is valid
			if (pccnts[ci] < 0 || maxallowed[ci] < pccnts[ci])
			{
				throw new IllegalStateException("illegal number of pieces found on the board!");
			}
			//else;//do nothing
			if (ci == 0)
			{
				if (pccnts[ci] == maxallowed[ci]);
				else throw new IllegalStateException("illegal number of kings found on the board!");
			}
			//else;//do nothing
		}
		//make sure the board is valid
		int ttl = 0;
		for (int ci = 0; ci < 6; ci++) ttl += pccnts[ci];
		if (ttl < 1 || 16 < ttl)
		{
			throw new IllegalStateException("illegal total number of side pieces (" + ttl + ") found on the board!");
		}
		//else;//do nothing
		int[] diffstart = new int[6];
		//actual amount - start amount; diff < 0 when actual < start; 0 < diff when start < actual
		for (int ci = 0; ci < 6; ci++) diffstart[ci] = pccnts[ci] - startamt[ci];
		int numusdpns = 0;
		for (int ci = 0; ci < 6; ci++)
		{
			if (diffstart[ci] < -8 || 8 < diffstart[ci])
			{
				throw new IllegalStateException("illegal diff (" + diffstart[ci] + ") found and used here!");
			}
			//else;//do nothing
			if (ci == 0)
			{
				if (diffstart[ci] == 0);
				else throw new IllegalStateException("illegal number of kings found on the board!");
			}
			else
			{
				if (0 < diffstart[ci]) numusdpns += diffstart[ci];
				//else;//do nothing
			}
		}
		//System.out.println("numusdpns = " + numusdpns);
		//System.out.println("pccnts[" + 5 + "] = " + pccnts[5]);
		if (numusdpns + pccnts[5] < 0 || 8 < numusdpns + pccnts[5])
		{
			throw new IllegalStateException("illegal number of used pawns pieces (" + numusdpns +
				") with " + pccnts[5] + " pawn(s) found on the board!");
		}
		//else;//do nothing
		return pccnts;
	}
	
	public static String[] getPieceTypes(ArrayList<ChessPiece> allpcs)
	{
		if (allpcs == null) return null;
		else
		{
			String[] wpcstps = new String[allpcs.size()];
			for (int x = 0; x < wpcstps.length; x++) wpcstps[x] = allpcs.get(x).getType();
    		return wpcstps;
		}
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
		ArrayList<ChessPiece> wpcs = filterListByColor(allpcs, "WHITE");
		ArrayList<ChessPiece> bpcs = filterListByColor(allpcs, "BLACK");
		String[] wpcstps = getPieceTypes(wpcs);
		String[] bpcstps = getPieceTypes(bpcs);
		try
		{
			int[] wpctpscnts = getCountsForEachPieceTypeForASide(wpcstps);
		}
		catch(Exception ex)
		{
			throw new IllegalStateException("ILLEGAL NUMBER OF WHITE PIECES FOUND ON THE BOARD!", ex);
		}
		try
		{
			int[] bpctpscnts = getCountsForEachPieceTypeForASide(bpcstps);
		}
		catch(Exception ex)
		{
			throw new IllegalStateException("ILLEGAL NUMBER OF BLACK PIECES FOUND ON THE BOARD!", ex);
		}
		return true;
	}
	public static boolean isBoardValid(int gid)
	{
		return isBoardValid(getAllPiecesWithGameID(gid));
	}
	
	
	//HOW TO REMOVE PIECES?
	//WE NEED TO REMOVE THEM FROM THE LIST OF PIECES.
	//WE NEED TO MAKE THEIR REFERENCES BE NULL.
	public static void removePieceAt(int rval, int cval, int gid)
	{
		int numpcs = getNumItemsInList(cps);
		if (numpcs < 1);
		else
		{
			for (int x = 0; x < numpcs; x++)
			{
				if (cps.get(x).getRow() == rval && cps.get(x).getCol() == cval && cps.get(x).getGameID() == gid)
				{
					System.out.println("REMOVED " + cps.get(x));
					cps.remove(cps.get(x));
					numpcs--;
					x--;
				}
				//else;//do nothing
			}
		}
	}
	
	
	//NEED TO KNOW WHOSE TURN IT IS AND
	//NEED TO PREVENT THE OTHER SIDE FROM MOVING UNTIL WE TELL THEM IT IS THEIR TURN
	//NEED A WAY TO AGREE UPON A TIE OR DRAW (STALEMATE)
	//NEED A WAY TO SURRENDER OR RESIGN
	//NEED A WAY TO UNDO OR REDO A MOVE
	//NEED A WAY TO STEP THROUGH A FINISHED GAME
	//NEED A WAY TO TELL THE OTHER COMPUTER: IT IS THEIR TURN, WHAT MOVES WERE MADE, AND HOW THE GAME ENDS,
	//WHAT TO DO AFTER THE GAME ENDS?
	//NEED A WAY TO COMMUNICATE WITH MY SERVER
	
	//HOW TO STEP THROUGH A COMPLETED GAME (ONLY COMPLETED GAMES):
	//WHAT DOES BACK AND NEXT DO? INCREASE OR DECREASE THE STEP_COUNTER/INDEX.
	//
	//TO GO BACKWARD:
	//UNDO THE CURRENT MOVE (PUT ON UNOFFICIAL MOVE, AND THEN UNDO THE UNOFFICIAL MOVE)
	//-COUNTER HAS TO DECREASE,
	//-CLEAR UNOFFICIAL_MOVE.
	//-THEN GET THE PREVIOUS MOVE
	//
	//TO GO FORWARDS: (MIGHT BE SMART TO START STEP_INDEX AT -1)
	//-INCREASE THE STEP COUNTER/INDEX
	//-GET THE CURRENT MOVE (SET IT AS THE UNOFFICIAL MOVE)
	//-MAKE IT (IT IS ALREADY OFFICIAL, SO DO NOT ADD TO LIST OF OFFICIAL MOVES)
	//
	
	//TO UNDO AN UNOFFICIAL_MOVE:
	//TAKE THE UNOFFICIAL_MOVE AND UNDO IT (DO THE OPPOSITE)
	//AND PUT IT ON LAST_UNDONE_MOVE
	
	//TO UNDO AN OFFICIAL MOVE:
	//MAKE IT THE UNOFFICIAL_MOVE
	//CLEAR LAST_REDONE_MOVE.
	
	//TO REDO AN UNDONE MOVE:
	//TAKE THE LAST_UNDONE_MOVE AND MAKE IT THE UNOFFICIAL_MOVE
	
	//TO MAKE AN UNOFFICIAL_MOVE OFFICIAL:
	//ADD IT TO THE LIST_OF_OFFICIAL_MOVES.
	//CLEAR LAST_REDONE_MOVE.
	
	
	//CHECKMATE: ONE SIDE IS IN CHECK AND CANNOT GET OUT OF IT
	//THEY CANNOT BLOCK CHECK, THEY CANNOT MOVE OUT OF CHECK, AND THEY CANNOT KILL THE CHECKING PIECE
	
	
	//BEFORE WE ADVANCE TO THE OTHER SIDE'S TURN:
	//MAKE THE UNOFFICIAL_MOVE OFFICIAL
	//IF THEY ARE IN CHECK AND THE UNOFFICIAL_MOVE DID NOT MOVE THEM OUT OF CHECK ASK IF THEY WANT TO SURRENDER OR UNDO?
	//IF THEY CHOOSE SURRENDER: CHECKMATE! OTHER SIDE WINS!
	//IF THEY CHOOSE UNDO: NEED TO LET THEM MOVE THEN RE-ENTER THIS METHOD.
	//WE NEED TO CHECK TO SEE IF THE CURRENT SIDE KING IS STILL IN CHECK() IF SO, END GAME IMMEDIATELY CHECKMATE!
	//CHECK TO SEE IF THE GAME ENDS IN AN AUTO-STALEMATE
	//WE NEED TO CHECK TO SEE IF THERE ARE PAWNS FOR THAT SIDE THAT HAVE MADE IT TO THE OTHER SIDE AND
	//-NEED PROMOTED AND TO PROMOTE THEM
	
	
	//STALEMATE: IS WHEN A SIDE HAS NO LEGAL MOVES. RULE: YOU CANNOT MOVE INTO CHECK!!!
	//
	//AUTO-STALEMATE:
	//ANY ONE OF THESE SITUATIONS OCCURS THE OTHERSIDE COULD HAVE ONE OF THESE
	//KING VS KING, KING AND KNIGHT VS KING
	//KING AND ANY NUMBER OF BISHOPS VS KING AND ANY NUMBER OF BISHOPS WHERE ALL THE BISHOPS ARE ON THE SAME COLOR SQUARES
	//KING AND PAWNS VS KING AND PAWNS (NO FREE PAWNS, ABILITY TO ALL BE BLOCKED AND REMAIN THAT WAY) -> (KING VS KING)
	//
	//DEBATE SURROUNDING THIS: KING AND 2 KNIGTS AND NO OPPOSING PAWNS VS KING (CHECK MATE IS POSSIBLE, BUT CANNOT BE FORCED)
	//KING AND (KNIGHT OR BISHOP) VS KING AND (KNIGHT OR BISHOP) CHECK MATE IS POSSIBLE, BUT CANNOT BE FORCED
	//
	//HOWEVER IF THERE IS A QUEEN ON THE BOARD OR A CASTLE, YOU WILL BE ABLE TO FORCE CHECKMATE
	
	
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
	
	
	//IF THE ALL PIECES LIST IS EMPTY RETURNS FALSE.
	public static boolean isPieceAtLocationOnAListOfTypes(int rval, int cval, String[] mtypes, ArrayList<ChessPiece> allpcs)
	{
		//System.out.println("INSIDE OF IS PIECE AT LOCATION ON A LIST OF TYPES WITH LOCATION: " +
		//	getLocString(rval, cval));
		//System.out.println("allpcs = " + allpcs);
		//System.out.print("mtypes = [");
		//for (int x = 0; x < mtypes.length; x++)
		//{
		//	System.out.print('"' + mtypes[x] + '"');
		//	if (x + 1 < mtypes.length) System.out.print(", ");
		//}
		//System.out.println("]");
		
		if (getNumItemsInList(allpcs) < 1);//no items on the add pieces list
		else
		{
			//System.out.println("INSIDE ELSE STATEMENT!");
			for (int x = 0; x < allpcs.size(); x++)
			{
				//System.out.println("x = " + x);
				//System.out.println("allpcs.get(" + x + ") = " + allpcs.get(x));
				//System.out.println("row = " + allpcs.get(x).getRow());
				//System.out.println("col = " + allpcs.get(x).getCol());
				if (allpcs.get(x).getRow() == rval && allpcs.get(x).getCol() == cval)
				{
					if (itemIsOnGivenList(allpcs.get(x).getType(), mtypes)) return true;
					else return false;
				}
				//else;//do nothing
			}
		}
		//System.out.println("DID NOT FIND IT!");
		return false;
	}
	//combines with the current board list prioritizes: boardlist < ignorelist < addpcs. 
	public static boolean isPieceAtLocationOnAListOfTypes(int rval, int cval, int gid, String[] mtypes,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return isPieceAtLocationOnAListOfTypes(rval, cval, mtypes, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int rval, int cval, int gid, String[] mtypes,
		int[][] ignorelist)
	{
		return isPieceAtLocationOnAListOfTypes(rval, cval, gid, mtypes, ignorelist, null);
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int[] loc, int gid, String[] mtypes,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (loc == null || loc.length != 2)
		{
			throw new IllegalStateException("You need to provide the chess piece location!");
		}
		else
		{
			return isPieceAtLocationOnAListOfTypes(loc[0], loc[1], mtypes,
				combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
		}
	}
	public static boolean isPieceAtLocationOnAListOfTypes(int[] loc, int gid, String[] mtypes,
		int[][] ignorelist)
	{
		return isPieceAtLocationOnAListOfTypes(loc, gid, mtypes, ignorelist, null);
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
	
	
	public static boolean[] getLocOnIgnoreListAndValidTypeData(int rval, int cval, int gid, String[] myvtps,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		boolean loconiglist = false;
		boolean pcatloconiglist = false;
		boolean isvpctpeoniglist = false;
		if (isLocOnListOfLocs(rval, cval, ignorelist))
		{
			//is there a piece on the add list that matches the loc?
			loconiglist = true;
			ChessPiece cp = getPieceAt(rval, cval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
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
	
	
	//DETECTS PIECES DIRECTLY ABLE TO ATTACK OR MOVE TO A LOCATION
	
	public static ArrayList<ChessPiece> getPiecesGuardingLocationByAKnight(int rval, int cval, int gid,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else throw new IllegalStateException("rval and cval must be valid!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		int[][] pklocs = getAllPossibleKnightMoveToLocs(rval, cval);
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		
		String[] mvtps = {"KNIGHT"};
		ArrayList<ChessPiece> gpcs = null;
		if (isvalidrorc(rval) && isvalidrorc(cval))
		{
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, cval, gid, mvtps, ignorelist, addpcs);
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
					(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, cval, gid, mvtps, ignorelist, addpcs)))
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					gpcs.add(getPieceAt(rval, cval, allpcs));
				}
				//else;//do nothing
			}
		}
		//else;//do nothing
		
		for (int x = 0; x < 8; x++)
		{
			if (isvalidrorc(pklocs[x][0]) && isvalidrorc(pklocs[x][1]))
			{
				boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(pklocs[x][0], pklocs[x][1], gid, mvtps,
					ignorelist, addpcs);
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
				
				if ((loconiglist && pcatloconiglist && isvpctpeoniglist) || (!loconiglist &&
					isPieceAtLocationOnAListOfTypes(pklocs[x][0], pklocs[x][1], gid, mvtps, ignorelist, addpcs)))
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					//System.out.println("ADD PIECE AT THIS LOCATION:");
					//System.out.println("pklocs[" + x + "][0] = " + pklocs[x][0]);
					//System.out.println("pklocs[" + x + "][1] = " + pklocs[x][1]);
					
					gpcs.add(getPieceAt(pklocs[x][0], pklocs[x][1], allpcs));
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
		//System.out.println("cp = " + cp);
		//System.out.println("srval = " + srval);
		//System.out.println("scval = " + scval);
		//if (myvtps == null || myvtps.length < 1) System.out.println("myvtps is null or empty!");
		//else
		//{
		//	System.out.println("myvtps.length = " + myvtps.length);
		//	for (int x = 0; x < myvtps.length; x++) System.out.println(myvtps[x]);
		//}
		boolean addit = initaddit; 
		if (cp == null) return false;
		else
		{
			//the piece is on our list of types, but it may not be able to attack the location
			//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
			if (itemIsOnGivenList(cp.getType(), myvtps))
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
						//System.out.println("THIS IS A PAWN!");
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
					//else System.out.println("NOT A PAWN!");
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
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		
		for (int x = 0; x < 4; x++)
		{
			//System.out.println("x = " + x);
			
			int r = rval;
			int c = cval;
			while (isvalidrorc(r) && isvalidrorc(c))
			{
				//System.out.println("r = " + r);
				//System.out.println("c = " + c);
				boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, c, gid, myvtps, ignorelist, addpcs);
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
				//IF IT IS ON THE ADD LIST, USE IT FIRST
				//ELSE NOT.
				
				if (inconly);
				else
				{
					boolean locntempty = true;
					if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
						(!loconiglist && isPieceAtLocationOnAListOfTypes(r, c, gid, myvtps, ignorelist, addpcs)))
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
						ChessPiece cp = getPieceAt(r, c, allpcs);
						//System.out.println("FINAL cp = " + cp);
						addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
						//System.out.println("addit = " + addit);
						
						if (addit)
						{
							if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
							//else;//do nothing
							
							gpcs.add(cp);
							//proceed below to handle exiting the loop
						}
						else
						{
							if (cp == null) locntempty = false;
							//else;//do nothing proceed below to handle exiting the loop
						}
					}
					else
					{
						if (loconiglist);//the location is not empty
						else
						{
							if (isLocationEmpty(r, c, gid, ignorelist, addpcs)) locntempty = false;
							//else;//do nothing proceed below to handle exiting the loop
						}
					}
					//System.out.println("locntempty = " + locntempty);
					if (locntempty)
					{
						if (r == rval && c == cval);
						else break;
					}
					//else;//do nothing
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
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		for (int r = rval; r < 8; r++)
		{
			//System.out.println("INC r = " + r);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, cval, gid, myvtps, ignorelist, addpcs);
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
			
			boolean locntempty = true;
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps, ignorelist, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				
				//the piece is on our list of types, but it may not be able to attack the location
				//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
				ChessPiece cp = getPieceAt(r, cval, allpcs);
				//System.out.println("FINAL cp = " + cp);
				boolean addit = true;
				String[] rstps = {"KING"};
				addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, false);
				
				if (addit)
				{
					if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					gpcs.add(cp);
					//proceed below to handle exiting the loop
				}
				else
				{
					if (cp == null) locntempty = false;
					//else;//do nothing proceed below to handle exiting the loop
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(r, cval, gid, ignorelist, addpcs)) locntempty = false;
					//else;//do nothing proceed below to handle exiting the loop
				}
			}
			//System.out.println("locntempty = " + locntempty);
			if (locntempty)
			{
				if (r == rval);
				else break;
			}
			//else;//do nothing
		}
		for (int r = rval; ((r == 0 || 0 < r) && r < 8); r--)
		{
			//System.out.println("DEC r = " + r);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(r, cval, gid, myvtps, ignorelist, addpcs);
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
			
			boolean locntempty = true;
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(r, cval, gid, myvtps, ignorelist, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (r == rval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = getPieceAt(r, cval, allpcs);
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, false);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
						//proceed below to handle exiting the loop
					}
					else
					{
						if (cp == null) locntempty = false;
						//else;//do nothing proceed below to handle exiting the loop
					}
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(r, cval, gid, ignorelist, addpcs)) locntempty = false;
					//else;//do nothing proceed below to handle exiting the loop
				}
			}
			//System.out.println("locntempty = " + locntempty);
			if (locntempty)
			{
				if (r == rval);
				else break;
			}
			//else;//do nothing
		}
		for (int c = cval; c < 8; c++)
		{
			//System.out.println("INC c = " + c);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, c, gid, myvtps, ignorelist, addpcs);
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
			
			boolean locntempty = true;
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps, ignorelist, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (c == cval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = getPieceAt(rval, c, allpcs);
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
						//proceed below to handle exiting the loop
					}
					else
					{
						if (cp == null) locntempty = false;
						//else;//do nothing proceed below to handle exiting the loop
					}
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(rval, c, gid, ignorelist, addpcs)) locntempty = false;
					//else;//do nothing proceed below to handle exiting the loop
				}
			}
			//System.out.println("locntempty = " + locntempty);
			if (locntempty)
			{
				if (c == cval);
				else break;
			}
			//else;//do nothing
		}
		for (int c = cval; ((c == 0 || 0 < c) && c < 8); c--)
		{
			//System.out.println("DEC c = " + c);
			boolean[] logonigvtpdtalist = getLocOnIgnoreListAndValidTypeData(rval, c, gid, myvtps, ignorelist, addpcs);
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
			
			boolean locntempty = true;
			if ((loconiglist && pcatloconiglist && isvpctpeoniglist) ||
				(!loconiglist && isPieceAtLocationOnAListOfTypes(rval, c, gid, myvtps, ignorelist, addpcs)))
			{
				//System.out.println("INSIDE IF STATEMENT!");
				if (c == cval);
				else
				{
					//the piece is on our list of types, but it may not be able to attack the location
					//if it is a king or pawn and distance in magnitude is more than 1, not a threat.
					ChessPiece cp = getPieceAt(rval, c, allpcs);
					//System.out.println("FINAL cp = " + cp);
					boolean addit = true;
					String[] rstps = {"KING"};
					addit = getCanAddPieceToGList(cp, rstps, rval, cval, addit, true);
					
					if (addit)
					{
						if (gpcs == null) gpcs = new ArrayList<ChessPiece>();
						//else;//do nothing
						
						gpcs.add(cp);
						//proceed below to handle exiting the loop
					}
					else
					{
						if (cp == null) locntempty = false;
						//else;//do nothing proceed below to handle exiting the loop
					}
				}
			}
			else
			{
				//System.out.println("INSIDE ELSE STATEMENT!");
				if (loconiglist);//the location is not empty
				else
				{
					if (isLocationEmpty(rval, c, gid, ignorelist, addpcs)) locntempty = false;
					//else;//do nothing
				}
			}
			//System.out.println("OUTSIDE OF IF-ELSE STATEMENT");
			//System.out.println("locntempty = " + locntempty);
			if (locntempty)
			{
				if (c == cval);
				else break;
			}
			//else;//do nothing
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
		//printLocsArray(ignorelist, "ignorelist");
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
	
	
	
	//CHECK METHODS
	
	//can I be directly attacked by the opposing side?
	public boolean inCheck(int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		//can I be directly attacked by the opposing side?
		ArrayList<ChessPiece> epcs = getEnemyPiecesGuardingLocation(getRow(), getCol(), getGameID(), getColor(),
			ignorelist, addpcs);
		//System.out.println("epcs = " + epcs);
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
	
	//this gets the king with the specified color and then calls inCheck on it
	public static boolean isSideInCheck(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		ChessPiece mkg = getCurrentSideKing(clrval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
		if (mkg == null) throw new IllegalStateException("the king must be found!");
		else return mkg.inCheck(ignorelist, addpcs);
	}
	public static boolean isSideInCheck(String clrval, int gid)
	{
		return isSideInCheck(clrval, null, null, gid);
	}
	
	//checks to see if a side is in check and checks the given color first, if no color provided it starts with white
	//it will also check black; white then black or black then white
	public static boolean isASideInCheck(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return (isSideInCheck(clrval, ignorelist, addpcs, gid) ||
			isSideInCheck(getOppositeColor(clrval), ignorelist, addpcs, gid));
	}
	public static boolean isASideInCheck(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return isASideInCheck("WHITE", ignorelist, addpcs, gid);
	}
	
	
	//GET CAN MOVE TO LOCATIONS METHODS
	
	public static boolean canAddThisMoveToLoc(int sr, int sc, int nr, int nc, String myclr, String mytpval,
		int[][] oignorelist, ArrayList<ChessPiece> oaddpcs, int gid)
	{
		if (isvalidrorc(sr) && isvalidrorc(sc));
		else throw new IllegalStateException("SR AND SC MUST BE VALID!");
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		ArrayList<ChessPiece> initbdpcs = combineBoardAddAndIgnoreLists(oignorelist, oaddpcs, gid);
		ChessPiece cp = getPieceAt(nr, nc, initbdpcs);
		//System.out.println("cp = " + cp);
		
		boolean addit = true;
		if (cp == null);
		else
		{
			if (cp.getColor().equals(myclr))
			{
				if (sr == cp.getRow() && sc == cp.getCol());
				else addit = false;
			}
			//else;//do nothing
		}
		//System.out.println("OLD addit = " + addit);
		
		if (mytpval == null) throw new IllegalStateException("mytpval must not be null!");
		else if (mytpval.equals("PAWN"))
		{
			if (nr != sr && nc != sc)
			{
				//System.out.println("PAWN IS MOVING DIAGNAL!");
				
				int rdiff = sr - nr;
				int cdiff = sc - nc;
				if (rdiff < 1) rdiff *= -1;
				if (cdiff < 1) cdiff *= -1;
				if (1 < rdiff || 1 < cdiff) addit = false;
				else if (rdiff == 1 && cdiff == 1)
				{
					if (cp == null) addit = false;
					else
					{
						if (cp.getColor().equals(myclr)) addit = false;
						//else;//do nothing
					}
				}
				//else;//do nothing
			}
			else if (nr != sr && nc == sc)
			{
				//System.out.println("PAWN IS MOVING FORWARD!");
				
				int rdiff = sr - nr;
				if (rdiff < 1) rdiff *= -1;
				//System.out.println("rdiff = " + rdiff);
				
				if (rdiff == 2)
				{
					int dirfact = 0;
					if (myclr == null) throw new IllegalStateException("color must not be null!");
					if (myclr.equals("WHITE")) dirfact = -1;
					else if (myclr.equals("BLACK")) dirfact = 1;
					else throw new IllegalStateException("illegal color (" + myclr + ") found and used here");
					//System.out.println("PAWN dirfact = " + dirfact);
						
					ChessPiece ocp = getPieceAt(sr + dirfact, nc, initbdpcs);
					//System.out.println("ocp = " + ocp);
					
					if (ocp == null);//do nothing add it
					else addit = false;
				}
				//else;//do nothing add it
				
				if (cp == null);//do nothing
				else addit = false;
			}
			//else;//do nothing
		}
		//else;//do nothing valid
		//System.out.println("NEW addit = " + addit);
		
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
			//System.out.println("mkg = " + mkg);
			//System.out.println("addpcs = " + addpcs);
			//printLocsArray(ignorelist, "ignorelist");
			if (mkg == null) throw new IllegalStateException("our king must be on the board, but it was not found!");
			else
			{
				if (mkg.inCheck(ignorelist, addpcs)) addit = false;
				//else;//do nothing
			}
		}
		//else;//do nothing
		//System.out.println("FINAL addit = " + addit);
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
				//System.out.println("r = " + r);
				//System.out.println("c = " + c);
				if (canAddThisMoveToLoc(sr, sc, r, c, myclr, "BISHOP", ignorelist, addpcs, gid))
				{
					//System.out.println("KEEP THIS LOCATION!");
					//need to make sure we are not adding a duplicate loc to the list...
					if (isLocOnListOfLocs(r, c, keeplist));
					else
					{
						keeplist[kli][0] = r;
						keeplist[kli][1] = c;
						kli++;
					}
				}
				//else;//do nothing
				ChessPiece cp = getPieceAt(r, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
				//System.out.println("cp = " + cp);
				if (cp == null);
				else
				{
					if (r == sr && c == sc);
					else break;
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
			//System.out.println("r = " + r);
			//System.out.println("c = " + sc);
			if (r == sr) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, r, sc, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				//System.out.println("ADD LOCATION!");
				keeplist[kli][0] = r;
				keeplist[kli][1] = sc;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(r, sc, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			//System.out.println("cp = " + cp);
			if (cp == null);
			else break;
		}
		for (int r = sr; (0 < r || r == 0 && r < 8); r--)
		{
			//System.out.println("r = " + r);
			//System.out.println("c = " + sc);
			if (r == sr) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, r, sc, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				//System.out.println("ADD LOCATION!");
				keeplist[kli][0] = r;
				keeplist[kli][1] = sc;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(r, sc, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			//System.out.println("cp = " + cp);
			if (cp == null);
			else break;
		}
		//row stays the same
		for (int c = sc; c < 8; c++)
		{
			//System.out.println("r = " + sr);
			//System.out.println("c = " + c);
			if (c == sc) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, sr, c, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				//System.out.println("ADD LOCATION!");
				keeplist[kli][0] = sr;
				keeplist[kli][1] = c;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(sr, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			//System.out.println("cp = " + cp);
			if (cp == null);
			else break;
		}
		for (int c = sc; (0 < c || c == 0 && c < 8); c--)
		{
			//System.out.println("r = " + sr);
			//System.out.println("c = " + c);
			if (c == sc) continue;
			//else;//do nothing
			
			if (canAddThisMoveToLoc(sr, sc, sr, c, myclr, "CASTLE", ignorelist, addpcs, gid))
			{
				//System.out.println("ADD LOCATION!");
				keeplist[kli][0] = sr;
				keeplist[kli][1] = c;
				kli++;
			}
			//else;//do nothing
			ChessPiece cp = getPieceAt(sr, c, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
			//System.out.println("cp = " + cp);
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
		
		int dirfact = 0;
		if (myclr == null) throw new IllegalStateException("color must not be null!");
		if (myclr.equals("WHITE")) dirfact = -1;
		else if (myclr.equals("BLACK")) dirfact = 1;
		else throw new IllegalStateException("illegal color (" + myclr + ") found and used here");
		//System.out.println("PAWN dirfact = " + dirfact);
		
		//can only move forward one or two spaces on the first turn otherwise forward one only
		//exception is attacking or pawning
		
		//if has not moved can move forward 2 spots or 1 spot
		//otherwise can only move forward 1 spot unless can kill a piece only attacks diagnal
		boolean canmvfwdtwo = ((sr == 6 && myclr.equals("WHITE")) || (sr == 1 && myclr.equals("BLACK")));
		//System.out.println("PAWN canmvfwdtwo = " + canmvfwdtwo);
		
		int[][] tplocs = new int[5][2];
		tplocs[0][0] = sr;
		tplocs[0][1] = sc;
		if (canmvfwdtwo)
		{
			tplocs[1][0] = sr + (2*dirfact);
			tplocs[1][1] = sc;
		}
		else
		{
			tplocs[1][0] = -1;
			tplocs[1][1] = -1;
		}
		//move forward or backwards one spot
		tplocs[2][0] = sr + dirfact;
		tplocs[2][1] = sc;
		//now attack locations...
		tplocs[3][0] = sr + dirfact;
		tplocs[3][1] = sc - 1;
		tplocs[4][0] = sr + dirfact;
		tplocs[4][1] = sc + 1;
		
		//now we need to go through all of these locations and see how they effect the king exclude the first one
		//exclude all invalid locations
		boolean[] isvloc = new boolean[tplocs.length];
		int numv = 1;
		isvloc[0] = true;
		//System.out.println("STARTING LOCATION: " + getLocString(sr, sc) + ": " + convertRowColToStringLoc(sr, sc));
		
		for (int x = 1; x < tplocs.length; x++)
    	{
    		isvloc[x] = (isvalidrorc(tplocs[x][0]) && isvalidrorc(tplocs[x][1]));
    		//System.out.println("CURRENT LOC " + getLocString(tplocs[x][0], tplocs[x][1]));
    		//System.out.println("OLD isvloc[" + x + "] = " + isvloc[x]);
    		
    		if (isvloc[x])
    		{
    			//the loc is valid, but now see if moving there moves our king to check or
    			//see if we can even move there in the first place
    			if (canAddThisMoveToLoc(sr, sc, tplocs[x][0], tplocs[x][1], myclr, "PAWN", ignorelist, addpcs, gid))
				{
					//System.out.println("VALID LOC " + getLocString(tplocs[x][0], tplocs[x][1]) + ": " +
	    			//	convertRowColToStringLoc(tplocs[x]));
					isvloc[x] = true;
				}
				else isvloc[x] = false;
				//System.out.println("NEW isvloc[" + x + "] = " + isvloc[x]);
	    	}
	    	//else;//do nothing
	    	//System.out.println("FINAL isvloc[" + x + "] = " + isvloc[x]);
    		
    		if (isvloc[x]) numv++;
    		//else;//do nothing
    	}
    	int[][] rlist = new int[numv][2];
    	int vki = 0;
    	for (int x = 0; x < tplocs.length; x++)
    	{
    		if (isvloc[x])
    		{
    			rlist[vki][0] = tplocs[x][0];
    			rlist[vki][1] = tplocs[x][1];
    			vki++;
    		}
    		//else;//do nothing
    	}
		return rlist;
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
		//System.out.println("sr = " + sr);
		//System.out.println("sc = " + sc);
		int sri = -1;
		if (0 < sr) sri = sr - 1;
		else if (0 == sr) sri = 0;
		else throw new IllegalStateException("negative values not allowed for sr!");
		int sci = -1;
		if (0 < sc) sci = sc - 1;
		else if (0 == sc) sci = 0;
		else throw new IllegalStateException("negative values not allowed for sc!");
		//System.out.println("sri = " + sri);
		//System.out.println("sci = " + sci);
		for (int r = sri; ((0 < r || r == 0) && r < 8) && r < sr + 2; r++)
		{
			for (int c = sci; ((0 < c || c == 0) && c < 8) && c < sc + 2; c++)
			{
				//System.out.println("r = " + r);
				//System.out.println("c = " + c);
				if (canAddThisMoveToLoc(sr, sc, r, c, myclr, "KING", ignorelist, addpcs, gid))//(r == sr && c == sc) || 
				{
					//System.out.println("ADD LOCATION!");
					//if (isLocOnListOfLocs(r, c, keeplist));
					//else
					//{
						keeplist[kli][0] = r;
						keeplist[kli][1] = c;
						kli++;
					//}
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
	
	//THIS TAKES INTO ACCOUNT PAWNING TOO; IF NOT CALLED ON A PAWN WITH THE SAME COLOR JUST RETURNS ABOVE
	public static int[][] getAllPawnCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece cp = getPieceAt(sr, sc, allpcs);
		int[][] pcmlocs = getPawnCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
		if (cp == null) return pcmlocs;
		else
		{
			if (cp.getColor().equals(myclr));
			else return pcmlocs;
			
			if (cp.getType().equals("PAWN"))
			{
				//now can handle the pawning stuff
				int[] pleftloc = null;
				int[] prightloc = null;
				if (cp.canPawnLeft(allpcs)) pleftloc = cp.getPawnLeftLocation(allpcs);
				if (cp.canPawnRight(allpcs)) prightloc = cp.getPawnRightLocation(allpcs);
				int numaddlocs = 0;
				boolean addpleft = false;
				if (pleftloc == null);
				else
				{
					numaddlocs++;
					addpleft = true;
				}
				boolean addpright = false;
				if (prightloc == null);
				else
				{
					numaddlocs++;
					addpleft = true;
				}
				//System.out.println("addpleft = " + addpleft);
				//System.out.println("addpright = " + addpright);
				int[][] locs = null;
				if (pcmlocs == null) locs = null;
				else
				{
					locs = new int[numaddlocs + pcmlocs.length][2];
					for (int x = 0; x < pcmlocs.length; x++)
					{
						locs[x][0] = pcmlocs[x][0];
						locs[x][1] = pcmlocs[x][1];
					}
					int lci = pcmlocs.length;
					if (addpleft)
					{
						locs[lci][0] = pleftloc[0];
						locs[lci][1] = pleftloc[1];
						lci++;
					}
					//else;//do nothing
					if (addpright)
					{
						locs[lci][0] = prightloc[0];
						locs[lci][1] = prightloc[1];
						lci++;
					}
					//else;//do nothing
					if (lci == locs.length);
					else throw new IllegalStateException("locs does not have the correct size!");
				}
				return locs;
			}
			else return pcmlocs;
		}
	}
	
	//THIS TAKES INTO ACCOUNT CASTLEING FOR KING ONLY; IF NOT CALLED ON A KING WITH THE SAME COLOR JUST RETURNS ABOVE
	public static int[][] getAllKingCanMoveToLocs(int sr, int sc, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		int[][] kcmvlocs = getKingCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece cp = getPieceAt(sr, sc, allpcs);
		if (cp == null) return kcmvlocs;
		else
		{
			if (cp.getColor().equals(myclr));
			else return kcmvlocs;
			
			if (cp.getType().equals("KING"))
			{
				//now we can see if we can castle easily now
				//System.out.println("SEES IF WE CAN CASTLE:");
				boolean ccleft = canSideCastleLeft(myclr, ignorelist, addpcs, gid);
				boolean ccright = canSideCastleRight(myclr, ignorelist, addpcs, gid);
				//System.out.println("ccleft = " + ccleft);
				//System.out.println("ccright = " + ccright);
				int[] clftloc = null;
				int[] crgtloc = null;
				int numadd = 0;
				if (ccleft)
				{
					clftloc = getLeftCastleSideNewKingLoc(myclr, ignorelist, addpcs, gid);
					numadd++;
					//System.out.println("clftloc[0] = " + clftloc[0]);
					//System.out.println("clftloc[1] = " + clftloc[1]);
				}
				if (ccright)
				{
					crgtloc = getRightCastleSideNewKingLoc(myclr, ignorelist, addpcs, gid);
					numadd++;
					//System.out.println("crgtloc[0] = " + crgtloc[0]);
					//System.out.println("crgtloc[1] = " + crgtloc[1]);
				}
				//System.out.println("numadd = " + numadd);
				if (numadd < 1) return kcmvlocs;
				else if (2 < numadd) throw new IllegalStateException("numadd is an invalid value!");
				//else;//do nothing
				if (kcmvlocs == null) return null;
				else
				{
					int[][] locs = new int[kcmvlocs.length + numadd][2];
					int lci = kcmvlocs.length;
					for (int x = 0; x < kcmvlocs.length; x++)
					{
						locs[x][0] = kcmvlocs[x][0];
						locs[x][1] = kcmvlocs[x][1];
					}
					if (ccleft)
					{
						locs[lci][0] = clftloc[0];
						locs[lci][1] = clftloc[1];
						lci++;
					}
					//else;//do nothing
					if (ccright)
					{
						locs[lci][0] = crgtloc[0];
						locs[lci][1] = crgtloc[1];
						lci++;
					}
					//else;//do nothing
					return locs;
				}
			}
			else return kcmvlocs;
		}
	}
	
	//calls the above methods
	public static int[][] getPieceCanMoveToLocs(int sr, int sc, String myclr, String mytpval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid, boolean nocsling)
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
			else if (mytpval.equals("PAWN")) return getAllPawnCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else if (mytpval.equals("KING"))
			{
				if (nocsling) return getKingCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
				else return getAllKingCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			}
			else if (mytpval.equals("KNIGHT")) return getKnightCanMoveToLocs(sr, sc, myclr, ignorelist, addpcs, gid);
			else throw new IllegalStateException("illegal value found and used here for mytpval (" + mytpval + ")!");
		}
	}
	public static int[][] getPieceCanMoveToLocs(int sr, int sc, String myclr, String mytpval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return getPieceCanMoveToLocs(sr, sc, myclr, mytpval, ignorelist, addpcs, gid, false);
	}
	
	
	//asks can piece at loc move around to another location other than the current location
	//if no piece is at the loc returns false
	public static boolean isPieceAtLocFreeToMoveAround(int sr, int sc, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid, boolean nocsling)
	{
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece cp = getPieceAt(sr, sc, allpcs);
		//System.out.println("sr = " + sr);
		//System.out.println("sc = " + sc);
		//System.out.println("cp = " + cp);
		if (cp == null) return false;
		else
		{
			int[][] mvlocs = getPieceCanMoveToLocs(sr, sc, cp.getColor(), cp.getType(), ignorelist, addpcs, gid, nocsling);
			if (mvlocs == null || mvlocs.length < 1)
			{
				//System.out.println("MOVELOCS IS EMPTY!");
				return false;
			}
			else
			{
				//System.out.println("mvlocs.length = " + mvlocs.length);
				if (mvlocs.length == 1)
				{
					//System.out.println("mvlocs[0][0] = " + mvlocs[0][0]);
					//System.out.println("mvlocs[0][1] = " + mvlocs[0][1]);
					if (mvlocs[0][0] == sr && mvlocs[0][1] == sc) return false;
					//else;//do nothing
				}
				//else
				//{
					//for (int x = 0; x < mvlocs.length; x++)
					//{
					//	System.out.println("mvlocs[" + x + "][0] = " + mvlocs[x][0]);
					//	System.out.println("mvlocs[" + x + "][1] = " + mvlocs[x][1]);
					//}
				//}
				return true;
			}
		}
	}
	public static boolean isPieceAtLocFreeToMoveAround(int sr, int sc, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return isPieceAtLocFreeToMoveAround(sr, sc, ignorelist, addpcs, gid, false);
	}
	
	public static ArrayList<ChessPiece> getPiecesThatAreFreeToMove(int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid, boolean nocsling)
	{
		//they can move to a location other than the current location it is on
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		if (getNumItemsInList(allpcs) < 1) return null;
		else
		{
			ArrayList<ChessPiece> fpcs = null;
			for (int x = 0; x < allpcs.size(); x++)
			{
				if (isPieceAtLocFreeToMoveAround(allpcs.get(x).getRow(), allpcs.get(x).getCol(),
					ignorelist, addpcs, gid, nocsling))
				{
					//add to list
					
					if (fpcs == null) fpcs = new ArrayList<ChessPiece>();
					//else;//do nothing
					
					fpcs.add(allpcs.get(x));
				}
				//else;//do nothing
			}
			return fpcs;
		}
	}
	public static ArrayList<ChessPiece> getPiecesThatAreFreeToMove(int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return getPiecesThatAreFreeToMove(ignorelist, addpcs, gid, false);
	}
	
	public static int[][] getPieceMoveToLocsForLocs(int[][] smvlocs, String mytpval, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (smvlocs == null || smvlocs.length < 1) return null;
		
		//for each location on the smvlocs list get the moveto locs and combine them all then return the list
		int[][][] tempmvlocs = new int[smvlocs.length][2][];
		int numadded = 0;
		for (int x = 0; x < smvlocs.length; x++)
		{
			int[][] mvlocs = getPieceCanMoveToLocs(smvlocs[x][0], smvlocs[x][1], myclr, mytpval,
				ignorelist, addpcs, gid, true);
			if (mvlocs == null || mvlocs.length < 1) tempmvlocs[x] = null;
			else
			{
				tempmvlocs[x][0] = new int[mvlocs.length];
				tempmvlocs[x][1] = new int[mvlocs.length];
				for (int c = 0; c < mvlocs.length; c++)
				{
					//System.out.println("mvlocs[" + c + "][0] = " + mvlocs[c][0]);
					//System.out.println("mvlocs[" + c + "][1] = " + mvlocs[c][1]);
					tempmvlocs[x][0][c] = mvlocs[c][0];
					tempmvlocs[x][1][c] = mvlocs[c][1];
				}
				numadded += mvlocs.length;
			}
		}
		
		int[][] rmvlocs = new int[numadded][2];
		for (int x = 0; x < numadded; x++)
		{
			rmvlocs[x][0] = -1;
			rmvlocs[x][1] = -1;
		}
		int mvi = 0;
		for (int x = 0; x < smvlocs.length; x++)
		{
			if (tempmvlocs[x] == null);
			else
			{
				for (int c = 0; c < tempmvlocs[x][0].length; c++)
				{
					if (isLocOnListOfLocs(tempmvlocs[x][0][c], tempmvlocs[x][1][c], rmvlocs));
					else
					{
						rmvlocs[mvi][0] = tempmvlocs[x][0][c];
						rmvlocs[mvi][1] = tempmvlocs[x][1][c];
						mvi++;
					}
				}
			}
		}
		//System.out.println("mvi = " + mvi);
		
		int[][] rlistmvlocs = new int[mvi][2];
		for (int x = 0; x < mvi; x++)
		{
			rlistmvlocs[x][0] = rmvlocs[x][0];
			rlistmvlocs[x][1] = rmvlocs[x][1];
		}
		//printLocsArray(rlistmvlocs, "rlistmvlocs");
		return rlistmvlocs;
	}
	
	public static int[][] getAllLocsThatCanBeReachedByPiece(int sr, int sc, String mytpval, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid, int[][] vlocs)
	{
		//what if our location is already on the vlocs list? need to stop the recursion
		
		//get the piece can move to locations no castling
		//if there are no locations we can move to, then what?
		//
		//if there are locations we can move to, then what?
		//we want to add all of these locations on the rlist of course...
		//we go through all of the locations and for each location:
		//(D7 - F7 and D8 - F8 inclusive on both)
		//where can they go?
		//-on the calling vlist will be the starting location that called it
		//-then we get all the possible moveto locs that these can move to
		//-then we add it to the return list provided it is not already on it
		//
		//  A   B   C   D   E   F   G   H  
		//|---|---|---|---|BKG|---|---|---| 1
		//|---|---|---|---|---|---|---|---| 2
		//|---|---|---|---|---|---|---|---| 3
		//|BPN|---|BPN|---|BPN|---|BPN|---| 4
		//|WPN|---|WPN|---|WPN|---|WPN|---| 5
		//|-4-|-3-|-2-|-2-|-2-|-2-|-2-|-3-| 6
		//|-4-|-3-|-2-|-1-|-1-|-1-|-2-|-3-| 7
		//|-4-|-3-|-2-|-1-|WKG|-1-|-2-|-3-| 8
		//
		//take the initial starting location and get the move to locs for it
		//now take each of the locations (1s) and get their unique move to locs for it
		//now take each of those locations (2s) and get their unique move to locs for it
		//now take each of those locations (3s) and get their unique move to locs for it
		//now take each of those locations (4s) and get their unique move to locs for it
		//repeat until cannot add any new unique locations...
		
		//if we visit all the locations we can move to first, then the starting location will be on the vlocs
		//do not add all locations on vlocs
		//add locations that are not on vlocs
		//the return list will be the vlocs + mvlocs not on vlocs
			
		int[][] mvlocs = getPieceCanMoveToLocs(sr, sc, myclr, mytpval, ignorelist, addpcs, gid, true);
		//if no mvlocs return vlist
		if (mvlocs == null || mvlocs.length < 1) return null;
		else
		{
			//if all of mvlocs are on the vlist return the vlist
			if (vlocs == null || vlocs.length < 1);
			else
			{
				boolean allonit = true;
				for (int x = 0; x < mvlocs.length; x++)
				{
					boolean fndit = false;
					for (int c = 0; c < vlocs.length; c++)
					{
						if (vlocs[c][0] == mvlocs[x][0] &&
							vlocs[c][1] == mvlocs[x][1])
						{
							fndit = true;
							break;
						}
						//else;//do nothing
					}
					if (fndit);
					else
					{
						allonit = false;
						break;
					}
				}
				if (allonit) return null;
				//else;//do nothing
			}
		}
		
		//now determine the unique move to locs that this offers...
		//keep getting it as long as size keeps increasing
		int prevsz = 0;
		int[][] mymvlocs = getPieceMoveToLocsForLocs(mvlocs, mytpval, myclr, ignorelist, addpcs, gid);
		//System.out.println("INIT prevsz = " + prevsz);
		//printLocsArray(mymvlocs, "lvtwomvlocs");
		
		if (mymvlocs == null);
		else
		{
			while(prevsz < mymvlocs.length)
			{
				prevsz = mymvlocs.length;
				//System.out.println("NEW prevsz = " + prevsz);
				
				mymvlocs = getPieceMoveToLocsForLocs(mymvlocs, mytpval, myclr, ignorelist, addpcs, gid);
				//printLocsArray(mymvlocs, "mymvlocs");
			}//end of while loop
		}
		
		//System.out.println("STARTING LOCATION IS " + getLocStringAndConvertIt(sr, sc));
		//printLocsArray(mymvlocs, "FINAL mymvlocs");
		return mymvlocs;
	}
	public static int[][] getAllLocsThatCanBeReachedByPiece(int sr, int sc, String mytpval, String myclr,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return getAllLocsThatCanBeReachedByPiece(sr, sc, mytpval, myclr, ignorelist, addpcs, gid, null);
	}
	public int[][] getAllLocsThatCanBeReachedByPiece(int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return getAllLocsThatCanBeReachedByPiece(getRow(), getCol(), getType(), getColor(), ignorelist, addpcs, getGameID());
	}
	
	public static int[][] getAllLocsThatCanBeReachedBySide(String clrval, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		//gets all the pieces for a side...
		//get all of their move to locations for each piece
		//then save a list of all of the unique locations
		ArrayList<ChessPiece> allmypcs = getCurrentSidePieces(clrval, gid, ignorelist, addpcs);
		int numallmypcs = getNumItemsInList(allmypcs);
		if (numallmypcs < 1) throw new IllegalStateException("there must be at least a king on one side!");
		//else;//do nothing
		int[][] tmplocslist = new int[64][2];
		for (int x = 0; x < 64; x++)
		{
			tmplocslist[x][0] = -1;
			tmplocslist[x][1] = -1;
		}
		int rszi = 0;
		for (int x = 0; x < numallmypcs; x++)
		{
			int[][] pcmvlocs = allmypcs.get(x).getAllLocsThatCanBeReachedByPiece(ignorelist, addpcs);
			if (pcmvlocs == null || pcmvlocs.length < 1);
			else
			{
				//add these to the rlist
				for (int c = 0; c < pcmvlocs.length; c++)
				{
					if (isLocOnListOfLocs(pcmvlocs[c][0], pcmvlocs[c][1], tmplocslist));
					else
					{
						tmplocslist[rszi][0] = pcmvlocs[c][0];
						tmplocslist[rszi][1] = pcmvlocs[c][1];
						rszi++;
					}
				}
			}
		}
		int[][] rlist = new int[rszi][2];
		for (int x = 0; x < rszi; x++)
		{
			rlist[x][0] = tmplocslist[x][0];
			rlist[x][1] = tmplocslist[x][1];
		}
		return rlist;
	}
	
	public boolean isMoveToASpecialMove(int nrval, int ncval, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		String[] tpsnospcmvs = {"QUEEN", "BISHOP", "KNIGHT", "CASTLE", "ROOK"};
		if (itemIsOnGivenList(getType(), tpsnospcmvs)) return false;
		else
		{
			int[][] allpclocs = getPieceCanMoveToLocs(getRow(), getCol(), getColor(), getType(), ignorelist, addpcs,
				getGameID(), false);
			int[][] normalpclocs = null;
			if (getType().equals("KING"))
			{
				normalpclocs = getKingCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
			}
			else
			{
				//pawn
				normalpclocs = getPawnCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
			}
			boolean onnrml = false;
			boolean onall = false;
			if (normalpclocs == null || normalpclocs.length < 1);
			else
			{
				for (int x = 0; x < normalpclocs.length; x++)
				{
					if (normalpclocs[x][0] == nrval && normalpclocs[x][1] == ncval)
					{
						onnrml = true;
						break;
					}
					//else;//do nothing
				}
			}
			if (allpclocs == null || allpclocs.length < 1);
			else
			{
				for (int x = 0; x < allpclocs.length; x++)
				{
					if (allpclocs[x][0] == nrval && allpclocs[x][1] == ncval)
					{
						onall = true;
						break;
					}
					//else;//do nothing
				}
			}
			//to be special it must be on all, but not on normal
			//if it is on all and on normal, then it is normal
			//if it is not on either it is not valid
			return (onall && !onnrml);
		}
	}
	
	
	//NOTE: TAKES INTO ACCOUNT PAWNING WHEN CALLED ON PAWN ONLY, TAKES INTO ACCOUNT CASTLING WHEN CALLED ON KING ONLY,
	//DOES NOT TAKE INTO ACCOUNT WHOSE TURN IT IS
	//TAKES INTO ACCOUNT WHAT THE NEW BOARD LOOKS LIKE, BUT REALLY SHOULD NOT
	public boolean canMoveTo(int rval, int cval, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		if (isvalidrorc(rval) && isvalidrorc(cval));
		else return false;
		//use current location, piece type, if side is in check or not, and opposing piece locations
		//to determine where I can move or if I can move at all.
		
		int[][] locs = null;
		if (getType().equals("BISHOP"))
		{
			//on diagnals only
			locs = getBishopCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
		}
		else if (getType().equals("CASTLE") || getType().equals("ROOK"))
		{
			//on same row or col only
			//can castle if the other pieces between the castle and the king are not there and if not in check
			//and if neither castle nor king have moved
			locs = getCastleCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
		}
		else if (getType().equals("QUEEN"))
		{
			//diagnals and on same row or same col
			locs = getQueenCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
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
			locs = getKnightCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
		}
		else if (getType().equals("PAWN"))
		{
			//can only move forward or diagnal one space to attack
			//if it is the first move, can move forward two spaces
			//in passing or EN PASSANT is a form of attack
			//you can only pawn a pawn that has made its first move
			locs = getAllPawnCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
		}
		else if (getType().equals("KING"))
		{
			//1 in any direction provided move does not put king in check
			//if in check and king cannot move without being put into check, see if another piece can block it
			//if the king cannot get out of check -> checkmate other side wins.
			//if the king cannot move, but must move -> stalemate tie.
			locs = getAllKingCanMoveToLocs(getRow(), getCol(), getColor(), ignorelist, addpcs, getGameID());
		}
		else throw new IllegalStateException("ILLEGAL TYPE FOUND AND USED HERE!");
		if (locs == null || locs.length < 1)
		{
			//System.out.println("LOCS LIST IS EMPTY!");
			return false;
		}
		else
		{
			for (int x = 0; x < locs.length; x++)
			{
				if (locs[x][0] == rval && locs[x][1] == cval) return true;
				//else;//do nothing
			}
		}
		//System.out.println("LOC " + getLocString(rval, cval) + " NOT FOUND ON THE LIST!");
		return false;
	}
	public boolean canMoveToLoc(int rval, int cval, int[][] ignorelist)
	{
		return canMoveTo(rval, cval, ignorelist, null);
	}
	public boolean canMoveToLoc(int rval, int cval)
	{
		return canMoveTo(rval, cval, null, null);
	}
	public boolean canMoveToLoc(int[] nloc)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		else return canMoveToLoc(nloc[0], nloc[1]);
	}
	
	
	//CHECKMATE METHODS
	
	//is color side in checkmate
	public static boolean inCheckmate(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//KING MUST BE IN CHECK
		//KING CANNOT MOVE OUT OF CHECK
		//THE SIDE WHO'S KING IS IN CHECK CANNOT BLOCK IT BY MOVING A PIECE IN FRONT OF IT
		//THE SIDE WHO'S KING IS IN CHECK CANNOT BLOCK IT BY KILLING THE PIECE(S) CHECKING THE KING
		
		ChessPiece mkg = getCurrentSideKing(clrval, combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
		if (mkg == null) throw new IllegalStateException("the king must be found!");
		//else;//do nothing
		
		//can I be directly attacked by the opposing side?
		ArrayList<ChessPiece> epcs = getEnemyPiecesGuardingLocation(mkg.getRow(), mkg.getCol(), gid, clrval,
			ignorelist, addpcs);
		//System.out.println("epcs = " + epcs);
		//is in check
		if (getNumItemsInList(epcs) < 1) return false;//not in check so not in checkmate
		//else;//do nothing my king is in check now need to determine if it is checkmate
		System.out.println("" + clrval + " KING IS IN CHECK!");
		
		//need to know if this king is free to move or rather can move somewhere other than the current location
		if (isPieceAtLocFreeToMoveAround(mkg.getRow(), mkg.getCol(), ignorelist, addpcs, gid, true)) return false;
		//can move out of check
		//else;//do nothing still in check
		System.out.println("" + clrval + " KING CANNOT MOVE OUT OF CHECK!");
		
		//can check be blocked
		//does side have no legal moves
		//if there is a legal move other than staying where we are, then it blocks check somehow
		
		ArrayList<ChessPiece> fpcs = getPiecesThatAreFreeToMove(ignorelist, addpcs, gid, true);
		//System.out.println("fpcs = " + fpcs);
		
		ArrayList<ChessPiece> myclrfpcs = filterListByColor(fpcs, clrval);
		//System.out.println("myclrfpcs = " + myclrfpcs);
		
		if (getNumItemsInList(myclrfpcs) < 1)
		{
			System.out.println("" + clrval + " HAS NO FREE PIECES! IT CANNOT BLOCK CHECK! IT IS CHECKMATE! " +
				getOppositeColor(clrval) + " WINS!");
			return true;
		}
		//else;//do nothing might be able to block check
		
		for (int x = 0; x < myclrfpcs.size(); x++)
		{
			//System.out.println("myclrfpcs.get(" + x + ") = " + myclrfpcs.get(x));
			
			int[][] pcmvlocs = getPieceCanMoveToLocs(myclrfpcs.get(x).getRow(), myclrfpcs.get(x).getCol(), clrval,
				myclrfpcs.get(x).getType(), ignorelist, addpcs, gid, true);
			//printLocsArray(pcmvlocs, "pcmvlocs");
			
			//determine where the piece can move to block check... if it indeed does block check
			if (myclrfpcs.get(x).getType().equals("KING"))
			{
				throw new IllegalStateException("the king cannot move out of check, now it says it can!");
			}
			else
			{
				if (1 < pcmvlocs.length)
				{
					System.out.println("AT LEAST ONE PIECE ON THE " + clrval + " SIDE CAN BLOCK CHECK!");
					return false;
				}
				//else;//do nothing
			}
		}//end of x for loop
		
		System.out.println("" + clrval + " CANNOT BLOCK CHECK WITH ITS FREE PIECES! IT IS CHECKMATE! " +
			getOppositeColor(clrval) + " WINS!");
		return true;
	}
	//is white in checkmate
	public static boolean inCheckmateWhite(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return inCheckmate("WHITE", ignorelist, addpcs, gid);
	}
	//is black in checkmate
	public static boolean inCheckmateBlack(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return inCheckmate("BLACK", ignorelist, addpcs, gid);
	}
	
	
	//STALEMATE METHODS
	
	//returns true if less than 2 pieces of that type are on the board
	public static boolean areAllOfTypeOnSameColorSquare(String typeval, ArrayList<ChessPiece> allpcs)
	{
		ArrayList<ChessPiece> bps = getAllOfType(typeval, allpcs);
		if (getNumItemsInList(bps) < 2) return true;
		else
		{
			String myfbpclr = getColorOfLoc(bps.get(0));
			for (int x = 1; x < bps.size(); x++)
			{
				if (getColorOfLoc(bps.get(x)).equals(myfbpclr));
				else return false;
			}
			return true;
		}
	}
	
	public static boolean areAllBishopsOnSameColorSquare(ArrayList<ChessPiece> allpcs)
	{
		return areAllOfTypeOnSameColorSquare("BISHOP", allpcs);
	}
	public static boolean areAllBishopsOnSameColorSquare(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return areAllBishopsOnSameColorSquare(combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
	}
	public static boolean areAllBishopsOnSameColorSquare(int gid)
	{
		return areAllBishopsOnSameColorSquare(getAllPiecesWithGameID(gid));
	}
	public boolean areAllBishopsOnSameColorSquare()
	{
		return areAllBishopsOnSameColorSquare(getGameID());
	}
	
	public static boolean isAutoStalemate(ArrayList<ChessPiece> allpcs)
	{
		//if we have just 2 kings -> yes
		//if we have a king and a bishop vs a king -> yes
		//if we have just 2 kings and bishops and bishops are all on the same color squares -> yes
		//if we have a king and a knight vs a king -> yes
		
		//STALEMATE, BUT NOT AUTO STALEMATE:
		//if we 2 kings and a bunch of pawns all blocking each other, but cannot capture each other -> yes
		//if no piece can kill an enemy piece to free up check-mating pieces (pawn, castle, or a queen) -> yes
		//if one side has no legal moves if not checkmate and it is their turn -> yes
		//if checkmate is not possible -> yes
		
		//CHECKMATE IS POSSIBLE:
		//king and 2 bishops (provided bishops are on different color squares) vs king
		//king and 2 knights vs king
		//if we have a king a knight or bishop vs a king and knight or bishop
		//king and queen vs king
		//king and castle vs king
		//checkmate is more likely than stalemate to occur with more pieces in general
		
		ArrayList<ChessPiece> wpcs = getCurrentSidePieces("WHITE", allpcs);
		ArrayList<ChessPiece> bpcs = getCurrentSidePieces("BLACK", allpcs);
		String[] wpcstps = getPieceTypes(wpcs);
		String[] bpcstps = getPieceTypes(bpcs);
		//king, queen, castle (rook), bishop, knight, pawn
		int[] wpccnts = getCountsForEachPieceTypeForASide(wpcstps);
		int[] bpccnts = getCountsForEachPieceTypeForASide(bpcstps);
		int numwkgs = getCountForPieceTypeForASide(wpccnts, "KING");
		int numbkgs = getCountForPieceTypeForASide(bpccnts, "KING");
		int numwbps = getCountForPieceTypeForASide(wpccnts, "BISHOP");
		int numbbps = getCountForPieceTypeForASide(bpccnts, "BISHOP");
		int numwcs = getCountForPieceTypeForASide(wpccnts, "CASTLE");
		int numbcs = getCountForPieceTypeForASide(bpccnts, "CASTLE");
		int numwqs = getCountForPieceTypeForASide(wpccnts, "QUEEN");
		int numbqs = getCountForPieceTypeForASide(bpccnts, "QUEEN");
		int numwkts = getCountForPieceTypeForASide(wpccnts, "KNIGHT");
		int numbkts = getCountForPieceTypeForASide(bpccnts, "KNIGHT");
		int numwps = getCountForPieceTypeForASide(wpccnts, "PAWN");
		int numbps = getCountForPieceTypeForASide(bpccnts, "PAWN");
		if (numwkgs == 1 && numbkgs == 1);
		else throw new IllegalStateException("invalid number of kings on the board!");
		//if there is a castle, a pawn, or a queen on the board: not an automatic stalemate
		if (0 < numwqs || 0 < numbqs || 0 < numwps || 0 < numbps || 0 < numwcs || 0 < numbcs) return false;
		//else;//do nothing this might be an automatic stalemate
		//is king vs king -> yes
		boolean kgvskg = (numwkgs == 1 && numbkgs == 1 && numwbps < 1 && numbbps < 1 && numwcs < 1 && numbcs < 1 &&
			numwqs < 1 && numbqs < 1 && numwkts < 1 && numbkts < 1 && numwps < 1 && numbps < 1);
		if (kgvskg) return true;
		//is king vs king and knight -> yes
		boolean kgvskgandkt = (numwkgs == 1 && numbkgs == 1 && ((numwkts == 1 && numbkts < 1) ||
			(numbkts == 1 && numwkts < 1)) && numwps < 1 && numbps < 1 && numwqs < 1 && numbqs < 1 && numwbps < 1 &&
			numbbps < 1 && numwcs < 1 && numbcs < 1);
		if (kgvskgandkt) return true;
		//king and any number of bishops vs king and any number of bishops provided all bishops are on same color square
		boolean kgsandbps = (numwkgs == 1 && numbkgs == 1 && numwcs < 1 && numbcs < 1 && numwqs < 1 && numbqs < 1 &&
			numwps < 1 && numbps < 1 && numwkts < 1 && numbkts < 1);
		if (kgsandbps && areAllBishopsOnSameColorSquare(allpcs)) return true;
		else return false;
	}
	public static boolean isAutoStalemate(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return isAutoStalemate(combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid));
	}
	
	//can an entire side not move
	public static boolean doesSideHaveNoLegalMoves(String clrval, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		ArrayList<ChessPiece> fpcs = getPiecesThatAreFreeToMove(ignorelist, addpcs, gid, true);
		//System.out.println("fpcs = " + fpcs);
		
		ArrayList<ChessPiece> myclrfpcs = filterListByColor(fpcs, clrval);
		//System.out.println("myclrfpcs = " + myclrfpcs);
		
		if (getNumItemsInList(myclrfpcs) < 1)
		{
			System.out.println("" + clrval + " HAS NO FREE PIECES! IT HAS NO LEGAL MOVES IT CAN MAKE! STALEMATE!");
			return true;
		}
		else return false;
	}
	public static boolean doesWhiteHaveNoLegalMoves(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return doesSideHaveNoLegalMoves("WHITE", ignorelist, addpcs, gid);
	}
	public static boolean doesBlackHaveNoLegalMoves(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return doesSideHaveNoLegalMoves("BLACK", ignorelist, addpcs, gid);
	}
	
	//how to determine if a situation comes down to just the free pieces?
	//how to determine if a free piece is able to capture an enemy piece through a series of legal moves?
	//-to move a piece on one board only, I could call setLoc which does not register the moves
	//-determine all possible places a piece can get from a certain starting location
	//-then from each of those locations see where it can get and add those locs to the list
	//-ignore castling to speed it up
	//-once the list is complete IE you cannot get to a new location because it is already on the list -> done
	//-now check and see if any enemy pieces are on those locations
	//if it does come down to just the free pieces, and those free pieces generate auto-stalemate -> yes
	//if an entire side cannot move and it is their turn and not checkmate -> yes
	
	//this asks is it possible for a specific side to capture an enemy piece (if the enemy stays in their current positions)
	public static boolean canSideCaptureAPieceIfEnemyStaysSame(String sideclrtomv, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		//if we can move to an enemy piece's square then yes a capture is possible
		//we need to take our free pieces for a side and see if they can make a capture
		//we can do this by checking where each piece can possibly move to
		//if enemy pieces reside at at least one location a capture is possible and not a stalemate.
		
		ArrayList<ChessPiece> myfpcs = filterListByColor(
			getPiecesThatAreFreeToMove(ignorelist, addpcs, gid, true), sideclrtomv);
		int numfpcs = getNumItemsInList(myfpcs);
		if (numfpcs < 1)
		{
			//System.out.println("sideclrtomv = " + sideclrtomv);
			//throw new IllegalStateException("the side has legal moves, that means that there is at least one piece " +
			//	"that is free, but none were found!");
			return false;
		}
		//else;//do nothing
		
		ArrayList<ChessPiece> allepcs = getOpposingSidePieces(sideclrtomv, gid, ignorelist, addpcs);
		int[][] epclocs = getLocsFromPieceList(allepcs);
		//printLocsArray(epclocs, "epclocs");
		//System.out.println();
		//System.out.println("MY SIDE PIECES CAN MOVE TO:");
		for (int x = 0; x < numfpcs; x++)
		{
			int[][] allpossiblemvlocsforpc = myfpcs.get(x).getAllLocsThatCanBeReachedByPiece(ignorelist, addpcs);
			//System.out.println("myfpcs.get(" + x + ") = " + myfpcs.get(x));
			//printLocsArray(allpossiblemvlocsforpc, "allpossiblemvlocsforpc");
			if (allpossiblemvlocsforpc == null || allpossiblemvlocsforpc.length < 2)
			{
				throw new IllegalStateException("the piece was free meaning it has more than one location " +
					"it can move to, but now it claims it cannot move!");
			}
			else
			{
				for (int r = 0; r < allpossiblemvlocsforpc.length; r++)
				{
					for (int c = 0; c < epclocs.length; c++)
					{
						if (allpossiblemvlocsforpc[r][0] == epclocs[c][0] &&
							allpossiblemvlocsforpc[r][0] == epclocs[c][1])
						{
							//it is possible to kill an enemy piece, therefore not a stalemate
							//System.out.println("A MATCH IS FOUND!");
							return true;
						}
						//else;//do nothing
					}//end of c for loop
				}//end of r for loop
			}
		}//end of x for loop
		//System.out.println("NO MATCHES FOUND!");
		return false;
	}
	
	//this asks is a capture possible starting with the given color, then it uses the opposite color
	//white is passed in by default for the color, so white then black or black then white
	public static boolean canASideCaptureAPieceIfEnemyStaysSame(String sideclrtomv, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return (canSideCaptureAPieceIfEnemyStaysSame(sideclrtomv, ignorelist, addpcs, gid) ||
			canSideCaptureAPieceIfEnemyStaysSame(getOppositeColor(sideclrtomv), ignorelist, addpcs, gid));
	}
	public static boolean canASideCaptureAPieceIfEnemyStaysSame(int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return canASideCaptureAPieceIfEnemyStaysSame("WHITE", ignorelist, addpcs, gid);
	}
	
	//this asks is it possible for both sides to move to a common location (this assumes that both sides move)
	public static boolean isACapturePossible(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		int[][] wmvlocs = getAllLocsThatCanBeReachedBySide("WHITE", ignorelist, addpcs, gid);
		//printLocsArray(wmvlocs, "wmvlocs");
		int[][] bmvlocs = getAllLocsThatCanBeReachedBySide("BLACK", ignorelist, addpcs, gid);
		//printLocsArray(bmvlocs, "bmvlocs");
		if (wmvlocs == null || wmvlocs.length < 1 || bmvlocs == null || bmvlocs.length < 1) return true;//not sure
		//else;//do nothing
		for (int x = 0; x < wmvlocs.length; x++)
		{
			for (int c = 0; c < bmvlocs.length; c++)
			{
				if (wmvlocs[x][0] == bmvlocs[c][0] &&
					wmvlocs[x][1] == bmvlocs[c][1])
				{
					//System.out.println("THE FIRST CAPTURE LOC FOUND IS: " +
					//	getLocStringAndConvertIt(wmvlocs[x][0], wmvlocs[x][1]));
					return true;
				}
				//else;//do nothing
			}
		}
		return false;
	}
	
	
	//MAIN STALEMATE METHODS
	
	//is stalemate side's color's turn to move
	public static boolean isStalemate(String sideclrtomv, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		//checks to see if both sides are in check starting with the color given then it will check the opposite color
		if (isASideInCheck(sideclrtomv, ignorelist, addpcs, gid))
		{
			System.out.println("ONE SIDE IS IN CHECK! SO NO STALEMATE!");
			return false;
		}
		//else;//do nothing
		System.out.println("NO SIDE IS IN CHECK!");
		
		if (isAutoStalemate(ignorelist, addpcs, gid) ||
			doesSideHaveNoLegalMoves(sideclrtomv, ignorelist, addpcs, gid))
		{
			System.out.println("EITHER THERE ARE NOT ENOUGH PIECES OR THE SIDE WHO IS SUPPOSED TO MOVE CANNOT! " +
				"SO STALEMATE!");
			return true;
		}
		//else;//do nothing
		System.out.println("THERE ARE ENOUGH PIECES ON THE BOARD! A CAPTURE MIGHT BE POSSIBLE!");
		
		//if it is not possible to make a capture, then the game cannot end in checkmate -> yes it is a stalemate
		//we need to take our free pieces for a side and see if they can make a capture
		//we can do this by checking where each piece can possibly move to
		//if enemy pieces reside at at least one location a capture is possible and not a stalemate.
		
		System.out.println("IS A CAPTURE POSSIBLE: " + isACapturePossible(ignorelist, addpcs, gid));
		
		if (canASideCaptureAPieceIfEnemyStaysSame(sideclrtomv, ignorelist, addpcs, gid))
		{
			System.out.println("IT IS POSSIBLE FOR ONE SIDE TO MAKE A CAPTURE!");
			return false;
		}
		else
		{
			System.out.println("IT IS NOT POSSIBLE FOR ONE SIDE TO MAKE A CAPTURE SO STALEMATE!");
			return true;//cannot capture an enemy piece -> stalemate
		}
	}
	public static boolean isStalemateWhite(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return isStalemate("WHITE", ignorelist, addpcs, gid);
	}
	public static boolean isStalemateBlack(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		return isStalemate("BLACK", ignorelist, addpcs, gid);
	}
	
	
	//SERVER METHODS
	
	public static boolean isDigit(String wd)
	{
		if (wd == null || wd.length() != 1) return false;
		
		String dgts = "0123456789";
		boolean isdgt = false;
		for (int di = 0; di < dgts.length(); di++)
		{
			if (wd.charAt(0) == dgts.charAt(di))
			{
				return true;
			}
			//else;//do nothing
		}
		return false;
	}
	
	//numei is inclusive
	public static int[] getNumStartAndEndIndexs(String wd, int offset)
	{
		if (offset < 0) throw new IllegalStateException("offset MUST BE AT LEAST ZERO (0)!");
		//else;//do nothing
		int numsi = -1;
		int numei = -1;
		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;
		for (int i = 0; i < wd.length(); i++)
		{
			if (isDigit("" + wd.charAt(i)))
			{
				numsi = i;
				break;
			}
			//else;//do nothing
		}
		if (numsi < 0 || wd.length() - 1 < numsi) return res;
		for (int i = numsi; i < wd.length(); i++)
		{
			if (isDigit("" + wd.charAt(i)))
			{
				if (i + 1 < wd.length());
				else if (i + 1 == wd.length())
				{
					numei = wd.length() - 1;
					break;
				}
				else throw new IllegalStateException("ILLEGAL VALUE FOUND AND USED HERE FOR DIGIT INDEX I!");
			}
			else
			{
				numei = i - 1;
				break;
			}
		}
		if (numei < 0 || numei < numsi || wd.length() - 1 < numei)
		{
			throw new IllegalStateException("END NUMBER INDEX NOT SET CORRECTLY!");
		}
		//else;//do nothing
		res[0] = numsi + offset;
		res[1] = numei + offset;
		return res;
	}
	
	public static boolean isANumber(String wd)
	{
		if (wd == null || wd.length() < 1) return false;
		else
		{
			for (int i = 0; i < wd.length(); i++)
			{
				if (isDigit("" + wd.charAt(i)));
				else return false;
			}
			return true;
		}
	}
	
	//NOT DONE YET...
	public static String getShortHandNotationForWord(String wd)
	{
		if (wd == null || wd.length() < 1) return wd;
		else
		{
			String[] myspcs = {" ", "\t", "\n"};
			for (int i = 0; i < wd.length(); i++)
			{
				if (itemIsOnGivenList("" + wd.charAt(i), myspcs))
				{
					throw new IllegalStateException("THE WORD (" + wd +
						") MUST NOT HAVE SPACING CHARACTERS ON IT, BUT IT DID!");
				}
				//else;//do nothing
			}
			if (wd.equals("CREATE")) return "+";
			else if (wd.equals("DELETE")) return "-";
			else if (itemIsOnGivenList(wd, validTypes)) return getShortHandType(wd);
			else if (itemIsOnGivenList(wd, validColors)) return getShortHandColor(wd);
			else if (wd.equals("to:") || wd.equals("TO:")) return "TO";
			else if (wd.equals("WITH") || wd.equals("with")) return "W";
			else if (wd.equals("LEFT") || wd.equals("left")) return "L";
			else if (wd.equals("RIGHT") || wd.equals("right")) return "R";
			else if (wd.equals("move(s)!") || wd.equals("move(s)") || wd.equals("moves!") || wd.equals("moves") ||
				wd.equals("move!") || wd.equals("move"))
			{
				return "MS";
			}
			else if (wd.equals("at:") || wd.equals("AT:") || wd.equals("AT") || wd.equals("at")) return "";
			else if (isANumber(wd)) return wd;
			else
			{
				System.out.println("wd = " + wd);
				System.out.println("NOT SURE WHAT TO DO HERE!");
				return "";
			}
		}
	}
	public static String[] getShortHandMoves(String[] mvs)
	{
		if (mvs == null || mvs.length < 1) return mvs;
		else
		{
			String[] nwmvs = new String[mvs.length];
			for (int c = 0; c < mvs.length; c++)
			{
				String oldmv = "" + mvs[c];
				String nwmv = "";
				int si = 0;
				boolean addstraight = false;
				for (int i = 0; i < oldmv.length(); i++)
				{
					if (oldmv.charAt(i) == ' ' || i + 1 == oldmv.length())
					{
						if (addstraight)
						{
							//System.out.println("HANDLE ADD STRAIGHT HERE:");
							if (i + 1 == oldmv.length()) nwmv += "" + oldmv.substring(si + 1);
							else nwmv += "" + oldmv.substring(si + 1, i);
							addstraight = false;
						}
						else nwmv += "" + getShortHandNotationForWord(oldmv.substring(si, i));
						si = i + 1;
					}
					else if ((oldmv.charAt(i) == 'a' ||  oldmv.charAt(i) == 'A') &&
						(oldmv.charAt(i + 1) == 't' || oldmv.charAt(i + 1) == 'T') && oldmv.charAt(i + 2) == ':' &&
						oldmv.charAt(i + 3) == ' ' && oldmv.charAt(i + 4) != '(')
					{
						si = i + 3;
						addstraight = true;
						i = i + 4;
						//System.out.println("AT: FOUND!");
						//System.out.println("si = " + si);
					}
					else if ((oldmv.charAt(i) == 't' ||  oldmv.charAt(i) == 't') &&
						(oldmv.charAt(i + 1) == 'o' || oldmv.charAt(i + 1) == 'O') && oldmv.charAt(i + 2) == ':' &&
						oldmv.charAt(i + 3) == ' ' && oldmv.charAt(i + 4) != '(')
					{
						si = i + 3;
						addstraight = true;
						i = i + 4;
						//System.out.println("TO: FOUND!");
						//System.out.println("si = " + si);
						nwmv += "TO";
					}
					else if (oldmv.charAt(i) == '(')
					{
						if (oldmv.charAt(i + 1) == 's');
						else
						{
							int cpi = -1;
							for (int k = i + 1; k < oldmv.length(); k++)
							{
								if (oldmv.charAt(k) == ')')
								{
									cpi = k;
									break;
								}
								//else;//do nothing
							}
							if (cpi < 0 || cpi < i + 1 || oldmv.length() - 1 < cpi)
							{
								throw new IllegalStateException("ILLEGAL INDEX (" + cpi +
									") FOUND AND USED FOR THE CLOSING PARENTHESIS INDEX!");
							}
							//else;//do nothing
							int myr = -1;
							int myc = -1;
							//get the numstartindex and numendindex
							//System.out.println("oldmv = " + oldmv);
							//System.out.println("oldmv.substring(" + i + ", " + cpi + ") = " + oldmv.substring(i, cpi));
							
							int[] snumsieis = getNumStartAndEndIndexs(oldmv.substring(i, cpi), i);
							//System.out.println("snumsieis[0] = " + snumsieis[0]);
							//System.out.println("snumsieis[1] = " + snumsieis[1]);
							
							myr = Integer.parseInt(oldmv.substring(snumsieis[0], snumsieis[1] + 1));
							int[] enumsieis = getNumStartAndEndIndexs(oldmv.substring(snumsieis[1] + 1, cpi),
								snumsieis[1] + 1);
							//System.out.println("enumsieis[0] = " + enumsieis[0]);
							//System.out.println("enumsieis[1] = " + enumsieis[1]);
							
							myc = Integer.parseInt(oldmv.substring(enumsieis[0], enumsieis[1] + 1));
							//System.out.println("myr = " + myr);
							//System.out.println("myc = " + myc);
							
							nwmv += convertRowColToStringLoc(myr, myc);//nwmv += "LOC";//
							i = cpi;
							si = cpi + 1;
						}
					}
					//else;//do nothing
				}//end of i for loop
				System.out.println("oldmv = " + oldmv);
				System.out.println("nwmv = " + nwmv);
				nwmvs[c] = "" + nwmv;
			}//end of c for loop
			return nwmvs;
		}
	}
	
	public static String convertShortHandMoveToLongVersion(String mv)
	{
		if (mv == null || mv.length() < 1) throw new IllegalStateException("mv must not be empty or null!");
		//else;//do nothing
		
		String nwmv = "";
		if (mv.charAt(0) == '-') nwmv += "DELETE ";
		else if (mv.charAt(0) == '+') nwmv += "CREATE ";
		else if (mv.charAt(0) == 'W') nwmv += "WHITE ";
		else if (mv.charAt(0) == 'B') nwmv += "BLACK ";
		else throw new IllegalStateException("ILLEGAL STARTING CHARACTER FOR THE MOVE!");
		
		String shtp = null;
		int ei = -1;
		if (mv.charAt(0) == '-' || mv.charAt(0) == '+')
		{
			//next will be color
			if (mv.charAt(1) == 'W') nwmv += "WHITE ";
			else if (mv.charAt(1) == 'B') nwmv += "BLACK ";
			else throw new IllegalStateException("ILLEGAL SECOND CHARACTER FOR THE MOVE!");
			
			//next will be type
			ei = 4;
			shtp = mv.substring(2, 4);
		}
		else
		{
			//type is next
			ei = 3;
			shtp = mv.substring(1, 3);
		}
		nwmv += getLongHandType(shtp) + " at: " + mv.substring(ei, ei + 2) + " ";
		if (mv.charAt(ei + 2) == 'T') nwmv += mv.substring(ei + 2, ei + 4) + ": " + mv.substring(ei + 4);
		else if (mv.charAt(ei + 2) == 'W') nwmv += " with " + mv.substring(ei + 3, mv.length() - 2) + " move(s)!";
		else
		{
			throw new IllegalStateException("ILLEGAL CHARACTER FOUND AT POSITION FAILED TO CONVERT SHORT HAND " +
				"MOVE TO LONG HAND VERSION!");
		}
		System.out.println("mv = " + mv);
		System.out.println("nwmv = " + nwmv);
		return "" + nwmv;
	}
	public static String[] convertAllShortHandMovesToLongVersion(String[] mvs)
	{
		if (mvs == null || mvs.length < 1) return mvs;
		else
		{
			String[] nwmvs = new String[mvs.length];
			for (int x = 0; x < mvs.length; x++)
			{
				nwmvs[x] = convertShortHandMoveToLongVersion(mvs[x]);
			}
			return nwmvs;
		}
	}
	
	public static String[] genPawningMoveToCommand(String clr, int crval, int ccval, int nrval, int ncval,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		//PAWNING NOTATION
		//WHITE LEFT PAWN at: current_loc to: next_loc
		//-BPWN??W?MVS (CAN BE DONE AFTER, BUT SHOULD NOT BE)
		//WLPNB4TOA3 (DISPLAY TO THE USER)
		//
		
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece mpc = getPieceAt(crval, ccval, allpcs);
		
		final boolean useleft = (ncval < ccval);
		
		//make sure we can do this otherwise error out
		if (mpc.canPawnLeftOrRight(useleft, allpcs));
		else throw new IllegalStateException("" + mpc + " CANNOT MOVE TO " + getLocString(nrval, ncval) + "!");
		
		//if command involves adding or removing a piece we need to include that here...
		ChessPiece epc = mpc.getEnemyPawnForLeftOrRightPawning(useleft, allpcs);
		String delcmd = null;
		if (epc == null) throw new IllegalStateException("the enemy pawn must not be null!");
		else
		{
			delcmd = "DELETE " + epc.getColor() + " " + epc.getType() + " at: " +
				convertRowColToStringLoc(epc.getRow(), epc.getCol()) + " with " + epc.getMoveCount() + " move(s)!";
		}
		String dirstr = null;
		if (useleft) dirstr = "LEFT";
		else dirstr = "RIGHT";
		String cmd = "" + clr + " " + dirstr + " PAWN at: " + convertRowColToStringLoc(crval, ccval) + " to: " +
			convertRowColToStringLoc(nrval, ncval);//convertRowColToStringLoc(nrval, ncval)
		//getLocString(crval, ccval);//getLocString(nrval, ncval);//convertRowColToStringLoc(nrval, ncval)
		System.out.println("cmd = " + cmd);
		String[] mvcmd = new String[2];
		mvcmd[0] = "" + delcmd;
		mvcmd[1] = "" + cmd;
		return getShortHandMoves(mvcmd);
	}
	
	public static String[] genCastlingMoveToCommand(String clr, boolean useleft, int gid,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		//WHITE LEFT CASTLE *** USE THIS NOTATION BECAUSE WE CAN GENERATE THE OTHERS
		//WLCE: (DISPLAY TO USER ONLY)
		//WCEA8TOD8
		//WKGE8TOC8
		String dirstr = null;
		if (useleft) dirstr = "LEFT";
		else dirstr = "RIGHT";
		if (canSideCastleLeftOrRight(useleft, clr, ignorelist, addpcs, gid));
		else throw new IllegalStateException("" + clr + " CANNOT CASTLE " + dirstr + "!");
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece mkg = getCurrentSideKing(clr, allpcs);
		int ncol = -1;
		if (useleft) ncol = 0;
		else ncol = 7;
		ChessPiece clcp = getPieceAt(mkg.getRow(), ncol, allpcs);
		int[] ncloc = getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, false, clr, ignorelist, addpcs, gid);
		String ccmvcmd = "" + clr + " CASTLE at: " + convertRowColToStringLoc(clcp.getRow(), clcp.getCol()) + " to: " +
			convertRowColToStringLoc(ncloc[0], ncloc[1]);//convertRowColToStringLoc(nrval, ncval)
		//getLocString(crval, ccval);//getLocString(nrval, ncval)
		System.out.println("ccmvcmd = " + ccmvcmd);
		int[] nkgloc = getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, true, clr, ignorelist, addpcs, gid);
		String kgmvcmd = "" + clr + " KING at: " + convertRowColToStringLoc(mkg.getRow(), mkg.getCol()) + " to: " +
			convertRowColToStringLoc(nkgloc[0], nkgloc[1]);//convertRowColToStringLoc(nrval, ncval)
		//getLocString(crval, ccval);//getLocString(nrval, ncval)
		System.out.println("kgmvcmd = " + kgmvcmd);
		String[] mvcmd = new String[3];
		mvcmd[0] = "" + clr + " " + dirstr + " CASTLE:";
		mvcmd[1] = "" + ccmvcmd;
		mvcmd[2] = "" + kgmvcmd;
		return getShortHandMoves(mvcmd);
	}
	
	public static String[] genMoveToCommand(String clr, String tp, int crval, int ccval, int nrval, int ncval,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs, boolean usecslingasmv)
	{
		//SHORT HAND EXAMPLES
		//WPNA5TOA6
		//WCEA5TOA6
		//WQNA5TOA6
		//WKGA5TOA6
		//WKTA5TOA6 (NOT LEGAL, BUT EXAMPLE ONLY)
		//WBPA5TOA6 (NOT LEGAL, BUT EXAMPLE ONLY)
		
		//SUPPOSE A CAPTURE WERE TO BE MADE LET US SAY A BLACK PAWN IS AT A6 AND WE CAN KILL IT
		//SHORT HAND EXAMPLES
		//-BPNA6W2MS (MUST BE DONE FIRST)
		//WCEA5TOA6 (DISPLAY TO THE USER)
		
		//CASTLING MOVETO NOTATION
		//WHITE LEFT CASTLE at: A8 to: D8 AND KING at: E8 to: C8
		//WLCEA8TOD8ANDKGE8TOC8
		//
		//OR:
		//
		//WHITE LEFT CASTLE *** USE THIS NOTATION BECAUSE WE CAN GENERATE THE OTHERS
		//WLCE: (DISPLAY TO USER ONLY)
		//WCEA8TOD8
		//WKGE8TOC8
		//
		//OR:
		//
		//WLCEA8TOD8
		//WKGE8TOC8
		//
		//WHITE RIGHT CASTLE
		//WRCE:
		//WCEH8TOF8
		//WKGE8TOG8
		//
		//OR:
		//
		//WRCEH8TOF8
		//WKGE8TOG8
		
		
		//PAWNING NOTATION
		//WHITE LEFT PAWN at: current_loc to: next_loc
		//-BPWN??W?MVS (CAN BE DONE AFTER, BUT SHOULD NOT BE)
		//WLPNB4TOA3 (DISPLAY TO THE USER)
		//
		//WHITE RIGHT PAWN at: current_loc to: next_loc
		//-BPWN??W?MVS
		//WRPNB4TOA3
		//
		//BLACK LEFT PAWN at: current_loc to: next_loc
		//-WPWN??W?MVS
		//BLPNB5TOA6
		//
		
		//- FOR DELETE
		//+ FOR CREATE
		ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
		ChessPiece mpc = getPieceAt(crval, ccval, allpcs);
		//cannot handle special moves if called with certain pieces it might recognize a special move is possible
		//to detect a special move, we need to get the generic move set, and the full move set, the difference is the
		//special set
		if (mpc.canMoveTo(nrval, ncval, ignorelist, addpcs))
		{
			if ((mpc.getType().equals("CASTLE") && usecslingasmv) ||
				mpc.isMoveToASpecialMove(nrval, ncval, ignorelist, addpcs))
			{
				System.out.println("MOVE IS A SPECIAL MOVE!");
				if (mpc.getType().equals("KING") || (mpc.getType().equals("CASTLE") && usecslingasmv))
				{
					System.out.println("WE ARE CASTLING!");
					
					boolean useleft = false;
					if (mpc.getType().equals("KING")) useleft = (ncval < ccval);
					else
					{
						//(mpc.getType().equals("CASTLE") && usecslingasmv)
						//we called it on a castle and told it we want to castle
						//there are two castles for each side
						//if the castle is left of the king it will be left
						//if the castle is right of the king it will be right
						ChessPiece mkg = mpc.getMySideKing();
						useleft = (mpc.getCol() < mkg.getCol());
					}
					System.out.println("useleft = " + useleft);
					
					return genCastlingMoveToCommand(clr, useleft, gid, ignorelist, addpcs);
				}
				else if (mpc.getType().equals("PAWN"))
				{
					return genPawningMoveToCommand(clr, crval, ccval, nrval, ncval, gid, ignorelist, addpcs);
				}
				else throw new IllegalStateException("THIS PIECE TYPE (" + mpc.getType() + ") HAS NO SPECIAL MOVES!");
			}
			//else;//do nothing safe to proceed
		}
		else throw new IllegalStateException("" + mpc + " CANNOT MOVE TO " + getLocString(nrval, ncval) + "!");
		
		//if command involves adding or removing a piece we need to include that here...
		ChessPiece ecp = getPieceAt(nrval, ncval, allpcs);
		String delcmd = null;
		boolean usedelcmd = true;
		if (ecp == null) usedelcmd = false;
		else
		{
			delcmd = "DELETE " + ecp.getColor() + " " + ecp.getType() + " at: " + convertRowColToStringLoc(nrval, ncval) +
				" with " + ecp.getMoveCount() + " move(s)!";
		}
		String cmd = "" + clr + " " + tp + " at: " + convertRowColToStringLoc(crval, ccval) + " to: " +
			convertRowColToStringLoc(nrval, ncval);//convertRowColToStringLoc(nrval, ncval)
		//getLocString(crval, ccval);//getLocString(nrval, ncval)
		System.out.println("cmd = " + cmd);
		int mxcnt = 0;
		if (usedelcmd) mxcnt = 2;
		else mxcnt = 1;
		String[] mvcmd = new String[mxcnt];
		if (usedelcmd)
		{
			mvcmd[0] = "" + delcmd;
			mvcmd[1] = "" + cmd;
		}
		else mvcmd[0] = "" + cmd;
		return getShortHandMoves(mvcmd);
		//return convertAllShortHandMovesToLongVersion(getShortHandMoves(mvcmd));
	}
	public static String[] genMoveToCommand(String clr, String tp, int crval, int ccval, int nrval, int ncval,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(clr, tp, crval, ccval, nrval, ncval, gid, ignorelist, addpcs, false);
	}
	public static String[] genMoveToCommand(String clr, String tp, int[] cloc, int[] nloc,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs, boolean usecslingasmv)
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
		
		return genMoveToCommand(clr, tp, cloc[0], cloc[1], nloc[0], nloc[1], gid, ignorelist, addpcs, usecslingasmv);
	}
	public static String[] genMoveToCommand(String clr, String tp, int[] cloc, int[] nloc,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(clr, tp, cloc, nloc, gid, ignorelist, addpcs, false);
	}
	public static String[] genMoveToCommand(ChessPiece cp, int nrval, int ncval,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs, boolean usecslingasmv)
	{
		if (cp == null)
		{
			throw new IllegalStateException("You need to provide the current chess piece location and the new location!");
		}
		else
		{
			return genMoveToCommand(cp.getColor(), cp.getType(), cp.getRow(), cp.getCol(), nrval, ncval,
				gid, ignorelist, addpcs, usecslingasmv);
		}
	}
	public static String[] genMoveToCommand(ChessPiece cp, int nrval, int ncval,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(cp, nrval, ncval, gid, ignorelist, addpcs, false);
	}
	public static String[] genMoveToCommand(ChessPiece cp, int[] nloc,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs, boolean usecslingasmv)
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
		else
		{
			return genMoveToCommand(cp.getColor(), cp.getType(), cp.getRow(), cp.getCol(), nloc[0], nloc[1],
				gid, ignorelist, addpcs, usecslingasmv);
		}
	}
	public static String[] genMoveToCommand(ChessPiece cp, int[] nloc,
		int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(cp, nloc, gid, ignorelist, addpcs, false);
	}
	public String[] genMoveToCommand(int[] nloc, int[][] ignorelist, ArrayList<ChessPiece> addpcs, boolean usecslingasmv)
	{
		if (nloc == null || nloc.length != 2)
		{
			throw new IllegalStateException("You need to provide the next chess piece location!");
		}
		else return genMoveToCommand(this, nloc, getGameID(), ignorelist, addpcs, usecslingasmv);
	}
	public String[] genMoveToCommand(int[] nloc, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(nloc, ignorelist, addpcs, false);
	}
	public String[] genMoveToCommand(int[] nloc, boolean usecslingasmv)
	{
		return genMoveToCommand(nloc, null, null, usecslingasmv);
	}
	public String[] genMoveToCommand(int[] nloc)
	{
		return genMoveToCommand(nloc, false);
	}
	public String[] genMoveToCommand(int nrval, int ncval, int[][] ignorelist, ArrayList<ChessPiece> addpcs,
		boolean usecslingasmv)
	{
		return genMoveToCommand(this, nrval, ncval, getGameID(), ignorelist, addpcs, usecslingasmv);
	}
	public String[] genMoveToCommand(int nrval, int ncval, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
	{
		return genMoveToCommand(nrval, ncval, ignorelist, addpcs, false);
	}
	public String[] genMoveToCommand(int nrval, int ncval, boolean usecslingasmv)
	{
		return genMoveToCommand(nrval, ncval, null, null, usecslingasmv);
	}
	public String[] genMoveToCommand(int nrval, int ncval)
	{
		return genMoveToCommand(nrval, ncval, false);
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
			//System.out.println("ONE SIDE HAS NO PAWNS! THERE MUST BE AT LEAST ONE PAWN ON EACH SIDE NEAR " +
			//	"EACH OTHER TO BE ABLE TO PAWN!");
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
				//System.out.println("THE LOCATION " + getLocString(getRow(), lc) + " IS EMPTY!");
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
	
	
	public ChessPiece getEnemyPawnForLeftOrRightPawning(boolean useleft, ArrayList<ChessPiece> allpcs)
	{
		if (canPawnLeftOrRight(useleft, allpcs))
		{
			int lc = -1;
			if (useleft) lc = getCol() - 1;
			else lc = getCol() + 1;
			if (isvalidrorc(lc));
			else throw new IllegalStateException("we can pawn, so there must be an enemy, but col is invalid!");
			
			ChessPiece ep = getPieceAt(getRow(), lc, allpcs);
			return ep;
		}
		else return null;
	}
	public ChessPiece getEnemyPawnForLeftPawning(ArrayList<ChessPiece> allpcs)
	{
		return getEnemyPawnForLeftOrRightPawning(true, allpcs);
	}
	public ChessPiece getEnemyPawnForRightPawning(ArrayList<ChessPiece> allpcs)
	{
		return getEnemyPawnForLeftOrRightPawning(false, allpcs);
	}
	public ChessPiece getEnemyPawnForLeftPawning()
	{
		return getEnemyPawnForLeftPawning(getAllPiecesWithGameID(getGameID()));
	}
	public ChessPiece getEnemyPawnForRightPawning()
	{
		return getEnemyPawnForRightPawning(getAllPiecesWithGameID(getGameID()));
	}
	
	public int[] getEnemyPawnLeftOrRightLocation(boolean useleft, ArrayList<ChessPiece> allpcs)
	{
		ChessPiece ep = getEnemyPawnForLeftOrRightPawning(useleft, allpcs);
		if (ep == null) return null;
		else
		{
			int[] loc = new int[2];
			loc[0] = ep.getRow();
			loc[1] = ep.getCol();
			return loc;
		}
	}
	public int[] getEnemyPawnLeftLocation(ArrayList<ChessPiece> allpcs)
	{
		return getEnemyPawnLeftOrRightLocation(true, allpcs);
	}
	public int[] getEnemyPawnRightLocation(ArrayList<ChessPiece> allpcs)
	{
		return getEnemyPawnLeftOrRightLocation(false, allpcs);
	}
	public int[] getEnemyPawnLeftLocation()
	{
		return getEnemyPawnLeftLocation(getAllPiecesWithGameID(getGameID()));
	}
	public int[] getEnemyPawnRightLocation()
	{
		return getEnemyPawnRightLocation(getAllPiecesWithGameID(getGameID()));
	}
	
	public int[] getPawnLeftOrRightLocation(boolean useleft, ArrayList<ChessPiece> allpcs)
	{
		if (canPawnLeftOrRight(useleft, allpcs))
		{
			int nr = -1;
			if (getColor().equals("WHITE")) nr = 2;
			else if (getColor().equals("BLACK")) nr = 5;
			else throw new IllegalStateException("PIECE FOUND WITH AN ILLEGAL COLOR FOUND AND USED HERE!");
			int nc = -1;
			if (useleft) nc = getCol() - 1;
			else nc = getCol() + 1;
			if (isvalidrorc(nr) && isvalidrorc(nc));
			else throw new IllegalStateException("SR AND SC MUST BE VALID BECAUSE WE CAN PAWN!");
			int[] loc = new int[2];
			loc[0] = nr;
			loc[1] = nc;
			return loc;
		}
		else return null;
	}
	public int[] getPawnLeftLocation(ArrayList<ChessPiece> allpcs)
	{
		return getPawnLeftOrRightLocation(true, allpcs);
	}
	public int[] getPawnRightLocation(ArrayList<ChessPiece> allpcs)
	{
		return getPawnLeftOrRightLocation(false, allpcs);
	}
	public int[] getPawnLeftLocation()
	{
		return getPawnLeftLocation(getAllPiecesWithGameID(getGameID()));
	}
	public int[] getPawnRightLocation()
	{
		return getPawnRightLocation(getAllPiecesWithGameID(getGameID()));
	}
	
	
	//THIS MAKES THE MOVE, IT INCREMENTS THE MOVE COUNT FOR THE SURVIVING PAWN AND REMOVES THE OTHER ONE ON THIS BOARD ONLY
	//THIS MUST BE CALLED ON THE PAWN THAT CAN PAWN
	public void pawnLeftOrRight(boolean useleft, ArrayList<ChessPiece> allpcs)
	{
		if (canPawnLeftOrRight(useleft, allpcs))
		{
			int[] eploc = getEnemyPawnLeftOrRightLocation(useleft, allpcs);
			removePieceAt(eploc[0], eploc[1], getGameID());
			int[] npnloc = getPawnLeftOrRightLocation(useleft, allpcs);
			setLoc(npnloc[0], npnloc[1]);
			incrementMoveCount();
		}
		else
		{
			String dirstr = null;
			if (useleft) dirstr = "LEFT";
			else dirstr = "RIGHT";
			throw new IllegalStateException("CANNOT PAWN " + dirstr + "!");
		}
	}
	public void pawnLeft(ArrayList<ChessPiece> allpcs)
	{
		pawnLeftOrRight(true, allpcs);
	}
	public void pawnLeft()
	{
		pawnLeft(getAllPiecesWithGameID(getGameID()));
	}
	public void pawnRight(ArrayList<ChessPiece> allpcs)
	{
		pawnLeftOrRight(false, allpcs);
	}
	public void pawnRight()
	{
		pawnRight(getAllPiecesWithGameID(getGameID()));
	}
	
	
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
	
	
	//returns an array with 2 integers both will be invalid if cannot castle that direction
	public static int[] getLeftOrRightCastleSideNewCastleOrKingLoc(boolean useleft, boolean usekg, String clrval,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		int[] myretarr = {-1, -1};
		if (canSideCastleLeftOrRight(useleft, clrval, ignorelist, addpcs, gid))
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
			if (usekg) cdiff = 0;
			//else;//do nothing
			ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
			myretarr[0] = getCurrentSideKing(clrval, allpcs).getRow();
			myretarr[1] = 4 + kdiff + cdiff;
		}
		//else;//do nothing
		return myretarr;
	}
	public static int[] getLeftOrRightCastleSideNewCastleOrKingLoc(boolean useleft, boolean usekg, String clrval, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, usekg, clrval, null, null, gid);
	}
	public static int[] getRightCastleSideNewKingLoc(String clrval, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, true, clrval, ignorelist, addpcs, gid);
	}
	public static int[] getRightCastleSideNewCastleLoc(String clrval, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, false, clrval, ignorelist, addpcs, gid);
	}
	public static int[] getLeftCastleSideNewKingLoc(String clrval, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, true, clrval, ignorelist, addpcs, gid);
	}
	public static int[] getLeftCastleSideNewCastleLoc(String clrval, int[][] ignorelist,
		ArrayList<ChessPiece> addpcs, int gid)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, false, clrval, ignorelist, addpcs, gid);
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
	
	
	//THIS MAKES THE MOVE, AND INCREMENTS THE MOVE COUNT FOR THE KING FOR THE SIDE WHO DID IT ON THIS SIDE OF THE BOARD
	public static void sideCastleLeftOrRight(String clrval, boolean useleft,
		int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		if (canSideCastleLeftOrRight(useleft, clrval, ignorelist, addpcs, gid))
		{
			ArrayList<ChessPiece> allpcs = combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
			ChessPiece mkg = getCurrentSideKing(clrval, allpcs);
			int oc = -1;
			if (useleft) oc = 0;
			else oc = 7;
			ChessPiece csl = getPieceAt(mkg.getRow(), oc, allpcs);
			int[] nwkgloc = getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, true, clrval, ignorelist, addpcs, gid);
			int[] nwcsloc = getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, false, clrval, ignorelist, addpcs, gid);
			mkg.setLoc(nwkgloc[0], nwkgloc[1]);
			csl.setLoc(nwcsloc[0], nwcsloc[1]);
			mkg.incrementMoveCount();
		}
		else throw new IllegalStateException("" + clrval + " CANNOT CASTLE!");
	}
	public static void whiteCastleLeftOrRight(boolean useleft, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		sideCastleLeftOrRight("WHITE", useleft, ignorelist, addpcs, gid);
	}
	public static void whiteCastleLeft(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		whiteCastleLeftOrRight(true, ignorelist, addpcs, gid);
	}
	public static void whiteCastleRight(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		whiteCastleLeftOrRight(false, ignorelist, addpcs, gid);
	}
	public static void blackCastleLeftOrRight(boolean useleft, int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		sideCastleLeftOrRight("BLACK", useleft, ignorelist, addpcs, gid);
	}
	public static void blackCastleLeft(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		blackCastleLeftOrRight(true, ignorelist, addpcs, gid);
	}
	public static void blackCastleRight(int[][] ignorelist, ArrayList<ChessPiece> addpcs, int gid)
	{
		blackCastleLeftOrRight(false, ignorelist, addpcs, gid);
	}
	
	
	//GENERIC TO STRING METHOD FOR THE PIECE
	
	public String toString()
	{
		return "<ChessPiece of Type: " + getType() + " and Color: " + getColor() +
			" at: " + getLocString(getRow(), getCol()) + " of Gender: " + convertGenderValueToString() +
			" on Game ID: " + getGameID() + ">";
	}
}
