package common_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static common_code.Misc.enter;

//FASTBOOT command
public class FASTBOOT {
    public void fastboot_command(String command1, String command2, String command3) {

        //command check
        if (command2 != null && command3 == null) {
            command3 = "";
        } else if (command2 == null) {
            command2 = "";
            command3 = "";
        }

        //main_code
        try {
            ProcessBuilder input_command = new ProcessBuilder("fastboot", command1, command2, command3);

            input_command.inheritIO();

            // excute
            Process process = input_command.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // print result
            String line;
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    //FASTBOOT REBOOT
    public void fastboot_reboot() {
        //main_code
        try {
            ProcessBuilder input_command = new ProcessBuilder("fastboot", "reboot");

            input_command.inheritIO();

            // excute
            Process process = input_command.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // print result
            String line;
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
