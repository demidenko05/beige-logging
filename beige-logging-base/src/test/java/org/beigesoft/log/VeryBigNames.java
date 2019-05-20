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
 * <p>Model for test class size depend of names. 1529 vs 1308 (SmNms) about 15% loss.</p>
 *
 * @author Yury Demidenko
 */
public class VeryBigNames {

  private String veryBigString1;

  private String veryBigString2;

  private String veryBigString3;

  private String veryBigString4;

  private String veryBigString5;

  //Simple getters and setters:
  /**
   * <p>Getter for veryBigString1.</p>
   * @return String
   **/
  public final String getVeryBigString1() {
    return this.veryBigString1;
  }

  /**
   * <p>Setter for veryBigString1.</p>
   * @param pVeryBigString1 reference
   **/
  public final void setVeryBigString1(final String pVeryBigString1) {
    this.veryBigString1 = pVeryBigString1;
  }

  /**
   * <p>Getter for veryBigString2.</p>
   * @return String
   **/
  public final String getVeryBigString2() {
    return this.veryBigString2;
  }

  /**
   * <p>Setter for veryBigString2.</p>
   * @param pVeryBigString2 reference
   **/
  public final void setVeryBigString2(final String pVeryBigString2) {
    this.veryBigString2 = pVeryBigString2;
  }

  /**
   * <p>Getter for veryBigString3.</p>
   * @return String
   **/
  public final String getVeryBigString3() {
    return this.veryBigString3;
  }

  /**
   * <p>Setter for veryBigString3.</p>
   * @param pVeryBigString3 reference
   **/
  public final void setVeryBigString3(final String pVeryBigString3) {
    this.veryBigString3 = pVeryBigString3;
  }

  /**
   * <p>Getter for veryBigString4.</p>
   * @return String
   **/
  public final String getVeryBigString4() {
    return this.veryBigString4;
  }

  /**
   * <p>Setter for veryBigString4.</p>
   * @param pVeryBigString4 reference
   **/
  public final void setVeryBigString4(final String pVeryBigString4) {
    this.veryBigString4 = pVeryBigString4;
  }

  /**
   * <p>Getter for veryBigString5.</p>
   * @return String
   **/
  public final String getVeryBigString5() {
    return this.veryBigString5;
  }

  /**
   * <p>Setter for veryBigString5.</p>
   * @param pVeryBigString5 reference
   **/
  public final void setVeryBigString5(final String pVeryBigString5) {
    this.veryBigString5 = pVeryBigString5;
  }
}
