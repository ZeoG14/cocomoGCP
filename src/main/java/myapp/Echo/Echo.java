package myapp.echo;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;


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


    @ApiMethod(name = "echo")
    public Message echo(Message message, @Named("n") @Nullable Integer n){
        return doEcho(message,n);
    }


    @ApiMethod(name = "echo_path_parameter", path = "echo/{n}")
    public Message echoPathParameter(Message message, @Named("n") int n) {
        return doEcho(message, n);
    }



    @ApiMethod(name = "echo_api_key", path = "echo_api_key", apiKeyRequired = AnnotationBoolean.TRUE)
    public Message echoApiKey(Message message, @Named("n") @Nullable Integer n) {
        return doEcho(message, n);
    }
    // [END echo_api_key]

    private Message doEcho(Message request, Integer n) {
        Message response = new Message();
        if (n != null && n >= 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(request.getMessage());
            }
            response.setMessage(sb.toString());
        }
        return response;
    }



}
