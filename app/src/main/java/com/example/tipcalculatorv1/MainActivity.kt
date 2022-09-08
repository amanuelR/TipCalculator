package com.example.tipcalculatorv1


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorv1.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tipCalc = TipCalculator(0.0f,0.0f)
    //  private lateinit var calculateButton: Button
    lateinit var amountBill: EditText
    lateinit var amountTip: EditText
    lateinit var amountTipView: TextView
    lateinit var amountTotalView: TextView
    var money = NumberFormat.getCurrencyInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculator.setOnClickListener { view: View ->
            calculate()
        }

    }
    fun calculate() {

        val  amountBill = binding.amountBill
        val stringBillAmount = amountBill.text.toString()
        val billAmount = stringBillAmount.toFloat()
        tipCalc.bill = billAmount

        //Get amount of tip percentage

        val billTip = binding.amountTipPercent.text.toString().toFloat()
        tipCalc.tip=.01f * billTip

        //Determine the amount of tip and convert it to string
        val amountTip: String = money.format(tipCalc.tipAmount()).toString()

        //Determine the total and convert it to string
        val amountTotal: String = money.format(tipCalc.totalAmount()).toString()

        //Display the amount of tip and convert it to string
        binding.amountTip.setText(amountTip)
        binding.amountTotal.setText(amountTotal)

    }
}