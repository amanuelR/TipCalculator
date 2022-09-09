package com.example.tipcalculatorv1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorv1.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tipCalc= TipCalculator(0.0f,0.0f)
    var money = NumberFormat.getCurrencyInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.amountBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { calculate() }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {}
        })

        // Create an instance of TextWatcher for tip percentage input
        binding.amountTipPercent.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { calculate() }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {}
        })

    }
    fun calculate() {
        var billAmount: Float =0.0f
        var billTip: Float = 0.0f

        // get the input for bill from layout
        val  amountBill1 = binding.amountBill
        val stringBillAmount = amountBill1.text.toString()

        //validate bill amount input
        billAmount = if (stringBillAmount.isEmpty()) 0.0f
        else stringBillAmount.toFloat()
        tipCalc.bill=billAmount

        // get the input for tip from layout
        val amountTip1 = binding.amountTipPercent
        val amountTipInput = amountTip1.text.toString()

        // validate bill tip input
        billTip = if (amountTipInput.isEmpty()) 0.0f
        else amountTipInput.toFloat()
        tipCalc.tip = billTip * 0.01f

        // covert the amounts to money format
        val amountTip: String = money.format(tipCalc.tipAmount()).toString()
        val amountTotal: String = money.format(tipCalc.totalAmount()).toString()

        // display the results in the layout text view for tip and total amount
        binding.amountTotal.text = amountTotal
        binding.amountTip.text = amountTip

    }
}