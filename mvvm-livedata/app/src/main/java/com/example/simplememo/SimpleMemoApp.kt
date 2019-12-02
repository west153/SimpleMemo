package com.example.simplememo

import android.app.Application
import com.example.simplememo.domain.Repository

class SimpleMemoApp : Application() {

  val dataRepository: Repository
    get() = RepositoryLocator.provideRepository(this)

  override fun onCreate() {
    super.onCreate()
  }

}