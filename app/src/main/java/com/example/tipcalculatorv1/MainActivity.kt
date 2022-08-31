package com.example.tipcalculatorv1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var  tipCalc: TipCalculator
    private lateinit var calculateButton: Button
    lateinit var button: Button
    lateinit var amountBill: EditText
    lateinit var amountTip: EditText
    lateinit var amountTipView: TextView
    lateinit var amountTotalView: TextView
    var money = NumberFormat.getCurrencyInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amountBill = findViewById(R.id.amount_bill)
        amountTip = findViewById(R.id.amount_tip_percent)
        amountTipView = findViewById(R.id.amount_tip)
        amountTotalView = findViewById(R.id.amount_total)
        calculateButton = findViewById<Button>(R.id.button_calculator)
        calculateButton.setOnClickListener { view: View ->
            // we need to perform calculation here
            // call the calculate function
              calculate()
        }
    }
    fun calculate() {
        tipCalc= TipCalculator() //default constructor
        val billString: String = amountBill.getText().toString()
        val tipString: String = amountTip.getText().toString()
        try {
            // convert billString and tipString to floats
            val billAmount = billString.toFloat()
            val tipPercent = tipString.toFloat()
            // update the Model (bill,tip)
            tipCalc.bill= billAmount
            tipCalc.tip= tipPercent
            // tipCalc.bill=billAmount
            // ask Model to calculate tip and total amounts
            val tip = tipCalc.tipAmount()
            val total = tipCalc.totalAmount()
            // update the View with formatted tip and total amounts
            amountTipView.text = (money.format(tip.toDouble())).toString()
            amountTotalView.text = (money.format(total.toDouble())).toString()

        } catch (nfe: NumberFormatException) {
            // pop up an alert view here
        }
    }
}