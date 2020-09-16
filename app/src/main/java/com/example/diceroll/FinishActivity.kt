package com.example.diceroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        val firstPlayerBall: Int = intent.getIntExtra("firstPlayerBall", 0)
        val secondPlayerBall: Int = intent.getIntExtra("secondPlayerBall", 0)
        if (firstPlayerBall > secondPlayerBall) {
            tvResult.text = "1-player"
        } else if (firstPlayerBall == secondPlayerBall) {
            tvResult.text = "Ten"
        } else {
            tvResult.text = "2-player"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        var intent: Intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}