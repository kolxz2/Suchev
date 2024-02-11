package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentFilmInfoBinding

class FilmInfoFragment : Fragment() {


    private val args by navArgs<FilmInfoFragmentArgs>()

    private val gameFragmentViewModelFactory by lazy {
        val filmId = requireArguments().getInt(FILM_IF_KEY)
        if (filmId == 0){
            FilmInfoFragmentViewModelFactory(args.filmId)
        } else{
            FilmInfoFragmentViewModelFactory(filmId)
        }

    }
    private val viewModal: FilmInfoViewModel by lazy {
        ViewModelProvider(
            this,
            gameFragmentViewModelFactory
        )[FilmInfoViewModel::class.java]
    }
    private var _binding: FragmentFilmInfoBinding? = null
    private val binding: FragmentFilmInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModal.loadFilmInfo()
        _binding = FragmentFilmInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModal.filmInfo.observe(viewLifecycleOwner){
            if (it == null) {
                binding.emFullFilm.isVisible = true
                binding.svContent.isVisible = false
            } else {
                Log.d("film", it.toString())
                binding.fullFilm = it
                binding.emFullFilm.isVisible = false
                binding.svContent.isVisible = true


            }
        }
        viewModal.loadingProgress.observe(viewLifecycleOwner){
            binding.pbFullFilm.isVisible = it
        }
        binding.emFullFilm.setRetryButtonClickListener{
            viewModal.loadFilmInfo()
        }

//        binding.ivBack.setOnClickListener{
//            findNavController().popBackStack()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{

        private const val FILM_IF_KEY = "FILM_IF_KEY"

        fun newInstance(filmId: Int): FilmInfoFragment{
            return FilmInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(FILM_IF_KEY,filmId)
                }
            }
        }
    }


}