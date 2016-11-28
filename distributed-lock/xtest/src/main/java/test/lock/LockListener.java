package test.lock;

import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.bean.XLock;
import com.alibaba.gtool.xlock.common.bean.XLockEventListener;
import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;
import com.alibaba.gtool.xlock.common.util.XlockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gumao
 * @since 2016-11-20
 */
public class LockListener implements XLockEventListener {
    private static Logger logger = LoggerFactory.getLogger(AtomicLock.class);
    private XLockEventEnum event;

    public LockListener(XLockEventEnum event) {
        this.event = event;
    }

    /**
     * 事件
     */
    public XLockEventEnum getEvent() {
        return event;
    }

    /**
     * 触发监听器
     */
    public void fire(XLockEventEnum event, Thread thread, XLock xlock) {
        AtomicLock lock = (AtomicLock) xlock;
        String log = " ### listener " + XlockUtil.nowDatetime() + " " + event + "  " + thread.getName() + "  " + lock.getName();
        logger.warn(log);
    }
}
