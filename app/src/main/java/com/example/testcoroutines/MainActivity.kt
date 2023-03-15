package com.example.testcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(MainActivity.TAG,"Main Activity thread started")
        longRunningWork("SampleCoRoutine",1000)
        Log.i(MainActivity.TAG,"Main Activity thread ended")
    }

    companion object{
        val TAG: String = "CoRoutine"
    }
}

fun longRunningWork(coroutineName: String, delay: Long) {
    Log.i(MainActivity.TAG,"$coroutineName thread started")
    Log.i(MainActivity.TAG,"Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().id} in ${coroutineName}")
    for( i in 0..9){
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            try{
                Thread.sleep(delay)
                Log.i(MainActivity.TAG,"$coroutineName is progress, remaining time is ${10-i}")
            }catch (ex: Exception){
                Log.i(MainActivity.TAG," ${ex?.message}")
            }
        }, 0)
    }
    Log.i(MainActivity.TAG,"$coroutineName thread ended")
}
