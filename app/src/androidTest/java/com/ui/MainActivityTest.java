package com.ui;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import com.example.charafsandroidlabs.R;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testValidPassword() {
        // Enter a valid password
        enterPassword("P@ssw0rd");

        // Click the button
        clickCheckPasswordButton();

        // Check that the messageTextView displays "Password is complex enough."
        checkMessageText("Password is complex enough.");
    }

    @Test
    public void testMissingUpperCaseLetter() {
        // Enter a password missing an uppercase letter
        enterPassword("p@ssw0rd");

        // Click the button
        clickCheckPasswordButton();

        // Check that the messageTextView displays "Your password does not have an upper case letter"
        checkMessageText("Your password does not have an upper case letter");
    }

    @Test
    public void testMissingLowerCaseLetter() {
        // Enter a password missing a lowercase letter
        enterPassword("P@SSW0RD");

        // Click the button
        clickCheckPasswordButton();

        // Check that the messageTextView displays "Your password does not have a lower case letter"
        checkMessageText("Your password does not have a lower case letter");
    }

    @Test
    public void testMissingNumber() {
        // Enter a password missing a number
        enterPassword("P@ssword");

        // Click the button
        clickCheckPasswordButton();

        // Check that the messageTextView displays "Your password does not have a number"
        checkMessageText("Your password does not have a number");
    }

    @Test
    public void testMissingSpecialSymbol() {
        // Enter a password missing a special symbol
        enterPassword("Passw0rd");

        // Click the button
        clickCheckPasswordButton();

        // Check that the messageTextView displays "Your password does not have a special symbol"
        checkMessageText("Your password does not have a special symbol");
    }

    private void enterPassword(String password) {
        Espresso.onView(
                        allOf(withId(R.id.field),
                                isDisplayed()))
                .perform(replaceText(password), closeSoftKeyboard());
    }

    private void clickCheckPasswordButton() {
        Espresso.onView(
                        allOf(withId(R.id.Btn),
                                isDisplayed()))
                .perform(click());
    }

    private void checkMessageText(String expectedText) {
        Espresso.onView(
                        allOf(withId(R.id.errorMessage),
                                isDisplayed()))
                .check(matches(withText(expectedText)));
    }
}
