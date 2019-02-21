import java.awt.Color;
import java.awt.Font;
import arc.*;

@SuppressWarnings("unused")
public class RPGMain
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);

		int intNext = 0;
		int intScore = 0;
		int intX; // The starting position is at (19,2), but the array starts at 0
		int intY; // Top left corner is (0,0).
		int intPreX = 22;
		int intPreY = 1;
		int intBossHP;
		int intRow;
		int intColumn;
		int intEndBattle = 0;
		char chrItem = ' ';
		char chrInput = ' ';
		char chrMove = ' ';
		Boolean temp = true;
		// Font nameFont = con.loadFont("Hack-Regular.ttf", 20);
		String strUsername;
		String strMap[][] = new String[20][20];
		Boolean blnQuit = false;
		Boolean blnWin = false;
		Boolean blnLose = false;
		TextOutputFile highscores = new TextOutputFile("highscores.txt", true);

		// Menu Loop
		while (blnQuit == false)
		{
			// Menu
			Tools.clearUI(con);
			intNext = Tools.menu(con, chrInput);
			// jethroWTools.logo(con);

			if (intNext == 1)
			{
				con.clear();

				// Clear Screen
				Tools.clearUI(con);

				// Reset Player Stats
				// jethroWTools.resetStats(con);
				intY = 1;
				intX = 22;

				// Load Map

				// Get username
				// con.setTextFont(nameFont);
				con.println("Enter your username:");
				strUsername = con.readLine();
				con.clear();

				// Print Map
				if (temp == true)
				{
					Tools.clearUI(con);

					strMap = Tools.loadMap(con, strMap);
					Tools.printMap(con, strMap, intX, intY);

					// Start Game
					for (intScore = 0; blnWin == false; intScore++)
					{
						if (chrMove == 'w' && intY != 0 && !strMap[intY - 1][intX].equals("u")
								&& !strMap[intY - 1][intX].equals("l"))
						{
							intY--;
						}
						else if (chrMove == 'a' && intX != 0 && !strMap[intY][intX - 1].equals("u")
								&& !strMap[intY][intX - 1].equals("l"))
						{
							intX--;
						}
						else if (chrMove == 's' && intY != 19 && !strMap[intY + 1][intX].equals("u")
								&& !strMap[intY + 1][intX].equals("l"))
						{
							intY++;
						}
						else if (chrMove == 'd' && intX != 19 && !strMap[intY][intX + 1].equals("u")
								&& !strMap[intY][intX + 1].equals("l"))
						{
							intX++;
						}
						// SECRET CODE
						else if (chrMove == ']')
						{
							blnWin = true;
						}

						Tools.printMap(con, strMap, intX, intY);

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
				if (blnWin == true && blnLose == false)
				{
					
				}
				// Lose Screen
				else
				{
					
				}
				blnWin = false;
			}
			// Control Menu
			else if (intNext == 2)
			{
				con.println("control menu");
				intNext = con.readInt();
			}
			// Help Menu
			else if (intNext == 3)
			{
				con.println("help menu");
				intNext = con.readInt();
			}
			// Highscores
			else if (intNext == 4)
			{
				con.println("highscores menu");
				intNext = con.readInt();
			} else if (intNext == 5)
			{
				blnQuit = true;
			}
			if (intNext == 5)
			{
				con.closeConsole();
			}
		}

	}
}
