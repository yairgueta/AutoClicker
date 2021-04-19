package model;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Still not working!
 * TODO: make it work by finding a way to find information of another windows!
 */
public class WindowsModel {
    public static void main(String[] args) throws AWTException {
        Scanner scanner = new Scanner(System.in);
        WindowsModel wm = new WindowsModel();

        while (true){
            System.out.println("Filter or Choose index:");
            String input = scanner.nextLine();

            if (input.matches("\\d+")){
                int i = Integer.parseInt(input);
                wm.selectWindow(i);
                System.out.println(wm.getSelectedApp());
            }else{
                if (input.equals("q")) break;
                wm.filterApps(input);
                AtomicInteger i = new AtomicInteger();
                wm.getApplicationsNames().forEach(s -> System.out.printf("%d: %-10s", i.getAndIncrement(), s));
                System.out.println();
            }
        }
    }

    private enum OS{WINDOWS, MAC}

    private final List<String> apps;
    private Integer selectedAppIndex;
    private OS os;
    private Pattern appNameRegex;

    public WindowsModel(){
        this.apps = new ArrayList<>();

        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("mac")) {
            this.os = OS.MAC;
            appNameRegex = Pattern.compile(".*/(.*)$");
        }
        else if(osName.contains("win")) {
            this.os = OS.WINDOWS;
            appNameRegex = Pattern.compile("(.*)");
        }
    }

    private String getAppName(String fullName){
        Matcher m = appNameRegex.matcher(fullName);
        if (m.find()) return m.group(1);
        return "";
    }

    public List<String> getApplicationsNames(){
        return apps.stream().map(this::getAppName).collect(Collectors.toList());
    }

    public void selectWindow(String name) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("selectWindow with name has not been implemented!");
    }

    public void selectWindow(int i){
        selectedAppIndex = i;
    }

    public String getSelectedApp(){
        return this.apps.get(this.selectedAppIndex);
    }

    public String getSelectedAppName(){
        return this.getAppName(this.getSelectedApp());
    }

    public int getX(){
        return 0;
    }

    public int getY(){
        return 0;
    }

    public void filterApps(String filter){
        findAllApps();
        apps.removeIf(s -> !getAppName(s).toLowerCase().contains(filter.toLowerCase()));
    }

    private void findAllApps() {
        apps.clear();
        String execCommand;
        String appFilter;
        switch (this.os){
            case MAC -> {
                execCommand = "ps -e";
                appFilter = ".*/Applications/.{0,100}";
            }
            case WINDOWS -> {
                execCommand = System.getenv("windir") +"\\system32\\"+"tasklist.exe";
                appFilter = ".*";
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.os);
        }
        Pattern pattern = Pattern.compile(appFilter);
        try {
            String line;
            Process p = Runtime.getRuntime().exec(execCommand);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (pattern.matcher(line).matches())
                   apps.add(line);
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

}
