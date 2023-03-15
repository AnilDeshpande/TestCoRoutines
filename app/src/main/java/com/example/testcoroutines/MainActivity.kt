package com.example.testcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(MainActivity.TAG,"Main Activity thread started")
        launch { longRunningWork("SampleCoRoutine",1000) }
        Log.i(MainActivity.TAG,"Main Activity thread ended")
    }

    companion object{
        val TAG: String = "CoRoutine"
    }
}

suspend fun longRunningWork(coroutineName: String, delay: Long) {
    Log.i(MainActivity.TAG,"$coroutineName thread started")
    for(i in 1..9){
        delay(delay);
        Log.i(MainActivity.TAG,"$coroutineName is progress, remaining time is ${10-i}")
    }

    Log.i(MainActivity.TAG,"$coroutineName thread ended")
}
