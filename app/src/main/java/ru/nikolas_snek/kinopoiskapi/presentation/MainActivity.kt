package ru.nikolas_snek.kinopoiskapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.databinding.ActivityMainBinding
import ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves.FavoriteMoviesFragment
import ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.MainFilmListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //todo после переворота запоминать выбраный экран
        binding.bnvMenu?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    launchFragment(FavoriteMoviesFragment.newInstance())
                    true
                }
                R.id.movies -> {
                    launchFragment(MainFilmListFragment.newInstance())
                    true
                }
                else -> false
            }


        }
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_view,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

}