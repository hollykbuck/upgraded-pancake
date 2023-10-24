package wiki.pde.interpreter.token;

public class Token {
    public TokenEnum type;
    public String literal;

    public Token() {
    }

    public Token(TokenEnum type, String literal) {
        this.type = type;
        this.literal = literal;
    }
}
