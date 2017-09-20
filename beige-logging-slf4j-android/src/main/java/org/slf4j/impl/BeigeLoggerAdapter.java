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

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import org.beigesoft.log.ILogger;

/**
 * <p>Beige-logger adapter.</p>
 *
 * @author Yury Demidenko
 */
public class BeigeLoggerAdapter extends MarkerIgnoringBase {
  private static final long serialVersionUID = -1227274521526687937L;

  private ILogger logger;

  /**
   * Package access allows only {@link BeigeLoggerFactory} to instantiate
   * SimpleLogger instances.
   */
  BeigeLoggerAdapter(String tag) {
      this.name = tag;
  }

  /**
   * Is this logger instance enabled for the VERBOSE level?
   *
   * @return True if this Logger is enabled for level VERBOSE, false otherwise.
   */
  public boolean isTraceEnabled() {
      return true;
  }

  /**
   * Log a message object at level VERBOSE.
   *
   * @param msg
   *          - the message object to be logged
   */
  public void trace(String msg) {
    if (this.logger.getIsShowDebugMessages()) {
      this.logger.debug(null, getClass(), msg);
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for level VERBOSE.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  public void trace(String format, Object arg) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the VERBOSE level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg1
   *          the first argument
   * @param arg2
   *          the second argument
   */
  public void trace(String format, Object arg1, Object arg2) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the VERBOSE level.
   * </p>
   *
   * @param format
   *          the format string
   * @param argArray
   *          an array of arguments
   */
  public void trace(String format, Object... argArray) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log an exception (throwable) at level VERBOSE with an accompanying message.
   *
   * @param msg
   *          the message accompanying the exception
   * @param t
   *          the exception (throwable) to log
   */
  public void trace(String msg, Throwable t) {
    if (this.logger.getIsShowDebugMessages()) {
      this.logger.debug(null, getClass(), msg, t);
    }
  }

  /**
   * Is this logger instance enabled for the DEBUG level?
   *
   * @return True if this Logger is enabled for level DEBUG, false otherwise.
   */
  public boolean isDebugEnabled() {
    return this.logger.getIsShowDebugMessages();
  }

  /**
   * Log a message object at level DEBUG.
   *
   * @param msg
   *          - the message object to be logged
   */
  public void debug(String msg) {
    if (this.logger.getIsShowDebugMessages()) {
      this.logger.debug(null, getClass(), msg);
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and argument.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for level DEBUG.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  public void debug(String format, Object arg) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the DEBUG level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg1
   *          the first argument
   * @param arg2
   *          the second argument
   */
  public void debug(String format, Object arg1, Object arg2) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the DEBUG level.
   * </p>
   *
   * @param format
   *          the format string
   * @param argArray
   *          an array of arguments
   */
  public void debug(String format, Object... argArray) {
    if (this.logger.getIsShowDebugMessages()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      this.logger.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log an exception (throwable) at level DEBUG with an accompanying message.
   *
   * @param msg
   *          the message accompanying the exception
   * @param t
   *          the exception (throwable) to log
   */
  public void debug(String msg, Throwable t) {
    if (this.logger.getIsShowDebugMessages()) {
      this.logger.debug(null, getClass(), msg, t);
    }
  }

  /**
   * Is this logger instance enabled for the INFO level?
   *
   * @return True if this Logger is enabled for the INFO level, false otherwise.
   */
  public boolean isInfoEnabled() {
    return true;
  }

  /**
   * Log a message object at the INFO level.
   *
   * @param msg
   *          - the message object to be logged
   */
  public void info(String msg) {
    this.logger.info(null, getClass(), msg);
  }

  /**
   * Log a message at level INFO according to the specified format and argument.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the INFO level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  public void info(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the INFO level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the INFO level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg1
   *          the first argument
   * @param arg2
   *          the second argument
   */
  public void info(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level INFO according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the INFO level.
   * </p>
   *
   * @param format
   *          the format string
   * @param argArray
   *          an array of arguments
   */
  public void info(String format, Object... argArray) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log an exception (throwable) at the INFO level with an accompanying
   * message.
   *
   * @param msg
   *          the message accompanying the exception
   * @param t
   *          the exception (throwable) to log
   */
  public void info(String msg, Throwable t) {
    this.logger.warn(null, getClass(), msg, t);
  }

  /**
   * Is this logger instance enabled for the WARN level?
   *
   * @return True if this Logger is enabled for the WARN level, false
   *         otherwise.
   */
  public boolean isWarnEnabled() {
      return true;
  }

  /**
   * Log a message object at the WARN level.
   *
   * @param msg
   *          - the message object to be logged
   */
  public void warn(String msg) {
    this.logger.warn(null, getClass(), msg);
  }

  /**
   * Log a message at the WARN level according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the WARN level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  public void warn(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the WARN level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the WARN level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg1
   *          the first argument
   * @param arg2
   *          the second argument
   */
  public void warn(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level WARN according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the WARN level.
   * </p>
   *
   * @param format
   *          the format string
   * @param argArray
   *          an array of arguments
   */
  public void warn(String format, Object... argArray) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
    this.logger.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log an exception (throwable) at the WARN level with an accompanying
   * message.
   *
   * @param msg
   *          the message accompanying the exception
   * @param t
   *          the exception (throwable) to log
   */
  public void warn(String msg, Throwable t) {
    this.logger.warn(null, getClass(), msg, t);
  }

  /**
   * Is this logger instance enabled for level ERROR?
   *
   * @return True if this Logger is enabled for level ERROR, false otherwise.
   */
  public boolean isErrorEnabled() {
      return true;
  }

  /**
   * Log a message object at the ERROR level.
   *
   * @param msg
   *          - the message object to be logged
   */
  public void error(String msg) {
    this.logger.error(null, getClass(), msg);
  }

  /**
   * Log a message at the ERROR level according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the ERROR level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  public void error(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.logger.error(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the ERROR level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the ERROR level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg1
   *          the first argument
   * @param arg2
   *          the second argument
   */
  public void error(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.logger.error(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level ERROR according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the logger is disabled
   * for the ERROR level.
   * </p>
   *
   * @param format
   *          the format string
   * @param argArray
   *          an array of arguments
   */
  public void error(String format, Object... argArray) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
    this.logger.error(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log an exception (throwable) at the ERROR level with an accompanying
   * message.
   *
   * @param msg
   *          the message accompanying the exception
   * @param t
   *          the exception (throwable) to log
   */
  public void error(String msg, Throwable t) {
    this.logger.error(null, getClass(), msg, t);
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
