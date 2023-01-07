package states

import tokens.NumberToken
import tokens.Token

class NumberState : State {
    private val stringBuilder = StringBuilder()

    override fun next(char: Int): Pair<State, List<Token>?> {
        if (char == -1) {
            return Pair(EndState(), listOf(NumberToken(stringBuilder.toString().toLong())))
        }
        return when (char.toChar()) {
            in '0'..'9' -> {
                stringBuilder.append(char.toChar())
                Pair(this, null)
            }

            else -> {
                val pair = StartState().next(char)
                Pair(pair.first,
                    mutableListOf<Token>(NumberToken(stringBuilder.toString().toLong())).also {
                        it.addAll(pair.second ?: emptyList())
                    })
            }
        }
    }
}