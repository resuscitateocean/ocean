package com.ocean.instance;


//租债器
public class Lease<T> {

	T object;

	//心跳连接的过期时间
	public static final int DEFULT_TIME_LIMIT=90;



	private long lastActivityTime;
	//生命周期  租期
	private  long  timeLimit;

	//被剔除的时间
	private  long expelTime;

	public Lease(T object, long lastActivityTime, long timeLimit) {
		this.object = object;
		this.lastActivityTime = lastActivityTime;
		this.timeLimit = timeLimit;
	}

	//续约
	public void renew(){
		this.lastActivityTime = System.currentTimeMillis()+this.timeLimit;
	}

	public  boolean isOverdue(){
		return expelTime>0||System.currentTimeMillis()>(lastActivityTime+timeLimit);
	}




	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public long getLastActivityTime() {
		return lastActivityTime;
	}

	public void setLastActivityTime(long lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public long getExpelTime() {
		return expelTime;
	}

	public void setExpelTime(long expelTime) {
		this.expelTime = expelTime;
	}
}