/**
 * Created on 2006-5-18
 * Created by Sunteya
 */
package com.sunteya.commons.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * 提供一个同步的监听容器
 *
 * @author 诸南敏
 */
public class EventListenerSupport<L extends EventListener> implements Serializable {

	private static final long serialVersionUID = -3659974570530618701L;
	private transient EventListenerAggregate<L> listeners;
	private Method addMethod;

	public synchronized void addEventListener(L listener) {
		if (listener == null) {
			return;
		}
		if (this.listeners == null) {
			this.listeners = new EventListenerAggregate<L>();
			for (Method method : this.listeners.getClass().getMethods()) {
				if (method.getName().equals("add")) {
					this.addMethod = method;
					break;
				}
			}
		}
		this.listeners.add(listener);
	}

	public synchronized void removeEventListener(L listener) {
		if (listener == null) {
			return;
		}

		if (this.listeners == null) {
			return;
		}
		this.listeners.remove(listener);
	}


    public synchronized List<L> getEventListeners() {
    	if (this.listeners != null) {
    		return this.listeners.getListenersInternal();
    	}
    	return new ArrayList<L>();
    }



	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();

		if (this.listeners != null) {
			List<L> list = this.listeners.getListenersCopy();

			for (L l : list) {
				if (l instanceof Serializable) {
					s.writeObject(l);
				}
			}
		}
		s.writeObject(null);
	}

	private void readObject(ObjectInputStream s) throws ClassNotFoundException,
			IOException {
		s.defaultReadObject();

		Object listenerOrNull;
		while (null != (listenerOrNull = s.readObject())) {
			try {
				this.addMethod.invoke(this.listeners, new Object[] { listenerOrNull });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
