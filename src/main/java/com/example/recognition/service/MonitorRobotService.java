package com.example.recognition.service;

import com.example.recognition.model.OSMessage;

import java.util.List;

public interface MonitorRobotService {
    List<OSMessage> getResult() throws Exception;
}
