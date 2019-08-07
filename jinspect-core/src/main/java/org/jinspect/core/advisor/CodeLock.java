package org.jinspect.core.advisor;

/**
 * 代码�?<br/>
 * �?么叫代码�??代码锁的出现是由于在字节码中，我们无法用�?单的if语句来判定这段代码是生成的还是原有的�?
 * 这会导致�?些监控�?�辑的混乱，比如trace命令如果不使用代码锁保护，将能看到Arthas�?植入的代码并进行跟踪
 * Created by vlinux on 15/5/28.
 */
public interface CodeLock {

    /**
     * 根据字节码流锁或解锁代码<br/>
     * 通过对字节码流的判断，决定当前代码是锁定和解�?
     *
     * @param opcode 字节�?
     */
    void code(int opcode);

    /**
     * 判断当前代码是否还在锁定�?
     *
     * @return true/false
     */
    boolean isLock();

    /**
     * 将一个代码块纳入代码锁保护范�?
     *
     * @param block 代码�?
     */
    void lock(Block block);

    /**
     * 代码�?
     */
    interface Block {
        /**
         * 代码
         */
        void code();
    }

}
