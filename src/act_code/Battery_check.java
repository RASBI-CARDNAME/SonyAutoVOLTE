package act_code;

import common_code.ADB;

import static common_code.Misc.enter;

public class Battery_check {
    public static void battery() {
        //instance
        ADB adb = new ADB();

        //main_code
        adb.battery_check();
    }
}
