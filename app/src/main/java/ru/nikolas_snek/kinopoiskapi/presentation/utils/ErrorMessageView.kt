package ru.nikolas_snek.kinopoiskapi.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import ru.nikolas_snek.kinopoiskapi.R

class ErrorMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.error_message, this, true)
    }

    fun setRetryButtonClickListener(listener: OnClickListener) {
        findViewById<Button>(R.id.buttonRetry).setOnClickListener(listener)
    }
}