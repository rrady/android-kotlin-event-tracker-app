package com.eventtracker.app.common.autocomplete

class TrieNode (
    var children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isWord: Boolean = false
)