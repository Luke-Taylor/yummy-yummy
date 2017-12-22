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

public class FoodSelection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        LinearLayout placeholders = findViewById(R.id.foodContainer);

        findViewById(R.id.chkBasketPage).setVisibility((Basket.getInstance().Items.size() > 0) ? View.VISIBLE : View.INVISIBLE);

        for(int i = 0; i < 10; i++){
            View v = LayoutInflater.from(this).inflate(R.layout.foodplaceholder, null);
            v.findViewById(R.id.placeholderButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Basket.getInstance().AddItem(view.getTag().toString());
                    Toast.makeText(getApplicationContext(),"Added to basket", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.chkBasketPage).setVisibility(View.VISIBLE);
                }
            });
            placeholders.addView(v);
            Log.d("yummy yummy","Added view " + i);
        }
    }

    public void openBasket(View v){
        Intent intent = new Intent(getApplicationContext(), ViewBasket.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        finish();
    }

}
