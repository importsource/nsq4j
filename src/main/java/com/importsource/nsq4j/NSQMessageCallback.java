/**
 *
 */
package com.importsource.nsq4j;


/**
 * @author Dustin Norlander
 * @created Jan 15, 2013
 *
 */
public interface NSQMessageCallback {

    public void message(Message message);

    public void error(Exception x);
}
