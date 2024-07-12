//기기 정보 출력

package act_code;

import common_code.ADB;

import static common_code.Misc.enter;

public class Print_device_info {
    public static void print_device_info() {
        //instance
        ADB adb = new ADB();

        //main_code
        System.out.print("모델명:");
        adb.adb_shell_command("getprop ro.product.name");
        System.out.print("핑거프린트 값:");
        adb.adb_shell_command("getprop ro.build.fingerprint");
        Battery_check.battery();

        enter();
    }
}