package com.abhisht28.eventmanager

import androidx.lifecycle.LiveData
import androidx.room.*


    @Dao
    interface EventDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(event: Event)

        @Delete
        suspend fun delete(event: Event)

        @Query("SELECT * FROM event_table ORDER BY id ASC")
        fun getAllEvents(): LiveData<List<Event>>


    }
