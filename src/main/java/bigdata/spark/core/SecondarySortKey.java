package bigdata.spark.core;

import scala.Serializable;
import scala.math.Ordered;

/**
 * 自定义的二次排序Key
 * Created by Administrator on 2016/10/8.
 */
public class SecondarySortKey implements Ordered<SecondarySortKey>, Serializable{

    //首先定义Key
    private String first;

    private int second;

    public SecondarySortKey(String first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compare(SecondarySortKey that) {
        int comp = this.getFirst().compareTo(that.getFirst());

        if (0 != comp) {
            return comp;
        }else{
            return Integer.valueOf(this.getSecond()).compareTo(Integer.valueOf(that.getSecond()));
        }
    }

    @Override
    public boolean $less(SecondarySortKey that) {
        /**
         * 第一列比第二列小的情况：
         *      第一列小
         *      第一列相等，第二列小
         */
        if (this.getFirst().compareTo(that.getFirst()) < 0) {
            return true;
        } else if ((this.getFirst().compareTo(that.getFirst()) == 0) && (this.getSecond() < this.getSecond())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater(SecondarySortKey that) {
        /**
         * 第一列比第二列大的情况：
         *      第一列大
         *      第一列相等，第二列大
         */
        if (this.getFirst().compareTo(that.getFirst()) > 0) {
            return true;
        } else if ((this.getFirst().compareTo(that.getFirst()) == 0) && (this.getSecond() > that.getSecond())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $less$eq(SecondarySortKey that) {
        if (this.$less(that)) {
            return true;
        } else if ((this.getFirst() == that.getFirst()) && (this.getSecond() == that.getSecond())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater$eq(SecondarySortKey that) {
        if (this.$greater(that)) {
            return true;
        } else if ((this.getFirst() == that.getFirst()) && (this.getSecond() == that.getSecond())) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(SecondarySortKey that) {
        int comp = this.getFirst().compareTo(that.getFirst());

        if (0 != comp) {
            return comp;
        }else{
            return Integer.valueOf(this.getSecond()).compareTo(Integer.valueOf(that.getSecond()));
        }
    }

    //定义get和set方法


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondarySortKey that = (SecondarySortKey) o;

        if (second != that.second) return false;
        return first != null ? first.equals(that.first) : that.first == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + second;
        return result;
    }
}
