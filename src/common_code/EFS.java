package common_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static common_code.Misc.enter;

public class EFS {
    //write file
    public void EFS_writefile(String efstools_folder_path, String mcfg_file_path, String target_folder){
        //main_code
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(efstools_folder_path,"writeFile","-i",mcfg_file_path,"-o",target_folder);

            processBuilder.inheritIO();

            // excute
            Process process = processBuilder.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //print result
            String line;
            while ((line = resultReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //upload folder EFS
    public void EFS_upload_folder(String efstools_path, String myEFS_folder){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(efstools_path,"uploadDirectory","-i",myEFS_folder,"-o","/","-v");

            processBuilder.inheritIO();

            // excute
            Process process = processBuilder.start();

            // wait until end
            process.waitFor();

            //execution result
            BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //print result
            String line;
            while ((line = resultReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
