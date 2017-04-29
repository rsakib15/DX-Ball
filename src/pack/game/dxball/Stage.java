package pack.game.dxball;


import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

public class Stage {
	float padding=25;
	float perRowNumberOfBrick = 5;
    float brickDistance = 20;
    float brickHeight = 80;
    float brickX;
    float brickY;
    int col;
    
    public void levelOne(Canvas canvas,ArrayList<Brick> bricks){
    	brickX= 40;
    	brickY= canvas.getHeight()/6;
    	float bricWidth = (((canvas.getWidth())-80) / perRowNumberOfBrick);
    	int cnt=0;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j < perRowNumberOfBrick; j++) {
            	if(cnt%2==0)
            		col = Color.BLACK;
            	else
            		col = Color.CYAN;
            	bricks.add(new Brick(brickX, brickY, brickX + bricWidth, brickY + brickHeight, col));
            	brickX += bricWidth;
             	cnt++;
            }
            brickY += brickHeight;
            brickX = 40;
        } 
    }
    
    public void levelTwo(Canvas canvas,ArrayList<Brick> bricks){
    	brickX= 40;
    	brickY= canvas.getHeight()/6;
    	float bricWidth = (((canvas.getWidth())-80) / perRowNumberOfBrick);
    	int cnt=0;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j < perRowNumberOfBrick; j++) {
            	if(cnt%2==0)
            		col = Color.BLACK;
            	else
            		col = Color.CYAN;
            	bricks.add(new Brick(brickX, brickY, brickX + bricWidth, brickY + brickHeight, col));
            	brickX += bricWidth;
             	cnt++;
            }
            brickY += brickHeight;
            brickX = 40;
        }
    }
}