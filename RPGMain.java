import java.awt.Font;
import arc.*;

@SuppressWarnings("unused")
public class RPGMain
{
	@SuppressWarnings("unused")
	public static void main (String[] args)
	{
		Console con = new Console();
		
		int intNext = 0;
	    int intScore = 0;
	    int intX = 18;   // The starting position is at (19,2), but the array starts at 0
	    int intY = 1;    // Top left corner is (0,0).
	    int intPreX = 18;
	    int intPreY = 1;
	    int intBossHP;
	    int intRow;
	    int intColumn;
	    int intEndBattle = 0;
	    char chrItem = ' ';
	    char chrInput;
	    char chrMove = ' ';
	    boolean temp = true;
	    //Font nameFont = con.loadFont("Hack-Regular.ttf", 20);
	    String strUsername;
	    String strMap[][] = new String[20][20];
	    Boolean blnQuit = false;
	    Boolean blnWin = false;
	    Boolean blnLose = false;
	    TextOutputFile highscores = new TextOutputFile("highscores.txt", true);
	    
	    // Menu Loop
	    while(blnQuit == false)
	    {
	      // Menu
	      jethroWTools.clear(con);
	      //intNext = jethroWTools.menu(con, chrInput);
	      //jethroWTools.logo(con);
	      
	      con.println("Main Menu:");
	      intNext = con.readInt();
	      
	      if(intNext == 1)
	      {
	    	con.println("Game");
	    	
	        // Clear Screen
	        jethroWTools.clear(con);
	        
	        // Reset Player Stats
	        //jethroWTools.resetStats(con);
	        intY = 1;
	        intX = 18;
	        
	        // Load Map
	        
	        // Get username
	        //con.setTextFont(nameFont);
	        con.println("Enter your username:");
	        strUsername = con.readLine();
	        con.clear();
	        
	        // Print Map
	        if(temp == true)
	        {
	          jethroWTools.clear(con);
	          
	          strMap = jethroWTools.loadMap(con, strMap);
	          jethroWTools.printMap(con, strMap);
	          
	          // Start Game
	          for(intScore = 0; blnWin == false; intScore++)
	          {
	        	intNext = con.readInt();
	        	if(intNext != 0)
	        	{
	        		blnWin = true;
	        	}
	            	            
	            // Player vs. Enemy
	            
	            // Items
	            
	            // Reset variables
	            intEndBattle = 0;
	            intPreX = intX;
	            intPreY = intY;
	            chrMove = ' ';
	            chrItem = ' ';
	          }
	        }
	        // Victory Screen
	        if(blnWin == true && blnLose == false)
	        {
	          con.println("victory");
	        }
	        // Lose Screen
	        else
	        {
	        	
	        }
	        blnWin = false;
	      }
	      // Control Menu
	      else if(intNext == 2)
	      {
	        
	        con.println("control menu");
	        intNext = con.readInt();
	      }
	      // Help Menu
	      else if(intNext == 3)
	      {
	        con.println("help menu");
	        intNext = con.readInt();
	      }
	      // Highscores
	      else if(intNext == 4)
	      {
	        con.println("highscores menu");
	        intNext = con.readInt();
	      }
	      else if(intNext == 5)
	      {
	        blnQuit = true;
	      }
	      if(intNext == 5)
	      {
	        con.closeConsole();
	      }
	    }
		
	}
}
