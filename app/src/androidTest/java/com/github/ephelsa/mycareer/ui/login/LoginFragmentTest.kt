package com.github.ephelsa.mycareer.ui.login

import android.content.Context
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.ui.container.MainActivity
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginFragmentTest {

    @Test
    fun navigation_to_RegistryScreen() {
        val cxt: Context = ApplicationProvider.getApplicationContext<MainActivity>()
        val navController = TestNavHostController(cxt).apply { setGraph(R.navigation.nav_graph) }
        val loginScreen = launchFragmentInContainer<LoginFragment>()

        loginScreen.onFragment { f ->
            Navigation.setViewNavController(f.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.registry_button)).perform(ViewActions.click())
        assertThat(
            navController.currentDestination?.id,
            Matchers.equalTo(R.id.registryFragment)
        )
    }
}
