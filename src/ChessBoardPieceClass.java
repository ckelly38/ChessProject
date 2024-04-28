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
    	//ChessPiece cp = new ChessPiece("PAWN", "WHITE", 6, 0);
    	//System.out.println(cp);
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
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(6, 0));
    	//System.out.println();
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(7, 4));
    	//System.out.println();
    	System.out.println("GETTING ENEMY PIECES GUARDING LOCATION (7, 4) " +
    		ChessPiece.convertRowColToStringLoc(7, 4) + " NOW:");
    	System.out.println(ChessPiece.getEnemyPiecesGuardingLocation(7, 4, gid, null));
    	
    	System.out.println("GETTING ALL PIECES GUARDING LOCATION (6, 7) " +
    		ChessPiece.convertRowColToStringLoc(6, 7) + " NOW:");
    	int[] nloc = ChessPiece.convertStringLocToRowCol("H4");
    	ArrayList<ChessPiece> addpcs = new ArrayList<ChessPiece>();
    	addpcs.add(new ChessPiece("QUEEN", "BLACK", nloc, gid, false));
    	//int[][] ignorelist = null;
    	System.out.println("NORMAL RESULTS WITHOUT ADDING ANY PIECES OR EXCLUDING ANY:");
    	ArrayList<ChessPiece> nrmlcps = ChessPiece.getPiecesGuardingLocation(6, 7, gid, null, null);//ignorelist, addpcs
    	System.out.println(nrmlcps);
    	System.out.println("RESULTS WITH THE ADDITION OF A BLACK QUEEN AT (6, 7):");
    	ArrayList<ChessPiece> nrmlwithaddpcs = ChessPiece.getPiecesGuardingLocation(6, 7, gid, null, addpcs);
    	//ignorelist, addpcs
    	System.out.println(nrmlwithaddpcs);
    	
    	printBoard(gid);
    	//not true for all test cases, but must be true for this case
    	if (nrmlcps.size() + 1 == nrmlwithaddpcs.size());
    	else throw new IllegalStateException("ADDING A QUEEN TEST FAILED!");
    	System.out.println(ChessPiece.isBoardValid(gid));
    	
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    	System.out.println("WHITE QUEEN IS IN CHECK: " + wqn.inCheck());
    	System.out.println("BLACK QUEEN IS IN CHECK: " + bqn.inCheck());
    	//getAndPrintAllPiecesGenders();
    	System.out.println();
    	//System.out.println(ChessPiece.getPiecesGuardingLocation(2, 2));
    	//System.out.println();
    	//testConvertingLocations();
    	System.out.println(ChessPiece.canSideCastle("WHITE", gid));
    	System.out.println(ChessPiece.canSideCastle("BLACK", gid));
    	System.out.println();
    	
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 0, gid);
    	wpn.genMoveToCommand(4, 0);
    	//wpn.moveTo(4, 0);
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 1, gid);
    	bpn.genMoveToCommand(3, 1);
    	//bpn.moveTo(3, 1);
    	wpn.genMoveToCommand(3, 1);
    	//wpn.moveTo(3, 1);
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
    
    public static void printBoard(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	ArrayList<ChessPiece> mycps = ChessPiece.getAllPiecesWithGameID(gid);
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
    
    public static void setUpBoardForPawning(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
    	
    	//BPN AT B2 -> B4 -> B5; WPN AT A7 -> A5; WKT AT G8 -> H6 -> F5
    	ChessPiece wkt = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("G8"), gid);
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("H6"));
    	ChessPiece bpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("B2"), gid);
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B4"));
    	ChessPiece wpn = ChessPiece.getPieceAt(ChessPiece.convertStringLocToRowCol("A7"), gid);
    	wpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("A5"));
    	bpn.genMoveToCommand(ChessPiece.convertStringLocToRowCol("B5"));
    	wkt.genMoveToCommand(ChessPiece.convertStringLocToRowCol("F5"));
    	//now test pawning methods here
    	System.out.println("WHITE CAN PAWN: " + ChessPiece.canSidePawn("WHITE", gid));
    	System.out.println("BLACK CAN PAWN: " + ChessPiece.canSidePawn("BLACK", gid));
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
    
    public static void setUpBoardWithFourMoveCheckMate(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 6, gid);
    	wpn.genMoveToCommand(5, 6);
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 0, gid);
    	bpn.genMoveToCommand(3, 0);
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);
    	wqn.genMoveToCommand(5, 5);
    	bpn.genMoveToCommand(4, 0);
    	ChessPiece wbp = ChessPiece.getPieceAt(7, 5, gid);
    	wbp.genMoveToCommand(4, 2);
    	bpn.genMoveToCommand(5, 0);
    	wqn.genMoveToCommand(1, 5);
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
    }
    public static void setUpBoardWithTwoMoveCheckMate(int gid)
    {
    	if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 6, gid);
    	wpn.genMoveToCommand(5, 6);
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 5, gid);
    	bpn.genMoveToCommand(2, 5);
    	ChessPiece owpn = ChessPiece.getPieceAt(6, 0, gid);
    	owpn.genMoveToCommand(5, 0);
    	ChessPiece obpn = ChessPiece.getPieceAt(1, 6, gid);
    	obpn.genMoveToCommand(3, 6);
    	ChessPiece wqn = ChessPiece.getPieceAt(7, 3, gid);
    	wqn.genMoveToCommand(3, 7);
    	//test check mate and check detection here and methods determinging where a piece can move to
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE", gid);
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK", gid);
    	System.out.println("WHITE KING IS IN CHECK: " + wkg.inCheck());
    	System.out.println("BLACK KING IS IN CHECK: " + bkg.inCheck());
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
