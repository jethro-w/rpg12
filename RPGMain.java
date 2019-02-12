import java.awt.Font;
import arc.*;

import arc.*;

public class RPGMain
{
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
	    Font nameFont = con.loadFont("Hack-Regular.ttf", 20);
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
	      intNext = jethroWTools.menu(con, chrInput);
	      jethroWTools.logo(con);
	      
	      if(intNext == 1)
	      {
	        // Clear Screen
	        jethroWTools.clear(con);
	        
	        // Reset Player Stats
	        jethroWTools.resetStats(con);
	        intY = 1;
	        intX = 18;
	        
	        // Load Map
	        strMapLine = jethroWTools.loadMap(con);
	        
	        // Get username
	        con.setTextFont(nameFont);
	        con.println("Enter your username:");
	        strUsername = con.readLine();
	        con.clear();
	        
	        // Choose Clan Type
	        
	        // Print Map
	        if(chrClan == 'f' || chrClan == 'e' || chrClan == 'w')
	        {
	          jethroWTools.clear(con);
	          strMap = jethroWTools.printMap(con, strMapLine);
	          // Start Game
	          System.out.println(chrMove);
	          for(intScore = 0; blnWin == false; intScore++)
	          {
	            	            
	            jethroWTools.justPrintMap(con, strMap);
	            
	            while(chrMove != 'w' && chrMove != 'a' && chrMove != 's' && chrMove != 'd' && chrMove != ']' && chrMove != 'q')
	            {
	              chrMove = con.getChar();
	            }
	            
	            jethroWTools.justPrintMap(con, strMap);
	            System.out.println(chrMove);
	            
	            // Player vs. Enemy
	            if(strMap[intY][intX].equals("e") || strMap[intY][intX].equals("b"))
	            {
	            
	              jethroWTools.clear(con);
	            
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
	          highscores.println(strUsername);
	          highscores.println(intScore);
	          highscores.println(chrClan);
	          jethroWTools.clear(con);
	          jethroWTools.vScreen(con);
	        }
	        // Lose Screen
	        else
	        {
	          jethroWTools.clear(con);
	          jethroWTools.lScreen(con);
	        }
	        blnWin = false;
	      }
	      // Control Menu
	      else if(intNext == 2)
	      {
	        jethroWTools.clear(con);
	        jethroWTools.controlMenu(con);
	        con.getChar();
	      }
	      // Help Menu
	      else if(intNext == 3)
	      {
	        jethroWTools.clear(con);
	        jethroWTools.helpMenu(con);
	      }
	      // Highscores
	      else if(intNext == 4)
	      {
	        jethroWTools.clear(con);
	        jethroWTools.highscores(con);
	        con.getChar();
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
