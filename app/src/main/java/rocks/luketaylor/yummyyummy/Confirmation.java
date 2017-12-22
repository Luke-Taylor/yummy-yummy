package rocks.luketaylor.yummyyummy;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Confirmation extends Activity {

    TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        mTextField = findViewById(R.id.confirmationCountdown);

        new CountDownTimer(1000 * 60 * 5, 1000){
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / 1000 > 60){
                    int minutes = (int)Math.floor((millisUntilFinished / 1000) / 60);
                    int seconds = (int)(millisUntilFinished / 1000) % 60;
                    mTextField.setText("Time remaining: " + minutes + " minutes, " + seconds + " seconds");
                } else {
                    mTextField.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
                }
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();
    }
}
