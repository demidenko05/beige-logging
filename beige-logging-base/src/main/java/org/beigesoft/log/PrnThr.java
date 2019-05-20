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

/**
 * <p>Throwable printer.</p>
 *
 * @author Yury Demidenko
 */
public class PrnThr {

  /**
   * <p>Line separator.</p>
   **/
  private String lnSp = System.getProperty("line.separator");

  //Utils:
  /**
   * <p>Append thrown message to the buffer.</p>
   * @param pBuf Buffer
   * @param pThrown throwable
   **/
  public final void excStr(final StringBuffer pBuf, final Throwable pThrown) {
    if (pThrown == null) {
        pBuf.append(" ex-null");
    } else {
      pBuf.append(pThrown.toString());
      StackTraceElement[] stes = pThrown.getStackTrace();
      for (int i = 0; stes != null && i < stes.length; i++) {
        pBuf.append(this.lnSp + "\tat ");
        pBuf.append(stes[i].toString());
      }
      for (Throwable sprd : pThrown.getSuppressed()) {
        pBuf.append(this.lnSp + "Suppressed: ");
        pBuf.append(sprd.toString() + "\t|");
      }
      Throwable thr = pThrown.getCause();
      if (thr != null && thr != pThrown) { //2-nd, ... levels
        excStrNxt(pBuf, thr);
      }
    }
    pBuf.append(this.lnSp);
  }

  /**
   * <p>Append thrown next level message to the buffer.</p>
   * @param pBuf Buffer
   * @param pThr throwable
   **/
  public final void excStrNxt(final StringBuffer pBuf, final Throwable pThr) {
    pBuf.append(this.lnSp).append("Caused by: ");
    pBuf.append(pThr);
    StackTraceElement[] stes = pThr.getStackTrace();
    for (int i = 0; stes != null && i < stes.length; i++) {
      pBuf.append(this.lnSp + "\tat ");
      pBuf.append(stes[i].toString());
    }
    for (Throwable sprd : pThr.getSuppressed()) {
      pBuf.append(this.lnSp + "Suppressed: ");
      pBuf.append(sprd.toString() + "\t|");
    }
    Throwable thrNxt = pThr.getCause();
    if (thrNxt != null && thrNxt != pThr) { //next level
      excStrNxt(pBuf, thrNxt);
    }
  }

  //Simple getters and setters:

  /**
   * <p>Getter for lnSp.</p>
   * @return String
   **/
  public final String getLnSp() {
    return this.lnSp;
  }

  /**
   * <p>Setter for lnSp.</p>
   * @param pLnSp reference
   **/
  public final void setLnSp(final String pLnSp) {
    this.lnSp = pLnSp;
  }
}
