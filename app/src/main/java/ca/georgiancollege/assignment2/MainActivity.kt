package ca.georgiancollege.assignment2

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.assignment2.databinding.ActivityMainBinding
import kotlin.math.sign

/**
 * This is the main activity of the application: Assignment 1
 * Name = Vansh Kumar Gautam
 * Student ID = 200544016
 * Date = 2nd June, 2024
 * In this assignment I have created the layout and design of the calculator application. And Along with that i have also created the bonus feature of the application
 * by creating the landscape layout for my application.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.ModeChange.setOnClickListener{
            if(binding.ModeChange.isChecked){
                binding.main.setBackgroundResource(R.color.OxfordBlue)
                binding.ControlsTab.setBackgroundResource(R.color.AliceBlue)
                val NumberedButtons = listOf(binding.One, binding.Two, binding.Three, binding.Four, binding.Five, binding.Six, binding.Seven, binding.Eight, binding.Nine, binding.Zero)
                for (button in NumberedButtons){
                    button?.setBackgroundColor(getColor(R.color.DarkAliceBlue))
                }
                binding.CalculationsView.setTextColor(Color.WHITE)
                binding.Result.setTextColor(Color.WHITE)
                binding.ModeChange.splitTrack = true
                binding.ModeChange.thumbTintMode = PorterDuff.Mode.ADD
                binding.ModeChange.trackTintList = getColorStateList(R.color.AliceBlue)
            }else if(!binding.ModeChange.isChecked){
                binding.main.setBackgroundResource(R.color.AliceBlue)
                binding.ControlsTab.setBackgroundResource(R.color.DarkAliceBlue)
                val NumberedButtons = listOf(binding.One, binding.Two, binding.Three, binding.Four, binding.Five, binding.Six, binding.Seven, binding.Eight, binding.Nine, binding.Zero)
                for (button in NumberedButtons){
                    button?.setBackgroundColor(getColor(R.color.AliceBlue))
                }
                binding.ModeChange.trackTintList = getColorStateList(R.color.white)
                binding.ModeChange.thumbTintMode = PorterDuff.Mode.ADD
                binding.CalculationsView.setTextColor(Color.BLACK)
                binding.Result.setTextColor(Color.BLACK)
            }
            val x = 5
            val square = (x*x).sign
            Log.i("Square", square.toString())
        }
    }
}