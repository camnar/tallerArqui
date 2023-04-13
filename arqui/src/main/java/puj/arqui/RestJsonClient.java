package puj.arqui;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RestJsonClient {

    private static final String REST_ENDPOINT = "http://localhost:8080/jakartaee-hello-world/rest/product/create";

    public static void main(String[] args) {
        
        Client client = ClientBuilder.newClient();

      
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode product = objectMapper.createObjectNode();
        product.put("name", "iPhone");
        product.put("description", "Celuco");
        product.put("price", "999.99");

        
        Response response = client.target(REST_ENDPOINT)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(product.toString(), MediaType.APPLICATION_JSON));

        
        if (response.getStatus() == 200) {
            System.out.println("Product created successfully.");
        } else {
            System.err.println("Error occurred: " + response.getStatus() + " " + response.getStatusInfo());
        }

        
        response.close();
        client.close();
    }
}

