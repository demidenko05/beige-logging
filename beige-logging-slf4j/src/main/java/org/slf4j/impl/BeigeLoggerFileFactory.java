/*
 * Copyright (c) 2004-2013 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import org.beigesoft.log.LoggerFile;
import org.beigesoft.log.FillerFileLogProperties;
import org.beigesoft.log.IDebugPrinter;
import org.beigesoft.log.DebugPrinterConsole;

/**
 * <p>BeigeLoggerFileFactory is an implementation of {@link ILoggerFactory} returning
 * the appropriately named {@link BeigeLoggerFactory} instance.
 * There is only LoggerFile for all classes that use SLS4J.
 * LoggerFile uses name "slf4j-all" as log file.
 * It expects "slsfj-all.properties" file for customize logger.
 * </p>
 *
 * @author Yury Demidenko
 */
class BeigeLoggerFileFactory implements ILoggerFactory {

  private Logger logger;

  private FillerFileLogProperties fillerFileLogProperties;

  /**
   * <p>Debug printer.</p>
   **/
  private IDebugPrinter debugPrinter;

  /**
   * Return an appropriate {@link BeigeLoggerAdapter} instance by name.
   */
  @Override
  public final Logger getLogger(final String name) {
    if (this.logger == null) {
      synchronized (this) {
        if (this.logger == null) {
          BeigeLoggerAdapter newInstance = new BeigeLoggerAdapter(getClass().getSimpleName());
          LoggerFile log = new LoggerFile();
          if (this.fillerFileLogProperties == null) {
            this.fillerFileLogProperties = new FillerFileLogProperties();
          }
          if (this.debugPrinter == null) {
            this.debugPrinter = new DebugPrinterConsole();
          }
          this.fillerFileLogProperties.setDebugPrinter(this.debugPrinter);
          this.fillerFileLogProperties.fillProperties(log, "slf4j-all");
          newInstance.setLogger(log);
          this.logger = newInstance; // finally assign fully initialized bean 
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
  public final synchronized FillerFileLogProperties
    getFillerFileLogProperties() {
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
  public final synchronized void setDebugPrinter(
    final IDebugPrinter pDebugPrinter) {
    this.debugPrinter = pDebugPrinter;
  }
}
