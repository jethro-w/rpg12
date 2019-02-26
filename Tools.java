import arc.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color.*;

public class Tools
{
	// Logo
	public static void logo(Console con)
	{
		BufferedImage logo = con.loadImage("logo.png");

		con.drawImage(logo, 1150, 750);
		con.repaint();
	}

	// Menu
	public static int menu(Console con, char chrInput)
	{
		int intNext = 1;
		char chrLR = 'n';
		Font menuFont = con.loadFont("I-pixel-u.ttf", 80);
		Font menuChoiceFont = con.loadFont("I-pixel-u.ttf", 24);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		// BufferedImage dragon = con.loadImage("");
		BufferedImage background = con.loadImage("battleBackground.png");

		while (chrLR != ' ')
		{
			con.clear();
			
			con.drawImage(background, 0, 0);
			con.setDrawFont(menuFont);
			con.setDrawColor(Color.BLACK);
			con.drawString("CAVE QUEST", 490, 390);
			con.setDrawColor(Color.WHITE);
			con.drawString("CAVE QUEST", 500, 400);
			
			con.setDrawFont(menuChoiceFont);
			
			con.setDrawColor(Color.RED);
			if (intNext == 1)
			{
				con.drawString("Play Game", 350, 650);
			}
			else if (intNext == 2)
			{
				con.drawString("Controls", 550, 650);
			}
			else if (intNext == 3)
			{
				con.drawString("Help", 750, 650);
			}
			else if (intNext == 4)
			{
				con.drawString("High Scores", 900, 650);
			}
			else if (intNext == 5)
			{
				con.drawString("Quit", 1150, 650);
			}

			con.setDrawColor(Color.WHITE);
			if (intNext != 1)
			{
				con.drawString("Play Game", 350, 650);
			}
			if (intNext != 2)
			{
				con.drawString("Controls", 550, 650);
			}
			if (intNext != 3)
			{
				con.drawString("Help", 750, 650);
			}
			if (intNext != 4)
			{
				con.drawString("High Scores", 900, 650);
			}
			if (intNext != 5)
			{
				con.drawString("Quit", 1150, 650);
			}
			con.drawString("Press [space] to select", 650, 800);
			con.repaint();

			chrLR = con.getChar();
			if (chrLR == 'a' && intNext != 1)
			{
				intNext = intNext - 1;
			}
			else if (chrLR == 'd' && intNext != 5)
			{
				intNext = intNext + 1;
			}
			con.repaint();
			con.setDrawFont(defaultFont);
		}
		return intNext;
	}

	// Load Map to array
	public static String[][] loadMap(Console con, String[][] strMap, int intMap)
	{
		int intRow;
		int intColumn;
		String[] strMapLine = new String[20];

		if (intMap == 1)
		{
			TextInputFile map1 = new TextInputFile("map1.csv");

			for (intRow = 0; map1.eof() == false; intRow++)
			{
				strMapLine[intRow] = map1.readLine();
			}
			map1.close();
		}
		else if (intMap == 2)
		{
			TextInputFile map2 = new TextInputFile("map2.csv");

			for (intRow = 0; map2.eof() == false; intRow++)
			{
				strMapLine[intRow] = map2.readLine();
			}
			map2.close();
		}

		// Load Map into Char array

		for (intRow = 0; intRow < 20; intRow++)
		{
			for (intColumn = 0; intColumn < 20; intColumn++)
			{
				strMap[intColumn] = strMapLine[intColumn].split(",");
			}
		}

		return strMap;
	}

	// Print Map to Screen
	public static void printMap(Console con, String strMap[][])
	{
		int intRow;
		int intColumn;
		int intX = 200;
		int intY = 100;
		BufferedImage dirt = con.loadImage("dirt.png");
		BufferedImage grass = con.loadImage("grass.png");
		BufferedImage air = con.loadImage("air.png");
		BufferedImage amethystOre = con.loadImage("amethyst.png");
		BufferedImage diamondOre = con.loadImage("diamond.png");
		BufferedImage ironOre = con.loadImage("iron ore.png");
		BufferedImage marble = con.loadImage("marble.png");
		BufferedImage stone = con.loadImage("stone.png");
		BufferedImage rubyOre = con.loadImage("ruby.png");
		BufferedImage titaniumOre = con.loadImage("titanium ore.png");
		BufferedImage ladder = con.loadImage("ladder.png");
		// BufferedImage boss = con.loadImage("boss.png");
		BufferedImage skeleton = con.loadImage("skeleton.png");
		BufferedImage zombie = con.loadImage("zombie.png");
		BufferedImage item1 = con.loadImage("gold.png");
		BufferedImage item2 = con.loadImage("demonite.png");
		BufferedImage item3 = con.loadImage("cobalt.png");
		BufferedImage item4 = con.loadImage("mythril.png");

		for (intRow = 0; intRow < 20; intRow++)
		{
			intX = 200;
			for (intColumn = 0; intColumn < 20; intColumn++)
			{
				if (strMap[intRow][intColumn].equals("d"))
				{
					con.drawImage(dirt, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("g"))
				{
					con.drawImage(grass, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("_"))
				{
					con.drawImage(air, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("u"))
				{
					con.drawImage(stone, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("m"))
				{
					con.drawImage(marble, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("e"))
				{
					con.drawImage(amethystOre, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("a"))
				{
					con.drawImage(diamondOre, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("i"))
				{
					con.drawImage(ironOre, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("r"))
				{
					con.drawImage(rubyOre, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("l"))
				{
					con.drawImage(ladder, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("t"))
				{
					con.drawImage(titaniumOre, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("x"))
				{
					con.drawImage(skeleton, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("X"))
				{
					// con.drawImage(boss, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("z"))
				{
					con.drawImage(zombie, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("1"))
				{
					con.drawImage(item1, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("2"))
				{
					con.drawImage(item2, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("3"))
				{
					con.drawImage(item3, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("4"))
				{
					con.drawImage(item4, intX, intY);
				}

				intX = intX + 40;

				con.repaint();
			}
			intY = intY + 40;
		}
		con.repaint();
	}

	// Draw Console
	public static void drawGameConsole(Console con)
	{
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 15);

		con.setDrawFont(defaultFont);
		con.setDrawColor(Color.BLACK);
		con.fillRect(850, 100, 300, 600);
		con.setDrawColor(Color.WHITE);
		con.fillRect(1050, 100, 4, 800);
		con.fillRect(1050, 100, 300, 4);
		con.fillRect(1050, 896, 300, 4);
		con.fillRect(1346, 100, 4, 800);
		con.drawString("Console:", 1062, 112);

		con.repaint();
	}

	public static void printStats(Console con)
	{
		int intRow;
		int intColumn;
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 15);
		double dblStats[][] = new double[3][5];
		TextInputFile playerStats = new TextInputFile("playerStats.txt");

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 5; intColumn++)
			{
				dblStats[intRow][intColumn] = playerStats.readDouble();
			}
		}
		Tools.drawGameConsole(con);
		con.setDrawFont(defaultFont);
		con.setDrawColor(Color.WHITE);
		con.drawString("Your stats:", 862, 136);
		con.drawString("Current HP: " + dblStats[0][0] + "/200", 862, 160);
		con.drawString("Attack: " + dblStats[0][1], 862, 184);
		con.drawString("Defense: " + dblStats[0][2], 862, 208);
		con.drawString("Buff Power: " + dblStats[0][3], 862, 232);
		con.drawString("Healing: " + dblStats[0][4], 862, 256);
		con.repaint();
	}

	// Battle
	public static int battle(Console con, int intY, int intX, String strMap[][])
	{
		int intRow;
		int intColumn;
		int intFrame;
		int intRand = 0;
		int intEnemyType = 0;
		int intBarMultiplier;
		int intPMaxHP = 200;
		int intEMaxHP;
		int intEndBattle = 0;
		int intPMissingHP = 0;
		int intEMissingHP = 0;
		int intNumOfStaticFrames = 0;
		int intNumOfAttackFrames = 0;
		char chrMove = ' ';
		double dblStats[][] = new double[3][5];
		Boolean blnDefend = false;
		Boolean blnEnemyDefend = false;
		BufferedImage[] enemyAttack = new BufferedImage[4];
		BufferedImage[] enemyStatic = new BufferedImage[3];
		BufferedImage[] playerAttack = new BufferedImage[4];
		BufferedImage[] playerStatic = new BufferedImage[3];
		BufferedImage background = con.loadImage("battleBackground.png");
		BufferedImage pBackground = con.loadImage("playerBackground.png");
		BufferedImage eBackground = con.loadImage("enemyBackground.png");
		BufferedImage hBackground = con.loadImage("healthbarBackground.png");
		TextInputFile statsIn = new TextInputFile("playerStats.txt");
		TextOutputFile statsOut = new TextOutputFile("playerStats.txt");

		// Read player stats from playerStats.txt
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				dblStats[intRow][intColumn] = statsIn.readDouble();
			}
		}
		
		// Row 0 = Player
		// Row 1 = Basic Enemy
		// Row 2 = Boss
		
		// Column 0 = Base Health
		// Column 1 = Attack
		// Column 2 = Defend
		
		// Load animation frames into arrays
		
		
		if (strMap[intY][intX].equals("z") || strMap[intY][intX].equals("x")
				|| strMap[intY][intX].equals("v") || strMap[intY][intX].equals("c"))
		{
			intEnemyType = 1;
			intNumOfStaticFrames = 3;
			intNumOfAttackFrames = 4;
			
			// Skeleton
			if (strMap[intY][intX].equals("x"))
			{
				for (intFrame = 0; intFrame < 3; intFrame++)
				{
					enemyStatic[intFrame] = con.loadImage("skeletonStatic" + intFrame + ".png");
				}
				
				for (intFrame = 0; intFrame < 4; intFrame++)
				{
					enemyAttack[intFrame] = con.loadImage("skeletonAttack" + intFrame + ".png");
				}
			}
			// Zombie
			else if (strMap[intY][intX].equals("z"))
			{
				for (intFrame = 0; intFrame < 3; intFrame++)
				{
					enemyStatic[intFrame] = con.loadImage("zombieStatic" + intFrame + ".png");
				}
				
				for (intFrame = 0; intFrame < 4; intFrame++)
				{
					enemyAttack[intFrame] = con.loadImage("zombieAttack" + intFrame + ".png");
				}
			}
		}
		else if (strMap[intY][intX].equals("X"))
		{
			intEnemyType = 2;
			intNumOfStaticFrames = 2;
			intNumOfAttackFrames = 3;
			
			// Golem boss
			for (intFrame = 0; intFrame < 2; intFrame++)
			{
				enemyStatic[intFrame] = con.loadImage("golemStatic" + intFrame + ".png");
			}
			
			for (intFrame = 0; intFrame < 3; intFrame++)
			{
				enemyAttack[intFrame] = con.loadImage("golemAttack" + intFrame + ".png");
			}
		}
		
		intEMaxHP = (int) (dblStats[intEnemyType][0]);

		// Draw Game Console
		Tools.clearAll(con);
		
		con.drawImage(background, 0, 0);
		Tools.drawBattleConsole(con);
		con.drawString("You have been approached by an enemy...", 216, 756);
		con.repaint();
		con.sleep(2000);
		
		Tools.battleClear(con);
		
		// Print health bar and stats
		while (intEndBattle == 0)
		{	
			Tools.drawBattleConsole(con);
			con.drawString("What will you do?", 216, 756);
			con.drawString("[1] Attack", 216, 790);
			con.drawString("[2] Block/Defend", 216, 820);
			con.drawImage(hBackground, 0, 0);
			
			if (dblStats[0][0] < 0)
			{
				con.drawString("Health: 0 / " + intPMaxHP, 200, 170);
			}
			else
			{
				con.drawString("Health: " + (int) dblStats[0][0] + " / " + intPMaxHP, 200, 170);
			}
			
			if (dblStats[intEnemyType][0] < 0)
			{
				con.drawString("Health: 0 / " + intEMaxHP, 1000, 170);
			}
			else
			{
				con.drawString("Health: " + (int) dblStats[intEnemyType][0] + " / " + intEMaxHP, 1000, 170);
			}
			con.repaint();
			
			// Health Bars
			con.setDrawColor(Color.GREEN);
			con.fillRect(200, 210, 300, 40);
			con.fillRect(1000, 210, 300, 40);
			con.repaint();
			con.setDrawColor(Color.RED);
			
			intBarMultiplier = 300 / intEMaxHP;
			
			// Check if player or enemy is dead
			if (dblStats[0][0] < 0.0)
			{
				intPMissingHP = 200;
			}
			else
			{
				intPMissingHP = intPMaxHP - (int) dblStats[0][0];
			}
			if (dblStats[intEnemyType][0] < 0.0)
			{
				if (intEnemyType == 1)
				{
					intEMissingHP = 150;
				}
				else if (intEnemyType == 2)
				{
					intEMissingHP = 400;
				}
			}
			else
			{
				intEMissingHP = intEMaxHP - (int) dblStats[intEnemyType][0];
			}
			
			// Player Red Bar
			con.fillRect(500 - intPMissingHP, 210, intPMissingHP, 40);
			
			// Enemy Red Bar
			con.fillRect(1300 - (intEMissingHP * intBarMultiplier), 210, intEMissingHP * intBarMultiplier, 40);
			con.repaint();
			
			if (dblStats[0][0] <= 0.0 || dblStats[intEnemyType][0] <= 0.0)
			{
				Tools.drawBattleConsole(con);
				
				if (intEnemyType == 2 && dblStats[intEnemyType][0] <= 0.0)
				{
					con.drawString("You have defeated the enemy.", 216, 756);
					intEndBattle = 3;
					con.setDrawColor(Color.RED);
					con.fillRect(200, 210, 300, 40);
				}
				else if (dblStats[0][0] <= 0.0)
				{
					con.drawString("You have been defeated.", 216, 756);
					intEndBattle = 2;
					con.repaint();
					con.setDrawColor(Color.RED);
					con.fillRect(200, 210, 300, 40);
				}
				else if (dblStats[intEnemyType][0] <= 0.0)
				{
					con.drawString("You have defeated the enemy.", 216, 756);
					intEndBattle = 1;
					con.repaint();
					con.setDrawColor(Color.RED);
					con.fillRect(1000, 210, 300, 40);
				}
				con.repaint();
				con.sleep(1000);
			}
			
			if (intEndBattle == 0)
			{
				chrMove = drawStatic(con, playerStatic, enemyStatic, pBackground, eBackground, intNumOfStaticFrames);
				
				Tools.drawBattleConsole(con);
				
				if (chrMove == '1')
				{
					if (blnEnemyDefend == false)
					{
						dblStats[intEnemyType][0] = dblStats[intEnemyType][0] - dblStats[0][1];
					}
					else if (blnEnemyDefend == true)
					{
						dblStats[intEnemyType][0] = dblStats[intEnemyType][0] - dblStats[0][1]
								+ dblStats[intEnemyType][2];
						blnEnemyDefend = false;
					}
					con.drawString("You chose to attack.", 216, 756);
					con.repaint();
				}
				else if (chrMove == '2')
				{
					blnDefend = true;
					con.drawString("You chose to defend.", 216, 756);
					con.repaint();
				}
				// SECRET CODE
				else if (chrMove == '5')
				{
					intEndBattle = 1;
				}
				else if (chrMove == '6')
				{
					intEndBattle = 2;
				}

				con.drawString("Press [space] to continue.", 216, 796);
				con.repaint();

				while (chrMove != ' ')
				{
					chrMove = con.getChar();
				}
				
				// Enemy's Turn
				// Plain Enemy
				if (intEnemyType == 1)
				{
					intRand = (int) (Math.random() * 2) + 1;
				}
				// Boss
				else if (intEnemyType == 2)
				{
					intRand = (int) (Math.random() * 10) + 1;
				}
				
				Tools.drawBattleConsole(con);
				
				// 50/50 for normal enemy and 70/30 for boss
				if ((intEnemyType == 1 && intRand == 1) || (intEnemyType == 2 && intRand >= 1 && intRand <= 7))
				{
					con.drawString("The enemy chose to attack.", 216, 796);
					con.repaint();
					
					if (blnDefend == false)
					{
						dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1];
					}
					else if (blnDefend == true)
					{
						dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1] + dblStats[0][2];
						blnDefend = false;
					}
					
					Tools.drawEnemyAttack(con, enemyAttack, eBackground, intNumOfAttackFrames);
					con.sleep(1000);
				}
				else if ((intEnemyType == 1 && intRand == 2) || (intEnemyType == 2 && intRand > 7 && intRand <= 10))
				{
					blnEnemyDefend = true;
					con.drawString("The enemy chose to defend.", 216, 796);
					con.repaint();
				}
				con.sleep(1000);
			}
		}
		
		
		// Print Stats Back into playerStats.txt (only player stats change)
		dblStats[1][0] = 150;
		
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				statsOut.println(dblStats[intRow][intColumn]);
			}
		}
		
		statsIn.close();
		statsOut.close();

		return intEndBattle;
	}

	public static void items(Console con, String strMap[][], int intY, int intX)
	{
		int intRow;
		int intColumn;
		double dblStats[][] = new double[3][5];
		TextInputFile statsIn = new TextInputFile("playerStats.txt");
		TextOutputFile statsOut = new TextOutputFile("playerStats.txt");

		// Read player stats from playerStats.txt (items affect player stats)
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 5; intColumn++)
			{
				dblStats[intRow][intColumn] = statsIn.readDouble();
			}
		}
		statsIn.close();

		// Column 0 = Health
		// Column 1 = Attack
		// Column 2 = Defend
		// Column 3 = Buff
		// Column 4 = Heal

		Tools.drawGameConsole(con);
		// Double Edged Sword
		if (strMap[intY][intX].equals("1"))
		{

		}
		// Potion of Power
		else if (strMap[intY][intX].equals("2"))
		{

		}
		// Cast Iron Armour
		else if (strMap[intY][intX].equals("3"))
		{

		}
		// Spellbook of Healing
		else if (strMap[intY][intX].equals("4"))
		{

		}
		con.repaint();
		strMap[intY][intX] = "g";

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 5; intColumn++)
			{
				statsOut.println(dblStats[intRow][intColumn]);
			}
		}
	}

	public static void waterDamage(Console con, char chrClan)
	{
		int intRow;
		int intColumn;
		double dblStats[][] = new double[3][5];
		TextInputFile playerStats = new TextInputFile("playerStats.txt");
		TextOutputFile printStats = new TextOutputFile("playerStats.txt");

		// Read player stats from playerStats.txt
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 5; intColumn++)
			{
				dblStats[intRow][intColumn] = playerStats.readDouble();
			}
		}

		if (chrClan == 'f')
		{
			dblStats[0][0] = dblStats[0][0] - 10;
			con.drawString("Ouch...that did 5 damage.", 862, 184);
			con.repaint();
		}
		else if (chrClan == 'e')
		{
			dblStats[0][0] = dblStats[0][0] - 5;
			con.drawString("Ouch...that did 5 damage.", 862, 184);
			con.repaint();
		}

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 5; intColumn++)
			{
				printStats.println(dblStats[intRow][intColumn]);
			}
		}
	}

	// Resets Player Stats in case they were changed from buffs/items
	public static void resetStats(Console con)
	{
		int intRow;
		int intColumn;
		double dblStats[][] = new double[3][3];
		TextOutputFile printStats = new TextOutputFile("playerStats.txt");
		
		dblStats[0][0] = 200;
		dblStats[0][1] = 20;
		dblStats[0][2] = 15;
		dblStats[1][0] = 150;
		dblStats[1][1] = 15;
		dblStats[1][2] = 15;
		dblStats[2][0] = 400;
		dblStats[2][1] = 20;
		dblStats[2][2] = 12;

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				printStats.println(dblStats[intRow][intColumn]);
			}
		}
	}

	// Victory Screen
	public static void victoryScreen(Console con)
	{

	}

	// Lose Screen
	public static void loseScreen(Console con)
	{

	}

	public static void helpMenu(Console con)
	{
		int intPage = 1;
		boolean blnLeave = false;
		char chrLeave = ' ';

		while (blnLeave == false)
		{
			if (intPage == 1)
			{
				if (chrLeave == 's')
				{
					blnLeave = true;
				}
				else if (blnLeave == false)
				{
					con.getChar();
					blnLeave = true;
				}
			}
		}
	}

	// Controls Menu
	public static void controlMenu(Console con)
	{

	}

	// Highscores Screen
	public static void highscores(Console con)
	{
		TextInputFile highscores = new TextInputFile("highscores.txt");
		Font titleFont = con.loadFont("alagard.ttf", 50);
		Font drawFont = con.loadFont("alagard.ttf", 30);
		Font _drawFont = con.loadFont("alagard.ttf", 20);
		String strPlace[] = new String[10];
		int intNumUsers = 0;
		int intCountA;
		int intCountB = 1;
		int intX = 100;
		int intY = 200;
		String strTemp;

		for (intCountA = 0; intCountA < 10; intCountA++)
		{
			strPlace[intCountA] = ("" + intCountB + "");
			intCountB++;
		}

		while (highscores.eof() == false)
		{
			strTemp = highscores.readLine();
			strTemp = highscores.readLine();
			strTemp = highscores.readLine();
			intNumUsers++;
		}
		highscores.close();

		System.out.println("intNumUsers");

		String strHighscores[][] = new String[intNumUsers][3];
		highscores = new TextInputFile("highscores.txt");

		for (intCountA = 0; intCountA < intNumUsers; intCountA++)
		{
			strHighscores[intCountA][0] = highscores.readLine();
			strHighscores[intCountA][1] = highscores.readLine();
			strHighscores[intCountA][2] = highscores.readLine();
		}
		highscores.close();

		// Sort Highscores
		for (intCountB = 0; intCountB < intNumUsers - 1; intCountB++)
		{
			for (intCountA = 0; intCountA < intNumUsers - 1; intCountA++)
			{
				if (Integer.parseInt(strHighscores[intCountA][1]) > Integer.parseInt(strHighscores[intCountA + 1][1]))
				{
					strTemp = strHighscores[intCountA][1];
					strHighscores[intCountA][1] = strHighscores[intCountA + 1][1];
					strHighscores[intCountA + 1][1] = strTemp;

					strTemp = strHighscores[intCountA][0];
					strHighscores[intCountA][0] = strHighscores[intCountA + 1][0];
					strHighscores[intCountA + 1][0] = strTemp;

					strTemp = strHighscores[intCountA][2];
					strHighscores[intCountA][2] = strHighscores[intCountA + 1][2];
					strHighscores[intCountA + 1][2] = strTemp;
				}
			}
		}
		System.out.println(intNumUsers);

		// Print Highscores
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(titleFont);
		con.drawString("Highscores", 100, 75);
		con.repaint();
		con.setDrawFont(drawFont);
		con.drawString("Username", 150, 150);
		con.drawString("Score", 600, 150);
		con.drawString("Clan", 1000, 150);
		con.setDrawFont(_drawFont);
		con.drawString("Press any key to return to the main menu.", 100, 700);
		con.setDrawFont(drawFont);
		for (intCountA = 0; intCountA < intNumUsers; intCountA++)
		{
			con.drawString(strPlace[intCountA] + ".", intX, intY);
			con.drawString(strHighscores[intCountA][0], intX + 50, intY);
			con.drawString(strHighscores[intCountA][1], intX + 500, intY);
			if (strHighscores[intCountA][2].equals("f"))
			{
				con.drawString("Fire", intX + 900, intY);
			}
			else if (strHighscores[intCountA][2].equals("e"))
			{
				con.drawString("Earth", intX + 900, intY);
			}
			else if (strHighscores[intCountA][2].equals("w"))
			{
				con.drawString("Water", intX + 900, intY);
			}
			con.repaint();
			intY = intY + 50;
		}
	}

	public static void drawBattleConsole(Console con)
	{
		BufferedImage cBackground = con.loadImage("consoleBackground.png");
		
		con.drawImage(cBackground, 0, 740);
		con.setDrawColor(Color.WHITE);
		con.fillRect(200, 750, 1192, 4);
		con.fillRect(200, 892, 1192, 4);
		con.fillRect(200, 750, 4, 146);
		con.fillRect(1392, 750, 4, 146);
		con.repaint();
	}
	
	public static char drawStatic(Console con, BufferedImage[] playerStatic, BufferedImage[] enemyStatic,
			BufferedImage pBackground, BufferedImage eBackground, int intNumOfFrames)
	{
		char chrMove = ' ';
		int intFrame;
		
		while (chrMove != '1' && chrMove != '2' && chrMove != '5' && chrMove != '6')
		{	
			for (intFrame = 0; intFrame < intNumOfFrames && chrMove != '1' && chrMove != '2' && chrMove != '5' && chrMove != '6'; intFrame++)
			{	
				chrMove = con.currentChar();
				
				con.drawImage(eBackground, 800, 250);
				con.repaint();
				con.drawImage(enemyStatic[intFrame], 1000, 300);
				con.repaint();
				
				con.drawImage(pBackground, 0, 250);
				con.repaint();
				con.drawImage(playerStatic[intFrame], 200, 300);
				con.repaint();
				
				con.sleep(500);
			}
		}
		
		return chrMove;
	}
	
	public static void drawPlayerAttack(Console con, BufferedImage[] playerAttack, BufferedImage pBackground, int intNumOfFrames)
	{
		int intFrame;
		
		for (intFrame = 0; intFrame < intNumOfFrames; intFrame++)
		{			
			con.drawImage(pBackground, 0, 250);
			con.drawImage(playerAttack[intFrame], 200, 250);
			con.sleep(500);
		}
	}
	
	public static void drawEnemyAttack(Console con, BufferedImage[] enemyAttack, BufferedImage eBackground, int intNumOfFrames)
	{
		int intFrame;
		
		for (intFrame = 0; intFrame < intNumOfFrames; intFrame++)
		{			
			con.drawImage(eBackground, 800, 250);
			con.drawImage(enemyAttack[intFrame], 850, 300);
			con.repaint();
			con.sleep(500);
		}
	}
	
	public static void battleClear(Console con)
	{
		BufferedImage background = con.loadImage("battleBackground.png");
		
		con.drawImage(background, 0, 0);
		con.setDrawColor(Color.WHITE);
		con.fillRect(200, 740, 1192, 4);
		con.fillRect(200, 892, 1192, 4);
		con.fillRect(200, 740, 4, 156);
		con.fillRect(1392, 740, 4, 156);
		con.repaint();
	}

	// Clear Screen Method
	public static void clearAll(Console con)
	{
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1600, 1000);
		con.repaint();
	}
}