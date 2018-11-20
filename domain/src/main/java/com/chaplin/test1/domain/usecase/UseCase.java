package com.chaplin.test1.domain.usecase;

public abstract class UseCase<RESULT, PARAMS> {

    public abstract RESULT execute(PARAMS params);
}
