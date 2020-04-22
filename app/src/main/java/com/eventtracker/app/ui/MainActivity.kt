package com.eventtracker.app.ui

import javax.inject.Inject
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

import com.eventtracker.app.App
import com.eventtracker.app.R
import com.eventtracker.app.ui.host.HostActivity
import com.eventtracker.app.ui.signin.SignInActivity

class MainActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.main_activity)
        verifyUserIsLoggedIn()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_hosts_feed,
            R.id.navigation_my_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        displayNotifications()
    }

    private fun verifyUserIsLoggedIn() {
        val uid = firebaseAuth.uid
        if (uid == null) {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun displayNotifications() {
        val hostIntent = Navigation.findNavController(this, R.id.nav_host_fragment)
            .createDeepLink()
            .setDestination(R.id.navigation_hosts_feed)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, App.CHANNEL_ID)
            .setContentIntent(hostIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("New event from a host you follow")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, builder.build())
    }
}