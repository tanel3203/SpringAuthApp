package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.entities.Country;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CountryRepository countryRepository;

    public Iterable<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(Client client) {

        clientRepository.save(client);
    }

    public Client getClient(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        return client;
    }

    public void editClient(Client client) {
//        clientRepository.deleteById(client.getId());
        clientRepository.save(client);
    }


//    public List<Client> findAll() {
//        ArrayList<Client> list = new ArrayList<Client>();
//        Iterable<Client> clients = clientRepository.findAll();
//        if (clients != null) {
//            for (Client client : clients) {
//                list.add(client);
//            }
//        }
//        return list;
//    }
}
