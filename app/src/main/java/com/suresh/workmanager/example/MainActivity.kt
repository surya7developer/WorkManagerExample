package com.suresh.workmanager.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun oneTimeRequest(view: View) {
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
          //  .setInitialDelay(5,TimeUnit.SECONDS) // We can set Initial Delay to start event
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)

    }
    fun periodicRequest(view: View) {

      val periodicRequest = PeriodicWorkRequest.Builder(MyWorkManager::class.java,15,TimeUnit.MINUTES)
          .build()
        WorkManager.getInstance(this).enqueue(periodicRequest)
    }
}