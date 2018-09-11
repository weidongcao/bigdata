package com.book.well_grounded.clz;

/**
 * 《Java程序员的修炼之道》第5章 类文件与字节码
 * 5.2.4 示例：反向、代理与方法句柄
 * Page：114
 * 用于演示方法句柄与反向、代理的简洁
 * Created by Cao Wei Dong on 2018-09-05 06:42.
 */
public class WorkUnit<T> {
    private final T workUnit;
    public T getWork(){
        return workUnit;
    }

    public WorkUnit(T workUnit_) {
        workUnit = workUnit_;
    }
}
