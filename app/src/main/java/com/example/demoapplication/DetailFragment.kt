package com.example.demoapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demoapplication.adepter.DetailAdepter
import com.example.demoapplication.model.DetailModel
import kotlinx.android.synthetic.main.fragment_ditail.*
import java.text.SimpleDateFormat
import java.util.*


class DetailFragment : Fragment() {
    private val detailList:ArrayList<DetailModel> = arrayListOf()
    private val myFormat = "dd/MM/yyyy"
    private val sdf = SimpleDateFormat(myFormat, Locale.getDefault())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ditail, container, false)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ly_map.setOnClickListener {
            val intent = Intent(this@DetailFragment.context, MapActivity::class.java)
            startActivity(intent)
        }
        imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        detailList.add(
            DetailModel(
                R.drawable.property_img,
                "Bender's Driveway",
                "125 PrinceCharts St",
                "$5.00/hr"
            )
        )
        detailList.add(
            DetailModel(
                R.drawable.property_img,
                "Bender's Driveway",
                "125 PrinceCharts St",
                "$5.00/hr"
            )
        )
        detailList.add(
            DetailModel(
                R.drawable.property_img,
                "Bender's Driveway",
                "125 PrinceCharts St",
                "$5.00/hr"
            )
        )

        recyclerView.adapter = DetailAdepter(detailList)
        emptyStartDate()
    }
    private fun addFromDate(){
        val cal: Calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(requireContext(), { _, year, month, day_of_month ->
            cal[Calendar.YEAR] = year
            cal[Calendar.MONTH] = month
            cal[Calendar.DAY_OF_MONTH] = day_of_month
            txt_fromDate.text= sdf.format(cal.time)
        }, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH])
        cal.add(Calendar.YEAR, 0)
        dialog.datePicker.maxDate = cal.timeInMillis
        dialog.show()
    }
    /**Once start date is selected, do not let user to select End date before the selected start date.*/
    private fun addToDate(){
            val toc = Calendar.getInstance()
            val getFromDate: String = txt_fromDate.text.toString().trim()
            val getFrom = getFromDate.split("/".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val year: Int = getFrom[2].toInt()
            val day: Int = getFrom[0].toInt()
            val sdf1 = SimpleDateFormat(myFormat, Locale.getDefault())
            val dialog = DatePickerDialog(requireContext(), { _, toYear, month, day_of_month ->
                toc[Calendar.YEAR] = toYear
                toc[Calendar.MONTH] = month
                toc[Calendar.DAY_OF_MONTH] = day_of_month
                txt_todate.text = sdf1.format(toc.time)
            },
                toc[Calendar.YEAR],
                toc[Calendar.MONTH],
                toc[Calendar.DAY_OF_MONTH])
            toc.add(Calendar.YEAR, 0)
        /*val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(myFormat)
        val current = formatter.format(time)*/
            val c = Calendar.getInstance()
        c[year,c[Calendar.MONTH]] = day
        /*if (txt_fromDate.text.toString() == current|| txt_todate.text.toString() == current){
            c[year,c[Calendar.MONTH]] = day
        }
        else{
            c[year,c[Calendar.MONTH]] = day + 1
        }*/
            dialog.datePicker.minDate = c.timeInMillis
            dialog.datePicker.maxDate = toc.timeInMillis
            dialog.show()
    }
    /**Once end date is selected, do not let user to select any start date which is after end date.*/
    private fun emptyStartDate() {
        txt_todate.setOnClickListener {
            if (txt_fromDate.text.isEmpty()) {
                val calEmptyFromDate = Calendar.getInstance()
                val dialog = DatePickerDialog(requireContext(), { _, year, month, day_of_month ->
                    calEmptyFromDate[Calendar.YEAR] = year
                    calEmptyFromDate[Calendar.MONTH] = month
                    calEmptyFromDate[Calendar.DAY_OF_MONTH] = day_of_month
                    txt_todate.text = sdf.format(calEmptyFromDate.time)
                }, calEmptyFromDate[Calendar.YEAR], calEmptyFromDate[Calendar.MONTH], calEmptyFromDate[Calendar.DAY_OF_MONTH])
                calEmptyFromDate.add(Calendar.YEAR, 0)
                dialog.datePicker.maxDate = calEmptyFromDate.timeInMillis
                dialog.show()
            }
            else{
             addToDate()
            }
        }
        txt_fromDate.setOnClickListener {

            if(txt_todate.text.isEmpty()){
                addFromDate()
            }
            else{
                val calToEmpty = Calendar.getInstance()
                val getToDate: String = txt_todate.text.toString().trim()
                val getTo = getToDate.split("/".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val year: Int = getTo[2].toInt()
                val day: Int = getTo[0].toInt()
                val sdf1 = SimpleDateFormat(myFormat, Locale.getDefault())
                val dialog = DatePickerDialog(
                    requireContext(), { _, toYear, month, day_of_month ->
                        calToEmpty[Calendar.YEAR] = toYear
                        calToEmpty[Calendar.MONTH] = month
                        calToEmpty[Calendar.DAY_OF_MONTH] = day_of_month
                        txt_fromDate.text = sdf1.format(calToEmpty.time)
                    },
                    calToEmpty[Calendar.YEAR],
                    calToEmpty[Calendar.MONTH],
                    calToEmpty[Calendar.DAY_OF_MONTH]
                )
                calToEmpty.add(Calendar.YEAR, 0)
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat(myFormat)
                //val current = formatter.format(time)
                val c = Calendar.getInstance()
                c[year,c[Calendar.MONTH]] = day
                /*if (txt_fromDate.text.toString() == current || txt_todate.text.toString()==current){
                    c[year,c[Calendar.MONTH]] = day
                }
                else{
                    c[year,c[Calendar.MONTH]] = day +1
                }*/
                //dialog.datePicker.minDate = c.timeInMillis
                dialog.datePicker.maxDate = c.timeInMillis/*calToEmpty.timeInMillis*/
                dialog.show()
            }

        }

    }
}