package myapp.echo;

import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;


@Api(
        name = "echo",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "echo.myapp",
                ownerName = "echo.myapp",
                packagePath = ""
        ),

        issuers = {
                @ApiIssuer(
                        name = "firebase",
                        issuer = "https://sercuretoken.google.com/cocomogcp",
                        jwksUri = "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system"
                                + ".gserviceaccount.com"
                )
        }

)

public class Echo {


    @ApiMethod(name = "echo",httpMethod = ApiMethod.HttpMethod.GET)
    public Message echo(Message message){
        return doEcho(message);
    }


    @ApiMethod(name = "echo_path_parameter", path = "echo/{n}",httpMethod = ApiMethod.HttpMethod.GET)
    public Message echoPathParameter(Message message) {
        return doEcho(message);
    }



    @ApiMethod(name = "echo_api_key", path = "echo_api_key", apiKeyRequired = AnnotationBoolean.TRUE, httpMethod = ApiMethod.HttpMethod.GET)
    public Message echoApiKey(Message message) {
        return doEcho(message);
    }
    // [END echo_api_key]
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET
    )
    private Message doEcho(Message request) {
        Message response = new Message();
        String s = request.getMessage();
        response.setMessage(s);

        return response;
    }
}
