package com.example.space.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

object NotificationChannelUseCase {

    const val channelId = "notification_channel"
    private const val channelName = "com.example.space"

    fun getChannel(): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
        } else null
    }

}