package com.bitpanda.homework.automation.driver;

import org.openqa.selenium.Platform;

public class DriversUtils {

    static public void initDrivers(Platform platform) {
        String familyOS = platform.family().name().toLowerCase();
        String extension = (familyOS.equals(Platform.WINDOWS.name().toLowerCase())) ? ".exe" : "";
        System.setProperty("webdriver.chrome.driver", DriverManager.class.getResource("/files/" + familyOS + "/chromedriver" + extension).getPath());
        System.setProperty("webdriver.gecko.driver", DriverManager.class.getResource("/files/" + familyOS + "/geckodriver" + extension).getPath());
        if (familyOS.equals(Platform.MAC.name().toLowerCase()))
            System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
        else if (familyOS.equals(Platform.WINDOWS.name().toLowerCase())) {
            // I am assuming End User installs firefox with Custom option
            // if not, user should set an environment variable having the installed firefox path value
            // then we extract ( if there is one, if not Throw RunTimeException)
            if (System.getProperty("os.arch").contains("64")) {
                System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            } else if (System.getProperty("os.arch").contains("x86")) {
                System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            }
        }
        //System.setProperty("webdriver.ie.driver", "${/path/to/}IEDriverServer_2.9.32.exe");
    }
}
