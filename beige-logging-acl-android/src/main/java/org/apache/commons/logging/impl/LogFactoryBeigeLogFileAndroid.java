/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.logging.impl;

import java.util.Hashtable;
import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogConfigurationException;

import org.beigesoft.log.LoggerFile;
import org.beigesoft.log.LoggerAclAdapter;
import org.beigesoft.log.FillerFileLogProperties;
import org.beigesoft.log.IDebugPrinter;
import org.beigesoft.log.DebugPrinterAndroid;

import android.os.Environment;

/**
 * <p>Concrete subclass of {@link LogFactory}.
 * Returns only file logger for all classes.</p>
 *
 */
public class LogFactoryBeigeLogFileAndroid extends LogFactory {

  private LoggerAclAdapter logger;

  private FillerFileLogProperties fillerFileLogProperties;

  /**
   * <p>Debug printer.</p>
   **/
  private IDebugPrinter debugPrinter;

  /**
   * Configuration attributes.
   */
  protected Hashtable attributes = new Hashtable();

  /**
   * Convenience method to derive a name from the specified class and
   * call <code>getInstance(String)</code> with it.
   *
   * @param clazz Class for which a suitable Log name will be derived
   *
   * @exception LogConfigurationException if a suitable <code>Log</code>
   *  instance cannot be returned
   */
  public Log getInstance(Class clazz) throws LogConfigurationException {
    return lazyGetLogger();
  }

  /**
   * <p>Construct (if necessary) and return a <code>Log</code> instance,
   * using the factory's current set of configuration attributes.</p>
   *
   * <p><strong>NOTE</strong> - Depending upon the implementation of
   * the <code>LogFactory</code> you are using, the <code>Log</code>
   * instance you are returned may or may not be local to the current
   * application, and may or may not be returned again on a subsequent
   * call with the same name argument.</p>
   *
   * @param name Logical name of the <code>Log</code> instance to be
   *  returned (the meaning of this name is only known to the underlying
   *  logging implementation that is being wrapped)
   *
   * @exception LogConfigurationException if a suitable <code>Log</code>
   *  instance cannot be returned
   */
  public Log getInstance(String name) throws LogConfigurationException {
    return lazyGetLogger();
  }

  /**
   * Release any internal references to previously created
   * {@link org.apache.commons.logging.Log}
   * instances returned by this factory.  This is useful in environments
   * like servlet containers, which implement application reloading by
   * throwing away a ClassLoader.  Dangling references to objects in that
   * class loader would prevent garbage collection.
   */
  public void release() {
    // stub
  }

  /**
   * Return the configuration attribute with the specified name (if any),
   * or <code>null</code> if there is no such attribute.
   *
   * @param name Name of the attribute to return
   */
  public Object getAttribute(String name) {
    return attributes.get(name);
  }

  /**
   * Return an array containing the names of all currently defined
   * configuration attributes.  If there are no such attributes, a zero
   * length array is returned.
   */
  public String[] getAttributeNames() {
    return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
  }

  /**
   * Remove any configuration attribute associated with the specified name.
   * If there is no such attribute, no action is taken.
   *
   * @param name Name of the attribute to remove
   */
  public void removeAttribute(String name) {
    attributes.remove(name);
  }

  /**
   * Set the configuration attribute with the specified name.  Calling
   * this with a <code>null</code> value is equivalent to calling
   * <code>removeAttribute(name)</code>.
   * <p>
   * This method can be used to set logging configuration programmatically
   * rather than via system properties. It can also be used in code running
   * within a container (such as a webapp) to configure behaviour on a
   * per-component level instead of globally as system properties would do.
   * To use this method instead of a system property, call
   * <pre>
   * LogFactory.getFactory().setAttribute(...)
   * </pre>
   * This must be done before the first Log object is created; configuration
   * changes after that point will be ignored.
   * <p>
   * This method is also called automatically if LogFactory detects a
   * commons-logging.properties file; every entry in that file is set
   * automatically as an attribute here.
   *
   * @param name Name of the attribute to set
   * @param value Value of the attribute to set, or <code>null</code>
   *  to remove any setting for this attribute
   */
  public void setAttribute(String name, Object value) {
    if (value == null) {
        attributes.remove(name);
    } else {
        attributes.put(name, value);
    }
  }

  /**
   * <p>Only constructor</p>
   **/
  public final LoggerAclAdapter lazyGetLogger() {
    if (this.logger == null) {
      synchronized (this) {
        if (this.logger == null) {
          LoggerFile log = new LoggerFile();
          if (this.fillerFileLogProperties == null) {
            this.fillerFileLogProperties = new FillerFileLogProperties();
          }
          if (this.debugPrinter == null) {
            this.debugPrinter = new DebugPrinterAndroid();
          }
          this.fillerFileLogProperties.setLogDir(Environment.getExternalStorageDirectory()
            .getAbsolutePath());
          this.fillerFileLogProperties.setDebugPrinter(this.debugPrinter);
          this.fillerFileLogProperties.fillProperties(log, "acl-all");
          LoggerAclAdapter loggerAclAdapter = new LoggerAclAdapter();
          loggerAclAdapter.setLogger(log);
          this.logger = loggerAclAdapter;
        }
      }
    }
    return this.logger;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for fillerFileLogProperties.</p>
   * @return FillerFileLogProperties
   **/
  public final synchronized FillerFileLogProperties getFillerFileLogProperties() {
    return this.fillerFileLogProperties;
  }

  /**
   * <p>Setter for fillerFileLogProperties.</p>
   * @param pFillerFileLogProperties reference
   **/
  public final synchronized void setFillerFileLogProperties(
    final FillerFileLogProperties pFillerFileLogProperties) {
    this.fillerFileLogProperties = pFillerFileLogProperties;
  }

  /**
   * <p>Getter for debugPrinter.</p>
   * @return IDebugPrinter
   **/
  public final synchronized IDebugPrinter getDebugPrinter() {
    return this.debugPrinter;
  }

  /**
   * <p>Setter for debugPrinter.</p>
   * @param pDebugPrinter reference
   **/
  public final synchronized void setDebugPrinter(final IDebugPrinter pDebugPrinter) {
    this.debugPrinter = pDebugPrinter;
  }
}
