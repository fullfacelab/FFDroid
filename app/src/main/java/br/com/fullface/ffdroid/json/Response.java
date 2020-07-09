package br.com.fullface.ffdroid.json;

/**
 * Created by Wender on 12/08/2016.
 */
public class Response {
    private final int statusCode;
    private final String content;

    Response(int statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isStatusCodeExpected(int expected) {
        return getStatusCode() == expected;
    }

    public final static class Builder {

        private final int statusCode;
        private String content;

        private Builder(int statusCode) {
            this.statusCode = statusCode;
        }

        public static final Builder create(int statusCode) {
            return new Builder(statusCode);
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }


        public Response build() {
            return new Response(statusCode, content);
        }
    }
}
