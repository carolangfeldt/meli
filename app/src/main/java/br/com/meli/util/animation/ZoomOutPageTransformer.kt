package br.com.meli.util.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val scale = 0.85f.coerceAtLeast(1 - kotlin.math.abs(position))
        val alpha = 0.5f.coerceAtLeast(1 - kotlin.math.abs(position))
        view.apply {
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
            translationX = -position * width * 0.3f
        }
    }
}