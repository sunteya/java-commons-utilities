/**
 * Created on 2006-5-19
 * Created by Sunteya
 */
package com.sunteya.commons.util;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * 事件容器
 * @author 诸南敏
 */
public class EventListenerAggregate<L extends EventListener> {
	private ArrayList<L> listenerList;

	public EventListenerAggregate() {
		this.listenerList = new ArrayList<L>();
	}

	public synchronized void add(L listener) {
		this.listenerList.add(listener);
	}

	@SuppressWarnings("unchecked")
	public synchronized List<L> getListenersCopy() {
		return (List<L>) this.listenerList.clone();
	}

	public synchronized List<L> getListenersInternal() {
		return this.listenerList;
	}

	public synchronized boolean isEmpty() {
		return this.listenerList.isEmpty();
	}

	public synchronized boolean remove(L listener) {
		return this.listenerList.remove(listener);
	}

	public synchronized int size() {
		return this.listenerList.size();
	}
}
