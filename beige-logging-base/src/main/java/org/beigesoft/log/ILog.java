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

import java.util.List;
import java.util.Map;

/**
 * <p>Abstraction of simple, non-static, OOP and memory friendly Logger.</p>
 *
 * @author Yury Demidenko
 */
public interface ILog {

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars, e.g. report to separate file
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  void debug(Map<String, Object> pRqVs, Class<?> pCls, String pMsg);

  /**
   * <p>Make test log. Only for tests.</p>
   * @param pRqVs request scoped vars, e.g. report to separate file
   * @param pCls of debudgged bean
   * @param pMsg message
   **/
  void test(Map<String, Object> pRqVs, Class<?> pCls, String pMsg);

  /**
   * <p>Make debug log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of debudgged bean
   * @param pMsg message
   * @param pThr thrown
   **/
  void debug(Map<String, Object> pRqVs,
    Class<?> pCls, String pMsg, Throwable pThr);

  /**
   * <p>Make info log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  void info(Map<String, Object> pRqVs, Class<?> pCls, String pMsg);

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  void error(Map<String, Object> pRqVs, Class<?> pCls, String pMsg);

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  void error(Map<String, Object> pRqVs,
    Class<?> pCls, String pMsg, Throwable pThr);

  /**
   * <p>Make warn log.</p>
   * @param pRqVs request scoped vars, e.g. report immediately to given email
   * @param pCls of bean
   * @param pMsg message
   **/
  void warn(Map<String, Object> pRqVs, Class<?> pCls, String pMsg);

  /**
   * <p>Make warn log for cases like "try to open non-exist file".</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  void warn(Map<String, Object> pRqVs,
    Class<?> pCls, String pMsg, Throwable pThr);

  /**
   * <p>Get is show debug messages.</p>
   * @return is show debug messages?
   **/
  boolean getDbgSh();

  /**
   * <p>List is show debug messages preference.</p>
   * @param pDbgSh is show debug messages?
   **/
  void setDbgSh(boolean pDbgSh);

  /**
   * <p>Getter for ranges.</p>
   * @return List<Range>
   **/
  List<Range> getRanges();

  /**
   * <p>Setter for ranges.</p>
   * @param pRanges reference
   **/
  void setRanges(List<Range> pRanges);

  /**
   * <p>Getter for rngMth.</p>
   * @return ERngMth
   **/
  ERngMth getRngMth();

  /**
   * <p>Setter for rngMth.</p>
   * @param pRngMth reference
   **/
  void setRngMth(ERngMth pRngMth);

  /**
   * <p>Get is show debug messages preference for given class and debug level.
   * performance friendly example:
   * <pre>
   *  if (this.log.getDbgSh(this.getClass(), 224365)) {
   *    // make some hard job:
   *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
   *      + Math.round(x1, this.settings.getRoundingMode()) .....
   *    // report message:
   *    this.log.debug(this.getClass(), msg);
   *  }
   * </pre>
   * Result depends of getDbgSh(pCls) && IN_RANGE(pLev).
   * </p>
   * @param pCls of bean
   * @param pLev debug level
   * @return is show debug messages?
   **/
  boolean getDbgSh(Class<?> pCls, int pLev);

  /**
   * <p>Get is show debug messages preference for given class.
   * performance friendly example:
   * <pre>
   *  if (this.log.getDbgSh(this.getClass())) {
   *    // make some hard job:
   *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
   *      + Math.round(x1, this.settings.getRoundingMode()) .....
   *    // report message:
   *    this.log.debug(this.getClass(), msg);
   *  }
   * </pre>
   * Client must itself use this method in this way
   * for performance reason, i.e. log will report debug message
   * without checking the flag.
   * </p>
   * @param pCls of bean
   * @return is show debug messages?
   **/
  boolean getDbgSh(Class<?> pCls);

  /**
   * <p>List is show debug messages for class preference.</p>
   * @param pCls of bean
   * @param pDbgSh is show debug messages?
   **/
  void setDbgSh(Class<?> pCls, boolean pDbgSh);

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
  int getDbgFl();

  /**
   * <p>List preferred detail floor level.</p>
   * @param pDbgFl preferred detail floor level
   **/
  void setDbgFl(int pDbgFl);

  /**
   * <p>Get preferred detail level ceiling.</p>
   * @return preferred detail ceiling level
   **/
  int getDbgCl();

  /**
   * <p>List preferred detail ceiling level.</p>
   * @param pDbgCl preferred detail ceiling level
   **/
  void setDbgCl(int pDbgCl);
}
