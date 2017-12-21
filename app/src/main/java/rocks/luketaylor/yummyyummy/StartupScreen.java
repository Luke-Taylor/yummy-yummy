package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartupScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "DEFAULT", "albertus-medium.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "albertus-medium.ttf");
        FontsOverride.setDefaultFont(this, "monospace", "albertus-medium.ttf");
        setContentView(R.layout.activity_startup_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
            }
        }, 2000);
    }
}
