package com.abhisht28.eventmanager

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity(), IEventAdapter, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    lateinit var option: Spinner
    lateinit var input_Event: TextView
    lateinit var viewModel: EventViewModel
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
  //  private val REQUEST_CODE = 100

//    lateinit var dateInput : TextView
//    lateinit var dateSaver: Button


    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val currentDate = sdf.format(Date())

    input_Event = findViewById(R.id.input_eventDetails)
    option = findViewById(R.id.spinner)
    val adapter1 = ArrayAdapter.createFromResource(
        this,
        R.array.events,
        android.R.layout.simple_spinner_item
    )
    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    option.adapter = adapter1
    option.onItemSelectedListener = this
    // option.setSelection("")

    //date

//        dateInput = findViewById(R.id.dateText)
//        dateSaver = findViewById(R.id.dateButton)
//

//    override fun onNothingSelected(parent: AdapterView<*>?) {
//        input_Event.text = "Please select an option"
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val text: String = parent?.getItemAtPosition(position).toString()
//        input_Event.text = text
//    }
//        val options = arrayOf("BIRTHDAY", "MEETING", "WEBINAR", "ANNIVERSARY", "MOVIE")
//        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,options )
//        option = findViewById(R.id.spinner)
//        input_Event = findViewById(R.id.input_eventDetails)
//        option.onItemClickListener = object : AdapterView.OnItemSelectedListener,
//            AdapterView.OnItemClickListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                input_Event.text = options.get(position)
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                input_Event.text = "Please select an option"
//            }
//
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//
//            }
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

    pickDate()




//        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
//        val date =sdf.format(pickDate())
//        val currentDate = sdf.format(Date())
//        if (currentDate.equals(date)) {
//            sendNotification()
//        }


    }
     private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
      //  pickDate()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
      }

     private fun pickDate() {

         val dateInput = findViewById<Button>(R.id.dateButton)
         dateInput.setOnClickListener {
             getDateTimeCalendar()
             DatePickerDialog(this, this, year, month, day).show()
             //  Toast.makeText(this, "date picked", Toast.LENGTH_LONG).show()
         }
     }

//    private fun getDateTimeCalendar() {
//        TODO("Not yet implemented")
//    }

    override fun onItemClicked(event:Event) {
        viewModel.deleteEvent(event)
        Toast.makeText(this, "${event.text} Deleted", Toast.LENGTH_LONG).show()
    }

    fun submitData(view: android.view.View) {
        val input_Data = findViewById<TextView>(R.id.input_name)
        val name_Text = input_Data.text.toString()
        var input_Event = findViewById<TextView>(R.id.input_eventDetails)
        val event_Text = input_Event.text.toString()
        val input_Date = findViewById<TextView>(R.id.dateText)
        val date_Text = input_Date.text.toString()


        if (name_Text.isNotEmpty()) {
            viewModel.insertEvent(Event(text = name_Text, textDetails = event_Text, dateDetails = date_Text))
            // viewModel.insertEvent(Event(text = name_Text))
            Toast.makeText(this, "$name_Text Inserted", Toast.LENGTH_LONG).show()
            input_Data.setText("")
            input_Event.setText("")
            input_Date.setText("")

            input_Event = findViewById(R.id.input_eventDetails)
            option = findViewById(R.id.spinner)
            val adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.events,
                android.R.layout.simple_spinner_item
            )
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            option.adapter = adapter1
            option.onItemSelectedListener = this



        }
//        val input_Event = findViewById<TextView>(R.id.input_eventDetails)
//        val event_Text = input_Event.text.toString()
//        if (event_Text.isNotEmpty()){
//            viewModel.insertEvent(Event(event_Text))
//            Toast.makeText(this, "$event_Text Inserted", Toast.LENGTH_LONG).show()
//
//        }
    }

    fun viewData(view: android.view.View) {
        Toast.makeText(this, "VIEW Button is clicked!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, ViewData::class.java)
        startActivity(intent)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        input_Event.text = text
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        input_Event.text = "Please select an option"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            savedDay= dayOfMonth
            savedMonth = month+1
            savedYear = year
        getDateTimeCalendar()

        val dateView = findViewById<TextView>(R.id.dateText)
        dateView.text= "$savedDay-$savedMonth-$savedYear"
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = " Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


    private fun sendNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Example Title - Reminder App")
            .setContentText("REMINDER")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

}
