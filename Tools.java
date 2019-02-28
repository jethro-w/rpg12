import arc.*;
import java.awt.*;
import java.awt.image.*;

public class Tools
{
	// Logo
	public static void logo(Console con)
	{
		BufferedImage logo = con.loadImage("logo.png");

		con.drawImage(logo, 1500, 900);
	}
		
	// Menu
	public static int menu(Console con)
	{
		int intNext = 1;
		int intFrame = 0;
		char chrLR = 'n';
		Font menuFont = con.loadFont("I-pixel-u.ttf", 150);
		Font menuChoiceFont = con.loadFont("I-pixel-u.ttf", 30);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		BufferedImage[] enemyStatic = new BufferedImage[2];
		BufferedImage background = con.loadImage("battleBackground.png");
		
		for (intFrame = 0; intFrame < 2; intFrame++)
		{
			enemyStatic[intFrame] = con.loadImage("golemStatic" + intFrame + ".png");
		}
		
		while (chrLR != ' ')
		{
			con.clear();
			
			if (intFrame == 2)
			{
				intFrame = 0;
			}
			
			con.drawImage(background, 0, 0);
			Tools.logo(con);
			con.drawImage(enemyStatic[intFrame], 500, 50);
			intFrame++;
			
			con.setDrawFont(menuFont);
			con.setDrawColor(Color.BLACK);
			con.drawString("CAVE QUEST", 240, 420);
			con.setDrawColor(Color.WHITE);
			con.drawString("CAVE QUEST", 250, 430);
			
			con.setDrawFont(menuChoiceFont);
			
			con.setDrawColor(Color.RED);
			if (intNext == 1)
			{
				con.drawString("Play Game", 350, 650);
			}
			else if (intNext == 2)
			{
				con.drawString("Controls", 650, 650);
			}
			else if (intNext == 3)
			{
				con.drawString("Help", 925, 650);
			}
			else if (intNext == 4)
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
				con.drawString("Controls", 650, 650);
			}
			if (intNext != 3)
			{
				con.drawString("Help", 925, 650);
			}
			if (intNext != 4)
			{
				con.drawString("Quit", 1150, 650);
			}
			con.drawString("Press [space] to select", 575, 800);
			con.repaint();

			chrLR = con.getChar();
			
			if (chrLR == 'a' && intNext != 1)
			{
				intNext = intNext - 1;
			}
			else if (chrLR == 'd' && intNext != 4)
			{
				intNext = intNext + 1;
			}
			con.repaint();
			con.setDrawFont(defaultFont);
		}
		return intNext;
	}
	
	public static String askForUsername(Console con)
	{
		Font bigFont = con.loadFont("I-pixel-u.ttf", 150);
		Font smallFont = con.loadFont("I-pixel-u.ttf", 60);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		char chrPart;
		String strPart;
		String strUsername = "";
		int intCount;
		
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(smallFont);
		con.drawString("Enter your username (3 characters)", 250, 300);
		con.repaint();
		
		con.setDrawFont(bigFont);
		
		for (intCount = 0; intCount < 3; intCount++)
		{
			chrPart = con.getChar();
			strPart = String.valueOf(chrPart);
			strUsername = strUsername + strPart;
			
			con.drawString(strPart, (200 * intCount) + 525, 400);
			con.repaint();
		}
		
		con.sleep(1000);
		
		con.setDrawFont(defaultFont);
		
		return strUsername;
	}
	
	public static int askForMap(Console con)
	{
		Font bigFont = con.loadFont("I-pixel-u.ttf", 60);
		Font smallFont = con.loadFont("I-pixel-u.ttf", 50);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		char chrMap = 'n';
		int intMap = 1;
		
		while (chrMap != ' ' && chrMap != 'q')
		{
			con.setDrawColor(Color.WHITE);
			con.setDrawFont(bigFont);
			con.drawString("Which map would you like to play on?", 200, 300);
			
			con.setDrawFont(smallFont);
			if (intMap == 1)
			{
				con.fillRect(275, 440, 300, 110);
				con.setDrawColor(Color.BLACK);
				con.fillRect(825, 440, 300, 110);
				con.setDrawColor(Color.WHITE);
				con.drawString("Map Two", 875, 450);
				con.setDrawColor(Color.BLACK);
				con.drawString("Map One", 325, 450);
			}
			else if (intMap == 2)
			{
				con.fillRect(825, 440, 300, 110);
				con.setDrawColor(Color.BLACK);
				con.fillRect(275, 440, 300, 110);
				con.setDrawColor(Color.WHITE);
				con.drawString("Map One", 325, 450);
				con.setDrawColor(Color.BLACK);
				con.drawString("Map Two", 875, 450);
			}
			con.setDrawColor(Color.WHITE);
			con.drawString("Press [Space] to select or [Q] to return to main menu.", 150, 650);
			
			con.repaint();
			
			chrMap = con.getChar();
			
			if (chrMap == 'a' && intMap != 1)
			{
				intMap--;
			}
			else if (chrMap == 'd' && intMap != 2)
			{
				intMap++;
			}
			else if (chrMap == 'q')
			{
				intMap = 0;
			}
		}
		
		con.setDrawFont(defaultFont);
		
		return intMap;
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
	public static void printMap(Console con, String strMap[][], int intPosX, int intPosY)
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
		BufferedImage lava = con.loadImage("lava.png");
		BufferedImage boss = con.loadImage("golem.png");
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
					con.drawImage(boss, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("z"))
				{
					con.drawImage(zombie, intX, intY);
				}
				else if (strMap[intRow][intColumn].equals("*"))
				{
					con.drawImage(lava, intX, intY);
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
			}
			intY = intY + 40;
		}
		Tools.drawFog(con, intPosX, intPosY);
	}
	
	public static void drawFog(Console con, int intX, int intY)
	{
		BufferedImage fog = con.loadImage("fog1.png");
		
		con.drawImage(fog, (intX * 40) + 160, (intY * 40) + 60);
		con.repaint();
		
		con.setDrawColor(Color.BLACK);
		con.fillRect(200, 100, (intX - 1) * 40, 800);
		con.fillRect(200, 100, 800, (intY - 1) * 40);
		con.fillRect(((intX + 2) * 40) + 200, 100, 800 - ((intX + 2) * 40), 800);
		con.fillRect(200, ((intY + 2) * 40) + 100, 800, 800 - ((intY + 2) * 40));
		con.repaint();
		
		con.setDrawColor(Color.WHITE);
	}
	
	// Draw Console
	public static void drawCurrentStats(Console con, int intScore, Boolean bln250HP)
	{
		int intRow;
		int intColumn;
		Font drawFont = con.loadFont("I-pixel-u.ttf", 20);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		double dblStats[][] = new double[3][3];
		TextInputFile playerStats = new TextInputFile("playerStats.txt");

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				dblStats[intRow][intColumn] = playerStats.readDouble();
			}
		}
		
		con.setDrawFont(drawFont);
		con.setDrawColor(Color.BLACK);
		con.fillRect(1050, 100, 300, 800);
		con.setDrawColor(Color.WHITE);
		con.fillRect(1050, 96, 4, 808);
		con.fillRect(1050, 96, 300, 4);
		con.fillRect(1050, 904, 300, 4);
		con.fillRect(1346, 96, 4, 808);
		con.fillRect(196, 96, 4, 808);
		con.fillRect(196, 96, 808, 4);
		con.fillRect(1000, 96, 4, 808);
		con.fillRect(196, 900, 808, 4);
		
		con.drawString("Current Score: " + intScore, 1062, 112);
		con.drawString("Current Stats:", 1062, 1136);
		con.setDrawFont(drawFont);
		con.setDrawColor(Color.WHITE);
		
		if (bln250HP == false)
		{
			con.drawString("Current HP: " + dblStats[0][0] + "/200", 1062, 160);
		}
		else
		{
			con.drawString("Current HP: " + dblStats[0][0] + "/250", 1062, 160);
		}
		con.drawString("Attack: " + dblStats[0][1], 1062, 184);
		
		con.repaint();
		
		con.setDrawFont(defaultFont);
		
		playerStats.close();
	}

	// Battle
	public static int battle(Console con, int intY, int intX, String strMap[][])
	{
		int intRow;
		int intColumn;
		int intFrame;
		int intRand = 0;
		int intEnemyType = 0;
		double dblBarMultiplier;
		int intPMaxHP;
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
		for (intFrame = 0; intFrame < 2; intFrame++)
		{
			playerStatic[intFrame] = con.loadImage("playerStatic" + intFrame + ".png");
		}
		
		for (intFrame = 0; intFrame < 3; intFrame++)
		{
			playerAttack[intFrame] = con.loadImage("playerAttack" + intFrame + ".png");
		}
		
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
		if (dblStats[0][0] > 200)
		{
			intPMaxHP = 250;
		}
		else
		{
			intPMaxHP = 200;
		}

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
				con.drawString("Health: 0 / " + intEMaxHP, 100, 170);
			}
			else
			{
				con.drawString("Health: " + (int) dblStats[intEnemyType][0] + " / " + intEMaxHP, 1000, 170);
			}
			con.repaint();
			
			// Health Bars
			con.setDrawColor(Color.GREEN);
			con.fillRect(200, 210, 400, 40);
			con.fillRect(1000, 210, 400, 40);
			con.repaint();
			con.setDrawColor(Color.RED);
			
			dblBarMultiplier = 400 / intEMaxHP;
			
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
			con.fillRect(600 - intPMissingHP, 210, intPMissingHP, 40);
			
			// Enemy Red Bar
			con.fillRect(1400 - ((int) (intEMissingHP * dblBarMultiplier)), 210, ((int) (intEMissingHP * dblBarMultiplier)), 40);
			con.repaint();
			
			if (dblStats[0][0] <= 0.0 || dblStats[intEnemyType][0] <= 0.0)
			{
				Tools.drawBattleConsole(con);
				
				if (intEnemyType == 2 && dblStats[intEnemyType][0] <= 0.0)
				{
					con.drawString("You have defeated the enemy.", 216, 756);
					intEndBattle = 3;
					con.setDrawColor(Color.RED);
					con.fillRect(200, 210, 400, 40);
				}
				else if (dblStats[0][0] <= 0.0)
				{
					con.drawString("You have been defeated.", 216, 756);
					intEndBattle = 2;
					con.repaint();
					con.setDrawColor(Color.RED);
					con.fillRect(200, 210, 400, 40);
				}
				else if (dblStats[intEnemyType][0] <= 0.0)
				{
					con.drawString("You have defeated the enemy.", 216, 756);
					intEndBattle = 1;
					con.repaint();
					con.setDrawColor(Color.RED);
					con.fillRect(900, 210, 400, 40);
				}
				con.repaint();
				con.sleep(1000);
			}
			
			if (intEndBattle == 0)
			{
				chrMove = drawStatic(con, playerStatic, enemyStatic, pBackground, eBackground,
						intNumOfStaticFrames, intEnemyType, 2);
				
				Tools.drawBattleConsole(con);
				
				// Player's Turn
				if (chrMove == '1')
				{					
					con.drawString("You chose to attack.", 216, 756);
					con.repaint();
				}
				else if (chrMove == '2')
				{
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
				
				// Enemy's Turn
				// Plain Enemy
				if (intEnemyType == 1)
				{
					intRand = (int) (Math.random() * 2) + 1;
					
					if (intRand == 2)
					{
						blnEnemyDefend = true;
					}
				}
				// Boss
				else if (intEnemyType == 2)
				{
					intRand = (int) (Math.random() * 10) + 1;
					
					if (intRand > 7 && intRand <= 10)
					{
						blnEnemyDefend = true;
					}
				}

				con.drawString("Press any key to continue.", 216, 796);
				con.repaint();

				con.getChar();
				
				Tools.drawBattleConsole(con);
				
				// Calculate Fight Damage
				if ((intEnemyType == 1 && intRand == 1) || (intEnemyType == 2 && intRand >= 1 && intRand <= 7))
				{
					con.drawString("The enemy chose to attack.", 216, 756);
					con.repaint();
					
					con.sleep(1000);
				}
				else if ((intEnemyType == 1 && intRand == 2) || (intEnemyType == 2 && intRand > 7 && intRand <= 10))
				{
					con.drawString("The enemy chose to defend.", 216, 756);
					con.repaint();
				}
				
				// Animate Attacks
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
					Tools.drawPlayerAttack(con, playerAttack, pBackground, 3);
				}
				if ((intEnemyType == 1 && intRand == 1) || (intEnemyType == 2 && intRand >= 1 && intRand <= 7))
				{
					if (blnDefend == false)
					{
						dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1];
					}
					else if (blnDefend == true)
					{
						dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1] + dblStats[0][2];
						blnDefend = false;
					}
					
					Tools.drawEnemyAttack(con, enemyAttack, eBackground, intNumOfAttackFrames, intEnemyType);
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

	public static boolean items(Console con, String strMap[][], int intX, int intY, int intScore)
	{
		int intRow;
		int intColumn;
		boolean bln250HP = false;
		double dblStats[][] = new double[3][5];
		Font drawFont = con.loadFont("I-pixel-u.ttf", 20);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		TextInputFile statsIn = new TextInputFile("playerStats.txt");
		TextOutputFile statsOut = new TextOutputFile("playerStats.txt");

		// Read player stats from playerStats.txt (items affect player stats)
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				dblStats[intRow][intColumn] = statsIn.readDouble();
			}
		}
		statsIn.close();

		// Column 0 = Health
		// Column 1 = Attack
		// Column 2 = Defend
		
		con.setDrawFont(drawFont);
		// Gold Nugget
		if (strMap[intY][intX].equals("1"))
		{
			con.drawString("Found some gold...", 1062, 208);
			con.drawString("Not very useful though.", 1062, 232);
		}
		// Demonite Gem
		else if (strMap[intY][intX].equals("2"))
		{
			con.drawString("Found a demonite gem!", 1062, 208);
			con.drawString("Stat boost: +15 damage", 1062, 232);
			
			dblStats[0][1] = dblStats[0][1] + 15;
		}
		// Mythril Gem
		else if (strMap[intY][intX].equals("3"))
		{
			con.drawString("Found a mythril gem!", 1062, 208);
			con.drawString("Stat boost: + 50 hp", 1062, 232);
			
			dblStats[0][0] = dblStats[0][0] + 50;
			
			if (dblStats[0][0] > 200)
			{
				bln250HP = true;
			}
		}
		// Cobalt Gem
		else if (strMap[intY][intX].equals("4"))
		{
			con.drawString("Found a cobalt gem!", 1062, 208);
			con.drawString("Stat boost: + 10 defense", 1062, 232);
			
			dblStats[0][2] = dblStats[0][2] + 10;
		}
		con.repaint();
		
		con.sleep(1000);

		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				statsOut.println(dblStats[intRow][intColumn]);
			}
		}
		
		Tools.drawCurrentStats(con, intScore, bln250HP);
		con.setDrawFont(defaultFont);
		
		return bln250HP;
	}
	
	public static void lavaDamage(Console con)
	{
		int intRow;
		int intColumn;
		double dblStats[][] = new double[3][3];
		Font drawFont = con.loadFont("I-pixel-u.ttf", 20);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		TextInputFile playerStats = new TextInputFile("playerStats.txt");
		TextOutputFile printStats = new TextOutputFile("playerStats.txt");

		// Read player stats from playerStats.txt
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				dblStats[intRow][intColumn] = playerStats.readDouble();
			}
		}
		playerStats.close();
		
		con.setDrawFont(drawFont);
		
		dblStats[0][0] = dblStats[0][0] - 10;
		con.drawString("Ouch...that did 10 damage.", 1062, 208);
		con.repaint();
		
		con.setDrawFont(defaultFont);
		
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				printStats.println(dblStats[intRow][intColumn]);
			}
		}
		printStats.close();
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
		
		printStats.close();
	}

	// Victory Screen
	public static void victoryScreen(Console con)
	{
		Tools.clearAll(con);
		
		BufferedImage confettiRight = con.loadImage("confettiFlipped.png");
		BufferedImage confettiLeft = con.loadImage("confetti.png");
		Font drawFont = con.loadFont("I-pixel-u.ttf", 150);
		Font smallFont = con.loadFont("I-pixel-u.ttf", 50);
		
		con.setDrawFont(drawFont);
		con.setDrawColor(Color.WHITE);
		
		con.drawImage(confettiLeft, 200, 300);
		con.drawImage(confettiRight, 1000, 300);
		con.drawString("Congratulations", 250, 350);
		
		con.setDrawFont(smallFont);
		
		con.drawString("Press any key to return to the main menu", 275, 600);
		con.repaint();
		
		con.getChar();
	}

	// Lose Screen
	public static void loseScreen(Console con)
	{
		Tools.clearAll(con);
		
		BufferedImage skull = con.loadImage("skull.png");
		Font drawFont = con.loadFont("I-pixel-u.ttf", 150);
		Font smallFont = con.loadFont("I-pixel-u.ttf", 50);
		
		con.setDrawFont(drawFont);
		con.setDrawColor(Color.RED);
		
		con.drawImage(skull, 500, 150);
		con.drawString("YOU DIED", 350, 400);
		
		con.setDrawFont(smallFont);
		con.setDrawColor(Color.WHITE);
		
		con.drawString("Press any key to return to the main menu", 275, 600);
		con.repaint();
		
		con.getChar();
	}

	public static void helpMenu(Console con)
	{
		Tools.clearAll(con);
		
		int intX;
		int intY;
		Font titleFont = con.loadFont("I-pixel-u.ttf", 50);
		Font drawFont = con.loadFont("I-pixel-u.ttf", 30);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		BufferedImage fog = con.loadImage("fog1.png");
		BufferedImage air = con.loadImage("air.png");
		BufferedImage player = con.loadImage("anglerLeft.png");
		BufferedImage gem = con.loadImage("demonite.png");
		
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(titleFont);
		
		con.drawString("Help Menu", 100, 75);
		
		con.setDrawFont(drawFont);
		
		con.drawString("The goal of the game is to find the golem boss and defeat it. However, you are surrounded by", 100, 200);
		con.drawString("fog. You must navigate in the fog and collect items to make yourself stronger. Each time", 100, 240);
		con.drawString("you travel on a tile, it will become a ladder tile. Use it as a breadcrum trail to find your", 100, 280);
		con.drawString("way around the map.", 100, 320);
		
		con.drawString("While exploring the cave, you will encounter walls that you cannot travel through as well as", 100, 400);
		con.drawString("enemies that you might have to fight.", 100, 440);
		
		for (intX = 0; intX < 3; intX++)
		{
			for (intY = 0; intY < 3; intY++)
			{
				con.drawImage(air, (intX * 40) + 100, (intY * 40) + 600);
			}
		}
		
		con.drawImage(player, 140, 640);
		con.drawImage(fog, 100, 600);
		con.drawImage(gem, 1300, 690);
		
		con.drawString("The player is surrounded by a 2x2 tile fog.", 300, 600);
		con.drawString("Gemstones like these will increase your stats.", 600, 680);
		
		con.drawString("Press any key to return to main menu", 500, 800);
		
		con.setDrawFont(defaultFont);
		con.repaint();
		
		con.getChar();
	}

	// Controls Menu
	public static void controlMenu(Console con)
	{
		Tools.clearAll(con);
		
		Font titleFont = con.loadFont("I-pixel-u.ttf", 50);
		Font drawFont = con.loadFont("I-pixel-u.ttf", 30);
		Font defaultFont = con.loadFont("Hack-Regular.ttf", 24);
		
		con.setDrawFont(titleFont);
	    con.setDrawColor(Color.WHITE);
	    con.drawString("Controls Menu", 100, 75);
	    con.setDrawFont(drawFont);
	    con.drawString("Left", 300, 350);
	    con.drawString("Down", 435, 500);
	    con.drawString("Up", 450, 225);
	    con.drawString("Right", 575, 350);
	    con.drawString("Select", 925, 500);
	    con.drawString("Press any key to return to the main menu.", 450, 800);
	    con.fillRect(300, 400, 100, 100);
	    con.fillRect(425, 400, 100, 100);
	    con.fillRect(425, 275, 100, 100);
	    con.fillRect(550, 400, 100, 100);
	    con.fillRect(800, 400, 400, 100);
	    con.repaint();

	    con.setDrawColor(Color.BLACK);
	    con.setDrawFont(titleFont);
	    con.drawString("W", 450, 300);
	    con.drawString("A", 325, 425);
	    con.drawString("S", 450, 425);
	    con.drawString("D", 575, 425);
	    con.drawString("[Space Bar]", 825, 425);

	    con.repaint();
	    
	    con.setDrawFont(defaultFont);
	    
	    con.getChar();
	}

	// Highscores Screen
	public static void highscores(Console con)
	{
		Tools.clearAll(con);
		
		TextInputFile highscores = new TextInputFile("highscores.txt");
		Font titleFont = con.loadFont("I-pixel-u.ttf", 50);
		Font drawFont = con.loadFont("I-pixel-u.ttf", 30);
		Font _drawFont = con.loadFont("I-pixel-u.ttf", 20);
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
		con.setDrawFont(_drawFont);
		con.drawString("Press any key to return to the main menu.", 100, 700);
		con.setDrawFont(drawFont);
		
		con.getChar();
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
			BufferedImage pBackground, BufferedImage eBackground, int intNumOfFrames, int intEnemyType, int intMaxPlayerFrames)
	{
		char chrMove = ' ';
		int intFrame;
		int intFrame2 = 0;
		
		while (chrMove != '1' && chrMove != '2' && chrMove != '5' && chrMove != '6')
		{	
			for (intFrame = 0; intFrame < intNumOfFrames && chrMove != '1' && chrMove != '2' && chrMove != '5' && chrMove != '6'; intFrame++)
			{	
				chrMove = con.currentChar();
				
				if (intEnemyType == 1)
				{
					con.drawImage(eBackground, 800, 250);
					con.repaint();
					con.drawImage(enemyStatic[intFrame], 1000, 300);
					con.repaint();
				}
				else if (intEnemyType == 2)
				{
					con.drawImage(eBackground, 800, 250);
					con.repaint();
					con.drawImage(enemyStatic[intFrame], 900, 250);
					con.repaint();
				}
				
				con.drawImage(pBackground, 0, 250);
				con.repaint();
				con.drawImage(playerStatic[intFrame2], 200, 275);
				con.repaint();
				intFrame2++;
				
				if (intFrame2 == intMaxPlayerFrames)
				{
					intFrame2 = 0;
				}
				
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
			con.drawImage(playerAttack[intFrame], 200, 275);
			con.repaint();
			con.sleep(300);
		}
	}
	
	public static void drawEnemyAttack(Console con, BufferedImage[] enemyAttack, BufferedImage eBackground, int intNumOfFrames, int intEnemyType)
	
	{
		int intFrame;
		
		if (intEnemyType == 1)
		{
			for (intFrame = 0; intFrame < intNumOfFrames; intFrame++)
			{			
				con.drawImage(eBackground, 800, 250);
				con.drawImage(enemyAttack[intFrame], 850, 300);
				con.repaint();
				con.sleep(300);
			}
		}
		else if (intEnemyType == 2)
		{
			for (intFrame = 0; intFrame < intNumOfFrames; intFrame++)
			{			
				con.drawImage(eBackground, 800, 250);
				con.drawImage(enemyAttack[intFrame], 650, 250);
				con.repaint();
				con.sleep(300);
			}
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