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

package org.beigesoft.loga;

import java.util.Map;

import android.util.Log;

import org.beigesoft.log.ALog;

/**
 * <p>Android Logger adapter.</p>
 *
 * @author Yury Demidenko
 */
public class Loga extends ALog {

  /**
   * <p>Make test log.</p>
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void test(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    Log.d(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    Log.d(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pCls of debudgged bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void debug(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " " + pMsg);
    excStr(sb, pThr);
    Log.d(pCls.getSimpleName(), sb.toString());
  }

  /**
   * <p>Make info log.</p>
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void info(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    Log.i(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void error(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    Log.e(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void error(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " " + pMsg);
    excStr(sb, pThr);
    Log.e(pCls.getSimpleName(), sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    Log.w(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " " + pMsg);
  }

  /**
   * <p>Make warn log.</p>
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " " + pMsg);
    excStr(sb, pThr);
    Log.w(pCls.getSimpleName(), sb.toString());
  }
}
