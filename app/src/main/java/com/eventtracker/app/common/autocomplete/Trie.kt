package com.eventtracker.app.common.autocomplete

class Trie {
    private lateinit var root: TrieNode

    fun build(words: List<String>) {
        root = TrieNode()
        var current: TrieNode
        for (word in words) {
            current = root
            for (char in word) {
                if (current.children[char] == null) {
                    current.children[char] =
                        TrieNode()
                }
                current = current.children[char]!!
            }
            current.isWord = true
        }
    }

    fun autocomplete(prefix: String): List<String> {
        var current = root
        for (char in prefix) {
            if (current.children[char] == null) {
                return listOf()
            }
            current = current.children[char]!!
        }

        return findWordsFromNode(current, prefix)
    }

    private fun findWordsFromNode(node: TrieNode, prefix: String): List<String> {
        var words: MutableList<String> = mutableListOf()
        if (node.isWord) {
            words.add(prefix)
        }
        for (char in node.children.keys) {
            words.addAll(findWordsFromNode(node.children[char]!!, "$prefix$char"))
        }

        return words
    }
}