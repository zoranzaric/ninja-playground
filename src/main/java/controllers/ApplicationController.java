/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

import com.google.inject.Inject;

public class ApplicationController {

  @Inject
  Context injectedContext;

  public Result index(Context context) {
    String foo = "";

    if (context.getSession() != null && context.getSession().get("foo") != null) {
      foo = context.getSession().get("foo");
    }

    return Results.html().render("foo", foo);
  }

  public Result storeDi() {
    Session session = injectedContext.getSession();
    session.put("foo", "bar");

    Result result = Results.redirect("/");

    // I would have to do this
    session.save(injectedContext, result);

    return result;
  }

  public Result storeNonDi(Session session) {
    session.put("foo", "bar");

    Result result = Results.redirect("/");
    return result;
  }

  public Result clearSession(Session session) {
    session.clear();

    Result result = Results.redirect("/");
    return result;
  }
}
