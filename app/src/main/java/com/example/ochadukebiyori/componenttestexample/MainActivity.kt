package com.example.ochadukebiyori.componenttestexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeGreetingButton = findViewById<Button>(R.id.changeGreeting)
        changeGreetingButton.setOnClickListener {
            findViewById<TextView>(R.id.helloWorld).text = "Hi World!"
        }

        val startOtherActivityButton = findViewById<Button>(R.id.startOtherActivity)
        startOtherActivityButton.setOnClickListener {
            val intent = Intent(this, OtherActivity::class.java).apply {
                this.putExtra("Source", "MainActivity")
            }
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) { return }
        if (requestCode != 1) { return }

        findViewById<TextView>(R.id.helloWorld).text = data?.getStringExtra("Finished")
    }
}
