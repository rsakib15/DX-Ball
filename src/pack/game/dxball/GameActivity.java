package pack.game.dxball;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

class GameActivity extends View{
	
	public static int life = 3;
    public static boolean gameOver=false;
    static int score=0;
    int level;
    float xT;
    
    boolean createGame;
    boolean gameStart = true;
  
    Canvas canvas;
    Paint paint;
    Bar bar;
    Ball ball;
    Score scorelist;
    ArrayList<Brick>bricks =new ArrayList<Brick>();
    Stage stage = new Stage();
    

    @SuppressLint("ClickableViewAccessibility")
	public GameActivity(Context context) {
        super(context);
        paint =new Paint();
        level = 1;
        createGame = true;
        gameOver = false;
        
        bar = new Bar();
        ball = new Ball();
        
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
				if(ball.isballAvailable()){
					xT = e.getX();
					if(xT<v.getWidth()/2 && bar.getBarLeft()>0){
						bar.setBarLeft(bar.getBarLeft()-10);
						bar.setBarRight(bar.getBarRight()-10);
						xT = -10;
					}
					else if(xT >= v.getWidth()/2 && bar.getBarRight()<v.getWidth()){
						bar.setBarLeft(bar.getBarLeft()+10);
						bar.setBarRight(bar.getBarRight()+10);
						xT = -10;
					}
					Log.i("Bar Position" , "Left : " + bar.getBarLeft() + "  Right : " + bar.getBarRight());
				} 
            	return true;	
            }
        });
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gameStart) {
            gameStart = false;
            bar.setBar(canvas);
            ball.setBall(canvas,bar);
        }
        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        ball.drawBall(canvas,paint);
        bar.drawBar(canvas,paint);
        ball.nextPos(canvas, bar, paint);
        drawLifeText(canvas);
        
        if (createGame) {
            createGame = false;
            if (level == 1) {
                stage.levelOne(canvas,bricks); 
            }
            else if (level == 2) {
                stage.levelTwo(canvas,bricks);
            }
            else{
                gameOver = true;
            }
        }
        for(int i = 0; i< bricks.size(); i++){
            canvas.drawRect(bricks.get(i).getLeft(), bricks.get(i).getTop(), bricks.get(i).getRight(), bricks.get(i).getBottom(), bricks.get(i).getPaint());
        }
        invalidate();
        ball.ballBrickCollision(bricks,ball);
        
        if(ball.isballAvailable() == false && gameOver==false){
            ball.setBallAvailable(true);
            gameStart = true;
            ball.setDX(5);
            ball.setDY(-5);
        }
        
        if(life !=0 && bricks.size()==0){
        	level++;
        	gameStart=true;
        	createGame=true;
        }
        
        if(life==0 || gameOver==true){
        	levelComplete();
        }
    }
    
    public void levelComplete() {
          Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                     Intent i = new Intent(getContext(), Score.class);
                     i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     String strName = String.valueOf(score);
                     i.putExtra("LEVEL", strName);
                     getContext().startActivity(i);
                     System.exit(0);
       
                }
         }, 10);
    }

    public void drawLifeText(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setColor(Color.RED);
        paint.setTypeface(Typeface.create("Arial",Typeface.BOLD));
        paint.setTextSize(40);
        canvas.drawText("LIFE : " + life,canvas.getWidth() - 150 , 60, paint);
        canvas.drawText("LEVEL : " + level, canvas.getWidth() / 2 - 60, 60, paint);
        canvas.drawText("SCORE : " + score,  20, 60, paint); 
    }
    

 
}