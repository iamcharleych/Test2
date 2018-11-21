package com.chaplin.test2.domain.execution;

import io.reactivex.Scheduler;

public interface ExecutionThread {
    Scheduler getScheduler();
}
