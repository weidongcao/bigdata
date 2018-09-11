package com.book.well_grounded.clz;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 《Java程序员的修炼之道》第5章 类文件与字节码
 * 5.2.4 示例：反向、代理与方法句柄
 * Page：114
 * 用于演示方法句柄与反向、代理的简洁
 * Created by Cao Wei Dong on 2018-09-05 06:42.
 */
public class ThreadPoolManager {
    private final ScheduledExecutorService stpe = Executors.newScheduledThreadPool(2);
    private final BlockingQueue<WorkUnit<String>> lbq;

    public ThreadPoolManager(BlockingQueue<WorkUnit<String>> lbq_) {
        lbq = lbq_;
    }
    public ScheduledFuture<?> run(QueueReaderTask msgReader) {
        msgReader.setQueue(lbq);
        return stpe.scheduleAtFixedRate(msgReader, 10, 10, TimeUnit.MILLISECONDS);
    }

    private void cancel(final ScheduledFuture<?> hndl) {
        stpe.schedule(new Runnable() {
            @Override
            public void run() {
                hndl.cancel(true);
            }
        }, 10, TimeUnit.MILLISECONDS);
    }
    public Method makeReflective() {
        Method meth = null;
        try{
            Class<?>[] argTypes = new Class[]{ScheduledFuture.class};
            meth = ThreadPoolManager.class.getDeclaredMethod("cancel", argTypes);
            meth.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return meth;
    }
    public static class CancelProxy{
        private CancelProxy(){}

        public void invoke(ThreadPoolManager mae_, ScheduledFuture<?> hndl_) {
            mae_.cancel(hndl_);
        }
    }

    public CancelProxy makeProxy(){
        return new CancelProxy();
    }
    public MethodHandle makeMh() {
        MethodHandle mh = null;
        MethodType desc = MethodType.methodType(void.class, ScheduledFuture.class);
        try{
            mh = MethodHandles.lookup().findVirtual(ThreadPoolManager.class, "cancel", desc);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mh;
    }
}
