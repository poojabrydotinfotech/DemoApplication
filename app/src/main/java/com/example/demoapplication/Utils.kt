package com.example.demoapplication

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showToast(text: String,context: Context) {
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}

fun snackBar(view: View,text: String){
    Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show()
}