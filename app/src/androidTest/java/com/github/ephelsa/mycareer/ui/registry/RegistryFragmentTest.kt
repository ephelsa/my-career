package com.github.ephelsa.mycareer.ui.registry

import androidx.annotation.UiThread
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.ui.NavigationHelper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistryFragmentTest {

    @Test
    @UiThread
    fun navigation() {
        val tests = listOf(
            NavigationHelper(
                name = "login button to login screen",
                idViewToNav = R.id.login_button,
                expectedDirection = R.id.loginFragment
            ),
            NavigationHelper(
                name = "back button to login screen",
                idViewToNav = R.id.back_button,
                expectedDirection = R.id.loginFragment
            )
        )

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        val registryScreen = launchFragmentInContainer<RegistryFragment>()
        registryScreen.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        for (tt in tests) {
            onView(ViewMatchers.withId(tt.idViewToNav)).perform(ViewActions.click())
            assertEquals(tt.name, tt.expectedDirection, navController.currentDestination?.id)
        }

    }
}