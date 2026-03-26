#include <iostream>
#include <string>

#include "src/Livro.hpp"
#include "src/Revista.hpp"
#include "src/PublicacaoImplBD.hpp"
#include "src/PublicacaoImplXML.hpp"

int main() {
    Implementador* bd = new PublicacaoImplBD();
    Implementador* xml = new PublicacaoImplXML();

    Publicacao* livro = new Livro(bd);
    Publicacao* revista = new Revista(xml);

    livro->obterDados("Livro");
    std::cout << livro->getTitulo() << std::endl;
    std::cout << livro->getAutor(1) << std::endl;

    std::cout << "-------------------" << std::endl;

    revista->obterDados("Revista");
    std::cout << revista->getTitulo() << std::endl;
    std::cout << revista->getAutor(2) << std::endl;

    return 0;
}