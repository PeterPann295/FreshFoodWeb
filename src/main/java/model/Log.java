package model;

import java.sql.Timestamp;

public class Log {
    private int id;
    private String action;
    private String table;
    private String level;
    private String beforeData;
    private String afterData;
    private int user_id;
    private Timestamp time;

    public Log(String action, String table, String level, String beforeData, String afterData, int user_id) {
        this.action = action;
        this.table = table;
        this.level = level;
        this.beforeData = beforeData;
        this.afterData = afterData;
        this.user_id = user_id;
        this.time = new Timestamp(System.currentTimeMillis());
    }
    public Log(){}
    public Log(int id, String action, String table, String level, String beforeData, String afterData, int user_id, Timestamp time) {
        this.id = id;
        this.action = action;
        this.table = table;
        this.level = level;
        this.beforeData = beforeData;
        this.afterData = afterData;
        this.user_id = user_id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }

    public String getAfterData() {
        return afterData;
    }

    public void setAfterData(String afterData) {
        this.afterData = afterData;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUsername(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", table='" + table + '\'' +
                ", level='" + level + '\'' +
                ", beforeData='" + beforeData + '\'' +
                ", afterData='" + afterData + '\'' +
                ", user_id='" + user_id+ '\'' +
                ", time=" + time +
                '}';
    }
    public static <T extends IModel> Log insert(T model, int user_id) {
        return new Log("insert", model.table(), "infor", "{}", model.afterData(), user_id);
    }
    public static <T extends IModel> Log update(T model, int user_id) {
        return new Log("update", model.table(), "warning", model.beforeData(), model.afterData(), user_id);
    }
    public static <T extends IModel> Log delete(T model, int user_id) {
        return new Log("delete", model.table(), "danger", model.beforeData(), "{}", user_id);
    }
    public static <T extends IModel> Log login(T model, int user_id) {
        return new Log("login", model.table(), "infor", "{}", "{}", user_id);
    }
}
