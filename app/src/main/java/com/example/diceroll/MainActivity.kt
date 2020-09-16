package com.example.diceroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var firstRandomNumer: Int = 1
    var secondRandomNumber: Int = 1
    var firstPlayerBall: Int = 0
    var secondPlayerBall: Int = 0
    val gameLimit: Int = 5
    var gameLive: Int = 1
    var firsPlayerLiveBall = 0
    var secondPlayerLiveBall = 0
    var selectPlayer: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            playGame()

            buttonRoll.setOnClickListener {
                firstRandomNumer = genarateRandomNumber()
                secondRandomNumber = genarateRandomNumber()
                diceRoll(firstRandomNumer, imageFirstDice)
                diceRoll(secondRandomNumber, imageSecondDice)
                if(selectPlayer == 0) {
                    firsPlayerLiveBall = firstRandomNumer + secondRandomNumber
                    selectPlayer++
                } else {
                    secondPlayerLiveBall = firstRandomNumer + secondRandomNumber
                    selectPlayer++
                }

                if (selectPlayer > 1) {
                    gameLive++
                    if (firsPlayerLiveBall > secondPlayerLiveBall) {
                        firstPlayerBall++
                    } else if (firsPlayerLiveBall == secondPlayerLiveBall) {
                        firstPlayerBall++
                        secondPlayerBall++
                    } else {
                        secondPlayerBall++
                    }
                    selectPlayer = 0
                    playGame()
                }
            }

    }

    private fun playGame() {
        if(gameLimit >= gameLive) {
            tvCounLivel.text = gameLive.toString() + "/" + gameLimit.toString()
            tvFirstPlayerBall.text = firstPlayerBall.toString()
            tvSecondPlayerBall.text = secondPlayerBall.toString()
        } else {
            val intent: Intent = Intent(this, FinishActivity::class.java)
            intent.putExtra("firstPlayerBall", firstPlayerBall)
            intent.putExtra("secondPlayerBall", secondPlayerBall)
            startActivity(intent)
        }

    }

    private fun genarateRandomNumber():Int = Random.nextInt(1, 7)

    private fun diceRoll(randomNumber: Int, image: ImageView) {
        imageAnimation(image)
        when (randomNumber) {
            1 -> image.setImageResource(R.drawable.dice_1)
            2 -> image.setImageResource(R.drawable.dice_2)
            3 -> image.setImageResource(R.drawable.dice_3)
            4 -> image.setImageResource(R.drawable.dice_4)
            5 -> image.setImageResource(R.drawable.dice_5)
            6 -> image.setImageResource(R.drawable.dice_6)
            else -> image.setImageResource(R.drawable.dice_1)
        }
    }
    private fun imageAnimation(image: ImageView) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.anim_dice)
        image.startAnimation(animation)
    }
}