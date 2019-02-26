import java.awt.color.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image.*;
import arc.*;

public class BattleAnimationTests
{
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);
		
		int intFrame;
		BufferedImage[] skeletonStatic = new BufferedImage[3];
		for (intFrame = 0; intFrame < 3; intFrame++)
		{
			skeletonStatic[intFrame] = con.loadImage("skeletonStatic" + intFrame + ".png");
		}
		
		BufferedImage[] enemyAttack = new BufferedImage[4];
		for (intFrame = 0; intFrame < 4; intFrame++)
		{
			enemyAttack[intFrame] = con.loadImage("skeletonAttack" + intFrame + ".png");
		}
		
		BufferedImage background = con.loadImage("battleBackground.png");
		BufferedImage pBackground = con.loadImage("playerBackground.png");
		BufferedImage eBackground = con.loadImage("enemyBackground.png");
		
		con.drawImage(background, 0, 0);
		con.repaint();
		
		int intCount = 0;
		
		while (intCount < 300)
		{
			for (intCount = 0; intCount < intFrame; intCount++)
			{			
				con.drawImage(eBackground, 800, 250);
				con.drawImage(enemyAttack[intCount], 850, 300);
				con.repaint();
				con.sleep(500);
			}
		}
	}
}
