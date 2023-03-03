package com.example.effectivemobiletest.ui.repositories

import androidx.lifecycle.LiveData
import com.example.effectivemobiletest.ui.models.local.UserDbEntity
import com.example.effectivemobiletest.ui.models.local.interfaces.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDbRep(private val userDao: UserDao) {

    fun insertNewUserData(userDbEntity: UserDbEntity) {
        userDao.insert(userDbEntity)
    }

    fun getUserData(): List<UserDbEntity> {
        return userDao.getAll()
    }

    fun getUserDataByEmail(email: String): UserDbEntity? {
        return userDao.getByEmail(email)
    }

    fun getUserDataByFirstName(firstName: String): UserDbEntity? {
        return userDao.getByFirstName(firstName)
    }

    suspend fun removeUserDataByEmail(email: String) {
        withContext(Dispatchers.IO) {
            userDao.deleteByEmail(email)
        }
    }
}