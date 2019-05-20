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

import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * <p>Utility that fills file logger properties.</p>
 *
 * @author Yury Demidenko
 */
public class FilFlLogPrp {

  /**
   * <p>Debug printer.</p>
   **/
  private IPrnDbg prnDbg;

  /**
   * <p>Logger files dir, it has higher priority (if settled by client)
   * than property "logDir" from properties file.</p>
   **/
  private String logDir;

  /**
   * <p>it means that application consist of only JAR
   * so it can be invoked by double click, so System property "user.dir"
   * will point to wrong place
   * e.g. beige-accounting-ajetty.jar.</p>
   **/
  private boolean onlyJar = true;

  /**
   * <p>It fills logger properties from properties file.</p>
   * @param pLog file logger
   * @param pFlBsNm log file base name e.g. "all-logs"
   **/
  public final void fill(final ALogFile pLog, final String pFlBsNm) {
    String logPrp = "/" + pFlBsNm + ".properties";
    URL urlSt = FilFlLogPrp.class.getResource(logPrp);
    if (urlSt != null) {
      this.prnDbg.prn(FilFlLogPrp.class, "Found properties: " + logPrp);
      InputStream is = null;
      try {
        Properties props = new Properties();
        is = FilFlLogPrp.class.getResourceAsStream(logPrp);
        props.load(is);
        if (this.logDir == null) { // this.logDir has higher priority
          String lgDir = props.getProperty("logDir");
          if (lgDir != null) {
            this.logDir = lgDir;
          } else {
            String ojStr = props.getProperty("onlyJar");
            if (ojStr != null) {
              this.onlyJar = Boolean.valueOf(ojStr);
            }
          }
        }
        String fmsStr = props.getProperty("maxSize");
        if (fmsStr != null) {
          int maxSize = Integer.parseInt(fmsStr);
          pLog.setMaxSize(maxSize);
        }
        String mitStr = props.getProperty("maxIdleTm");
        if (mitStr != null) {
          int maxIdleTm = Integer.parseInt(mitStr);
          pLog.setMaxIdleTm(maxIdleTm);
        }
        String clsImmStr = props.getProperty("clsImm");
        if (clsImmStr != null) {
          boolean clsImm = Boolean.parseBoolean(clsImmStr);
          pLog.setClsImm(clsImm);
        }
        String dbgShStr = props.getProperty("dbgSh");
        if (dbgShStr != null) {
          boolean dbgSh = Boolean.parseBoolean(dbgShStr);
          pLog.setDbgSh(dbgSh);
        }
      } catch (Exception ex) {
        this.prnDbg.prn(FilFlLogPrp.class, ex);
      } finally {
        if (is != null) {
          try {
            is.close();
          } catch (Exception ex) {
            this.prnDbg.prn(FilFlLogPrp.class, ex);
          }
        }
      }
    } else {
      this.prnDbg.prn(FilFlLogPrp.class,
        "There is no properties: " + logPrp);
    }
    if (this.logDir == null) {
      if (this.onlyJar) {
        try {
          File jarFl = new File(FilFlLogPrp.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI().getPath());
          this.logDir = jarFl.getParent();
        } catch (Exception ex) {
          this.logDir = System.getProperty("user.dir");
          this.prnDbg.prn(FilFlLogPrp.class, ex);
        }
      } else {
        this.logDir = System.getProperty("user.dir");
      }
    }
    pLog.setPath(this.logDir + File.separator + pFlBsNm);
    this.prnDbg.prn(FilFlLogPrp.class, "Log file path: " + pLog.getPath());
  }

  //Simple getters and setters:
  /**
   * <p>Getter for prnDbg.</p>
   * @return IPrnDbg
   **/
  public final IPrnDbg getPrnDbg() {
    return this.prnDbg;
  }

  /**
   * <p>Setter for prnDbg.</p>
   * @param pPrnDbg reference
   **/
  public final void setPrnDbg(final IPrnDbg pPrnDbg) {
    this.prnDbg = pPrnDbg;
  }

  /**
   * <p>Getter for logDir.</p>
   * @return String
   **/
  public final String getLogDir() {
    return this.logDir;
  }

  /**
   * <p>Setter for logDir.</p>
   * @param pLogDir reference
   **/
  public final void setLogDir(final String pLogDir) {
    this.logDir = pLogDir;
  }

  /**
   * <p>Getter for onlyJar.</p>
   * @return boolean
   **/
  public final boolean getOnlyJar() {
    return this.onlyJar;
  }

  /**
   * <p>Setter for onlyJar.</p>
   * @param pOnlyJar reference
   **/
  public final void setOnlyJar(final boolean pOnlyJar) {
    this.onlyJar = pOnlyJar;
  }
}
