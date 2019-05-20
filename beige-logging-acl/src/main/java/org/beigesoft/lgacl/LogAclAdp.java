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

package org.beigesoft.lgacl;

import org.apache.commons.logging.Log;

import org.beigesoft.log.ILog;

/**
 * <p>Adapter beige-log to Apache Common Logging.</p>
 *
 * @author Yury Demidenko
 */
public class LogAclAdp implements Log {

  /**
   * <p>Log delegate.</p>
   **/
  private ILog log;

  /**
   * Logs a message with debug log level.
   *
   * @param message log this message
   */
  @Override
  public final void debug(final Object msg) {
    if (this.log.getDbgSh()) {
      String sMsg = msg == null ? "???" : msg.toString();
      this.log.debug(null, getClass(), sMsg);
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
    this.log.warn(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with error log level.
   *
   * @param message log this message
   */
  @Override
  public final void error(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.log.error(null, getClass(), sMsg);
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
    this.log.error(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with fatal log level.
   *
   * @param message log this message
   */
  @Override
  public final void fatal(final Object msg) {
    String sMsg = msg == null ? "FATAL???" : msg.toString();
    this.log.error(null, getClass(), sMsg);
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
    this.log.error(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with info log level.
   *
   * @param message log this message
   */
  @Override
  public final void info(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.log.info(null, getClass(), sMsg);
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
    this.log.warn(null, getClass(), sMsg, t);
  }

  /**
   * Is debug logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than debug.
   *
   * @return true if debug is enabled in the underlying log.
   */
  @Override
  public final boolean isDebugEnabled() {
    return this.log.getDbgSh();
  }

  /**
   * Is error logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than error.
   *
   * @return true if error is enabled in the underlying log.
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
   * @return true if fatal is enabled in the underlying log.
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
   * @return true if info is enabled in the underlying log.
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
   * @return true if trace is enabled in the underlying log.
   */
  @Override
  public final boolean isTraceEnabled() {
    return this.log.getDbgSh();
  }

  /**
   * Is warn logging currently enabled?
   * <p>
   * Call this method to prevent having to perform expensive operations
   * (for example, <code>String</code> concatenation)
   * when the log level is more than warn.
   *
   * @return true if warn is enabled in the underlying log.
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
    this.log.debug(null, getClass(), sMsg);
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
    this.log.warn(null, getClass(), sMsg, t);
  }

  /**
   * Logs a message with warn log level.
   *
   * @param message log this message
   */
  @Override
  public final void warn(final Object msg) {
    String sMsg = msg == null ? "???" : msg.toString();
    this.log.warn(null, getClass(), sMsg);
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
    this.log.warn(null, getClass(), sMsg, t);
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
