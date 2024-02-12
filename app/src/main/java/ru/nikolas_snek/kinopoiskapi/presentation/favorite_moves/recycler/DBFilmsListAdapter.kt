package ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves.recycler

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity
import ru.nikolas_snek.kinopoiskapi.databinding.ItemFilmBinding

class DBFilmsListAdapter: ListAdapter<FilmEntity, DBFilmItemViewHolder>(DBFilmItemDiffUtilCallback())  {

    var onShopItemClickListener: ((FilmEntity) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBFilmItemViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DBFilmItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DBFilmItemViewHolder, position: Int) {
        val filmItem = getItem(position)!!
        val binding = viewHolder.binding
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(filmItem)
        }
        binding.tvFilmName.text = filmItem.nameRu

        binding.tvFilmInfo.text = String.format(
            binding.root.context.getString(R.string.short_info_template),
            filmItem.genres.split(", ").first(),
            filmItem.year
        )
        val bitmap = BitmapFactory.decodeByteArray(filmItem.imageData, 0, filmItem.imageData.size)
        binding.ivFilmPosterPreview.setImageBitmap(bitmap)
    }
}