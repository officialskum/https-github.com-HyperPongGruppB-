package com.example.hyperponggruppb

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.hyperponggruppb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Handler(Looper.myLooper()!!).postDelayed({

            setTheme(R.style.Theme_HyperPongGruppB)


        }, 2000)

        setContentView(binding.root)
        PlayerManager.playerPoints

        SoundEffectManager.bGMusic(this)


        binding.ivNewGame.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.enter_name_dialog)
            val nameField = dialog.findViewById<EditText>(R.id.et_enter_name_field)
            val startBtn = dialog.findViewById<Button>(R.id.start_btn)
            val cancelBtn = dialog.findViewById<Button>(R.id.cancel_btn)

            startBtn.setOnClickListener {

                if (nameField.text != null && nameField.text.length == 3){
                    PlayerManager.name = nameField.text.toString()
                    val toGameModeOne = Intent(this, GameMode1Activity::class.java)
                    SoundEffectManager.jukebox(this, 1)
                    dialog.dismiss()
                    startActivity(toGameModeOne)
                }
            }

            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()

        }

    }

    override fun onResume() {
        var stack = Thread.getAllStackTraces()
        Log.d(TAG, "onResume: $stack")
        super.onResume()
    }

/*


 */

}