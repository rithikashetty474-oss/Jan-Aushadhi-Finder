package com.healthcare.janaushadhi.utils

object FuzzySearch {

    /**
     * Calculate Levenshtein Distance between two strings
     * Returns the minimum number of edits needed to transform one string into another
     */
    fun levenshteinDistance(s1: String, s2: String): Int {
        val len1 = s1.length
        val len2 = s2.length

        val dp = Array(len1 + 1) { IntArray(len2 + 1) }

        for (i in 0..len1) dp[i][0] = i
        for (j in 0..len2) dp[0][j] = j

        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (s1[i - 1].equals(s2[j - 1], ignoreCase = true)) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,      // deletion
                    dp[i][j - 1] + 1,      // insertion
                    dp[i - 1][j - 1] + cost // substitution
                )
            }
        }

        return dp[len1][len2]
    }

    /**
     * Calculate similarity ratio between two strings (0.0 to 1.0)
     */
    fun similarityRatio(s1: String, s2: String): Double {
        val maxLen = maxOf(s1.length, s2.length)
        if (maxLen == 0) return 1.0
        val distance = levenshteinDistance(s1, s2)
        return 1.0 - (distance.toDouble() / maxLen)
    }

    /**
     * Fuzzy match - returns true if similarity is above threshold
     */
    fun fuzzyMatch(query: String, target: String, threshold: Double = 0.7): Boolean {
        val q = query.lowercase()
        val t = target.lowercase()

        // Exact match
        if (t.contains(q)) return true

        // Fuzzy match using Levenshtein
        return similarityRatio(q, t) >= threshold
    }

    /**
     * Advanced fuzzy search with multiple matching strategies
     */
    fun advancedFuzzyMatch(query: String, target: String): Boolean {
        val q = query.lowercase().trim()
        val t = target.lowercase().trim()

        if (q.isEmpty()) return true
        if (t.isEmpty()) return false

        // Strategy 1: Exact substring match
        if (t.contains(q)) return true

        // Strategy 2: Starts with
        if (t.startsWith(q)) return true

        // Strategy 3: Word boundary match
        val targetWords = t.split(" ", "-", "+")
        if (targetWords.any { it.startsWith(q) }) return true

        // Strategy 4: Character-by-character fuzzy match
        var targetIndex = 0
        var matchedChars = 0

        for (queryChar in q) {
            while (targetIndex < t.length) {
                if (t[targetIndex] == queryChar) {
                    matchedChars++
                    targetIndex++
                    break
                }
                targetIndex++
            }
        }

        val matchRatio = matchedChars.toDouble() / q.length
        if (matchRatio >= 0.8) return true

        // Strategy 5: Levenshtein distance
        if (q.length >= 4) {
            val similarity = similarityRatio(q, t)
            if (similarity >= 0.65) return true

            // Check against individual words
            for (word in targetWords) {
                if (word.length >= 3 && similarityRatio(q, word) >= 0.7) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * Get fuzzy match score (0.0 to 1.0) for sorting results
     */
    fun getMatchScore(query: String, target: String): Double {
        val q = query.lowercase().trim()
        val t = target.lowercase().trim()

        if (q.isEmpty()) return 0.0

        // Exact match = highest score
        if (t == q) return 1.0

        // Starts with = high score
        if (t.startsWith(q)) return 0.95

        // Contains = medium-high score
        if (t.contains(q)) return 0.85

        // Levenshtein similarity
        val similarity = similarityRatio(q, t)

        // Word-based matching bonus
        val targetWords = t.split(" ", "-", "+")
        val wordBonus = targetWords.maxOfOrNull { word ->
            if (word.startsWith(q)) 0.9
            else if (word.contains(q)) 0.8
            else similarityRatio(q, word)
        } ?: 0.0

        return maxOf(similarity * 0.7, wordBonus * 0.8)
    }
}