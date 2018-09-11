package com.book.well_grounded.concurrent;

import com.book.well_grounded.Author;
import com.book.well_grounded.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 《Java程序员的修炼之道》第4章 现代并发
 * 4.5.1 一个简单的分支/合并例子
 * Page:99
 * 我们为说明分支/合并框架而设定了这样的应用场景：有一个数组，里面
 * 存放不同时间到达的微博更新，我们想按到达时间对它们排序，以便为
 * 用户生成时间线。
 * <p>
 * 我们会用MergeSort的变体实现多线程排序。下面的例子用到了ForkJoinTask
 * 的特定子类RecursiveAction。因为它明显可以独立完成任务（对这些更新的
 * 排序能当即完成），而且具备递归处理能力（递归特别适合做排序），所
 * 以用RecursiveAction会比用通用的ForkJoinTask更简单。
 * <p>
 * MicroBlogUpdateSorter类用Update对象的CompareTo()方法对更新列表排序。
 * compute()方法（超类RecursiveAction中的抽象方法，必须实现）基本上是按
 * 创建时间对微博更新数组排序。
 * <p>
 * Created by CaoWeiDong on 2018-09-04.
 */
public class MicroBlogUpdateSorter extends RecursiveAction {
    private static final int SMALL_ENOUGH = 32;
    private final Update[] updates;
    private final int start, end;
    private final Update[] result;

    public MicroBlogUpdateSorter(Update[] updates_) {
        this(updates_, 0, updates_.length);
    }

    public MicroBlogUpdateSorter(Update[] upds_, int startPos_, int endPos_) {
        start = startPos_;
        end = endPos_;
        updates = upds_;
        result = new Update[updates.length];
    }

    private void merge(MicroBlogUpdateSorter left_, MicroBlogUpdateSorter right_) {
        int i = 0;
        int lCt = 0;
        int rCt = 0;
        while (lCt < left_.size() && rCt < right_.size()) {
            result[i++] = (left_.result[lCt].compareTo(right_.result[rCt]) < 0) ?
                    left_.result[lCt++] : right_.result[rCt++];
        }
        while (lCt < left_.size()) result[i++] = left_.result[lCt++];
        while (rCt < right_.size()) result[i++] = right_.result[rCt++];
    }

    public int size() {
        return end - start;
    }

    public Update[] getResult() {
        return result;
    }

    @Override
    protected void compute() {
        if (size() < SMALL_ENOUGH) {
            System.arraycopy(updates, start, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int mid = size() / 2;
            MicroBlogUpdateSorter left = new MicroBlogUpdateSorter(updates, start, start + mid);
            MicroBlogUpdateSorter right = new MicroBlogUpdateSorter(updates, start + mid, end);
            invokeAll(left, right);
            merge(left, right);
        }
    }

    public static void main(String[] args) {
        List<Update> lu = new ArrayList<>();
        String text = "";
        final Update.Builder ub = new Update.Builder();
        final Author a = new Author("Tallulah");
        for (int i = 0; i < 256; i++) {
            text = text + "X";
            long now = System.currentTimeMillis();
            lu.add(ub.author(a).updateText(text).createTime(now).build());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.shuffle(lu);
        Update[] updates = lu.toArray(new Update[0]);
        MicroBlogUpdateSorter sorter = new MicroBlogUpdateSorter(updates);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(sorter);
        for (Update u : sorter.getResult()) {
            System.out.println(u);
        }
    }
}
