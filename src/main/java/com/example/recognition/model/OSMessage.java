package com.example.recognition.model;

public class OSMessage {
    //实例名称
    private String osName;
    //实例IP
    private String ip;
    //计划进程数
    private Integer processPlanNum;
    //实际进程数
    private Integer processActualNum;
    //运行状态
    private String runStatus;
    //告警级别
    private Integer alarmLevel;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getProcessPlanNum() {
        return processPlanNum;
    }

    public void setProcessPlanNum(Integer processPlanNum) {
        this.processPlanNum = processPlanNum;
    }

    public Integer getProcessActualNum() {
        return processActualNum;
    }

    public void setProcessActualNum(Integer processActualNum) {
        this.processActualNum = processActualNum;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public OSMessage() {
    }

    public OSMessage(String osName, String ip, Integer processPlanNum, Integer processActualNum, String runStatus, Integer alarmLevel) {
        this.osName = osName;
        this.ip = ip;
        this.processPlanNum = processPlanNum;
        this.processActualNum = processActualNum;
        this.runStatus = runStatus;
        this.alarmLevel = alarmLevel;
    }
}
