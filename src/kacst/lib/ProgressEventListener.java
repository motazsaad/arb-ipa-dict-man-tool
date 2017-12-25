/*
 * ProgressEventListener.java
 *
 * Created on February 6, 2006, 6:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package kacst.lib;

/**
 * @author Ali
 */
public interface ProgressEventListener {
    void actionPerformed(String message, int value);

    void setMaximum(int value);
}
