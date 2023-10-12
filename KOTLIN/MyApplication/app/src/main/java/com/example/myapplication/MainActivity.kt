package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollbutton : Button = findViewById(R.id.button)

        rollbutton.setOnClickListener {

        rolldice()

            val toast = Toast.makeText(this, "Dice Rolled" , Toast.LENGTH_SHORT)
            toast.show()


        }


    }

    private fun rolldice() {

   val dice =  Dice(6)

        val diceroll = dice.roll()

         val resulttextview : TextView = findViewById(R.id.text)

        resulttextview.text = diceroll.toString()


        val diceimage : ImageView = findViewById(R.id.imageView)

        when(diceroll){

            1-> diceimage.setImageResource(R.drawable.dice_1)
            2-> diceimage.setImageResource(R.drawable.dice_2)
            3-> diceimage.setImageResource(R.drawable.dice_3)
            4-> diceimage.setImageResource(R.drawable.dice_4)
            5-> diceimage.setImageResource(R.drawable.dice_5)
            6-> diceimage.setImageResource(R.drawable.dice_6)
        }


    }
}
class Dice (private val numSides :Int){

    public fun roll():Int {
        val randomnum = (1..numSides).random()

        return randomnum
    }

}