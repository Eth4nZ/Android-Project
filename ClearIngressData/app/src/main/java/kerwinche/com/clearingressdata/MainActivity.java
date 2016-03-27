package kerwinche.com.clearingressdata;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private static final String CHARSET_NAME = "UTF-8";
    private static final String PACKAGE_NAME = "com.nianticproject.ingress";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String msg=clearApplicationCache(PACKAGE_NAME)?"Clear cache success!":"Clear cache fail!";
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

        //kill process
        new Timer().schedule(new TimerTask() {
            public void run() {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }, 500);


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean clearApplicationCache(String packageName) {

        String cmd = "pm clear "+packageName;
        boolean result= false;
        ProcessBuilder pb = new ProcessBuilder().redirectErrorStream(true).command("su");
        Process p = null;
        try {
            p = pb.start();
            StreamReader stdoutReader = new StreamReader(p.getInputStream(), CHARSET_NAME);
            stdoutReader.start();

            OutputStream out = p.getOutputStream();
            out.write((cmd + "\n").getBytes(CHARSET_NAME));
            out.write(("exit" + "\n").getBytes(CHARSET_NAME));
            out.flush();

            p.waitFor();
            String msg = stdoutReader.getResult().trim();

            result=msg.equals("Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}