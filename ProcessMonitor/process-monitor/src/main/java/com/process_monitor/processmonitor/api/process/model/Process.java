package com.process_monitor.processmonitor.api.process.model;

public class Process {
    int id;
    String name;
    String status;
    double cpuUsage;
    double gpuUsage;
    double memoryUsage;
    double networkUsage;

    public Process(int id, String name, String status, double cpuUsage, double gpuUsage, double memoryUsage, double networkUsage) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.memoryUsage = memoryUsage;
        this.networkUsage = networkUsage;
    }

    public Process(String name, String status, double cpuUsage, double gpuUsage, double memoryUsage, double networkUsage) {
        this.name = name;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.memoryUsage = memoryUsage;
        this.networkUsage = networkUsage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getGpuUsage() {
        return gpuUsage;
    }

    public void setGpuUsage(double gpuUsage) {
        this.gpuUsage = gpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getNetworkUsage() {
        return networkUsage;
    }

    public void setNetworkUsage(double networkUsage) {
        this.networkUsage = networkUsage;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", gpuUsage=" + gpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", networkUsage=" + networkUsage +
                '}';
    }
}
