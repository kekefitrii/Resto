@file:Suppress("DEPRECATION")

package com.ike.resto.ui.base

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import com.ike.resto.R
import com.ike.resto.ui.foodList.FoodsListActivity

class NotificationService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        showNotification(
            title = getString(R.string.notification_title),
            message = getString(R.string.notification_message)
        )
    }

    private fun showNotification(title: String, message: String) {
        val channelId = getString(R.string.channel_id)
        val notificationId = 1

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = getString(R.string.channel_description)
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this, FoodsListActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    companion object {
        const val JOB_ID = 123
        const val JOB_INTERVAL_MILLIS = 5 * 60 * 1000L // 5 menit

        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, NotificationService::class.java, JOB_ID, work)
        }
    }
}
