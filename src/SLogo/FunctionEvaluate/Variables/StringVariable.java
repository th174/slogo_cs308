package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class StringVariable extends Variable<String> {
    public StringVariable(String value) {
        super(value);
    }

    @Override
    public StringVariable sum(Variable other) {
        return new StringVariable(toString() + other.toString());
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
        return o instanceof StringVariable ? this.toString().equals(o.toString()) : super.equals(o);
    }

    @Override
    public int compareTo(Variable o) {
        return o instanceof StringVariable ? this.toString().compareTo(o.toString()) : super.compareTo(o);
    }

    @Override
    boolean toBoolean() {
        return value().length() > 0;
    }

    @Override
    double toNumber() throws NotANumberException{
        if (value().length() == 0) {
            return 0;
        }
        try {
            return Double.parseDouble(value());
        } catch (NumberFormatException e){
            throw new NotANumberException(value());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
