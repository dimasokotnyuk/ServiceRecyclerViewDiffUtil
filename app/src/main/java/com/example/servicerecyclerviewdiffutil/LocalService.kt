package com.example.servicerecyclerviewdiffutil

import android.app.Service
import android.content.Intent
import android.os.Binder

import android.os.IBinder
import kotlin.random.Random


class LocalService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    fun generateNullToTwo(): Int {
        return Random.nextInt(3)
    }
}