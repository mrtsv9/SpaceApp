package com.example.space.presentation.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.space.R
import com.example.space.presentation.MainActivity
import com.example.space.presentation.notifications.NotificationChannelUseCase.channelId

class NotificationsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("KEK", "onReceive")
        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                sendNotification(context)
            }
        }
    }

    private fun sendNotification(context: Context?) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        val notificationBuilder = NotificationCompat.Builder(
            context!!,
            channelId
        )
            .setContentTitle("Title")
            .setContentText("it’s time to explore our Earth!")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_splash)
            .addAction(R.drawable.ic_splash, "Explore", pendingIntent)

        val notificationManager =
            ContextWrapper(context).getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannelUseCase.getChannel()!!
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

}