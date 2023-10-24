package wiki.pde.interpreter.lexer;

import wiki.pde.interpreter.token.Token;
import wiki.pde.interpreter.token.TokenEnum;
import wiki.pde.interpreter.token.TokenUtil;

public class Lexer {
    int readPosition;
    int position;
    String input;
    char ch;
    public Lexer(String input) {
        this.input = input;
        readChar();
    }
    public void readChar() {
        if (readPosition >= input.length()) {
            ch = 0;
        } else {
            ch = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition += 1;
    }

    private static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
    }

    public void skipWhitespace() {
        while (TokenUtil.isWhitespace(ch)) {
            readChar();
        }
    }

    public Token NextToken() {
        Token token = new Token();
        skipWhitespace();
        switch (ch) {
            case '=':
                token.type = TokenEnum.ASSIGN;
                token.literal = "=";
                break;
            case ';':
                token.type = TokenEnum.SEMICOLON;
                token.literal = ";";
                break;
            case '(':
                token.type = TokenEnum.LPAREN;
                token.literal = "(";
                break;
            case ')':
                token.type = TokenEnum.RPAREN;
                token.literal = ")";
                break;
            case ',':
                token.type = TokenEnum.COMMA;
                token.literal = ",";
                break;
            case '+':
                token.type = TokenEnum.PLUS;
                token.literal = "+";
                break;
            case '{':
                token.type = TokenEnum.LBRACE;
                token.literal = "{";
                break;
            case '}':
                token.type = TokenEnum.RBRACE;
                token.literal = "}";
                break;
            case 0:
                token.type = TokenEnum.EOF;
                token.literal = "";
                break;
            default:
                if (isLetter(ch)) {
                    token.literal = readIdentifier();
                    token.type = TokenUtil.lookupIdent(token.literal);
                    return token;
                } else if (Character.isDigit(ch)) {
                    token.type = TokenEnum.INT;
                    token.literal = readNumber();
                    return token;
                } else {
                    token.type = TokenEnum.ILLEGAL;
                    token.literal = "";
                }
                break;
        }
        readChar();
        return token;
    }

    public String readIdentifier() {
        int pos = position;
        while (isLetter(ch)) {
            readChar();
        }
        return input.substring(pos, position);
    }

    public String readNumber() {
        int pos = position;
        while (Character.isDigit(ch)) {
            readChar();
        }
        return input.substring(pos, position);
    }


}
