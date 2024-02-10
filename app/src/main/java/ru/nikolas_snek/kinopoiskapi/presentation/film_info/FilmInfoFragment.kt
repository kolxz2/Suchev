package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import ru.nikolas_snek.kinopoiskapi.databinding.FragmentFilmInfoBinding

class FilmInfoFragment : Fragment() {


    private val args by navArgs<FilmInfoFragmentArgs>()
    private val gameFragmentViewModelFactory by lazy {
        FilmInfoFragmentViewModelFactory(args.filmId)
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
        Picasso.get().load("https://kinopoiskapiunofficial.tech/images/posters/kp/4507204.jpg")
            .into(binding.ivFilmPoster)
        viewModal.filmInfo.observe(viewLifecycleOwner){
            binding.tvFilmCountries.text = it.nameRu
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FilmInfoFragment()
    }

}