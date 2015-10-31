package Platformers;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.*;


public class GamePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=400,HEIGHT=400;
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private int FPS=30;
	private int targetTime = 100/FPS;
	
	private TileMap tileMap;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	public void addNotify() {
		super.addNotify();
		if(thread==null){
			thread = new Thread(this);
			thread.start();
			
		}
	}
	@Override
	public void run() {
		
		init();
		long startTime;
		long urdTime ,waitTime;
		while(running){
			startTime=System.nanoTime();
			update();
			render();
			draw();
			urdTime=(System.nanoTime()-startTime)/1000000;
			waitTime=targetTime-urdTime;
			try{
				Thread.sleep(waitTime);
			}
			catch(Exception e){
				
			}
		}
	}
	private void init(){
		running = true;
		tileMap=new TileMap("res/testMap.txt", 32);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		
	}
////////////////////////////////////////////////////////////////
	private void update(){
		tileMap.update();
	}
private void render(){
		tileMap.draw(g);
	}
private void draw(){
	Graphics g2=this.getGraphics();
	g2.drawImage(image, 0,0,null);
	g2.dispose();
}

}
