package com.abhisht28.eventmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository
    val allEvents: LiveData<List<Event>>

    init {
        val dao = EventDatabase.getDatabase(application).getEventDao()
         repository = EventRepository(dao)
        allEvents = repository.allEvents

    }

    fun deleteEvent (event: Event) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(event)
    }

    fun insertEvent (event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }


}