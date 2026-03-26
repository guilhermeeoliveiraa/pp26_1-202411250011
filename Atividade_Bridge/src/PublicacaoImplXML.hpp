#pragma once

#include <iostream>
#include <string>

#include "Implementador.hpp"

class PublicacaoImplXML : public Implementador
{
public:
	void getDados(std::string tipo) override
	{
		std::cout << "Carregando Dados a partir de XML" << std::endl;
	}
};