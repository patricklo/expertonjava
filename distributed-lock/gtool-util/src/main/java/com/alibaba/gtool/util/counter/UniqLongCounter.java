package com.alibaba.gtool.util.counter;

import java.util.BitSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 唯一值统计器
 *
 * @author gumao
 * @since 16/10/26
 */
public class UniqLongCounter {

    private Map<Integer, BitSet> map = new ConcurrentHashMap<Integer, BitSet>(100);
    private int onceSize = 10000000;

    /**
     * 添加一个数字
     */
    public void add(long num) {
        //下标
        long indexLong = num / onceSize;
        int index = (int) indexLong;
        //偏移值
        int left = (int) (num - indexLong * onceSize);
        BitSet bitSet = map.get(index);
        //如果不存在，就新建
        if (null == bitSet) {
            //排它锁
            synchronized (map) {
                //高并发预防
                if (null == bitSet) {
                    bitSet = new BitSet(onceSize);
                    map.put(index, bitSet);
                }
            }
        }
        //置为1
        bitSet.set(left);
    }

    /**
     * 唯一性计数
     */
    public long count() {
        long count = 0;
        Set<Entry<Integer, BitSet>> entrySet = map.entrySet();
        for (Entry<Integer, BitSet> entry : entrySet) {
            BitSet bitSet = entry.getValue();
            count += bitSet.cardinality();
        }
        return count;
    }

    /**
     * 清空
     */
    public void clear() {
        this.map.clear();
    }


}
