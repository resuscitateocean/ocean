package com.ocean.instance;


import lombok.Data;

//租债器
@Data
public class Lease<T> {


	//心跳连接的过期时间
	public static final int DEFULT_TIME_LIMIT=90;

	T object;
	//最后活跃时间
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
	//是否过期
	public  boolean isOverdue(){
		return expelTime>0||System.currentTimeMillis()>(lastActivityTime+timeLimit);
	}


}