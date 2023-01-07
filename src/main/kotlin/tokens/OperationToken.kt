package tokens

import visitors.TokenVisitor
import java.lang.IllegalArgumentException

abstract class OperationToken : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    abstract fun getPriority(): Int

    fun evaluate(a: Token, b: Token): Token {
        if (a is NumberToken && b is NumberToken) {
            return NumberToken(evaluateImpl(a.value, b.value))
        } else {
            throw IllegalArgumentException("operation can be only between numbers")
        }
    }

    abstract fun evaluateImpl(a: Long, b: Long): Long
}

class MultiplyToken : OperationToken() {
    override fun toString(): String {
        return "*"
    }

    override fun getPriority() = 1
    override fun evaluateImpl(a: Long, b: Long) = a * b
}

class DivideToken : OperationToken() {
    override fun toString(): String {
        return "/"
    }

    override fun getPriority(): Int = 1
    override fun evaluateImpl(a: Long, b: Long) = b / a
}

class PlusToken : OperationToken() {
    override fun toString(): String {
        return "+"
    }

    override fun getPriority(): Int = 2
    override fun evaluateImpl(a: Long, b: Long) = a + b
}

class MinusToken : OperationToken() {
    override fun toString(): String {
        return "-"
    }

    override fun getPriority(): Int = 2
    override fun evaluateImpl(a: Long, b: Long) = a - b
}