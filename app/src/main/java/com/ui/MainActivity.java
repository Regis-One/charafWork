package com.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.charafsandroidlabs.R;
import com.example.charafsandroidlabs.databinding.ActivityMainBinding;

/**
 * The main activity of the Android application.
 */
public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.field);
        messageTextView = findViewById(R.id.errorMessage);
        btn = findViewById(R.id.Btn);

        btn.setOnClickListener(click -> {
            String password = et.getText().toString();
            checkPasswordComplexity(password);
        });
    }

    /**
     * Checks if the provided password meets complexity requirements.
     *
     * @param pw The password to check.
     */
    private void checkPasswordComplexity(String pw) {
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        // Define the set of special symbols
        String specialSymbols = "#$%^&*!@?";

        // Iterate over each character in the password
        for (char c : pw.toCharArray()) {
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c, specialSymbols)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            showMessage("Your password does not have an upper case letter");
        } else if (!foundLowerCase) {
            showMessage("Your password does not have a lower case letter");
        } else if (!foundNumber) {
            showMessage("Your password does not have a number");
        } else if (!foundSpecial) {
            showMessage("Your password does not have a special symbol");
        } else {
            showMessage("Password is complex enough.");
        }
    }

    /**
     * Function to check if a character is a special symbol.
     *
     * @param c             The character to check.
     * @param specialSymbols The set of special symbols to compare against.
     * @return True if the character is a special symbol, false otherwise.
     */
    private boolean isSpecialCharacter(char c, String specialSymbols) {
        return specialSymbols.contains(String.valueOf(c));
    }

    /**
     * Display a message in the center of the screen using the messageTextView.
     *
     * @param message The message to display.
     */
    private void showMessage(String message) {
        messageTextView.setText(message);
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setTextColor(getResources().getColor(android.R.color.holo_red_light)); // Set text color to red
        messageTextView.setGravity(Gravity.CENTER); // Center-align the text
    }
}
