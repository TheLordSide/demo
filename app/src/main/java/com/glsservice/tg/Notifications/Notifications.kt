package com.glsservice.tg.Notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.glsservice.tg.R

fun showNotification(context: Context, titre: String, content: String) {
    val idNotification = 1
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "mon_canal"
        val channelNom = "GLS notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelNom, importance)
        notificationManager.createNotificationChannel(channel)
    }

    val notificationBuilder = NotificationCompat.Builder(context, "mon_canal")
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(titre)
        .setContentText(content)
        .setAutoCancel(true)

    // Afficher la notification
    notificationManager.notify(idNotification, notificationBuilder.build())
}
