package states

import tokens.Token

class ErrorState:State {
    fun getMessage() = "error"

    override fun next(char: Int): Pair<State, List<Token>?> {
        return Pair(this, null)
    }
}