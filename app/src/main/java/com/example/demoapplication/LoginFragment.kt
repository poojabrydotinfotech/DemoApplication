package com.example.demoapplication

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.auto_country
import kotlinx.android.synthetic.main.fragment_login.btn_signup
import kotlinx.android.synthetic.main.fragment_login.edt_Password
import kotlinx.android.synthetic.main.fragment_login.img_pass

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryList  = resources.getStringArray(R.array.country)
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, countryList)
            auto_country.setAdapter(adapter)
        showHidePass()
        pattern()
        btn_signup.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToDetailFragment())
        }
    }

    private fun pattern(){
        val num = 5
        for(i in 1..num)
        {
            for(j in 1..i)
            {
                Log.e("", ": ${print("* ")}" )
            }
            Log.e("", ": ${println()}" )
        }

    }
     private fun showHidePass() {

         img_pass.setOnClickListener {
             if (edt_Password.transformationMethod
                     .equals(PasswordTransformationMethod.getInstance())
             ) {
                 ((img_pass.setImageResource(R.drawable.password_hidden_img)))

                 //Show Password
                 edt_Password.transformationMethod = HideReturnsTransformationMethod.getInstance()
             } else {
                 ((img_pass.setImageResource(R.drawable.password_show_img)))

                 //Hide Password
                 edt_Password.transformationMethod = PasswordTransformationMethod.getInstance()


             }
         }
     }
}