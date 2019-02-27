import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import arc.*;

public class Test
{
	public static void main(String[] args)
	{
		Console con = new Console(1600, 1000);
		
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
	}
}
