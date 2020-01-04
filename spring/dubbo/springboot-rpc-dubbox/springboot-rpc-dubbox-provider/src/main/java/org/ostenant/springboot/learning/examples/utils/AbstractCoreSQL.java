package org.ostenant.springboot.learning.examples.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class AbstractCoreSQL<T> {
    private static final String AND = ") \nAND (";
    private static final String OR = ") \nOR (";
    private SQLStatement sql = new SQLStatement();

    public AbstractCoreSQL() {
    }

    public abstract T getSelf();

    public T UPDATE(String table) {
        this.sql().statementType = SQLStatement.StatementType.UPDATE;
        this.sql().tables.add(table);
        return this.getSelf();
    }

    public T SET(String sets) {
        this.sql().sets.add(sets);
        return this.getSelf();
    }

    public T SET(String... sets) {
        this.sql().sets.addAll(Arrays.asList(sets));
        return this.getSelf();
    }

    public T INSERT_INTO(String tableName) {
        this.sql().statementType = SQLStatement.StatementType.INSERT;
        this.sql().tables.add(tableName);
        return this.getSelf();
    }

    public T VALUES(String columns, String values) {
        this.sql().columns.add(columns);
        this.sql().values.add(values);
        return this.getSelf();
    }

    public T INTO_COLUMNS(String... columns) {
        this.sql().columns.addAll(Arrays.asList(columns));
        return this.getSelf();
    }

    public T INTO_VALUES(String... values) {
        this.sql().values.addAll(Arrays.asList(values));
        return this.getSelf();
    }

    public T INTO_MULTI_VALUES(List<List<String>> multiValues) {
        this.sql().multiValues.addAll(multiValues);
        return this.getSelf();
    }

    public T SELECT(String columns) {
        this.sql().statementType = SQLStatement.StatementType.SELECT;
        this.sql().select.add(columns);
        return this.getSelf();
    }

    public T SELECT(String... columns) {
        this.sql().statementType = SQLStatement.StatementType.SELECT;
        this.sql().select.addAll(Arrays.asList(columns));
        return this.getSelf();
    }

    public T SELECT_DISTINCT(String columns) {
        this.sql().distinct = true;
        this.SELECT(columns);
        return this.getSelf();
    }

    public T SELECT_DISTINCT(String... columns) {
        this.sql().distinct = true;
        this.SELECT(columns);
        return this.getSelf();
    }

    public T DELETE_FROM(String table) {
        this.sql().statementType = SQLStatement.StatementType.DELETE;
        this.sql().tables.add(table);
        return this.getSelf();
    }

    public T FROM(String table) {
        this.sql().tables.add(table);
        return this.getSelf();
    }

    public T FROM(String... tables) {
        this.sql().tables.addAll(Arrays.asList(tables));
        return this.getSelf();
    }

    public T JOIN(String join) {
        this.sql().join.add(join);
        return this.getSelf();
    }

    public T JOIN(String... joins) {
        this.sql().join.addAll(Arrays.asList(joins));
        return this.getSelf();
    }

    public T INNER_JOIN(String join) {
        this.sql().innerJoin.add(join);
        return this.getSelf();
    }

    public T INNER_JOIN(String... joins) {
        this.sql().innerJoin.addAll(Arrays.asList(joins));
        return this.getSelf();
    }

    public T LEFT_OUTER_JOIN(String join) {
        this.sql().leftOuterJoin.add(join);
        return this.getSelf();
    }

    public T LEFT_OUTER_JOIN(String... joins) {
        this.sql().leftOuterJoin.addAll(Arrays.asList(joins));
        return this.getSelf();
    }

    public T RIGHT_OUTER_JOIN(String join) {
        this.sql().rightOuterJoin.add(join);
        return this.getSelf();
    }

    public T RIGHT_OUTER_JOIN(String... joins) {
        this.sql().rightOuterJoin.addAll(Arrays.asList(joins));
        return this.getSelf();
    }

    public T OUTER_JOIN(String join) {
        this.sql().outerJoin.add(join);
        return this.getSelf();
    }

    public T OUTER_JOIN(String... joins) {
        this.sql().outerJoin.addAll(Arrays.asList(joins));
        return this.getSelf();
    }

    public T WHERE(String conditions) {
        this.sql().where.add(conditions);
        this.sql().lastList = this.sql().where;
        return this.getSelf();
    }

    public T WHERE(String... conditions) {
        this.sql().where.addAll(Arrays.asList(conditions));
        this.sql().lastList = this.sql().where;
        return this.getSelf();
    }

    public T OR() {
        this.sql().lastList.add(") \nOR (");
        return this.getSelf();
    }

    public T AND() {
        this.sql().lastList.add(") \nAND (");
        return this.getSelf();
    }

    public T GROUP_BY(String columns) {
        this.sql().groupBy.add(columns);
        return this.getSelf();
    }

    public T GROUP_BY(String... columns) {
        this.sql().groupBy.addAll(Arrays.asList(columns));
        return this.getSelf();
    }

    public T HAVING(String conditions) {
        this.sql().having.add(conditions);
        this.sql().lastList = this.sql().having;
        return this.getSelf();
    }

    public T HAVING(String... conditions) {
        this.sql().having.addAll(Arrays.asList(conditions));
        this.sql().lastList = this.sql().having;
        return this.getSelf();
    }

    public T ORDER_BY(String columns) {
        this.sql().orderBy.add(columns);
        return this.getSelf();
    }

    public T ORDER_BY(String... columns) {
        this.sql().orderBy.addAll(Arrays.asList(columns));
        return this.getSelf();
    }

    private SQLStatement sql() {
        return this.sql;
    }

    public <A extends Appendable> A usingAppender(A a) {
        this.sql().sql(a);
        return a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.sql().sql(sb);
        return sb.toString();
    }

    private static class SQLStatement {
        StatementType statementType;
        List<String> sets = new ArrayList();
        List<String> select = new ArrayList();
        List<String> tables = new ArrayList();
        List<String> join = new ArrayList();
        List<String> innerJoin = new ArrayList();
        List<String> outerJoin = new ArrayList();
        List<String> leftOuterJoin = new ArrayList();
        List<String> rightOuterJoin = new ArrayList();
        List<String> where = new ArrayList();
        List<String> having = new ArrayList();
        List<String> groupBy = new ArrayList();
        List<String> orderBy = new ArrayList();
        List<String> lastList = new ArrayList();
        List<String> columns = new ArrayList();
        List<String> values = new ArrayList();
        List<List<String>> multiValues = new ArrayList();
        boolean distinct;

        public SQLStatement() {
        }

        private void sqlClause(SafeAppendable builder, String keyword, List<String> parts, String open, String close, String conjunction) {
            if (!parts.isEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append("\n");
                }

                builder.append(keyword);
                builder.append(" ");
                builder.append(open);
                String last = "________";
                int i = 0;

                for (int n = parts.size(); i < n; ++i) {
                    String part = parts.get(i);
                    if (i > 0 && !part.equals(") \nAND (") && !part.equals(") \nOR (") && !last.equals(") \nAND (") && !last.equals(") \nOR (")) {
                        builder.append(conjunction);
                    }

                    builder.append(part);
                    last = part;
                }

                builder.append(close);
            }

        }


        private void multiValueSqlClause(SafeAppendable builder, String keyword, List<List<String>> multiParts, String open, String close, String conjunction) {
            if (!multiParts.isEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append("\n");
                }

                builder.append(keyword);
                builder.append(" ");
                String last = "________";

                int i = 0, j = 0;

                for (int m = multiParts.size(), o = -1; i < m; ++i) {
                    List<String> parts = multiParts.get(i);
                    if (i == 0) {
                        o = 0;
                    }
                    if (parts.isEmpty()) {
                        continue;
                    }
                    if (o > 0) {
                        builder.append(conjunction);
                    }
                    o++;

                    builder.append(open);
                    for (int n = parts.size(); j < n; ++j) {
                        String part = parts.get(j);
                        if (0 < j && !part.equals(") \nAND (") && !part.equals(") \nOR (") && !last.equals(") \nAND (") && !last.equals(") \nOR (")) {
                            builder.append(conjunction);
                        }
                        builder.append(part);
                        last = part;
                    }
                    builder.append(close);
                    j = 0;
                }


            }

        }

        private String selectSQL(SafeAppendable builder) {
            if (this.distinct) {
                this.sqlClause(builder, "SELECT DISTINCT", this.select, "", "", ", ");
            } else {
                this.sqlClause(builder, "SELECT", this.select, "", "", ", ");
            }

            this.sqlClause(builder, "FROM", this.tables, "", "", ", ");
            this.joins(builder);
            this.sqlClause(builder, "WHERE", this.where, "(", ")", " AND ");
            this.sqlClause(builder, "GROUP BY", this.groupBy, "", "", ", ");
            this.sqlClause(builder, "HAVING", this.having, "(", ")", " AND ");
            this.sqlClause(builder, "ORDER BY", this.orderBy, "", "", ", ");
            return builder.toString();
        }

        private void joins(SafeAppendable builder) {
            this.sqlClause(builder, "JOIN", this.join, "", "", "\nJOIN ");
            this.sqlClause(builder, "INNER JOIN", this.innerJoin, "", "", "\nINNER JOIN ");
            this.sqlClause(builder, "OUTER JOIN", this.outerJoin, "", "", "\nOUTER JOIN ");
            this.sqlClause(builder, "LEFT OUTER JOIN", this.leftOuterJoin, "", "", "\nLEFT OUTER JOIN ");
            this.sqlClause(builder, "RIGHT OUTER JOIN", this.rightOuterJoin, "", "", "\nRIGHT OUTER JOIN ");
        }

        private String insertSQL(SafeAppendable builder) {
            this.sqlClause(builder, "INSERT INTO", this.tables, "", "", "");
            this.sqlClause(builder, "", this.columns, "(", ")", ", ");
            if (this.values.size() > 0) {
                this.sqlClause(builder, "VALUES", this.values, "(", ")", ", ");
            }

            if (this.multiValues.size() > 0) {
                this.multiValueSqlClause(builder, "VALUES", this.multiValues, "(", ")", ", ");
            }
            return builder.toString();
        }

        private String deleteSQL(SafeAppendable builder) {
            this.sqlClause(builder, "DELETE FROM", this.tables, "", "", "");
            this.sqlClause(builder, "WHERE", this.where, "(", ")", " AND ");
            return builder.toString();
        }

        private String updateSQL(SafeAppendable builder) {
            this.sqlClause(builder, "UPDATE", this.tables, "", "", "");
            this.joins(builder);
            this.sqlClause(builder, "SET", this.sets, "", "", ", ");
            this.sqlClause(builder, "WHERE", this.where, "(", ")", " AND ");
            return builder.toString();
        }

        public String sql(Appendable a) {
            SafeAppendable builder = new SafeAppendable(a);
            if (this.statementType == null) {
                return null;
            } else {
                String answer;
                switch (statementType) {
                    case DELETE:
                        answer = this.deleteSQL(builder);
                        break;
                    case INSERT:
                        answer = this.insertSQL(builder);
                        break;
                    case SELECT:
                        answer = this.selectSQL(builder);
                        break;
                    case UPDATE:
                        answer = this.updateSQL(builder);
                        break;
                    default:
                        answer = null;
                }

                return answer;
            }
        }

        public static enum StatementType {
            DELETE,
            INSERT,
            SELECT,
            UPDATE;

            private StatementType() {
            }
        }
    }

    private static class SafeAppendable {
        private final Appendable a;
        private boolean empty = true;

        public SafeAppendable(Appendable a) {
            this.a = a;
        }

        public SafeAppendable append(CharSequence s) {
            try {
                if (this.empty && s.length() > 0) {
                    this.empty = false;
                }

                this.a.append(s);
                return this;
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }

        public boolean isEmpty() {
            return this.empty;
        }
    }
}
