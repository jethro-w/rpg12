import java.awt.image.BufferedImage;
import arc.*;

public class RPGMain
{
	public static void main(String[] args)
	{
		Console con = new Console("Cave Quest RPG", 1600, 1000);

		int intNext = 0;
		int intScore = 0;
		int intX = 0; // The starting position is at (19,2), but the array starts at 0
		int intY = 0; // Top left corner is (0,0).
		int intEndBattle = 0;
		int intMap = 0;
		char chrMove = ' ';
		char chrPrevMove = 'a';
		String strMap[][] = new String[20][20];
		Boolean blnQuit = false;
		Boolean blnWin = false;
		Boolean blnLose = false;
		Boolean bln250HP = false;
		BufferedImage playerLeft = con.loadImage("anglerLeft.png");
		BufferedImage playerRight = con.loadImage("anglerRight.png");
		// TextOutputFile highscores = new TextOutputFile("highscores.txt", true);

		// Menu Loop
		while (blnQuit == false)
		{
			// Menu
			Tools.clearAll(con);
			intNext = Tools.menu(con);
			// jethroWTools.logo(con);

			if (intNext == 1)
			{
				con.clear();

				// Clear Screen
				Tools.clearAll(con);

				// Reset All Stats
				Tools.resetStats(con);
				
				Tools.clearAll(con);
				
				// Ask for Map
				intMap = Tools.askForMap(con);
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
				if (intMap == 1 || intMap == 2)
				{
					Tools.clearAll(con);

					strMap = Tools.loadMap(con, strMap, intMap);
					Tools.printMap(con, strMap, intX, intY);
					Tools.drawFog(con, intX, intY);
					Tools.drawCurrentStats(con, intScore, bln250HP);

					con.drawImage(playerLeft, (intX * 40) + 200, (intY * 40) + 100);
					con.repaint();

					// Start Game
					for (intScore = 0; blnWin == false && blnLose == false; intScore++)
					{
						while (chrMove != 'w' && chrMove != 'a' && chrMove != 's' && chrMove != 'd' && chrMove != ']' && chrMove != '[')
						{
							chrMove = con.getChar();
						}

						if (chrMove == 'w' && intY != 0
								&& (strMap[intY - 1][intX].equals("_") || strMap[intY - 1][intX].equals("x")
										|| strMap[intY - 1][intX].equals("l") || strMap[intY - 1][intX].equals("*")
										|| strMap[intY - 1][intX].equals("X") || strMap[intY - 1][intX].equals("z")
										|| strMap[intY - 1][intX].equals("c") || strMap[intY - 1][intX].equals("v")
										|| strMap[intY - 1][intX].equals("1") || strMap[intY - 1][intX].equals("2")
										|| strMap[intY - 1][intX].equals("3") || strMap[intY - 1][intX].equals("4")))
						{
							intY--;
						}
						else if (chrMove == 'a' && intX != 0
								&& (strMap[intY][intX - 1].equals("_") || strMap[intY][intX - 1].equals("x")
										|| strMap[intY][intX - 1].equals("l") || strMap[intY][intX - 1].equals("*")
										|| strMap[intY][intX - 1].equals("X") || strMap[intY][intX - 1].equals("z")
										|| strMap[intY][intX - 1].equals("c") || strMap[intY][intX - 1].equals("v")
										|| strMap[intY][intX - 1].equals("1") || strMap[intY][intX - 1].equals("2")
										|| strMap[intY][intX - 1].equals("3") || strMap[intY][intX - 1].equals("4")))
						{
							intX--;
						}
						else if (chrMove == 's' && intY != 19
								&& (strMap[intY + 1][intX].equals("_") || strMap[intY + 1][intX].equals("x")
										|| strMap[intY + 1][intX].equals("l") || strMap[intY + 1][intX].equals("*")
										|| strMap[intY + 1][intX].equals("X") || strMap[intY + 1][intX].equals("z")
										|| strMap[intY + 1][intX].equals("c") || strMap[intY + 1][intX].equals("v")
										|| strMap[intY + 1][intX].equals("1") || strMap[intY + 1][intX].equals("2")
										|| strMap[intY + 1][intX].equals("3") || strMap[intY + 1][intX].equals("4")))
						{
							intY++;
						}
						else if (chrMove == 'd' && intX != 19
								&& (strMap[intY][intX + 1].equals("_") || strMap[intY][intX + 1].equals("x")
										|| strMap[intY][intX + 1].equals("l") || strMap[intY][intX + 1].equals("*")
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
						else if (chrMove == '[')
						{
							blnLose = true;
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
								strMap[intY][intX] = "l";
							}
							else if (intEndBattle == 2)
							{
								blnLose = true;
							}
							else if (intEndBattle == 3)
							{
								blnWin = true;
							}
							Tools.clearAll(con);
						}
						
						//Print player's updated position
						Tools.printMap(con, strMap, intX, intY);
						Tools.drawCurrentStats(con, intScore, bln250HP);
						
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
						
						// Lava Damage
						if (strMap[intY][intX].equals("*"))
						{
							Tools.lavaDamage(con);
							strMap[intY][intX] = "l";
						}
						
						// Items
						if (strMap[intY][intX].equals("1") || strMap[intY][intX].equals("2")
								|| strMap[intY][intX].equals("3") || strMap[intY][intX].equals("4"))
						{
							bln250HP = Tools.items(con, strMap, intX, intY, intScore);
						}
						
						// Replace Air with Ladder
						strMap[intY][intX] = "l";
						
						// Reset variables
						intEndBattle = 0;
						chrMove = ' ';
					}
					
					// Victory Screen
					if (blnWin == true && blnLose == false)
					{
						Tools.victoryScreen(con);
					}
					// Lose Screen
					else
					{
						Tools.loseScreen(con);
					}
					blnWin = false;
					blnLose = false;
				}
			}
			// Control Menu
			else if (intNext == 2)
			{
				Tools.controlMenu(con);
			}
			// Help
			else if (intNext == 3)
			{
				Tools.helpMenu(con);
			}
			else if (intNext == 4)
			{
				blnQuit = true;
			}
			if (intNext == 4)
			{
				con.closeConsole();
			}
		}
	}
}