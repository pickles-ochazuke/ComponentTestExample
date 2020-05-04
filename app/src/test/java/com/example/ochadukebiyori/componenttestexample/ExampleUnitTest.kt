package com.example.ochadukebiyori.componenttestexample

// Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

// JUnit
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {

    @get:Rule
    val mainScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun 起動したらHelloWorldが表示されるべき() {
        onView(withId(R.id.helloWorld)).check(matches(withText("Hello World!")))
    }
}
