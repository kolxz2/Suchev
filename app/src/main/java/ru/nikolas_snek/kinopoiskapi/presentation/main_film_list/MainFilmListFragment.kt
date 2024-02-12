package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentMainFilmListBinding
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.presentation.film_info.FilmInfoFragment
import ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler.FilmsListAdapter
import ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler.FilmsLoadStateAdapter

class MainFilmListFragment : Fragment() {


    private lateinit var viewModel: MainFilmListViewModel
    private lateinit var filmsListAdapter: FilmsListAdapter
    private var subsidiaryFragmentContainerView: FragmentContainerView? = null

    private lateinit var binding: FragmentMainFilmListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subsidiaryFragmentContainerView =
            requireActivity().findViewById(R.id.support_container_view)
        viewModel = ViewModelProvider(this)[MainFilmListViewModel::class.java]
        viewModel.getAllFilms()
        binding.rvFilmsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val loadStateAdapter = FilmsLoadStateAdapter()
            adapter =
                filmsListAdapter.withLoadStateFooter(loadStateAdapter)
            lifecycleScope.launch {
                viewModel.filmsFlow.collectLatest { pagingData ->
                    filmsListAdapter.submitData(pagingData)
                }
            }
        }
        binding.emRecyclerFilms.setRetryButtonClickListener {
            binding.emRecyclerFilms.visibility = View.GONE
            filmsListAdapter.retry()
        }

        filmsListAdapter.addLoadStateListener {
            binding.rvFilmsList.isVisible = it.refresh != LoadState.Loading
            binding.pbRecyclerFilms.isVisible = it.refresh == LoadState.Loading
            val errorState = it.refresh as? LoadState.Error
            if (errorState != null) {
                binding.emRecyclerFilms.visibility = View.VISIBLE
                 }
        }
    }

    private fun setupRecyclerView() {
        val rvFilmsList = binding.rvFilmsList
        with(rvFilmsList) {
            filmsListAdapter = FilmsListAdapter()
            adapter = filmsListAdapter


        }
        setupLongClickListener()
        setupClickListener()
    }

    private fun updatePagingData(
        filmsFlow: Flow<PagingData<ShortFilms>>,
        desiredFilmId: Int,
    ): Flow<PagingData<ShortFilms>> {
        return filmsFlow.map { pagingData ->
            pagingData.map { film ->
                if (film.filmId == desiredFilmId) {
                    film.copy(isFavorite = true)
                } else {
                    film
                }
            }
        }
    }

    private fun setupLongClickListener() {
        filmsListAdapter.onFilmItemLongClickListener = {
            lifecycleScope.launch {
                updatePagingData(viewModel.filmsFlow, it.filmId)
                    .collectLatest { pagingData ->
                        filmsListAdapter.submitData(pagingData)
                    }
            }
            viewModel.saveFilmToFavorite(it.filmId)
        }
    }

    private fun setupClickListener() {
        filmsListAdapter.onShopItemClickListener = {
            if (subsidiaryFragmentContainerView == null) {
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container_view,
                        FilmInfoFragment.newInstance(it.filmId)
                    )
                    .addToBackStack(null)
                    .commit()
            } else {
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.support_container_view,
                        FilmInfoFragment.newInstance(it.filmId)
                    )
                    .addToBackStack(null)
                    .commit()

            }

        }
    }

    companion object {
        fun newInstance() = MainFilmListFragment()
    }

}