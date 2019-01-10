/*
 * Sample test class
 */
package com.pingidentity;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class TestRTMLogin
{
    //Tests that the login page is loaded
    @Test
    public void testLoginPageLoaded()
    {
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.rememberthemilk.com/app/");
        assertTrue(driver.getTitle().equals("Remember The Milk - Login"));
        driver.quit();
    }
}
