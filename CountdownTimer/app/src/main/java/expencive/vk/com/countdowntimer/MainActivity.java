package expencive.vk.com.countdowntimer;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textViewHours, textViewMinutes, textViewSeconds;
    private TextView countdownText;
    private LinearLayout linearLayout;
    private int focusOnTimeTextView = 1;
    private String bufferSecondInt = "0";
    boolean isTextViewCheckedOnce;
    private FloatingActionButton buttonReset, buttonStartPause;

    private CountDownTimer countDownTimer;
    private long timeLeftInMiliseconds;


    private boolean timerStartedOnce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_buttons);

        linearLayout = findViewById(R.id.linearLayout);
        countdownText = findViewById(R.id.text_view_show_countdown);

        textViewHours = findViewById(R.id.text_view_hours);
        textViewMinutes = findViewById(R.id.text_view_minutes);
        textViewSeconds = findViewById(R.id.text_view_seconds);
        buttonReset = findViewById(R.id.button_reset);
        buttonStartPause = findViewById(R.id.button_start_pause);
        buttonStartPause.setTag("Empty");
        isTextViewCheckedOnce = false;

        textViewSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnTimeTextView = 1;
                showFocusOnTextView();


            }
        });

        textViewMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnTimeTextView = 2;
                showFocusOnTextView();


            }
        });

        textViewHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnTimeTextView = 3;
                showFocusOnTextView();


            }
        });

        showFocusOnTextView();


    }

    private void showFocusOnTextView() {
        isTextViewCheckedOnce = false;
        switch (focusOnTimeTextView) {
            case 1:
                textViewSeconds.setTextColor(ContextCompat.getColor
                        (this, R.color.colorAccent));
                textViewMinutes.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                textViewHours.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                break;
            case 2:
                textViewSeconds.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                textViewMinutes.setTextColor(ContextCompat.getColor
                        (this, R.color.colorAccent));
                textViewHours.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                break;

            case 3:
                textViewSeconds.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                textViewMinutes.setTextColor(ContextCompat.getColor
                        (this, R.color.colorPrimary));
                textViewHours.setTextColor(ContextCompat.getColor
                        (this, R.color.colorAccent));
                break;
        }
    }


    public void onClickButton(View view) {
        Button button = (Button) view;

        String getTextFromButton = "";
        getTextFromButton = button.getText().toString();

        switch (focusOnTimeTextView) {
            case 1:
                if (!isTextViewCheckedOnce) {
                    textViewSeconds.setText("0" + getTextFromButton);
                    bufferSecondInt = getTextFromButton;
                    isTextViewCheckedOnce = true;
                } else {
                    textViewSeconds.setText(bufferSecondInt + getTextFromButton);
                    bufferSecondInt = "0";
                    isTextViewCheckedOnce = false;

                }
                break;
            case 2:
                if (!isTextViewCheckedOnce) {
                    textViewMinutes.setText("0" + getTextFromButton);
                    bufferSecondInt = getTextFromButton;
                    isTextViewCheckedOnce = true;
                } else {
                    textViewMinutes.setText(bufferSecondInt + getTextFromButton);
                    bufferSecondInt = "0";
                    isTextViewCheckedOnce = false;

                }

                break;
            case 3:
                if (!isTextViewCheckedOnce) {
                    textViewHours.setText("0" + getTextFromButton);
                    bufferSecondInt = getTextFromButton;
                    isTextViewCheckedOnce = true;
                } else {
                    textViewHours.setText(bufferSecondInt + getTextFromButton);
                    bufferSecondInt = "0";
                    isTextViewCheckedOnce = false;

                }
                break;
        }
    }

    public void onClick_start_stop(View view) {
        if (textViewHours.getText().equals("00") && textViewMinutes.getText().equals("00")
                && textViewSeconds.getText().equals("00") & !timerStartedOnce) {
            Toast.makeText(this, "Установите время", Toast.LENGTH_SHORT).show();
        } else {
            switch (buttonStartPause.getTag().toString()) {
                case "Empty":
                    buttonStartPause.setTag("Started");
                    startTimer();
                    break;
                case "Started":
                    buttonStartPause.setTag("Paused");
                    stopTimer();
                    buttonReset.setVisibility(View.VISIBLE);
                    break;
                case "Paused":
                    buttonStartPause.setTag("Started");
                    buttonReset.setVisibility(View.GONE);
                    startTimer();
                    break;
            }
            Toast.makeText(this, buttonStartPause.getTag().toString(), Toast.LENGTH_SHORT).show();


        }

    }

    public void onClickReset(View view) {
        toStartState();


    }

    private void toStartState() {
        countDownTimer.cancel();
        textViewHours.setText("00");
        textViewMinutes.setText("00");
        textViewSeconds.setText("00");
        timerStartedOnce = false;
        buttonStartPause.setTag("Empty");
        countdownText.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        isTextViewCheckedOnce = false;
        buttonReset.setVisibility(View.GONE);
        focusOnTimeTextView = 1;
        showFocusOnTextView();
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();

    }

    private void startTimer() {
        if (!timerStartedOnce) {
            timeLeftInMiliseconds = (Integer.parseInt(textViewSeconds.getText().toString())) * 1000
                    + (Integer.parseInt(textViewMinutes.getText().toString())) * 60000
                    + (Integer.parseInt(textViewHours.getText().toString())) * 3600000;
        }


        countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMiliseconds = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {
                buttonReset.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Время истекло", Toast.LENGTH_SHORT).show();


            }
        }.start();


        timerStartedOnce = true;
        countdownText.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);





    }

    private void updateTimer() {
        int hours = (int) timeLeftInMiliseconds / 3600000;
        int minutes = (int) timeLeftInMiliseconds % 3600000 / 60000;
        int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + hours;
        timeLeftText = timeLeftText + ":";
        if (minutes < 10) timeLeftText += "0";
        timeLeftText += minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
        countdownText.setText(timeLeftText);


    }

    public void stopTimer() {
        countDownTimer.cancel();

    }


}
