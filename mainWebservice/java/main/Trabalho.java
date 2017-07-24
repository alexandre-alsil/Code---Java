/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import patrocinador.model.dao.PatrocinadorDAO;
import patrocinador.model.domain.Patrocinador;

/**
 * REST Web Service
 *
 * @author Alexandre
 */
@Path("trabalho")
public class Trabalho {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Trabalho
     */
    public Trabalho() {
    }

    /**
     * Retrieves representation of an instance of main.Trabalho
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getJson() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Patrocinadores";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Patrocinador/list")
    public ArrayList<Patrocinador> listPatrocinador(){
        ArrayList<Patrocinador> lista;
        
        PatrocinadorDAO patrocinadorDAO = new PatrocinadorDAO();
        lista = patrocinadorDAO.listar();
        
        return lista;
    }
    /**
     * PUT method for updating or creating an instance of Trabalho
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
