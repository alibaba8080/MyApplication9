package dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
----------------------------------------------------------------------------------------------

 *项目: MyApplication9      dao
 *创建: panshengtao  邮箱：1274218999@lecent.com
 *时间: 2019年05月07日 4:05 PM


 *描述:
--------------------------------------------------------------------------------------------*/

@Database(entities = arrayOf(Person::class), version = 1)
abstract class UDatabase : RoomDatabase() {
    abstract fun PersonDao(): PersonDao

    companion object {
        private const val DB_NAME = "user.db"
        @Volatile
        private var instace: UDatabase? = null

        fun getInstances(context: Context): UDatabase? {

            if (instace == null) {
                synchronized(UDatabase::class.java) {
                    instace = create(context)
                }
            }
            return instace
        }

        fun create(context: Context): UDatabase {

            return Room.databaseBuilder(
                    context,
                    UDatabase::class.java,
                    DB_NAME
            ).build()
        }
    }
}