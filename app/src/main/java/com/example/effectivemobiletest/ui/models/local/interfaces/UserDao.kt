package com.example.effectivemobiletest.ui.models.local.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.effectivemobiletest.ui.models.local.UserDbEntity


@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserDbEntity>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getByEmail(email: String): UserDbEntity?

    @Query("SELECT * FROM users WHERE firstName LIKE :firstName LIMIT 1")
    fun getByFirstName(firstName: String): UserDbEntity?

    @Insert
    fun insert(employee: UserDbEntity)

    @Update
    fun update(employee: UserDbEntity)

    @Query("DELETE FROM users WHERE email = :email")
    fun deleteByEmail(email: String)
}