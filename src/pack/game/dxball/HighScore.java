package pack.game.dxball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HighScore extends Activity {
	public static final String shared_preference="HighScore";
	public static String highscore;
	TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.highscore);
        tv1 = (TextView) findViewById(R.id.TextHighScore);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tv1.setText(getMemoryValue());
	}
	

	public String getMemoryValue(){
		SharedPreferences settings=getSharedPreferences(HighScore.shared_preference,0);
		highscore=settings.getString("storevalue", "0");
		if(highscore.length()==0){
			highscore="0";
		}
		return highscore;
	}
	
	public void onClickBack(View view) {
		 Intent i = new Intent(HighScore.this,MainActivity.class);
		 	startActivity(i);
		 	finish();
	 }
}
