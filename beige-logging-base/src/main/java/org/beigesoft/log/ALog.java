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

package org.beigesoft.log;

import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>Abstract Logger with date formatter, show debug message flags
 * and throwable printer.</p>
 *
 * @author Yury Demidenko
 */
public abstract class ALog extends PrnThr implements ILog {

  /**
   * <p>Is show debug messages main flag, false default.</p>
   **/
  private boolean dbgSh = false;

  /**
   * <p>Is show debug messages flags for classes.</p>
   **/
  private Map<Class<?>, Boolean> dbgShMap;

  /**
   * <p>Date formatter.</p>
   **/
  private DateFormat fmtDt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  /**
   * <p>Get preferred detail level floor, 0 default.</p>
   **/
  private int dbgFl = 0;

  /**
   * <p>Get preferred detail level ceiling, 99999999 default.</p>
   **/
  private int dbgCl = 99999999;

  /**
   * <p>Get preferred detail level floor, e.g. for suppress unwanted
   * debug messages for deep level from 3 to 9:
   * <pre>
   *  if (this.log.getDbgSh(this.getClass())
   *  && this.log.getDbgFl() < 4 && this.log.getDbgCl() > 8) {
   *    ... expensive code
   *  }
   * </pre>
   * </p>
   * @return preferred detail floor level
   **/
  @Override
  public final int getDbgFl() {
    return this.dbgFl;
  }

  /**
   * <p>Set preferred detail floor level.</p>
   * @param pDbgFl preferred detail floor level
   **/
  @Override
  public final void setDbgFl(final int pDbgFl) {
    this.dbgFl = pDbgFl;
  }

  /**
   * <p>Get preferred detail level ceiling.</p>
   * @return preferred detail ceiling level
   **/
  @Override
  public final int getDbgCl() {
    return this.dbgCl;
  }

  /**
   * <p>Set preferred detail ceiling level.</p>
   * @param pDbgCl preferred detail ceiling level
   **/
  @Override
  public final void setDbgCl(final int pDbgCl) {
    this.dbgCl = pDbgCl;
  }

  /**
   * <p>Set is show debug messages.</p>
   * @param pDbgSh is show debug messages?
   **/
  @Override
  public final void setDbgSh(final boolean pDbgSh) {
    this.dbgSh = pDbgSh;
  }

  /**
   * <p>Get is show debug messages preference.</p>
   * @return is show debug messages?
   **/
  @Override
  public final boolean getDbgSh() {
    return this.dbgSh;
  }

  /**
   * <p>Set is show debug messages for class preference.</p>
   * @param pCls of bean
   * @param pDbgSh is show debug messages?
   **/
  @Override
  public final void setDbgSh(final Class<?> pCls, final boolean pDbgSh) {
    this.dbgShMap.put(pCls, pDbgSh);
  }

  /**
   * <p>Get is show debug messages preference for given class.
   * Use code like is not hit performance:
   * <pre>
   *  if (this.logger.getDbgShs(this.getClass())) {
   *    // make some hard job:
   *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
   *      + Math.round(x1, this.settings.getRoundingMode()) .....
   *    // report message:
   *    this.logger.debug(this.getClass(), msg);
   *  }
   * </pre>
   * Client must itself use this method in this way
   * for performance reason, i.e. logger will report debug message
   * without checking the flag.
   * </p>
   * @param pCls of bean
   * @return is show debug messages for given class?
   **/
  @Override
  public final boolean getDbgSh(final Class<?> pCls) {
    if (this.dbgShMap != null) {
      return this.dbgShMap.get(pCls);
    }
    return this.dbgSh;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for dbgShMap.</p>
   * @return Map<Class<?>, Boolean>
   **/
  public final Map<Class<?>, Boolean> getDbgShMap() {
    return this.dbgShMap;
  }

  /**
   * <p>Setter for dbgShMap.</p>
   * @param pDbgShMap reference
   **/
  public final void setDbgShMap(final Map<Class<?>, Boolean> pDbgShMap) {
    this.dbgShMap = pDbgShMap;
  }

  /**
   * <p>Getter for fmtDt.</p>
   * @return DateFormat
   **/
  public final DateFormat getFmtDt() {
    return this.fmtDt;
  }

  /**
   * <p>Setter for fmtDt.</p>
   * @param pFmtDt reference
   **/
  public final void setFmtDt(final DateFormat pFmtDt) {
    this.fmtDt = pFmtDt;
  }
}
