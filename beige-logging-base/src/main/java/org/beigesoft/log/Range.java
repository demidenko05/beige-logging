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
 * <p>Range data model.</p>
 *
 * @author Yury Demidenko
 */
public class Range {

  /**
   * <p>Preferred detail level floor, 0 default.</p>
   **/
  private int dbgFl = 0;

  /**
   * <p>Preferred detail level ceiling, 99999999 default.</p>
   **/
  private int dbgCl = 99999999;

  //Simple getters and setters:
  /**
   * <p>Get preferred detail level floor.</p>
   * @return preferred detail floor level
   **/
  public final int getDbgFl() {
    return this.dbgFl;
  }

  /**
   * <p>Set preferred detail floor level.</p>
   * @param pDbgFl preferred detail floor level
   **/
  public final void setDbgFl(final int pDbgFl) {
    this.dbgFl = pDbgFl;
  }

  /**
   * <p>Get preferred detail level ceiling.</p>
   * @return preferred detail ceiling level
   **/
  public final int getDbgCl() {
    return this.dbgCl;
  }

  /**
   * <p>Set preferred detail ceiling level.</p>
   * @param pDbgCl preferred detail ceiling level
   **/
  public final void setDbgCl(final int pDbgCl) {
    this.dbgCl = pDbgCl;
  }
}
