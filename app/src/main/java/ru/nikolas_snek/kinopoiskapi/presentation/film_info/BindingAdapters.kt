package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import ru.nikolas_snek.kinopoiskapi.R
import ru.nikolas_snek.kinopoiskapi.doimain.models.Countries
import ru.nikolas_snek.kinopoiskapi.doimain.models.Genres

@BindingAdapter("filmPoster")
fun filmPoster(imageView: ImageView, filmPosterUrl: String?) {
    Picasso.get().load(filmPosterUrl).into(imageView)
}

@BindingAdapter("countryTemplate")
fun countryTemplate(textView: TextView, countries: List<Countries>?) {
    if (countries != null) {
        val genreStringBuilder = SpannableStringBuilder()
        genreStringBuilder.append(textView.context.getString(R.string.country_template))
        genreStringBuilder.append(" ")

        val boldSpan = StyleSpan(Typeface.BOLD)
        for ((index, country) in countries.withIndex()) {
            genreStringBuilder.append(country.country)
            if (index < countries.size - 1) {
                genreStringBuilder.append(", ")
            }
        }
        genreStringBuilder.setSpan(boldSpan, 0, genreStringBuilder.indexOf(":") + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        textView.text = genreStringBuilder
    } else {
        textView.text = ""
    }
}


@BindingAdapter("genreTemplate")
fun genreTemplate(textView: TextView, genres: List<Genres>?) {
    if (genres != null) {
        val genreStringBuilder = SpannableStringBuilder()
        genreStringBuilder.append(textView.context.getString(R.string.genre_template))
        genreStringBuilder.append(" ")

        val boldSpan = StyleSpan(Typeface.BOLD)
        for ((index, genre) in genres.withIndex()) {
            genreStringBuilder.append(genre.genre)
            if (index < genres.size - 1) {
                genreStringBuilder.append(", ")
            }
        }

        genreStringBuilder.setSpan(boldSpan, 0, genreStringBuilder.indexOf(":") + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        textView.text = genreStringBuilder
    } else {
        textView.text = ""
    }
}