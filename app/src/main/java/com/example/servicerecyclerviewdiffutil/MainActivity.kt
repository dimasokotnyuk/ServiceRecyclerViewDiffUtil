package com.example.servicerecyclerviewdiffutil

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var mCon = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    var mBound = false

    var localService = LocalService()

    val adapter = AdapterRecyclerView()

    val handler = Handler(Looper.getMainLooper())

    val runnable = object : Runnable {
        override fun run() {
            var randomPosition = Random.nextInt(adapter.data.size)
            var randomValue = Random.nextInt(100)
            if (mBound) {
                var randomAction = localService.generateNullToTwo()
                when (randomAction) {
                    0 -> adapter.addElement()
                    1 -> adapter.deleteElement(adapter.data.size - 1)
                    2 -> {
                        adapter.data[randomPosition].value = randomValue
                        adapter.notifyItemChanged(randomPosition)
                    }

                }
                handler.postDelayed(this, 5000)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvContainer.adapter = adapter
        adapter.setData(generate())
        handler.post(runnable)


    }

    override fun onStart() {
        super.onStart()
        var intent = Intent(this, LocalService::class.java)
        mBound = true
        bindService(intent, mCon, Context.BIND_AUTO_CREATE)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mCon)
            mBound = false
        }
    }


    private fun generate(): ArrayList<TestName> {
        var mutableList = arrayListOf<TestName>()
        for (i in 0..5) {
            mutableList.add(TestName(UUID.randomUUID().toString(), i))
        }
        return mutableList
    }
}