package com.pingidentity.pages;

public abstract class AbstractSynchronousPage extends AbstractBasePage {

	public AbstractSynchronousPage() {
		super();
		
		try {
			waitPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void waitPageLoad();
}
