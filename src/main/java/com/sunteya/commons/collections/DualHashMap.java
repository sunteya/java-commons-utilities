/**
 * Created on 2006-5-19
 * Created by Sunteya
 */
package com.sunteya.commons.collections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 双向HashMap
 * 由于Commons-Collections不支持泛型, 特写此类另时代替
 *
 * @author Sunteya
 *
 * @param <K>
 * @param <V>
 */
public class DualHashMap<K, V> implements Map<K, V>, Serializable, Cloneable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6893642042233207788L;

	private transient HashMap<K, V> normalMap;
	private transient HashMap<V, K> reverseMap;

	public DualHashMap() {
		this.normalMap = new HashMap<K, V>();
		this.reverseMap = new HashMap<V, K>();
	}

	public DualHashMap(int initialCapacity, float loadFactor) {
		this.normalMap = new HashMap<K, V>(initialCapacity, loadFactor);
		this.reverseMap = new HashMap<V, K>(initialCapacity, loadFactor);
	}

	public DualHashMap(int initialCapacity) {
		this.normalMap = new HashMap<K, V>(initialCapacity);
		this.reverseMap = new HashMap<V, K>(initialCapacity);
	}

	public DualHashMap(Map<? extends K, ? extends V> m) {
		this.normalMap = new HashMap<K, V>(m);
		this.reverseMap = new HashMap<V, K>();
		putAllToReverse(m);
	}

	private void putAllToReverse(Map<? extends K, ? extends V> m) {
		Set<? extends K> keys = m.keySet();
		for (K key : keys) {
			V value = m.get(key);
			this.reverseMap.put(value, key);
		}
	}

	public void clear() {
		this.normalMap.clear();
		this.reverseMap.clear();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object clone() {
		DualHashMap<K, V> clone = new DualHashMap<K, V>();
		clone.normalMap = (HashMap<K, V>) this.normalMap.clone();
		clone.reverseMap = (HashMap<V, K>) this.reverseMap.clone();
		return clone;
	}

	public boolean containsKey(Object key) {
		return this.normalMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.reverseMap.containsKey(value);
	}

	public Set<Entry<K, V>> entrySet() {
		return this.normalMap.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return this.normalMap.equals(o);
	}

	public V get(Object key) {
		return this.normalMap.get(key);
	}

	public K getKey(Object value) {
		return this.reverseMap.get(value);
	}

	@Override
	public int hashCode() {
		return this.normalMap.hashCode();
	}

	public boolean isEmpty() {
		return this.normalMap.isEmpty();
	}

	public Set<K> keySet() {
		return this.normalMap.keySet();
	}

	public Set<V> valueSet() {
		return this.reverseMap.keySet();
	}

	public V put(K key, V value) {
		V answer = this.normalMap.put(key, value);
		this.reverseMap.put(value, key);

		return answer;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		this.normalMap.putAll(m);
		putAllToReverse(m);
	}

	public V remove(Object key) {
		V value = this.normalMap.remove(key);
		this.reverseMap.remove(value);

		return value;
	}

	public K removeValue(Object value) {
		K key = this.reverseMap.remove(value);
		this.normalMap.remove(key);

		return key;
	}

	public int size() {
		return this.normalMap.size();
	}

	@Override
	public String toString() {
		return this.normalMap.toString();
	}

	public Collection<V> values() {
		return this.normalMap.values();
	}

	public Collection<K> keys() {
		return this.reverseMap.values();
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeObject(this.normalMap);
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream s) throws ClassNotFoundException,
			IOException {
		s.defaultReadObject();
		this.normalMap = (HashMap<K, V>) s.readObject();
		this.reverseMap = new HashMap<V, K>();
		putAllToReverse(this.normalMap);
	}
}
