package database;

public class logQueryWithParam {
    public static String logQueryWithParams(String query, Object... params) {
        if (params == null || params.length == 0) {
            return query;
        }
        StringBuilder sb = new StringBuilder();
        int paramIndex = 0;

        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (c == '?' && paramIndex < params.length) {
                Object param = params[paramIndex++];
                if (param instanceof String) {
                    sb.append("'").append(param).append("'");
                } else {
                    sb.append(param);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
