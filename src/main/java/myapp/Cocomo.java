/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import java.io.*;
import java.lang.Math;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Cocomo extends HttpServlet {

  public static int mo;
  public static double ef;
  public static double ti;
  public static String[] mode = {"Organic", "Semi-Detached", "Embedded"};
  public static double[][] table = {{2.4,1.05,2.5,0.38},{3,1.12,2.5,0.35},{3.6,1.2,2.5,0.32}};


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String size = req.getParameter("size");


    try {
      String mode = modeStr(Integer.parseInt(size));
      String effort = effortStr(Integer.parseInt(size));
      String time = timeStr();

      req.setAttribute("mode", mode);
      resp.setHeader("MODE", mode);


      req.setAttribute("effort", effort);
      resp.setHeader("EFFORT", effort);


      req.setAttribute("time", time);
      resp.setHeader("TIME", time);


      RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
      dispatcher.forward(req, resp);
    } catch (Exception e) {
      resp.sendRedirect("index.jsp");
    }

  }


  public String modeStr(int size) {
    int model = 0;

      //Check the mode according to size
      // 0 = organic

    if (size > 50 && size <= 300) {
      model = 1;
    } // semi-detached

    if (size > 300) {
      model = 2;
    } //embedded

    Cocomo.mo = model;

    return "The mode is " + mode[model] + System.lineSeparator();

  }

  public String effortStr(int size) {
    double effort = table[mo][0] * Math.pow(size, table[mo][1]);
    Cocomo.ef = effort;
    return "Effort = " + dround(effort) + " Person-Month" + System.lineSeparator();

  }

  public String timeStr() {
    double time = table[mo][2] * Math.pow(ef, table[mo][3]);
    Cocomo.ti = time;
    return "Development Time = " + dround(time) + " Months" + System.lineSeparator();
  }

  public String staffStr() {
    double staff = ef / ti;
    return "Average Staff Required = " + fround(staff) + " Persons" + System.lineSeparator();
  }

  public int fround(double x) {
    return (int) Math.ceil(x);
  }

  public int dround(double y) {
    return (int) (Math.round(y * 100000.00) / 100000.00);
  }

}