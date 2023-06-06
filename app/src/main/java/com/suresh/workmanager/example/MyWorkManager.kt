package com.suresh.workmanager.example

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class MyWorkManager(var context: Context,var workerParameters: WorkerParameters) : Worker(context,workerParameters) {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun doWork(): Result {
        Log.d("MyWorkManager","MyWorkManager doWork")

        //If we need to do something in loop with OneTimeWorkRequestBuilder
        // val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
        //     .setInitialDelay(5, TimeUnit.SECONDS)
        //     .build()
        // WorkManager.getInstance(context).enqueue(uploadWorkRequest)

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background))
        } else {

            builder = Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background))
        }
        notificationManager.notify(1234, builder.build())

        return Result.success()
    }
}