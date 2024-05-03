/**
 * @(#)ChessBoardPieceClass.java
 *
 * ChessBoardPieceClass application
 *
 * @author 
 * @version 1.00 2024/4/22
 */

import java.util.ArrayList;
public class ChessBoardPieceClass {
    public static void main(String[] args) {
    	
    	// TODO, add your application code
    	System.out.println("Hello World!");
    	int gid = 1;
    	setUpBoard(gid);
    	System.out.println("DONE SETTING UP THE BOARD!");
    	//ArrayList<ChessPiece> mycps = ChessPiece.cps;
    	//for (int c = 0; c < mycps.size(); c++) System.out.println(mycps.get(c));
    	printBoard(gid);
    	System.out.println(ChessPiece.isBoardValid(gid));
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);
    	ChessPiece bqn = ChessPiece.getPieceAt(0, 3, gid);
    	System.out.println(wkg);
    	System.out.println(bkg);
    	System.out.println(wqn);
    	System.out.println(bqn);
    	ChessPiece item = ChessPiece.getPieceAt(4, 0, gid);
    	System.out.println("ITEM AT (4, 0) IS: " + item);
    	System.out.println();
    	
    	testGetPiecesGuardingLocation(gid);
    	setUpFutureCheck(gid);
    	
    	System.out.println(ChessPiece.canSideCastle("WHITE", gid));
    	System.out.println(ChessPiece.canSideCastle("BLACK", gid));
    	System.out.println();
    	
    	//getAndPrintAllPiecesGenders();
    	//testConvertingLocations();
    	
    	//testPawning(gid, false);
    	//setUpBoardForPawnPromotion(gid, false);
    	//setUpBoardWithKnightCheckingKing(gid, false);
    	//setUpBoardForCastlingWhiteRight(gid, false);
    	//setUpBoardWithFourMoveCheckMate(gid, false);
    	//setUpBoardWithTwoMoveCheckMate(gid, false);
    	
    	int[][] initkgcanmvlocs = ChessPiece.getPieceCanMoveToLocs(7, 4, "WHITE", "KING", null, null, gid);
    	printLocsArray(initkgcanmvlocs, "initkgcanmvlocs");
    	int[][] initqncanmvlocs = ChessPiece.getPieceCanMoveToLocs(7, 3, "WHITE", "QUEEN", null, null, gid);
    	printLocsArray(initqncanmvlocs, "initqncanmvlocs");
    	int[][] initbpcanmvlocs = ChessPiece.getPieceCanMoveToLocs(7, 5, "WHITE", "BISHOP", null, null, gid);
    	printLocsArray(initbpcanmvlocs, "initbpcanmvlocs");
    	int[][] initclcanmvlocs = ChessPiece.getPieceCanMoveToLocs(7, 7, "WHITE", "CASTLE", null, null, gid);
    	printLocsArray(initclcanmvlocs, "initclcanmvlocs");
    	int[][] initktcanmvlocs = ChessPiece.getPieceCanMoveToLocs(7, 6, "WHITE", "KNIGHT", null, null, gid);
    	printLocsArray(initktcanmvlocs, "initktcanmvlocs");
    	int[][] initpwncanmvlocs = ChessPiece.getPieceCanMoveToLocs(6, 4, "WHITE", "PAWN", null, null, gid);
    	printLocsArray(initpwncanmvlocs, "initpwncanmvlocs");
    	
    	
    	//ChessPiece wpn = ChessPiece.getPieceAt(6, 0, gid);
    	//wpn.genMoveToCommand(4, 0);
    	//wpn.moveTo(4, 0);
    	//ChessPiece bpn = ChessPiece.getPieceAt(1, 1, gid);
    	//bpn.genMoveToCommand(3, 1);
    	//bpn.moveTo(3, 1);
    	//wpn.genMoveToCommand(3, 1);
    	//wpn.moveTo(3, 1);
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
	    		System.out.println(ChessPiece.getLocString(locs[x][0], locs[x][1]) + ": " +
	    			ChessPiece.convertRowColToStringLoc(locs[x]));
	    	}
    	}
    }
    public static void printLocsArray(int[][] locs)
    {
    	printLocsArray(locs, "locs");
    }
    
    public static void getAndPrintAllPiecesGenders(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	ArrayList<ChessPiece> mycps = ChessPiece.getAllPiecesWithGameID(gid);
    	for (int c = 0; c < mycps.size(); c++)
    	{
    		ChessPiece cp = mycps.get(c);
    		System.out.println("THE GENDER OF THE " + cp.getColor() + " " + cp.getType() + " AT: " +
    			cp.getLocString() + " IS: " + cp.convertGenderValueToString() + " WITH GAME ID: " + cp.getGameID());
    	}
    	System.out.println("mycps.size() = " + mycps.size());
    }
    
    public static void getAndPrintWhereAllThePiecesAre(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	ArrayList<ChessPiece> mycps = ChessPiece.getAllPiecesWithGameID(gid);
    	for (int c = 0; c < mycps.size(); c++) System.out.println(mycps.get(c));
    	System.out.println("mycps.size() = " + mycps.size());
    }
    
    public static void testConvertingLocations()
    {
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("H8"));//7,7
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("B8"));//7,1
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("C6"));//5,2
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("E5"));//4,4
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("F3"));//2,5
    	ChessPiece.printOneDIntArray(ChessPiece.convertStringLocToRowCol("D3"));//2,3
    	System.out.println(ChessPiece.convertRowColToStringLoc(7, 7));
    	System.out.println(ChessPiece.convertRowColToStringLoc(7, 1));
    	System.out.println(ChessPiece.convertRowColToStringLoc(5, 2));
    	System.out.println(ChessPiece.convertRowColToStringLoc(4, 4));
    	System.out.println(ChessPiece.convertRowColToStringLoc(2, 5));
    	System.out.println(ChessPiece.convertRowColToStringLoc(2, 3));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("H8")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("B8")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("C6")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("E5")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("F3")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("D3")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("X9")));
    	System.out.println(ChessPiece.convertRowColToStringLoc(ChessPiece.convertStringLocToRowCol("A9")));
    }
    
    public static void printBoard(ArrayList<ChessPiece> mycps)
    {
    	//for (int c = 0; c < mycps.size(); c++) System.out.println(mycps.get(c));
    	System.out.println("mycps.size() = " + mycps.size());
    	String myabt = "ABCDEFGH";
    	for (int c = 0; c < 8; c++) System.out.print("  " + myabt.charAt(c) + " ");
    	System.out.println(" ");
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			System.out.print("|");
    			boolean fndit = false;
    			for (int x = 0; x < mycps.size(); x++)
    			{
    				if (mycps.get(x).getRow() == r && mycps.get(x).getCol() == c)
    				{
    					//first letter of color, first letter of type, last letter of type
    					String mtp = "" + mycps.get(x).getType();
    					String mclr = "" + mycps.get(x).getColor();
    					System.out.print("" + mclr.charAt(0) + mtp.charAt(0) + mtp.charAt(mtp.length() - 1));
    					fndit = true;
    					break;
    				}
    				//else;//do nothing
    			}
    			if (fndit);
    			else System.out.print("---");
    		}
    		System.out.println("| " + (r + 1));
    	}
    }
    public static void printBoard(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else printBoard(ChessPiece.getAllPiecesWithGameID(gid));
    }
    
    public static void testGetPiecesGuardingLocation(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(6, 0));
    	//System.out.println();
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(7, 4));
    	//System.out.println();
    	System.out.println("GETTING ENEMY PIECES GUARDING LOCATION (7, 4) " +
    		ChessPiece.convertRowColToStringLoc(7, 4) + " NOW:");
    	System.out.println(ChessPiece.getEnemyPiecesGuardingLocation(7, 4, gid, null));
    	
    	System.out.println("GETTING ALL PIECES GUARDING LOCATION (6, 7) " +
    		ChessPiece.convertRowColToStringLoc(6, 7) + " NOW:");
    	int[] nloc = ChessPiece.convertStringLocToRowCol("H5");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", nloc, gid, false));
    	//int[][] ignorelist = null;
    	System.out.println("NORMAL RESULTS WITHOUT ADDING ANY PIECES OR EXCLUDING ANY:");
    	ArrayList<ChessPiece> nrmlcps = ChessPiece.getPiecesGuardingLocation(6, 7, gid, null, null);//ignorelist, addpcs
    	System.out.println(nrmlcps);
    	System.out.println();
    	System.out.println("RESULTS WITH THE ADDITION OF A BLACK QUEEN AT (" + nloc[0] + ", " + nloc[1] + "):");
    	ArrayList<ChessPiece> nrmlwithaddpcs = ChessPiece.getPiecesGuardingLocation(6, 7, gid, null, addpcs);
    	//ignorelist, addpcs
    	System.out.println(nrmlwithaddpcs);
    	System.out.println();
    	boolean runigpwntst = true;
    	if (runigpwntst)
    	{
    		System.out.println("RESULTS WITH IGNORING PAWN AT (6, 7), BUT INCLUDING BLACK QUEEN AT (" + nloc[0] + ", " +
    			nloc[1] + "):");
    	}
    	int[][] ignorelist = new int[1][2];
    	ignorelist[0][0] = 6;//6
    	ignorelist[0][1] = 7;//5
    	//int[] myintarr = new int[2];
    	//System.out.println(ChessPiece.getNumItemsInList(myintarr));
    	ArrayList<ChessPiece> nopwnwithaddpcs = null;
    	if (runigpwntst)
    	{
    		nopwnwithaddpcs = ChessPiece.getPiecesGuardingLocation(6, 7, gid, ignorelist, addpcs);//7,4
    		System.out.println(nopwnwithaddpcs);
    		//now filter the list...
    		//System.out.println(ChessPiece.filterListByColor(nopwnwithaddpcs, "BLACK"));
    		//if more than zero, piece would be in check under certain setup
    		System.out.println("addpcs = " + addpcs);
    		ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    		//System.out.println(nwpcslist);
    		printBoard(nwpcslist);
    		//System.out.println(ChessPiece.isBoardValid(nwpcslist));
    		System.out.println("DONE TESTING PRINT NEW SETUP BOARD!");
    	}
    	//else;//do nothing
    	
    	printBoard(gid);
    	//not true for all test cases, but must be true for this case
    	if (nrmlcps.size() + 1 == nrmlwithaddpcs.size());
    	else throw new IllegalStateException("ADDING A QUEEN TEST FAILED!");
    	if (runigpwntst)
    	{
    		if (nrmlcps.size() == nopwnwithaddpcs.size());
    		else throw new IllegalStateException("IGNORING A PAWN TEST FAILED!");
    	}
    	//else;//do nothing
    	System.out.println(ChessPiece.isBoardValid(gid));
    }
    public static void setUpFutureCheck(int gid)
    {
    	//now set up future check scenario
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);
    	ChessPiece bqn = ChessPiece.getPieceAt(0, 3, gid);
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", ChessPiece.convertStringLocToRowCol("H5"), gid, false));
    	int[][] ignorelist = new int[1][2];
    	ignorelist[0][0] = 6;
    	ignorelist[0][1] = 5;
    	ArrayList<ChessPiece> gwklocs = ChessPiece.getPiecesGuardingLocation(7, 4, gid, ignorelist, addpcs);
    	System.out.println("addpcs = " + addpcs);
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	System.out.println();
    	
    	System.out.println("ACCORDING TO THE FUTURE BOARD:");
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("WHITE QUEEN IS IN CHECK: " + wqn.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK QUEEN IS IN CHECK: " + bqn.inCheck(ignorelist, addpcs));
    	System.out.println();
    	
    	printBoard(gid);
    	System.out.println();
    	
    	System.out.println("ACCORDING TO THE ACTUAL BOARD:");
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    	System.out.println("WHITE QUEEN IS IN CHECK: " + wqn.inCheck());
    	System.out.println("BLACK QUEEN IS IN CHECK: " + bqn.inCheck());
    	System.out.println();
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(2, 2));
    	//System.out.println();
    }
    
    //SET UP BOARD METHODS
    
    public static void setUpBoardForPawnPromotion(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//GOAL: PROMOTE WHITE PAWN TO SOMETHING?
    	//WPN AT A7 -> A5 -> B4 -> B3 -> B2 -> A1;
    	//BPN AT B2 -> B4; OBPN AT H2 -> H4 -> H5 -> H6;
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	ChessPiece obpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("H2"), gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H4"));
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B3"));
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H5"));
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B2"));
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A1"));
    	//test pawn promotion methods here
    	System.out.println(ChessPiece.canPawnForSideBePromoted("WHITE", gid));
    	System.out.println(ChessPiece.canPawnForSideBePromoted("BLACK", gid));
    }
    public static void setUpBoardForPawnPromotionWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//ignore white pawn at A7; add it at A1 (5 moves)
    	//ignore black castle at A1
    	//ignore black pawn at B2
    	//ignore black pawn at H2; add it at H6 (3 moves)
    	
    	int[][] ignorelist = new int[4][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("H2");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("A7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("B2");
    	ignorelist[3] = ChessPiece.convertStringLocToRowCol("A1");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	ChessPiece wpn = new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("A1"), gid, 5, false);
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("H6"), gid, 3, false));
    	addpcs.add(wpn);
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test promotion methods here...
    	System.out.println("A WHITE PAWN CAN BE PROMOTED: " + ChessPiece.canPawnForSideBePromoted("WHITE", nwpcslist));
    	System.out.println("A BLACK PAWN CAN BE PROMOTED: " + ChessPiece.canPawnForSideBePromoted("BLACK", nwpcslist));
    	wpn.promotePawnTo("QUEEN");
    	System.out.println(wpn);
    	printBoard(nwpcslist);
    	//black pawn at H6 -> G7 -> H8;
    	//WQN AT A1 -> B1;
    	int[][] nwiglist = new int[5][2];
    	nwiglist[0] = ChessPiece.convertStringLocToRowCol("G7");
    	nwiglist[1] = ChessPiece.convertStringLocToRowCol("H8");
    	nwiglist[2] = ChessPiece.convertStringLocToRowCol("H6");
    	nwiglist[3] = ChessPiece.convertStringLocToRowCol("B1");
    	nwiglist[4] = ChessPiece.convertStringLocToRowCol("A1");
    	ArrayList<ChessPiece> nwaddpcs = new ArrayList<ChessPiece>();
    	ChessPiece bpn = new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("H8"), gid, 5, false);
    	nwaddpcs.add(bpn);
    	nwaddpcs.add(new ChessPiece("QUEEN", "WHITE", ChessPiece.convertStringLocToRowCol("B1"), gid, 6, false));
    	ArrayList<ChessPiece> myonwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(nwiglist, nwaddpcs, nwpcslist);
    	printBoard(myonwpcslist);
    	System.out.println("A WHITE PAWN CAN BE PROMOTED: " + ChessPiece.canPawnForSideBePromoted("WHITE", myonwpcslist));
    	System.out.println("A BLACK PAWN CAN BE PROMOTED: " + ChessPiece.canPawnForSideBePromoted("BLACK", myonwpcslist));
    	bpn.promotePawnTo("QUEEN");
    	System.out.println(bpn);
    	printBoard(myonwpcslist);
    }
    public static void setUpBoardForPawnPromotion(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForPawnPromotion(gid);
    	else setUpBoardForPawnPromotionWithoutMovingThere(gid);
    }
    
    public static void setUpBoardForPawning(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//BPN AT B2 -> B4 -> B5; WPN AT A7 -> A5; WKT AT G8 -> H6 -> F5
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("F5"));
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B5"));
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	//now test pawning methods here
    	System.out.println("WHITE CAN PAWN: " + ChessPiece.canSidePawn("WHITE", gid));
    	System.out.println("BLACK CAN PAWN: " + ChessPiece.canSidePawn("BLACK", gid));
    }
    public static void testPawningWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//ignore knight at G8; add it at F5
    	//ignore pawn at A7; add it at A5
    	//ignore pawn at B2; add it at B5
    	int[][] ignorelist = new int[3][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("G8");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("A7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("B2");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("B5"), gid, 2, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("A5"), gid, 1, false));
    	addpcs.add(new ChessPiece("KNIGHT", "WHITE", ChessPiece.convertStringLocToRowCol("F5"), gid, 2, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//call canPawnLeft on Black Pawn
    	System.out.println("WHITE CAN PAWN: " + ChessPiece.canSidePawn("WHITE", nwpcslist));
    	System.out.println("BLACK CAN PAWN: " + ChessPiece.canSidePawn("BLACK", nwpcslist));
    }
    public static void testPawning(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForPawning(gid);
    	else testPawningWithoutMovingThere(gid);
    }
    
    public static void setUpBoardWithKnightCheckingKing(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//White Knight at B8 -> C6 -> E5 -> (F3 OR D3)
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("C6"));
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A4"));
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("E5"));
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("F3"));
    	//now test check and figure out how to get out of it
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    }
    public static void setUpBoardWithKnightCheckingKingWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//White Knight at B8 -> C6 -> E5 -> (F3 OR D3)
    	//ignore knight at B8; add it at F3 OR D3;
    	//ignore pawn at A2; add it at A5;
    	int[][] ignorelist = new int[2][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("B8");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("A2");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("A5"), gid, 2, false));
    	addpcs.add(new ChessPiece("KNIGHT", "WHITE", ChessPiece.convertStringLocToRowCol("F3"), gid, 3, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//now test check and figure out how to get out of it
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", nwpcslist);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", nwpcslist);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    }
    public static void setUpBoardWithKnightCheckingKing(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardWithKnightCheckingKing(gid);
    	else setUpBoardWithKnightCheckingKingWithoutMovingThere(gid);
    }
    
    public static void setUpBoardForCastlingWhiteRight(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//WKT AT G8 -> H6; WPN AT E7 -> E6; WBP AT F8 -> C5
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A4"));
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("E7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("E6"));
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	ChessPiece wbp = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("F8"), gid);
    	wbp.genMoveToCommand(ChessPiece.convertStringLocToRowCol("C5"));
    	ChessPiece obpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G2"), gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("G4"));
    	//actually test the castling information here now...
    	System.out.println(ChessPiece.canSideCastle("WHITE", gid));
    	System.out.println(ChessPiece.canSideCastle("BLACK", gid));
    }
    public static void setUpBoardForCastlingWhiteRightWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//WKT AT G8 -> H6; WPN AT E7 -> E6; WBP AT F8 -> C5
    	//BPN AT A2 -> A4 -> A5; BPN AT G2 -> G4
    	int[][] ignorelist = new int[5][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("G8");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("E7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("F8");
    	ignorelist[3] = ChessPiece.convertStringLocToRowCol("A2");
    	ignorelist[4] = ChessPiece.convertStringLocToRowCol("G2");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("E6"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("A5"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("G4"), gid, 1, false));
    	addpcs.add(new ChessPiece("KNIGHT", "WHITE", ChessPiece.convertStringLocToRowCol("H6"), gid, 1, false));
    	addpcs.add(new ChessPiece("BISHOP", "WHITE", ChessPiece.convertStringLocToRowCol("C5"), gid, 1, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//actually test the castling information here now...
    	System.out.println("WHITE CAN CASTLE: " + ChessPiece.canSideCastle("WHITE", ignorelist, addpcs, gid));
    	System.out.println("BLACK CAN CASTLE: " + ChessPiece.canSideCastle("BLACK", ignorelist, addpcs, gid));
    }
    public static void setUpBoardForCastlingWhiteRight(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForCastlingWhiteRight(gid);
    	else setUpBoardForCastlingWhiteRightWithoutMovingThere(gid);
    }
    
    public static void setUpBoardWithFourMoveCheckMate(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//WPN AT E7 -> E6; WBP AT F8 -> C5; WQN AT D8 -> F6 -> F2
		//BPN AT A2 -> A4 -> A5 -> A6
    	
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 6, gid);//E7
    	wpn.genMoveToCommand(5, 6);//E6
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 0, gid);//A2
    	bpn.genMoveToCommand(3, 0);//A4
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);//D8
    	wqn.genMoveToCommand(5, 5);//F6
    	bpn.genMoveToCommand(4, 0);//A5
    	ChessPiece wbp = ChessPiece.getPieceAt(7, 5, gid);//F8
    	wbp.genMoveToCommand(4, 2);//C5
    	bpn.genMoveToCommand(5, 0);//A6
    	wqn.genMoveToCommand(1, 5);//F2
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    }
    public static void setUpBoardWithFourMoveCheckMateWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//ignore queen at D8; add it at F2
    	//ignore pawn at E7; add it at E6
    	//ignore bishop at F8; add it at C5
    	//ignore pawn at A2; add it at A6
    	int[][] ignorelist = new int[4][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("F8");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("E7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("D8");
    	ignorelist[3] = ChessPiece.convertStringLocToRowCol("A2");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("BISHOP", "WHITE", ChessPiece.convertStringLocToRowCol("C5"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("E6"), gid, 1, false));
    	addpcs.add(new ChessPiece("QUEEN", "WHITE", ChessPiece.convertStringLocToRowCol("F2"), gid, 2, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("A6"), gid, 3, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    }
    public static void setUpBoardWithFourMoveCheckMate(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardWithFourMoveCheckMate(gid);
    	else setUpBoardWithFourMoveCheckMateWithoutMovingThere(gid);
    }
    
    public static void setUpBoardWithTwoMoveCheckMate(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 6, gid);//G7
    	wpn.genMoveToCommand(5, 6);//G6
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 5, gid);//F2
    	bpn.genMoveToCommand(2, 5);//F3
    	ChessPiece owpn = ChessPiece.getPieceAt(6, 0, gid);//A7
    	owpn.genMoveToCommand(5, 0);//A6
    	ChessPiece obpn = ChessPiece.getPieceAt(1, 6, gid);//G2
    	obpn.genMoveToCommand(3, 6);//G4
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);//D8
    	wqn.genMoveToCommand(3, 7);//H4
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    }
    public static void setUpBoardWithTwoMoveCheckMateWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//BPN AT F2 -> F3; OBPN AT G2 -> G4;
		//WPN AT E7 -> E6; WQN AT D8 -> H4; OWPN AT A7 -> A5;
		
		int[][] ignorelist = new int[5][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("F2");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("E7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("D8");
    	ignorelist[3] = ChessPiece.convertStringLocToRowCol("A7");
    	ignorelist[4] = ChessPiece.convertStringLocToRowCol("G2");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("E6"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("A5"), gid, 1, false));
    	addpcs.add(new ChessPiece("QUEEN", "WHITE", ChessPiece.convertStringLocToRowCol("H4"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("F3"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("G4"), gid, 1, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    }
    public static void setUpBoardWithTwoMoveCheckMate(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardWithTwoMoveCheckMate(gid);
    	else setUpBoardWithTwoMoveCheckMateWithoutMovingThere(gid);
    }
    
    public static void setUpBoard(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing safe to proceed
    	
    	//white pawns on row 6 cols 0 through 7
    	//black pawns on row 1 cols 0 through 7
    	for (int x = 0; x < 2; x++)
    	{
    		int r = -1;
    		String clr = "";
    		if (x == 0)
    		{
    			r = 6;
    			clr = "WHITE";
    		}
    		else
    		{
    			r = 1;
    			clr = "BLACK";
    		}
    		for (int c = 0; c < 8; c++)
	    	{
	    		ChessPiece cp = new ChessPiece("PAWN", clr, r, c, gid);
	    		//cps.add(cp);
	    	}
	    	int orw = -1;
	    	if (clr.equals("WHITE")) orw = 7;
	    	else orw = 0;
	    	String[] mvtypes = ChessPiece.getValidTypes();
	    	for (int k = 0; k < mvtypes.length; k++)
	    	{
	    		if (mvtypes[k].equals("PAWN") || mvtypes[k].equals("ROOK")) continue;
	    		else
	    		{
	    			System.out.println("mvtypes[" + k + "] = " + mvtypes[k]);
	    			boolean uselft = true;
	    			for (int i = 0; i < 2; i++)
	    			{
	    				if (i == 0);
	    				else uselft = false;
	    				int nwcl = ChessPiece.getSetUpColForType(mvtypes[k], uselft);
	    				//System.out.println("i = " + i);
	    				//System.out.println("CREATED NEW PIECE AT (" + orw + ", " + nwcl + ")");
	    				ChessPiece ocp = new ChessPiece(mvtypes[k], clr, orw, nwcl, gid);
	    				//cps.add(ocp);
	    				if (mvtypes[k].equals("KING") || mvtypes[k].equals("QUEEN")) break;
	    			}//end of i for loop
	    		}
	    	}//end of k for loop
    	}//end of x for loop
    }
}
