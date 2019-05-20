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
/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.slf4j.impl;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import org.beigesoft.log.ILog;

/**
 * <p>Beige-log adapter.</p>
 *
 * @author Yury Demidenko
 */
public class BsfLogAdp extends MarkerIgnoringBase {

  private static final long serialVersionUID = 1227274521526668137L;

  private ILog log;

  /**
   * Package access allows only {@link BeigeLoggerFactory} to instantiate
   * SimpleLogger instances.
   */
  BsfLogAdp(String tag) {
      this.name = tag;
  }

  /**
   * Is this log instance enabled for the VERBOSE level?
   *
   * @return True if this Logger is enabled for level VERBOSE, false otherwise.
   */
  @Override
  public boolean isTraceEnabled() {
      return true;
  }

  /**
   * Log a message object at level VERBOSE.
   *
   * @param msg
   *          - the message object to be logged
   */
  @Override
  public void trace(String msg) {
    if (this.log.getDbgSh()) {
      this.log.debug(null, getClass(), msg);
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for level VERBOSE.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  @Override
  public void trace(String format, Object arg) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
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
  @Override
  public void trace(String format, Object arg1, Object arg2) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level VERBOSE according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the VERBOSE level.
   * </p>
   *
   * @param format
   *          the format string
   * @param args
   *          an array of arguments
   */
  @Override
  public void trace(String format, Object... args) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
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
  @Override
  public void trace(String msg, Throwable t) {
    if (this.log.getDbgSh()) {
      this.log.debug(null, getClass(), msg, t);
    }
  }

  /**
   * Is this log instance enabled for the DEBUG level?
   *
   * @return True if this Logger is enabled for level DEBUG, false otherwise.
   */
  @Override
  public boolean isDebugEnabled() {
    return this.log.getDbgSh();
  }

  /**
   * Log a message object at level DEBUG.
   *
   * @param msg
   *          - the message object to be logged
   */
  @Override
  public void debug(String msg) {
    if (this.log.getDbgSh()) {
      this.log.debug(null, getClass(), msg);
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and argument.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for level DEBUG.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  @Override
  public void debug(String format, Object arg) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
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
  @Override
  public void debug(String format, Object arg1, Object arg2) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
    }
  }

  /**
   * Log a message at level DEBUG according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the DEBUG level.
   * </p>
   *
   * @param format
   *          the format string
   * @param args
   *          an array of arguments
   */
  @Override
  public void debug(String format, Object... args) {
    if (this.log.getDbgSh()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
      this.log.debug(null, getClass(), ft.getMessage(), ft.getThrowable());
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
  @Override
  public void debug(String msg, Throwable t) {
    if (this.log.getDbgSh()) {
      this.log.debug(null, getClass(), msg, t);
    }
  }

  /**
   * Is this log instance enabled for the INFO level?
   *
   * @return True if this Logger is enabled for the INFO level, false otherwise.
   */
  @Override
  public boolean isInfoEnabled() {
    return true;
  }

  /**
   * Log a message object at the INFO level.
   *
   * @param msg
   *          - the message object to be logged
   */
  @Override
  public void info(String msg) {
    this.log.info(null, getClass(), msg);
  }

  /**
   * Log a message at level INFO according to the specified format and argument.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the INFO level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  @Override
  public void info(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the INFO level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
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
  @Override
  public void info(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level INFO according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the INFO level.
   * </p>
   *
   * @param format
   *          the format string
   * @param args
   *          an array of arguments
   */
  @Override
  public void info(String format, Object... args) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
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
  @Override
  public void info(String msg, Throwable t) {
    this.log.warn(null, getClass(), msg, t);
  }

  /**
   * Is this log instance enabled for the WARN level?
   *
   * @return True if this Logger is enabled for the WARN level, false
   *         otherwise.
   */
  @Override
  public boolean isWarnEnabled() {
      return true;
  }

  /**
   * Log a message object at the WARN level.
   *
   * @param msg
   *          - the message object to be logged
   */
  @Override
  public void warn(String msg) {
    this.log.warn(null, getClass(), msg);
  }

  /**
   * Log a message at the WARN level according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the WARN level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  @Override
  public void warn(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the WARN level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
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
  @Override
  public void warn(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level WARN according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the WARN level.
   * </p>
   *
   * @param format
   *          the format string
   * @param args
   *          an array of arguments
   */
  @Override
  public void warn(String format, Object... args) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
    this.log.warn(null, getClass(), ft.getMessage(), ft.getThrowable());
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
  @Override
  public void warn(String msg, Throwable t) {
    this.log.warn(null, getClass(), msg, t);
  }

  /**
   * Is this log instance enabled for level ERROR?
   *
   * @return True if this Logger is enabled for level ERROR, false otherwise.
   */
  @Override
  public boolean isErrorEnabled() {
      return true;
  }

  /**
   * Log a message object at the ERROR level.
   *
   * @param msg
   *          - the message object to be logged
   */
  @Override
  public void error(String msg) {
    this.log.error(null, getClass(), msg);
  }

  /**
   * Log a message at the ERROR level according to the specified format and
   * argument.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the ERROR level.
   * </p>
   *
   * @param format
   *          the format string
   * @param arg
   *          the argument
   */
  @Override
  public void error(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg});
    this.log.error(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at the ERROR level according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
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
  @Override
  public void error(String format, Object arg1, Object arg2) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[] {arg1, arg2});
    this.log.error(null, getClass(), ft.getMessage(), ft.getThrowable());
  }

  /**
   * Log a message at level ERROR according to the specified format and
   * arguments.
   *
   * <p>
   * This form avoids superfluous object creation when the log is disabled
   * for the ERROR level.
   * </p>
   *
   * @param format
   *          the format string
   * @param args
   *          an array of arguments
   */
  @Override
  public void error(String format, Object... args) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
    this.log.error(null, getClass(), ft.getMessage(), ft.getThrowable());
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
  @Override
  public void error(String msg, Throwable t) {
    this.log.error(null, getClass(), msg, t);
  }

  //Simple getters and setters:
  /**
   * <p>Getter for log.</p>
   * @return ILog
   **/
  public final ILog getLog() {
    return this.log;
  }

  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
  }
}
