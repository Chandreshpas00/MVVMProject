object Bob {
    fun hey(input: String): String {
        val trimmed = input.trim()

        return when {
            // Silence
            trimmed.isEmpty() ->
                "Fine. Be that way!"

            // Yelled question
            trimmed.endsWith("?") &&
            trimmed == trimmed.uppercase() &&
            trimmed.any { it.isLetter() } ->
                "Calm down, I know what I'm doing!"

            // Normal question
            trimmed.endsWith("?") ->
                "Sure."

            // Yell (all caps, contains letters)
            trimmed == trimmed.uppercase() &&
            trimmed.any { it.isLetter() } ->
                "Whoa, chill out!"

            else ->
                "Whatever."
        }
    }
}
