package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentMainFilmListBinding
import ru.nikolas_snek.kinopoiskapi.presentation.film_info.FilmInfoFragment
import ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler.FilmsListAdapter

class MainFilmListFragment : Fragment() {


    private lateinit var viewModel: MainFilmListViewModel
    private lateinit var filmsListAdapter: FilmsListAdapter
    private var subsidiaryFragmentContainerView: FragmentContainerView? = null

    private var _binding: FragmentMainFilmListBinding? = null
    private val binding: FragmentMainFilmListBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subsidiaryFragmentContainerView = requireActivity().findViewById(R.id.support_container_view)
        viewModel = ViewModelProvider(this)[MainFilmListViewModel::class.java]
        viewModel.getAllFilms()
        viewModel.filmsList.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.emRecyclerFilms.visibility = View.VISIBLE
                binding.rvFilmsList.visibility = View.GONE
            } else {
                filmsListAdapter.submitList(it)
                binding.emRecyclerFilms.visibility = View.GONE
                binding.rvFilmsList.visibility = View.VISIBLE
            }
        }
        viewModel.loadingProgress.observe(viewLifecycleOwner) {
            binding.pbRecyclerFilms.isVisible = it
        }
        binding.emRecyclerFilms.setRetryButtonClickListener {
            viewModel.getAllFilms()
            binding.emRecyclerFilms.visibility = View.GONE

        }
    }

    private fun setupRecyclerView() {
        val rvFilmsList = binding.rvFilmsList
        with(rvFilmsList) {
            filmsListAdapter = FilmsListAdapter()
            adapter = filmsListAdapter


        }
//        setupLongClickListener()
        setupClickListener()
    }

    private fun setupClickListener() {
        filmsListAdapter.onShopItemClickListener = {
            // на будущее при разделении экрана
            if (subsidiaryFragmentContainerView == null) {
                findNavController().navigate(
                    MainFilmListFragmentDirections.actionMainFilmListFragmentToFilmInfoFragment(
                        it.filmId
                    )
                )
            } else {
//                findNavController().navigate(
//                    MainFilmListFragmentDirections.actionMainFilmListFragmentToFilmInfoFragment(
//                        it.filmId
//                    )
//                )
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

//    private fun launchFragment(fragment: Fragment) {
//        supportFragmentManager.popBackStack()
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.shop_item_container,
//                fragment
//            )
//            .addToBackStack(null)
//            .commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}