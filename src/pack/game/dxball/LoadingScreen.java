package pack.game.dxball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class LoadingScreen extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loading);
       
        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    Log.d("Enrty-Log","Show Loading Secreen");
                    sleep(5000);
                } catch (Exception e) {

                } finally {
                	Log.d("Enrty-Log", "Loading Finish, Main Menu Activity");
                    Intent i = new Intent(LoadingScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}
