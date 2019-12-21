package com.example.simplememo.memolist

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.simplememo.R
import com.example.simplememo.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.example.simplememo.RepositoryLocator
import com.example.simplememo.TestRepository
import com.example.simplememo.domain.Repository
import com.example.simplememo.view.main.MainActivity
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MemoListFragmentTest {

  private lateinit var repository: Repository
  private val mTestObserver = TestObserver.create<Any>()

  @Before
  fun setup() {
    repository = TestRepository()
    RepositoryLocator.memoRepository = repository
  }

  @After
  fun clear() {
    RepositoryLocator.close()
  }


  @Test
  fun memoListIsEmpty() {
    // given - remove all list
    repository.removeAll()
      .subscribe(mTestObserver)

    //when - start activity
    launchActivity()

    // then - verify list size
    onView(withId(R.id.ivAdd)).check(matches(isDisplayed()))
    onView(withId(R.id.rvMemoList)).check(withItemCount(1))
  }

  private fun launchActivity(): ActivityScenario<MainActivity>? {
    return launch(MainActivity::class.java)
  }


}