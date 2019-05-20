/*
 * Copyright (c) 2004-2013 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
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


package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import org.beigesoft.log.LogFile;
import org.beigesoft.log.FilFlLogPrp;
import org.beigesoft.log.IPrnDbg;
import org.beigesoft.loga.PrnDbgAndr;

import android.os.Environment;

/**
 * <p>BsfLogFlFct is an implementation of {@link ILoggerFactory} returning
 * the appropriately named {@link BeigeLoggerFactory} instance.
 * There is only LogFile for all classes that use SLS4J.
 * LogFile uses name "slf4j-all" as log file at external storage
 * directory of Android application.
 * It expects "slsfj-all.properties" file for customize log.
 * </p>
 *
 * @author Yury Demidenko
 */
class BsfLogFlFct implements ILoggerFactory {

  private Logger log;

  private FilFlLogPrp filFlLogPrp;

  /**
   * <p>Debug printer.</p>
   **/
  private IPrnDbg prnDbg;

  /**
   * Return an appropriate {@link BsfLogAdp} instance by name.
   */
  public final Logger getLogger(final String name) {
    if (this.log == null) {
      synchronized (this) {
        if (this.log == null) {
          BsfLogAdp logAdp = new BsfLogAdp(getClass().getSimpleName());
          LogFile lg = new LogFile();
          lg.setClsImm(true);
          if (this.filFlLogPrp == null) {
            this.filFlLogPrp = new FilFlLogPrp();
          }
          if (this.prnDbg == null) {
            this.prnDbg = new PrnDbgAndr();
          }
          this.filFlLogPrp.setLogDir(Environment
            .getExternalStorageDirectory().getAbsolutePath());
          this.filFlLogPrp.setPrnDbg(this.prnDbg);
          this.filFlLogPrp.fill(lg, "slf4j-all");
          logAdp.setLog(lg);
          this.log = logAdp; // finally assign fully initialized bean 
        }
      }
    }
    return this.log;
  }


  //Simple getters and setters:
  /**
   * <p>Getter for filFlLogPrp.</p>
   * @return FilFlLogPrp
   **/
  public final synchronized FilFlLogPrp getFilFlLogPrp() {
    return this.filFlLogPrp;
  }

  /**
   * <p>Setter for filFlLogPrp.</p>
   * @param pFilFlLogPrp reference
   **/
  public final synchronized void setFilFlLogPrp(
    final FilFlLogPrp pFilFlLogPrp) {
    this.filFlLogPrp = pFilFlLogPrp;
  }

  /**
   * <p>Getter for prnDbg.</p>
   * @return IPrnDbg
   **/
  public final synchronized IPrnDbg getPrnDbg() {
    return this.prnDbg;
  }

  /**
   * <p>Setter for prnDbg.</p>
   * @param pPrnDbg reference
   **/
  public final synchronized void setPrnDbg(final IPrnDbg pPrnDbg) {
    this.prnDbg = pPrnDbg;
  }
}
