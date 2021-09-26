package com.abhisht28.eventmanager

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ViewData : AppCompatActivity(), IEventAdapter {

    lateinit var viewModel: EventViewModel
//    private val CHANNEL_ID = "channel_id_example_01"
//    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
//
//        lateinit var context: Context
//        lateinit var alarmManager: AlarmManager
        //sendNotification()

//        context = this
//        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
//        val date = findViewById<TextView>(R.id.date_Details)
//        val date1 = sdf.format(date)
//        val currentDate = sdf.format(Date())
//        if (currentDate.equals(date1)) {
//            val pendingIntent =
//                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//            Log.d("MainActivity", " Create : " + Date().toString())
//            alarmManager.setExact(
//                AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis() + 1000,
//                pendingIntent
//            )
////            createNotificationChannel()
////            sendNotification()
//        }

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = EventRVAdapter(this, this)
        recycler.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EventViewModel::class.java)
        viewModel.allEvents.observe(this, { list ->
            list?.let {
                adapter.updateList(it)
            }

        })
    }
//    class Receiver : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            Log.d("MainActivity", " Receiver : " + Date().toString())
//
//        }
//    }

    override fun onItemClicked(event: Event) {
        viewModel.deleteEvent(event)
        Toast.makeText(this, "${event.text} Deleted", Toast.LENGTH_LONG).show()
    }

//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Notification Title"
//            val descriptionText = " Notification Description"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//
//            val notificationManager: NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//
//
//    }
//
//    private fun sendNotification() {
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("Example Title - Reminder App")
//            .setContentText("Text_Msg")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)) {
//            notify(notificationId, builder.build())
//        }
//    }

}