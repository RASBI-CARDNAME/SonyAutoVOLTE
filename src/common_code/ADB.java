//ADB 커맨드

package common_code;

import java.io.*;

import static common_code.Misc.enter;

public class ADB {
    //일반 ADB 커맨드
    public void adb_command(String command1, String command2) {
        //커맨드 확인해서 미사용 필드는 null로
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

    //adb shell 일반 커맨드용
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

    //EFS 포트 개방 => adb shell 라인이 여러줄이라 따로 구현
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

    //volte 메뉴 활성화  => adb shell 라인이 여러줄이라 따로 구현
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

    public void battery_check() {
        //변수
        final String level = "level";
        final String Charge_counter = "Charge counter";

        String line;
        String current_level = "1";
        String Charge = "1";

        double battery_percent = 0.0;
        double charge_value = 0.0;

        try {
            ProcessBuilder input_command = new ProcessBuilder("adb","shell","dumpsys battery");

            // excute
            Process process = input_command.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //값 추출
            while ((line = result.readLine()) != null) {

                if (line.contains(level)) {
                    battery_percent = Double.valueOf(line.replaceAll("[^0-9]", ""));
                } else if (line.contains(Charge_counter)) {
                    charge_value = Double.valueOf(line.replaceAll("[^0-9]", ""));
                }
            }

            //수명 계산
            System.out.println("배터리 수명(신품 대비):"+(int)((charge_value/1000)/(battery_percent/100)));

        } catch (Exception e) {
            System.out.println("오류 발생! 프로그램을 종료합니다.");
            System.out.println(e.getMessage());
            enter();
            System.exit(0);
        }
    }
}
