package com.game.rania.utils;

public class Condition {
	
	private Object  waitObject = new Object();
	private boolean ready = false;
	
	public Condition(){
	}

	public void signalWait() throws InterruptedException{
		while (!ready){
			synchronized (waitObject){
				waitObject.wait();
			}
		}
		ready = false;
	}
	
	public void signal(){
		ready = true;
		synchronized (waitObject) {
			waitObject.notify();
		}
	}
	
	public void signalAll(){
		ready = true;
		synchronized (waitObject) {
			waitObject.notifyAll();
		}
	}
}
