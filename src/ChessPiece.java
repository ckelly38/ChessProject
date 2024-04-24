
import java.util.ArrayList;
class ChessPiece {
	private static String[] validTypes = {"PAWN", "CASTLE", "KNIGHT", "BISHOP", "QUEEN", "KING", "ROOK"};
	private static String[] validColors = {"WHITE", "BLACK"};
	private static final int ROWCOLMIN = 0;
	private static final int ROWCOLMAX = 7;
	public static ArrayList<ChessPiece> cps = new ArrayList<ChessPiece>();
	private String type = "";
	private String color = "";
	private int row = -1;
	private int col = -1;
	private int movecount = 0;
	private boolean isfirstmove = true;
	
	public ChessPiece(String tp, String clr, int r, int c)
	{
		if (tp == null || clr == null) throw new IllegalStateException("the given type and color must not be null!");
		setRow(r);
		setCol(c);
		setType(tp.toUpperCase());
		setColor(clr.toUpperCase());
		cps.add(this);
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
	
	public boolean isvalidrorc(int val)
	{
		if (val < this.ROWCOLMIN || this.ROWCOLMAX < val) return false;
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
	
	public static ChessPiece getPieceAt(int rval, int cval)
	{
		for (int x = 0; x < cps.size(); x++)
		{
			if (cps.get(x).getRow() == rval && cps.get(x).getCol() == cval)
			{
				return cps.get(x);
			}
		}
		System.out.println("NO ITEMS FOUND AT: " + getLocString(rval, cval) + "!");
		return null;
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
		if (val == null || val.length() < 1) return false;
		for (int i = 0; i < arr.length; i++) if (val.equals(arr[i])) return true;
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
	
	public static ArrayList<ChessPiece> getCurrentSidePieces(String clrval)
	{
		return filterListByColor(cps, clrval);
	}
	public static ArrayList<ChessPiece> getOpposingSidePieces(String clrval)
	{
		if (clrval.equals("WHITE")) return getCurrentSidePieces("BLACK");
		else return getCurrentSidePieces("WHITE");
	}
	public ArrayList<ChessPiece> getMySidePieces()
	{
		return getCurrentSidePieces(getColor());
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
	
	public static ArrayList<ChessPiece> getAllOfType(String typeval)
	{
		return filterListByType(cps, typeval);
	}
	public static ArrayList<ChessPiece> getAllKings()
	{
		return getAllOfType("KING");
	}
	public static ArrayList<ChessPiece> getAllCastles()
	{
		return getAllOfType("CASTLE");
	}
	public static ArrayList<ChessPiece> getAllRooks()
	{
		return getAllOfType("CASTLE");
	}
	public static ArrayList<ChessPiece> getAllQueens()
	{
		return getAllOfType("QUEEN");
	}
	public static ArrayList<ChessPiece> getAllKnights()
	{
		return getAllOfType("KNIGHT");
	}
	public static ArrayList<ChessPiece> getAllBishops()
	{
		return getAllOfType("BISHOP");
	}
	public static ArrayList<ChessPiece> getAllPawns()
	{
		return getAllOfType("PAWN");
	}
	
	public static ArrayList<ChessPiece> getAllKnightsOfColor(String clrval)
	{
		return filterListByColor(getAllKnights(), clrval);
	}
	public static ArrayList<ChessPiece> getAllKingsOfColor(String clrval)
	{
		return filterListByColor(getAllKings(), clrval);
	}
	public static ArrayList<ChessPiece> getAllCastlesOfColor(String clrval)
	{
		return filterListByColor(getAllCastles(), clrval);
	}
	public static ArrayList<ChessPiece> getAllRooksOfColor(String clrval)
	{
		return getAllCastlesOfColor(clrval);
	}
	public static ArrayList<ChessPiece> getAllQueensOfColor(String clrval)
	{
		return filterListByColor(getAllQueens(), clrval);
	}
	public static ArrayList<ChessPiece> getAllBishopsOfColor(String clrval)
	{
		return filterListByColor(getAllBishops(), clrval);
	}
	public static ArrayList<ChessPiece> getAllPawnsOfColor(String clrval)
	{
		return filterListByColor(getAllPawns(), clrval);
	}
	
	public static int getNumItemsInList(ArrayList<ChessPiece> mylist)
	{
		if (mylist == null) return 0;
		else return mylist.size();
	}
	
	public static boolean isBoardValid()
	{
		//each side must have at most 16 pieces total one of which must be a king
		//there are only 8 pawns so at most 8 pawns plus one of each
		//the most we can have of any one piece excluding kings and pawns is 9
		//at most 1 king, 8 pawns, 9 of the others per side.
		//if we have 9 of one we will have no pawns.
		
		//the # of pawns on the board will be minus one for every one more of another type.
		
		ArrayList<ChessPiece> wkgs = getAllKingsOfColor("WHITE");
		ArrayList<ChessPiece> bkgs = getAllKingsOfColor("BLACK");
		
		if (wkgs == null) return false;
		else if (1 < wkgs.size()) return false;
		if (bkgs == null) return false;
		else if (1 < bkgs.size()) return false;
		
		ArrayList<ChessPiece> wkts = getAllKnightsOfColor("WHITE");
		ArrayList<ChessPiece> bkts = getAllKnightsOfColor("BLACK");
		
		if (wkts == null);
		else if (9 < wkts.size()) return false;
		if (bkts == null);
		else if (9 < bkts.size()) return false;
		
		ArrayList<ChessPiece> wcls = getAllCastlesOfColor("WHITE");
		ArrayList<ChessPiece> bcls = getAllCastlesOfColor("BLACK");
		
		if (wcls == null);
		else if (9 < wcls.size()) return false;
		if (bcls == null);
		else if (9 < bcls.size()) return false;
		
		ArrayList<ChessPiece> wqns = getAllQueensOfColor("WHITE");
		ArrayList<ChessPiece> bqns = getAllQueensOfColor("BLACK");
		
		if (wqns == null);
		else if (9 < wqns.size()) return false;
		if (bqns == null);
		else if (9 < bqns.size()) return false;
		
		ArrayList<ChessPiece> wbps = getAllBishopsOfColor("WHITE");
		ArrayList<ChessPiece> bbps = getAllBishopsOfColor("BLACK");
		
		if (wbps == null);
		else if (9 < wbps.size()) return false;
		if (bbps == null);
		else if (9 < bbps.size()) return false;
		
		ArrayList<ChessPiece> wpns = getAllPawnsOfColor("WHITE");
		ArrayList<ChessPiece> bpns = getAllPawnsOfColor("BLACK");
		
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
	
	public static ChessPiece getCurrentSideKing(String clrval)
	{
		if (itemIsOnGivenList(clrval, validColors));
		else throw new IllegalStateException("ILLEGAL COLOR (" + clrval + ") FOUND AND USED HERE!");
		
		ArrayList<ChessPiece> mysidepieces = getCurrentSidePieces(clrval);
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
	public static ChessPiece getOppositeSideKing(String clrval)
	{
		if (clrval.equals("WHITE")) return getCurrentSideKing("BLACK");
		else return getCurrentSideKing("WHITE");
	}
	public ChessPiece getMySideKing()
	{
		if (getType().equals("KING")) return this;
		else return getCurrentSideKing(getColor());
	}
	
	//NOT DONE YET...
	//can I be directly attacked by the opposing side?
	public boolean inCheck()
	{
		//can I be directly attacked by the opposing side?
		return false;
	}
	
	public boolean isMySideInCheck()
	{
		//get my king
		//then ask can I be directly attacked by the opposing side?
		//if yes you are in check
		return getMySideKing().inCheck();
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
	
	public static String genMoveToCommand(String clr, String tp, int crval, int ccval, int nrval, int ncval)
	{
		String cmd = "" + clr + " " + tp + " at: " + getLocString(crval, ccval) + " to: " + getLocString(nrval, ncval);
		System.out.println("cmd = " + cmd);
		return cmd;
	}
	public String genMoveToCommand(int nrval, int ncval)
	{
		return genMoveToCommand(getColor(), getType(), getRow(), getCol(), nrval, ncval);
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
			else System.out.println("THIS PAWN HAS NOT REACHED THE CORRECT ROW FOR ITS COLOR!");
		}
		else System.out.println("THIS PIECE MUST BE A PAWN!");
		return false;
	}
	public static boolean isThereAPawnForSideThatNeedsPromoted(String clrval)
	{
		ArrayList<ChessPiece> pwnsclr = getAllPawnsOfColor(clrval);
		
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
	public void promotePawnTo(String nwtype)
	{
		if (canPawnBePromoted()) setType(nwtype);
		else throw new IllegalStateException("CANNOT PROMOTE THE PAWN!");
	}
	
	//NOT DONE YET...
	public boolean canPawnLeftOrRight(boolean useleft)
	{
		if (getType().equals("PAWN"))
		{
			//THIS IS WHEN AN OPPOSING PAWN CAPTURES A PAWN THAT MADE ITS FIRST MOVE
			//row 0: ----- ---- ---
			//row 1: --p-- -p-- -p-
			//row 2: --*-- xnx- -n-
			//row 3: -xnx- ---- x-x
			//        YES  YES  NO  IS PAWNING?
			//where n is next location of the pawn that made its first move
			//where * is next location of the pawns at x on the left part of the board
			//where p is the starting location of the pawn that will make its first move
			//p on the right is also where the pawning pawn will end up so there is a * there too
			
			//the this ChessPiece refers to the pawn at position x
			//Pawn may not be at position x
			
			if (((getRow() == 3 || getRow() == 2) && getColor().equals("WHITE")) ||
				((getRow() == 5 || getRow() == 4) && getColor().equals("BLACK")))
			{
				//we are on the row so it might be an option
			}
			else
			{
				System.out.println("OUR SIDE PIECE IS NOT ON THE APPROPRIATE ROW TO BE ABLE TO PAWN!");
				return false;
			}
			
			int lc = -1;
			if (useleft) lc = getCol() - 1;
			else lc = getCol() + 1;
			if (isvalidrorc(lc));
			else
			{
				System.out.println("THE LOCATION HAS AN INVALID COLUMN!");
				return false;
			}
			
			ChessPiece ep = ChessPiece.getPieceAt(getRow(), lc);
			if (ep == null)
			{
				System.out.println("THE LOCATION IS EMPTY!");
				return false;
			}
			else
			{
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
				
				System.out.println("NOT DONE YET HERE FOR PAWNING! NEED TO TELL IF KILLING THE PAWN " +
					"WILL PUT OUR KING INTO CHECK! OR IF WE ARE ALREADY IN CHECK AND IT DOES NOT MOVE US OUT OF CHECK!");
				
				return true;
			}
		}
		else
		{
			System.out.println("ONLY PAWNS CAN PAWN!");
			return false;
		}
	}
	public boolean canPawnLeft()
	{
		return canPawnLeftOrRight(true);
	}
	public boolean canPawnRight()
	{
		return canPawnLeftOrRight(false);
	}
	
	
	//CASTLING METHODS
	
	//NOT DONE YET...
	public static boolean canSideCastleLeftOrRight(boolean useleft, String clrval)
	{
		if (itemIsOnGivenList(clrval, validColors));
		else throw new IllegalStateException("ILLEGAL COLOR (" + clrval + ") FOUND AND USED HERE!");
		
		ChessPiece mkg = getCurrentSideKing(clrval);
		if (mkg.inCheck())
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
			ChessPiece mc = ChessPiece.getPieceAt(mcrw, mccol);
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
				if (ChessPiece.getPieceAt(mcrw, c) == null);
				else
				{
					System.out.println("THE SQUARES ARE NOT EMPTY!");
					return false;
				}
			}
			
			System.out.println("NOT DONE YET WITH CASTLING NEED TO VERIFY IF THE LOCATIONS " +
				"THE KING WILL BE MOVING ON/THROUGH WILL PUT THE KING INTO CHECK!");
			
			//need to know if there are any enemy pieces attacking the locations
			//for (int c = sccol + 1; c < cmx; c++)
			//{
			//	if (?);
			//	else return false;
			//}
			
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
	public static boolean canSideCastleLeft(String clrval)
	{
		return canSideCastleLeftOrRight(true, clrval);
	}
	public static boolean canSideCastleRight(String clrval)
	{
		return canSideCastleLeftOrRight(false, clrval);
	}
	public boolean canCastleLeftOrRight(boolean useleft)
	{
		if (getType().equals("CASTLE") || getType().equals("ROOK") || getType().equals("KING"));
		else
		{
			System.out.println("YOU MUST BE A CASTLE OR A KING TO CASTLE!");
			return false;
		}
		
		return canSideCastleLeftOrRight(useleft, getColor());
	}
	public boolean canCastleLeft()
	{
		return canCastleLeftOrRight(true);
	}
	public boolean canCastleRight()
	{
		return canCastleLeftOrRight(false);
	}
	
	public static int[] getLeftOrRightCastleSideNewCastleOrKingLoc(boolean useleft, boolean usekg, String clrval)
	{
		int[] myretarr = {-1, -1};
		if (canSideCastleLeftOrRight(useleft, clrval))
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
			myretarr[0] = getCurrentSideKing(clrval).getRow();
			myretarr[1] = 4 + kdiff + cdiff;
		}
		//else;//do nothing
		return myretarr;
	}
	public static int[] getRightCastleSideNewKingLoc(String clrval)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, true, clrval);
	}
	public static int[] getRightCastleSideNewCastleLoc(String clrval)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(false, false, clrval);
	}
	public static int[] getLeftCastleSideNewKingLoc(String clrval)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, true, clrval);
	}
	public static int[] getLeftCastleSideNewCastleLoc(String clrval)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(true, false, clrval);
	}
	//non-static version convenience methods
	public int[] getLeftOrRightCastleNewCastleOrKingLoc(boolean useleft, boolean usekg)
	{
		return getLeftOrRightCastleSideNewCastleOrKingLoc(useleft, usekg, getColor());
	}
	public int[] getRightCastleNewKingLoc()
	{
		return getRightCastleSideNewKingLoc(getColor());
	}
	public int[] getRightCastleNewCastleLoc()
	{
		return getRightCastleSideNewCastleLoc(getColor());
	}
	public int[] getLeftCastleNewKingLoc()
	{
		return getLeftCastleSideNewKingLoc(getColor());
	}
	public int[] getLeftCastleNewCastleLoc()
	{
		return getLeftCastleSideNewCastleLoc(getColor());
	}
	
	public String toString()
	{
		return "<ChessPiece of Type: " + getType() + " and Color: " + getColor() +
			" at: " + getLocString(getRow(), getCol()) + ">";
	}
}
