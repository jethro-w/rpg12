import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import arc.*;

@SuppressWarnings("unused")
public class RPGMain
{
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);

		int intNext = 0;
		int intScore = 0;
		int intX = 0; // The starting position is at (19,2), but the array starts at 0
		int intY = 0; // Top left corner is (0,0).
		int intPrevX = 22;
		int intPrevY = 1;
		int intEndBattle = 0;
		int intMap;
		char chrInput = ' ';
		char chrItem = ' ';
		char chrMove = ' ';
		char chrPrevMove = ' ';
		Boolean temp = true;
		// Font nameFont = con.loadFont("Hack-Regular.ttf", 20);
		String strUsername;
		String strMap[][] = new String[20][20];
		Boolean blnQuit = false;
		Boolean blnWin = false;
		Boolean blnLose = false;
		BufferedImage playerLeft = con.loadImage("anglerLeft.png");
		BufferedImage playerRight = con.loadImage("anglerRight.png");
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

				// Load Map

				// Get username
				// con.setTextFont(nameFont);
				con.println("Enter your username:");
				strUsername = con.readLine();

				con.println("select map");
				intMap = con.readInt();
				con.clear();

				if (intMap == 1)
				{
					intX = 2;
					intY = 0;
				}
				else if (intMap == 2)
				{
					intX = 19;
					intY = 19;
				}

				// Print Map
				if (temp == true)
				{
					Tools.clearUI(con);

					strMap = Tools.loadMap(con, strMap, intMap);
					Tools.printMap(con, strMap);

					con.drawImage(playerLeft, (intX * 40) + 200, (intY * 40) + 100);
					con.repaint();

					// Start Game
					for (intScore = 0; blnWin == false; intScore++)
					{
						while (chrMove != 'w' && chrMove != 'a' && chrMove != 's' && chrMove != 'd')
						{
							chrMove = con.getChar();
						}

						if (chrMove == 'w' && intY != 0
								&& (strMap[intY - 1][intX].equals("_") || strMap[intY - 1][intX].equals("x")
										|| strMap[intY - 1][intX].equals("X") || strMap[intY - 1][intX].equals("z")
										|| strMap[intY - 1][intX].equals("c") || strMap[intY - 1][intX].equals("v")
										|| strMap[intY - 1][intX].equals("1") || strMap[intY - 1][intX].equals("2")
										|| strMap[intY - 1][intX].equals("3") || strMap[intY - 1][intX].equals("4")))
						{
							intY--;
						}
						else if (chrMove == 'a' && intX != 0
								&& (strMap[intY][intX - 1].equals("_") || strMap[intY][intX - 1].equals("x")
										|| strMap[intY][intX - 1].equals("X") || strMap[intY][intX - 1].equals("z")
										|| strMap[intY][intX - 1].equals("c") || strMap[intY][intX - 1].equals("v")
										|| strMap[intY][intX - 1].equals("1") || strMap[intY][intX - 1].equals("2")
										|| strMap[intY][intX - 1].equals("3") || strMap[intY][intX - 1].equals("4")))
						{
							intX--;
						}
						else if (chrMove == 's' && intY != 19
								&& (strMap[intY + 1][intX].equals("_") || strMap[intY + 1][intX].equals("x")
										|| strMap[intY + 1][intX].equals("X") || strMap[intY + 1][intX].equals("z")
										|| strMap[intY + 1][intX].equals("c") || strMap[intY + 1][intX].equals("v")
										|| strMap[intY + 1][intX].equals("1") || strMap[intY + 1][intX].equals("2")
										|| strMap[intY + 1][intX].equals("3") || strMap[intY + 1][intX].equals("4")))
						{
							intY++;
						}
						else if (chrMove == 'd' && intX != 19
								&& (strMap[intY][intX + 1].equals("_") || strMap[intY][intX + 1].equals("x")
										|| strMap[intY][intX + 1].equals("X") || strMap[intY][intX + 1].equals("z")
										|| strMap[intY][intX + 1].equals("c") || strMap[intY][intX + 1].equals("v")
										|| strMap[intY][intX + 1].equals("1") || strMap[intY][intX + 1].equals("2")
										|| strMap[intY][intX + 1].equals("3") || strMap[intY][intX + 1].equals("4")))
						{
							intX++;
						}
						// SECRET CODE
						else if (chrMove == ']')
						{
							blnWin = true;
						}

						con.repaint();
						// Player vs. Enemy
						 
						if (strMap[intY][intX].equals("x") || strMap[intY][intX].equals("v")
								|| strMap[intY][intX].equals("c") || strMap[intY][intX].equals("X")
								|| strMap[intY][intX].equals("z"))
						{
							intEndBattle = Tools.battle(con, intY, intX, strMap);
							
							if (intEndBattle == 1)
							{
								
							}
							else if (intEndBattle == 2)
							{
								blnWin = true;
							}
						}
						
						Tools.printMap(con, strMap);
						
						if (chrMove == 'a' || (chrPrevMove == 'a' && chrMove != 'd'))
						{
							con.drawImage(playerLeft, (intX * 40) + 200, (intY * 40) + 100);
							if (chrMove == 'a')
							{
								chrPrevMove = chrMove;
							}
						}
						else if (chrMove == 'd' || (chrPrevMove == 'd' && chrMove != 'a'))
						{
							con.drawImage(playerRight, (intX * 40) + 200, (intY * 40) + 100);
							if (chrMove == 'd')
							{
								chrPrevMove = chrMove;
							}
						}
						
						// Items

						// Reset variables
						intEndBattle = 0;
						intPrevX = intX;
						intPrevY = intY;
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
			}
			else if (intNext == 5)
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