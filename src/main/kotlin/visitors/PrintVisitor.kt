package visitors

import tokens.BraceToken
import tokens.NumberToken
import tokens.OperationToken
import tokens.Token
import java.io.OutputStream

class PrintVisitor(private val outputStream:OutputStream) : TokenVisitor {
    override fun visit(token: NumberToken) {
        outputStream.write(token.toString().toByteArray())
    }

    override fun visit(token: BraceToken) {
//        throw IllegalStateException("brace found")
        outputStream.write(token.toString().toByteArray())
    }

    override fun visit(token: OperationToken) {
        outputStream.write(token.toString().toByteArray())
    }

    fun print(tokens:List<Token>) {
        tokens.forEach { it.accept(this) }
    }
}