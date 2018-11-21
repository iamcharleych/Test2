package com.chaplin.test1.domain.execution;

import io.reactivex.Scheduler;

public interface ExecutionThread {
    Scheduler getScheduler();
}
