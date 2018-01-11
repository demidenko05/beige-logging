package org.beigesoft.log.acl;

/*
 * Copyright (c) 2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import org.apache.commons.logging.Log;

import org.beigesoft.log.ILogger;

/**
 * <p>Adapter beige-logging to Apache Common Logging.
 * </p>
 *
 * @author Yury Demidenko
 */
public class LoggerAclAdapter implements Log {

  /**
   * <p>Logger delegate.</p>
   **/
  private ILogger logger;

  /**
   * Logs a message with debug log level.
   *
   * @param message log this message
   */
  @Override
  public final void debug(final Object msg) {
    if (this.logger.getIsShowDebugMessages()) {
      String sMsg = msg == null ? "???" : msg.toString();
      this.logger.debug(null, getClass(), sMsg);
    }
  }

  /**
   * Logs an error with debug log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void debug(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "DEBUG EXCEPTION???" : msg.toString();
    this.logger.warn(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with error log level.
   *
   * @param message log this message
   */
  @Override
  public final void error(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.error(null, getClass(), sMsg);
  }

  /**
   * Logs an error with error log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void error(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.error(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with fatal log level.
   *
   * @param message log this message
   */
  @Override
  public final void fatal(final Object msg) {
    String sMsg = msg == null ? "FATAL???" : msg.toString();
    this.logger.error(null, getClass(), sMsg);
  }

  /**
   * Logs an error with fatal log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void fatal(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "FATAL???" : msg.toString();
    this.logger.error(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with info log level.
   *
   * @param message log this message
   */
  @Override
  public final void info(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.info(null, getClass(), sMsg);
  }

  /**
   * Logs an error with info log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void info(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.warn(null, getClass(), sMsg, t);
  }

  /**
   * Is debug logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than debug.
   *
   * @return true if debug is enabled in the underlying logger.
   */
  @Override
  public final boolean isDebugEnabled() {
    return this.logger.getIsShowDebugMessages();
  }

  /**
   * Is error logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than error.
   *
   * @return true if error is enabled in the underlying logger.
   */
  @Override
  public final boolean isErrorEnabled() {
    return true;
  }

  /**
   * Is fatal logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than fatal.
   *
   * @return true if fatal is enabled in the underlying logger.
   */
  @Override
  public final boolean isFatalEnabled() {
    return true;
  }

  /**
   * Is info logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than info.
   *
   * @return true if info is enabled in the underlying logger.
   */
  @Override
  public final boolean isInfoEnabled() {
    return true;
  }

  /**
   * Is trace logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than trace.
   *
   * @return true if trace is enabled in the underlying logger.
   */
  @Override
  public final boolean isTraceEnabled() {
    return this.logger.getIsShowDebugMessages();
  }

  /**
   * Is warn logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than warn.
   *
   * @return true if warn is enabled in the underlying logger.
   */
  @Override
  public final boolean isWarnEnabled() {
    return true;
  }

  /**
   * Logs a message with trace log level.
   *
   * @param message log this message
   */
  @Override
  public final void trace(final Object msg) {
    String sMsg = msg == null ? "TRACE???" : msg.toString();
    this.logger.debug(null, getClass(), sMsg);
  }

  /**
   * Logs an error with trace log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void trace(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "TRACE EXCEPTION???" : msg.toString();
    this.logger.warn(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with warn log level.
   *
   * @param message log this message
   */
  @Override
  public final void warn(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.warn(null, getClass(), sMsg);
  }

  /**
   * Logs an error with warn log level.
   *
   * @param message log this message
   * @param t log this cause
   */
  @Override
  public final void warn(final Object msg, final Throwable t) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.logger.warn(null, getClass(), sMsg, t);
  }


  //Simple getters and setters:
  /**
   * <p>Getter for logger.</p>
   * @return ILogger
   **/
  public final ILogger getLogger() {
    return this.logger;
  }

  /**
   * <p>Setter for logger.</p>
   * @param pLogger reference
   **/
  public final void setLogger(final ILogger pLogger) {
    this.logger = pLogger;
  }
}
