package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FoodSelection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        LinearLayout placeholders = findViewById(R.id.foodContainer);

        for(int i = 0; i < 10; i++){
            placeholders.addView(LayoutInflater.from(this).inflate(R.layout.foodplaceholder, null));
            Log.d("yummy yummy","Added view " + i);
        }

        /*for(int i = 0; i < 10; i++){
            LinearLayout holder = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            holder.setOrientation(LinearLayout.HORIZONTAL);
            holder.setLayoutParams(params);

            ImageView placeholderImage = new ImageView(this); //,null,R.style.FoodPlaceholder);
            placeholderImage.setBackgroundResource(R.drawable.placeholder);

            TextView placeholderText = new TextView(this); //,null,R.style.FoodPlaceholder);
            placeholderText.setText(getText(R.string.placeholder));

            holder.addView(placeholderImage);
            holder.addView(placeholderText);

            placeholders.addView(holder);

            Log.d("yummy yummy","Added view " + i);
        }*/
    }
}
