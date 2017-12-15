package zhanghegang.com.bawei.onetime.bean;

import java.util.List;

/**
 * current package:zhanghegang.com.bawei.onetime.bean
 * Created by Mr.zhang
 * date: 2017/12/14
 * decription:开发
 */

public class UserInterfaceBean {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512531543678firstFrame.jpg","createTime":"2017-12-06T11:39:03","favoriteNum":null,"latitude":"40.040923","localUri":null,"longitude":"116.30046","playNum":null,"praiseNum":null,"uid":88,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512531543678video_1512531524161.mp4","wid":66,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512629690803firstFrame.jpg","createTime":"2017-12-07T14:54:50","favoriteNum":null,"latitude":"40.040464","localUri":null,"longitude":"116.300061","playNum":null,"praiseNum":null,"uid":88,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512629690803video_1512629663564.mp4","wid":183,"workDesc":"咳咳"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : null
         * cover : https://www.zhaoapi.cn/images/quarter/1512531543678firstFrame.jpg
         * createTime : 2017-12-06T11:39:03
         * favoriteNum : null
         * latitude : 40.040923
         * localUri : null
         * longitude : 116.30046
         * playNum : null
         * praiseNum : null
         * uid : 88
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512531543678video_1512531524161.mp4
         * wid : 66
         * workDesc :
         */

        private Object commentNum;
        private String cover;
        private String createTime;
        private Object favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private Object playNum;
        private Object praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private String workDesc;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(Object favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getLocalUri() {
            return localUri;
        }

        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getPlayNum() {
            return playNum;
        }

        public void setPlayNum(Object playNum) {
            this.playNum = playNum;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }
    }
}
