package tokenizer

import states.EndState
import states.ErrorState
import states.StartState
import states.State
import tokens.Token
import java.io.InputStream
import java.text.ParseException

class Tokenizer(private val inputStream: InputStream) {
    private var state: State = StartState()

    @Throws(ParseException::class)
    fun tokenize(): List<Token> {
        val list = mutableListOf<Token>()
        var offset = 0
        while (state !is EndState && state !is ErrorState) {
            var pair: Pair<State, List<Token>?> = Pair(state, null)
            while (pair.second == null && state !is ErrorState) {
                pair = state.next(inputStream.read())
                offset++
                state = pair.first
            }
            if (state is ErrorState) {
                throw ParseException((state as ErrorState).getMessage(), offset)
            } else {
                list.addAll(pair.second!!)
            }
        }
        return list
    }

}