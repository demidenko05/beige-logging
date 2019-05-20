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
 * <p>Generic exception for testing.
 * </p>
 *
 * @author Yury Demidenko
 */
public class ExceptionWithCode extends Exception {

  /**
   * <p>Code.</p>
   **/
  private int code;

  /**
   * <p>Short message, it's usually i18n message code.</p>
   **/
  private String shortMessage;

  /**
   * <p>Add message, it's usually user name whose action
   * cause exception.</p>
   **/
  private String addMessage;

  /**
   * <p>Constructor default.</p>
   **/
  public ExceptionWithCode() {
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   **/
  public ExceptionWithCode(final int pCode) {
    this.code = pCode;
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   * @param pCause parent exception
   **/
  public ExceptionWithCode(final int pCode, final Throwable pCause) {
    super(pCause);
    this.code = pCode;
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   * @param pMsg message
   **/
  public ExceptionWithCode(final int pCode, final String pMsg) {
    super(pMsg);
    this.code = pCode;
    this.shortMessage = pMsg;
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   * @param pMsg message
   * @param pAddMsg add message
   **/
  public ExceptionWithCode(final int pCode, final String pMsg,
    final String pAddMsg) {
    super(pMsg);
    this.code = pCode;
    this.shortMessage = pMsg;
    this.addMessage = pAddMsg;
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   * @param pMsg message
   * @param pCause parent exception
   **/
  public ExceptionWithCode(final int pCode, final String pMsg,
    final Throwable pCause) {
    super(pMsg, pCause);
    this.code = pCode;
    this.shortMessage = pMsg;
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCode Code
   * @param pMsg message
   * @param pAddMsg add message
   * @param pCause parent exception
   **/
  public ExceptionWithCode(final int pCode, final String pMsg,
    final String pAddMsg, final Throwable pCause) {
    super(pMsg, pCause);
    this.code = pCode;
    this.shortMessage = pMsg;
    this.addMessage = pAddMsg;
  }

  //Simple getters and setters:
  /**
   * <p>Geter for code.</p>
   * @return int
   **/
  public final int getCode() {
    return this.code;
  }

  /**
   * <p>Setter for code.</p>
   * @param pCode reference
   **/
  public final void setCode(final int pCode) {
    this.code = pCode;
  }

  /**
   * <p>Getter for shortMessage.</p>
   * @return String
   **/
  public final String getShortMessage() {
    return this.shortMessage;
  }

  /**
   * <p>Setter for shortMessage.</p>
   * @param pShortMessage reference
   **/
  public final void setShortMessage(final String pShortMessage) {
    this.shortMessage = pShortMessage;
  }

  /**
   * <p>Getter for addMessage.</p>
   * @return String
   **/
  public final String getAddMessage() {
    return this.addMessage;
  }

  /**
   * <p>Setter for addMessage.</p>
   * @param pAddMessage reference
   **/
  public final void setAddMessage(final String pAddMessage) {
    this.addMessage = pAddMessage;
  }
}
