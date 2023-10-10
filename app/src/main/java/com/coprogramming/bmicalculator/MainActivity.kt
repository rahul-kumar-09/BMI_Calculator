package com.coprogramming.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText = findViewById<EditText>(R.id.editTextHeight)
        val weightEditText = findViewById<EditText>(R.id.editTextWeight)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)

        calculateButton.setOnClickListener(View.OnClickListener {
            // Get height and weight as text from EditText fields
            val heightStr = heightEditText.text.toString()
            val weightStr = weightEditText.text.toString()

            if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                // Convert height (feet) to meters
                val heightFeet = heightStr.toDouble()
                val heightMeters = heightFeet * 0.3048

                // Convert weight (kg) to double
                val weight = weightStr.toDouble()

                // Calculate BMI based on selected gender
                val genderRadioButton = findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId)
                val gender = genderRadioButton.text.toString()

                val bmi = calculateBMI(heightMeters, weight, gender)

                // Display the result
                resultTextView.text = "Your BMI: $bmi"
            } else {
                // Handle empty input fields
                resultTextView.text = "Please enter height and weight."
            }
        })
    }

    private fun calculateBMI(height: Double, weight: Double, gender: String): Double {
        // BMI formula varies for males and females
        return if (gender.equals("Male", ignoreCase = true)) {
            // Male BMI formula
            weight / (height * height)
        } else {
            // Female BMI formula
            655 + (9.6 * weight) + (1.8 * height) - (4.7 * 30) // Assuming age as 30 for illustration
        }
    }
}
