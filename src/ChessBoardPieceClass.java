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
    	setUpBoard();
    	System.out.println("DONE SETTING UP THE BOARD!");
    	//ArrayList<ChessPiece> mycps = ChessPiece.cps;
    	//for (int c = 0; c < mycps.size(); c++) System.out.println(mycps.get(c));
    	printBoard();
    	System.out.println(ChessPiece.isBoardValid());
    	ChessPiece wkg = ChessPiece.getCurrentSideKing("WHITE");
    	ChessPiece bkg = ChessPiece.getCurrentSideKing("BLACK");
    	System.out.println(wkg);
    	System.out.println(bkg);
    	ChessPiece item = ChessPiece.getPieceAt(4, 0);
    	System.out.println(item);
    	ChessPiece wpn = ChessPiece.getPieceAt(6, 0);
    	wpn.genMoveToCommand(4, 0);
    	//wpn.moveTo(4, 0);
    	ChessPiece bpn = ChessPiece.getPieceAt(1, 1);
    	bpn.genMoveToCommand(3, 1);
    	//bpn.moveTo(3, 1);
    	wpn.genMoveToCommand(3, 1);
    	//wpn.moveTo(3, 1);
    }
    
    public static void printBoard()
    {
    	ArrayList<ChessPiece> mycps = ChessPiece.cps;
    	for (int c = 0; c < mycps.size(); c++) System.out.println(mycps.get(c));
    	System.out.println("mycps.size() = " + mycps.size());
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
    		System.out.println("|");
    	}
    }
    
    public static void setUpBoard()
    {
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
	    		ChessPiece cp = new ChessPiece("PAWN", clr, r, c);
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
	    				ChessPiece ocp = new ChessPiece(mvtypes[k], clr, orw, nwcl);
	    				//cps.add(ocp);
	    				if (mvtypes[k].equals("KING") || mvtypes[k].equals("QUEEN")) break;
	    			}//end of i for loop
	    		}
	    	}//end of k for loop
    	}//end of x for loop
    }
}
