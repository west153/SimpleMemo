package com.example.simplememo.memolist

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.simplememo.R
import com.example.simplememo.RepositoryLocator
import com.example.simplememo.domain.Repository
import com.example.simplememo.utils.DataBindingIdlingResource
import com.example.simplememo.utils.monitorFragment
import com.example.simplememo.view.memolist.MemoListFragment
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MemoListFragmentTest {

  private lateinit var repository: Repository
  private val compositeDisposable = CompositeDisposable()
  private val dataBindingIdlingResource = DataBindingIdlingResource()

  @Before
  fun init() {
    repository = RepositoryLocator.provideRepository(ApplicationProvider.getApplicationContext())
  }

  @After
  fun clear() {
    RepositoryLocator.close()
    compositeDisposable.clear()
  }

  @Before
  fun registerIdlingResource() {
    IdlingRegistry.getInstance().register(dataBindingIdlingResource)
  }


  @After
  fun unregisterIdlingResource() {
    IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
  }

  @Test
  fun memoListEmpty() {
    // given - launch fragment
    val fragmentScenario = launchFragmentInContainer<MemoListFragment>()

    dataBindingIdlingResource.monitorFragment(fragmentScenario)

    // when - remove memo all
    repository.removeAll()

    // then - verify
    onView(withId(R.drawable.ic_add_24px)).check(matches(isDisplayed()))
  }

}