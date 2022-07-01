package com.example.quizapp_frontend

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizapp_frontend.ui.fragments.MenuFragment
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuFragmentTest {
    @Test
    fun testMenuFragment(){
        launchFragmentInContainer<MenuFragment>()
        onView(withId(R.id.menuSinglePlayerButton)).check(matches(withText(R.string.single_player)))
    }

}