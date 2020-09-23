高效省内存的播放序列帧控件,支持从文件,resource,Assets读取序列帧,内存复用,读取多张只需一张图片内存,流式API,一行代码即可实现序列帧动画。

![image](https://github.com/zengd0/frameanimation/blob/master/screenshot.gif?raw=true)

此框架基于 https://github.com/Mr-wangyong/ImageFrame 改进，增加了以下功能：

1、支持从Assets中获取序列帧

2、支持设置播放次数

3、支持设置动画播放结束后的停留画面

4、支持监听播放结束

5、支持设置最后一帧的停留时间

#### 使用

```
  mV.delayFinishTime = 0;//最后一帧停留时间
  mV.playNull = true;//是否停留最后一帧，true不停留
  mV.startImageFrame(new ImageFrameHandler.AssetsHandlerBuilder("test"
                        , getApplicationContext())
                        .setLoop(true)// 设置是否循环播放
                        .openLruCache(false)// 设置是否开启LRU缓存,如果不循环,建议不开启,如果循环,建议开启,不过多次测试性能相差并不大。ps:在listview中设为false
                        .setFps(24).build());
```

本项目地址:

https://github.com/zengd0/frameanimation

感谢原作者。
