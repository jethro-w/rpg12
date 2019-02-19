import arc.*;

public class test
{
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);
		
		jethroWTools.drawGameConsole(con);
		
		int intRow;
	    int intColumn;
	    int intPlayerX = 1;
	    int intPlayerY = 1;
	    String[] strMapLine = new String [20];
	    String[][] strMap = new String[20][20];
	    TextInputFile map = new TextInputFile("map.csv");
	    
	    // Load Map into Char array
	    for(intRow = 0; map.eof() == false; intRow++)
	    {
	      strMapLine[intRow] = map.readLine();
	    }
	    map.close();
	    
	    for(intRow = 0; intRow < 20; intRow++)
	    {
	    	for(intColumn = 0; intColumn < 20; intColumn++)
	    	{
	    		strMap[intColumn] = strMapLine[intColumn].split(",");
	    	}
	    }
	    
	    jethroWTools.printMap(con, strMap, intPlayerX, intPlayerY);
	}
}
