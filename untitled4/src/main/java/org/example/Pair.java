package org.example;

import java.util.Objects;

@SuppressWarnings("unchecked") // do not remove this line

class Pair<T,E> {
    private T num;
    private E string;

    @Override
    public boolean equals(Object pair) {
        if (pair == this) {
            return true;
        }
        if (pair == null) {
            return false;
        }
       if (pair.getClass().equals(Pair.class)){
           Pair current = (Pair) pair;
           return num == null ? current.num == null : num.equals(current.num) &&
                   string == null ? current.string == null : string.equals(current.string);
       }
       return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (31 * result) + ((num == null) ? 0 : num.hashCode());
        result = (31 * result) + ((string == null) ? 0 : string.hashCode());
        return result;
    }

    public T getKey() {
        return num;
    }

    public E getValue() {
        return string;
    }

    public Pair(T num, E string) {
        this.num = num;
        this.string = string;
    }
}
