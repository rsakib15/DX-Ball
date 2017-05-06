package pack.game.dxball;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	MediaPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        player = MediaPlayer.create(getApplicationContext(), R.raw.menubg);
        player.setLooping(true);
        player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    public void onClickPlay(View view) {
        player.stop();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameActivity(this));
    }

    public void onClickAbout(View view) {
    	Log.d("Click-Log","Click on About Button");
        Intent i = new Intent(MainActivity.this,About.class);
        startActivity(i);
    }
    
    public void onClickHighScore(View view) {
    	Log.d("Click-Log","Click on High Score Button");
        Intent i = new Intent(MainActivity.this,HighScore.class);
        startActivity(i);
    }

    public void onClickExit(View view) {
       player.stop();
       this.finish();
       System.exit(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
        this.finish();
        System.exit(0);
    }
	
}
