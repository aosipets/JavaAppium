package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iOSNavigationUI;
import lib.ui.mobile_web.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory
{
    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        }else if (Platform.getInstance().isIos()){
            return new iOSNavigationUI(driver);
        }else{
            return new MWNavigationUI(driver);
        }
    }
}
