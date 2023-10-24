package wiki.pde.interpreter;

import wiki.pde.interpreter.lexer.Lexer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestLexer {
    @Test
    public void testNextToken() {
        String input = """
                let five = 5;
                let ten = 10;
                let add = fn(x, y) {
                x + y;
                };
                let result = add(five, ten);""";
        var tests = List.of(
                new Object[]{"let", "LET"},
                new Object[]{"five", "IDENT"},
                new Object[]{"=", "ASSIGN"},
                new Object[]{"5", "INT"},
                new Object[]{";", "SEMICOLON"},
                new Object[]{"let", "LET"},
                new Object[]{"ten", "IDENT"},
                new Object[]{"=", "ASSIGN"},
                new Object[]{"10", "INT"},
                new Object[]{";", "SEMICOLON"},
                new Object[]{"let", "LET"},
                new Object[]{"add", "IDENT"},
                new Object[]{"=", "ASSIGN"},
                new Object[]{"fn", "FUNCTION"},
                new Object[]{"(", "LPAREN"},
                new Object[]{"x", "IDENT"},
                new Object[]{",", "COMMA"},
                new Object[]{"y", "IDENT"},
                new Object[]{")", "RPAREN"},
                new Object[]{"{", "LBRACE"},
                new Object[]{"x", "IDENT"},
                new Object[]{"+", "PLUS"},
                new Object[]{"y", "IDENT"},
                new Object[]{";", "SEMICOLON"},
                new Object[]{"}", "RBRACE"},
                new Object[]{";", "SEMICOLON"},
                new Object[]{"let", "LET"},
                new Object[]{"result", "IDENT"},
                new Object[]{"=", "ASSIGN"},
                new Object[]{"add", "IDENT"},
                new Object[]{"(", "LPAREN"},
                new Object[]{"five", "IDENT"},
                new Object[]{",", "COMMA"},
                new Object[]{"ten", "IDENT"},
                new Object[]{")", "RPAREN"},
                new Object[]{";", "SEMICOLON"},
                new Object[]{"", "EOF"}
        );
        Lexer lexer = new Lexer(input);
        for (var test : tests) {
            var expectedLiteral = (String) test[0];
            var expectedType = (String) test[1];
            var token = lexer.NextToken();
            assertEquals(expectedLiteral, token.literal);
            assertEquals(expectedType, token.type.name());
//            assert token.literal.equals(expectedLiteral);
//            assert token.type.name().equals(expectedType);
        }

    }
}