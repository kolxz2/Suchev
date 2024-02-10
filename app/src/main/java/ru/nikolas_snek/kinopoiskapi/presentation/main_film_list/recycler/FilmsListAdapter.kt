package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.databinding.ItemFilmBinding
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class FilmsListAdapter: ListAdapter<ShortFilms, FilmItemViewHolder>(FilmItemDiffUtilCallback())  {

//    var onShopItemLongClickListener: ((ShortFilms) -> Unit)? = null
    var onShopItemClickListener: ((ShortFilms) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: FilmItemViewHolder, position: Int) {
        val filmItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(filmItem)
        }
        binding.tvFilmName.text = filmItem.nameRu

        binding.tvFilmInfo.text = String.format(
            binding.root.context.getString(R.string.short_info_template),
            filmItem.genres.first().genre,
            filmItem.year
        )
        Picasso.get().load(filmItem.posterUrlPreview).into(binding.ivFilmPosterPreview)
    }
}