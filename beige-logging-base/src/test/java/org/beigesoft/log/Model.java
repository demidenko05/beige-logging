package org.beigesoft.log;

/*
 * Copyright (c) 2018 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

/**
 * <p>
 * Simple Model for testing.
 * </p>
 *
 * @author Yury Demidenko
 */
public class Model {

  /**
   * <p>Nullable name.</p>
   **/
  private String itsName;

  /**
   * <p>Setter for itsName.</p>
   * @throws Exception
   **/
  public final void throwsException() throws Exception {
    try {
      int i = this.itsName.length();
    } catch (Exception e) {
      throw new ExceptionWithCode(1, "test ex ", e);
    }
  }

  //Simple getters and setters:
  /**
   * <p>Getter for itsName.</p>
   * @return String
   **/
  public final String getItsName() {
    return this.itsName;
  }

  /**
   * <p>Setter for itsName.</p>
   * @param pItsName reference
   **/
  public final void setItsName(final String pItsName) {
    this.itsName = pItsName;
  }
}
