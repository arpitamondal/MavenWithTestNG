package org.testng.driverinit;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.utilities.Logg;

public class ChromeWebDriver extends IDriver {
	private static Logger log = Logg.createLogger();

	protected void setPath() {
		System.setProperty("webdriver.chrome.driver",
				"src/main/resources/org/drivers/chromedriver.exe");
	}

	public WebDriver getdriver(Capabilities capabilities) {
		setPath();
		log.info("Intantiating/Launching the Chrome Browser");
		return new ChromeDriver(capabilities);
	}

	public Capabilities getCapabilities(Browser browser) {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName(browser.name);
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setVersion(browser.version);
		capabilities.setJavascriptEnabled(true);
		return capabilities;
	}

	public WebDriver getRemoteDriver(String gridIP,Capabilities capabilities) {
		WebDriver driver = null;
		log.info("Intantiating/Launching the Internet Explorer Browser on Remote Node");
		try {
			driver = new RemoteWebDriver(
					new URL("http://"+gridIP+":4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}