#pragma once

#include <iostream>
#include <string>

#include "Implementador.hpp"

class Publicacao 
{
public:
    Publicacao(Implementador* imp) : imp(imp) {}

    virtual void obterDados(std::string tipo) {
        imp->getDados(tipo);
    }

    virtual std::string getTitulo() = 0;
    virtual std::string getAutor(int id) = 0;
    
protected:
    Implementador* imp;
};