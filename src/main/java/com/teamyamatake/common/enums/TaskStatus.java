package com.teamyamatake.common.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    NotStarted(1, "未着手"),
    InProgress(2, "実行中"),
    Waiting(3, "ステイ中"),
    Done(4, "完了");

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

    public static TaskStatus getType(final int value) {
        TaskStatus[] types = TaskStatus.values();
        for (TaskStatus type : types) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
