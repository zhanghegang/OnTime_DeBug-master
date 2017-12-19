package zhanghegang.com.bawei.onetime.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * current package:zhanghegang.com.bawei.onetime.bean
 * Created by Mr.zhang
 * date: 2017/12/15
 * decription:开发
 */
@Entity
public class VerSionDownTable {
    @Id
    private Long id;
    @Property(nameInDb = "threadId")
    private String threadId;
    @Property(nameInDb = "startIndex")
    private int startIndex;
    @Property(nameInDb = "endIndex")
    private int endIndex;
    public int getEndIndex() {
        return this.endIndex;
    }
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    public int getStartIndex() {
        return this.startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public String getThreadId() {
        return this.threadId;
    }
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 557423997)
    public VerSionDownTable(Long id, String threadId, int startIndex, int endIndex) {
        this.id = id;
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    @Generated(hash = 1393729875)
    public VerSionDownTable() {
    }
    
}
