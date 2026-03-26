#pragma once

#include <iostream>
#include <string>

class Implementador 
{
public:
    virtual void getDados(std::string tipo) = 0;
};