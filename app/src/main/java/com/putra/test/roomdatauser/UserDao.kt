package com.putra.test.roomdatauser

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(dataUser: DataUser)

    @Update
    fun update(dataUser: DataUser)

    @Delete
    fun delete(dataUser: DataUser)

    @Query("SELECT * FROM datauser")
    fun getAll() : List<DataUser>

    @Query("SELECT * FROM datauser WHERE id = :id")
    fun getById(id: Int) : List<DataUser>

    @Query("DELETE FROM datauser")
    fun deleteAll()

    @Query("SELECT * FROM datauser where email= :email and password= :password")
    fun getUser(email: String?, password: String?): DataUser?
}