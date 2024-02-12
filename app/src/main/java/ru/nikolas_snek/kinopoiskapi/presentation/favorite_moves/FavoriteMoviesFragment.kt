package ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentFavoriteMoviesBinding
import ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves.recycler.DBFilmsListAdapter

class FavoriteMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteMoviesFragment()
    }

    private lateinit var viewModel: FavoriteMoviesViewModel

    private lateinit var binding: FragmentFavoriteMoviesBinding
    private lateinit var shopListAdapter: DBFilmsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
        viewModel.loadAllSavedFilms()
        shopListAdapter = DBFilmsListAdapter()
        binding.rvFilmsList.apply {
            adapter = shopListAdapter
        }
        viewModel.filmsFromBD.observe(viewLifecycleOwner){
            shopListAdapter.submitList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.pbRecyclerFilms.isVisible = it
        }
    }


}