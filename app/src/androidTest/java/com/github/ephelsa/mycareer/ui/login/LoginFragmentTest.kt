package com.github.ephelsa.mycareer.ui.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.ephelsa.mycareer.R
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Test
    fun navigation_to_registry_screen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        val loginScreen = launchFragmentInContainer<LoginFragment>()

        loginScreen.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.registry_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, Matchers.equalTo(R.id.registry_fragment))
    }
}