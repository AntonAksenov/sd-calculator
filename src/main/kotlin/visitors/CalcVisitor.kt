package visitors

import tokens.BraceToken
import tokens.NumberToken
import tokens.OperationToken
import tokens.Token

class CalcVisitor : TokenVisitor {
    private val stack = ArrayDeque<Token>()

    override fun visit(token: NumberToken) {
        stack.add(token)
    }

    override fun visit(token: BraceToken) {
        throw IllegalArgumentException("there should be no braces")
    }

    override fun visit(token: OperationToken) {
        check(stack.size >= 2)
        stack.add(token.evaluate(stack.removeLast(), stack.removeLast()))
    }

    fun evaluate(tokens: List<Token>): Token {
        if (tokens.isEmpty()) {
            throw IllegalArgumentException("Empty token list")
        }

        tokens.forEach { it.accept(this) }

        if (stack.size != 1) {
            throw IllegalArgumentException("Invalid token sequence")
        }

        return stack.removeLast()
    }
}