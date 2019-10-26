package com.example.recognition.model;

public enum OSShellCommand {
    ROBOR_SERVICE("robot","机器人服务","11.8.80.23","ps -ef|grep -v grep |grep \".\\/robot\"",10),
    RESOURCE_SCHEDULE_SERVICE("resource","资源调度服务","11.8.80.21","ps -ef|grep -v grep |grep \".\\/dpctrl\"",1),
    SEMANTIC_UNDERSTAND_SERVICE("semantic","语义理解服务","11.8.80.23","ps -ef|grep -v grep |grep \".\\/sds\"",10),
    VOICE_SYNTHESIS_SERVICE("voice_syn","语音合成服务","11.8.80.23","ps -ef|grep -v grep |grep \"ptts.pyc\"",20),
    VOICE_SYNTHESIS_CHILD_SERVICE("voice_syn_cld","语音合成子服务","11.8.80.23","ps -ef|grep -v grep |grep \".\\/c2v\"",10),
    VOICE_RECOGNITION_SERVICE("voice_rec","语音识别服务","11.8.80.23","ps -ef|grep -v grep |grep \".\\/xasr\"",10),
    WEB_APPLICATION_SERVICE("web","web应用服务","11.8.80.21","ps -ef|grep -v grep |grep \"tomcat\"",1);

    private String osFlag;  //标识
    private String osName;  //实例名称
    private String ip;      //实例IP
    private String command; //shell命令
    private Integer processNum; //计划进程数

    OSShellCommand(String osFlag, String osName, String ip, String command, Integer processNum) {
        this.osFlag = osFlag;
        this.osName = osName;
        this.ip = ip;
        this.command = command;
        this.processNum = processNum;
    }

    public String getOsFlag() {
        return osFlag;
    }

    public String getOsName() {
        return osName;
    }

    public String getIp() {
        return ip;
    }

    public String getCommand() {
        return command;
    }

    public Integer getProcessNum() {
        return processNum;
    }
}
