package br.com.davi.shop.http.controller;

import br.com.davi.shop.entity.Vendedor;
import br.com.davi.shop.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/vendedor")
public class VendedorController{

    @Autowired
    private VendedorService vendedorService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor cadastrar(@RequestBody Vendedor vendedor){
        return vendedorService.cadastrar(vendedor);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendedor buscarVendedor(@PathVariable("id") Long id){
        return vendedorService.buscarVendedor(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}