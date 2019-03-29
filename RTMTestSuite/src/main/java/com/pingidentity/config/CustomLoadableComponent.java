package com.pingidentity.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.pingidentity.AbstractBase;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class CustomLoadableComponent <T extends CustomLoadableComponent<T>> extends AbstractBase {
	
	private static final int LOAD_TIMEOUT = 30;
    private static final int REFRESH_RATE = 2;

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            isLoaded();
            return (T) this;
        } catch (Error e) {
            // This is the extra line of code
            log.info("Error encountered during page load: " + e.getMessage());
            load();
        }

        isLoaded();

        return (T) this;
    }
	
    protected abstract void load();

    protected abstract void isLoaded() throws Error;

    protected void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        Wait wait = new FluentWait(getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
    }
    
}