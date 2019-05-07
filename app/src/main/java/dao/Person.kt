package dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
----------------------------------------------------------------------------------------------

 *项目: MyApplication9      dao
 *创建: panshengtao  邮箱：1274218999@lecent.com
 *时间: 2019年05月07日 3:53 PM


 *描述:
--------------------------------------------------------------------------------------------*/

@Entity(tableName = "person")
data class Person constructor(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "passwords")var passwords: String)
