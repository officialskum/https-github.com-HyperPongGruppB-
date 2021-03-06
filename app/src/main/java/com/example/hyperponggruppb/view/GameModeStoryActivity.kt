package com.example.hyperponggruppb.view

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.hyperponggruppb.R
import com.example.hyperponggruppb.controller.PlayerManager
import com.example.hyperponggruppb.controller.PsyduckEngine
import com.example.hyperponggruppb.controller.SoundEffectManager
import com.example.hyperponggruppb.databinding.ActivityGameModeStoryBinding
import com.example.hyperponggruppb.view.fragment.StoryLevelFragment
import com.example.hyperponggruppb.view.fragment.PointFragmentStoryMode

class GameModeStoryActivity : AppCompatActivity() {


    private lateinit var binding: ActivityGameModeStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameModeStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragments()



    }

    private fun setFragments() {

        supportFragmentManager.commit {
            add(R.id.score_fragment_container_story, PointFragmentStoryMode())
            add(R.id.story_view_container, StoryLevelFragment())
        }
    }


    fun updateText() {

        runOnUiThread(Runnable {
            try {

                supportFragmentManager.commit {
                    replace(R.id.score_fragment_container_story, PointFragmentStoryMode())
                }

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "updateText: caught")
            }

        })
    }


    /**
     * checks if the user has selected a power-Up before starting the level and sets the right image in the UI
     */
    fun checkSelectedPowerup() {
        val activatedPowerup = (findViewById<ImageView>(R.id.iv_current_powerup_activated))

        if (PlayerManager.selectedPowerUp >= 0) {

            if (PlayerManager.selectedPowerUp == 0) { //multiball powerup
                activatedPowerup.setImageResource(R.drawable.multiball_button_selected)
            } else if (PlayerManager.selectedPowerUp == 1) {
                activatedPowerup.setImageResource(R.drawable.gun_button_selected)
            } else if (PlayerManager.selectedPowerUp == 2) {
                activatedPowerup.setImageResource(R.drawable.shield_button_selected)
            }
        }
    }

    /**
     * makes the button inside the level to activate the powerup clickable
     */
    fun activatePowerup() {

        runOnUiThread(Runnable {
            try {
                val activatedPowerup = (findViewById<ImageView>(R.id.iv_current_powerup_activated))
                activatedPowerup.setOnClickListener {
                    if (PlayerManager.selectedPowerUp == 0) { //multiball powerup
                        activatedPowerup.setImageResource(R.drawable.faded_multiball_button)
                    } else if (PlayerManager.selectedPowerUp == 1) {
                        activatedPowerup.setImageResource(R.drawable.faded_gun_button)
                    } else if (PlayerManager.selectedPowerUp == 2) {
                        activatedPowerup.setImageResource(R.drawable.faded_shield_button)
                    }
                    if (PlayerManager.selectedPowerUp >= 0) {
                        PlayerManager.activatePowerUp = true
                        PlayerManager.powerUpInventory[PlayerManager.selectedPowerUp] - 1
                    }
                }
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "updateText: caught")
            }
        })
    }

    fun updateComboCounter(){

        runOnUiThread(Runnable {
            try {

                val comboMeter = findViewById<TextView>(R.id.tv_combo_meter_story)
                val comboTextGif = findViewById<ImageView>(R.id.gif_combo_text_story)

                if (PlayerManager.comboPoints > 0){
                    val comboString = PlayerManager.comboPoints.toString() + "X"
                    comboMeter.text = comboString

                    if(PlayerManager.textIsOn){

                        comboTextGif.alpha = 1f

                    }else{

                        comboTextGif.alpha = 0f
                    }
                }else{
                    comboMeter.text = ""
                }
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "updateCombo: caught")
            }
        })
    }

    override fun onBackPressed() {
        PsyduckEngine.gameStart = false
        super.onBackPressed()
    }
}