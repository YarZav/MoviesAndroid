package com.example.movies.movies

import android.R.string
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.LineBackgroundSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.drawerLayout.closeDrawer(GravityCompat.START)

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
               R.id.general -> {
                   underlineMenuItem(menuItem)
                   menuItem.isChecked = true
               }
            }

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun underlineMenuItem(menuItem: MenuItem) {
        val spannableString = SpannableString(menuItem.title)
        spannableString.setSpan(CustomUnderlineSpan(), 0, spannableString.length, 0)
        menuItem.title = spannableString
    }
}

class CustomUnderlineSpan(
    private val underlineThickness: Float = 3f,
    private val underlineSpace: Int = 7
): LineBackgroundSpan {

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        val textPaint = paint as TextPaint
        val originalColor = textPaint.color
        textPaint.color = textPaint.color
        textPaint.strokeWidth = underlineThickness

        val right = paint.measureText(text.toString())
        canvas.drawLine(
            left.toFloat(),
            bottom.toFloat() + underlineSpace,
            right,
            bottom.toFloat() + underlineSpace,
            textPaint
        )

        textPaint.color = originalColor
        textPaint.strokeWidth = underlineThickness
    }
}