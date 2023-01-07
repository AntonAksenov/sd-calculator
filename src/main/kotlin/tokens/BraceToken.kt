package tokens

import visitors.TokenVisitor

open class BraceToken : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

class LeftBraceToken : BraceToken() {
    override fun toString(): String {
        return "("
    }
}

class RightBraceToken : BraceToken() {
    override fun toString(): String {
        return ")"
    }
}