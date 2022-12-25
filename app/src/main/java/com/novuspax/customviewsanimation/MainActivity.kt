package com.novuspax.customviewsanimation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.novuspax.androidutilities.utils.customviews.CustomProgressBarView
import com.novuspax.customviewsanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val progressBar = findViewById<CustomProgressBarView>(R.id.progress_bar)
        val animator = ValueAnimator.ofInt(0,100)
        animator.setDuration(5000)
        animator.addUpdateListener {
            progressBar.setProgress(it.animatedValue as Int)
        }
        animator.start()

        /*val cardView = findViewById<CardView>(R.id.card)
        cardView.setOnClickListener {
            it.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(200)
                .start()
        }*/

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val cardView = findViewById<CardView>(R.id.card)

        val fabAnimator = objectAnimatorPropertyValuesHolder(fab,cardView)
        fabAnimator.duration = 2000
        //            val animator = objectAnimatorFloatValuesHolder(it)
        fab.setOnClickListener {
            fabAnimator.start()
        }
        fabAnimator.addUpdateListener {
            Log.e("TAG", "onCreate: ${it.animatedValue}", )
        }




        val cardAnimator = objectAnimatorPropertyValuesHolder(cardView,fab)
        //            val animator = objectAnimatorFloatValuesHolder(it)
        cardView.setOnClickListener { c->
            animator.duration = 2000
            animator.addUpdateListener {

                objectAnimatorPropertyValuesHolder(c,fab)
            }
            animator.start()
        }
    }

    private fun objectAnimatorFloatValuesHolder(it: View?): ObjectAnimator {
        return ObjectAnimator.ofFloat(it,"x",200f)
    }

    private fun objectAnimatorPropertyValuesHolder(it: View,card:View):ObjectAnimator {
        return ObjectAnimator.ofPropertyValuesHolder(
            it,
            // x and y will move to particular position
            // x will go to left to right
            // y will go from top to bottom
            PropertyValuesHolder.ofFloat("x", card.x),
            PropertyValuesHolder.ofFloat("y", card.y),
//                PropertyValuesHolder.ofFloat("scaleX", 0.9f, 0f),
//                PropertyValuesHolder.ofFloat("scaleY", 0.9f, 0f),
//                PropertyValuesHolder.ofFloat("translationY", 0f, fab.top.toFloat() - it.top),
//                PropertyValuesHolder.ofFloat("translationX", 0f, fab.top.toFloat() - it.top)
        )
    }
}