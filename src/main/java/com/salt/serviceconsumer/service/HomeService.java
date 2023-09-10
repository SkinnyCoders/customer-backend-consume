package com.salt.serviceconsumer.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salt.serviceconsumer.Model.Konsument;
import com.salt.serviceconsumer.Model.KonsumentForm;

@Service
public class HomeService {

    private ObjectMapper MAPPER = new ObjectMapper();
    
    public List<Konsument> getData() throws JsonMappingException, JsonProcessingException, ParseException{
        List<Konsument> result = new ArrayList<>();

        Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8500/api/v1/konsumen");
		ClientResponse clientResponse = webResource.type("application/json").get(ClientResponse.class);
		String output = clientResponse.getEntity(String.class);
        JsonNode datas = MAPPER.readValue(output, JsonNode.class);

        for(JsonNode data : datas.get("data")){
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            
            Date inputDate = inputFormat.parse(data.get("tgl_registrasi").asText());
            String outputDatetimeStr = outputFormat.format(inputDate);

            Konsument konsument = new Konsument(data.get("id").asInt(), 
                    data.get("nama").asText(),  
                    data.get("alamat").asText(), 
                    data.get("kota").asText(),  
                    data.get("provinsi").asText(),  
                    outputDatetimeStr,  
                    data.get("status").asText());
            result.add(konsument);
        }
        return result;
    }

    public String insertData(KonsumentForm konsumentForm) throws JsonMappingException, JsonProcessingException{
        Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8500/api/v1/konsumen/register");
		ClientResponse clientResponse = webResource.type("application/json").post(ClientResponse.class, new ObjectMapper().writeValueAsString(konsumentForm));
		String output = clientResponse.getEntity(String.class);
        JsonNode datas = MAPPER.readValue(output, JsonNode.class);

        if(datas.get("message").asText().equals("SUCCESS")){
            return datas.get("message").asText();
        }

        return "FAILED";
    }

    public String deleteData(Integer id) throws JsonMappingException, JsonProcessingException, UniformInterfaceException, ClientHandlerException{
        Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8500/api/v1/konsumen/deleted/"+id);
		ClientResponse clientResponse = webResource.type("application/json").delete(ClientResponse.class);
		String output = clientResponse.getEntity(String.class);
        JsonNode datas = MAPPER.readValue(output, JsonNode.class);

        if(datas.get("message").asText().equals("SUCCESS")){
            return datas.get("message").asText();
        }

        return "FAILED";
    }
}
