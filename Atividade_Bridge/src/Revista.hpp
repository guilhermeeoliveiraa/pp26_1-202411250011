#pragma once

#include <iostream>
#include <string>

#include "Publicacao.hpp"

class Revista : public Publicacao 
{
public:
    Revista(Implementador* imp) : Publicacao(imp) {}

    std::string getArtigo() 
    {
        return "Artigo Revista";
    }

    std::string getTitulo() override 
    {
        return "Revista Exemplo";
    }

    std::string getAutor(int id) override 
    {
        return "Autor Revista";
    }
};