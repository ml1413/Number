package com.i.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.i.number.model.ModelNumberInfo

@Dao
interface HistoryDao {
    @Insert
    suspend fun add(numberEntity: NumberEntity)

    @Query("select*from history ORDER BY id DESC")
    fun getAllModel(): LiveData<List<NumberEntity>>
}