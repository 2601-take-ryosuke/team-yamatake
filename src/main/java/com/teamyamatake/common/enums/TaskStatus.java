package com.teamyamatake.common.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    NotStarted(0, "未着手"),
    InProgress(1, "実行中"),
    Waiting(2, "ステイ中"),
    Done(3, "完了");

    private final int value;
    private final String viewName;

    private TaskStatus(int value, String viewName) {
        this.value = value;
        this.viewName = viewName;
    }

    public boolean isNotStarted() {
        return this == NotStarted;
    }

    public boolean isInProgress() {
        return this == InProgress;
    }

    public boolean isWaiting() {
        return this == Waiting;
    }

    public boolean isDone() {
        return this == Done;
    }
}
