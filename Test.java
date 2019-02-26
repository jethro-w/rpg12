import java.awt.Color;
import java.awt.image.BufferedImage;

import arc.*;

public class Test
{
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);
		
		Tools.resetStats(con);
		
		int intRow;
		int intColumn;
		int intRand = 0;
		int intEnemyType = 0;
		int intBarMultiplier;
		int intPMaxHP = 200;
		int intEMaxHP = 150;
		int intEndBattle = 0;
		int intEMissingHP;
		int intEnemyMissingHP;
		char chrMove = 'n';
		double dblStats[][] = new double[3][5];
		Boolean blnDefend = false;
		Boolean blnEnemyDefend = false;
		BufferedImage enemyStatic = con.loadImage("skeletonStatic.png");
		BufferedImage enemyAttack = con.loadImage("skeletonAttack.png");
		BufferedImage background = con.loadImage("battleBackground.png");
		TextInputFile statsIn = new TextInputFile("playerStats.txt");
		TextOutputFile statsOut = new TextOutputFile("playerStats.txt");
		BufferedImage pBackground = con.loadImage("playerBackground.png");
		BufferedImage eBackground = con.loadImage("enemyBackground.png");
		BufferedImage cBackground = con.loadImage("consoleBackground.png");

		// Read player stats from playerStats.txt
		for (intRow = 0; intRow < 3; intRow++)
		{
			for (intColumn = 0; intColumn < 3; intColumn++)
			{
				dblStats[intRow][intColumn] = statsIn.readDouble();
			}
		}
		
		con.drawImage(background, 0, 0);
		con.setDrawColor(Color.WHITE);
		con.fillRect(200, 740, 1192, 4);
		con.fillRect(200, 892, 1192, 4);
		con.fillRect(200, 740, 4, 156);
		con.fillRect(1392, 740, 4, 156);
		
		con.setDrawColor(Color.GREEN);
		con.fillRect(200, 200, 300, 40);
		con.fillRect(1000, 200, 300, 40);
		con.repaint();
		
		con.setDrawColor(Color.RED);
		con.fillRect(216, 564, 856, 92);
		con.repaint();
		
		con.setDrawColor(Color.BLUE);
		con.fillRect(600, 150, 600, 400);
		con.repaint();
		
		con.drawImage(enemyAttack, 850, 300);
		con.repaint();
		
		con.sleep(5000);
		
		con.fillRect(0, 0, 1600, 1000);
		con.setDrawColor(Color.GREEN);
		con.fillRect(200, 200, 300, 40);
		con.fillRect(1000, 200, 300, 40);
		con.repaint();
		con.drawImage(pBackground, 0, 250);
		con.drawImage(eBackground, 850, 250);
		con.drawImage(cBackground, 0, 740);
		con.repaint();
		}
}
