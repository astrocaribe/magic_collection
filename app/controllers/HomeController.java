package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    public Result ping() {
        Logger.info(String.format("Starting request - [%s %s]", request().method(), request().path()));
        final JsonNode jsonResponse = Json.toJson("This service is alive!");

        Logger.info(String.format("Finished request - [%s %s]", request().method(), request().path()));

        return ok(jsonResponse);
    }

}
