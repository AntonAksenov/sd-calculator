package visitors

import tokens.BraceToken
import tokens.NumberToken
import tokens.OperationToken

interface TokenVisitor {
    fun visit(token: NumberToken)
    fun visit(token: BraceToken)
    fun visit(token: OperationToken)
}