package com.support.customdatepicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.textView)
        findViewById<Button>(R.id.btn).setOnClickListener {
            val pdialogue = BottomSheetDialog(this)
            val v = layoutInflater.inflate(R.layout.dialogue_datepicker, null)
            val dateval = DatePickValidater(v.findViewById(R.id.yearnp), v.findViewById(R.id.monthnp), v.findViewById(R.id.daynp))
            dateval.initializeNp()
            v.findViewById<Button>(R.id.cancel).setOnClickListener {
                pdialogue.dismiss()
            }
            v.findViewById<Button>(R.id.submit).setOnClickListener {
                if (dateval.validate()) {
                    Log.d("ssltag", dateval.toString())
                    tv.text = dateval.toString()
                    pdialogue.dismiss()
                } else {
                    Log.d("ssltag", "not valid date")
                }
            }
            pdialogue.setContentView(v)
            pdialogue.show()
        }
    }
}
