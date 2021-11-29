package jth.companies.main.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["logo"])
fun setLogo(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(it).into(view)
    }
}
