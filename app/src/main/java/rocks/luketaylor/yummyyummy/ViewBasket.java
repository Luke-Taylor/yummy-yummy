package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class ViewBasket extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basket);
        reloadBasket();
    }

    public void reloadBasket(){
        Basket b = Basket.getInstance();
        LinearLayout placeholders = findViewById(R.id.basketContainer);
        placeholders.removeAllViews();

        if(b.Items.size() == 0){
            placeholders.addView(LayoutInflater.from(this).inflate(R.layout.empty_basket, null));
            findViewById(R.id.chkDeliveryPage).setVisibility(View.INVISIBLE);
        } else {

            for(int i = 0; i < b.Items.size(); i++){
                View v = LayoutInflater.from(this).inflate(R.layout.foodplaceholder, null);
                Button butt = v.findViewById(R.id.placeholderButton);
                butt.setText(R.string.removeFood);
                butt.setTag(b.Items.get(i));
                butt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Basket.getInstance().RemoveItem(view.getTag().toString());
                        Toast.makeText(getApplicationContext(),"Removed from basket", Toast.LENGTH_SHORT).show();
                        reloadBasket();
                    }
                });
                placeholders.addView(v);
                Log.d("yummy yummy","Added view " + i);
            }

            findViewById(R.id.chkDeliveryPage).setVisibility(View.VISIBLE);
        }
    }

    public void selectFood(View v){
        Intent intent = new Intent(getApplicationContext(), FoodSelection.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        finish();
    }

    public void setDelivery(View v){
        Basket b = Basket.getInstance();
        b.Cost = (new Random().nextDouble()) * b.Items.size();
        Intent intent = new Intent(getApplicationContext(), Delivery.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        finish();
    }
}
