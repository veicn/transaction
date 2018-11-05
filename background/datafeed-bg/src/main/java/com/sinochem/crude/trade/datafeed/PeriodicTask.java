package com.sinochem.crude.trade.datafeed;

public class PeriodicTask {
    private int id;
    private long time;
    private Callback<Integer> task;
    private long lastTime = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Callback<Integer> getTask() {
        return task;
    }

    public void setTask(Callback<Integer> task) {
        this.task = task;
    }

    public void runIfTimeOut(long now) {
        if (0 == lastTime) {
            lastTime = now;
            return;
        }
        if (now - lastTime >= time) {
            task.handle(id);
            lastTime = now;
        }
    }
}
