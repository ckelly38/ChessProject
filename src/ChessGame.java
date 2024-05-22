
import java.util.ArrayList;
class ChessGame {
	private String lastsetlocmv = null;
	private int gameID = -1;
	public static ArrayList<ChessGame> all = new ArrayList<ChessGame>();
	
	public ChessGame(int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		//else;//do nothing
		for (int x = 0; x < all.size(); x++)
		{
			if (all.get(x).getGameID() == gid) throw new IllegalStateException("there must be only one game with that ID!");
		}
		this.gameID = gid;
		all.add(this);
	}
	
	public int getGameID()
	{
		return gameID;
	}
	
	public static ChessGame getGame(int gid)
	{
		if (gid < 1) throw new IllegalStateException("GAME ID must be at least 1!");
		else
		{
			for (int x = 0; x < all.size(); x++)
			{
				if (all.get(x).getGameID() == gid) return all.get(x);
			}
			return null;
		}
	}
	
	public void setLastSetLocMove(String mvstr)
	{
		if (mvstr == null) lastsetlocmv = null;
		else lastsetlocmv = "" + mvstr;
	}
	public String getLastSetLocMove()
	{
		return lastsetlocmv;
	}
}
