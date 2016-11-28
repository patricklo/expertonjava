package com.alibaba.gtool.xlock.common.enums;

/**
 * 分布式锁的事件。
 *
 * @author gumao
 * @since 2016-11-20
 */
public enum XLockEventEnum {
    LOCK_SUCCESS("加锁成功"), LOCK_FAIL("加锁失败"), UNLOCK_SUCCESS("解锁成功"), UNLOCK_FAIL("解锁失败"), LOSE_LOCK("被动丢失锁");

    private String name;

    private XLockEventEnum(String name) {
        this.name = name;
    }
}
