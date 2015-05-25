#include <iostream>
#include "main.h"

void run(const std::vector<std::string>& args) {
  for (const std::string& arg : args) {
    std:: cout << "Argument: " << arg << std::endl;
  }
  std::cout << "Hello, World" << std::endl;
}
