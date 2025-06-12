package com.example.movies.movies

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.menu.MenuItemUnderlineSpan


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
        spannableString.setSpan(MenuItemUnderlineSpan(), 0, spannableString.length, 0)
        menuItem.title = spannableString
    }
}