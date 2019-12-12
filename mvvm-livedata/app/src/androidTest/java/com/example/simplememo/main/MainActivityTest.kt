package com.example.simplememo.main

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.simplememo.RepositoryLocator
import com.example.simplememo.domain.Repository
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

  private lateinit var repository: Repository
  private val compositeDisposable = CompositeDisposable()

  @Before
  fun init() {
    repository = RepositoryLocator.provideRepository(getApplicationContext())
    repository.removeAll()
  }

  @After
  fun clear() {
    RepositoryLocator.close()
    compositeDisposable.clear()
  }

  @Test
  fun memoListEmpty() {

  }
}