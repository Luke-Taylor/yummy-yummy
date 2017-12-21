package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StartupScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "DEFAULT", "albertus-medium.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "albertus-medium.ttf");
        setContentView(R.layout.activity_startup_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), FoodSelection.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
