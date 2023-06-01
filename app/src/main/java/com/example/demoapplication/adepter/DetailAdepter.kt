package com.example.demoapplication.adepter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.target.Target
import com.example.demoapplication.R
import com.example.demoapplication.model.DetailModel
import kotlinx.android.synthetic.main.row_parking_detail.view.price


class DetailAdepter (var detailList:ArrayList<DetailModel>,val onclick:(DetailModel)->Unit):RecyclerView.Adapter<DetailAdepter.ViewHolder> (){
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img:ImageView = itemView.findViewById(R.id.img)
        val title:TextView = itemView.findViewById(R.id.txt_title)
        val dis:TextView = itemView.findViewById(R.id.txt_dis)
        val btn:Button = itemView.findViewById(R.id.price)
        fun onBind(model: DetailModel) {
            Glide.with(itemView)
                .load(model.img)
                .placeholder(R.drawable.property_img)
                .transform(GranularRoundedCorners(35f,35f,35f,35f,))
                .into(img)
            title.text = model.address
            dis.text = model.addressDetail
            btn.text = model.price
        }


    }
   /* fun ImageView.loadImageWithCustomCorners(@DrawableRes resId:Int ,radius:Int)=
        Glide.with(this)
            .load(resId)
            .transform(GranularRoundedCorners(75f, 75f, 75f, 75f))
            .into(this)*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_parking_detail, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
       return detailList.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.onBind(detailList[position])
        holder.itemView.price.setOnClickListener {
            onclick(detailList[position])
        }
    }

}

