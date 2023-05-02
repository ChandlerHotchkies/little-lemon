package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val Description: String,
    val price: Double,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}