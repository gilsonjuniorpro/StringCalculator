package stringcalculator.ca

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StringCalculatorInstrumentedTest {

    private val intent = Intent(
        InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java
    )

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(intent)

    @Test
    fun additionWithOneSeparatorIsCorrect() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText("//@1@2@3"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("The result is: 6")))
    }

    @Test
    fun additionWithTwoSeparatorsIsCorrect() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText("//@1@2@3//@1@2@3"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("The result is: 12")))
    }

    @Test
    fun emptyStringShouldReturnZero() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText(""))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("The result is: 0")))
    }

    @Test
    fun negativeValuesShouldReturnException() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText("//@1@-1@-5@-6@10@50"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_error_message))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun negativeValuesShouldPopulateTheExceptionList() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText("//@1@-1@-5@-6@10@50"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("[-1, -5, -6]")))
    }

    @Test
    fun valuesGreaterThanOneThousandShouldBeIgnored() {
        Espresso.onView(withId(R.id.edit_text_value)).perform(ViewActions.typeText("//@1@@50@1001@6"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("The result is: 57")))
    }

    @Test
    fun breakLinesShouldBeIgnored() {
        Espresso.onView(withId(R.id.edit_text_value))
            .perform(ViewActions.typeText("//@1@@50@1001\n@6"))
        Espresso.onView(withId(R.id.button_calculate)).perform(ViewActions.click())
        //Thread.sleep(3000L)
        Espresso.onView(withId(R.id.text_view_result)).check(matches(withText("The result is: 57")))
    }
}
