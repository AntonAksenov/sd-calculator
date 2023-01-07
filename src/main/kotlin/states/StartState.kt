package states

import tokens.*

class StartState : State {
    override fun next(char: Int): Pair<State, List<Token>?> {
        if (char == -1) {
            return Pair(EndState(), null)
        }
        return when (char.toChar()) {
            ' ', '\t', '\n', '\r' -> Pair(this, null)
            in '0'..'9' -> NumberState().next(char)
            '+' -> Pair(this, listOf(PlusToken()))
            '-' -> Pair(this, listOf(MinusToken()))
            '*' -> Pair(this, listOf(MultiplyToken()))
            '/' -> Pair(this, listOf(DivideToken()))
            '(' -> Pair(this, listOf(LeftBraceToken()))
            ')' -> Pair(this, listOf(RightBraceToken()))
            else -> Pair(ErrorState(), null)
        }
    }
}