package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Payment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void changeLocation(View v){
        Intent intent = new Intent(getApplicationContext(), Delivery.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        finish();
    }

    public void showConfirmation(View v){
        Intent intent = new Intent(getApplicationContext(), Payment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        finish();
    }
}
