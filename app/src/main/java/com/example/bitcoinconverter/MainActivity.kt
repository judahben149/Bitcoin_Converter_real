package com.example.bitcoinconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitcoinconverter.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            calcBtc()
        }

    }

    private fun calcBtc () {
        val naira = binding.etAmount.toString().toDoubleOrNull()

        if (naira == null || naira == 0.0){
            dispBtc(0.0)
            return
        }
        var btc = naira * (0.00002/500)

        btc = if (binding.swRoundup.isChecked) {
            round(btc)
        } else {
            btc.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }

        dispBtc(btc)
    }

    private fun dispBtc (btc: Double) {
        binding.etBtc.text = getString(R.string.btc_equiv, btc)
    }
}