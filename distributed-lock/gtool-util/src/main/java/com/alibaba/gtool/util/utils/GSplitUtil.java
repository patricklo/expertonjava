package com.alibaba.gtool.util.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 拆分工具类
 *
 * @author gumao
 */
public class GSplitUtil {
    private GSplitUtil() {
    }

    /**
     * 把大的列表拆分成小的列表 by gumao
     */
    public static <A> List<List<A>> splitBigListToSmallList(List<A> bigList, int onceSize) {
        if (null == bigList || 0 == bigList.size()) {
            return null;
        }
        if (onceSize <= 0) {
            return null;
        }

        int bigListSize = bigList.size();
        int splitCount = bigListSize / onceSize;
        // 如果有多余个数
        if (bigListSize != (splitCount * onceSize)) {
            splitCount = splitCount + 1;
        }
        // 最终结果
        List<List<A>> finalList = new ArrayList<List<A>>(splitCount);
        int i = 0;
        // 如果还有数量
        while (i < bigListSize) {
            // 一次取多少个
            int endIndex = Math.min((i + onceSize), bigListSize);
            // 小区间。视图
            List<A> subList = bigList.subList(i, endIndex);
            // 新列表
            List<A> smallList = new ArrayList<A>(subList);
            // 把一小段添加进去
            finalList.add(smallList);

            // 下一次的开始
            i = endIndex;
        }

        return finalList;
    }

    /**
     * 把有序的数字区间按顺序平均分配。 by gumao
     */
    public static Map<Integer, List<Long>> splitNumRangeSorted(long minNum, long maxNum, int holderCount) {
        // 总数量
        int totalSize = (int) (maxNum - minNum + 1);

        // 所有，排好序的
        List<Long> totalQueues = new ArrayList<Long>(totalSize);
        for (long num = minNum; num <= maxNum; ++num) {
            totalQueues.add(num);// 下标
        }
        return splitBigListToHolders(totalQueues, holderCount);
    }

    /**
     * 把大列表平均分配，保证每个holder都有队列。 by gumao
     */
    public static <A> Map<Integer, List<A>> splitBigListToHolders(List<A> bigList, int holderCount) {
        // 总数量
        int totalSize = bigList.size();
        // 每个的大小
        int onceSize = totalSize / holderCount;
        // 是否能平均分
        int left = totalSize - (holderCount * onceSize);

        // 每个holder的小队列
        List<List<A>> finalList = new ArrayList<List<A>>(holderCount);

        // 如果不能平均分，有剩余，就分2大段。
        if (left > 0) {
            // 第一段的每段大小
            int onceOne = (onceSize + 1);
            // 第二段的每段大小
            int onceTwo = onceSize;
            // 2段的拆分下标
            int splitIndex = left * onceOne;
            // 第一段
            List<A> rangeOne = bigList.subList(0, splitIndex);
            // 第二段
            List<A> rangeTwo = bigList.subList(splitIndex, bigList.size());
            // 第一段分解
            List<List<A>> listOne = splitBigListToSmallList(rangeOne, onceOne);
            // 第二段分解
            List<List<A>> listTwo = splitBigListToSmallList(rangeTwo, onceTwo);
            if (null != listOne) {
                // 保存第一段
                finalList.addAll(listOne);
            }
            if (null != listTwo) {
                // 保存第二段
                finalList.addAll(listTwo);
            }
        }
        // 如果能平均分，就直接平均
        else {
            finalList = splitBigListToSmallList(bigList, onceSize);
        }

        // 如果没有把holder分配完，就填充空值
        int needAddHolder = holderCount - finalList.size();
        if (needAddHolder > 0) {
            for (int j = 0; j < needAddHolder; ++j) {
                finalList.add(new ArrayList<A>(0));
            }
        }

        // 每个holder和对应的队列
        Map<Integer, List<A>> finalMap = new TreeMap<Integer, List<A>>();
        for (int i = 0; i < finalList.size(); ++i) {
            Integer key = i;
            List<A> val = finalList.get(i);
            if (val != null) {
                finalMap.put(key, val);
                // System.out.println(">  key=" + i + "  size=" + val.size() +
                // "  val=" + JSON.toJSONString(val));
            }
        }

        return finalMap;
    }
}
