package br.com.meli

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.meli.presentation.search.SearchFragment
import io.mockk.mockk
import org.hamcrest.Matchers.not
import org.junit.Test

class SearchFragmentTest {

    @Test
    fun test_searchButton_disabled_when_input_empty() {
        launchFragmentInContainer<SearchFragment>(themeResId = R.style.Theme_Meli)

        onView(withId(R.id.btnSearch))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun test_searchButton_enabled_when_input_filled() {
        launchFragmentInContainer<SearchFragment>(themeResId = R.style.Theme_Meli)

        onView(withId(R.id.etSearch))
            .perform(typeText("iPhone"))

        onView(withId(R.id.btnSearch))
            .check(matches(isEnabled()))
    }

    @Test
    fun test_navigation_to_results_on_button_click() {
        val navController = mockk<NavController>(relaxed = true)

        val scenario = launchFragmentInContainer<SearchFragment>(
            fragmentArgs = null,
            themeResId = R.style.Theme_Meli
        )

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.etSearch)).perform(typeText("notebook"), closeSoftKeyboard())
        onView(withId(R.id.btnSearch)).perform(click())

    }
}