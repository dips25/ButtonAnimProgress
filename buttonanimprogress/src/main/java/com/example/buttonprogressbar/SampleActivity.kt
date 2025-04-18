package com.example.buttonprogressbar

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var bp:ButtonProgress = findViewById(R.id.button_progress);

        bp.setOnClickListener {

            Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()

            bp.setAnim(Anim.RESUME)

            Handler().postDelayed(object:Runnable{

                override fun run() {

                    bp.setAnim(Anim.STOP)
                }


            },2000)
        }


    }
}