package com.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CookQueue {
	
	@Id
	private int queueId;
	private Date pickup_date;
	@Temporal(TemporalType.TIME)
	private Date pickup_time;
	@Temporal(TemporalType.TIME)
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time;
	private int cook_id;
	
	public CookQueue() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CookQueue(int queueId, Date pickup_date, Date pickup_time, Date start_time, Date end_time, int cook_id) {
		super();
		this.queueId = queueId;
		this.pickup_date = pickup_date;
		this.pickup_time = pickup_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.cook_id = cook_id;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public Date getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(Date pickup_date) {
		this.pickup_date = pickup_date;
	}
	public Date getPickup_time() {
		return pickup_time;
	}
	public void setPickup_time(Date pickup_time) {
		this.pickup_time = pickup_time;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getCook_id() {
		return cook_id;
	}
	public void setCook_id(int cook_id) {
		this.cook_id = cook_id;
	}

	@Override
	public String toString() {
		return "CookQueue [queueId=" + queueId + ", pickup_date=" + pickup_date + ", pickup_time=" + pickup_time
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", cook_id=" + cook_id + "]";
	}
	
	
	

}
