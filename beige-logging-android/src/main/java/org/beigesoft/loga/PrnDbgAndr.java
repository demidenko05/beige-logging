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

import android.util.Log;

import org.beigesoft.log.IPrnDbg;
import org.beigesoft.log.PrnThr;

/**
 * <p>Implementation of debug printer (logger) that log messages
 * to debug logger itself, it print messages with Log.i.</p>
 *
 * @author Yury Demidenko
 */
public class PrnDbgAndr extends PrnThr implements IPrnDbg {

  /**
   * <p>Make debug log.</p>
   * @param pCls of debugged bean
   * @param pMsg message
   **/
  @Override
  public final void prn(final Class<?> pCls, final String pMsg) {
    Log.i(pCls.getSimpleName(), "thread#" + Thread.currentThread().getId()
      + " DP> " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pCls of debugged bean
   * @param pExc exception
   **/
  @Override
  public final void prn(final Class<?> pCls, final Throwable pExc) {
    StringBuffer sb = new StringBuffer("thread#" + Thread.currentThread()
      .getId() + " DP> ");
    excStr(sb, pExc);
    Log.i(pCls.getSimpleName(), sb.toString());
  }
}
