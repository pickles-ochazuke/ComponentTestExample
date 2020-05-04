package com.example.ochadukebiyori.componenttestexample

// android
import android.app.Activity
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent

// Espresso
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

// JUnit
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

// hamcrest
import org.hamcrest.Matchers.allOf

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    @get:Rule
    val mainIntentsRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun 起動したらHelloWorldが表示されるべき() {
        onView(withId(R.id.helloWorld)).check(matches(withText("Hello World!")))
    }

    @Test
    fun ボタンを押すとhelloWorldのテキストが変わるべき() {
        onView(withId(R.id.changeGreeting)).perform(click())

        onView(withId(R.id.helloWorld)).check(matches(withText("Hi World!")))
    }

    @Test
    fun ボタンを押すとOtherActivityが起動するべき() {
        onView(withId(R.id.startOtherActivity)).perform(click())

        intended(allOf(
            hasComponent(OtherActivity::class.java.name),
            hasExtra("Source", "MainActivity")
        ))
    }

    @Test
    fun OtherActivityから受け取った結果がテキストに反映されるべき() {
        val intent = Intent().apply {
            this.putExtra("Finished", "OtherActivity")
        }

        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)
        val componentName = ComponentName(
                ApplicationProvider.getApplicationContext(),
                OtherActivity::class.java
            )
        intending(hasComponent(componentName)).respondWith(result)

        onView(withId(R.id.startOtherActivity)).perform(click())

        onView(withId(R.id.helloWorld)).check(matches(withText("OtherActivity")))
    }
}
