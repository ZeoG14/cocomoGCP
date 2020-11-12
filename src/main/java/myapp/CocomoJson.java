package myapp;
import java.util.Random;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.json.Json;

public class CocomoJson extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cocomo cocomo = new Cocomo();
        Random rand = new Random();
        int kloc = rand.nextInt(1000);

        String mode = cocomo.modeStr(kloc);
        String effort = cocomo.effortStr(kloc);
        String time = cocomo.timeStr();


        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

       JsonObjectBuilder root = Json.createObjectBuilder();
       JsonObject cocoJson = root.add("mode", mode).add("effort",effort).add("time",time).build();

       writer.print(cocoJson);
       writer.flush();
       writer.close();

    }
}
