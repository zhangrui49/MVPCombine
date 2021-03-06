package com.zhangrui.huiju.mvp.model;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class Gank {

    /**
     * error : false
     * results : [{"_id":"581828c8421aa90e6f21b495","createdAt":"2016-11-01T13:31:52.168Z","desc":"优化 App 启动时间","publishedAt":"2016-11-02T11:49:08.835Z","source":"web","type":"iOS","url":"http://yulingtianxia.com/blog/2016/10/30/Optimizing-App-Startup-Time/","used":true,"who":"杨萧玉"},{"_id":"58182900421aa90e799ec24f","createdAt":"2016-11-01T13:32:48.436Z","desc":"Android性能优化典范 - 第5季","images":["http://img.gank.io/f18c07f5-e535-4432-8ba6-8537e80ddd01"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"web","type":"Android","url":"http://hukai.me/android-performance-patterns-season-5/","used":true,"who":"Pan HongJin"},{"_id":"58193781421aa90e6f21b49f","createdAt":"2016-11-02T08:46:57.726Z","desc":"11-2","publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg","used":true,"who":"daimajia"},{"_id":"5819472d421aa90e799ec255","createdAt":"2016-11-02T09:53:49.625Z","desc":"一款可以自由定制外观、手势旋转的android雷达图表lib","images":["http://img.gank.io/17c022c4-e12a-430f-b267-fd521eac5395"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"web","type":"Android","url":"https://github.com/qstumn/RadarChart","used":true,"who":"Rorbin Qiu"},{"_id":"581950b0421aa91376974605","createdAt":"2016-11-02T10:34:24.683Z","desc":"Android App Shortcuts","publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/23357071","used":true,"who":"LHF"},{"_id":"5819510f421aa90e6f21b4a1","createdAt":"2016-11-02T10:35:59.540Z","desc":"Mac Range 选择器，选择区间范围，做的萌萌的","images":["http://img.gank.io/4e40a72e-fe28-45d9-bb1a-2bd05698b37f"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"iOS","url":"https://github.com/matthewreagan/RangeSlider","used":true,"who":"代码家"},{"_id":"5819514a421aa90e799ec257","createdAt":"2016-11-02T10:36:58.623Z","desc":"用 Swift 动态 Render HTML","images":["http://img.gank.io/8a636be3-ab0a-48b0-b9de-25f32e177231"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"iOS","url":"https://github.com/sahandnayebaziz/Hypertext","used":true,"who":"代码家"},{"_id":"58195197421aa91369f959d1","createdAt":"2016-11-02T10:38:15.922Z","desc":"React Native 控制 Touchbar","images":["http://img.gank.io/0691521b-7c94-4fed-9a05-5c732ada3a37"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"iOS","url":"https://github.com/ptmt/react-native-touchbar","used":true,"who":"ptmt"},{"_id":"58195221421aa91376974608","createdAt":"2016-11-02T10:40:33.702Z","desc":"iTerm2 下的一个漂亮的色彩主题😍","images":["http://img.gank.io/4ddb448a-8159-48ad-8231-b6b68fc691e5"],"publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"拓展资源","url":"https://github.com/sindresorhus/iterm2-snazzy","used":true,"who":"sind"},{"_id":"58136806421aa90e799ec22e","createdAt":"2016-10-28T23:00:22.904Z","desc":"实现系统通知拦截功能的 App","images":["http://img.gank.io/1dce0d48-99aa-4d68-83bc-d3f08b68c1c3"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"Android","url":"https://github.com/gavinliu/NotificationBox","used":true,"who":"Jason"},{"_id":"5814888c421aa90e799ec233","createdAt":"2016-10-29T19:31:24.675Z","desc":"滚动播放的公告控件","publishedAt":"2016-11-01T11:46:01.617Z","source":"web","type":"Android","url":"https://github.com/czy1121/noticeview","used":true,"who":"ezy"},{"_id":"5817215a421aa90e799ec245","createdAt":"2016-10-31T18:47:54.791Z","desc":"迄今为止又一款 Android 第三方 Sketch Mirror 软件","images":["http://img.gank.io/41a42b2e-2061-40dc-82de-b5111bed82f6"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"Android","url":"https://github.com/zhihu/mirror","used":true,"who":"mthli"},{"_id":"5817e1fa421aa913769745fe","createdAt":"2016-11-01T08:29:46.640Z","desc":"11-1","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg","used":true,"who":"daimajia"},{"_id":"5817f9fb421aa90e799ec248","createdAt":"2016-11-01T10:12:11.766Z","desc":"论面试的套路，大写的服！","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/2304440777959f68faf9645d72572d77ae45bc","used":true,"who":"lxxself"},{"_id":"5817fd2c421aa91369f959c1","createdAt":"2016-11-01T10:25:48.756Z","desc":"用 TouchID 完成 sudo 输密码操作，对开发者来说太实用了。","images":["http://img.gank.io/316564bf-b8eb-4034-9547-30776c73f2d7"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"瞎推荐","url":"https://github.com/mattrajca/sudo-touchid","used":true,"who":"代码家"},{"_id":"5817fda8421aa91376974600","createdAt":"2016-11-01T10:27:52.803Z","desc":"在 cell 中预览即将会出现的页面内容的前几个，更高效的让用户完成内容的选择。","images":["http://img.gank.io/13218b3f-2a48-4464-b6b0-7bc6d3af2201"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"iOS","url":"https://github.com/robertherdzik/RHPreviewCell","used":true,"who":"代码家"},{"_id":"5817fe42421aa91369f959c3","createdAt":"2016-11-01T10:30:26.407Z","desc":"类似 Wallet 效果的卡片管理组件","images":["http://img.gank.io/53fe7cc3-0140-4f58-9575-ea42bdce310a"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"iOS","url":"https://github.com/rshevchuk/Wallet","used":true,"who":"rshe"},{"_id":"5817ffef421aa90e6f21b493","createdAt":"2016-11-01T10:37:35.724Z","desc":"Mac 上轻量 GIF 录屏小工具 - Kap","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"iOS","url":"https://github.com/wulkano/kap","used":true,"who":"Dear宅学长"},{"_id":"58180ec9421aa90e799ec24d","createdAt":"2016-11-01T11:40:57.96Z","desc":"给用户输入自动加上特定格式","images":["http://img.gank.io/1eba4c3d-8d90-4565-9b37-61047dbe41ba"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"Android","url":"https://github.com/RedMadRobot/input-mask-android","used":true,"who":"代码家"},{"_id":"58180eef421aa90e6f21b494","createdAt":"2016-11-01T11:41:35.943Z","desc":"一款比较完整的 Android 安全管理 App","images":["http://img.gank.io/eb2fbee1-47ee-4ddb-a09f-2d900b05b8fd"],"publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"App","url":"https://github.com/ittianyu/MobileGuard","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 581828c8421aa90e6f21b495
     * createdAt : 2016-11-01T13:31:52.168Z
     * desc : 优化 App 启动时间
     * publishedAt : 2016-11-02T11:49:08.835Z
     * source : web
     * type : iOS
     * url : http://yulingtianxia.com/blog/2016/10/30/Optimizing-App-Startup-Time/
     * used : true
     * who : 杨萧玉
     */

    private List<GankData> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankData> getResults() {
        return results;
    }

    public void setResults(List<GankData> results) {
        this.results = results;
    }


}
