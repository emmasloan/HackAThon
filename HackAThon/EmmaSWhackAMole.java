package classwork;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.util.Random;

import javax.imageio.ImageIO;

//Emma Sloan
//Intro to Computer Science
//block G
//May 14, 2018
//trying to tap a mole with the cursor to get a good score
public class EmmaSWhackAMole extends JoeApplet implements KeyListener, MouseListener, MouseMotionListener
{

	//declare variables
	
	//solid objects
	SolidObject mole = new SolidObject();
	SolidObject ball1SO = new SolidObject();
	
	//random generator
	Random gen = new Random();

	//booleans
	boolean startScreen = true;
	boolean playGame = true;
	boolean firstHit = true;
	
	//integers
	int score = 0;
	int count;
	int time;
	int ballX = 100;
	int ballY = 100;
	int rand;
	int Xposition;
	int Yposition;
	int speed = 1;
	
	//images
	Image img;
	
	//colors and fonts
	Color background = new Color(153, 204, 255);
	Color brown = new Color(102, 51, 0);
	Color grey = new Color(96 ,96, 96);
	Font big = new Font("arial", Font.BOLD, 30);
	Font smallBold = new Font("arial", Font.BOLD, 20);
	Font small = new Font("arial",Font.ITALIC, 15);
	Font medium = new Font("arial", Font.BOLD, 15);
	
	//necessary use
	public void init()
	{
		img = getImage(getDocumentBase(),"mole.jpg");
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	//draws the start screen before the game
	public void drawStartScreen(Graphics art)
	{
		setBackground(background);
		art.setColor(grey);
		art.setFont(big);
		art.drawString("Hello, Welcome to Whack-A-Mole", 100, 100);
		art.setFont(small);
		art.drawString("left click when your cursor is over a mole to hit it,", 150, 150);
		art.drawString("hit as many moles as you can in thirty seconds", 155, 175);
		art.setFont(medium);
		art.drawString("Press 1 for a slow speed", 205, 250);
		art.drawString("Press 2 for a medium speed", 195, 270);
		art.drawString("Press 3 for a fast speed", 207, 290);
		art.setFont(big);
		art.drawString("Press X to begin", 180, 380);
	}
	
	//draw end screen after the game
	public void endScreen(Graphics art)
	{
		
		setBackground(background);
		art.setColor(grey);
		art.setFont(big);
		art.drawString("Game Over", 250, 100);
		art.setFont(small);
		art.drawString("your final score was", 260, 150);
		art.setFont(big);
		art.drawString(" " +score, 310, 200);
		art.setFont(smallBold);
		art.drawString("press r to restart", 245, 250);
	}
	
	//draws background for the game 
	public void drawBackground(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(50, 50, 600, 400);
		art.setColor(brown);
		art.fillOval(100, 100, 65, 25);
		art.fillOval(100, 175, 65, 25);
		art.fillOval(100, 250, 65, 25);
		art.fillOval(100, 325, 65, 25);
		art.fillOval(100, 400, 65, 25);
		art.fillOval(250, 100, 65, 25);
		art.fillOval(250, 175, 65, 25);
		art.fillOval(250, 250, 65, 25);
		art.fillOval(250, 325, 65, 25);
		art.fillOval(250, 400, 65, 25);
		art.fillOval(400, 100, 65, 25);
		art.fillOval(400, 175, 65, 25);
		art.fillOval(400, 250, 65, 25);
		art.fillOval(400, 325, 65, 25);
		art.fillOval(400, 400, 65, 25);
		art.fillOval(550, 100, 65, 25);
		art.fillOval(550, 175, 65, 25);
		art.fillOval(550, 250, 65, 25);
		art.fillOval(550, 325, 65, 25);
		art.fillOval(550, 400, 65, 25);
	}

	//sets up ball that follows mouse
	public void mouseWork(Graphics art)
	{
		art.setColor(Color.GREEN);
		art.fillOval(ballX, ballY, 1, 1);
	}
	
	//creates the moles appear on the holes
	public void drawMoles(Graphics art)
	{
		if(count % 2 == 0)
		{
		Xposition = gen.nextInt(4);
		int[] getX = {100, 250, 400, 550};
		Xposition = getX[Xposition];

		Yposition = gen.nextInt(5);
		int[] getY = {75, 160, 235, 310, 385};
		Yposition = getY[Yposition];
		art.drawImage(img, Xposition, Yposition, 65, 50, this);
		mole.makeSolidObject(Xposition, Yposition, 75, 75);
		}
		
	}
	
	//when the mole and mouse collide
	public void getPoints(Graphics art)

	{
		if(ball1SO.isCollidingWith(mole))
		{
			if(firstHit)
			{
				score ++;
				firstHit = false;
			}
			else
			{
				firstHit = true;
			}
		}
	}

	//keeps track of time
	public void timer(Graphics art)
	{
		count ++;
		if(count % speed == 0)
		{
			time ++;
		}
		if(time >= 30)
			playGame = false;
		art.setColor(grey);
		art.drawString("Timer = " +time, 50, 25);
	}
	
	//keeps track of score
	public void drawScore(Graphics art)
	{
		art.setColor(grey);
		art.drawString("Score =" +score, 500, 25);
	}
	
	//starts the program
	public void paint(Graphics art)
	{
		setSize(700, 500);
		setFrameRate(speed);
		if(startScreen)
		{
			drawStartScreen(art);
		}
		else if(playGame)
		{
			drawBackground(art);
			drawMoles(art);
			mouseWork(art);
			getPoints(art);
			timer(art);
			drawScore(art);
		}
		else
		{
			endScreen(art);
			
		}
		repaint();
	}

//empty
@Override
public void mouseClicked(MouseEvent e)
{
}

//empty
@Override
public void mouseEntered(MouseEvent e)
{
}

//empty
@Override
public void mouseExited(MouseEvent e)
{
}

//used
@Override
public void mousePressed(MouseEvent e)
{
	int press = e.getButton();
	if(press == e.BUTTON1)
	{
		ball1SO.makeSolidObject(ballX, ballY, 40, 40);
	}
}

//used
@Override
public void mouseReleased(MouseEvent e)
{
	int release = e.getButton();
	if(release == e.BUTTON1)
	{
		firstHit = true;
	}
}

//empty
@Override
public void mouseDragged(MouseEvent e)
{
}

//used
@Override
public void mouseMoved(MouseEvent e)
{
	ballX = e.getX();
	ballY = e.getY();
}

//used
@Override
public void keyPressed(KeyEvent e)
{
	int key = e.getKeyCode();
	if(key == e.VK_X)
	{
		startScreen = false;
	}
	if(key == e.VK_R)
	{
		startScreen = true;
		count = 0;
		time = 0;
		playGame = true;
		score = 0;
		speed = 1;
	}
	if(key == e.VK_1)
	{
		speed = 1;
	}
	if(key == e.VK_2)
	{
		speed = 2;
	}
	if(key == e.VK_3)
	{
		speed = 3;
	}

		
}

//empty
@Override
public void keyReleased(KeyEvent e)
{
}

//empty
@Override
public void keyTyped(KeyEvent e)
{
}

}
