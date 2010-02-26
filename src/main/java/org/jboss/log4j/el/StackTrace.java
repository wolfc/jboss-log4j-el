/*
 * JBoss, Home of Professional Open Source
 * Copyright (c) 2010, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.log4j.el;

import org.jboss.log4j.el.util.GetPropertyAction;

import static java.security.AccessController.doPrivileged;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class StackTrace
{
   private static String LINE_SEPARATOR = doPrivileged(new GetPropertyAction("line.separator"));

   public static String here()
   {
      // pick a number, any number, ah well let's take 21
      return here(21);
   }

   public static String here(int start)
   {
      String s = "";
      StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
      for(int i = start; i < stackTrace.length; i++)
      {
         s += "\tat " + stackTrace[i];
         if(i < (stackTrace.length - 1))
            s += LINE_SEPARATOR;
      }
      return s;
   }
}
