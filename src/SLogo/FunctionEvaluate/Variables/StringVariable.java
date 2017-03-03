package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class StringVariable extends Variable<String> {
    StringVariable(String value) {
        super(value);
    }

    @Override
    public StringVariable sum(Variable other) {
        return new StringVariable(value() + other.toContentString());
    }

    @Override
    public StringVariable random() {
        char[] randomChars = new char[(int) (Math.random() * (value().length() + 1))];
        for (int i = 0; i < randomChars.length; i++) {
            String alphanumericString = "abcdefghijklmnopqrstuvwxyz0123456789";
            randomChars[i] = alphanumericString.charAt((int) (Math.random() * alphanumericString.length()));
        }
        return new StringVariable(new String(randomChars));
    }

    @Override
    public boolean equals(Variable o) {
        return o instanceof StringVariable ? this.toContentString().equals(o.toContentString()) : super.equals(o);
    }

    @Override
    public int compareTo(Variable o) {
        return o instanceof StringVariable ? this.toContentString().compareTo(o.toContentString()) : super.compareTo(o);
    }

    @Override
    public boolean toBoolean() {
        return value().length() > 0;
    }

    @Override
    public double toNumber() throws NotANumberException {
        if (value().length() == 0) {
            return 0;
        }
        try {
            return Double.parseDouble(value());
        } catch (NumberFormatException e) {
            throw new NotANumberException(value());
        }
    }

    @Override
    public String toString() {
        return '\"' + value() + '\"';
    }
}
