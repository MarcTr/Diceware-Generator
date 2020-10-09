package com.marctr.dicewaregenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.marctr.dicewaregenerator.generation.DicewareGenerator
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var loadingLayout: ConstraintLayout
    private lateinit var contentLayout: ConstraintLayout
    private lateinit var generatePassphraseBtn: Button
    private lateinit var passphraseTv: TextView
    private lateinit var yourPassphraseTv: TextView
    private lateinit var numberPicker: NumberPicker
    private lateinit var randomCharSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generator = DicewareGenerator(baseContext)
        loadingLayout = findViewById(R.id.loadingLayout)
        contentLayout = findViewById(R.id.contentLayout)

        CoroutineScope(Dispatchers.Main).launch {
            generator.init()
            loadingLayout.visibility = View.GONE
            contentLayout.visibility = View.VISIBLE
        }

        numberPicker = findViewById(R.id.number_picker)
        numberPicker.minValue = 2
        numberPicker.maxValue = 10
        numberPicker.value = 6 // recommended default

        passphraseTv = findViewById(R.id.passphrase_tv)
        yourPassphraseTv = findViewById(R.id.yourPassphrase_tv)
        randomCharSwitch = findViewById(R.id.randomCharSwitch)

        generatePassphraseBtn = findViewById(R.id.generate_btn)
        generatePassphraseBtn.setOnClickListener {
            val numberOfWords = numberPicker.value
            val insertRandomChar = randomCharSwitch.isChecked
            val passphrase = generator.generatePassphrase(numberOfWords, insertRandomChar)
            passphraseTv.text = passphrase
            yourPassphraseTv.visibility = TextView.VISIBLE
        }
    }
}