package pack.game.dxball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends Activity{
	TextView tv1;
	String newString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.score);
        tv1 = (TextView) findViewById(R.id.btnPoint);
        Bundle extras = getIntent().getExtras();
        newString = extras.getString("LEVEL");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tv1.setText(newString);
		Log.d("Entry-Log","new Highscore");
		if(Integer.parseInt(newString)>Integer.parseInt(getMemoryValue())){
			Log.d("Entry-Log","new Highscore Done");
			setMemoryValue(String.valueOf(newString));
		}
		
	}
	
	public void setMemoryValue(String str){
		SharedPreferences settings=getSharedPreferences(HighScore.shared_preference,0);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("storevalue",str);
		editor.commit();
	}
	
	public String getMemoryValue(){
		SharedPreferences settings=getSharedPreferences(HighScore.shared_preference,0);
		String highscore=settings.getString("storevalue", "0");
		if(highscore.length()==0){
			highscore="0";
		}
		return highscore;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	 public void onClickTryAgain(View view) {
	        //player.stop();
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(new GameActivity(this));
	 }
	
	 public void onClickMainMenu(View view) {
	        //player.stop();
		 	Intent i = new Intent(Score.this,MainActivity.class);
		 	startActivity(i);
		 	finish();
	 }
}
