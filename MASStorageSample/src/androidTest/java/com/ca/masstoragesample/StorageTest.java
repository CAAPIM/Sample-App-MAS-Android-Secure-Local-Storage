package com.ca.masstoragesample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class StorageTest {

    @Before
    public void before() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource);
    }


    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void saveTest() throws InterruptedException {
        onView(withId(R.id.title))
                .perform(typeText("Test tile"), closeSoftKeyboard());

        onView(withId(R.id.content))
                .perform(typeText("Test content"), closeSoftKeyboard());
        onView(withId(R.id.save)).perform(click());

        Thread.sleep(5000);
        if (MainActivity.LOGIN_STATUS) {
            Thread.sleep(5000);
            onView(withId(R.id.activity_mas_login_edit_text_username))
                    .perform(typeText("admin"), closeSoftKeyboard());
            onView(withId(R.id.activity_mas_login_edit_text_password))
                    .perform(typeText("7layer"), closeSoftKeyboard());
            onView(withId(R.id.activity_mas_login_button_login)).perform(click());
            Thread.sleep(15000);
            onView(withId(R.id.content))
                    .check(matches(withText("")));
        } else {

            onView(withId(R.id.content))
                    .check(matches(withText("")));
        }

    }


    @Test
    public void open() {
        onView(withId(R.id.title))
                .perform(typeText("Test tile"), closeSoftKeyboard());
        onView(withId(R.id.open)).perform(click());

        onView(withId(R.id.content))
                .check(matches(withText("Test content")));

    }


}
