package de.comparus.opensource.longmap;

import java.lang.reflect.Array;
import java.util.*;

public class LongMapImpl<V> implements LongMap<V> {

    private MyEntry<Long, V>[] bucketArray;
    private int numBuckets;
    private int size;
    private MyEntry<Long, V> myEntry = new MyEntry<>();

    public LongMapImpl()
    {
        this.numBuckets = 5;
        this.bucketArray = (MyEntry<Long, V>[]) Array.newInstance(myEntry.getClass(), numBuckets);
        this.size = 0;

    }

    final class MyEntry<Long, V> {
        private Long key;
        private V value;
        final int hashCode;
        private MyEntry<Long, V> next;

        public MyEntry(Long key, V value, int hashCode)
        {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
        public MyEntry(){
            this.hashCode = 0;
        }

        public Long getKey() {
            return key;
        }

        public void setKey(Long key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public MyEntry<Long, V> getNext() {
            return next;
        }

        public void setNext(MyEntry<Long, V> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
            return Objects.equals(key, myEntry.key) &&
                    Objects.equals(value, myEntry.value);
        }

        @Override
        public int hashCode() {

            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "MyEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", hashCode=" + hashCode +
                    ", next=" + next +
                    '}';
        }
    }

    private int hashCode (long key) {
        return Objects.hashCode(key);
    }

    private int getBucketIndex(long key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;

        index = index < 0 ? index * -1 : index;
        return index;
    }

    public V remove(long key)
    {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        MyEntry<Long, V> head = bucketArray[bucketIndex];

        MyEntry<Long, V> prev = null;
        while (head != null) {

            if (head.key.equals(key) && hashCode == head.hashCode) {
                break;
            }

            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            bucketArray[bucketIndex] = head.next;
        }

        return head.value;
    }

    public V get(long key)
    {

        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        MyEntry<Long, V> head = bucketArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public V put(long key, V value) {

        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        MyEntry<Long, V> head = bucketArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return value;
            }
            head = head.next;
        }

        size++;
        head = bucketArray[bucketIndex];
        MyEntry<Long, V> newNode = new MyEntry<Long, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray[bucketIndex] = newNode;
        if ((1.0 * size) / numBuckets >= 0.7) {
            numBuckets = 2 * numBuckets;
            MyEntry<Long, V>[] temp = bucketArray;
            bucketArray = (MyEntry<Long, V>[]) Array.newInstance(myEntry.getClass(), numBuckets);
            size = 0;

            for (MyEntry<Long, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
        return value;
    }

    public boolean isEmpty() { return size() == 0; }

    public boolean containsKey(long key) {

        for(MyEntry<Long, V> myEntry: bucketArray){
            if(myEntry != null) {
                if(key == myEntry.key){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {

        for(MyEntry<Long, V> myEntry: bucketArray){
            if(myEntry != null) {
                if(value.equals(myEntry.value)){
                    return true;
                }
            }
        }
        return false;
    }

    public long[] keys() {

        List<Long> listKeys = new ArrayList<>();
        for(MyEntry<Long, V> myEntry: bucketArray){
            if(myEntry != null) {
                listKeys.add(myEntry.key);
            }
        }

        long[] keys = new long[listKeys.size()];
        for (int i = 0; i < listKeys.size(); i++){
            keys[i] = listKeys.get(i);
        }
        return keys;
    }

    public V[] values() {

        List<V> listValues = new ArrayList<>();
        for(MyEntry<Long, V> myEntry: bucketArray){
            if(myEntry != null) {
                listValues.add(myEntry.value);
            }
        }

        Class v = listValues.get(0).getClass();
        V[] values = (V[]) Array.newInstance(v, listValues.size());

        for (int i = 0; i < listValues.size(); i++){
            values[i] = listValues.get(i);
        }

        return values;
    }

    public long size() { return size; }

    public void clear() {
        for (int i = 0; i < numBuckets; i++) {
            bucketArray[i] = null;
        }

    }
}
