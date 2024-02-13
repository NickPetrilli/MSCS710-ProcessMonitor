package com.process_monitor.processmonitor.api.gpu.model;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;

public class Gpu extends Cpu {
    public Gpu(int processId, String timestamp, double speed, double maxSpeed) {
        super(processId, timestamp, speed, maxSpeed);
    }
}
