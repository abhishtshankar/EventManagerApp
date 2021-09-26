package com.abhisht28.eventmanager

import androidx.lifecycle.LiveData

class EventRepository (private val eventDao: EventDao) {

    val allEvents: LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun insert (event: Event) {
        eventDao.insert(event)
    }
    suspend fun delete (event: Event){
        eventDao.delete(event)
    }
 }