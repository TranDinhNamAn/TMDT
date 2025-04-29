package Utils;

import java.time.LocalDateTime;

public class Log {
    private int logID;
    private String ip;
    private String nation;
    private int userID;
    private String status;
    private String action;
    private String dataBefore;
    private String dataAfter;
    private LocalDateTime time;
    private int level;

    public Log(int logID, String ip, String nation, int userID, String status, String action, String dataBefore, String dataAfter, LocalDateTime time, int level) {
        this.logID = logID;
        this.ip = ip;
        this.nation = nation;
        this.userID = userID;
        this.status = status;
        this.action = action;
        this.dataBefore = dataBefore;
        this.dataAfter = dataAfter;
        this.time = time;
        this.level = level;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDataBefore() {
        return dataBefore;
    }

    public void setDataBefore(String dataBefore) {
        this.dataBefore = dataBefore;
    }

    public String getDataAfter() {
        return dataAfter;
    }

    public void setDataAfter(String dataAfter) {
        this.dataAfter = dataAfter;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
