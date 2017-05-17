当Android手机睡眠之后（即黑屏状态），发出的NMEA频率大大降低了，大概40秒才会发出一次数据。
        解决的方法是，打开App时，拿到睡眠锁，App退出时，释放睡眠锁。代码如下：
public class WakeLockManager {
    public final static String TAG = "WakeLockManager";
    private static WakeLockManager instance = null;
    private PowerManager.WakeLock wakeLock = null;
    private WakeLockManager(){
    }
 
    public static WakeLockManager getInstance() {
        if (instance == null) {
            synchronized (WakeLockManager.class) {
                if (instance == null) {
                    instance = new WakeLockManager();
                }
            }
        }
        return instance;
    }
 
    /**
     * 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
     */
    public void acquire(Context context) {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE, context.getClass()
                    .getCanonicalName());
            wakeLock.acquire();
            L.i(TAG + "call acquireWakeLock");
        }
    }
 
    // 释放设备电源锁
    public void release() {
        if (null != wakeLock && wakeLock.isHeld()) {
            Log.i(TAG, "call releaseWakeLock");
            wakeLock.release();
            wakeLock = null;
        }
    }
}
