import org.junit.Test
import tokenizer.Tokenizer

class TokenizerTest {

    private fun parse(s: String): Any {
        return Tokenizer(s.byteInputStream()).tokenize().toString()
    }

    @Test
    fun numberTest() {
        val expected = "123"
        val actual = parse(expected)
        assert("[123]" == actual) { "[123] != $actual" }
    }
    @Test
    fun severalTokensTest() {
        val expected = "123 456)(234+345"
        val actual = parse(expected)
        assert("[123, 456, ), (, 234, +, 345]" == actual) { "[123, 256] != $actual" }
    }

}