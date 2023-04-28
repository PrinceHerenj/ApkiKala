package dev.groupx.apkikala.ui.screen.search

import dev.groupx.apkikala.model.SearchResult

data class SearchResultsUiState(
    val searchString: String = "",
    var SearchResults: List<SearchResult> = emptyList(),
    val loading: Boolean = false
)