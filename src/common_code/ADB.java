package common_code;

import java.io.*;

import static common_code.Misc.enter;

public class ADB {
    //normal adb command
    public void adb_command(String command1, String command2) {
        //command2 check
        if (command2 == null) {
            command2 = "";
        }

        //main_code
        try {
            ProcessBuilder input_command = new ProcessBuilder("adb", command1 , command2);

            input_command.inheritIO();

            // excute
            Process process = input_command.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // print result
            String line;
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("오류 발생! 프로그램을 종료합니다.");
            enter();
            System.exit(0);
        }
    }

    //adb shell command
    public void adb_shell_command(String command1) {
        try {
            ProcessBuilder input_command = new ProcessBuilder("adb","shell",command1);

            input_command.inheritIO();

            // excute
            Process process = input_command.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // print result
            String line;
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("오류 발생! 프로그램을 종료합니다.");
            System.out.println(e.getMessage());
            enter();
            System.exit(0);
        }
    }

    public void efs_port_open () {
        try {
            // adb shell 실행
            Process adbShellProcess = Runtime.getRuntime().exec("adb shell");

            // adb shell 입력 스트림
            BufferedWriter adbShellInputWriter = new BufferedWriter(new OutputStreamWriter(adbShellProcess.getOutputStream()));

            // su 입력 (root 권한 획득)
            adbShellInputWriter.write("su\n");
            adbShellInputWriter.flush();

            // 명령어 입력
            adbShellInputWriter.write("setprop sys.usb.config diag,serial_cdev,rmnet,adb\n");
            adbShellInputWriter.flush();

            // adb shell 종료
            adbShellInputWriter.write("exit\n");
            adbShellInputWriter.flush();

            adbShellInputWriter.write("exit\n");
            adbShellInputWriter.flush();

            // adb shell 프로세스 대기
            adbShellProcess.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void volte_toggle() {
        try {
            // adb shell 실행
            Process adbShellProcess = Runtime.getRuntime().exec("adb shell");

            // adb shell 입력 스트림
            BufferedWriter adbShellInputWriter = new BufferedWriter(new OutputStreamWriter(adbShellProcess.getOutputStream()));

            // su 입력 (root 권한 획득)
            adbShellInputWriter.write("su\n");
            adbShellInputWriter.flush();

            // 명령어 입력
            adbShellInputWriter.write("setprop persist.dbg.ims_avail_ovr 1\n");
            adbShellInputWriter.flush();

            adbShellInputWriter.write("setprop persist.dbg.volte_avail_ovr 1\n");
            adbShellInputWriter.flush();

            adbShellInputWriter.write("setprop persist.dbg.vt_avail_ovr 1\n");
            adbShellInputWriter.flush();

            adbShellInputWriter.write("setprop persist.dbg.wfc_avail_ovr 1\n");
            adbShellInputWriter.flush();

            // adb shell 종료
            adbShellInputWriter.write("exit\n");
            adbShellInputWriter.flush();

            adbShellInputWriter.write("exit\n");
            adbShellInputWriter.flush();

            // adb shell 프로세스 대기
            adbShellProcess.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
