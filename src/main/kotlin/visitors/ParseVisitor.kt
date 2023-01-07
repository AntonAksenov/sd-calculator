package visitors

import tokens.*
import java.text.ParseException

class ParseVisitor : TokenVisitor {
    private val resultTokens = mutableListOf<Token>()
    private val stack = ArrayDeque<Token>()

    override fun visit(token: NumberToken) {
        resultTokens.add(token)
    }

    override fun visit(token: BraceToken) {
        when (token) {
            is LeftBraceToken -> stack.add(token)
            is RightBraceToken -> {
                while (!stack.isEmpty()) {
                    val fromStack = stack.removeLast()
                    if (fromStack is LeftBraceToken) {
                        break
                    } else {
                        if (stack.isEmpty()) {
                            throw ParseException("wrong brace sequence", 0)
                        }
                        resultTokens.add(fromStack)
                    }
                }
            }
        }
    }

    override fun visit(token: OperationToken) {
        if (!stack.isEmpty()) {
            var fromStack: Token = stack.last()
            while (fromStack is OperationToken && token.getPriority() >= fromStack.getPriority()) {
                resultTokens.add(stack.removeLast())
                if (stack.isEmpty()) {
                    break
                } else {
                    fromStack = stack.last()
                }
            }
        }
        stack.add(token)
    }

    fun parse(tokens: List<Token>): List<Token> {
        tokens.forEach { it.accept(this) }
        resultTokens.addAll(stack.reversed())
        return resultTokens
    }
}