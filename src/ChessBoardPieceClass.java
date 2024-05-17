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
    	
    	//System.out.println(ChessPiece.getColorOfLoc(7, 7));
    	
    	//setUpBoardTestIsEmptyMoveToLocsWithoutMovingThere(gid);
    	//if (true) return;
    	
    	//getAndPrintAllPiecesGenders();
    	//testConvertingLocations();
    	//testCanMoveToLocs(gid, null, null);
    	
    	testPawning(gid, true);
    	//setUpBoardForPawnPromotion(gid, true);
    	//setUpBoardForCastlingWhiteRight(gid, true);
    	//setUpBoardWithKnightCheckingKing(gid, false);
    	//CHECKMATE TESTS
    	//setUpBoardWithFourMoveCheckMate(gid, false);
    	//setUpBoardWithTwoMoveCheckMateBlack(gid, false);
    	//setUpBoardWithTwoMoveCheckMateWhite(gid, false);
    	//setUpBoardCheckmateKingBishopVSameDiffColorSquares(gid);
    	//setUpBoardWhiteCheckmateAfterManyMoves(gid);
    	//STALEMATE TESTS
    	//setUpBoardWithKingVKingAndStuckPawnsWithoutMovingThere(gid);
    	//setUpBoardWithBlockedPawnsAndBishops(gid);
    	//setUpBoardWhiteStalemateAfterManyMoves(gid);
    	//AUTO STALEMATES
    	//setUpBoardWithKingAndBishopsVKingBishops(gid, 1, 1);
    	//setUpBoardWithKingAndBishopsVKingBishops(gid, 0, 1);
    	//setUpBoardWithKingAndBishopsVKingBishops(gid, 1, 0);
    	//setUpBoardWithKingVKingOnlyWithoutMovingThere(gid);
    	//setUpBoardWithKingAndBishopsVKingBishops(gid, 0, 0);//also produces king vs king board
    	//setUpBoardWithKingAndKnightVKing(gid);
    	//
    	//NOT A STALEMATE BECAUSE BISHOPS ARE ON DIFFERENT COLOR SQUARES
    	//setUpBoardWithKingAndBishopsVKingBishops(gid, 1, 1, false);//true passed in produces a stalemate
    	
    	//System.out.println();
    	//System.out.println("AFTER SPECIAL TESTS!");
    	
    	
    	//ChessPiece wpn = ChessPiece.getPieceAt(6, 0, gid);
    	//wpn.genMoveToCommand(4, 0);
    	//wpn.setLoc(4, 0);
    	//wpn.moveTo(4, 0);
    	//ChessPiece bpn = ChessPiece.getPieceAt(1, 1, gid);
    	//bpn.genMoveToCommand(3, 1);
    	//bpn.setLoc(3, 1);
    	//bpn.moveTo(3, 1);
    	//printBoard(gid);
    	//wpn.genMoveToCommand(3, 1);
    	//System.out.println("TOTAL PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	//ChessPiece.removePieceAt(3, 1, gid);
    	//System.out.println("TOTAL PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	//wpn.setLoc(3, 1);
    	//wpn.moveTo(3, 1);
    	//printBoard(gid);
    	//ChessPiece obpn = ChessPiece.getPieceAt(1, 0, gid);
    	//obpn.genMoveToCommand(3, 0);
    	//obpn.setLoc(3, 0);
    	//obpn.setMoveCount(1);
    	//System.out.println("OTHER BLACK PAWN MOVE COUNT: " + obpn.getMoveCount());
    	//printBoard(gid);
    	//wpn.genMoveToCommand(2, 0);
    	//System.out.println("TOTAL PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	//ChessPiece.removePieceAt(3, 0, gid);
    	//wpn.setLoc(2, 0);
    	//wpn.pawnLeft();
    	//System.out.println("TOTAL PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	//printBoard(gid);
    }
    
    //PRINT BOARD METHODS
    
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
    		if (ChessPiece.WHITE_MOVES_DOWN_RANKS) System.out.println("| " + (r + 1));
    		else System.out.println("| " + (8 - r));
    	}
    }
    public static void printBoard(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else printBoard(ChessPiece.getAllPiecesWithGameID(gid));
    }
    
    //TEST SOME BASIC METHODS
    
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
    
    public static void setUpBoardTestIsEmptyMoveToLocsWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	int[][] ignorelist = new int[2][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("H2");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("H7");
    	//add list is null
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, null, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, null));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, null));
    	System.out.println("ALL BISHOPS ON SAME COLOR SQUARES: " + ChessPiece.areAllBishopsOnSameColorSquare(nwpcslist));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, null, gid));
    	System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	System.out.println();
    	
    	//returns false because there is a piece there on the normal board
    	System.out.println("IS (row: 1, col: 7) EMPTY: " + ChessPiece.isLocationEmpty(1, 7, gid, ignorelist, null));
    	System.out.println("IS (row: 1, col: 7) EMPTY: " + ChessPiece.isLocationEmpty(1, 7, nwpcslist));
    	System.out.println("IS (row: 6, col: 7) EMPTY: " + ChessPiece.isLocationEmpty(6, 7, gid, ignorelist, null));
    	System.out.println("IS (row: 6, col: 7) EMPTY: " + ChessPiece.isLocationEmpty(6, 7, nwpcslist));
    	
    	testKingCanMoveToLocs(gid, 7, 4, "WHITE", ignorelist, null);
    	testKingCanMoveToLocs(gid, 0, 4, "BLACK", ignorelist, null);
    	testCastleCanMoveToLocs(gid, 7, 7, "WHITE", ignorelist, null);
    	testCastleCanMoveToLocs(gid, 0, 7, "BLACK", ignorelist, null);
    }
    
    
    //TEST MOVE TO LOCS METHODS
    
    public static void testPieceCanMoveToLocs(int gid, int rval, int cval, String clrval, String tpval, String locsarrnm,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	System.out.println();
    	//System.out.println("CALLING " + clrval + " " + tpval + " CAN MOVE TO LOCS WITH STARTING LOCATION " +
    	//	ChessPiece.getLocStringAndConvertIt(rval, cval) + "!");
    	int[][] locs = ChessPiece.getPieceCanMoveToLocs(rval, cval, clrval, tpval, ignorelist, addpcs, gid);
    	System.out.println("RESULTS " + clrval + " " + tpval + " CAN MOVE TO LOCS WITH STARTING LOCATION " +
    		ChessPiece.getLocStringAndConvertIt(rval, cval) + "!");
    	ChessPiece.printLocsArray(locs, locsarrnm);
    }
    public static void testKingCanMoveToLocs(int gid, int kgr, int kgc, String kgclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, kgr, kgc, kgclr, "KING", "initkgcanmvlocs", ignorelist, addpcs);
    }
    public static void testKnightCanMoveToLocs(int gid, int ktr, int ktc, String ktclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, ktr, ktc, ktclr, "KNIGHT", "initktcanmvlocs", ignorelist, addpcs);
    }
    public static void testQueenCanMoveToLocs(int gid, int qr, int qc, String qclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, qr, qc, qclr, "QUEEN", "initqncanmvlocs", ignorelist, addpcs);
    }
    public static void testBishopCanMoveToLocs(int gid, int br, int bc, String bclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, br, bc, bclr, "BISHOP", "initbpcanmvlocs", ignorelist, addpcs);
    }
    public static void testCastleCanMoveToLocs(int gid, int cr, int cc, String cclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, cr, cc, cclr, "CASTLE", "initclcanmvlocs", ignorelist, addpcs);
    }
    public static void testPawnCanMoveToLocs(int gid, int pr, int pc, String pclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testPieceCanMoveToLocs(gid, pr, pc, pclr, "PAWN", "initpwncanmvlocs", ignorelist, addpcs);
    }
    
    //calls the above methods once for each type of piece
    public static void testCanMoveToLocs(int gid, int kgr, int kgc, String kgclr, int ktr, int ktc, String ktclr,
    	int cr, int cc, String cclr, int br, int bc, String bclr, int pr, int pc, String pclr, int qr, int qc, String qclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testKingCanMoveToLocs(gid, kgr, kgc, kgclr, ignorelist, addpcs);
    	testQueenCanMoveToLocs(gid, qr, qc, qclr, ignorelist, addpcs);
    	testBishopCanMoveToLocs(gid, br, bc, bclr, ignorelist, addpcs);
    	testCastleCanMoveToLocs(gid, cr, cc, cclr, ignorelist, addpcs);
    	testKnightCanMoveToLocs(gid, ktr, ktc, ktclr, ignorelist, addpcs);
    	testPawnCanMoveToLocs(gid, pr, pc, pclr, ignorelist, addpcs);
    }
    //loc lists in the order of: king, knight, castle (rook), bishop, pawn, queen
    public static void testCanMoveToLocs(int gid, int[] kgloc, String kgclr, int[] ktloc, String ktclr,
    	int[] cloc, String cclr, int[] bloc, String bclr, int[] ploc, String pclr, int[] qloc, String qclr,
    	int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	testCanMoveToLocs(gid, kgloc[0], kgloc[1], kgclr, ktloc[0], ktloc[1], ktclr, cloc[0], cloc[1], cclr,
    		bloc[0], bloc[1], bclr, ploc[0], ploc[1], pclr, qloc[0], qloc[1], qclr, ignorelist, addpcs);
    }
    //pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    public static void testCanMoveToLocs(int gid, int[][] pcslocs, String[] psclrs, int[][] ignorelist,
    	ArrayList<ChessPiece> addpcs)
    {
    	testCanMoveToLocs(gid, pcslocs[0][0], pcslocs[0][1], psclrs[0], pcslocs[1][0], pcslocs[1][1], psclrs[1],
    		pcslocs[2][0], pcslocs[2][1], psclrs[2], pcslocs[3][0], pcslocs[3][1], psclrs[3],
    		pcslocs[4][0], pcslocs[4][1], psclrs[4], pcslocs[5][0], pcslocs[5][1], psclrs[5], ignorelist, addpcs);
    }
    public static void testCanMoveToLocs(int gid, int[][] ignorelist, ArrayList<ChessPiece> addpcs)
    {
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 7, 6, "WHITE", 7, 7, "WHITE", 7, 5, "WHITE", 6, 4, "WHITE", 7, 3, "WHITE",
    		ignorelist, addpcs);
    }
    
    
    //SET UP BOARD METHODS
    //SET UP BOARD PAWN PROMOTION
    
    public static void setUpBoardForPawnPromotion(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//GOAL: PROMOTE WHITE PAWN TO SOMETHING?
    	//WPN AT A7 -> A5 -> B4 -> B3 -> B2 -> A1;
    	//BPN AT B2 -> B4; OBPN AT H2 -> H4 -> H5 -> H6;
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("A5"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("B4"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	printBoard(gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	ChessPiece.removePieceAt(ChessPiece.convertStringLocToRowCol("B4"), gid);
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("B4"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	printBoard(gid);
    	System.out.println(ChessPiece.isBoardValid(gid));
    	ChessPiece obpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("H2"), gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H4"));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("H4"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B3"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("B3"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H5"));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("H5"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B2"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("B2"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("H6"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	
    	boolean tstmovandpromote = false;
    	String mymvploc = null;
    	if (tstmovandpromote)
    	{
    		mymvploc = "B1";
    		System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    		ChessPiece.removePieceAt(ChessPiece.convertStringLocToRowCol(mymvploc), gid);
    		System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	}
    	else mymvploc = "A1";
    	
    	printBoard(gid);
    	String[] mymv = wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol(mymvploc), "QUEEN");
    	String[] myunmv = ChessPiece.genUndoMoveToShortHandCommand(mymv);
    	String[] myredmv = ChessPiece.genRedoMoveToShortHandCommand(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myredmv);
    	boolean stoptest = false;
    	if (stoptest) throw new IllegalStateException("UNDO GEN PROMOTE PAWN COMMAND FAILED!");
    	
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	ChessPiece.removePieceAt(ChessPiece.convertStringLocToRowCol("A1"), gid);
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("A1"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	//test pawn promotion methods here
    	printBoard(gid);
    	System.out.println("WHITE CAN PROMOTE A PAWN: " + ChessPiece.canPawnForSideBePromoted("WHITE", gid));
    	System.out.println("BLACK CAN PROMOTE A PAWN: " + ChessPiece.canPawnForSideBePromoted("BLACK", gid));
    	wpn.promotePawnTo("QUEEN");
    	System.out.println(wpn);
    	printBoard(gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("G7"));
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	ChessPiece.removePieceAt(ChessPiece.convertStringLocToRowCol("G7"), gid);
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("G7"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B1"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("B1"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	printBoard(gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H8"));
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	ChessPiece.removePieceAt(ChessPiece.convertStringLocToRowCol("H8"), gid);
    	System.out.println("TOTAL NUMBER OF PIECES: " + ChessPiece.getNumItemsInList(ChessPiece.cps));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("H8"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	//test pawn promotion methods here
    	printBoard(gid);
    	System.out.println("WHITE CAN PROMOTE A PAWN: " + ChessPiece.canPawnForSideBePromoted("WHITE", gid));
    	System.out.println("BLACK CAN PROMOTE A PAWN: " + ChessPiece.canPawnForSideBePromoted("BLACK", gid));
    	obpn.promotePawnTo("QUEEN");
    	System.out.println(obpn);
    	printBoard(gid);
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
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 7, 6, "WHITE", 7, 0, "WHITE", 0, 2, "BLACK", 1, 2, "BLACK", 7, 7, "BLACK",
    		ChessPiece.combineIgnoreLists(nwiglist, ignorelist), ChessPiece.combineTwoPieceLists(nwaddpcs, addpcs));
    }
    public static void setUpBoardForPawnPromotion(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForPawnPromotion(gid);
    	else setUpBoardForPawnPromotionWithoutMovingThere(gid);
    }
    
    //SET UP BOARD PAWNING
    
    public static void setUpBoardForPawning(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//BPN AT B2 -> B4 -> B5; WPN AT A7 -> A5; WKT AT G8 -> H6 -> F5
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("H6"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("B4"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("F5"));
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("F5"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B5"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("B5"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("A5"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	printBoard(gid);
    	//now test pawning methods here
    	System.out.println("WHITE CAN PAWN: " + ChessPiece.canSidePawn("WHITE", gid));
    	System.out.println("BLACK CAN PAWN: " + ChessPiece.canSidePawn("BLACK", gid));
    	String[] mymv = bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A6"));
    	String[] myunmv = ChessPiece.genUndoMoveToShortHandCommand(mymv);
    	String[] myredmv = ChessPiece.genRedoMoveToShortHandCommand(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myredmv);
    	boolean stoptest = false;
    	if (stoptest) throw new IllegalStateException("UNDO GEN PROMOTE PAWN COMMAND FAILED!");
    	bpn.pawnLeft();
    	printBoard(gid);
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
    	ChessPiece bpn = new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("B5"), gid, 2, false);
    	addpcs.add(bpn);
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("A5"), gid, 1, false));
    	addpcs.add(new ChessPiece("KNIGHT", "WHITE", ChessPiece.convertStringLocToRowCol("F5"), gid, 2, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//call canPawnLeft on Black Pawn
    	System.out.println("WHITE CAN PAWN: " + ChessPiece.canSidePawn("WHITE", nwpcslist));
    	System.out.println("BLACK CAN PAWN: " + ChessPiece.canSidePawn("BLACK", nwpcslist));
    	int[][] bpwncanmvlocs = ChessPiece.getPieceCanMoveToLocs(bpn.getRow(), bpn.getCol(), "BLACK", "PAWN",
    		ignorelist, addpcs, gid);
    	ChessPiece.printLocsArray(bpwncanmvlocs, "bpwncanmvlocs");
    	System.out.println("BLACK CAN PAWN LEFT: " + bpn.canPawnLeft(nwpcslist));
    	System.out.println("BLACK CAN PAWN RIGHT: " + bpn.canPawnRight(nwpcslist));
    	int[] bplftloc = bpn.getPawnLeftLocation(nwpcslist);
    	int[] bprgtloc = bpn.getPawnRightLocation(nwpcslist);
    	if (bplftloc == null) System.out.println("BLACK PAWNING MOVE TO LEFT LOCATION: null");
    	else
    	{
    		System.out.println("BLACK PAWNING MOVE TO LEFT LOCATION: " + ChessPiece.getLocString(bplftloc) + " IS: " +
    			ChessPiece.convertRowColToStringLoc(bplftloc));
    	}
    	if (bprgtloc == null) System.out.println("BLACK PAWNING MOVE TO RIGHT LOCATION: null");
    	else
    	{
    		System.out.println("BLACK PAWNING MOVE TO RIGHT LOCATION: " + ChessPiece.getLocString(bprgtloc) + " IS: " +
    			ChessPiece.convertRowColToStringLoc(bprgtloc));
    	}
    	System.out.println("BLACK ENEMY PAWN FOR LEFT PAWNING: " + bpn.getEnemyPawnForLeftPawning(nwpcslist));
    	System.out.println("BLACK ENEMY PAWN FOR RIGHT PAWNING: " + bpn.getEnemyPawnForRightPawning(nwpcslist));
    	if (bplftloc == null) System.out.println("BLACK PAWN CAN MOVE TO THE LEFT PAWNING LOCATION: false");
    	else
    	{
    		System.out.println("BLACK PAWN CAN MOVE TO THE LEFT PAWNING LOCATION: " +
    			bpn.canMoveTo(bplftloc[0], bplftloc[1], ignorelist, addpcs));
    	}
    	if (bprgtloc == null) System.out.println("BLACK PAWN CAN MOVE TO THE RIGHT PAWNING LOCATION: false");
    	else
    	{
    		System.out.println("BLACK PAWN CAN MOVE TO THE RIGHT PAWNING LOCATION: " +
    			bpn.canMoveTo(bprgtloc[0], bprgtloc[1], ignorelist, addpcs));
    	}
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 4, 5, "WHITE", 7, 7, "WHITE", 0, 2, "BLACK", 4, 1, "BLACK", 0, 3, "BLACK",
    		ignorelist, addpcs);
    }
    public static void testPawning(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForPawning(gid);
    	else testPawningWithoutMovingThere(gid);
    }
    
    //SET UP BOARD CASTLING
    
    public static void setUpBoardForCastlingWhiteRight(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//WKT AT G8 -> H6; WPN AT E7 -> E6; WBP AT F8 -> C5
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("H6"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A4"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("A4"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("E7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("E6"));
    	wpn.setLoc(ChessPiece.convertStringLocToRowCol("E6"));
    	wpn.setMoveCount(wpn.getMoveCount() + 1);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("A5"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	ChessPiece wbp = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("F8"), gid);
    	wbp.genMoveToCommand(ChessPiece.convertStringLocToRowCol("C5"));
    	wbp.setLoc(ChessPiece.convertStringLocToRowCol("C5"));
    	wbp.setMoveCount(wbp.getMoveCount() + 1);
    	ChessPiece obpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G2"), gid);
    	obpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("G4"));
    	obpn.setLoc(ChessPiece.convertStringLocToRowCol("G4"));
    	obpn.setMoveCount(obpn.getMoveCount() + 1);
    	//actually test the castling information here now...
    	System.out.println("WHITE CAN CASTLE: " + ChessPiece.canSideCastle("WHITE", gid));
    	System.out.println("BLACK CAN CASTLE: " + ChessPiece.canSideCastle("BLACK", gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(null, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(null, null, gid));
    	printBoard(gid);
    	String[] mymv = ChessPiece.genCastlingMoveToCommand("WHITE", false, gid, null, null);
    	String[] myunmv = ChessPiece.genUndoMoveToShortHandCommand(mymv);
    	String[] myredmv = ChessPiece.genRedoMoveToShortHandCommand(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myunmv);
    	ChessPiece.convertAllShortHandMovesToLongVersion(myredmv);
    	boolean stoptest = false;
    	if (stoptest) throw new IllegalStateException("UNDO GEN PROMOTE PAWN COMMAND FAILED!");
    	ChessPiece.whiteCastleRight(gid, null, null);//move count will be automatically incremented in this method
    	printBoard(gid);
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
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 5, 7, "WHITE", 7, 7, "WHITE", 4, 2, "WHITE", 6, 1, "WHITE", 7, 3, "WHITE",
    		ignorelist, addpcs);
    }
    public static void setUpBoardForCastlingWhiteRight(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardForCastlingWhiteRight(gid);
    	else setUpBoardForCastlingWhiteRightWithoutMovingThere(gid);
    }
    
    //SETUP BOARD CHECK
    
    public static void setUpBoardWithKnightCheckingKing(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	//White Knight at B8 -> C6 -> E5 -> (F3 OR D3)
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B8"), gid);
    	String[] mymv = wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("C6"));
    	String[] myunmv = ChessPiece.genUndoMoveToShortHandCommand(mymv);
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("C6"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A4"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("A4"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("E5"));
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("E5"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	bpn.setLoc(ChessPiece.convertStringLocToRowCol("A5"));
    	bpn.setMoveCount(bpn.getMoveCount() + 1);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("F3"));
    	wkt.setLoc(ChessPiece.convertStringLocToRowCol("F3"));
    	wkt.setMoveCount(wkt.getMoveCount() + 1);
    	printBoard(gid);
    	//now test check and figure out how to get out of it
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(null, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(null, null, gid));
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
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 0, 4, "BLACK", 0, 6, "BLACK", 0, 0, "BLACK", 7, 2, "WHITE", 1, 6, "BLACK", 0, 3, "BLACK",
    		ignorelist, addpcs);
    }
    public static void setUpBoardWithKnightCheckingKing(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardWithKnightCheckingKing(gid);
    	else setUpBoardWithKnightCheckingKingWithoutMovingThere(gid);
    }
    
    //black queen checks white king
    public static void setUpFutureCheck(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
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
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	System.out.println();
    	
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 7, 6, "WHITE", 7, 7, "WHITE", 7, 5, "WHITE", 6, 6, "WHITE", 4, 7, "BLACK",
    		ignorelist, addpcs);
    	System.out.println();
    	//System.out.println(ChessPiece.getCountsForEachPieceTypeForASide(ChessPiece.getPieceTypes(
    	//			ChessPiece.filterListByColor(nwpcslist, "BLACK"))));
    	//String[] tstpctps = {"KING", "CASTLE", "CASTLE", "CASTLE", "CASTLE", "CASTLE", "CASTLE", "CASTLE", "CASTLE",
    	//	"CASTLE", "CASTLE", "QUEEN", "QUEEN"};
    	//System.out.println(ChessPiece.getCountsForEachPieceTypeForASide(tstpctps));
    	//System.out.println(ChessPiece.isBoardValid(nwpcslist));
    	
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
    
    //SETUP BOARD CHECKMATE
    
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
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(null, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(null, null, gid));
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
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 0, 4, "BLACK", 0, 6, "BLACK", 0, 7, "BLACK", 0, 5, "BLACK", 1, 4, "BLACK", 1, 5, "WHITE",
    		ignorelist, addpcs);
    }
    public static void setUpBoardWithFourMoveCheckMate(int gid, boolean movethere)
    {
    	if (movethere) setUpBoardWithFourMoveCheckMate(gid);
    	else setUpBoardWithFourMoveCheckMateWithoutMovingThere(gid);
    }
    
    public static void setUpBoardWithTwoMoveCheckMateBlack(int gid)
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
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(null, null, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(null, null, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(null, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(null, null, gid));
    }
    public static void setUpBoardWithTwoMoveCheckMateBlackWithoutMovingThere(int gid)
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
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 0, 4, "BLACK", 0, 6, "BLACK", 0, 7, "BLACK", 0, 5, "BLACK", 1, 4, "BLACK", 3, 7, "WHITE",
    		ignorelist, addpcs);
    }
    
    public static void setUpBoardWithTwoMoveCheckMateWhite(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//WPN AT F7 -> F6; OWPN AT G7 -> G5;
    	//BPN AT E2 -> E3; BQN AT D1 -> H5
    	
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 5, gid);//F7
    	wpn.genMoveToCommand(5, 5);//F6
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 4, gid);//E2
    	bpn.genMoveToCommand(2, 4);//E3
    	ChessPiece owpn = ChessPiece.getPieceAt(6, 6, gid);//G7
    	owpn.genMoveToCommand(4, 6);//G5
    	ChessPiece bqn = ChessPiece.getPieceAt(0, 3, gid);//D1
    	bqn.genMoveToCommand(4, 7);//H5
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(null, null, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(null, null, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(null, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(null, null, gid));
    }
    public static void setUpBoardWithTwoMoveCheckMateWhiteWithoutMovingThere(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//WPN AT F7 -> F6; OWPN AT G7 -> G5;
    	//BPN AT E2 -> E3; BQN AT D1 -> H5
    	
    	int[][] ignorelist = new int[4][2];
    	ignorelist[0] = ChessPiece.convertStringLocToRowCol("E2");
    	ignorelist[1] = ChessPiece.convertStringLocToRowCol("F7");
    	ignorelist[2] = ChessPiece.convertStringLocToRowCol("D1");
    	ignorelist[3] = ChessPiece.convertStringLocToRowCol("G7");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("F6"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", ChessPiece.convertStringLocToRowCol("G5"), gid, 1, false));
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", ChessPiece.convertStringLocToRowCol("H5"), gid, 1, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", ChessPiece.convertStringLocToRowCol("E3"), gid, 1, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	//pcslocs in the order of: king, knight, castle (rook), bishop, pawn, queen
    	testCanMoveToLocs(gid, 7, 4, "WHITE", 7, 6, "WHITE", 7, 7, "WHITE", 7, 5, "WHITE", 7, 4, "WHITE", 4, 7, "BLACK",
    		ignorelist, addpcs);
    }
    //calls the above two move checkmate methods
    public static void setUpBoardWithTwoMoveCheckMate(int gid, boolean usewhite, boolean movethere)
    {
    	if (usewhite)
    	{
    		if (movethere) setUpBoardWithTwoMoveCheckMateWhite(gid);
    		else setUpBoardWithTwoMoveCheckMateWhiteWithoutMovingThere(gid);
    	}
    	else
    	{
    		if (movethere) setUpBoardWithTwoMoveCheckMateBlack(gid);
    		else setUpBoardWithTwoMoveCheckMateBlackWithoutMovingThere(gid);
    	}
    }
    public static void setUpBoardWithTwoMoveCheckMateWhite(int gid, boolean movethere)
    {
    	setUpBoardWithTwoMoveCheckMate(gid, true, movethere);
    }
    public static void setUpBoardWithTwoMoveCheckMateBlack(int gid, boolean movethere)
    {
    	setUpBoardWithTwoMoveCheckMate(gid, false, movethere);
    }
    
    public static void setUpBoardCheckmateKingBishopVSameDiffColorSquares(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
    	//else;//do nothing
    	
    	//ignore everything except the kings
    	//then add a bunch of blocked pawns that cannot move
    	int[][] ignorelist = new int[32][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			//if (c == 4 && (r == 0 || r == 7));
    			//else
    			//{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			//}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("BISHOP", "BLACK", 1, 0, gid, 5, false));
    	addpcs.add(new ChessPiece("BISHOP", "WHITE", 2, 2, gid, 5, false));
    	ChessPiece bkg = new ChessPiece("KING", "BLACK", 0, 0, gid, 5, false);
    	addpcs.add(bkg);
    	ChessPiece wkg = new ChessPiece("KING", "WHITE", 1, 2, gid, 5, false);
    	addpcs.add(wkg);
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 1, 2, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 0, "BLACK", ignorelist, addpcs);
    	testBishopCanMoveToLocs(gid, 1, 0, "BLACK", ignorelist, addpcs);
    	testBishopCanMoveToLocs(gid, 2, 2, "WHITE", ignorelist, addpcs);
    }
    
    public static void setUpBoardWhiteCheckmateAfterManyMoves(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
    	//else;//do nothing
    	
    	//ignore all pieces except white pawn at 6, 0 A6
    	int[][] ignorelist = new int[31][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			//if (c == 4 && (r == 0 || r == 7));
    			if (r == 6 && c == 0);//keep the white pawn
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	ChessPiece bkg = new ChessPiece("KING", "BLACK", 0, 6, gid, 3, false);
    	addpcs.add(bkg);
    	ChessPiece wkg = new ChessPiece("KING", "WHITE", 7, 0, gid, 4, false);
    	addpcs.add(wkg);
    	addpcs.add(new ChessPiece("CASTLE", "BLACK", 0, 1, gid, 1, false));
    	addpcs.add(new ChessPiece("CASTLE", "BLACK", 7, 4, gid, 3, false));
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", 5, 0, gid, 2, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, addpcs, gid));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 0, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 6, "BLACK", ignorelist, addpcs);
    	//testBishopCanMoveToLocs(gid, r, c, "BLACK", ignorelist, addpcs);
    	//testBishopCanMoveToLocs(gid, r, c, "WHITE", ignorelist, addpcs);
    }
    
    //SETUP BOARD STALEMATE
    
    public static void setUpBoardWithKingVKingBlockedPawnsWithoutMoving(int gid, boolean addstuckpawns)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//ignore everything except the kings
    	//then add a bunch of blocked pawns that cannot move
    	int[][] ignorelist = new int[30][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			if (c == 4 && (r == 0 || r == 7));
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = null;
    	int incbyval = 2;//change this for the spacing of the pawns, must be 2 or 3 for stalemate
    	int csi = 0;//change where the pawns start colum
    	int rsi = 3;//change where the pawns start row
    	if (addstuckpawns)
    	{
    		addpcs = new ArrayList<ChessPiece>();
    		for (int r = rsi; r < rsi + 2 && r < 8; r++)
    		{
    			String npcclr = "";
    			int initmvcnt = 1;
    			if (r == rsi)
    			{
    				npcclr = "BLACK";
    				if (rsi == 3) initmvcnt = 1;
    				else if (rsi == 2) initmvcnt = 1;
    				else if (rsi == 4) initmvcnt = 2;
    				else if (rsi == 5) initmvcnt = 3;
    				else if (rsi == 1) initmvcnt = 0;
    				else throw new IllegalStateException("Illegal postion for BLACK row of pawns!");
    			}
    			else
    			{
    				npcclr = "WHITE";
    				if (rsi == 3) initmvcnt = 1;
    				else if (rsi == 2) initmvcnt = 2;
    				else if (rsi == 4) initmvcnt = 1;
    				else if (rsi == 5) initmvcnt = 0;
    				else if (rsi == 1) initmvcnt = 3;
    				else throw new IllegalStateException("Illegal postion for WHITE row of pawns!");
    			}
    			//if c increments by 3, 2 stalemate, but if not game is not over
    			//if colors of the kings are swapped, game is not over
    			for (int c = csi; c < 8; c += incbyval)
    			{
    				addpcs.add(new ChessPiece("PAWN", "" + npcclr, r, c, gid, initmvcnt, false));
    			}
    		}
    	}
    	//else;//do nothing
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	//System.out.println("WHITE HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesWhiteHaveNoLegalMoves(ignorelist, addpcs, gid));
    	//System.out.println("BLACK HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesBlackHaveNoLegalMoves(ignorelist, addpcs, gid));
    	//ChessPiece.printLocsArray(ChessPiece.getAllLocsThatCanBeReachedByPiece(wkg.getRow(), wkg.getCol(), "KING", "WHITE",
    	//	ignorelist, addpcs, gid), "wkingpossiblecanmovetolocs");
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 4, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 4, "BLACK", ignorelist, addpcs);
    	if (addstuckpawns)
    	{
    		int ccl = -1;
    		if (csi == 0)
    		{
    			if (incbyval == 2 || incbyval == 1) ccl = 4;
    			else ccl = 3;
    		}
    		else if (csi == 1)
    		{
    			if (incbyval == 1) ccl = 4;
    			else if (incbyval == 3) ccl = 4;
    			else ccl = 3;
    		}
    		//else;//do nothing not a stalemate
    		testPawnCanMoveToLocs(gid, 3, ccl, "BLACK", ignorelist, addpcs);
    		testPawnCanMoveToLocs(gid, 4, ccl, "WHITE", ignorelist, addpcs);
    	}
    	//else;//do nothing
    }
    public static void setUpBoardWithKingVKingAndStuckPawnsWithoutMovingThere(int gid)
    {
    	setUpBoardWithKingVKingBlockedPawnsWithoutMoving(gid, true);
    }
    public static void setUpBoardWithKingVKingOnlyWithoutMovingThere(int gid)
    {
    	setUpBoardWithKingVKingBlockedPawnsWithoutMoving(gid, false);
    }
    
    public static void setUpBoardWithKingAndBishopsVKingBishops(int gid, int numbkbps, int numwtbps, boolean usesmt)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else if ((0 < numbkbps || numbkbps == 0) && (numbkbps < 8 || numbkbps == 8));
		else throw new IllegalStateException("illegal number of black bishops found and used here!");
		if ((0 < numwtbps || numwtbps == 0) && (numwtbps < 8 || numwtbps == 8));
		else throw new IllegalStateException("illegal number of white bishops found and used here!");
		
		//ignore everything except the kings
    	//then add a bunch of blocked pawns that cannot move
    	int[][] ignorelist = new int[30][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			if (c == 4 && (r == 0 || r == 7));
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	int r = 0;
    	int c = 0;
    	for (int x = 0; x < numbkbps; x++)
    	{
    		r = x;
    		c = x;
    		addpcs.add(new ChessPiece("BISHOP", "BLACK", r, c, gid, 5, false));
    	}
    	r = 0;
    	c = 0;
    	int csi = 6;
    	if (usesmt) csi = 6;
    	else csi = 7;
    	for (int x = 0; x < numwtbps; x++)
    	{
    		r = x;
    		c = csi - x;
    		if (r == c)
    		{
    			x--;
    			continue;
    		}
    		else addpcs.add(new ChessPiece("BISHOP", "WHITE", r, c, gid, 5, false));
    	}
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("ALL BISHOPS ON SAME COLOR SQUARES: " + ChessPiece.areAllBishopsOnSameColorSquare(nwpcslist));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, addpcs, gid));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	//System.out.println("WHITE HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesWhiteHaveNoLegalMoves(ignorelist, addpcs, gid));
    	//System.out.println("BLACK HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesBlackHaveNoLegalMoves(ignorelist, addpcs, gid));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 4, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 4, "BLACK", ignorelist, addpcs);
    	//testBishopCanMoveToLocs(gid, int br, int bc, String bclr, ignorelist, addpcs);
    }
    public static void setUpBoardWithKingAndBishopsVKingBishops(int gid, int numbkbps, int numwtbps)
    {
    	setUpBoardWithKingAndBishopsVKingBishops(gid, numbkbps, numwtbps, true);
    }
    
    public static void setUpBoardWithBlockedPawnsAndBishops(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
		//ignore everything except the kings
    	//then add a bunch of blocked pawns that cannot move
    	int[][] ignorelist = new int[30][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			//if (c == 4 && (r == 0 || r == 7));//keep the kings
    			if ((r == 1 || r == 6) && (c == 4 || c == 6));//keep 2 black and white pawns
    			else if ((r == 0 || r == 7) && c == 5);//keep a black and a white bishop
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	ChessPiece bkg = new ChessPiece("KING", "BLACK", 0, 7, gid, 5, false);
    	ChessPiece wkg = new ChessPiece("KING", "WHITE", 7, 7, gid, 5, false);
    	addpcs.add(bkg);
    	addpcs.add(wkg);
    	addpcs.add(new ChessPiece("PAWN", "BLACK", 5, 4, gid, 5, false));
    	addpcs.add(new ChessPiece("PAWN", "BLACK", 5, 6, gid, 5, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", 2, 4, gid, 5, false));
    	addpcs.add(new ChessPiece("PAWN", "WHITE", 2, 6, gid, 5, false));
    	addpcs.add(new ChessPiece("BISHOP", "WHITE", 3, 1, gid, 5, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("ALL BISHOPS ON SAME COLOR SQUARES: " + ChessPiece.areAllBishopsOnSameColorSquare(nwpcslist));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, addpcs, gid));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	//System.out.println("WHITE HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesWhiteHaveNoLegalMoves(ignorelist, addpcs, gid));
    	//System.out.println("BLACK HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesBlackHaveNoLegalMoves(ignorelist, addpcs, gid));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 7, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 7, "BLACK", ignorelist, addpcs);
    	testBishopCanMoveToLocs(gid, 3, 1, "WHITE", ignorelist, addpcs);
    	testBishopCanMoveToLocs(gid, 7, 5, "WHITE", ignorelist, addpcs);
    }
    
    public static void setUpBoardWithKingAndKnightVKing(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
    	//else;//do nothing
    	
    	//ignore everything except the kings
    	//then add a bunch of blocked pawns that cannot move
    	int[][] ignorelist = new int[30][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			if (c == 4 && (r == 0 || r == 7));//keep kings
    			else if (c == 6 && r == 7);//keep a knight
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	//do not need to add any pieces
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, null, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, null));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, null));
    	System.out.println("ALL BISHOPS ON SAME COLOR SQUARES: " + ChessPiece.areAllBishopsOnSameColorSquare(nwpcslist));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, null, gid));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	//System.out.println("WHITE HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesWhiteHaveNoLegalMoves(ignorelist, null, gid));
    	//System.out.println("BLACK HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesBlackHaveNoLegalMoves(ignorelist, null, gid));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, null, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, null, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, null, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, null, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 4, "WHITE", ignorelist, null);
    	testKingCanMoveToLocs(gid, 0, 4, "BLACK", ignorelist, null);
    	testKnightCanMoveToLocs(gid, 7, 6, "WHITE", ignorelist, null);
    }
    
    public static void setUpBoardWhiteStalemateAfterManyMoves(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
    	//else;//do nothing
    	
    	//ignore all pieces except white pawn at 6, 0 A6
    	int[][] ignorelist = new int[31][2];
    	int ili = 0;
    	for (int r = 0; r < 8; r++)
    	{
    		for (int c = 0; c < 8; c++)
    		{
    			//if (c == 4 && (r == 0 || r == 7));
    			if (r == 6 && c == 0);//keep the white pawn
    			else
    			{
    				ignorelist[ili][0] = r;
    				ignorelist[ili][1] = c;
    				ili++;
    			}
    		}
    		if (r == 1) r = 5;
    	}
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	ChessPiece bkg = new ChessPiece("KING", "BLACK", 0, 6, gid, 3, false);
    	addpcs.add(bkg);
    	ChessPiece wkg = new ChessPiece("KING", "WHITE", 7, 0, gid, 4, false);
    	addpcs.add(wkg);
    	addpcs.add(new ChessPiece("CASTLE", "BLACK", 0, 1, gid, 1, false));
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", 5, 0, gid, 2, false));
    	//print the board first
    	ArrayList<ChessPiece> nwpcslist = ChessPiece.combineBoardAddAndIgnoreLists(ignorelist, addpcs, gid);
    	//System.out.println(nwpcslist);
    	printBoard(nwpcslist);
    	//test stale mate and check detection here and methods determinging where a piece can move to
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck(ignorelist, addpcs));
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck(ignorelist, addpcs));
    	System.out.println("FREE PIECES: " + ChessPiece.getPiecesThatAreFreeToMove(ignorelist, addpcs, gid));
    	//System.out.println("IS AUTO-STALEMATE: " + ChessPiece.isAutoStalemate(nwpcslist));
    	//System.out.println("WHITE HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesWhiteHaveNoLegalMoves(ignorelist, addpcs, gid));
    	//System.out.println("BLACK HAS NO LEGAL MOVES IT CAN MAKE: " +
    	//	ChessPiece.doesBlackHaveNoLegalMoves(ignorelist, addpcs, gid));
    	System.out.println("WHITE IN STALEMATE: " + ChessPiece.isStalemateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IN STALEMATE: " + ChessPiece.isStalemateBlack(ignorelist, addpcs, gid));
    	System.out.println("WHITE IS IN CHECKMATE: " + ChessPiece.inCheckmateWhite(ignorelist, addpcs, gid));
    	System.out.println("BLACK IS IN CHECKMATE: " + ChessPiece.inCheckmateBlack(ignorelist, addpcs, gid));
    	
    	testKingCanMoveToLocs(gid, 7, 0, "WHITE", ignorelist, addpcs);
    	testKingCanMoveToLocs(gid, 0, 6, "BLACK", ignorelist, addpcs);
    	//testBishopCanMoveToLocs(gid, r, c, "BLACK", ignorelist, addpcs);
    	//testBishopCanMoveToLocs(gid, r, c, "WHITE", ignorelist, addpcs);
    }
    
    //NORMAL BOARD SETUP METHOD
    
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
