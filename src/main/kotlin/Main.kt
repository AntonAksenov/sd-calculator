import tokenizer.Tokenizer
import visitors.CalcVisitor
import visitors.ParseVisitor
import visitors.PrintVisitor
import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val parseVisitor = ParseVisitor()
    val printVisitor = PrintVisitor(System.out)
    val calcVisitor = CalcVisitor()

    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val line = reader.readLine()
        val tokens = Tokenizer(line.byteInputStream()).tokenize()
        printVisitor.print(tokens)
        println()
        val parsed = parseVisitor.parse(tokens)
        printVisitor.print(parsed)
        println()
        println(calcVisitor.evaluate(parsed))
    }
}