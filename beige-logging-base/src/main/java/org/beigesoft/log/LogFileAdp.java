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

import java.util.Date;
import java.util.Map;

/**
 * <p>Simple log to file.
 * It's used by programs that not respect "check if need to debug",
 * i.e. they don't do this:
 * <pre>
 *  if (this.log.getDbgSh(this.getClass())) {
 *    // make some hard job:
 *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
 *      + Math.round(x1, this.settings.getRoundingMode()) .....
 *    // report message:
 *    this.log.debug(this.getClass(), msg);
 *  }
 * </pre>
 * It is actually for using in adapters for 3d party libraries, e.g. A-Jetty.
 * </p>
 *
 * @author Yury Demidenko
 */
public class LogFileAdp extends ALogFile {

  /**
   * <p>Make test log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void test(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    prn("thread#" + Thread.currentThread().getId() + " TEST " + getFmtDt()
  .format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg + getLnSp());
  }

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    if (getDbgSh()) {
      prn("thread#" + Thread.currentThread().getId() + " DEBUG " + getFmtDt()
  .format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg + getLnSp());
    }
  }

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final synchronized void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThrown) {
    if (getDbgSh()) {
      StringBuffer sb = new StringBuffer();
      sb.append("thread#" + Thread.currentThread().getId() + " DEBUG "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
      excStr(sb, pThrown);
      prn(sb.toString());
    }
  }
}
