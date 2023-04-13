package puj.arqui;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserRestJsonClient {

    private static final String REST_ENDPOINT = "http://10.0.1.108:8080/jakartaee-hello-world/rest/user/create";

    public static void main(String[] args) {
      
        Client client = ClientBuilder.newClient();

        
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode user = objectMapper.createObjectNode();
        user.put("name", "Aton Code");
        user.put("email", "hello@atoncode.me");
        user.put("password", "123456");

       
        Response response = client.target(REST_ENDPOINT)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user.toString(), MediaType.APPLICATION_JSON));

        
        if (response.getStatus() == 200) {
            System.out.println("User created successfully.");
        } else {
            System.err.println("Error occurred: " + response.getStatus() + " " + response.getStatusInfo());
        }

        
        response.close();
        client.close();
    }
}

