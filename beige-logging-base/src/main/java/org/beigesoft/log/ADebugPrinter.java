package org.beigesoft.log;

/*
 * Copyright (c) 2015-2017 Beigesoft ™
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
 * <p>Half implementation of debug printer (logger) that log messages
 * to debug logger itself, usually it print messages to System.out for
 * standard Java or Log.i for Android.</p>
 *
 * @author Yury Demidenko
 */
public abstract class ADebugPrinter implements IDebugPrinter {

  /**
   * <p>Line separator.</p>
   **/
  private String lineSeparator = System.getProperty("line.separator");

  //Utils:
  /**
   * <p>Append thrown message to the buffer.</p>
   * @param pBuffer Buffer
   * @param pThrown throwable
   **/
  public final void exceptionToString(final StringBuffer pBuffer,
    final Throwable pThrown) {
    if (pThrown == null) {
        pBuffer.append(" ex-null");
    } else {
      pBuffer.append(pThrown.toString());
      StackTraceElement[] elements = pThrown.getStackTrace();
      for (int i = 0; elements != null && i < elements.length; i++) {
        pBuffer.append(this.lineSeparator + "\tat ");
        pBuffer.append(elements[i].toString());
      }
      for (Throwable suppressed : pThrown.getSuppressed()) {
        pBuffer.append(this.lineSeparator + "Suppressed: ");
        pBuffer.append(suppressed.toString() + "\t|");
      }
      Throwable cause = pThrown.getCause();
      if (cause != null && cause != pThrown) {
        pBuffer.append(this.lineSeparator).append("Caused by: ");
        pBuffer.append(cause);
      }
    }
    pBuffer.append(this.lineSeparator);
  }

 //Simple getters and setters:
  /**
   * <p>Getter for lineSeparator.</p>
   * @return String
   **/
  public final String getLineSeparator() {
    return this.lineSeparator;
  }

  /**
   * <p>Setter for lineSeparator.</p>
   * @param pLineSeparator reference
   **/
  public final void setLineSeparator(final String pLineSeparator) {
    this.lineSeparator = pLineSeparator;
  }
}
