package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentMainFilmListBinding
import ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler.FilmsListAdapter

class MainFilmListFragment : Fragment() {


    private lateinit var viewModel: MainFilmListViewModel
    private lateinit var filmsListAdapter: FilmsListAdapter
    private var fragmentContainerView: FragmentContainerView? = null

    private var _binding: FragmentMainFilmListBinding? = null
    private val binding: FragmentMainFilmListBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fragmentContainerView = requireActivity().findViewById(R.id.portrait_container_view)
        viewModel = ViewModelProvider(this).get(MainFilmListViewModel::class.java)
        viewModel.getAllFilms()
        viewModel.filmsList.observe(viewLifecycleOwner) {
            filmsListAdapter.submitList(it)
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
            findNavController().navigate(
                MainFilmListFragmentDirections.actionMainFilmListFragmentToFilmInfoFragment(
                    it.filmId
                ))

                // bcgjkmpjdfnm
//            if (fragmentContainerView == null) {
//                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
//                startActivity(intent)
//            } else {
//                launchFragment(ShopItemFragment.newIntentEditItem(it.id))
//            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFilmListFragment()
    }

}