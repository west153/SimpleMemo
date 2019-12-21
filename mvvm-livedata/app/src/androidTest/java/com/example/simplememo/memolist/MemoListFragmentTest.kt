package com.example.simplememo.memolist

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.simplememo.R
import com.example.simplememo.RepositoryLocator
import com.example.simplememo.TestRepository
import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.utils.withItemCount
import com.example.simplememo.view.main.MainActivity
import com.example.simplememo.view.memolist.MemoListFragment
import com.example.simplememo.view.memolist.MemoListFragmentDirections
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.lang.Thread.sleep


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
  fun displayMemoList() {
    launchActivity()

    onView(withText(R.string.memo_list)).check(matches(isDisplayed()))
  }


  @Test
  fun emptyMemoList() {
    // given - remove all list
    repository.removeAll().subscribe(mTestObserver)

    //when - start activity
    launchActivity()

    // then - verify list size
    onView(withId(R.id.ivAdd)).check(matches(isDisplayed()))
    onView(withId(R.id.rvMemoList)).check(withItemCount(1))
  }

  @Test
  fun addOneMemo() {
    // given - remove all list and  add memo vo
    repository.saveMemo(MemoVo(1, "Memo01", 0))
      .subscribe(mTestObserver)

    //when - start activity
    launchActivity()

    // then - verify memo
    onView(withText("Memo01")).check(matches(isDisplayed()))
  }

  @Test
  fun addMemoUiTest() {
    // given - remove all list
    repository.removeAll().subscribe(mTestObserver)

    launchActivity()

    val content = "Memo Test"

    // when - input memo and save
    onView(withId(R.id.ivAdd)).perform(click())
    onView(withId(R.id.editMemo)).perform(typeText(content), closeSoftKeyboard())
    onView(withId(R.id.action_done)).perform(click())

    // then - verify add memo
    onView(withText(content)).check(matches(isDisplayed()))
  }

  @Test
  fun addAndDeleteMemo() {
    // given - remove all list and  add memo vo
    repository.saveMemo(MemoVo(1, "Memo01", 0))
      .subscribe(mTestObserver)

    val launchActivity = launchActivity()
    var activity: Activity? = null
    launchActivity?.onActivity {
      activity = it
    }

    //when - memo click and delete
    onView(withText("Memo01")).check(matches(isDisplayed())).perform(click())
    onView(withId(R.id.action_delete)).perform(click())

    // then - verify memo 01
    onView(withText("Memo01")).check(doesNotExist())
    onView(withText(R.string.remove_complete))
      .inRoot(withDecorView(not(`is`(activity?.window?.decorView))))
      .check(matches(isDisplayed()))
  }

  @Test
  fun clickAddItem_navigateToAddMemoFragment() {
    // given - on the memo list screen
    val scenario = launchFragmentInContainer<MemoListFragment>(Bundle(), R.style.AppTheme)
    val navController = Mockito.mock(NavController::class.java)
    scenario.onFragment {
      Navigation.setViewNavController(it.view!!, navController)
    }

    // when - click on the "+" item
    onView(withId(R.id.ivAdd)).perform(click())

    sleep(3000)

    // then - verify that we navigate to the add screen
    verify(navController).navigate(
      MemoListFragmentDirections.actionMemoListFragmentToAddMemoFragment(0)
    )
  }


  private fun launchActivity(): ActivityScenario<MainActivity>? {
    return launch(MainActivity::class.java).apply {
      this.onActivity { activity ->
        activity.findViewById<RecyclerView>(R.id.rvMemoList).itemAnimator = null
      }
    }
  }


}