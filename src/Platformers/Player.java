
package Platformers;
import java.awt.*;

public class Player {
private double x,y,dx,dy;

private int width,height;

private boolean left,right,jumping,falling;

private double moveSpeed,maxSpeed,maxFallingSpeed,stopSpeed,jumpStart,gravity;

public Player(TileMap tm){
	tileMap=tm;
	width=20;
	height=20;
	moveSpeed=0.6;
	maxSpeed=4.2;
	maxFallingSpeed=12;
	stopSpeed=0.3;
	jumpStart=-11.0;
	gravity=0.64;
	 
	
}

public void setLeft(boolean left) {
	this.left = left;
}

public void setRight(boolean right) {
	this.right = right;
}

public void setJumping(boolean jumping) {
	if(!falling)this.jumping = jumping;
}

//////////////////////////////////////
public void update(){
	//determine next Position
	if(left){
		dx-=moveSpeed;
		if(dx<-maxSpeed){
			dx=-maxSpeed;
		}
	}
	else if (right){
		dx+=moveSpeed;
		if(dx>maxSpeed){
			dx=maxSpeed;
		}
	}
	else{
		if(dx>0){
			dx-=stopSpeed;
		
		if(dx<0)dx=0;
		}
		else if (dx<0){
			dx+=stopSpeed;
			if(dx>0){
				dx=0;
			}
				
		}
		}
	
	if(jumping){
		dy=jumpStart;  
		falling =true;
		jumping=false;
		
	}
	if(falling){
		dy+=gravity;
		if(dy>maxFallingSpeed){
			dy=maxFallingSpeed;
		}
	
	}
	else{
		dy=0; 
	}
	int currentCol=tileMap.getColTile((int)x);
	int currentRow=tileMap.getRowTile((int)y);
	
	double toX=x+dx;
	double toY=y+dy;
	double tempX=x;
	double tempY=y;
}
public void draw(Graphics2D g){
	int tx=tileMap.getX();
	int ty=tileMap.getY();
	g.setColor(Color.RED);
	g.fillRect((int)(tx+x-width/2),(int)(ty+y-height/2), width, height);
}
}
