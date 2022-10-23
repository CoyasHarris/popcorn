/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn.service;

import com.harris.popcorn.entity.Client;
import java.util.List;
import org.springframework.stereotype.Service;




@Service
public interface ClientService {
    
    public  Client getClient(Long id);
    public  Client saveClient(Client client);
    public List<Client> listAll();
    void deleteClient(Long id);
    
    
}
