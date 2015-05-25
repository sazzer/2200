#include "main.h"

int main(const int argc, const char** argv) {
  std::vector<std::string> args(argv, argv + argc);
  run(args);
}
