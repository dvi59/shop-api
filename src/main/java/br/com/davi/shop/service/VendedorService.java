package br.com.davi.shop.service;

import br.com.davi.shop.entity.Vendedor;
import br.com.davi.shop.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor cadastrar(Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    public List<Vendedor> listaVendedor(){
        return vendedorRepository.findAll();
    }
    public Optional<Vendedor> buscarVendedor(Long id){
        return vendedorRepository.findById(id);
    }


}
