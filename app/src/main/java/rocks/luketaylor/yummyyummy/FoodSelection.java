package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FoodSelection extends Activity implements GestureDetector.OnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        LinearLayout placeholders = findViewById(R.id.foodContainer);

        for(int i = 0; i < 10; i++){
            View v = LayoutInflater.from(this).inflate(R.layout.foodplaceholder, null);
            v.findViewById(R.id.placeholderButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Basket.getInstance().AddItem("Placeholder");
                    Toast.makeText(getApplicationContext(),"Added to basket", Toast.LENGTH_SHORT);
                }
            });
            placeholders.addView(v);
            Log.d("yummy yummy","Added view " + i);
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                Log.d("yummy yummy", "swipe declined");
                return false;
            }
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("yummy yummy","swiped right to left");
                Intent intent = new Intent(getApplicationContext(), ViewBasket.class);
                startActivity(intent);

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //left to right flip
                Log.d("yummy yummy","swiped left to right");
            }
        return false;
    }
}
