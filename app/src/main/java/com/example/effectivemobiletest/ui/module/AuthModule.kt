package com.example.effectivemobiletest.ui.module

import android.content.Context
import android.util.Log
import com.example.effectivemobiletest.ui.models.local.AppDatabase
import com.example.effectivemobiletest.ui.models.local.UserDbEntity
import com.example.effectivemobiletest.ui.models.local.interfaces.UserDao
import com.example.effectivemobiletest.ui.repositories.LocalDbRep
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

sealed class AuthState{
    object NotFound: AuthState()
    data class Success(var user: UserDbEntity): AuthState()
}

sealed class RegState{
    data class Success(var user: UserDbEntity): RegState()
    object NotEmail: RegState()
    object NotFull: RegState()
    object AlreadyExist: RegState()
}

class AuthModule(applicationContext: Context) {

    private val db = RoomModule().init(applicationContext)

    fun isUserExsist(firstName: String, email: String): Boolean{
        val user = db.userRep.getUserDataByFirstName(firstName)
        val user2 = db.userRep.getUserDataByEmail(email)

        return !(user == null && user2 == null)
    }

    fun login(
        firstName: String,
        password: String = ""
    ): AuthState
    {
        val user = db.userRep.getUserDataByFirstName(firstName)

        if(user != null){
            return AuthState.Success(user)
        }

        return AuthState.NotFound
    }

     fun registration(
        firstName: String,
        secondName: String,
        email: String
    ): RegState
    {
        if(isUserExsist(firstName, email)) return RegState.AlreadyExist

        if(firstName.isEmpty() || secondName.isEmpty() || email.isEmpty()) return RegState.NotFull

        var count = 0
        email.forEach { if (it == '@') count++ }

        if(count != 1 || !email.split("@")[1].contains('.')) return RegState.NotEmail

        val newUser = UserDbEntity(
            email = email,
            firstName = firstName,
            secondName = secondName,
            password = ""
        )

        db.userRep.insertNewUserData(newUser)

        return RegState.Success(newUser)
    }
}