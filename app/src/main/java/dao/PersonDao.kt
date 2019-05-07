package dao

import android.arch.persistence.room.*

/**
----------------------------------------------------------------------------------------------

 *项目: MyApplication9      dao
 *创建: panshengtao  邮箱：1274218999@lecent.com
 *时间: 2019年05月07日 3:59 PM


 *描述:
--------------------------------------------------------------------------------------------*/

@Dao
interface PersonDao{
    @Query("SELECT * FROM person")
    fun getAll():List<Person>

    @Delete
    fun delete(person: Person)

    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)
}