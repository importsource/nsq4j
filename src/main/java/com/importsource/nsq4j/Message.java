/**
 * 
 */
package com.importsource.nsq4j;

import java.util.Date;

/**
 * message interface
 * @author Hezf
 *
 */
public interface Message {
	 public void finished();
	 public void requeue(int timeoutMillis) ;
	 public Connection getConnection();
	 public byte[] getId();
	 public int getAttempts();
	 public Date getTimestamp();
	 public byte[] getMessage();
}

