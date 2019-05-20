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
 * <p>Simple logger to console.
 * Useful for debug purposes.</p>
 *
 * @author Yury Demidenko
 */
public class LogSmp extends ALog {

  /**
   * <p>Make test log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void test(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    System.out.println("thread#" + Thread.currentThread().getId() + " TEST "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    System.out.println("thread#" + Thread.currentThread().getId() + " DEBUG "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " DEBUG "
      + getFmtDt().format(new Date()) + " " + pCls.getSimpleName()
        + " - " + pMsg + getLnSp());
    excStr(sb, pThr);
    System.out.println(sb.toString());
  }

  /**
   * <p>Make info log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void info(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    System.out.println("thread#" + Thread.currentThread().getId() + " INFO "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void error(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    System.err.println("thread#" + Thread.currentThread().getId() + " ERROR "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void error(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " ERROR "
      + getFmtDt().format(new Date()) + " " + pCls.getSimpleName()
        + " - " + pMsg + getLnSp());
    excStr(sb, pThr);
    System.out.println(sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    System.out.println("thread#" + Thread.currentThread().getId() + " WARN "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make warn log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " WARN "
      + getFmtDt().format(new Date()) + " " + pCls.getSimpleName()
        + " - " + pMsg + getLnSp());
    excStr(sb, pThr);
    System.out.println(sb.toString());
  }
}
