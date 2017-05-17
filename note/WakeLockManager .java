��Android�ֻ�˯��֮�󣨼�����״̬����������NMEAƵ�ʴ�󽵵��ˣ����40��Żᷢ��һ�����ݡ�
        ����ķ����ǣ���Appʱ���õ�˯������App�˳�ʱ���ͷ�˯�������������£�
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
     * ��ȡ��Դ�������ָ÷�������ĻϨ��ʱ��Ȼ��ȡCPUʱ����������
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
 
    // �ͷ��豸��Դ��
    public void release() {
        if (null != wakeLock && wakeLock.isHeld()) {
            Log.i(TAG, "call releaseWakeLock");
            wakeLock.release();
            wakeLock = null;
        }
    }
}
