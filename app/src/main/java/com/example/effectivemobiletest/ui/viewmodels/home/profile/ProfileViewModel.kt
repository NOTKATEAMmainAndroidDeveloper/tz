package com.example.effectivemobiletest.ui.viewmodels.home.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.effectivemobiletest.ui.activities.LogInActivity

class ProfileViewModel(): ViewModel(){
    var ui = ProfileView(this)

    lateinit var context: Context

    fun init(context: Context){
        this@ProfileViewModel.context = context
    }

    fun logOut(){
        context.startActivity(Intent(context, LogInActivity::class.java))
    }
}