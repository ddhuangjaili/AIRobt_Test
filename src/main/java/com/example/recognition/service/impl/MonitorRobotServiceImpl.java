package com.example.recognition.service.impl;

import com.example.recognition.ConfigBeanValue;
import com.example.recognition.execute.RemoteExecuteCommand;
import com.example.recognition.model.OSLoginMessage;
import com.example.recognition.model.OSMessage;
import com.example.recognition.model.OSShellCommand;
import com.example.recognition.service.MonitorRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MonitorRobotServiceImpl implements MonitorRobotService {

    @Autowired
    ConfigBeanValue config;

    @Override
    public List<OSMessage> getResult() throws Exception {
        return "1".endsWith(config.env_flag) ?
                getResultList(config.env_sjs) : getResultList(config.env_zt);
    }

    /**
     * 获取结果集
     * @param ipGroup
     * @return
     */
    private List<OSMessage> getResultList(String ipGroup) throws Exception {
        List<OSMessage> resultList = new ArrayList<>();
        OSMessage osm = null;

        List<OSLoginMessage> loginList = getLoginList(ipGroup);

        if (loginList.size() > 0) {
            for (OSLoginMessage os : loginList) {
                RemoteExecuteCommand rec = new RemoteExecuteCommand(os.getIp(), os.getUserName(), os.getUserPwd());
                Map<String, Integer> map = rec.execute_num();
                for (OSShellCommand command : OSShellCommand.values()) {
                    osm = new OSMessage(
                            command.getOsName(),
                            os.getIp(),
                            command.getProcessNum(),
                            map.get(command.getOsFlag()),
                            getRunnerStatus(command.getProcessNum(), map.get(command.getOsFlag())),
                            getAlarmStatus(command.getProcessNum(), map.get(command.getOsFlag()))
                    );

                    resultList.add(osm);
                }
            }
        }

        return resultList;
    }

    /**
     * 获取ssh登录信息
     * @param configValue
     * @return
     */
    private List<OSLoginMessage> getLoginList(String configValue){
        List<OSLoginMessage> reusltList = new ArrayList<>();
        String[] strList = configValue.split(";");
        OSLoginMessage os = null;

        for (String str : strList){
            os = new OSLoginMessage(str.split(",")[0],
                    str.split(",")[1],
                    str.split(",")[2]);
            reusltList.add(os);
        }

        return reusltList;
    }

    /**
     * 运行状态
     * n:正常运行;f:故障运行;s:停止运行
     * @param pl
     * @param ac
     * @return
     */
    private String getRunnerStatus(Integer pl, Integer ac){
        return ac == pl ? "n" : (ac > 0 && ac < pl) ? "f": "s";
    }

    /**
     * 告警级别
     * 0:无;1:紧急;2:严重
     * @param pl
     * @param ac
     * @return
     */
    private Integer getAlarmStatus(Integer pl, Integer ac){
        return ac == pl ? 0 : (ac > 0 && ac < pl) ? 1: 2;
    }
}
