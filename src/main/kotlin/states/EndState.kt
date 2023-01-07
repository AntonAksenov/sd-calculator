package states

import tokens.Token

class EndState :State{
    override fun next(char: Int): Pair<State, List<Token>?> {
        return Pair(this, null)
    }
}