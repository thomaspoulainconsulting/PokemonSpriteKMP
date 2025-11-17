package com.thomaspoulain.pokemonspritekmp.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Filters(
    filter: Generation,
    onFilterChange: (Generation) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Generation.entries.forEach {
            FilterChip(
                selected = filter == it,
                label = { Text(it.name) },
                onClick = { onFilterChange(it) },
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        Filters(
            filter = Generation.Gen1,
            onFilterChange = {},
        )
    }
}