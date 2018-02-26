package com.support.customdatepicker

import android.text.TextUtils
import android.util.Log
import android.widget.NumberPicker
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 2/26/2018.
 */
class DatePickValidater( private var yearNp: NumberPicker, private var monthNp: NumberPicker, private var dayNp: NumberPicker) : NumberPicker.OnValueChangeListener {

    private var dateFormatter: SimpleDateFormat? = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    override fun onValueChange(p0: NumberPicker?, old: Int, new: Int) {
        val maxdate = getDaysFromYearDate()
        dayNp.maxValue = maxdate
        when (p0!!.id) {
            R.id.yearnp -> {
                Log.d("ssltag", " yearnp : $old , $new , numberpicker value ${yearNp.value}, maxdate  $maxdate")
            }
            R.id.monthnp -> {

                Log.d("ssltag", " monthnp : $old , $new, numberpicker value ${monthNp.value} , maxdate  $maxdate")
            }
            R.id.daynp -> {
                Log.d("ssltag", " daynp : $old , $new, numberpicker value ${dayNp.value}, maxdate  $maxdate")
            }
        }
    }

    private fun getDaysFromYearDate(): Int {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, yearNp.value)
        calendar.set(Calendar.MONTH, monthNp.value - 1)
        return calendar.getActualMaximum(Calendar.DATE)
    }

    private val mcal: Calendar = Calendar.getInstance()

    fun initializeNp() {
        yearNp.minValue = mcal.get(Calendar.YEAR) - 80
        yearNp.maxValue = mcal.get(Calendar.YEAR) - 18
        yearNp.setOnValueChangedListener(this)
        yearNp.wrapSelectorWheel = false
        yearNp.value = mcal.get(Calendar.YEAR) - 18

        monthNp.minValue = 1
        monthNp.maxValue = 12
        monthNp.setOnValueChangedListener(this)
        monthNp.value = 1


        dayNp.minValue = 1
        dayNp.maxValue = 31
        dayNp.setOnValueChangedListener(this)
        dayNp.value = 1
    }

    fun validate(): Boolean {
        return !(TextUtils.isEmpty(yearNp.value.toString()) || TextUtils.isEmpty(monthNp.value.toString()) || TextUtils.isEmpty(dayNp.value.toString()))
    }

    override fun toString(): String {
        val newDate = Calendar.getInstance()
        newDate.set(yearNp.value, monthNp.value-1, dayNp.value)

        return dateFormatter!!.format(newDate.time).toString()
    }


}