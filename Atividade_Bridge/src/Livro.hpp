#pragma once

#include <iostream>
#include <string>

#include "Publicacao.hpp"

class Livro : public Publicacao {
public:
    Livro(Implementador* imp) : Publicacao(imp) {}

    std::string getISBN() 
    {
        return "ISBN-1234";
    }

    std::string getTitulo() override 
    {
        return "Livro Exemplo";
    }

    std::string getAutor(int id) override 
    {
        return "Autor Livro";
    }
};