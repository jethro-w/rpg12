import arc.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color.*;

public class jethroWTools
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
    Font menuFont = con.loadFont("alagard.ttf", 90);
    Font menuChoiceFont = con.loadFont("alagard.ttf", 30);
    Font defaultFont = con.loadFont("Hack-Regular.ttf", 15);
    BufferedImage dragon = con.loadImage("titleDragon.png");
    BufferedImage background = con.loadImage("background.png");
    
    con.setDrawFont(menuFont);
    con.drawImage(background, 0, 0);
    con.repaint();
    con.drawImage(dragon, 200, 0);
    con.repaint();
    con.setDrawColor(Color.BLACK);
    con.drawString("How to Kill a Dragon", 205, 105);
    con.repaint();
    con.setDrawColor(Color.RED);
    con.drawString("How to Kill a Dragon", 200, 100);
    con.repaint();
    
    while(chrLR != ' ')
    {
      con.setDrawFont(menuChoiceFont);
      con.setDrawColor(Color.RED);
      if(intNext == 1)
      {
        con.drawString("Play Game", 200, 500);
      }
      else if(intNext == 2)
      {
        con.drawString("Controls", 400, 500);
      }
      else if(intNext == 3)
      {
        con.drawString("Help", 600, 500);
      }
      else if(intNext == 4)
      {
        con.drawString("High Scores", 750, 500);
      }
      else if(intNext == 5)
      {
        con.drawString("Quit", 1000, 500);
      }
      
      con.setDrawColor(Color.WHITE);
      if(intNext != 1)
      {
        con.drawString("Play Game", 200, 500);
      }
      if(intNext != 2)
      {
        con.drawString("Controls", 400, 500);
      }
      if(intNext != 3)
      {
        con.drawString("Help", 600, 500);
      }
      if(intNext != 4)
      {
        con.drawString("High Scores", 750, 500);
      }
      if(intNext != 5)
      {
        con.drawString("Quit", 1000, 500);
      }
      con.drawString("Press [space] to select", 450, 650);
      con.repaint();
      
      chrLR = con.getChar();
      if(chrLR == 'a' && intNext != 1)
      {
        intNext = intNext - 1;
      }
      else if(chrLR == 'd' && intNext != 5)
      {
        intNext = intNext + 1;
      }
      con.repaint();
      con.setDrawFont(defaultFont);
    }
    return intNext;
  }
  // Load Map to array
  public static String[] loadMap(Console con)
  {
    int intRow;
    int intColumn;
    int intY = 0;
    int intX = 0;
    TextInputFile overworld = new TextInputFile("map.txt");
    String strMapLine[] = new String[20];
    
    // Load Map into Char array
    for(intRow = 0; overworld.eof() == false; intRow++)
    {
      strMapLine[intRow] = overworld.readLine();
    }
    overworld.close();
    
    return strMapLine;
  }
  // Print Map to Screen
  public static String[][] printMap(Console con, String strMapLine[])
  {
    int intRow;
    int intColumn;
    int intCount;
    int intX = 200;
    int intY = 100;
    String strMap[][] = new String[20][20];
    
    // Load Textures
    
    return strMap;
  }
  // Draw Console
  public static void drawGameConsole(Console con)
  {
    Font defaultFont = con.loadFont("Hack-Regular.ttf", 15);
    
    con.setDrawFont(defaultFont);
    con.setDrawColor(Color.BLACK);
    con.fillRect(850, 100, 300, 600);
    con.setDrawColor(Color.WHITE);
    con.fillRect(850, 100, 4, 600);
    con.fillRect(850, 100, 300, 4);
    con.fillRect(850, 696, 300, 4);
    con.fillRect(1146, 100, 4, 600);
    con.drawString("Console:", 862, 112);
    con.repaint();
  }
  public static void printStats(Console con)
  {
    int intRow;
    int intColumn;
    int intCount;
    Font defaultFont = con.loadFont("Hack-Regular.ttf", 15);
    double dblStats[][] = new double[3][5];
    TextInputFile playerStats = new TextInputFile("playerStats.txt");
    
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        dblStats[intRow][intColumn] = playerStats.readDouble();
      }
    }
    jethroWTools.drawGameConsole(con);
    con.setDrawFont(defaultFont);
    con.setDrawColor(Color.WHITE);
    con.drawString("Your stats:", 862, 136);
    con.drawString("Current HP: "+dblStats[0][0]+"/200", 862, 160);
    con.drawString("Attack: "+dblStats[0][1], 862, 184);
    con.drawString("Defense: "+dblStats[0][2], 862, 208);
    con.drawString("Buff Power: "+dblStats[0][3], 862, 232);
    con.drawString("Healing: "+dblStats[0][4], 862, 256);
    con.repaint();
  }
  // Battle
  public static int battle(Console con, int intY, int intX, String strMap[][], char chrClan)
  {
    int intRow;
    int intColumn;
    int intCount;
    int intRand = 0;
    int intEnemyType = 0;
    int intBarMultiplier;
    int intMaxHP = 200;
    int intEMaxHP;
    int intEndBattle = 0;
    int intEMissingHP;
    int intEnemyMissingHP;
    char chrMove = 'n';
    double dblStats[][] = new double[3][5];
    Boolean blnDefend = false;
    Boolean blnEnemyDefend = false;
    BufferedImage fire = con.loadImage("fire.png");
    BufferedImage water = con.loadImage("water.png");
    BufferedImage earth = con.loadImage("earth.png");
    BufferedImage dragonAttack = con.loadImage("dragonAttack.png");
    BufferedImage dragonDefend = con.loadImage("dragonDefend.png");
    BufferedImage dragonStatic = con.loadImage("dragonStatic.png");
    BufferedImage skeletonStatic = con.loadImage("skeletonStatic.png");
    BufferedImage skeletonAttack = con.loadImage("skeletonAttack.png");
    TextInputFile playerStats = new TextInputFile("playerStats.txt");
    TextOutputFile printStats = new TextOutputFile("playerStats.txt");
    
    // Read player stats from playerStats.txt
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        dblStats[intRow][intColumn] = playerStats.readDouble();
      }
    }
    
    if(strMap[intY][intX].equals("e"))
    {
      intEnemyType = 1;
    }
    else if(strMap[intY][intX].equals("b"))
    {
      intEnemyType = 2;
    }
    intEMaxHP = (int)(dblStats[intEnemyType][0]);
    
    // Column 0 = Health
    // Column 1 = Attack
    // Column 2 = Defend
    // Column 3 = Buff
    // Column 4 = Heal
    
    // Draw Game Console
    con.setDrawColor(Color.WHITE);
    con.fillRect(168, 560, 864, 4);
    con.fillRect(168, 656, 864, 4);
    con.fillRect(168, 560, 4, 96);
    con.fillRect(1028, 560, 4, 96);
    con.drawString("Console:", 192, 572);
    con.drawString("You have been approached by an enemy!", 192, 596);
    con.repaint();
    con.sleep(2000);
    
    while(intEndBattle == 0)
    {
      jethroWTools.clear(con);
      
      if(chrClan == 'f')
      {
        con.drawImage(fire, 150, 225);
      }
      else if(chrClan == 'w')
      {
        con.drawImage(water, 150, 225);
      }
      else if(chrClan == 'e')
      {
        con.drawImage(earth, 150, 225);
      }
      if(intEnemyType == 1)
      {
        con.drawImage(skeletonStatic, 750, 200);
      }
      else if(intEnemyType == 2)
      {
        con.drawImage(dragonStatic, 700, 200);
      }
      con.repaint();
      
      con.setDrawColor(Color.WHITE);
      con.fillRect(168, 560, 864, 4);
      con.fillRect(168, 656, 864, 4);
      con.fillRect(168, 560, 4, 96);
      con.fillRect(1028, 560, 4, 96);
      con.drawString("Console:", 192, 572);
      con.drawString("What will you do?", 192, 596);
      con.drawString("[1] Attack          [2]Block/Defend         [3]Buff          [4]Heal", 192, 620);
      con.drawString("Health: "+(int)dblStats[0][0]+" / "+intMaxHP, 200, 70);
      con.drawString("Health: "+(int)dblStats[intEnemyType][0]+" / "+intEMaxHP, 800, 70);
      con.repaint();
      
      // Health Bars
      con.setDrawColor(Color.GREEN);
      con.fillRect(200, 100, 200, 30);
      con.fillRect(800, 100, 200, 30);
      con.repaint();
      con.setDrawColor(Color.RED);
      intBarMultiplier = 200 / intEMaxHP;
      intEMissingHP = intMaxHP - (int)dblStats[0][0];
      intEnemyMissingHP = intEMaxHP - (int)dblStats[intEnemyType][0];
      // Player Red Bar
      con.fillRect(400 - intEMissingHP, 100, intEMissingHP, 30);
      // Enemy Red Bar
      con.fillRect(1000 - (intEnemyMissingHP * intBarMultiplier), 100, intEnemyMissingHP * intBarMultiplier, 30);
      con.repaint();
      
      if(dblStats[0][0] <= 0.0 || dblStats[intEnemyType][0] <= 0.0)
      {
        jethroWTools.battleConsoleClear(con);
        if(dblStats[0][0] <= 0.0)
        {
          con.drawString("You have been defeated.", 192, 596);
          intEndBattle = 2;
          con.repaint();
          con.setDrawColor(Color.RED);
          con.fillRect(200, 100, 200, 30);
        }
        else if(dblStats[intEnemyType][0] <= 0.0)
        {
          con.drawString("You have defeated the enemy.", 192, 596);
          intEndBattle = 1;
          con.repaint();
          con.setDrawColor(Color.RED);
          con.fillRect(800, 100, 200, 30);
        }
        con.repaint();
        con.sleep(1000);
      }
      if(intEndBattle == 0)
      {
        while(chrMove != '1' && chrMove != '2' && chrMove != '3' && chrMove != '4' && chrMove != '5')
        {
          chrMove = con.getChar();
          con.setDrawColor(Color.WHITE);
        }
        jethroWTools.battleConsoleClear(con);
        if(chrMove == '1')
        {
          if(blnEnemyDefend == false)
          {
            dblStats[intEnemyType][0] = dblStats[intEnemyType][0] - dblStats[0][1];
          }
          else if(blnEnemyDefend == true)
          {
            dblStats[intEnemyType][0] = dblStats[intEnemyType][0] - dblStats[0][1] + dblStats[intEnemyType][2];
            blnEnemyDefend = false;
          }
          con.drawString("You chose to attack.", 192, 596);
          con.repaint();
        }
        else if(chrMove == '2')
        {
          blnDefend = true;
          con.drawString("You chose to defend.", 192, 596);
          con.repaint();
        }
        else if(chrMove == '3')
        {
          for(intColumn = 2; intColumn < 5; intColumn++)
          {
            dblStats[0][intColumn] = dblStats[0][intColumn] + 0.25;
          }
          con.drawString("You chose to buff.", 192, 596);
          con.repaint();
        }
        else if(chrMove == '4')
        {
          if(intMaxHP - (int)dblStats[0][4] >= (dblStats[0][0]))
          {
            dblStats[0][0] = dblStats[0][0] + dblStats[0][4];
            con.drawString("You chose to heal.", 192, 596);
            con.repaint();
          }
          else
          {
            con.drawString("You cannot heal.", 192, 596);
            con.repaint();
          }
        }
        // SECRET CODE
        else if(chrMove == '5')
        {
          intEndBattle = 1;
        }
        con.drawString("Console", 192, 572);

        con.drawString("Press [space] to continue.", 192, 620);
        con.repaint();
        
        while(chrMove != ' ')
        {
          chrMove = con.getChar();
        }
        
        // Enemy's Turn
        // Plain Enemy
        if(intEnemyType == 1)
        {
          intRand = (int)(Math.random() * 2) + 1;
        }
        // Boss
        else if(intEnemyType == 2)
        {
          intRand = (int)(Math.random() * 10) + 1;
        }
        jethroWTools.battleConsoleClear(con);
        
        if((intEnemyType == 1 && intRand == 1) || (intEnemyType == 2 && intRand >= 1 && intRand <= 7))
        {
          con.drawString("The enemy chose to attack.", 192, 596);
          con.repaint();
          if(blnDefend == false)
          {
            dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1];
          }
          else if(blnDefend == true)
          {
            dblStats[0][0] = dblStats[0][0] - dblStats[intEnemyType][1] + dblStats[0][2];
            blnDefend = false;
          }
          if(intEnemyType == 1)
          {
            con.setDrawColor(Color.BLACK);
            con.fillRect(600, 150, 600, 400);
            con.repaint();
            con.drawImage(skeletonAttack, 550, 200);
          }
          else if(intEnemyType == 2)
          {
            con.setDrawColor(Color.BLACK);
            con.fillRect(600, 150, 600, 400);
            con.repaint();
            con.drawImage(dragonAttack, 600, 160);
          }
        }
        else if((intEnemyType == 1 && intRand == 2) || (intEnemyType == 2 && intRand >= 7 && intRand <= 10))
        {
          blnEnemyDefend = true;
          con.drawString("The enemy chose to defend.", 192, 596);
          con.repaint();
          if(intEnemyType == 1)
          {
            con.setDrawColor(Color.BLACK);
            con.fillRect(600, 150, 600, 400);
            con.repaint();
            con.drawImage(skeletonStatic, 750, 200);
          }
          else if(intEnemyType == 2)
          {
            con.setDrawColor(Color.BLACK);
            con.fillRect(600, 150, 600, 400);
            con.repaint();
            con.drawImage(dragonDefend, 600, 225);
          }
        }
        else if(intRand >= 8 && intRand <= 10)
        {
          dblStats[intEnemyType][0] = dblStats[intEnemyType][0] + dblStats[intEnemyType][4];
        }
        con.sleep(1000);
      }
    }
    // Print Stats Back into playerStats.txt (only player stats change)
    dblStats[1][0] = 50;
    
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        printStats.println(dblStats[intRow][intColumn]);
      }
    }
    playerStats.close();
    printStats.close();
    
    return intEndBattle;
  }
  public static void items(Console con, String strMap[][], int intY, int intX)
  {
    int intRow;
    int intColumn;
    double dblStats[][] = new double[3][5];
    TextInputFile playerStats = new TextInputFile("playerStats.txt");
    TextOutputFile printStats = new TextOutputFile("playerStats.txt");
    
    // Read player stats from playerStats.txt (items affect player stats)
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        dblStats[intRow][intColumn] = playerStats.readDouble();
      }
    }
    playerStats.close();
    
    // Column 0 = Health
    // Column 1 = Attack
    // Column 2 = Defend
    // Column 3 = Buff
    // Column 4 = Heal
    
    jethroWTools.drawGameConsole(con);
    // Double Edged Sword
    if(strMap[intY][intX].equals("1"))
    {
      
    }
    // Potion of Power
    else if(strMap[intY][intX].equals("2"))
    {
      
    }
    // Cast Iron Armour
    else if(strMap[intY][intX].equals("3"))
    {
      
    }
    // Spellbook of Healing
    else if(strMap[intY][intX].equals("4"))
    {
      
    }
    else if(strMap[intY][intX].equals("5"))
    {
      
    }
    // Potion of Health
    else if(strMap[intY][intX].equals("6"))
    {
      
    }
    con.repaint();
    strMap[intY][intX] = "g";
    
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        printStats.println(dblStats[intRow][intColumn]);
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
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        dblStats[intRow][intColumn] = playerStats.readDouble();
      }
    }
    
    if(chrClan == 'f')
    {
      dblStats[0][0] = dblStats[0][0] - 10;
      con.drawString("Ouch...that did 5 damage.", 862, 184);
      con.repaint();
    }
    else if(chrClan == 'e')
    {
      dblStats[0][0] = dblStats[0][0] - 5;
      con.drawString("Ouch...that did 5 damage.", 862, 184);
      con.repaint();
    }
    
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
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
    double dblStats[][] = new double[3][5];
    TextOutputFile printStats = new TextOutputFile("playerStats.txt");
    
    dblStats[0][0] = 200;
    dblStats[0][1] = 20;
    dblStats[0][2] = 15;
    dblStats[0][3] = 5;
    dblStats[0][4] = 12;
    dblStats[1][0] = 50;
    dblStats[1][1] = 15;
    dblStats[1][2] = 15;
    dblStats[1][3] = 0;
    dblStats[1][4] = 0;
    dblStats[2][0] = 150;
    dblStats[2][1] = 21;
    dblStats[2][2] = 12;
    dblStats[2][3] = 0;
    dblStats[2][4] = 3;
    
    for(intRow = 0; intRow < 3; intRow++)
    {
      for(intColumn = 0; intColumn < 5; intColumn++)
      {
        printStats.println(dblStats[intRow][intColumn]);
      }
    }
  }
  // Victory Screen
  public static void vScreen(Console con)
  { 
    
  }
  // Lose Screen
  public static void lScreen(Console con)
  {
    
  }
  public static void battleConsoleClear(Console con)
  {
    con.setDrawColor(Color.BLACK);
    con.fillRect(172, 564, 856, 92);
    con.repaint();
    con.setDrawColor(Color.WHITE);
    con.drawString("Console:", 192, 572);
    con.repaint();
  }
  public static void helpMenu(Console con)
  {
    int intPage = 1;
    boolean blnLeave = false;
    char chrLeave = ' ';
    
	while(blnLeave == false)
    {
      if(intPage == 1)
      {
        
        if(chrLeave == 's')
        {
          blnLeave = true;
        }
        else if(blnLeave == false)
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
    String strNum;
    
    for(intCountA = 0; intCountA < 10; intCountA++)
    {
      strPlace[intCountA] = (""+intCountB+"");
      intCountB++;
    }
    
    while(highscores.eof() == false)
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
    
    for(intCountA = 0; intCountA < intNumUsers; intCountA++)
    {
      strHighscores[intCountA][0] = highscores.readLine();
      strHighscores[intCountA][1] = highscores.readLine();
      strHighscores[intCountA][2] = highscores.readLine();
    }
    highscores.close();
    
    // Sort Highscores
    for(intCountB = 0; intCountB < intNumUsers - 1; intCountB++)
    {
      for(intCountA = 0; intCountA < intNumUsers - 1; intCountA++)
      {
        if(Integer.parseInt(strHighscores[intCountA][1]) > Integer.parseInt(strHighscores[intCountA + 1][1]))
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
    for(intCountA = 0; intCountA < intNumUsers; intCountA++)
    {
      con.drawString(strPlace[intCountA]+".", intX, intY);
      con.drawString(strHighscores[intCountA][0], intX + 50, intY);
      con.drawString(strHighscores[intCountA][1], intX + 500, intY);
      if(strHighscores[intCountA][2].equals("f"))
      {
        con.drawString("Fire", intX + 900, intY);
      }
      else if(strHighscores[intCountA][2].equals("e"))
      {
        con.drawString("Earth", intX + 900, intY);
      }
      else if(strHighscores[intCountA][2].equals("w"))
      {
        con.drawString("Water", intX + 900, intY);
      }
      con.repaint();
      intY = intY + 50;
    }
  }
  // Clear Screen Method
  public static void clear(Console con)
  {
    con.setDrawColor(Color.BLACK);
    con.fillRect(0, 0, 1200, 800);
    con.repaint();
  }
}