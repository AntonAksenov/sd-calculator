package states

import tokens.Token

interface State {
    fun next(char: Int): Pair<State, List<Token>?>
}